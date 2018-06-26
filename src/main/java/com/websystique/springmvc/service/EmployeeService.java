package com.websystique.springmvc.service;

import com.websystique.springmvc.model.Employee;
import com.websystique.springmvc.model.Skill;
import com.websystique.springmvc.utils.Utils;

import java.util.List;

public interface EmployeeService {
    String u = new Utils("EmployeeService Interface", "class").toString();

    List<Employee> findAllEmployees();

    Employee checkEmployee(String a, String b);

    void saveEmployee(Employee employee);

    void updateEmployee(Employee employee);

    Employee getEmployeeById(int employeeId);

    void deleteEmployee(Employee employee);

    List<Employee> search(String search);
}
