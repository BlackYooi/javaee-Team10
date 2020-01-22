//package com.kindblack.team10.POJO.Permission;
//
//import javax.persistence.Basic;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import java.util.List;
//
//
///**
//* @author black
//* @date 2019/12/27 - 21:35
//*/
//
//
//@Entity(name = "roleinfo")
//public class RoleInfo {
//
//    /**
//    * id
//    * 角色名
//    * 权限编码（AxxxAxxx-Bxxx-Cxxx）
//    */
//    private String roleNmae;
//    private String permissionCode;
//
//
//    @Id
//    @Column(name = "rolename",nullable = true,length = 50)
//    public String getRoleNmae() {
//        return roleNmae;
//    }
//
//    public void setRoleNmae(String roleNmae) {
//        this.roleNmae = roleNmae;
//    }
//
//    @Basic
//    @Column(name = "permissioncode",nullable = false,length = 50)
//    public String getPermissionCode() {
//        return permissionCode;
//    }
//
//    public void setPermissionCode(String permissionCode) {
//        this.permissionCode = permissionCode;
//    }
//
//}
