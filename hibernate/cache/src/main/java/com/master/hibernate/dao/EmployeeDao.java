package com.master.hibernate.dao;

import java.util.Set;

import com.master.hibernate.model.Employee;

public interface EmployeeDao extends BaseDaoInterface<Employee> {
	
	Set<Employee> loadByLastName(String lastName);
	
}