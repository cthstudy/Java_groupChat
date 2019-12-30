package cn.tcp.Thread;

import java.net.*;

import sun.net.InetAddressCachePolicy;

/**
 * socket服务端
 * @author 曹天化
 *
 */
public class SocketServer {

	public static void main(String[] args) {
		try {
			ServerSocket serverSocket = new ServerSocket(10086);//
			Socket socket = null;//接收客户端的socket
			System.out.println("服务器启动了");
			int count = 0;//客户端数量
			//定义一个死循环,不停接收客户端连接
			while(true) {
				//监听连接
				socket = serverSocket.accept();
				//获取客户端连接
				InetAddress inetAddress = socket.getInetAddress();
				//创建一个线程类
				ServerThread thread=new ServerThread(socket,inetAddress);
				thread.start();//启动线程
				count++;//如果正确建立连接
                System.out.println("客户端数量：" + count);//打印客户端数量
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
