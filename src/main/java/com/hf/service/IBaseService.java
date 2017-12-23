package com.hf.service;

import java.util.List;

/**
 * Created by fjm on 2017/12/23.
 */
public interface IBaseService<T> {

    List<T> select(T condition, int pageNum, int pageSize);

    T insert(T record);

    T insertSelective(T record);

    T updateByPrimaryKey(T record);

    T updateByPrimaryKeySelective(T record);


    T selectByPrimaryKey(T record);

    int deleteByPrimaryKey(T record);

    List<T> selectAll();

    int batchDelete(List<T> list);

    int delete(T t);

}
