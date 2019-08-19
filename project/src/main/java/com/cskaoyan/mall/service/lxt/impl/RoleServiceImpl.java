package com.cskaoyan.mall.service.lxt.impl;

import com.cskaoyan.mall.bean.Role;
import com.cskaoyan.mall.bean.RoleExample;
import com.cskaoyan.mall.mapper.RoleMapper;
import com.cskaoyan.mall.service.lxt.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleMapper roleMapper;
    @Override
    public List<Role> getAllRoles() {
        List<Role> roles = roleMapper.selectByExample(new RoleExample());
        return roles;
    }

    @Override
    public List<Role> getRoleByIds(int[] roleIds) {
        List<Role> roles = new ArrayList<>();
        for (int roleId : roleIds) {
            roles.add(roleMapper.selectByPrimaryKey(roleId));
        }
        return roles;
    }
}
