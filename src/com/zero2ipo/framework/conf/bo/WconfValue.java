/**
 * Copyright (c) 2010 CEPRI,Inc.All rights reserved.
 * Created by 2010-8-12 
 */
package com.zero2ipo.framework.conf.bo;

import java.io.Serializable;

/**
 * @title :全局参数维护配置表
 * @deprecated: 全局参数维护配置表
 * @author: zhengYunFei
 * @date: 2013-08-16
 */
public class WconfValue implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private String sortCode;//配置项分类编码
    private String confKey;//配置项关键字
    private String confValue;//配置值
    private String confDes;//配置值描述信息
    private String remark;//备注
    
    public String getSortCode() {
        return sortCode;
    }
    public void setSortCode(String sortCode) {
        this.sortCode = sortCode;
    }
    public String getConfKey() {
        return confKey;
    }
    public void setConfKey(String confKey) {
        this.confKey = confKey;
    }
    public String getConfValue() {
        return confValue;
    }
    public void setConfValue(String confValue) {
        this.confValue = confValue;
    }
    public String getConfDes() {
        return confDes;
    }
    public void setConfDes(String confDes) {
        this.confDes = confDes;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    
}
