package com.java.main;

import com.java.domian.Employee;
import com.java.service.NameListService;
import com.java.service.TeamException;

public class TeamMainTest {

	public static void main(String[] args) {
		NameListService service=new NameListService();
		Employee []employees=service.getAllEmployees();
		for(int i=0;i<employees.length;i++) {
			System.out.println(employees[i]);
		}
		try {
			Employee e=service.getEmployee(16);
			System.out.println(e);
			
		} catch (TeamException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
	
}











