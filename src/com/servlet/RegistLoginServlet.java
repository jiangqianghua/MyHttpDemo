package com.servlet;

import com.http.Request;
import com.http.Response;


public class RegistLoginServlet extends Servlet {

	@Override
	public void doGet(Request request, Response response) throws Exception {
		// TODO Auto-generated method stub
		super.doGet(request, response);
		response.htmlContent("<html><head></head><body>this is my RegistLoginServlet</body></html>");
	}

	@Override
	public void doPost(Request request, Response response) throws Exception {
		// TODO Auto-generated method stub
		super.doPost(request, response);
	}

}
