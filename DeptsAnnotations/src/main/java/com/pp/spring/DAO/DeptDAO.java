package com.pp.spring.DAO;

import java.util.List;

import com.pp.spring.model.Dept;

public interface DeptDAO {
	
	void addDept(Dept dept);
	void deleteDept(Dept dept);
	void updateDept(Dept dept);
	Dept getDeptById(int id);
	Dept findByName(String name);
	void deleteDeptById(int id);
	List<Dept> getAllDepts();
	
}
