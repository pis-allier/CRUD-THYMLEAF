package com.jagat.EMPthymleafCRUD.demo.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.jagat.EMPthymleafCRUD.demo.model.Employee;

public interface EmployeeService {

	List<Employee> listOfAllEmployees();

	void saveEmployee(Employee employee);

	Employee getEmpById(Long id);

	void deleteEmpById(Long id);

	// method for pagination
	Page<Employee> findPaginated(int pageNo, int pageSize);
}
