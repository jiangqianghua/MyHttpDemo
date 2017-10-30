import java.util.Map;

public class WebApp {

	private static ServletContext context ; 
	//http://127.0.0.1:8889/LoginServlet?name=jiang1111&gender=1
	static{
		context = new ServletContext();
		Map<String, String> mapping = context.getMapping() ;
		Map<String,String> servlet = context.getSerlvet() ;
		// web.xml
		mapping.put("LoginServlet", "LoginServlet");
		servlet.put("LoginServlet", "LoginServlet");
		
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
