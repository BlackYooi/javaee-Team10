package com.kindblack.team10.controller;

import com.kindblack.team10.POJO.Employee;
import com.kindblack.team10.Utils.Msg;
import com.kindblack.team10.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author black
 * @date 2019/12/15 - 20:38
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    /*添加员工*/
    @PostMapping
    public Msg addEmployee(@RequestBody Employee employee) {
        String uuid = employeeService.addEmployee(employee);
        if (uuid.length() == 32) {
            return Msg.success().add("uuid", uuid);
        } else {
            return Msg.fail().add("msg", "0");
        }
    }

    /*删除员工*/
    @DeleteMapping
    public Msg deleteEmployee(@RequestParam String id) {
        if (employeeService.deleteEmployee(id)) {
            return Msg.success().add("msg", "1");
        } else {
            return Msg.fail().add("msg", "0");
        }
    }

    /*员工的修改*/
    @PutMapping
    public Msg updateEmp(@RequestBody Employee employee) {
        if (employeeService.updateEmp(employee)) {
            return Msg.success().add("msg", "1");
        } else {
            return Msg.fail().add("msg", "0");
        }
    }

    @GetMapping
    public Msg getEmployee(@RequestParam int getPage) {
        Page<Employee> page = employeeService.getAllEmp(getPage);
        //return Msg.success().add("emps",page.getContent());
        return Msg.success().add("emps", page.getContent())
                .add("totalPage", page.getTotalPages())
                .add("totalEmps", page.getTotalElements());
    }

    /*员工的模糊查询*/
    @PostMapping("/lookup")
    public Msg getEmpByOptions(@RequestBody Employee employee) {
        List<Employee> list = employeeService.findByOptons(employee);
        return Msg.success().add("emps", list);
    }
}
