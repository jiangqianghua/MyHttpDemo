package com.servlet;
import com.http.Request;
import com.http.Response;


public class LoginServlet extends Servlet {

	@Override
	public void doGet(Request request, Response response) throws Exception {
		// TODO Auto-generated method stub
		super.doGet(request, response);
		// 返回浏览器显示内容
		response.htmlContent("<html><head></head><body>this is my LoginServlet</body></html>");
	}

	@Override
	public void doPost(Request request, Response response) throws Exception {
		// TODO Auto-generated method stub
		super.doPost(request, response);
	}

}
