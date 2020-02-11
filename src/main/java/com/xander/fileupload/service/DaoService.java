package com.xander.fileupload.service;

import com.xander.fileupload.User;
import com.xander.fileupload.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("daoService")
public class DaoService {

    @Autowired
    private UserDao userDao;
    public List<User> findAllUser(){
        return userDao.findAllUser();
    }
    public void insertUserByParam(User bean){ userDao.insertUserByParam(bean); }
    public void deleteAll(){ userDao.deleteAll(); }
    public User getRand(){ return userDao.getRand(); }
}
