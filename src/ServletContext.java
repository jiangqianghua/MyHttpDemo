import java.util.HashMap;
import java.util.Map;

public class ServletContext {
	// 存放servlet对应的路径的类名
	private Map<String, String> servlet ; 
	// 存放action对应的名称
	private Map<String,String> mapping ; 
	
	public ServletContext(){
		servlet = new HashMap<String, String>();
		mapping = new HashMap<String, String>();	
	}
	
	public Map<String,String> getSerlvet()
	{
		return servlet ;
	}
	
	public void setServlet(Map<String,String> servlet)
	{
		this.servlet = servlet ; 
	}
	
	public Map<String, String> getMapping() {
		return mapping;
	}
	
	public void setMapping(Map<String, String> mapping) {
		this.mapping = mapping;
	}
}
