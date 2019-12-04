package com.briup.apps.cms.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.briup.apps.cms.bean.BaseRole;
import com.briup.apps.cms.bean.BaseRoleExample;
import com.briup.apps.cms.bean.RolePrivilege;
import com.briup.apps.cms.bean.RolePrivilegeExample;
import com.briup.apps.cms.bean.extend.BaseRoleExtend;
import com.briup.apps.cms.dao.BaseRoleMapper;
import com.briup.apps.cms.dao.RolePrivilegeMapper;
import com.briup.apps.cms.dao.extend.BaseRoleExtendMapper;
import com.briup.apps.cms.utils.CustomerException;

/**
 * @program: cms_jd1911
 * @description: 角色实现类
 * @author: charles
 * @create: 2019-11-16 15:55
 **/
@Service
public class BaseRoleServiceImpl implements IBaseRoleService {
    @Resource
    private BaseRoleMapper baseRoleMapper;
    @Resource
    private BaseRoleExtendMapper baseRoleExtendMapper;
    @Resource
    private RolePrivilegeMapper baseRolePrivilegeMapper;

    public List<BaseRole> findAll() {
    	
    	return baseRoleMapper.selectByExample(new BaseRoleExample());
    }


    
    public void authorization(long roleId, List<Long> privilegeIds) {
        // 根据roleid查询出所有的权限
        RolePrivilegeExample example = new RolePrivilegeExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        List<RolePrivilege> list = baseRolePrivilegeMapper.selectByExample(example);
        // 将list转换为privilegeIDs的集合
        List<Long> old_privilegeIds = new ArrayList<>();
        for(RolePrivilege rp : list){
            old_privilegeIds.add(rp.getPrivilegeId());
        }
        // 依次判断privilegeIds 是否存在old_privilegeIds，如果不在则插入
        for(long privilegeId : privilegeIds){
            if (!old_privilegeIds.contains(privilegeId)) {
                RolePrivilege rp = new RolePrivilege();
                rp.setRoleId(roleId);
                rp.setPrivilegeId(privilegeId);
                baseRolePrivilegeMapper.insert(rp);
            }
        }
        // 依次判断 是否存在old_privilegeIds 是否存在privilegeIds，如果不存在删除
        for(long privilegeId: old_privilegeIds){
            if(!privilegeIds.contains(privilegeId)){
                // 根据privilegeId 从桥表中删除
                example.clear();
                example.createCriteria()
                        .andRoleIdEqualTo(roleId)
                        .andPrivilegeIdEqualTo(privilegeId);
                baseRolePrivilegeMapper.deleteByExample(example);
            }
        }

    }

    

    @Override
    public List<BaseRoleExtend> cascadePrivilegeFindAll() {
        return baseRoleExtendMapper.selectAll();
    }

    @Override
    public void saveOrUpdate(BaseRole baseRole) throws CustomerException {
        if(baseRole.getId()!=null){
            baseRoleMapper.updateByPrimaryKey(baseRole);
        } else {
            baseRoleMapper.insert(baseRole);
        }
    }

    @Override
    public void deleteById(long id) throws CustomerException {
        BaseRole role = baseRoleMapper.selectByPrimaryKey(id);
        if(role == null){
            throw new CustomerException("要删除的角色不存在");
        }
        baseRoleMapper.deleteByPrimaryKey(id);
    }
}
