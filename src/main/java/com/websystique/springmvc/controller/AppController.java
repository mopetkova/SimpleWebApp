package com.websystique.springmvc.controller;


import com.websystique.springmvc.model.Employee;
import com.websystique.springmvc.model.MaritalStatus;
import com.websystique.springmvc.model.Skill;
import com.websystique.springmvc.service.EmployeeService;

import com.websystique.springmvc.service.MaritalStatusService;
import com.websystique.springmvc.service.SkillService;
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
import java.text.SimpleDateFormat;
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
            model.addAttribute("error", "User not exist");
            return "error";
        }

    }


    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String homePage(ModelMap model, @RequestParam(value = "filter", required = false) String filter) {
        String u = new Utils("AppController", "homePage").toString();

        model.addAttribute("filter", "");

        //TODO if utente in sessione? getEmployee()

        List<Employee> employees = service.findAllEmployees();


        if (isValid(filter)) {
            u = new Utils("AppController", "homePage - isVALID").toString();
            employees = search(filter);
        }

        model.addAttribute("employees", employees);

        return "allEmployees";
    }


    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registrationPost(@ModelAttribute("employee") Employee employee, BindingResult result, ModelMap model) {
        String u = new Utils("AppController", "registrationPOST").toString();

        Employee e = createEmployee(employee, result, model);
        service.saveEmployee(e);

        if (result.hasErrors()) {
            model.addAttribute("error", "Errore durante la registrazione! \n" + result.toString());
            return "error";
        } else {
            return homePage(model, null);
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

    @RequestMapping(value = "/update", method = RequestMethod.GET)
//    public String updateEmployeeGet(@ModelAttribute("Employee") Employee Employee, BindingResult result, ModelMap model, HttpSession session) {
    public String updateEmployeeGet(@ModelAttribute("employee") Employee employee,
                                    @RequestParam("id") int employeeId, ModelMap model,
                                    @ModelAttribute("maritalStatus") MaritalStatus maritalStatus
                                   ) {
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
    public String updateEmployeePost(@ModelAttribute("employee") Employee employee, BindingResult result, ModelMap model) {
        String u = new Utils("AppController", "updateEmployeePOST").toString();

        service.updateEmployee(employee);

        if (result.hasErrors()) {
            model.addAttribute("error", "Errore durante update! \n" + result.toString());
            return "error";
        } else {
            return homePage(model, null);
        }


    }

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public String test(@ModelAttribute("employee") Employee employee, BindingResult result, ModelMap model,
                       @ModelAttribute("maritalStatus") MaritalStatus maritalStatus) {
        String u = new Utils("AppController", "updateEmployeePOST").toString();

        service.updateEmployee(employee);

        model.addAttribute("employee", employee);
        model.addAttribute("model", model);
        model.addAttribute("result", result);
       // model.addAttribute("maritalStatus", maritalStatus);

        if (result.hasErrors()) {
            model.addAttribute("error", "Errore durante update! \n" + result.toString());
            return "error";
        } else {
            return"test";
        }



    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteEmployee(@ModelAttribute("employee") Employee employee, @RequestParam("id") int employeeId, BindingResult result, ModelMap model) {
        String u = new Utils("AppController", "deleteEmployeeGET").toString();

        Employee employeeById = service.getEmployeeById(employeeId);
        service.deleteEmployee(employeeById);

        if (result.hasErrors()) {
            model.addAttribute("error", "Errore durante delete! \n" + result.toString());
            return "error";
        } else {
            return homePage(model, null);
        }
    }

    public Employee createEmployee(@ModelAttribute("employee") Employee employee, BindingResult result, ModelMap model) {

        //TODO controllo sui campi, not empty

        System.out.println(" - - - employee from post");
        System.out.println(employee.toString());

        Employee e = new Employee();

        e.setName(employee.getName());
        e.setSurname(employee.getSurname());
        e.setCountry(employee.getCountry());
        if (employee.getBirthDate() != null) {
            e.setBirthDate(employee.getBirthDate());
        }
        if (employee.getMaritalStatus() != null) {
            e.setMaritalStatusId(employee.getMaritalStatus());
        } else {
            System.out.println("---marital status null");
        }


        e.setUsername(employee.getUsername());
        e.setPassword(employee.getPassword());

        return e;
    }

    public List<Employee> search(String search) {
        String u = new Utils("AppController", "search()").toString();
        String searchParam = "%" + search + "%"; //WHERE CustomerName LIKE 'a%'	Finds any values that start with "a"
        List<Employee> filterList = service.search(searchParam);
        return filterList;
    }




    /*
     * This method will be called on form submission, handling POST request
     * It also validates the Employee input
     */
//    @RequestMapping(method = RequestMethod.POST)
//    public String saveRegistration(@Valid EmployeeForm EmployeeForm, BindingResult result, ModelMap model){
//        String u = new Utils("AppController", "saveRegistration").toString();
//        System.out.println("Result "+result);
//        if(result.hasErrors()) {
//            u = new Utils("AppController", "errorRegistration").toString();
//            System.out.println("ErrorResult "+result);
//            return "enroll";
//        }
//
//        model.addAttribute("success", "Dear "+ EmployeeForm.getName()+" , your Registration completed successfully");
//        return "success";
//    }


//    @RequestMapping(value = "/login",method = RequestMethod.GET)
//    public String login(ModelMap model) {
//        String u = new Utils("AppController", "login").toString();
//        model.addAttribute("greeting", "Hello World from Spring 4 MVC");
//        return "welcome";
//    }
//    @RequestMapping(method = RequestMethod.GET)
//    public String index(ModelMap model) {
//        String u = new Utils("AppController", "index").toString();
//        model.addAttribute("greeting", "Hello World from Spring 4 MVC");
//        return "welcome";
//    }

//    @RequestMapping(value="/Employee", method=RequestMethod.GET)
//    public String savePerson(@ModelAttribute EmployeeForm EmployeeForm, Model model) {
//        String u = new Utils("AppController","savePerson").toString();
//        model.addAttribute("EmployeeForm", EmployeeForm);
//        return "Employee";
//    }
//
//    @RequestMapping(value="/", method=RequestMethod.POST)
//    public String savePersonPost(@Valid @ModelAttribute EmployeeForm personForm, BindingResult result, Model model) {
//        String u = new Utils("AppController","savePersonPost").toString();
//        if(result.hasErrors()) {
//            model.addAttribute("personForm", personForm);
//            return "Employee";
//        }
//        System.out.println(personForm.getName() + " " + personForm.getSurname());
//        return "redirect:/";
//    }

//    @RequestMapping(value="/", method=RequestMethod.GET)
//    public String updatePerson(@ModelAttribute EmployeeForm EmployeeForm, Model model) {
//        String u = new Utils("AppController","updatePerson").toString();
//        EmployeeForm.setName("Nome di default");
//        EmployeeForm.setSurname("Cognome di default");
//        model.addAttribute("EmployeeForm", EmployeeForm);
//        return "Employee";
//    }
}