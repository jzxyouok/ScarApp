/**
 * @(#)BaseDaoImpl.java	10:10 07/01/2013
 * 
 * Copyright (c) 2013 S9,Inc.All rights reserved.
 * Created by 2013-07-01
 */
package com.zero2ipo.framework.db.bizc.impl;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Service;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.zero2ipo.framework.db.bizc.IBaseDao;
import com.zero2ipo.framework.db.ibatis.DbContext;
import com.zero2ipo.framework.exception.BaseException;
import com.zero2ipo.framework.log.BaseLog;
import com.zero2ipo.framework.util.StringUtil;

/**
 * @title :数据库公共操作接口实现类
 * @description: 数据库IBATIS公共操作接口实现类
 * @author: ZhengYunfei
 * @date: 2014-08-29
 */
@Service("baseDao")
public class BaseDaoImpl extends SqlMapClientTemplate implements IBaseDao {
	
	//填充数组大小
    private int size = 100;
    
    //填充指定模块序列的序列值String:模块序列名 Long:序列值
    private Hashtable<String, Long> seqNos = new Hashtable<String, Long>();
     
    //记录指定模块序列的大小String:模块序列名 Integer:填充数组的大小
    private Hashtable<String, Integer> lens = new Hashtable<String, Integer>();
    
    /**
     * @title : 填充数组
     * @description: 获取数据中指定模块的序列值 并填充数组
     * @param： seqName 指定模块的序列名
     * @author: ZhengYunfei
     * @date: 2014-08-29
     * @return： void
     */
    @SuppressWarnings("unchecked")
	private synchronized void fill(String seqName) {
        try {
        	//获取指定模块序列名的当前序列值
            List list = super.queryForList("getSequenceVal", seqName);
            if (list != null && (list.size() > 0 && list.size() < size)) {
                String strSeq = list.get(0).toString();
                //填充指定模块序列的序列值
                seqNos.put(seqName, Long.valueOf(strSeq));
            }
            //填充完该数组后，更新该模块的当前序列值
            super.update("updSequenceVal", seqName) ;
        } catch (Exception e) {
            BaseLog.e(this.getClass(), "fill():取序列错误", e);
            throw new BaseException("取序列错误", e);
        }
    }
    
    /**
     * @title : 获取序列值操作
     * @description: 获取数据中指定模块的序列值
     * @param： seqName 指定模块的序列名(S9_SYS_SEQUENCE表中SEQUENCE_NAME字段)
     * @param: len   指定序列值的长度
     * @param: head  序列值前缀:年月日
     * @author: ZhengYunfei
     * @date: 2014-08-29
     * @return： String 序列值
     */
    private synchronized String getSerialNo(String seqName, int len, String head) {
        long returnVal;
        if (lens.containsKey(seqName) && lens.get(seqName) > 0) {
            // 从数组里面取值
            returnVal = seqNos.get(seqName);
            seqNos.put(seqName, returnVal + 1);
            lens.put(seqName, lens.get(seqName) - 1);
        } else {
            // 数组已经取完重新填充数组
            fill(seqName);
            lens.put(seqName, size);
            returnVal = seqNos.get(seqName);
            seqNos.put(seqName, returnVal + 1);
            lens.put(seqName, lens.get(seqName) - 1);
        }
        return head + StringUtil.zeroPadString(String.valueOf(returnVal), len);
    }
    
    /**
     * @title : 获取序列值接口定义
     * @description: 在MySql数据中获取指定模块的序列值
     * @param： seqName 指定模块的序列名(S9_SYS_SEQUENCE表中SEQUENCE_NAME字段)
     * @author: liyang
     * @date: 2015-03-11
     * @return： long 序列值
     */
    public synchronized String getSerialNoLen(String seqName, int len) {
    	String serialNo = "";
    	if(seqName != null && !"".equals(seqName)) 
    	{
    		serialNo  = (String) super.queryForObject("getSequenceVal", seqName);
    		Map<String, Object> params = new HashMap<String, Object>();
    		params.put("seqName", seqName);
    		params.put("len", len);
    		super.update("updSequenceValLen", params);
    	}
    	else 
    	{
    		BaseException e = new BaseException("传入的序列名无效");
            BaseLog.e(this.getClass(), "getSerialNo:传入的序列名无效",e);
            throw e;
    	}
    	return serialNo;
    }
    
    /**
     * @title : 获取序列值接口
     * @description: 在MySql数据中获取指定模块的序列值
     * @param： seqName 指定模块的序列名(S9_SYS_SEQUENCE表中SEQUENCE_NAME字段)
     * @author: ZhengYunfei
     * @date: 2014-08-29
     * @return： long 序列值
     */
    public long getSerialNo(String seqName) {
        long serialNo = 0;
        if (null == (seqName) || "".equals(seqName)) {
            BaseException e = new BaseException("传入的序列名无效");
            BaseLog.e(this.getClass(), "getSerialNo:传入的序列名无效",e);
            throw e;
        } else {
            Calendar cal = Calendar.getInstance();
            serialNo = Long.parseLong(this.getSerialNo(seqName, 12,
                        String.valueOf(cal.get(Calendar.YEAR))));
        }
        return serialNo;
    }
    
	
    /**
     * @description: 带传入参数查询
     * @param statementName
     * @param parameterObject ：传入参数
     * @author: ZhengYunfei
     * @date: 2014-08-29
     */
    public Object findForObject(String statementName, Object parameterObject) {
    	try {
    		 return super.queryForObject(statementName,parameterObject);
		} catch (Exception e) {
			 e.printStackTrace();
			 BaseLog.e(this.getClass(), "findForObject", e);
	         throw new BaseException("传入参数查询错误，或数据库连接异常");
		}
    }
    
    /**
     * @description:带传入参数返回值查询
     * @param statementName
     * @param parameterObject：传入参数
     * @param resultObject：返回值类型
     * @author: ZhengYunfei
     * @date: 2014-08-29
     */
    public Object findForObject(String statementName, Object parameterObject,
            Object resultObject) {
    	try {
    		return super.queryForObject(statementName,parameterObject, resultObject);
		} catch (Exception e) {
			BaseLog.e(this.getClass(), "findForObject:resultObject", e);
	        throw new BaseException("带传入参数返回值查询错误，或数据库连接异常");
		}
    }
    
    /**
     * @description: 带传入参数集合查询
     * @param statementName
     * @param parameterObject：传入参数
     * @author: ZhengYunfei
     * @date: 2014-08-29
     * @return
     */
    public List findForList(String statementName, Object parameterObject) {
    	try {
    		return super.queryForList(statementName, parameterObject);
		} catch (Exception e) {
			e.printStackTrace();
			BaseLog.e(this.getClass(), "findForList", e);
	        throw new BaseException("带传入参数集合查询错误，或数据库连接异常");
		}
        
    }
    
    /**
     * @description: 带传入参数分页查询
     * @param statementName
     * @param parameterObject
     * @param skipResults ：跳过记录数
     * @param maxResults ：每页最多记录数
     * @author: ZhengYunfei
     * @date: 2014-08-29
     * @return
     */
    public List findForList(String statementName, Object parameterObject,
            int skipResults, int maxResults) {
    	try {
    		return super.queryForList(statementName,parameterObject, skipResults, maxResults);
		} catch (Exception e) {
			e.printStackTrace();
			BaseLog.e(this.getClass(), "findForList分页", e);
	        throw new BaseException("带传入参数分页查询错误，或数据库连接异常");
		}
        
    }
    
    public Map findForMap(String statementName, Object parameterObject,
            String keyProperty) {
    	try {
    		return super.queryForMap(statementName, parameterObject, keyProperty);
		} catch (Exception e) {
			e.printStackTrace();
			BaseLog.e(this.getClass(), "findForMap", e);
	        throw new BaseException("带传入参数的map查询错误，或数据库连接异常");
		}
    }
    
    public Map findForMap(String statementName, Object parameterObject,
            String keyProperty, String valueProperty) {
    	try {
    		return super.queryForMap(statementName,parameterObject, keyProperty, valueProperty);
		} catch (Exception e) {
			e.printStackTrace();
			BaseLog.e(this.getClass(), "findForMap：带返回值", e);
	        throw new BaseException("带返回值的map查询错误，或数据库连接异常");
		}
    }
    
    /**
     * @description: 带传入参数插入操作插入操作
     * @param statementName
     * @param parameterObject
     * @author: ZhengYunfei
     * @date: 2014-08-29
     * @return
     */
    public Object addObject(String statementName, Object parameterObject) {
    	try {
    		return super.insert(statementName, parameterObject);
		} catch (Exception e) {
			e.printStackTrace();
			BaseLog.e(this.getClass(), "addObject", e);
	        throw new BaseException("带传入参数插入操作插入操作错误，或数据库连接异常");
		}
    }
    
    /**
     * @description:更新对象
     * @param statementName
     * @param parameterObject
     * @author: ZhengYunfei
     * @date: 2014-08-29
     * @return
     */
    public int updObject(String statementName, Object parameterObject) {
    	try {
    		return super.update(statementName,parameterObject);
		} catch (Exception e) {
			e.printStackTrace();
			BaseLog.e(this.getClass(), "updObject", e);
	        throw new BaseException("更新对象错误，或数据库连接异常");
		}
    }
    
    public void updObject(String statementName, Object parameterObject,
            int requiredRowsAffected) {
    	try {
    		super.update(statementName,parameterObject, requiredRowsAffected);
		} catch (Exception e) {
			e.printStackTrace();
			BaseLog.e(this.getClass(), "updObject:requiredRowsAffected", e);
	        throw new BaseException("更新对象错误，或数据库连接异常");
		}
    }
    
    /**
     * @description: 删除对象
     * @param statementName
     * @param parameterObject
     * @author: ZhengYunfei
     * @date: 2014-08-29
     * @return
     */
    public int delObject(String statementName, Object parameterObject) {
    	try {
    		return super.delete(statementName,parameterObject);
		} catch (Exception e) {
			e.printStackTrace();
			BaseLog.e(this.getClass(), "delObject", e);
	        throw new BaseException("删除对象错误，或数据库连接异常");
		}
    }
    
    public void delObject(String statementName, Object parameterObject,
            int requiredRowsAffected) {
    	try {
    		super.delete(statementName,parameterObject, requiredRowsAffected);
		} catch (Exception e) {
			e.printStackTrace();
			BaseLog.e(this.getClass(), "delObject:requiredRowsAffected", e);
	        throw new BaseException("删除对象错误，或数据库连接异常");
		}
    }
    /**
     * @description: 设置指定类型操作对应的数据源名称
     * @param dbType 操作的分库类型 eg：FwConstant.DBTYPE_*
     * @author: ZhengYunfei
     * @date: 2014-08-29
     */
    @SuppressWarnings("unchecked")
    public void setDbType(String dbType) {
        DbContext.setDBType(dbType);
    }
    
    /**
     * @title: 获取ibatis数据层处理对象,
     * @description:用于事物开启,提交,回滚,批处理等操作
     * @author: ZhengYunfei
     * @date: 2014-08-29
     * @return：taskey
     */
    public SqlMapClient getSqlMapClient(){
        return super.getSqlMapClient();
    }

	@Autowired @Qualifier("sqlMapClient")
	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		super.setSqlMapClient(sqlMapClient);
	}
}