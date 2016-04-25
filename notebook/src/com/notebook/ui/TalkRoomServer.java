package com.notebook.ui; 
import java.io.*;
import java.net.*;
import java.util.*;
public class TalkRoomServer {
	public static void main(String args[])throws Exception{
		ServerSocket ss=new ServerSocket(9000);
		Map sockets=new HashMap();
		while(true){
			Socket s=ss.accept();
			BufferedReader in=new BufferedReader(new InputStreamReader(s.getInputStream()));
			PrintWriter out=new PrintWriter(s.getOutputStream());
			String name=in.readLine();
			String IP=s.getInetAddress().toString();
			System.out.print(name+":"+IP);
			Collection valuse=sockets.values();
			Iterator it=valuse.iterator();
			while(it.hasNext()){
				Socket s1=(Socket)it.next();
				PrintWriter pw=new PrintWriter(s1.getOutputStream());
				pw.println("Add:"+name+IP);
				pw.flush();
			}
			sockets.put(name, s);
			Set names=sockets.keySet();
			it=names.iterator();
			while(it.hasNext()){
				String loginedUser=(String)it.next();
				out.println("Add:"+loginedUser+IP);
				out.flush();
			}
			Thread tt=new Talking(name,s,sockets);
			tt.start();
		}
	}

}
