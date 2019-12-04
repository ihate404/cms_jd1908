package com.briup.apps.cms.service;

import java.util.List;

import com.briup.apps.cms.bean.Article;
import com.briup.apps.cms.bean.extend.ArticleExtend;
import com.briup.apps.cms.utils.CustomerException;

public interface IArticleService {
 List<Article> findAll();
 List<ArticleExtend> cascadeFindAll();
 void saveOrUpdate(Article article)throws CustomerException;
 ArticleExtend findById(long id);
 void deleteById(long id)throws CustomerException;
 
}
