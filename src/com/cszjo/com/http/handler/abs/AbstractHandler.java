package com.cszjo.com.http.handler.abs;

import com.cszjo.com.http.context.Context;
import com.cszjo.com.http.context.Request;
import com.cszjo.com.http.handler.Handler;
import com.cszjo.com.http.handler.ResponseHandler;

/**  
 * @Title:  AbstractHandler.java   
 * @Description: Handler抽象类
 * @author: Han   
 * @date:   2016年7月16日 下午2:11:57  
 */  
public class AbstractHandler implements Handler {
	
	protected Context context;
	
	@Override
	public void init(Context context) {
		this.context = context;
		this.service(context);
	}
	
	@Override
	public void service(Context context) {
		//通过请求方式选择具体解决方法
		String method = context.getRequest().getMethod();
		if(method.equals(Request.GET)) {
			this.doGet(context);
		} else if (method.equals(Request.POST)) {
			this.doPost(context);
		}
		sendResponse(context);
	}

	@Override
	public void doGet(Context context) {
		
	}

	@Override
	public void doPost(Context context) {
		
	}

	@Override
	public void destory(Context context) {
		context = null;
	}

	/**
	 * 通过上下文，返回封装response响应
	 * @param:  @param context  
	 * @return: void
	 * @Autor: Han
	 */
	private void sendResponse(Context context) {
		new ResponseHandler().write(context);
	}
}
