package com.springbatchdemo.stepclass;

import java.sql.Date;

public class Employee {
	private int EmployeeId;
	private String EmployeeName;
	private double EmployeeSalary;
	private Date Date_Of_Joining;
	private String Is_Sync;
	private boolean Status;

	public Employee(int employeeId, String employeeName, double employeeSalary, Date date_Of_Joining, String is_Sync,
			boolean status) {
		super();
		EmployeeId = employeeId;
		EmployeeName = employeeName;
		EmployeeSalary = employeeSalary;
		Date_Of_Joining = date_Of_Joining;
		Is_Sync = is_Sync;
		Status = status;
	}

	public Employee() {
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

	public String getIs_Sync() {
		return Is_Sync;
	}

	public void setIs_Sync(String is_Sync) {
		Is_Sync = is_Sync;
	}

	public boolean isStatus() {
		return Status;
	}

	public void setStatus(boolean status) {
		Status = status;
	}

	@Override
	public String toString() {
		return "Employee [EmployeeId=" + EmployeeId + ", EmployeeName=" + EmployeeName + ", EmployeeSalary="
				+ EmployeeSalary + ", Date_Of_Joining=" + Date_Of_Joining + ", Is_Sync=" + Is_Sync + ", Status="
				+ Status + "]";
	}

}
