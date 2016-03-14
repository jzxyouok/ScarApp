package com.zero2ipo.eeh.course.web;

import com.zero2ipo.common.web.BaseCtrl;
import com.zero2ipo.eeh.classroom.bizc.IClassRoomService;
import com.zero2ipo.eeh.course.bizc.ICourseService;
import com.zero2ipo.eeh.course.bo.CourseBo;
import com.zero2ipo.framework.exception.BaseException;
import com.zero2ipo.framework.log.BaseLog;
import com.zero2ipo.framework.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 教学楼ctrl
 * Created by Administrator on 2016/2/24.
 */
@Controller
@RequestMapping("/Course")
public class CourseCtrl extends BaseCtrl {
    @Autowired
    @Qualifier("CourseService")
    private ICourseService CourseService ;

    @Autowired
    @Qualifier("classRoomService")
    private IClassRoomService classRoomService;

    /**
     * 初始化页面
     * @return
     */
    @RequestMapping("forInit.html")
    public ModelAndView forInitPage() {
        ModelAndView mv = new ModelAndView("/eeh/Course/list.jsp");
        //查询所有班级
       // getSelectGradeList(mv);
        return mv;
    }
    /**
     * 初始化页面
     * @return
     */
    @RequestMapping("peiyouke.html")
    public ModelAndView peiyouke() {
        ModelAndView mv = new ModelAndView("/eeh/Course/pewClass.jsp");
        return mv;
    }
    /**
     * 初始化json数据
     * @param curNo
     * @param curSize
     * @return
     */
    @RequestMapping("findAllList.html")
    @ResponseBody
    public Map<String,Object> findAllList(CourseBo bo){
        Map<String,Object> jsonMap = new HashMap<String, Object>();
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            if(!StringUtil.isNullOrEmpty(bo)){
                String kemu=bo.getKemu();
                String classRoom=bo.getClassRoom();
                String seatSetStatus=bo.getSeatSetStatus();
                String week=bo.getWeek();
                if(!StringUtil.isNullOrEmpty(kemu)){
                    map.put("kemu",kemu);
                }
                if(!StringUtil.isNullOrEmpty(week)){
                    map.put("week",week);
                }
                if(!StringUtil.isNullOrEmpty(classRoom)){
                    map.put("classRoom",classRoom);
                }
                if(!StringUtil.isNullOrEmpty(seatSetStatus)){
                    map.put("seatSetStatus",seatSetStatus);
                }
            }
            int total=0;
            total=CourseService.getTotal(map);
            List<CourseBo> list= null;
            if(total>0){
                list = CourseService.findAllList(map);
            }
            jsonMap.put("result", list);
        } catch (Exception e) {
            e.printStackTrace();
            BaseLog.e(this.getClass(), "forLinkTypeinitAjax.html:管理人分类信息初始化有误", e);
        }
        return jsonMap;
    }
 /******************************************新增*********************************************************/
    /**
     * 新增页面初始化
     * @return
     */
    @RequestMapping("forAddInitPage.html")
    public ModelAndView forAddInitPage() {
        ModelAndView mv = new ModelAndView("/eeh/Course/add.jsp");
        return mv;
    }
    /**
     * 添加保存数据
     * @param bo
     * @return
     */
    @RequestMapping("forAddAjax.html")
    @ResponseBody
    public Map<String,Object> addSave(CourseBo bo) {
        Map<String,Object> result=new HashMap<String,Object>();
        boolean flg=false;
        try {
            flg=CourseService.add(bo);
        } catch (Exception e) {
            flg=false;
            e.printStackTrace();
            BaseLog.e(this.getClass(), "saveOrder", e);
            throw new BaseException("saveOrder 异常！");
        }
        result.put("success", flg);
        return result;
    }
    /******************************************修改*********************************************************/
    /**
     * 新增页面初始化
     * @return
     */
    @RequestMapping("forUpdateInitPage.html")
    public ModelAndView forUpdateInitPage(String id) {
        ModelAndView mv = new ModelAndView("/eeh/Course/update.jsp");
        CourseBo bo=CourseService.findById(id);
        mv.addObject("bo", bo);
        return mv;
    }


    /**
     * 修改保存数据
     * @param bo
     * @return
     */
    @RequestMapping("forUpdateAjax.html")
    @ResponseBody
    public Map<String,Object> updateSave(CourseBo bo) {
        Map<String,Object> result=new HashMap<String,Object>();
        boolean flg=false;
        try {
            flg=CourseService.update(bo);
        } catch (Exception e) {
            flg=false;
            e.printStackTrace();
            BaseLog.e(this.getClass(), "forUpdateAjax", e);
            throw new BaseException("forUpdateAjax 异常！");
        }
        result.put("success", flg);
        return result;
    }
    /******************************************删除操作*********************************************************/

    @RequestMapping("delSave.html")
    @ResponseBody
    public Map<String,Object> delSave(String ids) {
        Map<String,Object> result=new HashMap<String,Object>();
        boolean flg=false;
        try {
            flg=CourseService.delete(ids);
        } catch (Exception e) {
            flg=false;
            e.printStackTrace();
            BaseLog.e(this.getClass(), "delSave", e);
            throw new BaseException("delSave 异常！");
        }
        result.put("success", flg);
        return result;
    }
}
