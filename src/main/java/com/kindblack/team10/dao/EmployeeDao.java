package com.kindblack.team10.dao;

import com.kindblack.team10.POJO.Employee;
import com.kindblack.team10.Utils.OrgodicObject;
import com.kindblack.team10.dao.DaoInter.EmployeeDaoInter;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

/**
 * @author black
 * @date 2019/12/15 - 20:00
 */
@Component
public class EmployeeDao {

    @Autowired
    EmployeeDaoInter employeeDaoInter;
    @Autowired
    OrgodicObject orgodicObject;//遍历对象，还能拼接模糊查询sql

    /*增加员工*/
    public String addEmployee(Employee employee) {
        employee.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        employeeDaoInter.save(employee);
        return employee.getId();
    }

    /*删除id为XXX员工*/
    public boolean deleteEmployee(String id) {
        String sql = "delete from employee where id='" + id + "'";
        Configuration cfg = new Configuration().configure();
        Session session = cfg.buildSessionFactory().openSession();
        Transaction tx = null;
        tx = session.beginTransaction();

        Query query = session.createSQLQuery(sql);
        int i = query.executeUpdate();
        tx.commit();
        session.close();
        if (i > 0)
            return true;
        return false;
    }

    /*修改员工*/
    public boolean updateEmp(Employee employee) {
        String sql = "update employee set  id=id";
        Configuration cfg = new Configuration().configure();
        Session session = cfg.buildSessionFactory().openSession();
        Transaction tx = null;
        tx = session.beginTransaction();

        if (employee.getName() != null) {
            sql = sql + ",name='" + employee.getName() + "' ";
        }
        if (employee.getAge() != null) {
            sql = sql + ",age='" + employee.getAge() + "' ";
        }
        if (employee.getSex() != null) {
            sql = sql + ",sex='" + employee.getSex() + "' ";
        }
        if (employee.getDepartmentId() != null) {
            sql = sql + ",departmentId='" + employee.getDepartmentId() + "' ";
        }
        if (employee.getPositionId() != null) {
            sql = sql + ",positionId='" + employee.getPositionId() + "' ";
        }
        sql = sql + "  where id='" + employee.getId() + "' ";

        Query query = session.createSQLQuery(sql);
        int i = query.executeUpdate();

        tx.commit();
        session.close();
        if (i > 0)
            return true;
        return false;
    }

    public Page<Employee> getAllEmp(int getPage) {
        Sort sort = Sort.by(Sort.Direction.ASC, "name");
        Pageable pageable = PageRequest.of(getPage, 12, sort);
        Page<Employee> page = employeeDaoInter.findAll(pageable);
        return page;
    }

    /*根据id查询员工*/
    public List getEmpByID(String id) {
        String sql = "select * from employee where id='" + id + "'";
        Configuration cfg = new Configuration().configure();
        Session session = cfg.buildSessionFactory().openSession();
        Transaction tx = null;
        tx = session.beginTransaction();

        NativeQuery query = session.createNativeQuery(sql);
        List list = query.getResultList();

        tx.commit();
        session.close();
        return list;
    }

    public List<Employee> getEmpByOptions(Employee employee){
        Configuration cfg = new Configuration().configure();
        Session session = cfg.buildSessionFactory().openSession();
        Transaction tx = null;
        tx = session.beginTransaction();

        String getSql = orgodicObject.getSql("employee", employee);
        NativeQuery query = session.createNativeQuery(getSql.toString(), Employee.class);
        List<Employee> resultList = query.getResultList();

        tx.commit();
        session.close();

        return resultList;
    }



}
