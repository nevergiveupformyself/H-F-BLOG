package com.hf.social;

import com.hf.authorize.SecurityConstants;
import com.hf.social.api.QQ;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.oauth2.OAuth2ServiceProvider;

/**
 * qq的连接工厂，用于处理向qq发出的请求
 *
 * @author jingmin.feng@hand-china.com
 * @create 2018-04-30 23:35
 **/
public class QQConnectionFactory extends OAuth2ConnectionFactory<QQ> {

    /**
     * Create a {@link OAuth2ConnectionFactory}.
     *
     * @param providerId      the provider id e.g. "facebook"
     * @param serviceProvider the ServiceProvider model for conducting the authorization flow and obtaining a native service API instance.
     * @param apiAdapter      the ApiAdapter for mapping the provider-specific service API model to the uniform {@link Connection} interface.
     */
    public QQConnectionFactory(String providerId, OAuth2ServiceProvider<QQ> serviceProvider, ApiAdapter<QQ> apiAdapter) {
        super(providerId, serviceProvider, apiAdapter);
    }

    /**
     *
     * 使用已有的常量进行构造
     *
     */
    public QQConnectionFactory() {
        super(SecurityConstants.DEFAULT_SOCIAL_QQ_PROVIDER_ID, new QQServiceProvider(SecurityConstants.DEFAULT_SOCIAL_QQ_APP_ID, SecurityConstants.DEFAULT_SOCIAL_QQ_APP_SECRET), new QQAdapter());
    }
}
