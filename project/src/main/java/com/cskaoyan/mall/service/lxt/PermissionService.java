package com.cskaoyan.mall.service.lxt;

import com.cskaoyan.mall.bean.Permission;

import java.util.Collection;
import java.util.List;

public interface PermissionService {
    Collection<String> getPermissionsByRoleId(Integer id);

    List<Permission> getAllPermissions();
}
