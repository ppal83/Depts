package com.pp.spring.service;

import java.util.List;

import com.pp.spring.model.Dept;

public interface DeptService {

	void addDept(Dept dept);
	void deleteDept(Dept dept);
	void updateDept(Dept dept);
	Dept getDeptById(int id);
	void deleteDeptById(int id);
	List<Dept> getAllDepts();

}
