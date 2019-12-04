package com.briup.apps.cms.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.briup.apps.cms.bean.BaseUser;
import com.briup.apps.cms.bean.BaseUserExample;
import com.briup.apps.cms.bean.UserRole;
import com.briup.apps.cms.bean.UserRoleExample;
import com.briup.apps.cms.bean.extend.BaseUserExtend;
import com.briup.apps.cms.dao.BaseUserMapper;
import com.briup.apps.cms.dao.UserRoleMapper;
import com.briup.apps.cms.dao.extend.BaseUserExtendMapper;
import com.briup.apps.cms.utils.CustomerException;
import com.briup.apps.cms.vm.UserVM;

/**
 * 用户类
 * @author lenovo
 *
 */
 
 //用户业务实现类
@Service
public class BaseUserServiceImpl implements IBaseUserService {
    @Resource
    private BaseUserExtendMapper baseUserExtendMapper;
    @Resource
    private BaseUserMapper baseUserMapper;
    @Resource
    private UserRoleMapper baseUserRoleMapper;

    @Override
    public BaseUserExtend findById(long id) {
    	return baseUserExtendMapper.selectById(id);
    }
    
    @Override
    public List<BaseUser> findAll() {
    	return baseUserMapper.selectByExample(new BaseUserExample());
    }
    //级联查询所有
    @Override
    public List<BaseUserExtend> cascadeRoleFindAll() {
    	return baseUserExtendMapper.selectAll();
    }
    //绑定角色
    @Override
    public void setRoles(long id, List<Long> roles) {
    	// 根据userid查询原来自己的角色
    	UserRoleExample example = new UserRoleExample();
    	example.createCriteria().andUserIdEqualTo(id);
    	// 用户角色关系,获取所有老的角色
    	List<UserRole> list = baseUserRoleMapper.selectByExample(example);
    	List<Long> oldRoles = new ArrayList<>();
    	//oldRoles放角色的id
    	Iterator<UserRole> iterator = list.iterator();
    	while(iterator.hasNext()){
    		oldRoles.add(iterator.next().getRoleId());
    	}
    	// [1,2,3] -> [3,4] 添加1,2 => [1,2,3,4]
    	// 依次判断新角色是否存在于list中，如果不存在则添加
    	for(Long roleId : roles){
    		if(!oldRoles.contains(roleId)){
    			UserRole userRole = new UserRole();
    			userRole.setRoleId(roleId);
    			userRole.setUserId(id);
    			baseUserRoleMapper.insert(userRole);
    		}
    	}
    	// [1,2,3] -> [1,2,3,4]   删除 3,4  => [1,2]
    	// 依次判断老的角色是否存在于roles中，如果不存在则删除
    	//在桥表中删除
    	for(UserRole userRole : list){
    		if(!roles.contains(userRole.getRoleId())){
    			UserRoleExample example1 = new UserRoleExample();
    			baseUserRoleMapper.deleteByPrimaryKey(userRole.getId());
    		}
    	}
    	
    	
    }
    @Override
    public BaseUser login(UserVM userVM) throws CustomerException {
        BaseUserExample example = new BaseUserExample();
        example.createCriteria().andUsernameEqualTo(userVM.getUsername());
        List<BaseUser> list = baseUserMapper.selectByExample(example);
        if(list.size()<=0){
            throw new CustomerException("该用户不存在");
        }
        BaseUser user = list.get(0);
        if(!user.getPassword().equals(userVM.getPassword())){
            throw new CustomerException("密码不匹配");
        }
        return user;

    }



    @Override
    public void saveOrUpdate(BaseUser baseUser) throws CustomerException {
        if(baseUser.getId()!=null){
            baseUserMapper.updateByPrimaryKey(baseUser);
        } else {
            // 判断用户名是否被占用
            BaseUserExample example = new BaseUserExample();
            example.createCriteria().andUsernameEqualTo(baseUser.getUsername());
            List<BaseUser> list = baseUserMapper.selectByExample(example);
            if(list.size()>0){
                throw new CustomerException("该用户已经被占用");
            }
            // 初始化
            baseUser.setRegisterTime(new Date().getTime());
            baseUser.setStatus(BaseUserExtend.STATUS_NORMAL);
            baseUserMapper.insert(baseUser);
        }
    }

    @Override
    public void changeStatus(long id,String status) throws CustomerException {
        BaseUser user = this.findById(id);
        if(user==null){
            throw new CustomerException("该用户不存在");
        }
        user.setStatus(status);
        baseUserMapper.updateByPrimaryKey(user);
    }

    @Override
    public void deleteById(long id) throws CustomerException {
        BaseUser user = this.findById(id);
        if(user==null){
            throw new CustomerException("该用户不存在");
        }
        baseUserMapper.deleteByPrimaryKey(id);
    }
}

