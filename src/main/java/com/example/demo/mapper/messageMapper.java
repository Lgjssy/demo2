package com.example.demo.mapper;

import com.example.demo.entity.Message;

import java.util.List;

public interface messageMapper {
    //根据发送者查找消息

    public List<Message> findAllByType(String receiverId,String type);
    //根据消息类型和接受者查找未读消息
    public void addMessAge(Message message);

}
