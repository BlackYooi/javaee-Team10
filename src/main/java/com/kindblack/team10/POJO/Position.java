package com.kindblack.team10.POJO;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

/**
 * @author black
 * @date 2019/12/16 - 16:24
 */
@Entity
public class Position {
    private String id;
    private String positionName;
    private int salary;

    @Id
    @Column(name = "ID", nullable = false, length = 255)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "positionname", nullable = true, length = 50)
    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    @Basic
    @Column(name = "salary", nullable = true, length = 50)
    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position)) return false;
        Position position = (Position) o;
        return getId().equals(position.getId()) &&
                Objects.equals(getPositionName(), position.getPositionName()) &&
                Objects.equals(getSalary(), position.getSalary());
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public String toString() {
        return "Position{" +
                "id='" + id + '\'' +
                ", positionName='" + positionName + '\'' +
                ", salary=" + salary +
                '}';
    }
}
