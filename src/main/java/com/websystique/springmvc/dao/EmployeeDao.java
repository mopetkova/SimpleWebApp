package com.websystique.springmvc.dao;

import java.util.List;

import com.websystique.springmvc.model.Employee;
import com.websystique.springmvc.model.Skill;

public interface EmployeeDao {


    Employee findById(int employeeId);

    void saveEmployee(Employee employee);

    List<Employee> findAllEmployees();

    Employee checkEmployee(String username, String password);

    void updateEmployee(Employee Employee);

    Employee getEmployeeById(int employeeId);

    void deleteEmployee(Employee employee);

    List<Employee> search(String search);


//    void deleteEmployeeBySsn(String ssn);
//    Employee findEmployeeBySsn(String ssn);
}
