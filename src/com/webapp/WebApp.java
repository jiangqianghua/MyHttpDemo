package com.webapp;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import com.servlet.Servlet;
import com.servlet.ServletContext;
import com.xml.XMLHandler;
import com.xml.XmlMapping;
import com.xml.XmlServlet;

public class WebApp {

	private static ServletContext context ; 
	//http://127.0.0.1:8889/LoginServlet?name=jiang1111&gender=1
	static{
		context = new ServletContext();
		Map<String, String> mapping = context.getMapping() ;
		Map<String,String> servlet = context.getSerlvet() ;
		// web.xml
//		mapping.put("LoginServlet", "LoginServlet");
//		servlet.put("LoginServlet", "LoginServlet");
		
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {
			SAXParser parser = factory.newSAXParser() ;
			XMLHandler handler = new XMLHandler();
			InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("web/WEB-INF/web.xml");
			parser.parse(is, handler);
			List<XmlServlet> serv = handler.getServlet();
			for(XmlServlet xmlServlet:serv){
				servlet.put(xmlServlet.getServletName(), xmlServlet.getServletClass());
			}
			
			List<XmlMapping>map = handler.getMapping();
			for(XmlMapping xmlMapping:map){
				List<String> actions = xmlMapping.getUrlPattern();
				for(String action:actions){
					mapping.put(action, xmlMapping.getServletName());
				}
			}
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("ok");
	}
	public static Servlet getServlet(String action)
	{
		action = action.replaceAll("/", "");
		Servlet servlet = null ; 
		String servletName = context.getMapping().get(action);
		String classPath = context.getSerlvet().get(servletName);
		if(classPath != null)
		{
			Class<?> clazz = null ;
			try {
				clazz = Class.forName(classPath);
				servlet = (Servlet)clazz.newInstance() ;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return servlet ;
	}
}
