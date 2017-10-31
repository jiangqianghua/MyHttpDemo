package com.xml;

import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.events.StartDocument;
import javax.xml.stream.events.StartElement;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * 解析web.xml
 * @author jiangqianghua
 *
 */
public class XMLHandler  extends DefaultHandler{

			private List<XmlServlet> servlet ; 
			private List<XmlMapping> mapping ;
			public List<XmlServlet> getServlet() {
				return servlet;
			}
			public void setServlet(List<XmlServlet> servlet) {
				this.servlet = servlet;
			}
			public List<XmlMapping> getMapping() {
				return mapping;
			}
			public void setMapping(List<XmlMapping> mapping) {
				this.mapping = mapping;
			} 
			
			private XmlServlet serv ; 
			private XmlMapping map ; 
			
			private String beginTag ; 
			private boolean isMap ; 
			
			@Override
			public void startDocument() throws SAXException {
				servlet = new ArrayList<XmlServlet>();
				mapping = new ArrayList<XmlMapping>();
			}
			
			@Override
			public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
				if(qName != null){
					beginTag = qName ;
					if(qName.equals("servlet")){
						serv = new XmlServlet();
						isMap = false ;
					} else if(qName.equals("servlet-mapping")){
						map = new XmlMapping();
						isMap = true ;
					}
				}
			}
			
			@Override
			public void characters(char[] ch, int start, int length)
			throws SAXException {
				if(beginTag != null){
					String info = new String(ch,start,length);
					if(isMap){
						if(beginTag.equals("servlet-name")){
							map.setServletName(info.trim());
						} else if(beginTag.equals("url-pattern")){
							map.getUrlPattern().add(info);
						}
					}else{
						if(beginTag.equals("servlet-name")){
							serv.setServletName(info);
						}else if(beginTag.equals("servlet-class")){
							serv.setServletClass(info);
						}
					}
				}
			}
			
			@Override
			public void endElement(String uri, String localName, String qName)
			throws SAXException {
					if(qName != null){
						if(qName.equals("servlet")){
							servlet.add(serv);
						}else if(qName.equals("servlet-mapping")){
							mapping.add(map);
						}
					}
					beginTag = null ;
			}
			
}
