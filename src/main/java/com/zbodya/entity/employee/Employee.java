package com.zbodya.entity.employee;


public class Employee 
{
	
	private int id;
	private String name;
	private String surname;
	private int PESEL;
	private String IDcard;
	private String numberTel;
	
	
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
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public int getPESEL() {
		return PESEL;
	}
	public void setPESEL(int PESEL) {
		this.PESEL = PESEL;
	}
	public String getIDcard() {
		return IDcard;
	}
	public void setIDcard(String iDcard) {
		IDcard = iDcard;
	}
	public String getNumberTel() {
		return numberTel;
	}
	public void setNumberTel(String numberTel) {
		this.numberTel = numberTel;
	}
	public Employee(int id, String name, String surname, int PESEL, String iDcard, String numberTel) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.PESEL = PESEL;
		IDcard = iDcard;
		this.numberTel = numberTel;
	}
	public Employee() {
		super();
	}
	
	public String toString() 
	{
		return "ID: " + this.id + " Name: " + this.name + " Surname: " 
				+  this.surname + " PESEL: " + this.PESEL + " IDcard: " + this.IDcard + " number tel.: " + this.numberTel;
	}

}
