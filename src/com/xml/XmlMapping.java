package com.xml;
import java.util.ArrayList;
import java.util.List;


public class XmlMapping {

	private String servletName;  
	private List<String> urlPattern ;
	
	
	public XmlMapping() {
		urlPattern = new ArrayList<String>();
	}
	public String getServletName() {
		return servletName;
	}
	public void setServletName(String servletName) {
		this.servletName = servletName;
	}
	public List<String> getUrlPattern() {
		return urlPattern;
	}
	public void setUrlPattern(List<String> urlPattern) {
		this.urlPattern = urlPattern;
	}
	
	
}
