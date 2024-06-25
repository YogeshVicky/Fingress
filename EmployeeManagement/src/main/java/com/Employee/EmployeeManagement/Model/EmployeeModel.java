package com.Employee.EmployeeManagement.Model;

import java.sql.Date;

public class EmployeeModel {

	private int EmployeeId;
	private String EmployeeName;
	private double EmployeeSalary;
	private Date Date_Of_Joining;

	public EmployeeModel(int employeeId, String employeeName, long employeeSalary, Date date_Of_Joining) {
		super();
		EmployeeId = employeeId;
		EmployeeName = employeeName;
		EmployeeSalary = employeeSalary;
		Date_Of_Joining = date_Of_Joining;
	}

	public EmployeeModel() {
		super();

	}

	public int getEmployeeId() {
		return EmployeeId;
	}

	public void setEmployeeId(int employeeId) {
		EmployeeId = employeeId;
	}

	public String getEmployeeName() {
		return EmployeeName;
	}

	public void setEmployeeName(String employeeName) {
		EmployeeName = employeeName;
	}

	public double getEmployeeSalary() {
		return EmployeeSalary;
	}

	public void setEmployeeSalary(double employeeSalary) {
		EmployeeSalary = employeeSalary;
	}

	public Date getDate_Of_Joining() {
		return Date_Of_Joining;
	}

	public void setDate_Of_Joining(Date date_Of_Joining) {
		Date_Of_Joining = date_Of_Joining;
	}

	@Override
	public String toString() {
		return "EmployeeModel [EmployeeId=" + EmployeeId + ", EmployeeName=" + EmployeeName + ", EmployeeSalary="
				+ EmployeeSalary + ", Date_Of_Joining=" + Date_Of_Joining + "]";
	}

}
