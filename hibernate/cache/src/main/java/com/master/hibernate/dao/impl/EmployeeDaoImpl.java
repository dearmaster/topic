package com.master.hibernate.dao.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.master.hibernate.dao.AbstractBaseDaoSupport;
import com.master.hibernate.dao.EmployeeDao;
import com.master.hibernate.model.Employee;

@Repository("employeeDao")
public class EmployeeDaoImpl extends AbstractBaseDaoSupport<Employee> implements EmployeeDao {
	
	private static final String HQL_LOAD_BY_LAST_NAME = "from Employee where lastName = :lastName";

	@Override
	public Set<Employee> loadAll() {
		return super.loadAll(Employee.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<Employee> loadByLastName(String lastName) {
		Session session = super.getSession();
		Query query = session.createQuery(HQL_LOAD_BY_LAST_NAME);
		
		query.setCacheable(true);
		query.setCacheRegion("org.hibernate.cache.internal.StandardQueryCache");
		
		List<Employee> list = query.setString("lastName", lastName).list();
		return new HashSet<Employee>(list);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<Employee> loadByFirstName(String firstName) {
		Session session = super.getSession();
		Criteria criteria = session.createCriteria(Employee.class);
		List<Employee> list = criteria.add(Restrictions.eq("firstName", firstName)).list();
		
		return new HashSet<Employee>(list);
	}
	
}