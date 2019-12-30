package cn.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.mapper.MessageMapper;
import cn.pojo.Message;

@Service("messageService")
public class MessageServiceImpl implements MessageService{

	@Resource private MessageMapper mapper;
	
	@Override
	public int addMessage(String userName, String msgInfo, String dateTime) {
		return mapper.addMessage(userName, msgInfo, dateTime);
	}

	@Override
	public List<Message> getMessageByName(String userName) {
		return mapper.getMessageByName(userName);
	}

	@Override
	public List<Message> getAllMessage() {
		return mapper.getAllMessage();
	}

	@Override
	public int delMessageByName(String userName) {
		return mapper.delMessageByName(userName);
	}

	@Override
	public Message getmsgByMsgAndName(String userName, String msgInfo) {
		return mapper.getmsgByMsgAndName(userName, msgInfo);
	}

}
