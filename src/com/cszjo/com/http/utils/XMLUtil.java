package com.cszjo.com.http.utils;

import java.io.File;
import java.util.List;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**  
 * @Title:  XMLUtil.java   
 * @Description: 解决XML读取问题 
 * @author: Han   
 * @date:   2016年7月15日 下午4:55:28  
 */  
public class XMLUtil {
	
	private static Logger logger = Logger.getLogger(XMLUtil.class);
	private static SAXReader reader = new SAXReader();
	
	/**
	 * 得到根节点
	 * @param:  @param xmlPath
	 * @param:  @return  
	 * @return: Element
	 * @Autor: Han
	 */
	public static Element getRootElement(String xmlPath) {
		Document document = null;;
		try {
			document = reader.read(new File(xmlPath));
		} catch (DocumentException e) {
			logger.error("找不到指定的xml文件的路径" + xmlPath + "！");
			return null;
		}
		return document.getRootElement();
	}
	
	/**
	 * 得到该节点下的子节点集合
	 * @param:  @param element
	 * @param:  @return  
	 * @return: List<Element>
	 * @Autor: Han
	 */
	@SuppressWarnings("unchecked")
	public static List<Element> getElements(Element element) {
		return element.elements();
	}
	
	/**
	 * 得到该节点下指定的节点
	 * @param:  @param name
	 * @param:  @return  
	 * @return: Element
	 * @Autor: Han
	 */
	public static Element getElement(Element element, String name) {
		Element childElement = element.element(name);
		if(childElement == null) {
			logger.error(element.getName() + "节点下没有子节点" + name);
			return null;
		}
		return childElement;
	}
	
	/**
	 * 得到该节点的内容
	 * @param:  @param element
	 * @param:  @return  
	 * @return: String
	 * @Autor: Han
	 */
	public static String getElementText(Element element) {
		return element.getText();
	}
}
