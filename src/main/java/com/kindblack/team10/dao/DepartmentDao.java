package com.kindblack.team10.dao;

import com.kindblack.team10.POJO.Department;
import com.kindblack.team10.dao.DaoInter.DepartmentInter;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


/**
 * @author black
 * @date 2019/12/16 - 13:15
 */
@Component
public class DepartmentDao {
    @Autowired
    DepartmentInter departmentInter;

    public String addDepart(Department department) {
        Configuration cfg = new Configuration().configure();
        Session session = cfg.buildSessionFactory().openSession();
        Transaction tx = null;
        tx = session.beginTransaction();
        department.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        String sql = "insert into Department values ('"
                + department.getId()
                + "','"
                + department.getDepartmentName()
                + "','" + department.getLeader()
                + "')";
        Query query = session.createSQLQuery(sql);
        int i = query.executeUpdate();
        tx.commit();
        session.close();
        if (i > 0)
            return department.getId();
        return "";
    }

    /*删除部门*/
    public Boolean deleteDepartment(String id) {
        String sql = "delete from Department where id='" + id + "'";
        Configuration cfg = new Configuration().configure();
        Session session = cfg.buildSessionFactory().openSession();
        Transaction tx = null;
        tx = session.beginTransaction();

        Query query = session.createSQLQuery(sql);
        int i = query.executeUpdate();

        tx.commit();
        session.close();
        if (i > 0) {
            return true;
        } else {
            return false;
        }
    }

    /*修改部门*/
    public boolean updateEmp(Department department) {
        String sql = "update Department set  id=id";
        Configuration cfg = new Configuration().configure();
        Session session = cfg.buildSessionFactory().openSession();
        Transaction tx = null;
        tx = session.beginTransaction();

        if (department.getDepartmentName() != null) {
            sql = sql + ",Departmentname='" + department.getDepartmentName() + "' ";
        }
        if (department.getLeader() != null) {
            sql = sql + ",leader='" + department.getLeader() + "' ";
        }
        sql = sql + "  where id='" + department.getId() + "' ";

        Query query = session.createSQLQuery(sql);
        int i = query.executeUpdate();

        tx.commit();
        session.close();
        if (i > 0)
            return true;
        return false;
    }

    /*分页查询所有部门*/
    public Page<Department> getAll(int getPage) {
        Sort sort = Sort.by(Sort.Direction.ASC, "departmentName");
        //因为没做分页了，所以分页查询设个100个
        Pageable pageable = PageRequest.of(getPage, 100, sort);
        Page<Department> page = departmentInter.findAll(pageable);
        return page;
    }

    /*查询所有部门*/
    public List<Department> getAll() {
        Sort sort = Sort.by(Sort.Direction.ASC, "departmentName");
        List<Department> list = departmentInter.findAll(sort);
        return list;
    }

    /*根据id查询部门*/
    public Optional<Department> getById(String id) {
        Optional<Department> optional = departmentInter.findById(id);
        return optional;
    }

}
