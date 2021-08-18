package com.zbodya.service.employee;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.logging.log4j.Logger;
import org.apache.el.stream.Stream;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.zbodya.SpringLbdApplication;
import com.zbodya.entity.employee.Employee;

@Component
//@Qualifier("first")
//@Primary
@Profile("dev")
public class EmployeeServiceFirst implements EmployeeService 
{
	
	Logger LOG =    LogManager.getLogger(EmployeeServiceFirst.class);
	
	@Value("${namePrefix}")
	private String prefix;
	
	@Value("${nameSuffix}")
	private String suffix;

	@Override
	public List findAll() 
	{
		System.out.println("Employee Service First");
		return null;
	}

	@Override
	public String getEmployeeNIckName(String firstName, String lastName)
	{
		LOG.info("First Name: " + firstName + " Last Name: " + lastName);
		LOG.info("Result nickName: " + prefix + firstName.substring(0, 3) + lastName.substring(0, 3) + suffix);
		return prefix + firstName.substring(0, 3) + lastName.substring(0, 3) + suffix;		
	}

	@Override
	public String findByName(String name) 
	{
		Optional<Map.Entry<Long,Employee>>employee = Optional.ofNullable((employees.entrySet().stream().
				filter(e -> e.getValue().getName().equals(name) || e.getValue().getSurname().equals(name)).findFirst().orElse(null)));
		if(employee.isPresent())
			return employee.get().getValue().toString();
		else 
			return "Nothing found!";
	}

	@Override
	public void save(Employee employee) 
	{
		LOG.info("Counter: " + ++SpringLbdApplication.counter);
		EmployeeService.employees.put(SpringLbdApplication.counter, employee);		
	}
	
	

}
