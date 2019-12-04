package com.briup.apps.cms.dao.extend;

import com.briup.apps.cms.bean.BasePrivilege;
import com.briup.apps.cms.vm.PrivilegeTree;

import java.util.List;

public interface BasePrivilegeExtendMapper {
	//查询权限树
    List<PrivilegeTree> selectAll();
  //通过父id查询子权限
    List<BasePrivilege> selectByParentId(long id);
//通过 角色id查询权限
    List<BasePrivilege> selectByRoleId(long id);

    List<BasePrivilege> selectByUserId(long id);
}
