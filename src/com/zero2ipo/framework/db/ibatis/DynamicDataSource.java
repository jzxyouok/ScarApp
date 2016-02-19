/**
 * Copyright (c) 2010 CEPRI,Inc.All rights reserved.
 * Created by 2012-7-31 
 */
package com.zero2ipo.framework.db.ibatis;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @title :动态数据源路由对象
 * @author: zhengYunFei
 * @date: 2012-7-31
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return DbContext.getDBType();
    }

}