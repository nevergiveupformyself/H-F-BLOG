package com.hf.social;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.ConnectSupport;
import org.springframework.social.connect.web.ProviderSignInController;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.web.context.request.NativeWebRequest;

import javax.sql.DataSource;
import java.lang.reflect.Field;

/**
 * 社交登录配置类
 *
 * @author jingmin.feng@hand-china.com
 * @create 2018-04-30 23:29
 **/
@Configuration
@EnableSocial
public class HFSocialConfig extends SocialAutoConfigurerAdapter implements InitializingBean {

    @Autowired
    private ProviderSignInController providerSignInController;

    @Autowired
    private DataSource dataSource;

    @Override
    protected ConnectionFactory<?> createConnectionFactory() {
        return new QQConnectionFactory();
    }

    @Bean
    public ProviderSignInController providerSignInController(ConnectionFactoryLocator connectionFactoryLocator, UsersConnectionRepository usersConnectionRepository) {
        ProviderSignInController providerSignInController = new ProviderSignInController(connectionFactoryLocator, usersConnectionRepository, new SignInAdapter() {
            @Override
            public String signIn(String userId, Connection<?> connection, NativeWebRequest request) {

                return null;
            }
        });
        return providerSignInController;
    }

    @Bean
    public ProviderSignInUtils providerSignInUtils(ConnectionFactoryLocator factoryLocator) {
        return new ProviderSignInUtils(factoryLocator, getUsersConnectionRepository(factoryLocator));
    }

    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        JdbcUsersConnectionRepository repository = new JdbcUsersConnectionRepository(dataSource,
                connectionFactoryLocator, Encryptors.noOpText());
        return repository;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        try {
            Field connectSupport = providerSignInController.getClass().getDeclaredField("connectSupport");
            connectSupport.setAccessible(true);
            ConnectSupport support = (ConnectSupport) connectSupport.get(providerSignInController);
            support.setCallbackUrl("http://www.merryyou.cn/login/qq");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


}
