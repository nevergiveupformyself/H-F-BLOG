package com.hf.service.impl;

import com.hf.dao.UserMapper;
import com.hf.dto.User;
import com.hf.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by fjm on 2017/12/25.
 */
@Transactional
@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements IUserService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByUserName(String username) {
        return userMapper.findByUserName(username);
    }
}
