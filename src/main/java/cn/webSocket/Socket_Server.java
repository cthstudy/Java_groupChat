package cn.webSocket;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 * 
 * @author CTH
 *
 */
@ServerEndpoint("/socket_server")
public class Socket_Server {

	private Session session = null;
	
	/**
	 * 建立连接
	 * @param session
	 */
	@OnOpen
	public void onOpen(Session session) {
		this.session = session;
		System.out.println("已建立连接,sessionID:"+ session.getId());
	}
	
	/**
	 * 接收数据
	 * @param message
	 */
	@OnMessage
	public void onMessage(String msg) {
		//服务器接收客户端的消息
		System.out.println("接收客户端的数据:"+ msg);
		try {
			//服务器接收客户端的消息再发送给客户端
			this.session.getBasicRemote().sendText(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@OnError
	public void error(Throwable error) {
		System.out.println("发送错误!");
		error.printStackTrace();
	}
	
	@OnClose
	public void onClose() {
		System.out.println("连接已关闭!");
	}
}
