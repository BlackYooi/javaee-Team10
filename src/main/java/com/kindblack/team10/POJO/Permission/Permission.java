package com.kindblack.team10.POJO.Permission;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author black
 * @date 2019/12/27 - 22:07
 */
@Entity(name = "permission")
public class Permission {

    /**
     * 分别对应
     * id
     * 请求的接口
     * 编码（3位纯数字字符串）
     * 类型（按钮还是网页链接）
     * 描述
     * 父接口编码
     * 级别
     * 在本层顺序
     */

    private String systemsUrl;
    private String urlCode;
    private String urlTtype;
    private String urlDescribe;
    private String parentCode;
    private String level;
    private String number;


    @Basic
    @Column(name = "systemurl", nullable = true, length = 32)
    public String getSystemsUrl() {
        return systemsUrl;
    }

    public void setSystemsUrl(String systemsUrl) {
        this.systemsUrl = systemsUrl;
    }

    @Id
    @Column(name = "urlcode", nullable = false, length = 3)
    public String getUrlCode() {
        return urlCode;
    }

    public void setUrlCode(String urlCode) {
        this.urlCode = urlCode;
    }

    @Basic
    @Column(name = "urltype", nullable = true, length = 50)
    public String getUrlTtype() {
        return urlTtype;
    }

    public void setUrlTtype(String urlTtype) {
        this.urlTtype = urlTtype;
    }

    @Basic
    @Column(name = "urldescribe", nullable = true, length = 50)
    public String getUrlDescribe() {
        return urlDescribe;
    }

    public void setUrlDescribe(String urlDescribe) {
        this.urlDescribe = urlDescribe;
    }

    @Basic
    @Column(name = "parentcode", nullable = true, length = 3)
    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    @Basic
    @Column(name = "level", nullable = false, length = 2)
    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Basic
    @Column(name = "number", nullable = true, length = 2)
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
