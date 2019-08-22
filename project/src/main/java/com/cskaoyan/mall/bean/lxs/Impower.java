package com.cskaoyan.mall.bean.lxs;

import java.security.Permissions;
import java.util.Arrays;

public class Impower {
    String[] Permissions;
     int roleId;

    @Override
    public String toString() {
        return "Impower{" +
                "Permissions=" + Arrays.toString(Permissions) +
                ", roleId=" + roleId +
                '}';
    }

    public String[] getPermissions() {
        return Permissions;
    }

    public void setPermissions(String[] permissions) {
        Permissions = permissions;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}
