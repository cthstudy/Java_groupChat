package cn.pojo;

import org.springframework.stereotype.Component;

@Component("message")
public class Message {

	private int id;
	private String msgInfo;//消息内容
	private String dateTime;//消息时间
	private String userName;//客户端用户名
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMsgInfo() {
		return msgInfo;
	}
	public void setMsgInfo(String msgInfo) {
		this.msgInfo = msgInfo;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Override
	public String toString() {
		return "Message [id=" + id + ", msgInfo=" + msgInfo + ", dateTime=" + dateTime + ", userName=" + userName + "]";
	}
	
}
