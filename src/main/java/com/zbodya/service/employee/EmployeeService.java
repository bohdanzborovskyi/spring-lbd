package com.zbodya.service.employee;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zbodya.entity.employee.Employee;



public interface EmployeeService 
{
	public static long counter = 0;
	public static final Map<Long, Employee> employees = new HashMap<Long, Employee>();
	List findAll();
	String getEmployeeNIckName(String firstName, String lastName);
	String findByName(String name);
	void save(Employee employee);
	
}
