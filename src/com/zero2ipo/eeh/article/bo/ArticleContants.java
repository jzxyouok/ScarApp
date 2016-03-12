package com.zero2ipo.eeh.article.bo;

import java.util.HashMap;
import java.util.Map;

/**
    * eehArticle 实体类
    * Fri Feb 26 14:23:18 GMT+08:00 2016
    */
public class ArticleContants {
	public static final String ARTICLE_TYPE_01="1";
	public static final String ARTICLE_TYPE_02="2";
	public static final String ARTICLE_TYPE_03="3";
	public static final String ARTICLE_TYPE_04="4";
	public final static Map articleMap = new HashMap() {{
		put(ARTICLE_TYPE_01, "通知");
		put(ARTICLE_TYPE_02, "公告");
		put(ARTICLE_TYPE_03, "班级风采");
		put(ARTICLE_TYPE_04, "班级明星");
	}};

}

