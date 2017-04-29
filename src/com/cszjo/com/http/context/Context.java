package com.cszjo.com.http.context;

import java.nio.channels.SelectionKey;

/**  
 * @Title:  Context.java   
 * @Description: Http上下文抽象类
 * @author: Han   
 * @date:   2016年7月16日 下午2:19:06  
 */  
public abstract class Context {
	
	protected Request request;
	protected Response response;
	
	/**
	 * 设置当前连接的上下文
	 * @param:  @return  
	 * @return: Context
	 * @Autor: Han
	 */
	public abstract void setContext(String requestHeader, SelectionKey key);
	
	/**
	 * 得到Request
	 * @param:  @return  
	 * @return: Request
	 * @Autor: Han
	 */
	public Request getRequest() {
		return request;
	}
	
	/**
	 * 得到Response
	 * @param:  @return  
	 * @return: Response
	 * @Autor: Han
	 */
	public Response getResponse() {
		return response;
	}

}
