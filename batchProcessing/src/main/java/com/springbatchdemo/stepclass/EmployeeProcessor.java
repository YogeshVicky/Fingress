package com.springbatchdemo.stepclass;

import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmployeeProcessor implements ItemProcessor<List<Employee>, List<String>> {
	Log log = LogFactory.getLog(EmployeeProcessor.class);

	@Override
	public List<String> process(List<Employee> employee) throws Exception {

		List<String> empList = new ArrayList<String>();
		for (Employee data : employee) {
//			String row = data.getEmployeeId() + "," + data.getEmployeeName() + "," + data.getEmployeeSalary() + ","
//					+ data.getDate_Of_Joining() + "," + data.getIs_Sync() + "," + data.isStatus();
			String row = data.getEmployeeId() + "," + data.getEmployeeName() + "," + data.getIs_Sync();
			
			empList.add(row);
		}
		return empList;
	}

	@Bean
	public EmployeeProcessor processor() {
		return new EmployeeProcessor();

	}

}
