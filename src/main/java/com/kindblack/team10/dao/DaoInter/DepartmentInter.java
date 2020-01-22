package com.kindblack.team10.dao.DaoInter;

import com.kindblack.team10.POJO.Department;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DepartmentInter extends JpaRepository<Department, String> {
}
