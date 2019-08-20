package com.cskaoyan.mall.service.lxs;



import com.cskaoyan.mall.bean.lxs.lxsAdmin;
import com.cskaoyan.mall.bean.lxs.lxsAdminTwo;
import com.cskaoyan.mall.bean.lxs.lxsRole;
import com.cskaoyan.mall.mapper.AdminMapper;
import com.cskaoyan.mall.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.List;
@Component
public class AdminServiceImpl implements AdminService{
    @Autowired
    AdminMapper adminMapper;

    @Autowired
    RoleMapper roleMapper;



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
    public int usernameExist(String username) {
       lxsAdminTwo admin=adminMapper.selectByName(username);
       if(admin==null){
           return 0;
       }
        return 1;
    }

    @Override
    public lxsAdminTwo selectByName(String username) {
        lxsAdminTwo admin=adminMapper.selectByName(username);
        return admin;
    }

    @Override
    public int update(lxsAdminTwo admin) {
        lxsAdminTwo user = selectByName(admin.getUsername());
        if(user!=null){
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


}
