package com.http;
import java.io.IOException;
import java.net.Socket;

import com.servlet.Servlet;
import com.webapp.WebApp;
/**
 * 把请求返回响应给线程处理
 * @author jiangqianghua
 *
 */
public class Dispatch implements Runnable {

	private Socket client ; 
	private Request request ; 
	private Response response ; 
	private int code = 200 ; 
	
	public Dispatch(Socket client)
	{
		this.client = client ; 
		try {
			request = new Request(client.getInputStream());
			response = new Response(client.getOutputStream());
		} catch (Exception e) {
			code = 500 ;
			//response.pushToClient(code);
			return ;
		}
	}
	
	@Override
	public void run() {
		String action = request.getAction() ; 
		if("/favicon.ico".equals(action))
		{
			return ;
		}
		Servlet servlet = WebApp.getServlet(action);
		if(servlet == null){
			this.code = 404;
			response.pushToClient(code);
			return ;
		}
		try {
			servlet.service(request, response);
		} catch (Exception e1) {
			e1.printStackTrace();
			code = 500;
		}
		response.pushToClient(code);
		try {
			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
