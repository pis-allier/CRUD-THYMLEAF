package com.jagat.EMPthymleafCRUD.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.jagat.EMPthymleafCRUD.demo.model.Employee;
import com.jagat.EMPthymleafCRUD.demo.services.EmployeeService;

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	// method to display list of employees
	@GetMapping("/")
	public String viewHomePage(Model model) {
	 return findPaginated(1, model);  
	}
	
	//method to show employee form
	@GetMapping("/showEmpForm")
	public String showEmployeeForm(Model model) {
		//create model object to bind data to form data
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		return "new_emp";
	}
	//method to save emp details to database.
	@PostMapping("/saveEmployee")
	public String saveEmployee(@ModelAttribute("employee") Employee employee)  {
		employeeService.saveEmployee(employee);
		return "redirect:/";
	}
	
	//method to update employee Details
	@GetMapping("/showUpdateForm/{id}")
	public String showUpdateForm(@PathVariable (value= "id") long id, Model model) {
		Employee employee = employeeService.getEmpById(id);
		model.addAttribute("employee", employee);
		return "update_emp";
	}
	
	//method to delete emp by id
			@GetMapping("/deleteEmp/{id}")
			public String deleteEmp(@PathVariable (value="id") long id) {
				this.employeeService.deleteEmpById(id);
				return "redirect:/";
			}
			
			
			
			@GetMapping("/page/{pageNo}")
			public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model) {
			    int pageSize = 5;
			    Page < Employee > page = employeeService.findPaginated(pageNo, pageSize);
			    List< Employee > listEmployees = page.getContent();
			    model.addAttribute("currentPage", pageNo);
			    model.addAttribute("totalPages", page.getTotalPages());
			    model.addAttribute("totalItems", page.getTotalElements());
			    model.addAttribute("listEmployees", listEmployees);
			    return "index";
			}
}
