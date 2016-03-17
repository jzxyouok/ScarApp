package com.zero2ipo.eeh.Timetable.bizc.impl;

import com.zero2ipo.eeh.Timetable.bizc.ITimetableService;
import com.zero2ipo.eeh.Timetable.bo.TimetableBo;
import com.zero2ipo.framework.exception.BaseException;
import com.zero2ipo.framework.log.BaseLog;
import com.zero2ipo.mobile.dao.base.IbatisBaseDao;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/2/24.
 */
@Service("TimetableService")
public class TimetableServiceImpl extends IbatisBaseDao implements ITimetableService {
    public final static String NAMESPACE="mobile.Timetable.";
    public final static String COMMON="Timetable";
    public final static String ADD=NAMESPACE+"add_"+COMMON;
    public final static String UPDATE=NAMESPACE+"upd_"+COMMON;
    public final static String DELETE=NAMESPACE+"del_"+COMMON;
    public final static String FINDALLLIST=NAMESPACE+"find"+COMMON+"List";
    public final static String FINDALLLISTCOUNT=NAMESPACE+"find"+COMMON+"ListCount";
    public final static String FINDBYID=NAMESPACE+"find"+COMMON+"ById";


    @Override
    public boolean add(TimetableBo bo) {
        boolean flag=false;
        try{
            this.insert(ADD, bo);
            flag=true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean update(TimetableBo bo) {
        boolean flag=false;
        try{
            this.update(UPDATE, bo);
            flag=true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean delete(String ids) {
        boolean flag=false;
        try {
            Map map = new HashMap();
            map.put("id", ids.split(","));
            this.delete(DELETE, map);
            // 删除成功
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
            BaseLog.e(this.getClass(), "del_Timetable 删除出错", e);
            throw new BaseException("删除出错！", e);
        }
        return flag;
    }

    @Override
    public List<TimetableBo> findAllList(Map<String, Object> queryMap) {
        List<TimetableBo> list = null;
        try {
            list = (List<TimetableBo>) this.queryAll(FINDALLLIST, queryMap);
        } catch (Exception e) {
            e.printStackTrace();
            BaseLog.e(this.getClass(), "findAllList 查询列表", e);
            throw new BaseException("查询列表出错！", e);
        }
        return list;
    }

    @Override
    public List<TimetableBo> findAllList(Map<String, Object> queryMap, int skip, int max) {
        List<TimetableBo> list = null;
        try {
            list = (List<TimetableBo>) this.queryAll(FINDALLLIST, queryMap);
            int size=list.size();
            for(int i=0;i<size;i++){
                //  String tbId=list.get(i).getTbId();
                //根据教学楼id查询教学楼名称
                //TeachingBuildingBo teachingBuildingBo=teachingBuildingService.findById(tbId);
                //list.get(i).setTeachingBuildingBo(teachingBuildingBo);
            }
        } catch (Exception e) {
            e.printStackTrace();
            BaseLog.e(this.getClass(), "findAllList 查询列表", e);
            throw new BaseException("查询列表出错！", e);
        }
        return list;
    }

    @Override
    public int getTotal(Map<String, Object> queryMap) {
        int count = 0;
        try {
            count = (Integer) this.query(FINDALLLISTCOUNT, queryMap);
        } catch (Exception e) {
            BaseLog.e(this.getClass(), "findUserCouponListCount查询优惠券支付个数", e);
        }
        return count;
    }
    public TimetableBo findById(String id){
        TimetableBo bo=null;
        try {
            Map<String,Object> queryMap=new HashMap<String,Object>();
            queryMap.put("id", id);
            bo = (TimetableBo) this.query(FINDBYID, queryMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bo;
    }
}