package cn.service;

import java.util.List;

import cn.pojo.Message;

public interface MessageService {

	/**
	 * 客户端发送给服务端的消息 存入到数据库 等待服务端查看
	 * @param userName
	 * 用户名
	 * @param message
	 * 消息
	 * @param dateTime
	 * 发送时间
	 * @return int
	 */
	int addMessage(String userName , String msgInfo , String dateTime);
	
	/**
	 * 根据用户名称获取该用户的消息
	 * @param userName
	 * 用户名
	 * @return List
	 * 消息对象
	 */
	List<Message> getMessageByName(String userName);
	
	/**
	 * 服务端打开聊天窗口 遍历出所有的消息
	 * @return List
	 * 消息集合
	 */
	List<Message> getAllMessage();
	
	/**
	 * 根据用户名删除 该对象的聊天记录 
	 * @param userName
	 * 用户名
	 * @return
	 * int 代表删除个数
	 */
	int delMessageByName(String userName);
	
	/**
	 * 判断数据库是否有这个消息有的话 就不添加了
	 * @param userName
	 * @param msgInfo
	 * @return
	 */
	Message getmsgByMsgAndName(String userName , String msgInfo);
	
	
}
