package com.briup.apps.cms.dao.extend;

import java.util.List;

import com.briup.apps.cms.bean.BaseRole;
import com.briup.apps.cms.bean.extend.BaseRoleExtend;
/**
 * 通过用户id查询角色
 * @author lenovo
 *
 */
public interface BaseRoleExtendMapper {
    List<BaseRole> selectByUserId(long id);
    List<BaseRoleExtend> selectAll();
}

