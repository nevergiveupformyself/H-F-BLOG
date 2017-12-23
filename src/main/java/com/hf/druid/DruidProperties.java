package com.hf.druid;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by fjm on 2017/12/23.
 */
@ConfigurationProperties(prefix = "druid")
public class DruidProperties {

    private String url;//连接url
    private String username;//账号
    private String password;//密码
    private String driverClass;//驱动类型
    private int     maxActive;//最大连接数
    private int     minIdle;//最小空闲连接数
    private int     initialSize;//连接池初始化大小
    private boolean testOnBorrow;//指明是否在从池中取出连接前进行检验,如果检验失败,则从池中去除连接并尝试取出另一个.

    public String getDriverClass() {
        return driverClass;
    }

    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }

    public int getInitialSize() {
        return initialSize;
    }

    public void setInitialSize(int initialSize) {
        this.initialSize = initialSize;
    }

    public int getMaxActive() {
        return maxActive;
    }

    public void setMaxActive(int maxActive) {
        this.maxActive = maxActive;
    }

    public int getMinIdle() {
        return minIdle;
    }

    public void setMinIdle(int minIdle) {
        this.minIdle = minIdle;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isTestOnBorrow() {
        return testOnBorrow;
    }

    public void setTestOnBorrow(boolean testOnBorrow) {
        this.testOnBorrow = testOnBorrow;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
