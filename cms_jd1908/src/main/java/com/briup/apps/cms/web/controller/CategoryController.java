package com.briup.apps.cms.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.briup.apps.cms.bean.Category;
import com.briup.apps.cms.service.ICategoryService;
import com.briup.apps.cms.utils.Message;
import com.briup.apps.cms.utils.MessageUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("栏目相关接口")
@RestController
@RequestMapping("/category")
public class CategoryController {
	@Autowired
	private ICategoryService categoryService;
	@ApiOperation(value="查询所有")
	@GetMapping(value="findAll")
public Message findAll() {
	List<Category> list = categoryService.findAll();
	return MessageUtil.success(list);
}
	@ApiOperation(value="通过id删除")
	@GetMapping(value="deleteById")
public Message deleteById(long id) {
	categoryService.deleteById(id);
	return MessageUtil.success("删除成功");
}
	@ApiOperation(value="批量删除")
	@PostMapping(value="batchDelete")
public Message batchDelete(long[] ids) {
	categoryService.batchDelete(ids);
	return MessageUtil.success("删除成功");
}
	
	@ApiOperation(value="保存或者更新",notes="保存的时候无需传递id,"
			+ "如果传id后台认为要完成更新操作")
	@PostMapping(value="saveOrUpdate")
public Message saveOrUpdate(Category category) {
	categoryService.saveOrUpdate(category);
	return MessageUtil.success("更新成功");
			
}


}
