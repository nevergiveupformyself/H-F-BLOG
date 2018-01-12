package com.hf.service;

import com.hf.dto.Article;


/**
 * Created by fjm on 2017/12/25.
 */
public interface IArticleService extends IBaseService<Article>{

    String markDown2html(Article article);

}
