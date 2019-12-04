package com.briup.apps.cms.service;

import com.briup.apps.cms.bean.BaseUser;
import com.briup.apps.cms.bean.extend.BaseUserExtend;
import com.briup.apps.cms.utils.CustomerException;
import com.briup.apps.cms.vm.UserVM;

import java.util.List;
 //用户接口

public interface IBaseUserService {
	//通过id查询
	BaseUserExtend findById(long id);
	
	//查询所有
	List<BaseUser> findAll();
    
	// 设置角色   
	void setRoles(long id, List<Long> roles);
	// 级联查询所有
	List<BaseUserExtend> cascadeRoleFindAll();
	
    /** 
     * @Description: 登录
     * @Param: [userVM] 
     * @return: void 
     * @Author: charles 
     * @Date: 2019-11-18 
     */
    BaseUser login(UserVM userVM) throws CustomerException;
    

    /**
     * @Description:  保存或更新
     * @Param: [baseUser]
     * @return: void
     * @Author: charles
     * @Date: 2019-11-16
     */
    void saveOrUpdate(BaseUser baseUser) throws CustomerException;

    /** 
     * @Description: 更新状态
     * @Param: [status] 
     * @return: void 
     * @Author: charles 
     * @Date: 2019-11-16 
     */ 
    void changeStatus(long id,String status) throws CustomerException;
    
    /** 
     * @Description: 通过id删除
     * @Param: [id] 
     * @return: void 
     * @Author: charles 
     * @Date: 2019-11-17
     */ 
    void deleteById(long id) throws CustomerException;
    
}
