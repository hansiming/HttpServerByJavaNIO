package com.cszjo.com.http.server;

/**  
 * @Title:  Solution.java   
 * @Description: 启动Web服务器入口
 * @author: Han   
 * @date:   2016年7月12日 下午7:11:15  
 */  
public class Solution {
	
	//启动方法
	public static void main(String[] args) {
		new Thread(new Server(false)).start();
	}
}
