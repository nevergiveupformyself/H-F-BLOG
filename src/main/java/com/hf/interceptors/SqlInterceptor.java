package com.hf.interceptors;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.util.Properties;

/**
 * Created by fjm on 2018/1/20.
 */
//@Component
@Intercepts( {
//        @Signature(method = "query", type = Executor.class, args = {
//                MappedStatement.class, Object.class, RowBounds.class,
//                ResultHandler.class }),
        @Signature(method = "prepare", type = StatementHandler.class, args = { Connection.class,Integer.class }) })
public class SqlInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object result = invocation.proceed();
        System.out.println("Invocation.proceed()");
        return result;
    }

    @Override
    public Object plugin(Object o) {
        return Plugin.wrap(o, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
