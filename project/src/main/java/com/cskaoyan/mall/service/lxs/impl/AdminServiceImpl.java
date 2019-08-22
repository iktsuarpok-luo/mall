package com.cskaoyan.mall.service.lxs.impl;



import com.cskaoyan.mall.bean.*;
import com.cskaoyan.mall.bean.lxs.Impower;
import com.cskaoyan.mall.bean.lxs.data.datathree;
import com.cskaoyan.mall.bean.lxs.data.datatwo;
import com.cskaoyan.mall.bean.lxs.lxsAdmin;
import com.cskaoyan.mall.bean.lxs.lxsAdminTwo;
import com.cskaoyan.mall.bean.lxs.lxsRole;
import com.cskaoyan.mall.mapper.*;

import com.cskaoyan.mall.service.lxs.AdminService;
import org.springframework.beans.factory.annotation.Autowired;


import java.lang.System;

import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminMapper adminMapper;

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    LogMapper logMapper;
    @Autowired
    SystemPermissionsMapper systemPermissionsMapper;
    @Autowired
    PermissionMapper permissionMapper;

    @Override
    public List<lxsAdmin> getdata() {

        return adminMapper.getdata();
    }

    @Override
    public List<lxsRole> getOptionData() {
        return roleMapper.getOptionData();
    }

    @Override
    public void deleteById(int id) {
        adminMapper.deleteByPrimaryKey(id);

    }

    @Override
    public void insert(lxsAdminTwo admin) {
        Date date = new Date();
        admin.setAddTime(date);
        adminMapper.getInsert(admin);
    }

    @Override
    public int usernameExist(String username){
       lxsAdminTwo admin=adminMapper.selectByName(username);
       if(admin!=null){
               return 1;
       }
        return 0;
    }

    @Override
    public lxsAdminTwo selectByName(String username) {
        lxsAdminTwo admin=adminMapper.selectByName(username);
        return admin;
    }

    @Override
    public int update(lxsAdminTwo admin) {
        lxsAdminTwo user = selectByName(admin.getUsername());
        lxsAdminTwo user1 = selectById(admin.getId()) ;
        if(user!=null && !(user.getUsername().equals(user1.getUsername()))){
            return 1;
        }
        admin.setUpdateTime(new Date());
        adminMapper.updateById(admin);
        return 0;
    }

    @Override
    public lxsAdminTwo selectById(Integer id) {

        return adminMapper.selectById(id);
    }

    @Override
    public List<lxsAdmin> searchByName(String username) {

        return adminMapper.searchByName(username);
    }

    @Override
    public void logInsert(Log log) {
        int insert = logMapper.insert(log);
        System.out.println(insert);
    }

    @Override
    public List<Log> getLog() {
        LogExample logExample = new LogExample();
       logExample.createCriteria().andIdIsNotNull();
        return logMapper.selectByExample(logExample);
    }

    @Override
    public List<Role> getAllRoleList() {
        RoleExample roleExample = new RoleExample();
        roleExample.createCriteria().andIdIsNotNull();
        List<Role> roles = roleMapper.selectByExample(roleExample);
        return roles;
    }

    @Override
    public int roleUpdate(Role role) {
        Role role1 = roleMapper.selectByPrimaryKey(role.getId());
        Role role2 = roleMapper.selectByName(role.getName());
        if(role2!=null&&!(role1.getName().equals(role2.getName()))){
            return 1;
        }
        role.setUpdateTime(new Date());
        roleMapper.updateByPrimaryKey(role);
        return 0;
    }

    @Override
    public int roleInsert(Role role1) {
        Role role = selectByRoleName(role1.getName());
        if(role!=null){
            return 1;
        }
        roleMapper.insert(role1);
        return 0;
    }

    @Override
    public Role selectByRoleName(String name) {
        Role role = roleMapper.selectByName(name);
        return role;
    }

    @Override
    public int deleteRole(Role role) {
        int id = role.getId();
        String s = String.valueOf(id);

        List<Admin> admins = adminMapper.selectByRoleIds(s);
        if(admins.size()==0)
        {
            roleMapper.deleteByPrimaryKey(role.getId());
            return 0;
        }
        else{
            return 1;
        }

    }

    @Override
    public List<Role> selectByRoleNames(String name) {

        return  roleMapper.selectByNames(name);
    }

    @Override
    public datatwo[] selectAllSP() {
        //datafour datafour=SystemPermissionsMapper.selectAllSP
        datatwo<datathree[]>[] datatwos =systemPermissionsMapper.selectDataTwo();
        for (datatwo datatwo : datatwos) {
            String id = datatwo.getId();
            datathree[] datathrees = systemPermissionsMapper.selectDataThree(id);
            for (datathree datathree : datathrees) {
                String id1 = datathree.getId();
                datathree.setChildren(systemPermissionsMapper.selectDataFour(id1));
            }
            datatwo.setChildren(datathrees);
        }

        return datatwos;
    }

    @Override
    public String[] selectPermission(int roleId) {
        Permission[] permissions;
        if(roleId!=1) {
             permissions = systemPermissionsMapper.selectPermission(roleId);
        }else {
            permissions = systemPermissionsMapper.selectAll();
        }
        String[] strings = new String[permissions.length];
        for(int i=0; i<permissions.length;i++){
            strings[i]=permissions[i].getPermission();
        }
        return strings;
    }

    @Override
    public void insertPower(Impower impower) {
        String[] permissions = impower.getPermissions();
        int roleId = impower.getRoleId();

        if(roleId!=1) {
            permissionMapper.deleteByRoleId(roleId);
            Permission permission = new Permission();

            for (String p : permissions) {
                permission.setAddTime(new Date());
                permission.setDeleted(true);
                permission.setUpdateTime(new Date());
                permission.setRoleId(roleId);
                permission.setPermission(p);
                permissionMapper.insert(permission);

            }
        }

         /* 这是用循环的方法插入数据，适用于数据库唯一的的字段
        Permission[] permissions1 = systemPermissionsMapper.selectPermission(roleId);
        String[] strings = new String[permissions1.length];
        for(int i=0; i<permissions.length;i++){
            strings[i]=permissions1[i].getPermission();
        }

       ArrayList<String> inserts = new ArrayList<>();
        ArrayList<String> deletes = new ArrayList<>();

        for(int i=0;i<strings.length;i++){
            for(int j=0;j<permissions.length;j++){
                if(!(strings[i].equals(permissions[j]))&& j==permissions.length-1){
                    deletes.add(strings[i]);
                }
                if(!(permissions[j].equals(strings[i]))&& i==strings.length-1){
                    inserts.add(permissions[j]);
                }
            }
        }*/


    }


}
