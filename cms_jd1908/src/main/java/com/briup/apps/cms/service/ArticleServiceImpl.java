package com.briup.apps.cms.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.briup.apps.cms.bean.Article;
import com.briup.apps.cms.bean.ArticleExample;
import com.briup.apps.cms.bean.extend.ArticleExtend;
import com.briup.apps.cms.dao.ArticleMapper;
import com.briup.apps.cms.dao.extend.ArticleExtendMapper;
import com.briup.apps.cms.utils.CustomerException;

@Service
public class ArticleServiceImpl implements IArticleService {
	@Resource
	private ArticleMapper articleMapper;
	@Resource
	private ArticleExtendMapper articleExtendMapper;

	@Override
	public List<Article> findAll() {

		ArticleExample example = new ArticleExample();
		return articleMapper.selectByExample(example);
	}

	@Override
	public void saveOrUpdate(Article article)throws CustomerException {
		 //判断是保存还是更新
		   if(article.getId()!=null) {
		 //更新
			   articleMapper.updateByPrimaryKey(article);
		  }else {
		  //初始化
			 ArticleExample example = new ArticleExample(); 
			  example.createCriteria().andTitleEqualTo(article.getTitle());
			  List<Article> list = articleMapper.selectByExample(example);
			  if(list.size()>0) {
				 //标题名不能重复 
				throw new   CustomerException("文章标题不能重复");
			  }
			  article.setStatus(ArticleExtend.STATUS_UNCHECK);
			  article.setThumpUp(0l);
			  article.setThumpDown(0l);
			  article.setPublishTime(new Date().getTime());
			  article.setReadTimes(0l);
			 
			  articleMapper.insert(article);
		   }
		 
	}

	@Override
	public List<ArticleExtend> cascadeFindAll() {
		// TODO Auto-generated method stub
		return articleExtendMapper.selectAll();
	}

	@Override
	public ArticleExtend findById(long id) {
		return articleExtendMapper.selectById(id);
	}

	@Override
	public void deleteById(long id) throws CustomerException{
		Article article = articleMapper.selectByPrimaryKey(id);
		if(article==null) {
			throw new CustomerException("该文章不存在");
		}
		
		 articleMapper.deleteByPrimaryKey(id);
		
	}

}
