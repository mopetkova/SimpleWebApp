package com.websystique.springmvc.controller;


import com.websystique.springmvc.model.Employee;
import com.websystique.springmvc.model.MaritalStatus;
import com.websystique.springmvc.model.Skill;
import com.websystique.springmvc.service.EmployeeService;

import com.websystique.springmvc.service.MaritalStatusService;
import com.websystique.springmvc.service.SkillService;
import com.websystique.springmvc.utils.Notification;
import com.websystique.springmvc.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static com.websystique.springmvc.utils.Utils.isValid;

//otg.springframework ; spring-webmvc
@Controller
@RequestMapping("/")
public class AppController {
    String u = new Utils("AppController", "class").toString();

    @Autowired
    EmployeeService service;
    @Autowired
    SkillService skillService;
    @Autowired
    MaritalStatusService maritalStatusService;

    private List<Notification> notifications = new ArrayList<Notification>();

    @RequestMapping(method = RequestMethod.GET)
    public String welcome(ModelMap model) {
        String u = new Utils("AppController", "welcomeGET").toString();
        model.addAttribute("employee", new Employee());
        return "welcome";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String loginPost(@ModelAttribute("employee") Employee employee, ModelMap model) {
        String u = new Utils("AppController", "loginPost").toString();

        Employee checkEmployee = service.checkEmployee(employee.getUsername(), employee.getPassword());

        if (checkEmployee != null) {
            return homePage(model, null);
        } else {
            notifications.clear();
            notifications.add(new Notification("alert-danger", "User not found. Insert valid username and password."));
            model.addAttribute("notifications", notifications);
            return "welcome";
        }

    }


    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String homePage(ModelMap model, @RequestParam(value = "filter", required = false) String filter) {
        String u = new Utils("AppController", "homePage").toString();
        //TODO if utente in sessione? getEmployee()

        List<Employee> employees = service.findAllEmployees();
        model.addAttribute("filter", "");

        if (isValid(filter)) {
            employees = search(filter);
        }

        model.addAttribute("employees", employees);

       // model.addAttribute("blabla", employeeSkill(employees.get(0)));

        return "allEmployees";
    }


    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String updateEmployeeGet(@ModelAttribute("employee") @Valid Employee employee,
                                    @RequestParam("id") int employeeId, ModelMap model) {
        String u = new Utils("AppController", "updateEmployeeGET").toString();

        List<Skill> skills = skillService.allSkills();
        List<MaritalStatus> maritalStatusList = maritalStatusService.allMaritalStatus();
        Employee employeeById = service.getEmployeeById(employeeId);

        model.addAttribute("skills", skills);
        model.addAttribute("maritalStatusList", maritalStatusList);
        model.addAttribute("employee", employeeById);

        return "update";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateEmployeePost(@ModelAttribute("employee") @Valid Employee employee, BindingResult result, ModelMap model) {
        String u = new Utils("AppController", "updateEmployeePOST").toString();
//
//        Employee e = service.getEmployeeById(employeeId);
//
//        if(e == null){
//            notifications.clear();
//            notifications.add(new Notification("alert-danger", "User not found."));
//            model.addAttribute("notifications", notifications);
//            return "update";
//        }
//        else {
        service.updateEmployee(employee);

        if (result.hasErrors()) {
            notifications.clear();
            notifications.add(new Notification("alert-danger", "Error during the update."));
            model.addAttribute("notifications", notifications);
            return "update";
        } else {
            notifications.clear();
            notifications.add(new Notification("alert-success", "User updated successfully!"));
            model.addAttribute("notifications", notifications);
            return homePage(model, null);
        }


    }


    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registrationPost(@ModelAttribute("employee") Employee employee, BindingResult result, ModelMap model) {
        String u = new Utils("AppController", "registrationPOST").toString();

        if (checkValidEmployee(employee)) {

            service.saveEmployee(employee);

            if (result.hasErrors()) {
                notifications.clear();
                notifications.add(new Notification("alert-danger", "Error during registration."));
                model.addAttribute("notifications", notifications);
                model.addAttribute("error", "Errore durante la registrazione! \n" + result.toString());
                return "error";
            } else {
                notifications.clear();
                notifications.add(new Notification("alert-success", "User registered successfully!"));
                model.addAttribute("notifications", notifications);
                return homePage(model, null);
            }

        } else {
            model.addAttribute("notifications", notifications);
            return registrationGet(employee, model);
        }

    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registrationGet(@ModelAttribute("employee") Employee employee, ModelMap model) {
        String u = new Utils("AppController", "registrationGET").toString();

        List<Skill> skills = skillService.allSkills();
        List<MaritalStatus> maritalStatusList = maritalStatusService.allMaritalStatus();

        model.addAttribute("employee", new Employee());
        model.addAttribute("skills", skills);
        model.addAttribute("maritalStatusList", maritalStatusList);

        return "registration";
    }


    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteEmployee(@ModelAttribute("employee") Employee employee, @RequestParam("id") int employeeId, BindingResult result, ModelMap model) {
        String u = new Utils("AppController", "deleteEmployeeGET").toString();

        Employee employeeById = service.getEmployeeById(employeeId);
        service.deleteEmployee(employeeById);

        if (result.hasErrors()) {
            notifications.clear();
            notifications.add(new Notification("alert-danger", "Error during delete."));
            model.addAttribute("notifications", notifications);
            model.addAttribute("error", "Error durante delete! \n" + result.toString());
            return "error";
        } else {
            notifications.clear();
            notifications.add(new Notification("alert-success", "User deleted successfully!"));
            model.addAttribute("notifications", notifications);
            return homePage(model, null);
        }
    }


    public boolean checkValidEmployee(@ModelAttribute("employee") Employee employee) {
        String u = new Utils("AppController", "createEmployee").toString();

        boolean valid = false;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        notifications.clear();


        if (!isValid(employee.getName())) {
            notifications.add(new Notification("alert-danger", "Name required."));
        }
        if (!isValid(employee.getSurname())) {
            notifications.add(new Notification("alert-danger", "Surname required."));
        }
//        if (!isValid(df.format(employee.getBirthDate()))) {
//            notifications.add(new Notification("alert-danger", "Birth date required."));
//        }
        if (!isValid(employee.getUsername())) {
            notifications.add(new Notification("alert-danger", "Username required."));
        }
        if (!isValid(employee.getPassword())) {
            notifications.add(new Notification("alert-danger", "Password required."));
        } else {
            valid = true;
        }

        return valid;
    }

    public List<Employee> search(String search) {
        String u = new Utils("AppController", "search()").toString();
        String searchParam = "%" + search + "%"; //WHERE CustomerName LIKE 'a%'	Finds any values that start with "a"
        List<Employee> filterList = service.search(searchParam);
        return filterList;
    }

    public String employeeSkill(Employee employee) {
        String u = new Utils("AppController", "employeeSkill").toString();
        List<Skill> employeeSkill = employee.getSkills();
        System.out.println(employeeSkill);
        StringBuilder skill = new StringBuilder();
        for (int i = 0; i < employeeSkill.size(); i++) {

            skill.append(", ").append(employeeSkill.get(i).getName());
        }
        System.out.println(skill);
        return skill.toString();
    }


    @ModelAttribute("maritalStatus")
    public List<MaritalStatus> initializeMaritalStatus() {

        return maritalStatusService.allMaritalStatus();
    }

    @ModelAttribute("skill")
    public List<Skill> initializeSkill() {
        return skillService.allSkills();
    }

}
