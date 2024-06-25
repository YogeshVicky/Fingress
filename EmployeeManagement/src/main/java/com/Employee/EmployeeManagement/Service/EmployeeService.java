package com.Employee.EmployeeManagement.Service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.Employee.EmployeeManagement.Model.EmployeeModel;
import com.Employee.EmployeeManagement.Repository.EmployeeRepository;

@Service

public class EmployeeService {

	@Autowired
	EmployeeRepository emprepo;

	public List<EmployeeModel> getAll() throws SQLException {
		return emprepo.getAll();
	}

	public EmployeeModel getById(int id) throws SQLException {
		return emprepo.getById(id);
	}

	public String insertDetails(EmployeeModel empMod) throws SQLException {
		emprepo.insertDetails(empMod);
		return "Inserted Successfuly";
	}

	public String updateDetails(EmployeeModel empMod) throws SQLException {
		emprepo.updateEmployee(empMod);
		return "Updated Successfuly";
	}
	
	public String deleteEmployee(int id) throws SQLException {
		emprepo.deleteById(id);
		return "Deleted Successfuly";
	}
}
