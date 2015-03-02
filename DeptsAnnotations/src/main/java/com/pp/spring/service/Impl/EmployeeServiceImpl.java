package com.pp.spring.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pp.spring.DAO.EmployeeDAO;
import com.pp.spring.model.Employee;
import com.pp.spring.service.EmployeeService;

@Service("emplService")
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired(required=true)
	@Qualifier(value="employeeDAO")
	private EmployeeDAO employeeDAO;

	@Override
	@Transactional
	public void addEmloyee(Employee emp) {
		employeeDAO.addEmloyee(emp);
	}

	@Override
	@Transactional
	public void deleteEmployee(Employee emp) {
		employeeDAO.deleteEmployee(emp);
	}

	@Override
	@Transactional
	public void updateEmployee(Employee emp) {
		employeeDAO.updateEmployee(emp);
	}

	@Override
	@Transactional
	public Employee getEmployeeById(int id) {
		return employeeDAO.getEmployeeById(id);
	}

	@Override
	@Transactional
	public void deleteEmployeeById(int id) {
		employeeDAO.deleteEmployeeById(id);
	}

	@Override
	@Transactional
	public List<Employee> getAllEmployees() {
		return employeeDAO.getAllEmployees();
	}

	@Override
	@Transactional
	public Employee findByName(String name) {
		return employeeDAO.findByName(name);
	}

	@Override
	@Transactional
	public Employee findByEmail(String email) {
		return employeeDAO.findByEmail(email);
	}

}
