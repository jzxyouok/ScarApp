package com.zero2ipo.weixin.token;

import com.zero2ipo.common.entity.CodeCommon;
import com.zero2ipo.core.MobileContants;
import com.zero2ipo.mobile.dao.config.impl.ConfigDaoImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 * 初始化servlet
 *
 * @author liuyq
 * @date 2013-05-02
 */
public class InitServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory.getLogger(WeixinUtil.class);
	public void init() throws ServletException {
		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
		ConfigDaoImpl coreService= (ConfigDaoImpl) wac.getBean(ConfigDaoImpl.class);
		String appid=coreService.getValue(CodeCommon.APPID);
		String appsecret=coreService.getValue(CodeCommon.APPSECRET);
		String partnerId = coreService.getValue(CodeCommon.PartnerKey);
		String partnerValue = coreService.getValue(CodeCommon.PartnerValue);
		String prePayBody = coreService.getValue(CodeCommon.PREPAY_BODY);
		String domain=coreService.getValue(CodeCommon.DOMAIN);
		wac.getServletContext().setAttribute(MobileContants.APPID_KEY,appid);//保存appid到缓存中
		wac.getServletContext().setAttribute(MobileContants.PARTNERID_KEY,partnerId);//保存商户id到缓存中
		wac.getServletContext().setAttribute(MobileContants.PARTNERVALUE_KEY,partnerValue);//保存商户秘钥到缓存中
		wac.getServletContext().setAttribute(MobileContants.PREPAYBODY_KEY,prePayBody);//保存微信支付主体到缓存中
		wac.getServletContext().setAttribute(MobileContants.DOMAIN_KEY,domain);//保存域名到缓存中

		// 获取web.xml中配置的参数
		//TokenThread.appid = getInitParameter("appid");
		TokenThread.appid = appid;
		//TokenThread.appsecret = getInitParameter("appsecret");
		TokenThread.appsecret = appsecret;

		log.info("weixin api appid:{}", TokenThread.appid);
		log.info("weixin api appsecret:{}", TokenThread.appsecret);

		// 未配置appid、appsecret时给出提示
		if ("".equals(TokenThread.appid) || "".equals(TokenThread.appsecret)) {
			log.error("appid and appsecret configuration error, please check carefully.");
		} else {
			// 启动定时获取access_token的线程
			new Thread(new TokenThread()).start();
		}
	}

}
