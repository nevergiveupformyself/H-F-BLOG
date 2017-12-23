package com.hf.service.impl;

import com.hf.dto.Demo;
import com.hf.service.IDemoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by fjm on 2017/12/23.
 */
@Transactional
@Service
public class DemoServiceImpl extends BaseServiceImpl<Demo> implements IDemoService{
}
