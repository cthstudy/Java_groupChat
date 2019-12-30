package cn.tcp.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 服务器
 * @author 曹天化
 *
 */
public class Server {

	public static void main(String[] args) {
		
		try {
			System.out.println("服务器已启动");
			//监听客户端的请求
			ServerSocket server = new ServerSocket(0320);
			Socket socket = server.accept();
			//读取socket的输入流
			BufferedReader br_s = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			//接收客户端socket输出流
			PrintWriter pw = new PrintWriter(socket.getOutputStream());
			//读取控制台的输入流
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			//打印从客户端读取的字符串
			System.out.println("客户端的消息:"+ br_s.readLine());
			//读取控制台内容
			String readline = br.readLine();
			while(!readline.equals("bye")) {
				//向客户端输出该字符串
				pw.println(readline);
				//刷新输出流 使客户端立马显示
				pw.flush();
				//在系统标准输出上打印读入的字符串
				//System.out.println("Server:"+ readline);
				//从Client读入一字符串，并打印到标准输出上
				System.out.println("客户端的消息:"+ br_s.readLine());
				//从系统标准输入读入一字符串
				readline = br.readLine();
			}
			System.out.println("聊天结束!");
			br.close();
			pw.close();
			br_s.close();
			socket.close();
			server.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
