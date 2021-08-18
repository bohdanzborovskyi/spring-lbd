package com.zbodya.service.employee;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.zbodya.entity.employee.Employee;

@Component
//@Qualifier("second")
@Profile("prod")
public class EmployeeServiceSecond implements EmployeeService 
{
	@Value("${namePrefix}")
	private String prefix;
	
	@Value("${nameSuffix}")
	private String suffix;

	@Override
	public List findAll() {
		System.out.println("Empployee Service Second");
		return null;
	}
	
	@Override
	public String getEmployeeNIckName(String firstName, String lastName)
	{
		return prefix + firstName.substring(0, 3) + lastName.substring(0, 3) + suffix;
	}

	@Override
	public String findByName(String name)
	{		
		return null;
	}

	@Override
	public void save(Employee employee) 
	{
		
	}

}
