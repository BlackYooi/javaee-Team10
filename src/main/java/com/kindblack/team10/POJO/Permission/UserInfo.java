package com.kindblack.team10.POJO.Permission;

import org.springframework.util.DigestUtils;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author black
 * @date 2019/12/27 - 10:44
 */
@Entity(name = "userinfo")
public class UserInfo {
    /**
     * id
     * 用户名
     * 密码
     * 角色名
     */
    private String userName;
    private String password;
    private String roleName;
    private String nickName;
    private String permissionCode;


    @Id
    @Column(name = "username", nullable = false, length = 50)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 50)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        ;
    }

    public void setPasswordToMd5() {
        this.password = DigestUtils.md5DigestAsHex(this.getPassword().getBytes());
    }

    @Basic
    @Column(name = "rolename", nullable = true, length = 15)
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Basic
    @Column(name = "nickname", nullable = true, length = 15)
    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Basic
    @Column(name = "permissioncode", nullable = false, length = 255)
    public String getPermissionCode() {
        return permissionCode;
    }

    public void setPermissionCode(String permissionCode) {
        this.permissionCode = permissionCode;
    }

    public String AnNormalName() {
        return "普通用户";
    }

    public boolean checkPassword(UserInfo userInfoSql, String password) {
        if (userInfoSql.getPassword()
                .equals(DigestUtils
                        .md5DigestAsHex(password.getBytes()))) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userName='" + userName + '\'' +
                ", roleName='" + roleName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", permissionCode='" + permissionCode + '\'' +
                '}';
    }
}
