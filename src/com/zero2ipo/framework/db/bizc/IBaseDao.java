/**
 * @(#)IBaseDao.java	10:10 07/01/2013
 * 
 * Copyright (c) 2013 S9,Inc.All rights reserved.
 * Created by 2013-07-01
 */
package com.zero2ipo.framework.db.bizc;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ibatis.sqlmap.client.SqlMapClient;


/**
 * @title : 数据库公共操作接口
 * @description: 数据库IBATIS公共操作接口定义
 * @author: ZhengYunfei
 * @date: 2014-08-29
 */
public interface IBaseDao {
	
    /**
     * @title : 获取序列值接口定义
     * @description: 在MySql数据中获取指定模块的序列值
     * @param： seqName 指定模块的序列名(S9_SYS_SEQUENCE表中SEQUENCE_NAME字段)
     * @author: zhengYunFei
     * @date: 2013-07-22 17:00
     * @return： long 序列值
     */
    public long getSerialNo(String seqName) ;
    
    /**
     * @title : 获取序列值接口定义
     * @description: 在MySql数据中获取指定模块的序列值
     * @param： seqName 指定模块的序列名(S9_SYS_SEQUENCE表中SEQUENCE_NAME字段)
     * @author: liyang
     * @date: 2015-03-11
     * @return： long 序列值
     */
    public String getSerialNoLen(String seqName, int len);
    
    /**
     * @description: 带传入参数单对象查询
     * @param statementName
     * @param parameterObject ：传入参数
     * @author: ZhengYunfei
     * @date: 2014-08-29
     * @return
     * @throws org.springframework.dao.DataAccessException
     */
    public Object findForObject(String statementName, Object parameterObject);
    
    /**
     * @description: 带传入参数返回值类型单对象查询
     * @param statementName
     * @param parameterObject  ：传入参数
     * @param resultObject  ：返回值类型
     * @author: ZhengYunfei
     * @date: 2014-08-29
     * @return
     */
    public Object findForObject(String statementName, Object parameterObject,
                                Object resultObject);
    
    /**
     * @description: 带传入参数集合查询
     * @param statementName
     * @param parameterObject
     *            ：传入参数
     * @author: ZhengYunfei
     * @date: 2014-08-29
     * @return
     * @throws org.springframework.dao.DataAccessException
     */
    public List findForList(String statementName, Object parameterObject);
    
    /**
     * @description: 带传入参数分页查询
     * @param statementName
     * @param parameterObject
     * @param skipResults
     * @param maxResults
     * @author: ZhengYunfei
     * @date: 2014-08-29
     * @return
     * @throws org.springframework.dao.DataAccessException
     */
    public List findForList(String statementName, Object parameterObject,
                            int skipResults, int maxResults);
    
    /**
     * @description: 带参数插入操作
     * @param statementName
     * @param parameterObject
     * @author: ZhengYunfei
     * @date: 2014-08-29
     * @throws org.springframework.dao.DataAccessException
     */
    public Object addObject(String statementName, Object parameterObject);
    
    /**
     * @description: 带传入参数更新操作
     * @param statementName
     * @param parameterObject
     * @author: ZhengYunfei
     * @date: 2014-08-29
     */
    public int updObject(String statementName, Object parameterObject);
    
    /**
     * @description: 带传入参数，更新范围更新操作
     * @param statementName
     * @param parameterObject
     * @param requiredRowsAffected
     * @author: ZhengYunfei
     * @date: 2014-08-29
     */
    public void updObject(String statementName, Object parameterObject,
                          int requiredRowsAffected);
    
    /**
     * @description: 带传入参数删除操作
     * @param statementName
     * @param parameterObject
     * @author: ZhengYunfei
     * @date: 2014-08-29
     */
    public int delObject(String statementName, Object parameterObject);
    
    /**
     * @description:
     * @param statementName
     * @param parameterObject
     * @param requiredRowsAffected
     * @author: ZhengYunfei
     * @date: 2014-08-29
     */
    public void delObject(String statementName, Object parameterObject,
                          int requiredRowsAffected);
     
    /**
     * @description: 设置指定类型操作对应的数据源名称
     * @param dbType 操作的分库类型 eg：FwConstant.DBTYPE_*
     * @author: ZhengYunfei
     * @date: 2014-08-29
     */
    public void setDbType(String dbType) ;
    
    /**
     * @title: 获取ibatis数据层处理对象,
     * @description:用于事物开启:startTransaction(),
     *                     提交:commitTransaction(),
     *                     结束,endTransaction()
     *                     批处理: 开启批处理startBatch();
     *                             执行批处理executeBatch();
     * @return：SqlMapClient
     */
    public SqlMapClient getSqlMapClient();
}
