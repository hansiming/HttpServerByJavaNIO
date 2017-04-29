package com.cszjo.com.http.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.junit.Test;

import com.cszjo.com.http.context.Request;
import com.cszjo.com.http.context.impl.HttpRequest;

public class HttpRequestTest {
	
	@SuppressWarnings("unused")
	@Test
	public void testRequest() throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader(new File("src/header.txt")));
		String str = reader.readLine();
		String header = "";
		while(str != null) {
			header += str + "\r\n";
			str = reader.readLine();
		}
		Request request = new HttpRequest(header);
		reader.close();
	}
}
