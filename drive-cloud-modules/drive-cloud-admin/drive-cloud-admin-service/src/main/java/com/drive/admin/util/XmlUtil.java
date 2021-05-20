package com.drive.admin.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.jdom2.input.SAXBuilder;

import com.alibaba.fastjson.JSONObject;

public class XmlUtil {

	public static void getAllFileFromPath(File file, List<File> fileList,
			String ed) {
		if (file != null) {
			if (file.isDirectory()) {
				File[] files = file.listFiles();
				if (files != null) {
					for (File tmp : files) {
						getAllFileFromPath(tmp, fileList, ed);
					}
				}
			} else if (file.getName().toLowerCase().endsWith(ed)) {
				fileList.add(file);
			}
		}
	}

	public static Document read(String fileName) throws DocumentException {
		SAXReader reader = new SAXReader();
		Document document = reader.read(new File(fileName));

		return document;
	}

	public static String getAttributeValue(Attribute attribute) {
		String result = "";
		if (attribute != null)
			result = attribute.getValue();
		return result;
	}

	public static String getDefaultAttributeValue(Element element) {
		return getElementAttributeValue(element, "value");
	}

	public static String getElementAttributeValue(Element element, String value) {
		if (element != null)
			return getAttributeValue(element.attribute(value));
		else
			return "";
	}

	public static String getElementText(Element element) {
		String result = null;
		if (element != null)
			result = element.getText();
		return result;
	}

	public static Document read(File file) throws DocumentException {
		SAXReader reader = new SAXReader();
		Document document = reader.read(file);

		return document;
	}

	public static org.dom4j.Document read(File file, String encoding)
			throws DocumentException {
		SAXReader reader = new SAXReader();
		reader.setEncoding(encoding);
		org.dom4j.Document document = reader.read(file);

		return document;
	}

	public static Document read(InputStream is) throws DocumentException {
		SAXReader reader = new SAXReader();
		Document document = reader.read(is);

		return document;
	}

	public static Document read(InputStream is,String encoding) throws DocumentException {
		SAXReader reader = new SAXReader();
		
		if(encoding!=null)
			reader.setEncoding(encoding);
		Document document = reader.read(is);
		

		return document;
	}

	public static Document read(URL url) throws DocumentException {
		SAXReader reader = new SAXReader();
		Document document = reader.read(url);

		return document;
	}

	public static Element getRootElement(Document document) {

		return document.getRootElement();
	}

	@SuppressWarnings("unchecked")
	public static List<Element> getChildElements(Element parent) {
		return parent.elements();
	}

	public static boolean hasChild(Element element) {

		return element.nodeCount() > 0;

	}

	public static Document createDocument() {
		Document document = DocumentHelper.createDocument();
		return document;
	}

	public static Element addAttribute(org.dom4j.Element element, String name,
			String value) {
		return element.addAttribute(name, value);

	}

	public static Document parseText(String text) throws DocumentException {
		return DocumentHelper.parseText(text);
	}

	public static void save(Document document, String fileName, String encoding)
			throws Exception {
		XMLWriter writer = null;
		try {
			OutputFormat format = OutputFormat.createPrettyPrint();

			if (!"".equals(encoding))
				format.setEncoding(encoding);
			else
				format.setEncoding("UTF-8");

			FileOutputStream fos = new FileOutputStream(fileName);
			writer = new XMLWriter(fos, format);
			writer.setEscapeText(false);
			writer.write(document);
			writer.close();
		} catch (Exception e) {
			throw e;
		} finally {
			if (writer != null)
				writer.close();

		}

	}
	
	/**
	 * xml 转 json 值为 String
	 * @param xml
	 * @author GY
	 */
	public static JSONObject xml2JSONStr(String str) throws Exception {
        JSONObject json = new JSONObject();
        InputStream is = new ByteArrayInputStream(str.getBytes("UTF-8"));
        SAXBuilder sb = new SAXBuilder();
        org.jdom2.Document doc = sb.build(is);
        org.jdom2.Element root = doc.getRootElement();
        json.put(root.getName(), iterateElementStr(root));
        return json;
    }
	
	
	
	
	 private static JSONObject iterateElementStr(org.jdom2.Element element) {
	        List<org.jdom2.Element> node = (element).getChildren();
	        org.jdom2.Element et = null;
	        JSONObject obj = new JSONObject();
	        for (int i = 0; i < node.size(); i++) {
	            et = node.get(i);
		            if(et!=null) {
		            	obj.put(et.getName(), et.getTextTrim());
		            }
	            }
	        return obj;
	 }
	 
	 /**
	  * xml 转 json  值为 list
	  * @param xml
	  * @author GY
	  */
	 public static JSONObject xml2JSONList(byte[] xml) throws Exception {
	        JSONObject json = new JSONObject();
	        InputStream is = new ByteArrayInputStream(xml);
	        SAXBuilder sb = new SAXBuilder();
	        org.jdom2.Document doc = sb.build(is);
	        org.jdom2.Element root = doc.getRootElement();
	        json.put(root.getName(), iterateElementList(root));
	        return json;
	    }
		
	 
	 @SuppressWarnings("unchecked")
	private static JSONObject iterateElementList(org.jdom2.Element element) {
	        List<org.jdom2.Element> node = (element).getChildren();
	        org.jdom2.Element et = null;
	        JSONObject obj = new JSONObject();
	        List<Serializable> list = null;
	        for (int i = 0; i < node.size(); i++) {
	            list = new LinkedList<Serializable>();
	            et = node.get(i);
	            if (et.getTextTrim().equals("")) {
	                if (et.getChildren().size() == 0)
	                    continue;
	                if (obj.containsKey(et.getName())) {
	                    list = (List<Serializable>) obj.get(et.getName());
	                }
	                list.add(iterateElementList(et));
	                obj.put(et.getName(), list);
	            } else {
	                if (obj.containsKey(et.getName())) {
	                    list = (List<Serializable>) obj.get(et.getName());
	                }
	                list.add(et.getTextTrim());
	                obj.put(et.getName(), list);
	            }
	        }
	        return obj;
	    }

		
	 public static void main(String[] args) throws Exception {  
		 
			String  a= "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg>"
					+ "<![CDATA[OK]]></return_msg><appid><![CDATA[wx82a1a8792f7c1b21]]></appid><mch_id><![CDATA[1531087481]]></mch_id><nonce_str><![CDATA[XNKnTG8dJrRtfZgG]]></nonce_str><sign><![CDATA[99EEB1BF91C9F672BBC508DB99B059B8]]></sign><result_code><![CDATA[FAIL]]></result_code><err_code><![CDATA[ORDERPAID]]></err_code><err_code_des><![CDATA[该订单已支付]]></err_code_des></xml>";
			JSONObject b=XmlUtil.xml2JSONStr(a);
			System.out.println(b);
			
	 }
	 
}
