package com.cszjo.com.http.handler.impl;

import org.apache.log4j.Logger;

import com.cszjo.com.http.context.Context;
import com.cszjo.com.http.handler.abs.AbstractHandler;

/**  
 * @Title:  LogionHandler.java   
 * @Description: 解决login业务逻辑 
 * @author: Han   
 * @date:   2016年7月16日 下午2:08:18  
 */  
public class LogionHandler extends AbstractHandler{

	private Logger logger = Logger.getLogger(LogionHandler.class);
	
	@Override
	public void doGet(Context context) {
		logger.info("进入了handler--->LoginHandler");
		context.getResponse().setHtmlFile("login.html");
	}
}
