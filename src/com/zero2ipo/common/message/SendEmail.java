package com.zero2ipo.common.message;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import freemarker.template.Template;

/**
 * 发送邮件类
 * 
 * @author liyang
 *
 */
public class SendEmail {

	private static final String fromAuth = "财富街 <pestreet@zero2ipo.com.cn>"; 
	
	private JavaMailSender sender;

	private FreeMarkerConfigurer freeMarkerConfigurer = null;// FreeMarker的技术类

	public void setFreeMarkerConfigurer(
			FreeMarkerConfigurer freeMarkerConfigurer) {
		this.freeMarkerConfigurer = freeMarkerConfigurer;
	}

	public void setSender(JavaMailSender sender) {
		this.sender = sender;
	}

	/*
	 * 生成html模板字符串
	 * 
	 * @param root 存储动态数据的map
	 * 
	 * @return
	 */
	private String getMailText(Map<String, Object> root, String templateName) {
		String htmlText = "";
		try {
			// 通过指定模板名获取FreeMarker模板实例
			Template tpl = freeMarkerConfigurer.getConfiguration().getTemplate(
					templateName);
			htmlText = FreeMarkerTemplateUtils.processTemplateIntoString(tpl,
					root);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return htmlText;
	}

	/*
	 * 发送邮件
	 * @param root
	 *            存储动态数据的map
	 * @param toEmail
	 *            邮件地址
	 * @param subject
	 *            邮件主题
	 * @return
	 */
	public boolean sendTemplateMail(Map<String, Object> root, String toEmail, String subject, String templateName) {
		try {
			MimeMessage msg = sender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(msg, false, "utf-8");	// 由于是html邮件，不是mulitpart类型
			//String nick = MimeUtility.encodeText(fromAuth, "utf-8", "B");
			//helper.setFrom(javax.mail.internet.MimeUtility.encodeText(fromAuth , "GBK","B"));
			helper.setFrom(getFromInternetAddress(fromAuth));
			helper.setTo(toEmail);
			helper.setSubject(subject);
			String htmlText = getMailText(root, templateName); 
			helper.setText(htmlText, true);
			sender.send(msg);
			return true;
		} catch (MailException e) {
			e.printStackTrace();
			return false;
		} catch (MessagingException e) {
			e.printStackTrace();
			return false;
		}
	}
	
    public final String regex1 = ".*[<][^>]*[>].*";    //判断是 xxxx <xxx>格式文本  
    public final String regex2 = "<([^>]*)>";      //尖括号匹配  
    
    /*
     * 获取发件人  
     */  
    public InternetAddress getFromInternetAddress(String from) {  
        String personal = null;  
        String address = null;  
      
        if(from.matches(regex1)){  
            personal = from.replaceAll(regex2, "").trim();  
            Matcher m = Pattern.compile(regex2).matcher(from);  
            if(m.find()){  
                address = m.group(1).trim();  
            }  
            try {  
                return new InternetAddress(address, personal, "gb2312");  
            } catch (UnsupportedEncodingException e) {  
                e.printStackTrace();  
            }  
        }else{  
            try {  
                return new InternetAddress(from);  
            } catch (AddressException e) {  
                e.printStackTrace();  
            }  
        }  
        return null;  
    }  
}