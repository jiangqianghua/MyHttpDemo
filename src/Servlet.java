
public class Servlet {

	public void service(Request request,Response response){
		String name = request.getParameter("name");
		response.htmlContent("<html><head></head><body>this is my page1<br><br>");
		response.htmlContent(" welcome "+name+" to page </body></html>");
		
	}
}
