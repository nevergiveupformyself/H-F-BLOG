package com.hf.util;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 用于继承的 BaseMapper
 * Created by fjm on 2017/12/22.
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
