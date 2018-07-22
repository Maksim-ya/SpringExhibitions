package com.maksim.service;

import com.maksim.controller.manager.Logs;
import com.maksim.model.dao.UserDao;
import com.maksim.model.domain.User;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private static final Logger logger = Logger.getLogger(UserService.class);
    @Autowired
    private UserDao userDao;
//    private static final UserDao userDao = DaoFactoryImpl.getInstance().getUserDao();
//    private static final UserService USER_SERVICE = new UserService();
//
//    private UserService() {}
//
//    public static UserService getService() {
//        return USER_SERVICE;
//    }

    public static String encryptPassword(String st) {
        String md5Hex = DigestUtils.md5Hex(st);
        return md5Hex;
    }

    @Transactional
    public boolean addUser(User user) {
        userDao.addUser(user);
        user.setPassword(encryptPassword(user.getPassword()));
        logger.info(Logs.USER_REGISTER);
        return true;
    }

    public User checkLoginAndPassword(String login, String password) {
        return userDao.checkLoginAndPassword(login, encryptPassword(password));
    }
}
