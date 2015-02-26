package com.pp.spring.service;

import java.util.List;

import com.pp.spring.model.Employee;

public interface EmployeeService {

	void addEmloyee(Employee emp);
	void deleteEmployee(Employee emp);
	void updateEmployee(Employee emp);
	Employee getEmployeeById(int id);
	void deleteEmployeeById(int id);
	List<Employee> getAllEmployees();
	
}
