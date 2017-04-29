package com.cszjo.com.http.test;

import static org.junit.Assert.*;

import java.util.List;

import org.dom4j.Element;
import org.junit.Test;

import com.cszjo.com.http.utils.XMLUtil;

@SuppressWarnings("unused")
public class XMLUtilTest {
	
	@Test
	public void testGetRootElement() throws Exception {
		Element rootElement = XMLUtil.getRootElement("web.xml");
	}
	
	@Test
	public void testgetElements() throws Exception {
		Element rootElement = XMLUtil.getRootElement("web.xml");
		List<Element> elements = XMLUtil.getElements(rootElement);
	}
	
	@Test
	public void testgetElement() throws Exception {
		Element rootElement = XMLUtil.getRootElement("web.xml");
		Element element = XMLUtil.getElement(rootElement, "123");
	}
}
