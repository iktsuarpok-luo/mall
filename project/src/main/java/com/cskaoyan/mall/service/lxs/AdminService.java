package com.cskaoyan.mall.service.lxs;
import com.cskaoyan.mall.bean.Log;
import com.cskaoyan.mall.bean.Role;
import com.cskaoyan.mall.bean.lxs.Impower;
import com.cskaoyan.mall.bean.lxs.data.datatwo;
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

    void logInsert(Log log);

    List<Log> getLog();

    List<Role> getAllRoleList();

    int roleUpdate(Role role);

    int roleInsert(Role role1);

    Role selectByRoleName(String name);

    int deleteRole(Role role);

    List<Role> selectByRoleNames(String name);

    datatwo[] selectAllSP();

    String[] selectPermission(int roleId);

    void insertPower(Impower impower);
}
