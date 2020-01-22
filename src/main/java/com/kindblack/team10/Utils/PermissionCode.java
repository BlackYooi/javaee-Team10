package com.kindblack.team10.Utils;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author black
 * @date 2019/12/29 - 10:31
 * 编码原则：不同级用-隔开，同级不同父用~隔开，同级各个元素用大写字母隔开
 */

@Component
public class PermissionCode {

    /*用户执行操作时权限认证到这一步即可，主要访问level2*/
    private List<String> level0 = new ArrayList<String>();
    private List<List<String>> level1 = new ArrayList<>();

    /*解码*/
    public void setAll(String code) {
        level0.clear();
        level1.clear();
        String[] levelGroupString = code.split("-");
        /*一级视图*/
        Collections.addAll(level0, levelGroupString[0].split("A"));
        /*二级视图*/
        String[] group1 = levelGroupString[1].split("~");//去掉~的分组
        for (int i = 0; i < group1.length; i++) {
            List<String> listI = new ArrayList<String>();
            String[] groupI = group1[i].split("B");
            Collections.addAll(listI, groupI);
            Collections.addAll(level1, listI);
        }
    }

    /*管理员权限*/
    private String code1
            = "100A200-"
            + "110B120B130~210";
    /*用户权限*/
    private String code2
            = "200-"
            + "210B220B230";//普通用户没有put功能

    public String getSystemCode() {
        return code1;
    }

    public String getNormalCode() {
        return code2;
    }

    public List<String> getLevel0() {
        return level0;
    }

    public List<List<String>> getLevel1() {
        return level1;
    }
}
