package com.example.demo.mapper;

import com.example.demo.entity.User;

import java.util.List;
import java.util.Map;

public interface userMapper {
    //查找所有用户
    public List<User> findUserList();
    //通过用户昵称查找用户,关键字
    public List<User> findUserByName(String name);
    //通过用户ID来查找用户
    public User FindUser(String id);
    //通过用户id来查找密码
    public String FindPassword(String id);
    //添加新的用户
    public Integer addNewUser(String user_id,String user_name,String password,String position,String phone_number,String photo_file,String about);
    //查询身份
    public String FindPosition(String id);
    //查找所有信息
    public Map<String,String> FindUserDetails(String id);
    public List<String> findFollower(String id);
    //查询文章数
    public List<String> findUserArticle(String id);
    //通过id查找用户
    public  String findUserName(String id);
    //通过用户id查找用户图片
    public String findUserPhoto(String id);
    public List<String> findMyUp(String id);
    //用户昵称来查找用户
    public User findUserByName1(String name);
    public void addFollower(String user,String follower);
    public void deleteFollow(String user,String follower);
}
