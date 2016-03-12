package com.zero2ipo.eeh.grade.bizc.impl;

import com.zero2ipo.eeh.grade.bizc.IGradeService;
import com.zero2ipo.eeh.grade.bo.GradeBo;
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
@Service("GradeService")
public class GradeServiceImpl extends IbatisBaseDao implements IGradeService {
 public final static String NAMESPACE="mobile.Grade.";
 public final static String COMMON="Grade";
 public final static String ADD=NAMESPACE+"add_"+COMMON;
 public final static String UPDATE=NAMESPACE+"upd_"+COMMON;
 public final static String DELETE=NAMESPACE+"del_"+COMMON;
 public final static String FINDALLLIST=NAMESPACE+"find"+COMMON+"List";
 public final static String FINDALLLISTCOUNT=NAMESPACE+"find"+COMMON+"ListCount";
 public final static String FINDBYID=NAMESPACE+"find"+COMMON+"ById";


    @Override
    public boolean add(GradeBo bo) {
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
    public boolean update(GradeBo bo) {
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
            BaseLog.e(this.getClass(), "del_Grade 删除出错", e);
            throw new BaseException("删除出错！", e);
        }
        return flag;
    }

    @Override
    public List<GradeBo> findAllList(Map<String, Object> queryMap) {
        List<GradeBo> list = null;
        try {
            list = (List<GradeBo>) this.queryAll(FINDALLLIST, queryMap);
        } catch (Exception e) {
            e.printStackTrace();
            BaseLog.e(this.getClass(), "findAllList 查询列表", e);
            throw new BaseException("查询列表出错！", e);
        }
        return list;
    }

    @Override
    public List<GradeBo> findAllList(Map<String, Object> queryMap, int skip, int max) {
        List<GradeBo> list = null;
        try {
            list = (List<GradeBo>) this.queryAll(FINDALLLIST, queryMap);
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
    public GradeBo findById(String id){
        GradeBo bo=null;
        try {
            Map<String,Object> queryMap=new HashMap<String,Object>();
            queryMap.put("id", id);
            bo = (GradeBo) this.query(FINDBYID, queryMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bo;
    }
}
