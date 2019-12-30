package cn.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import cn.pojo.Message;

public interface MessageMapper {

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
	@Insert("insert into message (userName,msgInfo,dateTime) values(#{userName},#{msgInfo},#{dateTime})")
	int addMessage(@Param("userName") String userName ,@Param("msgInfo") String msgInfo ,@Param("dateTime") String dateTime);
	
	/**
	 * 根据用户名称获取该用户的消息
	 * @param userName
	 * 用户名
	 * @return List
	 */
	@Select("select userName,msgInfo,dateTime from message where userName=#{userName} ")
	List<Message> getMessageByName(String userName);
	
	/**
	 * 服务端打开聊天窗口 遍历出所有的消息
	 * @return List
	 * 消息集合
	 */
	@Select("select msgInfo,dateTime,userName from message")
	List<Message> getAllMessage();
	
	/**
	 * 根据用户名删除 该对象的聊天记录 
	 * @param userName
	 * 用户名
	 * @return
	 * int 代表删除个数
	 */
	@Delete("delete from message where userName")
	int delMessageByName(String userName);
	
	/**
	 * 判断数据库是否有这个消息有的话 就不添加了
	 * @param userName
	 * @param msgInfo
	 * @return
	 */
	@Select("select * from message where userName=#{userName} and msgInfo=#{msgInfo}")
	Message getmsgByMsgAndName(@Param("userName")String userName ,@Param("msgInfo") String msgInfo);
}
