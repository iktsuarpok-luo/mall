package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.Permission;
import com.cskaoyan.mall.bean.PermissionExample;
import java.util.List;

import com.cskaoyan.mall.bean.lxs.data.datafour;
import org.apache.ibatis.annotations.Param;

public interface PermissionMapper {
    long countByExample(PermissionExample example);

    int deleteByExample(PermissionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Permission record);

    int insertSelective(Permission record);

    List<Permission> selectByExample(PermissionExample example);

    Permission selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Permission record, @Param("example") PermissionExample example);

    int updateByExample(@Param("record") Permission record, @Param("example") PermissionExample example);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);

    void insertdata(@Param("data") datafour datafour);

    void deleteByRoleId(@Param("roleid") int roleId);
}