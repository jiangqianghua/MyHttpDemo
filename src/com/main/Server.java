package com.main;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

import com.http.Dispatch;
//http://127.0.0.1:8889/?name=jiang&gender=1
public class Server {

	private boolean isShutDown = false ;
	
	public static void main(String[] args) {
		Server server = new Server();
		server.start();
	}
	
	public void start(){
		start(8889);
	}
	
	public void start(int port){
		
		try {
			ServerSocket ss = new ServerSocket(port);
			this.receive(ss);
		} catch (IOException e) {
			stop();
		}
		
	}
	
	private void stop(){
		isShutDown = true ;
	}
	
	/**
	 * 接受请求
	 * @param ss
	 */
	private void receive(ServerSocket ss)
	{
		while(!isShutDown){
			Socket client;
			try {
				client = ss.accept();
				new Thread(new Dispatch(client)).start();
			} catch (IOException e) {
				e.printStackTrace();
				isShutDown = true; 
			} 
		}
	}

}
