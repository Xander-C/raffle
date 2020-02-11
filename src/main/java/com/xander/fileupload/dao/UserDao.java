package com.xander.fileupload.dao;

import com.xander.fileupload.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface UserDao {
    @Select("select id as id,sid as sid,name as name from user")
    List<User> findAllUser();

    @Select("SELECT id as id,sid as sid,name as name\n " +
            "FROM user\n" +
            "WHERE id >= (\n" +
            "    (SELECT MAX(id) FROM user)-(SELECT MIN(id) FROM user))*RAND() + (SELECT MIN(id) FROM user\n" +
            ") LIMIT 1")
    User getRand();

    @Insert("insert into user" + " (sid, name) " + " values " + "(#{sid}, #{name})")
    void insertUserByParam(User bean);

    @Update("TRUNCATE TABLE user")
    void deleteAll();

}
