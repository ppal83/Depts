package com.pp.spring.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import com.pp.spring.DAO.DeptDAO;
import com.pp.spring.model.Dept;
import com.pp.spring.service.DeptService;

@Service("deptService")
public class DeptServiceImpl implements DeptService {
	
	@Autowired(required=true)
	@Qualifier(value="deptDAO")
	private DeptDAO deptDAO;
	
	 @Autowired
	    private Validator validator;

	@Override
	@Transactional
	public void addDept(Dept dept) {
		deptDAO.addDept(dept);
	}

	@Override
	@Transactional
	public void deleteDept(Dept dept) {
		deptDAO.deleteDept(dept);
	}

	@Override
	@Transactional
	public void updateDept(Dept dept) {
		deptDAO.updateDept(dept);
	}

	@Override
	@Transactional
	public Dept getDeptById(int id) {
		return deptDAO.getDeptById(id);
	}

	@Override
	@Transactional
	public void deleteDeptById(int id) {
		deptDAO.deleteDeptById(id);
	}

	@Override
	@Transactional
	public List<Dept> getAllDepts() {
		return deptDAO.getAllDepts();
	}

	@Override
	@Transactional
	public Dept findByName(String name) {
		return deptDAO.findByName(name);
	}

}
