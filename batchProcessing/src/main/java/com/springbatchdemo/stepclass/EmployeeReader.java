package com.springbatchdemo.stepclass;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Component
public class EmployeeReader implements ItemReader<List<Employee>> {
	Log log = LogFactory.getLog(EmployeeReader.class);

	@Autowired
	DataSource dataSource;

	private ResultSet resultSet;
	private boolean jobState = false;
	String query = "Select Is_Sync from employee where Is_Sync ='No'";

	public List<Employee> read()
			throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

		if (!jobState) {
			List<Employee> emplist = new ArrayList<Employee>();
			try {
				Connection con = dataSource.getConnection();
				PreparedStatement preparedStatement = con.prepareStatement(query);
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					emplist.add(mapRow(resultSet));
				}
				jobState = true;
				return emplist;

			} catch (Exception e) {
				log.error("Invalid Request " + e);
			}
		}
		return null;
	}

	private Employee mapRow(ResultSet rs) throws SQLException {
		Employee employee = new Employee();
		employee.setIs_Sync(rs.getString("Is_Sync"));
		return employee;
	}

	public ExitStatus jobStopper(StepExecution stepExecution) {
		jobState = false;
		return stepExecution.getExitStatus();
	}
}
