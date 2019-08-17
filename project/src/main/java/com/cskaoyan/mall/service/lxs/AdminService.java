package com.cskaoyan.mall.service.lxs;

import com.cskaoyan.mall.bean.lxs.lxsAdmin;
import com.cskaoyan.mall.bean.lxs.lxsAdminTwo;
import com.cskaoyan.mall.bean.lxs.lxsRole;
import java.util.List;

public interface AdminService {
    List<lxsAdmin> getdata();

    List<lxsRole> getOptionData();

    void deleteById(int id);

    void insert(lxsAdminTwo admin);


    int usernameExist(String username);

    lxsAdminTwo selectByName(String username);

    int update(lxsAdminTwo admin);

    lxsAdminTwo selectById(Integer id);

    List<lxsAdmin> searchByName(String username);
}
