package com.hf.social;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

/**
 * 自定义社交登录登录dao
 *
 * @author jingmin.feng@hand-china.com
 * @create 2018-05-02 8:33
 **/
//@Component
public class MyJdbcUserConnectionRepository implements UsersConnectionRepository{
    @Override
    public List<String> findUserIdsWithConnection(Connection<?> connection) {
        return null;
    }

    @Override
    public Set<String> findUserIdsConnectedTo(String providerId, Set<String> providerUserIds) {
        return null;
    }

    @Override
    public ConnectionRepository createConnectionRepository(String userId) {
        return null;
    }
}
