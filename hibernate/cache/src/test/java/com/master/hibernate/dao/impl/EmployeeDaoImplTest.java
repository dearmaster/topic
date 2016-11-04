package com.master.hibernate.dao.impl;

import com.master.hibernate.dao.EmployeeDao;
import com.master.hibernate.model.Employee;
import org.apache.log4j.Logger;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/cache-db.xml" })
@Transactional
@Rollback(false)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmployeeDaoImplTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	private static final Logger logger = Logger.getLogger(EmployeeDaoImplTest.class);
	
	@Autowired
	private EmployeeDao employeeDao;
	
	private static Employee employee;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		employee = new Employee("lc500", "Li", "Cheng", "男");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test2LoadAll() {
		Set<Employee> employees = employeeDao.loadAll();
		for(Employee emp : employees) {
			logger.info(emp);
		}
	}
	
	@Ignore
	@Test
	public void test1Save() {
		employeeDao.save(employee);
		logger.info(employee);
	}
	
	@Test
	public void test3Load() {
		Employee emp1 = employeeDao.load(Employee.class, new String(employee.getId()));
		logger.info(emp1);
		
		Employee emp2 = employeeDao.load(Employee.class, new String(employee.getId()));
		logger.info(emp2);
	}
	
	/**
	 * If the second cache is not configured, we can see one query from log for test4Load<br>
	 * On the contrary, If secondary cache is configured, no query can be seen for test4Load
	 */
	@Test
	public void test4Load() {
		Employee emp1 = employeeDao.load(Employee.class, new String(employee.getId()));
		logger.info(emp1);
		
		Employee emp2 = employeeDao.load(Employee.class, new String(employee.getId()));
		logger.info(emp2);
	}
	
	@Test
	public void test5LoadByLastName() {
		Set<Employee> employees1 = employeeDao.loadByLastName(employee.getLastName());
		for(Employee emp : employees1) {
			logger.info(emp);
		}
		
		Set<Employee> employees2 = employeeDao.loadByLastName(employee.getLastName());
		for(Employee emp : employees2) {
			logger.info(emp);
		}
	}
	
	@Test
	public void test6LoadByFirstName() {
		Set<Employee> employee1 = employeeDao.loadByFirstName(employee.getFirstName());
		for(Employee emp : employee1) {
			logger.info(emp);
		}
		
		Set<Employee> employee2 = employeeDao.loadByFirstName(employee.getFirstName());
		for(Employee emp : employee2) {
			logger.info(emp);
		}
	}

}
