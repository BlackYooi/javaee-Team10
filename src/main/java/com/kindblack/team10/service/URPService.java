package com.kindblack.team10.service;

import com.kindblack.team10.POJO.Permission.Permission;
import com.kindblack.team10.POJO.Permission.UserInfo;
import com.kindblack.team10.Utils.ConstantUtils;
import com.kindblack.team10.Utils.PermissionCode;
import com.kindblack.team10.common.LoginHandlerInterceptor;
import com.kindblack.team10.dao.UserRolePermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author black
 * @date 2019/12/28 - 12:40
 */
@Service
public class URPService {
    @Autowired
    UserRolePermission userRolePermission;
    @Autowired
    LoginHandlerInterceptor loginHandlerInterceptor;
    @Autowired
    PermissionCode permissionCode;

    /*登录验证*/
    public UserInfo loginCheck(String userName, String password) {
        List<UserInfo> userInfoSqlList = userRolePermission.findUserInfoSql(userName);
        if (!userInfoSqlList.isEmpty()) {
            UserInfo userInfoSql = userInfoSqlList.get(0);
            if (userInfoSql.checkPassword(userInfoSql, password)) {
                return userInfoSql;
            }
        }
        return null;
    }

    /*解析权限对象,生成带等级的权限数组*/
    public PermissionCode getPCODE(UserInfo userInfo) {
        String code = userInfo.getPermissionCode();
        permissionCode.setAll(code);
        return permissionCode;
    }

    /*得到视图一级*/
    public List<Permission> getViewFr(HttpSession session) {
        UserInfo userInfo = (UserInfo) session.getAttribute(ConstantUtils.USER_SESSION_KEY);
        permissionCode.setAll(userInfo.getPermissionCode());
        return userRolePermission.getView(permissionCode.getLevel0());
    }

    /*得到二级视图*/
    public List<List<Permission>> getViewSe(HttpSession session) {
        UserInfo userInfo = (UserInfo) session.getAttribute(ConstantUtils.USER_SESSION_KEY);
        permissionCode.setAll(userInfo.getPermissionCode());
        List<List<Permission>> lists = new ArrayList<>();
        for (int i = 0; i < permissionCode.getLevel1().size(); i++) {
            List<Permission> list = userRolePermission.getView(permissionCode.getLevel1().get(i));
            Collections.addAll(lists, list);
        }
        return lists;
    }


}
