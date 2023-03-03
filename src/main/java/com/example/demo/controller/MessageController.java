package com.example.demo.controller;
import com.example.demo.entity.Article;
import com.example.demo.entity.Discuss;
import com.example.demo.entity.Message;
import com.example.demo.mapper.esOperation;
import com.example.demo.mapper.messageMapper;
import com.example.demo.mapper.userMapper;
import com.example.demo.mapper.article_mapper;
import lombok.extern.slf4j.Slf4j;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@Slf4j
public class MessageController {
    @Autowired
    messageMapper messageMapper;
    @CrossOrigin
    @RequestMapping("/findDiscuss")
    public List<Message> Login(@RequestBody Map<String,String> map){
        System.out.println(map.get("user_id"));
        List<Message> list=new ArrayList<>();
        list=messageMapper.findAllByType(map.get("user_id"),map.get("type"));
        System.out.println(list);
        return list;
    }
}
