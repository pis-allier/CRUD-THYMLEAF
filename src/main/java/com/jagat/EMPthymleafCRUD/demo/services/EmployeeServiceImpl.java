package com.jagat.EMPthymleafCRUD.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.jagat.EMPthymleafCRUD.demo.model.Employee;
import com.jagat.EMPthymleafCRUD.demo.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public List<Employee> listOfAllEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public void saveEmployee(Employee employee) {
		// TODO Auto-generated method stub
		this.employeeRepository.save(employee);

	}

	// update Employee method
	@Override
	public Employee getEmpById(Long id) {
		// TODO Auto-generated method stub
		Optional<Employee> optional = employeeRepository.findById(id);
		Employee emp = null;
		if (optional.isPresent()) {
			emp = optional.get();
		} else {
			throw new RuntimeException("Employee not Present  " + id);
		}
		return emp;
	}

	//method for delete emp
	@Override
	public void deleteEmpById(Long id) {
		// TODO Auto-generated method stub
		this.employeeRepository.deleteById(id);
	}

	@Override
	public Page<Employee> findPaginated(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageable = PageRequest.of(pageNo - 1, pageSize);
		
		
		 return this.employeeRepository.findAll(pageable);
		
	}

}
