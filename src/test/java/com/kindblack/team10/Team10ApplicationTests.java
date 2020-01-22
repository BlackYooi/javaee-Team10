package com.kindblack.team10;

import com.kindblack.team10.POJO.Permission.Permission;
import com.kindblack.team10.POJO.Recruit;
import com.kindblack.team10.Utils.PermissionCode;
import com.kindblack.team10.dao.EmployeeDao;
import com.kindblack.team10.dao.UserRolePermission;
import com.kindblack.team10.service.RecruitService;
import com.kindblack.team10.service.URPService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

import java.util.List;


@SpringBootTest
class Team10ApplicationTests {
    @Autowired
    EmployeeDao empployeeDao;
    @Autowired
    PermissionCode permissionCode;
    @Autowired
    UserRolePermission userRolePermission;
    @Autowired
    URPService urpService;
    @Autowired
    RecruitService recruitService;

    //密码测试
    @Test
    public void sda() {
        System.out.println(permissionCode.getNormalCode());
        System.out.println(DigestUtils.md5DigestAsHex("123456".getBytes()));
    }

    //测试获取权限编码
    @Test
    public void getUserInfo() {
        System.out.println(userRolePermission.findUserInfoSql("black").get(0).toString());
    }

    //测试获取视图（同时可以测试拿到的权限编码）
    @Test
    public void viewTes() {
        System.out.println(permissionCode.getSystemCode().toString());
        permissionCode.setAll(permissionCode.getSystemCode());
        List<Permission> view = userRolePermission.getView(permissionCode.getLevel0());
        System.out.println(view);
        System.out.println("end");
    }

    //级联查询
    @Test
    public void findRecruit() {
        //List<Recruit> allRec = recruitService.findAllRec();
        List<Recruit> allRec = recruitService.findAllRec();
        //System.out.println(allRec.get(0).getResumes().size());
        System.out.println("end");
    }


}
