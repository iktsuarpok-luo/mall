package com.cskaoyan.mall.service.lxt.impl;

import com.cskaoyan.mall.bean.Permission;
import com.cskaoyan.mall.bean.PermissionExample;
import com.cskaoyan.mall.mapper.PermissionMapper;
import com.cskaoyan.mall.service.lxt.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    PermissionMapper permissionMapper;
    @Override
    public Collection<String> getPermissionsByRoleId(Integer id) {
        PermissionExample permissionExample = new PermissionExample();
        permissionExample.createCriteria().andRoleIdEqualTo(id);
        List<Permission> permissions = permissionMapper.selectByExample(permissionExample);
        List<String> result = new ArrayList<>();
        for (Permission permission : permissions) {
            result.add(permission.getPermission());
        }
        return result;
    }

    @Override
    public List<Permission> getAllPermissions() {
        List<Permission> permissions = permissionMapper.selectByExample(new PermissionExample());
        return permissions;
    }
}
