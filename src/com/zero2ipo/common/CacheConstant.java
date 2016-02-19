package com.zero2ipo.common;

import java.util.Date;

public class CacheConstant {
 // 缓存时长配置
    public static final Date CACHE_EXPIRE_10MIN= new Date(1000*60*10);// 有效期10分钟
    public static final Date CACHE_EXPIRE_15MIN= new Date(1000*60*15);// 有效期15分钟
    public static final Date CACHE_EXPIRE_30MIN= new Date(1000*60*30);// 有效期半个小时
    public static final Date CACHE_EXPIRE_ONEDAY= new Date(1000*60*60*24);//有效期一天
    
    // 缓存key配置 
    public static final String M_KEY_USERDBROUTE= "100000027";//账户路由信息
    public static final String M_KEY_DATASOUCE= "100000028";//数据源
    public static final String M_KEY_GLOBALPRO= "100000030";//全局属性文件
    
    
    // 属性文件缓存key配置
    public static final String M_KEY_BIZSERV= "101000004";//bizservice属性文件缓存
    public static final String M_KEY_WEBSERV= "101000003";//webservice属性文件缓存
    //public static final String M_KEY_CMS_SENSITIVE = "100500004"; //敏感词
    public static final String M_KEY_SENSITIVE= "100000033"; //敏感词 

    /***********CMS 缓存key***********/
    public static final String M_KEY_CMS_SITEINFO = "100500001"; //站点信息缓存key
//    public static final String M_KEY_CMS_SITEINFO = "100500002"; //...
    
    public static final String M_KEY_CMS_LOGINUSER = "100500003"; //有审核或者发布权限的在线操作员key
    public static final String M_KEY_CMS_LOGINPASS_ERROR_COUNT = "100500004"; //登录密码错误次数
    
    public static final String M_KEY_SYSMENU = "100500005"; //系统菜单信息
}
