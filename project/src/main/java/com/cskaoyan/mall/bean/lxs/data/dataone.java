package com.cskaoyan.mall.bean.lxs.data;


import java.util.Arrays;

public class dataone<T> {
 String[] assignedPermissions;
 T[] systemPermissions;





    public String[] getAssignedPermissions() {
        return assignedPermissions;
    }

    public void setAssignedPermissions(String[] assignedPermissions) {
        this.assignedPermissions = assignedPermissions;
    }

    public T[] getSystemPermissions() {
        return systemPermissions;
    }

    public void setSystemPermissions(T[] systemPermissions) {
        this.systemPermissions = systemPermissions;
    }


    @Override
    public String toString() {
        return "dataone{" +
                "assignedPermissions=" + Arrays.toString(assignedPermissions) +
                ", systemPermissions=" + Arrays.toString(systemPermissions) +
                '}';
    }
}
