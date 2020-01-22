package com.kindblack.team10.POJO;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @author black
 * @date 2019/12/16 - 13:15
 */
@Entity
public class Department {
    private String id;
    private String departmentName;
    private String leader;

    @Id
    @Column(name = "ID", nullable = false, length = 255)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "Departmentname", nullable = true, length = 50)
    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    @Basic
    @Column(name = "Leader", nullable = true, length = 50)
    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id='" + id + '\'' +
                ", departmentName='" + departmentName + '\'' +
                ", leader='" + leader + '\'' +
                '}';
    }
}
