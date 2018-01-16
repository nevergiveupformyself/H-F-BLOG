package com.hf.service.impl;

import com.github.pagehelper.PageHelper;
import com.hf.service.IBaseService;
import com.hf.util.MyMapper;
import com.hf.util.ProxySelf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by fjm on 2017/12/23.
 */
public abstract class BaseServiceImpl<T> implements IBaseService<T> {

    @Autowired
    private MyMapper<T> baseMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<T> select(T condition, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return baseMapper.select(condition);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<T> selectAll() {
        return baseMapper.selectAll();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public T selectByPrimaryKey(T record) {
        return baseMapper.selectByPrimaryKey(record);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public T insert(T record) {
        baseMapper.insert(record);
        return record;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public T insertSelective(T record) {
        baseMapper.insertSelective(record);
        return record;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public T updateByPrimaryKey(T record) {
        baseMapper.updateByPrimaryKey(record);
        return record;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public T updateByPrimaryKeySelective(T record) {
        baseMapper.updateByPrimaryKeySelective(record);
        return record;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteByPrimaryKey(T record) {
        return baseMapper.deleteByPrimaryKey(record);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchDelete(List<T> list) {
        IBaseService<T> self = (IBaseService<T>) self();
        int c = 0;
        for (T t : list) {
            c += self.deleteByPrimaryKey(t);
        }
        return c;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delete(T record) {
        return baseMapper.delete(record);
    }
}
