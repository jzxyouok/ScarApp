package com.zero2ipo.mobile.action;

import com.zero2ipo.core.MobileContants;
import com.zero2ipo.weixin.token.AccessToken;
import com.zero2ipo.weixin.token.TokenThread;
import com.zero2ipo.weixin.utils.GetImage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * 手机端主页调整控制
 * @author zhengyunfei
 * @date 2015-04-22
 *
 */

@Controller
public class DownLoadImage {
	public  final static String DOWNLOAD_WEIXIN_IMAGE="http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";
	/**
	 * download image to local server computer
	 * @param request
	 * @param response
	 * @param model
     * @return
     */
	@RequestMapping(value = "/bfpic/downimage.html", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> downloadImageToLocalServer(HttpServletRequest request,
														 HttpServletResponse response, ModelMap model, String media_id) {
		Map<String,Object> map=new HashMap<String, Object>();
		System.out.println("方法进来了嘛》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》media_id==="+media_id);
		try{
			//String localPath=System.getProperty("user.dir").replace("bin", "webapps/upload/");
			//通过media_id获取网络图片url
			String appId=request.getSession().getAttribute(MobileContants.APPID_KEY)+"";
			//String appSecret=request.getSession().getAttribute(MobileContants.APPSCRET_KEY)+"";
			AccessToken token= TokenThread.accessToken;
			String access_token = token.getToken();
			System.out.println("token==========================="+access_token);
			String requestUrl =DOWNLOAD_WEIXIN_IMAGE;
			String args[]=media_id.split(",");
			int count=args.length;
			for(int i=0;i<count;i++){
				requestUrl = requestUrl.replace("ACCESS_TOKEN",access_token).replace("MEDIA_ID",args[i]);
				String base64Image= GetImage.downImageForNetUrl(requestUrl,media_id);
				map.put("bfPic"+i,base64Image);
			}
			map.put("success",true);
		}catch (Exception e){
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * download image to local server computer
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/afpic/downimage.html", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> afpicDownloadImageToLocalServer(HttpServletRequest request,
														 HttpServletResponse response, ModelMap model, String media_id) {
		Map<String,Object> map=new HashMap<String, Object>();
		System.out.println("方法进来了嘛》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》media_id==="+media_id);
		try{
			//String localPath=System.getProperty("user.dir").replace("bin", "webapps/upload/");
			//通过media_id获取网络图片url
			String appId=request.getSession().getAttribute(MobileContants.APPID_KEY)+"";
			//String appSecret=request.getSession().getAttribute(MobileContants.APPSCRET_KEY)+"";
			AccessToken token= TokenThread.accessToken;
			String access_token = token.getToken();
			System.out.println("token==========================="+access_token);
			String requestUrl =DOWNLOAD_WEIXIN_IMAGE;
			String args[]=media_id.split(",");
			int count=args.length;
			for(int i=0;i<count;i++){
				requestUrl = requestUrl.replace("ACCESS_TOKEN",access_token).replace("MEDIA_ID",args[i]);
				String base64Image= GetImage.downImageForNetUrl(requestUrl,media_id);
				map.put("afPic"+i,base64Image);
			}
			map.put("success",true);
		}catch (Exception e){
			e.printStackTrace();
		}
		return map;
	}


}
