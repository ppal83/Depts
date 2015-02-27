package com.pp.spring.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.pp.spring.validate.UniqueDeptName;

@Entity
@Table(name = "dept")
public class Dept {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "name")
	@Size(min=1, max=30)
	@UniqueDeptName
	private String name;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "dept")
	@Fetch(FetchMode.SELECT)
	@Cascade(CascadeType.DETACH)
	@OrderBy("id")
	private Set<Employee> emps = new HashSet<>(0);

	public Dept() {

	}

	public Dept(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public Set<Employee> getEmps() {
		return emps;
	}

	public void setEmps(Set<Employee> emps) {
		this.emps = emps;
	}

	@Override
	public String toString() {
		return "Dept [id = " + id + ", name = " + name + "]";
	}
}
