package com.pp.spring.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name = "employee")
//@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@DateTimeFormat(pattern="yyyy-MMMM-dd")
	@Temporal(TemporalType.DATE)
	@Column(name = "birth_date")
	private Date birthDate;

	@DateTimeFormat(pattern="yyyy-MMMM-dd")
	@Temporal(TemporalType.DATE)
	@Column(name = "hire_date")
	private Date hireDate;

	@Column(name = "address")
	private String address;
	
	@Column(name="email")
	private String email;
	
	@ManyToOne
	@JoinColumn(name = "dept_id")
	private Dept dept;
	
	@Column(name = "salary")
	private Integer salary;
	
	public Employee() {

	}

	public Employee(String name, Date birthDate, Date hireDate,
			String address, Dept dept, Integer salary) {
		this.name = name;
		this.birthDate = birthDate;
		this.hireDate = hireDate;
		this.address = address;
		this.dept = dept;
		this.salary = salary;
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

    @JsonSerialize(using=DateSerializer.class)
	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

    @JsonSerialize(using=DateSerializer.class)
	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Dept getDept() {
		return dept;
	}

	public void setDept(Dept dept) {
		this.dept = dept;
	}
	
	public Integer getSalary() {
		return salary;
	}

	public void setSalary(Integer salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Employee [id = " + id + ", name = " + name + ", birthDate = "
				+ birthDate + ", hireDate = " + hireDate + ", address = " + address
				+ ", dept = " + dept + ", salary = " + salary + "]";
	}
}
