package com.zero2ipo.bsb.webc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zero2ipo.bsb.bizc.IHistoryDao;
import com.zero2ipo.bsb.entity.Car;
import com.zero2ipo.common.web.BaseCtrl;
import com.zero2ipo.framework.log.BaseLog;
import com.zero2ipo.framework.util.StringUtil;

@Controller
@RequestMapping("/car")
public class CarAction extends BaseCtrl{
	
	@Autowired @Qualifier("historyDao")
	private IHistoryDao historyDao;  
	@RequestMapping("forInit.shtml")
    public ModelAndView forInit(){
        return new ModelAndView("/s9/paidan/list.jsp");
    }
    /**
     * 修改页面跳转
     * @param id
     * @return
     */
   @RequestMapping("forUpdateInitPage.shtml")
   public ModelAndView forUpdateInitPage(String status,String preTime) {
   	ModelAndView mv = new ModelAndView("/s9/paidan/update.jsp");
	try {
		//用户信息修改初始化
		Car bo=new Car();
		bo.setStatus(status);
		bo.setPreTime(preTime);
		mv.addObject("bo", bo);
	} catch (Exception e) {
		e.printStackTrace();
	    BaseLog.e(this.getClass(), "upSysOperInit 修改管理人查询",e);
	}
	return mv;
   }
    @RequestMapping("findAllList.shtml")
    @ResponseBody
    public Map<String,Object>  findAllList(String curNo, String curSize, String name,String shortName,String title,String sendStartTime,String sendEndTime){
    	 Map<String,Object> jsonMap = new HashMap<String, Object>();
    	 try {
    		 /************* 分页处理 ************/
 			int skip;
 			int max;
 			if (StringUtil.isNullOrEmpty(curNo))
 				curNo = "0";
 			if (StringUtil.isNullOrEmpty(curSize))
 				curSize = "20";
 			skip = Integer.parseInt(curNo);
 			max = Integer.parseInt(curSize);
 			/************  分页处理结束 ***********/
			Map<String, Object> map = new HashMap<String, Object>();
			if(!StringUtil.isNullOrEmpty(name)){
				map.put("name", name);	//产品名称
			}
			if(!StringUtil.isNullOrEmpty(shortName)){
				map.put("shortName",shortName);
			}
			if(!StringUtil.isNullOrEmpty(title)){
				map.put("title",title);
			}
			if(!StringUtil.isNullOrEmpty(sendStartTime)){
				map.put("sendStartTime",sendStartTime+" 00:00:00");
			}
			if(!StringUtil.isNullOrEmpty(sendEndTime)){
				map.put("sendEndTime",sendEndTime+" 23:59:59");
			}
        	int total=0;
        	total=historyDao.findAllListCount(map);
        	List<Car> list= null;
        	if(total>0){
        		list = historyDao.findAllListPage(map, (skip-1)*max, max);
        	}
        	jsonMap.put("Rows", list);
    		jsonMap.put("Total", total);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonMap;
    }
    
    @RequestMapping("updCar.shtml")
    @ResponseBody
    public Map<String,Object> updRm(Car bo) {
 	Map<String,Object> resultMap=new HashMap<String, Object>();
    	try{
    			historyDao.update(bo);
 			resultMap.put("result", true);
    	}catch (Exception e) {
    		BaseLog.e(this.getClass(), "updUser修改管理人信息失败", e);
    		resultMap.put("result", false);
 		}
 		return resultMap;
    }
}
