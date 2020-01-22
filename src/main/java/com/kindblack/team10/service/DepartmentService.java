package com.kindblack.team10.service;

import com.kindblack.team10.POJO.Department;
import com.kindblack.team10.dao.DaoInter.EmployeeDaoInter;
import com.kindblack.team10.dao.DepartmentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author black
 * @date 2019/12/16 - 12:16
 */
@Service
public class DepartmentService {
    @Autowired
    DepartmentDao departmentDao;
    @Autowired
    EmployeeDaoInter employeeDaoInter;

    public String addDepart(Department department) {
        return departmentDao.addDepart(department);
    }

    public Boolean deleteDepartment(String id) {
        employeeDaoInter.deleteByDepartmentId(id);
        return departmentDao.deleteDepartment(id);
    }

    public boolean updateEmp(Department department) {
        return departmentDao.updateEmp(department);
    }

    public Page<Department> getAll(int getPage) {
        return departmentDao.getAll(getPage);
    }

    public List<Department> getAll() {
        return departmentDao.getAll();
    }

    public Optional<Department> getById(String id) {
        return departmentDao.getById(id);
    }
}
