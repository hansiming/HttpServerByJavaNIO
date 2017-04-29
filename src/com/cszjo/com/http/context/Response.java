package com.cszjo.com.http.context;

import java.nio.channels.SelectionKey;

import com.cszjo.com.http.utils.XMLUtil;

/**  
 * @Title:  Response.java   
 * @Description: 接口设计：response接口
 * @author: Han   
 * @date:   2016年7月16日 下午2:19:25  
 */  
public interface Response {
	
	//服务器名字
	public static final String SERVER_NAME = XMLUtil.getRootElement("server.xml").element("serverName").getText();
	
	public String getContentType();
	
	public int getStatuCode();
	
	public String getStatuCodeStr();
	
	public String getHtmlFile();
	
	public void setHtmlFile(String htmlFile);
	
	public SelectionKey getKey();
	
	public void setContentType(String contentType);
	
	public void setStatuCode(int statuCode);
	
	public void setStatuCodeStr(String statuCodeStr);
}
