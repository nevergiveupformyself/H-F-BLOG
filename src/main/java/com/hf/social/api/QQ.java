package com.hf.social.api;

import com.hf.social.domain.QQUserInfo;

/**
 * QQ返回信息的接口
 *
 * @author jingmin.feng@hand-china.com
 * @create 2018-04-30 23:36
 **/
public interface QQ {
    /**
     * 获取用户信息
     * @return
     */
    QQUserInfo getUserInfo();
}
