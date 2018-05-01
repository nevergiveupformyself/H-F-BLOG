package com.hf.social;

import com.hf.social.api.QQ;
import com.hf.social.domain.QQUserInfo;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

/**
 * 构造qq连接池时需要该类型参数，目测用于回调
 *
 * @author jingmin.feng@hand-china.com
 * @create 2018-05-01 9:42
 **/
public class QQAdapter implements ApiAdapter<QQ> {

    public QQAdapter() {
        super();
    }

    @Override
    public boolean test(QQ api) {
        return true;
    }

    @Override
    public void setConnectionValues(QQ api, ConnectionValues values) {
        QQUserInfo userInfo = api.getUserInfo();
        values.setProviderUserId(userInfo.getOpenId());//openId 唯一标识
        values.setDisplayName(userInfo.getNickname());
        values.setImageUrl(userInfo.getFigureurl_qq_1());
        values.setProfileUrl(null);
    }

    @Override
    public UserProfile fetchUserProfile(QQ api) {
        return null;
    }

    @Override
    public void updateStatus(QQ api, String message) {

    }
}