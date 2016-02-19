/**
 * Copyright (c) 2010 CEPRI,Inc.All rights reserved.
 * Created by 2010-8-8 
 */
package com.zero2ipo.framework.db.ibatis;

import java.io.Serializable;

/**
 * @title :数据源上下文对象,通过本地线程 保存切换后的数据源
 * @author: zhengYunFei
 * @date: 2012-7-31
 */
public class DbContext implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();    
        
    public static void setDBType(String customerType) {    
      contextHolder.set(customerType);    
    }    
        
    public static String getDBType() { 
        return contextHolder.get();    
    }    
        
    public static void clearDBType() {    
      contextHolder.remove();    
    } 
    
}
