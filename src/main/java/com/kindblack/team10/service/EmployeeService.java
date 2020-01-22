package com.kindblack.team10.service;

import com.kindblack.team10.POJO.Employee;
import com.kindblack.team10.dao.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;


/**
 * @author black
 * @date 2019/12/15 - 20:05
 */

@Service
@Transactional
public class EmployeeService {
    @Autowired
    private EmployeeDao employeeDao;

    public String addEmployee(Employee employee) {
        return employeeDao.addEmployee(employee);
    }

    public Boolean deleteEmployee(String id) {
        if (employeeDao.deleteEmployee(id)) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean updateEmp(Employee employee) {
        return employeeDao.updateEmp(employee);
    }

    public Page<Employee> getAllEmp(int getPage) {
        return employeeDao.getAllEmp(getPage);
    }

    public List getEmpByID(String id) {
        return employeeDao.getEmpByID(id);
    }

    /*多条件查询*/
    public List<Employee> findByOptons(Employee employee) {
        employee.toString();
        List<Employee> list = employeeDao.getEmpByOptions(employee);
        return list;
    }

}
