package com.kindblack.team10.dao.DaoInter;

import com.kindblack.team10.POJO.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;


/**
 * @author black
 * @date 2019/12/15 - 20:38
 */
public interface EmployeeDaoInter extends JpaRepository<Employee, String> {

    @Transactional
    void deleteByDepartmentId(String id);
    @Transactional
    void deleteByPositionId(String id);
}
