package com.cskaoyan.mall.bean.lxs;

import java.util.Arrays;

public class lxsAdmin {
    Integer id;
    String username;
    String avatar;
    int[] roleIds;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int[] getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(int[] roleIds) {
        this.roleIds = roleIds;
    }

    @Override
    public String toString() {
        return "lxsAdmin{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", avatar='" + avatar + '\'' +
                ", role_ids=" + Arrays.toString(roleIds) +
                '}';
    }
}
