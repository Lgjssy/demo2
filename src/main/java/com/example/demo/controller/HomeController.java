package com.example.demo.controller;

import com.example.demo.entity.Article;
import com.example.demo.entity.User;
import com.example.demo.mapper.article_mapper;
import com.example.demo.mapper.userMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class HomeController {


    @Autowired
    article_mapper ArticleMapper;
    @Autowired
    userMapper UserMapper;
    @CrossOrigin
    @RequestMapping("/popularWorks")//返回3个热门文章，用map.get（0，1，2）获取文章属性，LIst的属性同实体类
    public Map<Integer, Article >popularWorks(){
        List<Article> articleTemp=ArticleMapper.all_articles();//查询所有文章，并按推荐度来排序
        Map<Integer,Article>map=new HashMap<>();
        for(int i=0;i<3;i++){
            map.put(i,articleTemp.get(i));
        }
        return map;
    }
    @CrossOrigin
    @RequestMapping("/popularUser")//返回5个热门用户，用map.get（0，1，2，3，4）获取用户属性，LIst的属性同实体类
    public Map<Integer, List<String> >popularUser(){
        List<User> articleTemp=UserMapper.findUserList();//查询所有用户，并按关注数来排序
        Map<Integer,List<String>>map=null;
        for(int i=0;i<5;i++){//循环将查询出来的前五个用户加入List《string》中
            List<String> stringTemp = null;
            stringTemp.add(articleTemp.get(i).getUser_id());
            stringTemp.add(articleTemp.get(i).getUser_name());
            stringTemp.add(articleTemp.get(i).getPassword());
            stringTemp.add(articleTemp.get(i).getPosition());
            stringTemp.add(articleTemp.get(i).getPhone_number());
            stringTemp.add(articleTemp.get(i).getPhoto_file());
            map.put(i,stringTemp);
        }
        return map;
    }
    @CrossOrigin
    @RequestMapping("/findByID")
    public User findByID(@RequestBody Map<String,String> map){
        System.out.println(map);
        User user=UserMapper.FindUser(map.get("user_Id"));
        return user;
    }
    @CrossOrigin
    @RequestMapping("/findPopular")
    public Map<Integer,User> findPopular(){
        List<User> useTemp=new ArrayList<>();
        Map<Integer,User>map=new HashMap<>();
        useTemp=UserMapper.findUserList();
        for(int i=0;i<5;i++){
            map.put(i,useTemp.get(i));
        }
        return map;
    }
}
