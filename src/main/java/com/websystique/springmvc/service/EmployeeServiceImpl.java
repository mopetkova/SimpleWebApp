package com.websystique.springmvc.service;

import com.websystique.springmvc.dao.EmployeeDao;
import com.websystique.springmvc.model.Employee;
import com.websystique.springmvc.model.Skill;
import com.websystique.springmvc.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("EmployeeService")
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
    String u = new Utils("EmployeeService Impl", "class").toString();

    @Autowired
    private EmployeeDao dao;

    public List<Employee> findAllEmployees() {
        return dao.findAllEmployees();
    }


    public Employee checkEmployee(String username, String password) {
        return dao.checkEmployee(username, password);
    }

    public void saveEmployee(Employee employee) {
        dao.saveEmployee(employee);
    }

    public void updateEmployee(Employee employee) {
        dao.updateEmployee(employee);
    }

    public Employee getEmployeeById(int employeeId) {
        return dao.getEmployeeById(employeeId);
    }

    public void deleteEmployee(Employee employee) {
        dao.deleteEmployee(employee);
    }

    public List<Employee> search(String search){
        String u = new Utils("EmployeeServiceImpl", "search").toString();
        return dao.search(search);
    }

}
