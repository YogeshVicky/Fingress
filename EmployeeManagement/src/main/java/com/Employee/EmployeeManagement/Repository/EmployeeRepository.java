package com.Employee.EmployeeManagement.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.management.RuntimeErrorException;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.Employee.EmployeeManagement.Model.EmployeeModel;

@Repository
public class EmployeeRepository {
	private static final Log log = LogFactory.getLog(EmployeeRepository.class);

	@Autowired
	DataSource dataSource;

	String query1 = "select*from employee";
	String query2 = "select*from employee where EmployeeId =?";
	String query3 = "insert into employee (EmployeeName,EmployeeSalary,Date_Of_Joining) values (?,?,?)";
	String query4 = "update employee set EmployeeName =?,EmployeeSalary=? where EmployeeId=?";
	String query5 = "delete from employee where EmployeeId =?";
	

	public List<EmployeeModel> getAll() {

		List<EmployeeModel> employeeAllDetails = new ArrayList<>();

		try {
			Connection con = dataSource.getConnection();
			PreparedStatement pst = con.prepareStatement(query1);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				employeeAllDetails.add(getDetails(rs));
			}
			con.close();
		}

		catch (Exception e) {
			log.error("Invalid Request",e);
			throw new RuntimeException();
		}
		return employeeAllDetails;
	}

//--------------------------------------------------getByID()----------------------------------------------------------//

	public EmployeeModel getById(int id) {

		try (Connection con = dataSource.getConnection(); PreparedStatement pst = con.prepareStatement(query2);) {

			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				return getDetails(rs);
			} else {
				 
				throw new SQLException();
			}
           
		}

		catch (Exception e) {
			log.error("Invalid Id " + id,e);
			throw new RuntimeException();
		}

	}

	public EmployeeModel getDetails(ResultSet rs) throws SQLException {
		EmployeeModel empMod = new EmployeeModel();

		empMod.setEmployeeId(rs.getInt("employeeId"));
		empMod.setEmployeeName(rs.getString("employeeName"));
		empMod.setEmployeeSalary(rs.getDouble("employeeSalary"));
		empMod.setDate_Of_Joining(rs.getDate("date_Of_Joining"));
		return empMod;

	}

//--------------------------------------------------insert()----------------------------------------------------------//

	public void insertDetails(EmployeeModel empMod) {

		try {
			Connection con = dataSource.getConnection();
			PreparedStatement pst = con.prepareStatement(query3);
			pst.setString(1, empMod.getEmployeeName());
			pst.setDouble(2, empMod.getEmployeeSalary());
			pst.setDate(3, empMod.getDate_Of_Joining());
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			log.error("Unable to insert",e);
			throw new RuntimeException();
		}
	}
//--------------------------------------------------update()----------------------------------------------------------//	

	public void updateEmployee(EmployeeModel empMod) {

		try {
			Connection con = dataSource.getConnection();
			PreparedStatement pst = con.prepareStatement(query4);
			pst.setString(1, empMod.getEmployeeName());
			pst.setDouble(2, empMod.getEmployeeSalary());
			pst.setInt(3, empMod.getEmployeeId());
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			log.error("Unable to update",e);
			throw new RuntimeException();
		}
	}

//--------------------------------------------------DeleteById()------------------------------------------------------//

	public void deleteById(int id) {

		try {
			Connection con = dataSource.getConnection();
			PreparedStatement pst = con.prepareStatement(query5);
			pst.setInt(1, id);
			pst.executeUpdate();
			con.close();
		}

		catch (Exception e) {
			log.error("Invalid Id "+ id,e);
			throw new RuntimeException();
		}
	}

}
