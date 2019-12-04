package com.briup.apps.cms.dao.extend;

import com.briup.apps.cms.bean.extend.BaseUserExtend;

import java.util.List;

public interface BaseUserExtendMapper {
    BaseUserExtend selectById(long id);
     //级联查询，查询所有
    List<BaseUserExtend> selectAll();
}
