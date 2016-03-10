package com.zero2ipo.framework.conf;

import java.util.ArrayList;
import java.util.Hashtable;

import com.zero2ipo.common.CacheConstant;
import com.zero2ipo.framework.FwConstant;
import com.zero2ipo.framework.cache.CacheUtil;
import com.zero2ipo.framework.conf.bo.WconfValue;
import com.zero2ipo.framework.db.bizc.IBaseDao;
import com.zero2ipo.framework.log.BaseLog;
import com.zero2ipo.framework.util.SpringContextUtils;
/**
 * @title : 针对全局配置信息工具类
 * @deprecated: 针对全局配置信息工具类
 * @author: zhengYunFei
 * @date: 2013-08-19 12:30
 */
public class ConfUtil {
	/**
     * @description: 通过属性名称获取属性值
     * @param proName 属性名
     * @return：value 属性值
     */
    public static String getProperties(String proName) {
        String proVal = null;
        if(CacheUtil.connStat()){// 缓存是否可用
            Hashtable<String, String> gMap = null;
        	if (CacheUtil.keyExists(CacheConstant.M_KEY_GLOBALPRO)) {// 全局属性数据缓存项是否存在
        	    gMap = (Hashtable<String, String>)CacheUtil.findObject(CacheConstant.M_KEY_GLOBALPRO);
        	}else{
                gMap = initGlobal();//初始化数据从数据库到缓存
                CacheUtil.addObject(CacheConstant.M_KEY_GLOBALPRO, gMap);
            }
        	if(gMap != null){
				proVal =  gMap.get(proName);
    		}
        }else{
            // 缓存不可用时直接去数据库查询
            Hashtable<String, String> gMap = initGlobal();
            proVal = gMap.get(proName);
        }
        return proVal;
    }
    
    /**
     * @title: 将global配置文件中的信息加载入缓存，该方法系统启动时自动运行
     */
    public static Hashtable<String, String> initGlobal(){
        Hashtable<String, String> ht = null;
        try {
            IBaseDao baseDao = (IBaseDao) SpringContextUtils.getBean("baseDao");
            baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
            ArrayList<WconfValue> al = (ArrayList<WconfValue>)baseDao.findForList("findGconfMap", null);
            ht = new Hashtable<String, String>();
            for (WconfValue wc : al) {
                String key = wc.getConfKey(); 
                if(key!=null){
                    ht.put(key, wc.getConfValue() == null ? "" : wc.getConfValue());
                }
            }
        } catch (Exception e) {
            BaseLog.e(ConfUtil.class, "initGlobal", e);
        }
        return ht;
    }
    

}
