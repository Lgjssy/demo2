package com.example.demo.mapper;

import com.example.demo.entity.Article;
import com.example.demo.entity.Discuss;

import java.util.List;

public interface article_mapper {
    public List<Article> all_articles();//寻找所有的文章

    public List<Article> articles_by_writer(String user_id);//根据作者来查找文章
    public List<Article> articles_by_state(String state);//根据文章状态来查找文章
    public List<Article> personal_articles_by_state(String state, String user_id);//根据个人文章状态来查找文章
    public List<Article> articles_by_topic(String topic);//根据主题来查找
    public List<Article> articles_by_key_words(String words);//文章关键字查找
    public List<Article> articles_by_key_words1(String words);//主题关键字查找
    //新增文章
    public void add_article(Article article);
    //修改文章状态
    public void change_state(String article_id,String state);
    //删除文章
    public void delete_article(String article_id);
    //根据喜欢来查找文章id
    public List<String> findArticleByLike(String id);
    //文章id来查找文章
    public Article findArticleById(String id);
    //点赞数统计
    public List<String> likes(String id);
    //分享数
    public List<String> shares(String id);
    //评论数
    public List<String> comment(String id);
    //更具分享
    public List<String> findArticleByShare(String id);
    //查找草稿
    public List<Article> findRoughById(String id);
    //查找待审核
    public List<Article> findAuditById(String id);
    //文章标题来查找对应文章
    public Article findArticle(String name);
    //查询评论
    public List<Discuss> findDiscuss(String id);
    //文章名来查找文章id
    public Integer findId(String id);
    //查询文章类型
    public String find_type(String name);
    public void addDiscuss(String article_id,String user_id,String content,String data,String userPhoto);
    public void addShare(String article_id,String user_id,String date);
    public void addLike(String article_id,String user_id,String date);
    //更新
    public void changeArticle(String article_name,String s1,String s2,String s3,String s4,String s5,String s6,String s7,String s8);
}
