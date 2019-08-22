package com.cskaoyan.mall.service.lxt;

import com.cskaoyan.mall.bean.Role;

import java.util.List;

public interface RoleService {

    List<Role> getAllRoles();

    List<Role> getRoleByIds(int[] roleIds);
}
