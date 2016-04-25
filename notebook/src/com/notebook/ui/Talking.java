package com.notebook.ui;
import java.io.*;
import java.net.*;
import java.util.*;
public class Talking extends Thread{
	Socket s;
	Map sockets;
	String name;
	public Talking(String n,Socket s,Map m){
		super();
		this.s=s;
		this.sockets=m;
		this.name=n;
	}
	public void run(){
		try{
			BufferedReader in=new BufferedReader(new InputStreamReader(s.getInputStream()));
			while(true){
				String text=in.readLine();
				StringTokenizer st=new StringTokenizer(text,"$");
				String from=st.nextToken();
				String to=st.nextToken();
				if(to.equals("All")){
					Collection values=sockets.values();
					Iterator it=values.iterator();
					while(it.hasNext()){
						Socket s1=(Socket)it.next();
						PrintWriter out=new PrintWriter(s1.getOutputStream());
						out.println("Text:"+text);
						out.flush();
					}
				}else{
					Socket s2=(Socket)sockets.get(to);
					PrintWriter out=new PrintWriter(s2.getOutputStream());
					out.println("Text:"+text);
					out.flush();
				}
			}
		}catch(Exception e){
			e.getStackTrace();
		}finally{
			try{
				this.sockets.remove(name);
				Collection value=sockets.values();
				Iterator it=value.iterator();
				while(it.hasNext()){
					Socket s2=(Socket)it.next();
					PrintWriter pw=new PrintWriter(s2.getOutputStream());
					pw.println("Del:"+name);
					pw.flush();
				}
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}

}
