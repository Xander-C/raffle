package com.xander.fileupload.dao;

import com.xander.fileupload.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface UserDao {
    @Select("select sid as sid,name as name from user")
    List<User> findAllUser();

    @Select("SELECT sid as sid,name as name FROM `user`  AS t1 JOIN " +
            "(SELECT ROUND(RAND() * (SELECT MAX(id) FROM `user`) + 0.5) AS id) " +
            "AS t2 WHERE t1.id >= t2.id ORDER BY t1.id ASC LIMIT 1")
    User getRand();

    @Insert("insert into user" + " (sid, name) " + " values " + "(#{sid}, #{name})")
    void insertUserByParam(User bean);

    @Update("TRUNCATE TABLE user")
    void deleteAll();

}
