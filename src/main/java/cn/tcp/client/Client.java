package cn.tcp.client;
/**
 * 客户端
 * @author 曹天化
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

	public static void main(String[] args) {
		try {
			System.out.println("客户端已启动");
			//创建socket  向本机的4700端口发出客户请求
			Socket socket = new Socket("127.0.0.1",0320);
			//由系统标准输入设备构造BufferedReader对象
			//接收控制台消息的输入流
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));//"new InputStreamReader(System.in)"
		    //由Socket对象得到输出流，并构造PrintWriter对象
			PrintWriter pw = new  PrintWriter(socket.getOutputStream());
			//由socket对象得到输入流，并构造相应的BufferedReader对象
			//接收socket消息的输入流
			BufferedReader br_s = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			//读取控制台接收的字符 一次读一行
			String readline = br.readLine();
			//若从标准输入读入的字符串为 "bye"则停止循环
			while(!readline.equals("bye")) {
				//将从控制台读入的字符串输出到Server
				pw.println(readline);
				//刷新输入流, 使server立马收到该字符串
				pw.flush();
				//在控制台打印读入的字符串
				//System.out.println("Client:" + readline);
				//接收服务器数据 打印出来
				System.out.println("服务器的消息:" + br_s.readLine());
				//保证一直监听 一直循环
				readline = br.readLine();
			}
			System.out.println("聊天结束!");
			br_s.close();
			pw.close();
			br.close();
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
