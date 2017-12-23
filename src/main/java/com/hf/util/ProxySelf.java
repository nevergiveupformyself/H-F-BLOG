package com.hf.util;

import org.springframework.aop.framework.AopContext;

/**
 * 用于获取当前bean的代理对象
 *
 * Created by fjm on 2017/12/23.
 */
public interface ProxySelf<T> {
    default T self(){
        return (T) AopContext.currentProxy();
    }
}
