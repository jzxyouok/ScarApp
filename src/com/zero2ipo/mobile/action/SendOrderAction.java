package com.zero2ipo.mobile.action;

import com.zero2ipo.common.domain.Upload;
import com.zero2ipo.common.domain.exception.DomainFileUploadException;
import com.zero2ipo.common.entity.*;
import com.zero2ipo.common.entity.app.Users;
import com.zero2ipo.common.http.FmUtils;
import com.zero2ipo.core.MobileContants;
import com.zero2ipo.framework.util.DateUtil;
import com.zero2ipo.framework.util.StringUtil;
import com.zero2ipo.mobile.services.bsb.IOrderService;
import com.zero2ipo.mobile.services.bsb.ISendOrderService;
import com.zero2ipo.mobile.services.config.IConfManage;
import com.zero2ipo.mobile.services.user.IUserServices;
import com.zero2ipo.mobile.web.SessionHelper;
import com.zero2ipo.weixin.services.message.ICoreService;
import com.zero2ipo.weixin.templateMessage.TemplateData;
import com.zero2ipo.weixin.templateMessage.WxTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2015/8/31.
 */
@Controller
public class SendOrderAction {

	@Resource(name = "sendOrderService")
	private ISendOrderService sendOrderService ;
	@Autowired
	private IOrderService orderService;
	@Resource(name = "confManage")
	private IConfManage confManage;
	@Autowired
	private IUserServices userServices;
	/*
     * 核心服务接口注入
     */
	@Autowired
	public ICoreService coreService;

	/*
	 * 跨域上传
	 */
	@Resource(name = "domainUpload")
	public Upload upload;

	/**
	 * update washcar before after photo
	 */
	@RequestMapping(value = "/updateSendOrder.html", method = RequestMethod.POST)
	public String registerStep2POST(HttpServletRequest request,
									HttpServletResponse response, ModelMap model, String userCardFile, String idCardFile, String orderId,String content,RedirectAttributes redirectAttributes ) {
		FmUtils.FmData(request, model);
		ModelAndView mv=new ModelAndView("redirect:/renwu/order"+orderId+".html");
		Map<String, Object> resultMap=new HashMap<String, Object>();
		boolean flag=registerStep2(request,response,model,userCardFile,idCardFile,orderId,content);
		if(flag){
			mv.addObject("orderId", orderId);
			mv.addObject("success",true);
			redirectAttributes.addFlashAttribute("success",true);
			redirectAttributes.addFlashAttribute("orderId",orderId);
		}
		String url="renwu/order"+orderId+"/f4"+".html";
		return "redirect:/"+url;
	}
	/**
	 *开始洗车，修改洗车状态为开始洗车
	 */
	@RequestMapping(value = "/updateSendOrderStatus.html", method = RequestMethod.POST)
	public String updateSendOrderStatus(HttpServletRequest request,
										HttpServletResponse response, ModelMap model, String userCardFile, String idCardFile, String orderId,String content,RedirectAttributes redirectAttributes ) {
		FmUtils.FmData(request, model);
		ModelAndView mv=new ModelAndView("redirect:/renwu/order"+orderId+".html");
		Map<String, Object> resultMap=new HashMap<String, Object>();
		//SendOrder  sendOrder=new SendOrder();
		//sendOrder.setStatus("3");
		//sendOrder.setOrderId(orderId);
		//boolean flag= sendOrderService.updSendOrder(sendOrder);
		boolean flag=startWashCar(request, response, model, userCardFile, idCardFile, orderId, content);
		if(flag){
			mv.addObject("orderId", orderId);
			mv.addObject("success",true);
			redirectAttributes.addFlashAttribute("success",true);
			redirectAttributes.addFlashAttribute("orderId",orderId);
			//根据订单id查询订单信息
			Map<String,Object> orderMap=new HashMap<String, Object>();
			orderMap.put("id",orderId);
			Order order=orderService.findById(orderMap);
			String keyword1="";
			String keyword2="";
			String keyword3="";
			String keyword4="";
			if(!StringUtil.isNullOrEmpty(order)){
				keyword1=order.getOutTradeNo();
				keyword2=order.getWashTime();
				keyword3=order.getWashType();
			}
			//根据订单id查询洗车工信息姓名和手机号码
			Map<String, Object> queryMap=new HashMap<String, Object>();
			queryMap.put("orderId",orderId);
			AdminBo adminBo=userServices.findAdminLoginMessage(queryMap);
			String name="";
			String mobile="";
			if(!StringUtil.isNullOrEmpty(adminBo)){
				name=adminBo.getUserName();

				mobile=adminBo.getMobile();
				keyword4=name+" "+mobile;
				//发送洗车开始通知
				ConfValue confValue;
				//是否test模板消息
				String openId=getValue(CodeCommon.TEST_TEMPLATE_MESSAGE);
				if(StringUtil.isNullOrEmpty(openId)||"null".equals(openId)){
					Users userEntity=userServices.findUserByMap(orderMap);
					if(!StringUtil.isNullOrEmpty(userEntity)){
						openId=userEntity.getOpenId();
					}
				}
				String domain=getValue(CodeCommon.DOMAIN);
				String url=domain+"/f/order"+orderId+".html";
				String templateMessageId=getValue( CodeCommon.WASH_CAR_START_MESSAGE);
				WxTemplate wxTemplate=getStartWxTemplate(openId, templateMessageId, url, keyword1, keyword2,keyword3,keyword4);
				String appid=getValue(CodeCommon.APPID);
				String appscret=getValue(CodeCommon.APPSECRET);
				coreService.send_template_message(appid,appscret,openId,wxTemplate);
			}
		}
		String url="renwu/order"+orderId+"/f3"+".html";
		return "redirect:/"+url;
	}

	/**
	 * 根据key获取value
	 * @param key
	 * @return
	 */
	public String getValue(String key){
		String result="";
		Map<String, Object> m=new HashMap<String, Object>();
		m.put("confKey",key);
		ConfValue  confValue=confManage.findConfValueByMap(m);
		result=confValue.getConfValue();
		return result;
	}

	/**
	 * 洗车完成
	 * @param request
	 * @param response
	 * @param model
	 * @param userCardFile
	 * @param idCardFile
	 * @param orderId
	 * @param content
	 * @return
	 */
	public boolean registerStep2(HttpServletRequest request,
								 HttpServletResponse response, ModelMap model, String userCardFile,
								 String idCardFile,String orderId,String content) {

		String userCardUrl = "";
		String idCardUrl = "";


		try {
			//首先根据orderId查询派单任务

			String uploadDirectory = upload.getUploadFileDirectory();
			if(userCardFile != null && !"".equals(userCardFile)&&userCardFile.split("upload").length==1)
			{
				String userCardPath = uploadDirectory + userCardFile;
				upload.requestDomainUpload(request, userCardPath);
				userCardUrl = upload.getStringValue("url");
				upload.removeLocationUploadFile(userCardPath);
			}else {
				userCardUrl=userCardFile;
			}
			if(idCardFile != null && !"".equals(idCardFile)&&idCardFile.split("upload").length==1)
			{
				String idCardPath = uploadDirectory + idCardFile;
				//上传名片与身份证图片给远程服务器
				upload.requestDomainUpload(request, idCardPath);
				idCardUrl = upload.getStringValue("url");
				//上传后，删除本地存储文件
				upload.removeLocationUploadFile(idCardPath);
			}else {
				idCardUrl=idCardFile;
			}


			Object o = SessionHelper.getAttribute(request, MobileContants.ADMIN_SESSION_KEY);
			if(o != null )
			{
				//UserEntity user = (UserEntity)o;
				//user.setUserRegisterStep(3);
				SendOrder  sendOrder=new SendOrder();
				if(userCardUrl != null && !"".equals(userCardUrl))
				{
					sendOrder.setBeforePhoto(userCardUrl);
				}
				if(idCardUrl != null && !"".equals(idCardUrl))
				{
					sendOrder.setAfterPhoto(idCardUrl);
				}
				if(!StringUtil.isNullOrEmpty(orderId)){
					sendOrder.setOrderId(orderId);
					//update sender status
					sendOrder.setStatus("4");
					sendOrder.setContent(content);
				}

				boolean u = sendOrderService.updSendOrder(sendOrder);
				if(u)
				{
					//SessionHelper.setAttribute(request, MobileContants.USER_SESSION_KEY, u);
					Map<String, Object> m=new HashMap<String, Object>();
					String templateMessageId="";
					String appid="";
					String appscret="";
					m.put("confKey", CodeCommon.WASH_CAR_COMPLETE_MESSAGE);
					ConfValue confValue=confManage.findConfValueByMap(m);
					templateMessageId=confValue.getConfValue();
					m.put("confKey", CodeCommon.APPID);
					confValue=confManage.findConfValueByMap(m);
					appid=confValue.getConfValue();
					m.put("confKey", CodeCommon.APPSECRET);
					confValue=confManage.findConfValueByMap(m);
					appscret=confValue.getConfValue();
					if(!StringUtil.isNullOrEmpty(confValue)){
						String openId="";
						Map<String, Object> queryMap=new HashMap<String, Object>();
						queryMap.put("id", orderId);
						Users userEntity=userServices.findUserByMap(queryMap);
						if(!StringUtil.isNullOrEmpty(userEntity)){
							openId=userEntity.getOpenId();
						}
						SendTemplateMessage(orderId, m, templateMessageId,
								appid, appscret, openId);

					}

					return u;
				}
			}

		}  catch (DomainFileUploadException e) {
			e.printStackTrace();
		}
		return false;

	}

	/**
	 * start wash car
	 * @param request
	 * @param response
	 * @param model
	 * @param userCardFile
	 * @param idCardFile
	 * @param orderId
	 * @param content
	 * @return
	 */
	public boolean startWashCar(HttpServletRequest request,
								 HttpServletResponse response, ModelMap model, String userCardFile,
								 String idCardFile,String orderId,String content) {

		String userCardUrl = "";
		String idCardUrl = "";
		boolean flg=false;

		try {
			//首先根据orderId查询派单任务

			String uploadDirectory = upload.getUploadFileDirectory();
			if(userCardFile != null && !"".equals(userCardFile)&&userCardFile.split("upload").length==1)
			{
				String userCardPath = uploadDirectory + userCardFile;
				upload.requestDomainUpload(request, userCardPath);
				userCardUrl = upload.getStringValue("url");
				upload.removeLocationUploadFile(userCardPath);
			}else {
				userCardUrl=userCardFile;
			}
			if(idCardFile != null && !"".equals(idCardFile)&&idCardFile.split("upload").length==1)
			{
				String idCardPath = uploadDirectory + idCardFile;
				//上传名片与身份证图片给远程服务器
				upload.requestDomainUpload(request, idCardPath);
				idCardUrl = upload.getStringValue("url");
				//上传后，删除本地存储文件
				upload.removeLocationUploadFile(idCardPath);
			}else {
				idCardUrl=idCardFile;
			}


			Object o = SessionHelper.getAttribute(request, MobileContants.ADMIN_SESSION_KEY);
			if(o != null )
			{
				//UserEntity user = (UserEntity)o;
				//user.setUserRegisterStep(3);
				SendOrder  sendOrder=new SendOrder();
				if(userCardUrl != null && !"".equals(userCardUrl))
				{
					sendOrder.setBeforePhoto(userCardUrl);
				}
				if(idCardUrl != null && !"".equals(idCardUrl))
				{
					sendOrder.setAfterPhoto(idCardUrl);
				}
				if(!StringUtil.isNullOrEmpty(orderId)){
					sendOrder.setOrderId(orderId);
					//update sender status
					sendOrder.setStatus("3");
					sendOrder.setContent(content);
				}

				flg = sendOrderService.updSendOrder(sendOrder);
			/*	if(u)
				{
					//SessionHelper.setAttribute(request, MobileContants.USER_SESSION_KEY, u);
					Map<String, Object> m=new HashMap<String, Object>();
					String templateMessageId="";
					String appid="";
					String appscret="";
					m.put("confKey", CodeCommon.WASH_CAR_COMPLETE_MESSAGE);
					ConfValue confValue=confManage.findConfValueByMap(m);
					templateMessageId=confValue.getConfValue();
					m.put("confKey", CodeCommon.APPID);
					confValue=confManage.findConfValueByMap(m);
					appid=confValue.getConfValue();
					m.put("confKey", CodeCommon.APPSECRET);
					confValue=confManage.findConfValueByMap(m);
					appscret=confValue.getConfValue();
					if(!StringUtil.isNullOrEmpty(confValue)){
						String openId="";
						Map<String, Object> queryMap=new HashMap<String, Object>();
						queryMap.put("id", orderId);
						Users userEntity=userServices.findUserByMap(queryMap);
						if(!StringUtil.isNullOrEmpty(userEntity)){
							openId=userEntity.getOpenId();
						}
						SendTemplateMessage(orderId, m, templateMessageId,
								appid, appscret, openId);

					}

					return u;
				}*/
			}

		}  catch (DomainFileUploadException e) {
			e.printStackTrace();
		}
		return flg;

	}
	/**
	 * 洗车完成发送模板消息通知
	 * @param orderId
	 * @param m
	 * @param templateMessageId
	 * @param appid
	 * @param appscret
	 * @param openId
	 */
	private void SendTemplateMessage(String orderId, Map<String, Object> m,
									 String templateMessageId, String appid, String appscret,
									 String openId) {
		ConfValue confValue;
		//是否test模板消息
		m.put("confKey", CodeCommon.TEST_TEMPLATE_MESSAGE);
		ConfValue testConfValue=confManage.findConfValueByMap(m);
		//if(!StringUtil.isNullOrEmpty(testConfValue)&&!StringUtil.isNullOrEmpty(testConfValue.getConfValue())){
		//	openId=testConfValue.getConfValue();
	//	}

		m.put("confKey", CodeCommon.DOMAIN);
		confValue=confManage.findConfValueByMap(m);
		String domain=confValue.getConfValue();
		String url=domain+"/f/order"+orderId+".html";
		WxTemplate wxTemplate=getWxTemplate(openId,templateMessageId,url);

		coreService.send_template_message(appid,appscret,openId,wxTemplate);
	}

	/**
	 * 洗车完成模板
	 * @return
	 */
	public WxTemplate getWxTemplate(String openId,String msgTemplateId,String url){
		WxTemplate temp = new WxTemplate();
		temp.setTouser(openId);
		temp.setTemplate_id(msgTemplateId);
		temp.setUrl(url);
		temp.setTopcolor("#000000");

		Map<String,TemplateData> paramMap=new HashMap<String, TemplateData>();
		TemplateData data0=new TemplateData();
		data0.setValue("尊敬的客戶您好，您的爱车已经为您洗好");
		data0.setColor("#040188");
		paramMap.put("first",data0);

		TemplateData data1=new TemplateData();
		data1.setValue("已完成");
		data1.setColor("#040188");
		paramMap.put("keyword1",data1);

		TemplateData rmTime=new TemplateData();
		rmTime.setValue(DateUtil.getCurrentDate());
		rmTime.setColor("#040188");
		paramMap.put("keyword2",rmTime);
		temp.setData(paramMap);
		TemplateData remark=new TemplateData();
		remark.setValue("感谢您选择我们的服务,请您点击连接对此次服务进行评价");
		remark.setColor("#040188");
		paramMap.put("remark",remark);
		temp.setData(paramMap);

		return temp;
	}
	/**
	 * 洗车开始模板数据获取模板数据
	 * @return
	 */
	public WxTemplate getStartWxTemplate(String openId,String msgTemplateId,String url,String keyword1,String keyword2,String keyword3,String keyword4){
		WxTemplate temp = new WxTemplate();
		temp.setTouser(openId);
		temp.setTemplate_id(msgTemplateId);
		temp.setUrl(url);
		temp.setTopcolor("#000000");
		Map<String,TemplateData> paramMap=new HashMap<String, TemplateData>();
		TemplateData data0=new TemplateData();
		data0.setValue("尊敬的客户您好，师傅已开始为您的爱车服务");
		data0.setColor("#040188");
		paramMap.put("first",data0);

		TemplateData data1=new TemplateData();
		data1.setValue("已开始");
		data1.setColor("#040188");
		paramMap.put("keyword1",data1);
		TemplateData data2=new TemplateData();
		data2.setValue(keyword2);
		data2.setColor("#040188");
		paramMap.put("keyword2",data2);

		TemplateData data3=new TemplateData();
		data3.setValue(keyword3);
		data3.setColor("#040188");
		paramMap.put("keyword3",data3);

		TemplateData data4=new TemplateData();
		data4.setValue(keyword4);
		data4.setColor("#040188");
		paramMap.put("keyword4",data4);
		TemplateData remark=new TemplateData();
		remark.setValue("如有问题，请联系我们的师傅。");
		remark.setColor("#040188");
		paramMap.put("remark",remark);
		temp.setData(paramMap);
		return temp;
	}
}

