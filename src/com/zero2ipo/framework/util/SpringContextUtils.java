/*
 * Copyright (c) alongtech.com 2005
 * Created by zhengYunFei 2005-7-31
 */
package com.zero2ipo.framework.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 手动静态获取bean对象工具类
 * @author zhengYunFei
 * @version 1.0
 */
@Component
public class SpringContextUtils implements ApplicationContextAware{

    // spring 容器上下文对象
	private static ApplicationContext ctx = null;

    /**
	 * 获取spring 管理的bean对象
	 * @param name
	 * @return
	 */
	public static Object getBean(String name) {
		return ctx.getBean(name);
	}

    public void setApplicationContext(ApplicationContext context)throws BeansException {
        ctx = context;
    }
    
    public static ApplicationContext getApplicationContext() throws BeansException {
        return ctx;
    }
}
