package com.example.demo.controller;


import com.example.demo.entity.Message;
import com.example.demo.entity.User;
import com.example.demo.mapper.article_mapper;
import com.example.demo.mapper.messageMapper;
import com.example.demo.mapper.userMapper;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@Slf4j
public class UserController {
    @Autowired
    userMapper userMapper;
    @Autowired
    article_mapper article_mapper;
    @Autowired
    messageMapper messageMapper;
    @CrossOrigin
    @RequestMapping("/Login")
    public Integer Login(@RequestBody Map<String,String> map){
        System.out.println(map.get("User_Id"));
        System.out.println(map.get("User_Password"));
        if(userMapper.FindUser(map.get("User_Id"))!=null){
            if(map.get("User_Password").equals(userMapper.FindPassword(map.get("User_Id")))){
                if(userMapper.FindPosition(map.get("User_Id")).equals("普通用户"))
                return 1;//表示登录成功,为普通用户
                return 2;//表示管理员登录
            }
            else return 0;//表示密码错误
        }
        else return -1;//表示用户不存在
    }
    @CrossOrigin
    @RequestMapping("/Register")
    public Integer Register(@RequestBody Map<String,String> map){
        if(userMapper.FindUser(map.get("username"))!=null){
            return 0;
        }
        System.out.println(map);
         userMapper.addNewUser(map.get("user_id"),map.get("username"),map.get("password"),"普通用户",map.get("phoneNumber"),"logo.png",map.get("about"));
        return 1;
    }
    //查询用户的所有信息
    @CrossOrigin
    @RequestMapping("/Details")
    public Map Details(@RequestBody Map<String,String> map){
        System.out.println(map);
        Map<String,String> user_map=new HashMap<String,String>();
        user_map=userMapper.FindUserDetails(map.get("user_Id"));
        user_map.put("followerNumber",String.valueOf(userMapper.findFollower(map.get("user_Id")).size()));
        System.out.println(String.valueOf(userMapper.findUserArticle(map.get("user_Id")).size()));
        user_map.put("articleNumber",String.valueOf(userMapper.findUserArticle(map.get("user_Id")).size()));
        return user_map;
    }
    @CrossOrigin
    @RequestMapping("/findUserByName")
    public List<User> findUserByName(@RequestBody Map<String,String> map){
        List<User> list=new ArrayList<>();
        list=userMapper.findUserByName(map.get("user_name"));
        return list;
    }
    @CrossOrigin
    @RequestMapping("/addMyUser")
    public List<User> addMyUser(@RequestBody Map<String,String> map){
        List<User> list=new ArrayList<>();
        list=userMapper.findUserList();
        return list;
    }
    @CrossOrigin
    @RequestMapping("/addMyRelationship")
    public List<User> addMyRelationship(@RequestBody Map<String,String> map){
        List<User> list=new ArrayList<>();
        List<String> list1=userMapper.findMyUp(map.get("user_id"));
        for(int i=0;i<list1.size();i++){
            list.add(userMapper.FindUser(list1.get(i)));
        }

        System.out.println(list1);
        return list;
    }
    @CrossOrigin
    @RequestMapping("/addFollow")
    public Integer addFollow(@RequestBody Map<String,String> map){
        System.out.println(map);
        Date date1 = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String currentTime = formatter.format(date1);
        userMapper.addFollower(map.get("user_id"),map.get("follower_id"));
        Message message=new Message("我关注了你",map.get("follower_id"), map.get("user_id"),"关注",currentTime);
        messageMapper.addMessAge(message);
        return 1;
    }
    @CrossOrigin
    @RequestMapping("/findID")
    public Map<String,String> findID(@RequestBody Map<String,String> map){

        System.out.println(564564);
        Map<String,String> map1=new HashMap<>();
        map1.put("id",userMapper.findUserByName1(map.get("user_name")).getUser_id());

        return map1;
    }
    @CrossOrigin
    @RequestMapping("/cancel")
    public Integer cancel(@RequestBody Map<String,String> map){

        System.out.println(map);
        userMapper.deleteFollow(map.get("user_id"),map.get("follower_id"));
        return 1;
    }
}
