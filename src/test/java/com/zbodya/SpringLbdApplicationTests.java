package com.zbodya;


import org.springframework.boot.test.context.SpringBootTest;


import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.isA;
import static org.hamcrest.CoreMatchers.notNullValue;

import java.util.Map;
import java.util.Map.Entry;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.zbodya.entity.employee.Employee;
import com.zbodya.service.employee.EmployeeService;
import com.zbodya.service.employee.EmployeeServiceFirst;


@SpringBootTest
class SpringLbdApplicationTests 
{

	

	@Autowired
	EmployeeServiceFirst esf;
	
	@Test
	void contextLoads()
	{
		
	}
	
	@Test
	void checkNickName() 
	{
		assertThat(esf.getEmployeeNIckName("Jack", "Grealish").equals("PROJacGrePRO"));
	}
	
	@Test 
	void checkSavingEmployee() 
	{
		esf.save(new Employee(0,"Lick","Angel",434352192,"AP-oii2942","832-232-211"));
		Employee empl = esf.employees.get(1L);
		System.out.println(empl);		
		assertThat(empl.getName().equals("Lick"));
	}
	
	@Test
	void chceckFindingByNameAndSurname() 
	{
		String empl = esf.findByName("Angel");
		assertThat(empl.equals("ID: 0 Name: Lick Surname: Angel PESEL: 434352192 IDcard: AP-oii2942 number tel.: 832-232-211"));
	}

	

}
