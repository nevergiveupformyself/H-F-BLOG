package com.hf.social;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author jingmin.feng@hand-china.com
 * @create 2018-05-02 8:39
 **/
//@Component
public class MyJdbcConnectionRepository implements ConnectionRepository {

    private final String userId;

    private final ConnectionFactoryLocator connectionFactoryLocator;

    private final BaseMapper<?> userMapper;

    public MyJdbcConnectionRepository(String userId, ConnectionFactoryLocator connectionFactoryLocator, BaseMapper userMapper){
        this.userId = userId;
        this.connectionFactoryLocator = connectionFactoryLocator;
        this.userMapper = userMapper;
    }

    @Override
    public MultiValueMap<String, Connection<?>> findAllConnections() {
        return null;
    }

    @Override
    public List<Connection<?>> findConnections(String providerId) {
        return null;
    }

    @Override
    public <A> List<Connection<A>> findConnections(Class<A> apiType) {
        return null;
    }

    @Override
    public MultiValueMap<String, Connection<?>> findConnectionsToUsers(MultiValueMap<String, String> providerUserIds) {
        return null;
    }

    @Override
    public Connection<?> getConnection(ConnectionKey connectionKey) {
        return null;
    }

    @Override
    public <A> Connection<A> getConnection(Class<A> apiType, String providerUserId) {
        return null;
    }

    @Override
    public <A> Connection<A> getPrimaryConnection(Class<A> apiType) {
        return null;
    }

    @Override
    public <A> Connection<A> findPrimaryConnection(Class<A> apiType) {
        return null;
    }

    @Override
    public void addConnection(Connection<?> connection) {

    }

    @Override
    public void updateConnection(Connection<?> connection) {

    }

    @Override
    public void removeConnections(String providerId) {

    }

    @Override
    public void removeConnection(ConnectionKey connectionKey) {

    }
}
