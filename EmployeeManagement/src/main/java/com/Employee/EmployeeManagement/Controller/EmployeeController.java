package com.Employee.EmployeeManagement.Controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.Employee.EmployeeManagement.Model.EmployeeModel;
import com.Employee.EmployeeManagement.Service.EmployeeService;

@RestController
@RequestMapping("/EmployeeManagementApi/employee")
public class EmployeeController  {

	@Autowired
	EmployeeService empService;

	@GetMapping("getAll/list")
	public List<EmployeeModel> getAllEmployeeDetails() throws SQLException {

		return empService.getAll();

	}

	@GetMapping("getById/{id}")
	public EmployeeModel getById(@PathVariable int id) throws SQLException {
		return empService.getById(id);
	}

	@PostMapping("/add")
	public String insertDetails(@RequestBody EmployeeModel empMod) throws SQLException {
		return empService.insertDetails(empMod);

	}

	@PutMapping("/update")
	public String updateDetails(@RequestBody EmployeeModel empMod) throws SQLException {
		return empService.updateDetails(empMod);
	}
	
	@DeleteMapping("deleteById/{id}")
	public String deleteEmployee(@PathVariable int id ) throws SQLException {
		return empService.deleteEmployee(id);
		
	}

}
