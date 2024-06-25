package com.fingress.fingressFileReading.router;

import javax.sql.DataSource;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.stereotype.Component;

import com.fingress.fingressFileReading.transformer.TransformerFileToJdbc;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Component
class RouterFileToJdbc {
	public static void main(String[] args) throws Exception {

		@SuppressWarnings("resource")
		CamelContext context = new DefaultCamelContext();
		HikariConfig config = new HikariConfig();
		config.setDriverClassName("com.mysql.cj.jdbc.Driver");
		config.setUsername("root");
		config.setPassword("root");
		config.setJdbcUrl("jdbc:mysql://localhost:3307/employee");

		context.addRoutes(new RouteBuilder() {

			@Override
			public void configure() throws Exception {
				
				String query = "insert into employee (EmployeeId,EmployeeName,EmployeeSalary,Date_Of_Joining) values ('${body[EmployeeId]}','${body[EmployeeName]}','${body[EmployeeSalary]}','${body[Date_Of_Joining]}')";
				DataSource dataSource = new HikariDataSource(config);
				bindToRegistry("mydataSource", dataSource);
				from("file:file/Input?noop=true").process(new TransformerFileToJdbc()).split(body())
						.setBody(simple(query)).to("jdbc:mydataSource").log("Sucess").end();

			}
		});
		context.start();
		Thread.sleep(1000);
		context.stop();

	}

}

/*
 * @Override public void configure() throws Exception { HikariConfig config =
 * new HikariConfig(); config.setDriverClassName("com.mysql.cj.jdbc.Driver");
 * config.setUsername("root"); config.setPassword("root");
 * config.setJdbcUrl("jdbc:mysql://localhost:3307/employee");
 * 
 * DataSource dataSource = new HikariDataSource(config);
 * bindToRegistry("mydataSource", dataSource); String query =
 * "insert into employee (EmployeeId,EmployeeName,EmployeeSalary,Date_Of_Joining) values ('${body[EmployeeId]}','${body[EmployeeName]}','${body[EmployeeSalary]}','${body[Date_Of_Joining]}')"
 * ;
 * 
 * from("file:file/Output?noop=true").process(new
 * TransformerFileToJdbc()).split(body()).setBody(simple(query))
 * .to("jdbc:mydataSource").log("Sucess").end(); }
 */