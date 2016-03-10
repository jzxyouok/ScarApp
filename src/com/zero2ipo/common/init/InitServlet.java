package com.zero2ipo.common.init;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class InitServlet extends HttpServlet {
	
    @Override
    public void init(ServletConfig config) throws ServletException {
    	super.init();
    	/* 初始servlet配置信息 */
        super.init(config);
 
    }
}
