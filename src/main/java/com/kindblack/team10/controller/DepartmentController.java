package com.kindblack.team10.controller;

import com.kindblack.team10.POJO.Department;
import com.kindblack.team10.POJO.Employee;
import com.kindblack.team10.Utils.Msg;
import com.kindblack.team10.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author black
 * @date 2019/12/16 - 12:15
 */
@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    /*添加部门*/
    @PostMapping
    public Msg addDepart(@RequestBody Department department) {
        String uuid = departmentService.addDepart(department);
        if (uuid.length() > 0) {
            return Msg.success().add("uuid", uuid);
        }
        return Msg.fail().add("msg", "0");
    }

    /*删除部门*/
    @DeleteMapping
    public Msg deleteDepartment(@RequestParam String id) {
        if (departmentService.deleteDepartment(id) == false) {
            return Msg.fail().add("msg", "0");
        }
        return Msg.success().add("msg", "1");
    }

    /*修改部门*/
    @PutMapping
    public Msg updateEmp(@RequestBody Department department) {
        if (departmentService.updateEmp(department)) {
            return Msg.success().add("msg", "1");
        }
        return Msg.fail().add("msg", "0");
    }

    /*分页查询所有部门*/
    @GetMapping
    public Msg getAll(@RequestParam int getPage) {
        Page<Department> page = departmentService.getAll(getPage);
        return Msg.success().add("departs", page.getContent())
                .add("totalPage", page.getTotalPages())
                .add("totalDeparts", page.getTotalElements());
    }

    /*查询所有部门*/
    @GetMapping("/all")
    public Msg getAll() {
        List<Department> list = departmentService.getAll();
        return Msg.success().add("departs", list);
    }

}
