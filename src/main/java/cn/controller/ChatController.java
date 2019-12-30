package cn.controller;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;

import cn.pojo.Message;
import cn.service.MessageService;
import cn.util.CTHUtil;

@Controller
@RequestMapping("/chat")
public class ChatController {
	
	@Resource
	private MessageService messageService;
	public  Map<String,String> userMap = new ConcurrentHashMap<String, String>();
	private HttpSession session = null;

	/**
	 * @param md
	 * @return
	 */
	@RequestMapping("/welcome")
	public ModelAndView welcome(ModelAndView md) {
//		System.out.println(messageService.getAllMessage()); 
		md.setViewName("welcome"); 
		return md;
	}
	
	/**
	 * 到聊天页面-客户端
	 * @param md
	 * @return
	 */
	@RequestMapping("/action")
	public ModelAndView action(ModelAndView md) {
		md.addObject("localhost_ip", CTHUtil.getLocalIPList().get(1));
		md.setViewName("webChat_Client"); 
		return md;
	}
	
	/**
	 * 到群聊天页面-客户端
	 * @param md
	 * @return
	 */
	@RequestMapping("/action_Groups")
	public ModelAndView action_Groups(ModelAndView md,
			@RequestParam(name="userId" ,defaultValue = "")String userId,
			@RequestParam(name="imgsrc" ,defaultValue = "")String imgsrc) {
//		System.out.println(userId+","+imgsrc);
		md.addObject("userId", userId);
		md.addObject("imgsrc", imgsrc);
		md.setViewName("groupChats"); 
		return md;
	}
	
	/**
	 * 到聊天页面-客户端
	 * @param md
	 * @return
	 */
	@RequestMapping("/action_Group")
	public ModelAndView action_Group(ModelAndView md) {
		md.addObject("localhost_ip", CTHUtil.getLocalIPList().get(1));
		md.setViewName("groupChat"); 
		return md;
	}
	
	/**
	 * 到聊天页面 - 服务端
	 * @return
	 */ 
	@RequestMapping("/actions")
	public ModelAndView actions(ModelAndView md) {
		//获取消息列表
//		System.out.println(messageService.getAllMessage());
		//获取客户联系人存入map集合 用户名是key 消息个数是value
		Map<String,Integer> map = new ConcurrentHashMap<>();
		//存放消息的List集合
		List<String> list = new ArrayList<>();
		for(Message m : messageService.getAllMessage()) {
			map.put(m.getUserName(), messageService.getMessageByName(m.getUserName()).size());
		}
//		System.out.println(map);
		for(String name : map.keySet()) {
			for(Message s : messageService.getMessageByName(name)) {
				list.add(s.getMsgInfo());
			}
		}
		md.addObject("map", map);
		md.addObject("list", list);
		md.setViewName("webChat_Server"); 
		return md;
	}  
	
	/**  
	 * 客户端的消息内容
	 * @param md
	 * @param info
	 * @return
	 */ 
	@RequestMapping("/chatInfo")
	public ModelAndView chatInfo(ModelAndView md,
			@RequestParam(name="info",defaultValue="") String info) {
		System.out.println(info);
		md.setViewName("chats");
		return md;
	}
	
	/**
	 * 发送者存放数据 工接收者显示
	 * @param userId
	 * @param imgSrc
	 */
	@RequestMapping("ax_inUser")
	@ResponseBody
	public void ax_inUser(String userId, String imgSrc ,HttpServletRequest request,HttpServletResponse response) {
		session = request.getSession();
//		System.out.println("ax_inUser:"+userId+"发消息");
		session.setAttribute("userId", userId); 
		session.setAttribute("imgSrc", imgSrc);
	}
	
	/**
	 * 接收者 获取发送者的数据
	 * @param userId
	 * @param imgSrc
	 */
	@RequestMapping("ax_outUser") 
	@ResponseBody
	public String ax_outUser(HttpServletRequest request, String userId, String imgSrc) { 
		session = request.getSession();
		String id = (String) session.getAttribute("userId");
		String img = (String) session.getAttribute("imgSrc");
		//System.out.println("ax_outUser:"+session.getAttribute("userId")+"发来的消息,"+userId+"收消息"); 
		String val =id +","+img;
		session.removeAttribute("userId");
		session.removeAttribute("imgSrc");
		return JSON.toJSONString(val);
	}
}
