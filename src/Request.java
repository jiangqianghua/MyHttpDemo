import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Request {

	private	static final String ENTER = "\r\n";
	/**
	 *  接受请求
	 */
	private BufferedReader request ;
	/**
	 * 存储请求头部信息
	 */
	private String requestHeader ; 
	/**
	 * 请求的方法 GET or POST
	 */
	private String medthod ; 
	/**
	 * 请求的url
	 */
	private String action ; 
	/**
	 * 请求的参数
	 */
	private Map<String, List<String>> parameter; 
	
	public Request(){
		requestHeader = "";
		medthod = "";
		action = "";
		parameter = new HashMap<String, List<String>>();
	}
	
	
	public Request(InputStream inputStream){
		this();
		request = new BufferedReader(new InputStreamReader(inputStream));
		String temp ; 
		try {
			while(!(temp = request.readLine()).equals("")){
				requestHeader +=(temp+ENTER);
			}
			System.out.println(requestHeader);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 解析头部信息
		parseRequestHeader();
	}

	/**
	 * 解析头部信息
	 */
	private void parseRequestHeader() {
		String paramsString = "";
		String firstLine = requestHeader.substring(0,requestHeader.indexOf(ENTER));
		int splitPointOne = firstLine.indexOf("/");
		medthod = firstLine.substring(0,splitPointOne).trim();
		int splitPointTwo = firstLine.indexOf("HTTP/");
		String actionTemp = firstLine.substring(splitPointOne,splitPointTwo).trim();
		if(medthod.equalsIgnoreCase("post")){
			// todo post
		}
		else if(medthod.equalsIgnoreCase("get")){
			if(actionTemp.contains("?")){
				paramsString = actionTemp.substring((actionTemp.indexOf("?")+1)).trim();
				this.action = actionTemp.substring(0,actionTemp.indexOf("?"));
			}
			else
			{
				this.action = actionTemp ;
			}
			
			parseParameterString(paramsString);
		}
	}
	
	/**
	 * 解析参数字符串，将参数存放在字符串中
	 * @param parameterString
	 */
	private void parseParameterString(String parameterString){
		if("".equals(parameterString)){
			return ;
		}else{
			String[] parameterKeyValues = parameterString.split("&");
			
			for(int i = 0 ; i < parameterKeyValues.length ; i++)
			{
				String[] keyValues = parameterKeyValues[i].split("=");
				if(keyValues.length == 1)
				{
					keyValues = Arrays.copyOf(keyValues, 2);
					keyValues[1] = null ;
				}
				String key = keyValues[0].trim();
				String values = null == keyValues[1]?null:decode(keyValues[1].trim(),"UTF-8");
				if(!parameter.containsKey(key)){
					parameter.put(key, new ArrayList<String>());
				}
				List<String> value = parameter.get(key);
				value.add(values);
			}
		}
	}
	/**
	 * 反解码使用指定的编码机制对 application/x-www-form-urlencoded 字符串解码。
	 * @param string
	 * @param encoding
	 * @return
	 */
	private String decode(String string , String encoding){
		
		try {
			return URLDecoder.decode(string,encoding);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null ;
	}
	
	/**
	 * 根据名字返回多个值
	 * @param name
	 * @return
	 */
	public String[] getParametervalues(String name){
		List<String> values = parameter.get(name);
		if(values == null)
		{
			return null ; 
		}
		return values.toArray(new String[0]);
	}
	
	/**
	 * 根据名字返回单个值
	 * @param name
	 * @return
	 */
	public String getParameter(String name){
		String[] value = getParametervalues(name);
		if(value == null)
		{
			return null ; 
		}
		return value[0];
	}


	public String getMedthod() {
		return medthod;
	}


	public void setMedthod(String medthod) {
		this.medthod = medthod;
	}


	public String getAction() {
		return action;
	}


	public void setAction(String action) {
		this.action = action;
	}
	
	
}
