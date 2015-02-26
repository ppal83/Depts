package com.pp.spring.DAO.Impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pp.spring.DAO.DeptDAO;
import com.pp.spring.model.Dept;

@Repository
public class DeptDAOImpl implements DeptDAO {

	private static final Logger logger = Logger.getLogger(DeptDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addDept(Dept dept) {
		Session session = sessionFactory.getCurrentSession();
		session.save(dept);
		logger.info("Dept was saved successfully. Dept details: " + dept);
	}

	@Override
	public void deleteDept(Dept dept) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(dept);
		logger.info("Dept was deleted successfully. Dept details: " + dept);
	}

	@Override
	public void updateDept(Dept dept) {
		Session session = sessionFactory.getCurrentSession();
		session.update(dept);
		logger.info("Dept was updated successfully. Dept details: " + dept);
	}

	@Override
	public Dept getDeptById(int id) {
		Session session = sessionFactory.getCurrentSession();
		Dept dept = (Dept) session.get(Dept.class, id);
		logger.info("Dept #" + id + " was loaded successfully. "
				  + "Dept details: " + dept);

		return dept;
	}

	@Override
	public void deleteDeptById(int id) {
		Session session = sessionFactory.getCurrentSession();
		Dept dept = (Dept) session.get(Dept.class, id);
		if(dept != null){
			session.delete(dept);
		}
		logger.info("Dept #" + id + " was deleted successfully. "
				  + "Dept details: " + dept);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Dept> getAllDepts() {
		Session session = sessionFactory.getCurrentSession();
		List<Dept> deptsList = session.createCriteria(Dept.class).list();
		logger.info("Depts list was loaded successfully. Depts list: " + deptsList);

		return deptsList;
	}

}
