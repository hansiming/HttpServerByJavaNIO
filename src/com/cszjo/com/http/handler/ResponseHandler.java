package com.cszjo.com.http.handler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Date;

import org.apache.log4j.Logger;

import com.cszjo.com.http.context.Context;
import com.cszjo.com.http.context.Request;
import com.cszjo.com.http.context.Response;

/**  
 * @Title:  ResponseHandler.java   
 * @Description: 封装response响应
 * @author: Han   
 * @date:   2016年7月16日 下午2:09:45  
 */  
public class ResponseHandler {
	
	private Request request;
	private Response response;
	private String protocol;
	private int statuCode;
	private String statuCodeStr;
	private ByteBuffer buffer;
	private String serverName;
	private String contentType;
	private SocketChannel channel;
	private Selector selector;
	private SelectionKey key;
	private Logger logger = Logger.getLogger(ResponseHandler.class);
	private BufferedReader reader;
	private String htmlFile;
	
	public void write(Context context) {
		//从context中得到相应的参数
		request = context.getRequest();
		response = context.getResponse();
		buffer = ByteBuffer.allocate(1024);
		protocol = request.getProtocol();
		statuCode = response.getStatuCode();
		statuCodeStr = response.getStatuCodeStr();
		serverName = Response.SERVER_NAME;
		contentType = response.getContentType();
		key = response.getKey();
		selector = key.selector();
		channel = (SocketChannel)key.channel();
		htmlFile = response.getHtmlFile();
		
		//得到响应正文内容
		String html = setHtml(context);
		
		StringBuilder sb = new StringBuilder();
		//状态行
		sb.append(protocol + " " + statuCode + " " + statuCodeStr + "\r\n");
		//响应头
		sb.append("Server: " + serverName + "\r\n");
		sb.append("Content-Type: " + contentType + "\r\n");
		sb.append("Date: " + new Date() + "\r\n");
		if(reader != null) {
			sb.append("Content-Length: " + html.getBytes().length + "\r\n");
		}

		//响应内容
		sb.append("\r\n");
		sb.append(html);
		
		buffer.put(sb.toString().getBytes());
		//从写模式，切换到读模式
		buffer.flip();
		try {
			logger.info("生成相应\r\n" + sb.toString());
			channel.register(selector, SelectionKey.OP_WRITE);
			channel.write(buffer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String setHtml(Context context) {
		StringBuilder html = null;
		if(htmlFile != null && htmlFile.length() > 0) {
			
			html = new StringBuilder();
			
			try {
				reader = new BufferedReader(new FileReader(new File(htmlFile)));
				String htmlStr;
				htmlStr = reader.readLine();
				while(htmlStr != null) {
					html.append(htmlStr + "\r\n");
					htmlStr = reader.readLine();
				}
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return html.toString();
	}
}
