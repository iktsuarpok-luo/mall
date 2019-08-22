package com.cskaoyan.mall.mapper;


import com.cskaoyan.mall.bean.Permission;
import com.cskaoyan.mall.bean.lxs.data.datafour;
import com.cskaoyan.mall.bean.lxs.data.datathree;
import com.cskaoyan.mall.bean.lxs.data.datatwo;
import org.apache.ibatis.annotations.Param;

public interface SystemPermissionsMapper {


    datatwo[] selectDataTwo();

    datathree[] selectDataThree(@Param("id") String id);

    datafour[] selectDataFour(@Param("id")String id1);

    Permission[] selectPermission(@Param("id") int roleId);

    Permission[] selectAll();


}
