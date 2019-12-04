package com.briup.apps.cms.vm;

import com.briup.apps.cms.bean.BasePrivilege;

import java.util.List;

//权限树  权限扩展

public class PrivilegeTree extends BasePrivilege {
    private List<BasePrivilege> children;

    public List<BasePrivilege> getChildren() {
        return children;
    }

    public void setChildren(List<BasePrivilege> children) {
        this.children = children;
    }
}
