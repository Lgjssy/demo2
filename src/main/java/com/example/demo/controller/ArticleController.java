package com.example.demo.controller;
import com.example.demo.entity.Article;
import com.example.demo.entity.Discuss;
import com.example.demo.entity.Message;
import com.example.demo.mapper.esOperation;
import com.example.demo.mapper.userMapper;
import com.example.demo.mapper.messageMapper;
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
public class ArticleController {
    @Autowired
    article_mapper article_mapper;
    @Autowired
    esOperation EsOperation;
    @Autowired
    userMapper userMapper;
    @Autowired
     messageMapper messageMapper;
    @CrossOrigin
    @RequestMapping("/userArticle")
    public List<Article> Login(@RequestBody Map<String,String> map){
        List<Article> articleList=new ArrayList<>();

        System.out.println(article_mapper.articles_by_writer(map.get("user_id")));
        articleList=article_mapper.articles_by_writer(map.get("user_id"));
        return articleList;
    }

    @CrossOrigin
    @RequestMapping("/aboutArticle")
    public Map<String,String> aboutArticle(@RequestBody Map<String,String> map){
        List<Map<String,String>> list=new ArrayList<>();
        Map<String,String> map1=new HashMap<>();
        System.out.println(map);
        map1.put("likes",String.valueOf(article_mapper.likes(map.get("article_id")).size()));
        map1.put("comment",String.valueOf(article_mapper.comment(map.get("article_id")).size()));
        map1.put("shares",String.valueOf(article_mapper.shares(map.get("article_id")).size()));

        return map1;
    }
    @CrossOrigin
    @RequestMapping("/userLikeArticle")
    public List<Article> userLikeArticle(@RequestBody Map<String,String> map){
        List<Article> articleList=new ArrayList<>();
        List<String> list=removeDuplicate(article_mapper.findArticleByLike(map.get("user_id")));
        for(int i=0;i<list.size();i++){
            articleList.add(article_mapper.findArticleById(list.get(i)));
        }
        return articleList;
    }
    @CrossOrigin
    @RequestMapping("/userShareArticle")
    public List<Article> userShareArticle(@RequestBody Map<String,String> map){
        List<Article> articleList=new ArrayList<>();
        List<String> list=removeDuplicate(article_mapper.findArticleByShare(map.get("user_id")));
        for(int i=0;i<list.size();i++){
            articleList.add(article_mapper.findArticleById(list.get(i)));
        }
        return articleList;
    }
    @CrossOrigin
    @RequestMapping("/userRoughArticle")
    public List<Article> userRoughArticle(@RequestBody Map<String,String> map){
        List<Article> articleList=new ArrayList<>();

        System.out.println(article_mapper.findRoughById(map.get("user_id")));
        articleList=article_mapper.findRoughById(map.get("user_id"));
        return articleList;
    }
    @CrossOrigin
    @RequestMapping("/userAuditArticle")
    public List<Article> userAuditArticle(@RequestBody Map<String,String> map){
        List<Article> articleList=new ArrayList<>();

        articleList=article_mapper.findAuditById(map.get("user_id"));
        return articleList;
    }
    @CrossOrigin
    @RequestMapping("/checkBox")
    public List<Article> checkBox(@RequestBody Map<String,String> map){
        List<Article> articleList=new ArrayList<>();

        articleList=article_mapper.articles_by_state("审核中");
        System.out.println(articleList);
        return articleList;
    }
    @CrossOrigin
    @RequestMapping("/checkBoxNum")
    public Map<String,String> checkBoxNum(@RequestBody Map<String,String> map){
        Map<String,String> map1=new HashMap<>();
        map1.put("finish",String.valueOf(article_mapper.articles_by_state("已发布").size()));
        map1.put("unFinish",String.valueOf(article_mapper.articles_by_state("审核中").size()));

        return map1;
    }
    @CrossOrigin
    @RequestMapping("/searchArticleByName")
    public List<Article> searchArticleByName(@RequestBody Map<String,String> map){
       List<Article> articles=new ArrayList<>();
        articles=article_mapper.articles_by_key_words(map.get("key_word"));

        return articles;
    }
    @CrossOrigin
    @RequestMapping("/searchArticleByName2")
    public List<Article> searchArticleByName2(@RequestBody Map<String,String> map){
        List<Article> articles=new ArrayList<>();
        articles=article_mapper.articles_by_key_words1(map.get("key_word"));
        System.out.println(map.get("key_word"));
        return articles;
    }
    @CrossOrigin
    @RequestMapping("/searchArticleByName3")
    public Article searchArticleByName3(@RequestBody Map<String,String> map){
        Article articles;
        System.out.println(map.get("ArticleTile"));
        articles=article_mapper.findArticle(map.get("ArticleTile"));
        articles.setWriter_id(userMapper.findUserName(articles.getWriter_id()));
        System.out.println(map.get("ArticleTile"));
        return articles;
    }

    @CrossOrigin
    @RequestMapping("/searchDiscuss")
    public List<Discuss> searchDiscuss(@RequestBody Map<String,String> map){

        List<Discuss> list1=new ArrayList<>();
        list1=article_mapper.findDiscuss(article_mapper.findArticle(map.get("article_name")).getArticle_id());
        return list1;
    }
    @CrossOrigin
    @RequestMapping("/showContent")//获得文章信息和文章内容
    //map.get(article)获得文章属性，LIst的属性同实体类
    //map.get(content)获得文章内容，List《0》即文章内容
    public Map<String, String> ShowContent(@RequestBody Map<String,String> map) throws IOException {
        List<String> stringTemp=null;
        System.out.println(45465);
        System.out.println(map);
        Map<String,String> map1 =new HashMap<>();
        System.out.println(article_mapper.findArticle(map.get("article_name")));
        stringTemp =EsOperation.QueryKeyword(article_mapper.findArticle(map.get("article_name")).getArticle_id());
        System.out.println(stringTemp.get(0));
        String content=stringTemp.get(0).substring( stringTemp.get(0).indexOf(",\"text\":\"")+9,stringTemp.get(0).indexOf("\"}"));
        System.out.println("contentdasd");
        System.out.println(content);
        map1.put("content",content);
        return map1;//成功
    }
    @CrossOrigin
    @RequestMapping("/content")
    public Integer Content(@RequestBody Map<String,String> map) throws IOException {
        System.out.println(map);
        EsOperation.Insert(map.get("article_id"), map.get("content"));
        return 1;//成功
    }
    @CrossOrigin
    @RequestMapping("/content2")
    public Integer content2(@RequestBody Map<String,String> map) throws IOException {
        System.out.println("5465465465----------");
        System.out.println(map.get("article_id"));

        EsOperation.updateArticle(map.get("article_id"), map.get("content"));

        return 1;//成功
    }
    @CrossOrigin
    @RequestMapping("/form")
    public Map<String,String> Form(@RequestBody Map<String,String> map) {
        System.out.println(map);
        Map<String,String>map1=new HashMap<>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = new Date();

        String currentTime = formatter.format(date1);
        System.out.println(currentTime);
        Article article =new Article(map.get("article_introduction"),map.get("writer_id"),map.get("topic"),map.get("article_name"),"审核中",map.get("type"),currentTime,"article21.png");
        article_mapper.add_article(article);

            System.out.println();
            map1.put("article_id",String.valueOf(article_mapper.findId(article.getArticle_title())));
            return map1;

    }
    @CrossOrigin
    @RequestMapping("/form2")
    public Map<String,String> form2(@RequestBody Map<String,String> map) {
        System.out.println(map);
        Map<String,String>map1=new HashMap<>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = new Date();

        String currentTime = formatter.format(date1);
        System.out.println(currentTime);
        Article article =new Article(map.get("article_introduction"),map.get("writer_id"),map.get("topic"),map.get("article_name"),"草稿",map.get("type"),currentTime,"article21.png");
        article_mapper.add_article(article);
        System.out.println();
        map1.put("article_id",String.valueOf(article_mapper.findId(article.getArticle_title())));
        return map1;

    }
    @CrossOrigin
    @RequestMapping("/form3")
    public Map<String,String> form3(@RequestBody Map<String,String> map) {
        System.out.println(map);
        Map<String,String>map1=new HashMap<>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = new Date();

        String currentTime = formatter.format(date1);
        System.out.println(article_mapper.findArticle(map.get("article_id")).getArticle_id());
        article_mapper.changeArticle(article_mapper.findArticle(map.get("article_id")).getArticle_id(),map.get("article_introduction"),map.get("writer_id"),map.get("topic"),map.get("article_name"),"审核中",map.get("type"),currentTime,"article21.png");
        System.out.println();
        map1.put("article_id",String.valueOf(article_mapper.findArticle(map.get("article_name")).getArticle_id()));
        return map1;

    }
    @CrossOrigin
    @RequestMapping("/Yes")
    public Integer Yes(@RequestBody Map<String,String> map) {
        System.out.println(map);
        Article article=article_mapper.findArticle(map.get("article_name"));
        article_mapper.change_state(article.getArticle_id(),"已发布");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = new Date();

        String currentTime = formatter.format(date1);
        Message message=new Message("你的文章《"+map.get("article_name")+"》已经审核通过！",map.get("user_id"),article.getWriter_id(),"通知",currentTime);
        messageMapper.addMessAge(message);
        return 1;
    }
    @CrossOrigin
    @RequestMapping("/No")
    public Integer No(@RequestBody Map<String,String> map) {
        System.out.println(map);
        Article article=article_mapper.findArticle(map.get("article_name"));
        article_mapper.change_state(article.getArticle_id(),"草稿");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = new Date();

        String currentTime = formatter.format(date1);
        Message message=new Message("你的文章还是存在一点问题，请再次修改一下！",map.get("user_id"),article.getWriter_id(),"通知",currentTime);
        messageMapper.addMessAge(message);
        return 1;
    }
    @CrossOrigin
    @RequestMapping("/findArticleType")
    public Map<String,String> findArticleType(@RequestBody Map<String,String> map) {
        Map<String,String> map1=new HashMap<>();
        System.out.println(map);
        map1.put("state",article_mapper.find_type(map.get("article_name")));
        System.out.println(map1);
        return map1;
    }
    @CrossOrigin
    @RequestMapping("/addDiscuss")
    public Integer addDiscuss(@RequestBody Map<String,String> map) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = new Date();
        String currentTime = formatter.format(date1);
        article_mapper.addDiscuss(article_mapper.findArticle(map.get("article_id")).getArticle_id(),map.get("user_id"),map.get("content"),currentTime,"logo.png");
        Message message=new Message("文章："+map.get("article_id")+"------"+map.get("content"),map.get("user_id"),article_mapper.findArticle(map.get("article_id")).getWriter_id(),"评论",currentTime);
        messageMapper.addMessAge(message);
        return 1;
    }
    @CrossOrigin
    @RequestMapping("/addShare")
    public Integer addShare(@RequestBody Map<String,String> map) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = new Date();

        String currentTime = formatter.format(date1);
        article_mapper.addShare(article_mapper.findArticle(map.get("article_name")).getArticle_id(),map.get("user_id"),currentTime);
        return 1;
    }
    @CrossOrigin
    @RequestMapping("/addLike")
    public Integer addLike(@RequestBody Map<String,String> map) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = new Date();

        String currentTime = formatter.format(date1);
        Message message=new Message("点赞了文章"+map.get("article_name"),map.get("user_id"),article_mapper.findArticle(map.get("article_name")).getWriter_id(),"点赞",currentTime);
        messageMapper.addMessAge(message);
        article_mapper.addLike(article_mapper.findArticle(map.get("article_name")).getArticle_id(),map.get("user_id"),currentTime);
        return 1;
    }
    @CrossOrigin
    @RequestMapping("/change_state")
    public Integer change_state(@RequestBody Map<String,String> map) {
        article_mapper.change_state(map.get("article_id"),"草稿");
        return 1;
    }
    @CrossOrigin
    @RequestMapping("/delete_article")
    public Integer delete_article(@RequestBody Map<String,String> map) {
        article_mapper.delete_article(map.get("article_id"));
        return 1;
    }
    public  List removeDuplicate(List list) {
        HashSet h = new HashSet(list);
        list.clear();
        list.addAll(h);
        return list;
    }
}

