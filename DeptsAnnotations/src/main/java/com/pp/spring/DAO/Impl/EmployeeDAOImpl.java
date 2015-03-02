package com.pp.spring.DAO.Impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pp.spring.DAO.EmployeeDAO;
import com.pp.spring.model.Employee;

@Repository("employeeDAO")
public class EmployeeDAOImpl implements EmployeeDAO {

	private static final Logger logger = Logger.getLogger(EmployeeDAOImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addEmloyee(Employee emp) {
		Session session = sessionFactory.getCurrentSession();
		session.save(emp);
		logger.info("Employee was saved successfully. Employee details: " + emp);
	}

	@Override
	public void deleteEmployee(Employee emp) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(emp);
		logger.info("Employee was deleted successfully. Employee details: " + emp);
	}

	@Override
	public void updateEmployee(Employee emp) {
		Session session = sessionFactory.getCurrentSession();
		session.update(emp);
		logger.info("Employee was updated successfully. Employee details: " + emp);
	}

	@Override
	public Employee getEmployeeById(int id) {
		Session session = sessionFactory.getCurrentSession();
		Employee emp = (Employee) session.get(Employee.class, id);
		logger.info("Employee #" + id + " was loaded successfully. "
				  + "Employee details: " + emp);

		return emp;
	}
	
	@Override
	public void deleteEmployeeById(int id) {
		Session session = sessionFactory.getCurrentSession();
		Employee emp = (Employee) session.get(Employee.class, id);
		if(emp != null){
			session.delete(emp);
		}
		logger.info("Employee #" + id + " was deleted successfully. "
				  + "Employee details: " + emp);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> getAllEmployees() {
		Session session = sessionFactory.getCurrentSession();
		List<Employee> employeesList = session.createCriteria(Employee.class).list();
		logger.info("Employees list was loaded successfully. Employees list: " + employeesList);

		return employeesList;
	}

	@Override
	public Employee findByName(String name) {
		Employee emp = null;
		emp = (Employee) sessionFactory.getCurrentSession()
				.createQuery("from Employee where name=?")
				.setParameter(0, name).uniqueResult();
		
		return emp;
	}

	@Override
	public Employee findByEmail(String email) {
		Employee emp = null;
		emp = (Employee) sessionFactory.getCurrentSession()
				.createQuery("from Employee where email=?")
				.setParameter(0, email).uniqueResult();
		
		return emp;
	}

}
