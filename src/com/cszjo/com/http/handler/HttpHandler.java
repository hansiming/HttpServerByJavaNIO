package com.cszjo.com.http.handler;

import java.nio.channels.SelectionKey;

import org.apache.log4j.Logger;

import com.cszjo.com.http.context.Context;
import com.cszjo.com.http.context.impl.HttpContext;
import com.cszjo.com.http.handler.impl.NotFoundHandler;

/**  
 * @Title:  HandlerHttp.java   
 * @Description: 处理一次Http请求 
 * @author: Han   
 * @date:   2016年7月15日 下午7:07:21  
 */  
public class HttpHandler implements Runnable {

	//就绪的I/O键
	private SelectionKey key;
	//上下文
	private Context context = new HttpContext();
	//http请求字符串
	private String requestHeader;
	//针对uri选择不同的处理器
	private Handler handler;
	private Logger logger = Logger.getLogger(HttpHandler.class);
	
	public HttpHandler(String requestHeader, SelectionKey key) {
		this.key = key;
		this.requestHeader = requestHeader;
	}

	@Override
	public void run() {
		//初始化上下文
		context.setContext(requestHeader, key);
		//得到uri
		String uri = context.getRequest().getUri();
		logger.info("得到了uri " + uri);
		//得到MapHandler集合(uri-->handler)
		handler = MapHandler.getContextMapInstance().getHandlerMap().get(uri);
		//找不到对应的handler
		if(handler == null) {
			//404Handler进行处理
			handler = new NotFoundHandler();
		}
		//初始化handler并执行
		handler.init(context);
	}
}
