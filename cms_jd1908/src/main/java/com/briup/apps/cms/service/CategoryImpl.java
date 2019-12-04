package com.briup.apps.cms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.apps.cms.bean.Category;
import com.briup.apps.cms.bean.CategoryExample;
import com.briup.apps.cms.dao.CategoryMapper;
import com.briup.apps.cms.utils.CustomerException;

/**
 * 栏目管理的业务实现类
 * @author lenovo
 *
 */
@Service
public class CategoryImpl implements ICategoryService{
   @Autowired
   private CategoryMapper categorymapper;
	 
	public List<Category> findAll() {
		CategoryExample example = new CategoryExample();
	 return categorymapper.selectByExample(example);
	}

	@Override
	public void saveOrUpdate(Category category) throws CustomerException {
		 if(category.getId()!=null) {
			 //更新
			 categorymapper.updateByPrimaryKey(category);
		 }
		 else {
		// 保存
			 CategoryExample example = new CategoryExample();
			 example.createCriteria().andNameEqualTo(category.getName());
			 List<Category> list = categorymapper.selectByExample(example);
			 if(list.size()>0) {
				throw new CustomerException("栏目不可以重名"); 
			 }
		categorymapper.insert(category);
	}
	}

	@Override
	public void deleteById(long id) throws CustomerException {
		 Category category = categorymapper.selectByPrimaryKey(id);
		 if(category==null) {
			 throw new CustomerException("该栏目不存在");
		 }
		categorymapper.deleteByPrimaryKey(id);
	}

	@Override
	public void batchDelete(long[] ids) throws CustomerException {
	 for(long id:ids) {
		 this.deleteById(id);
	 }
		
	}

}
