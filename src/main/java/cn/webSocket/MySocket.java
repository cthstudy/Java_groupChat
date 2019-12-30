package cn.webSocket;
/**
 * @author CTH
 */
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import cn.controller.ChatController;
import cn.pojo.ApplicationHelper;
import cn.service.MessageService;
import cn.util.CTHUtil;

//声明WebSocket的请求名
@ServerEndpoint("/websocket")
public class MySocket {

	// 时间
//	private SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");//线程安全
	// 一个线程集合存储session线程会话
	private Map<String,MySocket> map = new ConcurrentHashMap<String,MySocket>();
	private Session session;
	
	private MessageService messageService = (MessageService) ApplicationHelper.getBean("messageService");
	
	/**
	 * 建立连接 
	 * websocket通讯，浏览器和服务器只会建立一次连接，因此，我们需要在建立连接握手的时候，把这个会话存储到集合里面
	 * 1、一般是用map集合，因为key是不能重复的 
	 * 2、如果是List集合的话，无法区分是自己发的信息还是别人发的信息
	 * 3、集合存在意义，就是实现循环遍历转发给连接服务器的客户端浏览器页面
	 * @param session
	 */
	@OnOpen
	public void onOpen(Session session) {
		System.out.println("连接已建立,sessionID:"+session.getId());
		// 把当前的session存储起来，以便后来的时候服务器转发数据
		this.session = session;
		map.put(CTHUtil.getLocalIPList().get(1),this);
		System.out.println("当前连接总数:"+map.size());
	}
	
	/**
	 * 接受客户端的消息
	 * js通过ws.send()调用该方法
	 * @param message
	 */  
	@OnMessage
	public void getMessage(String msgInfo, Session session) { 
		for (String s : map.keySet()) {
			System.out.println("收到"+s+"的信息:" + msgInfo); 
			// 服务器把信息分为是自己的还是其他人发的，再加上时间，然后群发到连接的用户session会话里面
			String dateTime = fmt.format(LocalDateTime.now());
			String info = msgInfo; //+ "时间:" + time; 
			if(messageService.getmsgByMsgAndName(s, msgInfo) == null) {
				messageService.addMessage(s, msgInfo, dateTime);
			}
			// 会话异步发送消息
			map.get(s).session.getAsyncRemote().sendText(info);
		}
 
	}
	
	@OnClose
	public void onClose() {
//		map.remove(this);  //删除连接数
		System.out.println("连接关闭!");
	}
	
	@OnError
	public void error(Throwable t) {
		// 添加错误操作
		System.out.println(t);
	}
}
