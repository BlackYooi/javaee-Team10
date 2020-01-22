package com.kindblack.team10.dao;

import com.kindblack.team10.POJO.Permission.Permission;
import com.kindblack.team10.POJO.Permission.UserInfo;
import com.kindblack.team10.Utils.PermissionCode;
import com.kindblack.team10.dao.DaoInter.PermissionInter;
import com.kindblack.team10.dao.DaoInter.UserInfoInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author black
 * @date 2019/12/28 - 11:34
 */
@Component
public class UserRolePermission {

    @Autowired
    PermissionCode permissionCode;
    @Autowired
    UserInfoInter userInfoInter;
    @Autowired
    PermissionInter permissionInter;

    /*增添普通用户->注册功能*/
    public void addUserInfo(UserInfo userInfo) {
        userInfo.setPermissionCode(permissionCode.getNormalCode());
        userInfo.setRoleName(userInfo.AnNormalName());
        userInfo.setPasswordToMd5();//set可以进行md5加密一次
        userInfoInter.save(userInfo);
    }

    /*查询用户->登录验证*/
    public List<UserInfo> findUserInfoSql(String userName) {
        return userInfoInter.findAllById(Collections.singleton(userName));
    }

    /*删除用户->注销功能（有时间再做）*/
    /*修改用户->改密功能（有时间再做）*/

    /*查找视图BYcode*/
    public List<Permission> getView(List list) {
        List<Permission> list1 = new ArrayList<>();
        //逐个查找list中的视图（第i个视图）
        for (int i = 0; i < list.size(); i++) {
            String code = (String) list.get(i);
            Optional<Permission> element = permissionInter.findById(code);
            list1.add(element.get());
        }
        return list1;
    }

}
