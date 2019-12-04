package com.briup.apps.cms.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

 
  import com.briup.apps.cms.bean.Test; 
  import com.briup.apps.cms.bean.TestExample; 
  
 
  import  com.briup.apps.cms.dao.TestMapper;
 
/**
 * 测试业务逻辑实现圂
 * @author lenovo
 *
 */
 
  @Service
  public class TestServiceImpl implements ITestService {

      @Resource
      private TestMapper testMapper;

      @Override
      public List<Test> findAll() {
          TestExample example = new TestExample();
          return testMapper.selectByExample(example);
      }

      @Override
      public void saveOrUpdate(Test test) {
          if(test.getId()!=null){
              testMapper.updateByPrimaryKey(test);
          } else {
              testMapper.insert(test);
          }
      }
  }
 