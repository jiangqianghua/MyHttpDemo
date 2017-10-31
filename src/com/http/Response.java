package com.http;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Date;

/**
 * 响应对象
 * @author jiangqianghua
 */
public class Response {
	
	private static final String ENTER = "\r\n";
	private static final String SPACE = " ";
	// 存储头部信息
	private StringBuffer headerInfo ; 
	//  存储正文信息
	private StringBuffer textContent ; 
	// 存储正文信息字节长度
	private int contentLength ; 
	// 构建输出流
	private BufferedWriter bw ; 
	
	public Response(){
		headerInfo = new StringBuffer();
		textContent = new StringBuffer();
		contentLength = 0 ;
	}
	
	public Response(OutputStream os){
		this();
		bw = new BufferedWriter(new OutputStreamWriter(os));
	}
	
	
	private void createHeader(int code){
		headerInfo.append("HTTP/1.1").append(SPACE).append(code).append(SPACE);
		String codeInfo = "";
		switch (code) {
		case 200:
			codeInfo = "OK" ;
			break;
		case 404:
			codeInfo = "NOT FOUND";
			break;
		case 500:
			codeInfo = "SERVER ERROR";
		default:
			break;
		}
		headerInfo.append(codeInfo).append(ENTER);
		
		headerInfo.append("Server:myServer").append(SPACE).append("0.0.1v").append(ENTER);
		headerInfo.append("Data:Sat,"+SPACE).append(new Date()).append(ENTER);
		headerInfo.append("Content-Type:text/html;charset=UTF-8").append(ENTER);
		headerInfo.append("Content-Length:").append(contentLength).append(ENTER);
		headerInfo.append(ENTER);
	}
	
	/**
	 * 响应给浏览器的解析内容
	 * @param content
	 * @return
	 */
	public Response htmlContent(String content){
		textContent.append(content).append(ENTER);
		contentLength += (content+ENTER).toString().getBytes().length;
		return this ;
	}
	
	/**
	 * 发送给浏览器
	 */
	public void pushToClient(int code){
		createHeader(code);
		try {
				bw.append(headerInfo.toString());
				bw.append(textContent.toString());
				System.out.println(headerInfo.toString());
				System.out.println(textContent.toString());
				bw.flush();
			} catch (IOException e) {
			e.printStackTrace();
		}
		finally{
			try {
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
