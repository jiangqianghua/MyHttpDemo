
public abstract class Servlet {

	public void service(Request request,Response response) throws Exception{
		String method = request.getMedthod();
		
		if(method.equalsIgnoreCase("get")){
			this.doGet(request,response);
		}
		else if(method.equalsIgnoreCase("post")){
			this.doGet(request,response);
		}
	}

	public void doGet(Request request, Response response) throws Exception {
		
	}

	public void doPost(Request request, Response response)throws Exception {
		
	}
	
	
}
