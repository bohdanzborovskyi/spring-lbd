package com.zbodya;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.zbodya.entity.employee.Employee;
import com.zbodya.service.employee.EmployeeService;

@SpringBootApplication
public class SpringLbdApplication {	
	
	@Autowired
	EmployeeService employeeServ;
	
	public static long counter = 0;
	
	@PostConstruct
	public void post() 
	{
		employeeServ.findAll();
		employeeServ.save(new Employee(0,"Lick","Angel",434352192,"AP-oii2942","832-232-211"));
		System.out.println(employeeServ.findByName("Angel"));
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SpringLbdApplication.class, args);
	}


}
