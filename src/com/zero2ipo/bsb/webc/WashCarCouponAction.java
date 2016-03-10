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

import com.zero2ipo.bsb.bizc.ICouponDao;
import com.zero2ipo.bsb.entity.Car;
import com.zero2ipo.bsb.entity.GgwashCoupon;
import com.zero2ipo.common.web.BaseCtrl;
import com.zero2ipo.framework.log.BaseLog;
import com.zero2ipo.framework.util.StringUtil;
/**
 * 洗车券功能
 * @author zhengyunfei
 *
 */
@Controller
@RequestMapping("/coupon")
public class WashCarCouponAction extends BaseCtrl{
	
	@Autowired @Qualifier("couponDao")
	private ICouponDao couponDao;  
	/**
	 * 初始化页面
	 * @return
	 */
	@RequestMapping("forInit.shtml")
    public ModelAndView forInit(){
        return new ModelAndView("/s9/coupon/list.jsp");
    }
    /**
     * 修改页面跳转
     * @param id
     * @return
     */
   @RequestMapping("forUpdateInitPage.shtml")
   public ModelAndView forUpdateInitPage(String id) {
   	ModelAndView mv = new ModelAndView("/s9/coupon/update.jsp");
   	GgwashCoupon bo=null;
	try {
		bo=couponDao.findById(id);
		if(StringUtil.isNullOrEmpty(bo)){
			bo=new GgwashCoupon();
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	mv.addObject("bo",bo);
	return mv;
   }
   /**
    * 新增页面跳转
    * @param id
    * @return
    */
  @RequestMapping("forAddInitPage.shtml")
  public ModelAndView forUpdateInitPage() {
  	ModelAndView mv = new ModelAndView("/s9/coupon/add.jsp");
	return mv;
  }
  /**
   * 保存洗车券
   * @param curNo
   * @param curSize
   * @return
   */

  @RequestMapping("addSave.shtml")
  @ResponseBody
  public Map<String,Object> addSave(GgwashCoupon bo) {
	Map<String,Object> resultMap=new HashMap<String,Object>();
  	try{
  		boolean flag=false;
  		flag=couponDao.add(bo);
		resultMap.put("success", flag);
  	}catch (Exception e) {
  		e.printStackTrace();
  		resultMap.put("success", false);
	}
	return resultMap;
  }
  	/**
  	 * ajax查询洗车卷
  	 * @param curNo
  	 * @param curSize
  	 * @return
  	 */
    @RequestMapping("findAllList.shtml")
    @ResponseBody
    public Map<String,Object>  findAllList(String curNo, String curSize){
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
        	int total=0;
        	total=couponDao.findAllListCount(map);
        	List<GgwashCoupon> list= null;
        	if(total>0){
        		list = couponDao.findAllListPage(map, (skip-1)*max, max);
        	}
        	jsonMap.put("Rows", list);
    		jsonMap.put("Total", total);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonMap;
    }
    /**
     * 修改洗车券
     * @param curNo
     * @param curSize
     * @return
     */
    @RequestMapping("updCoupon.shtml")
    @ResponseBody
    public Map<String,Object> updCoupon(GgwashCoupon bo) {
 	Map<String,Object> resultMap=new HashMap<String, Object>();
    	try{
    		boolean flag=false;
    		flag=couponDao.update(bo);
 			resultMap.put("success", flag);
    	}catch (Exception e) {
    		e.printStackTrace();
    		resultMap.put("result", false);
 		}
 		return resultMap;
    }
    /**
     * 删除洗车券
     * @param curNo
     * @param curSize
     * @return
     */
    @RequestMapping("delCoupon.shtml")
    @ResponseBody
    public Map<String,Object> delCoupon(String ids) {
 	Map<String,Object> resultMap=new HashMap<String, Object>();
    	try{
    		boolean flag=false;
    		flag=couponDao.delete(ids);
 			resultMap.put("success", flag);
    	}catch (Exception e) {
    		e.printStackTrace();
    		resultMap.put("success", false);
 		}
 		return resultMap;
    }
    
}
