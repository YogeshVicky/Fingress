package com.fingress.fingressActivemq.router;

import javax.sql.DataSource;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.stereotype.Component;

import com.fingress.fingressActivemq.transformer.TransformerActivemqToJdbc;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Component
public class RouterActivemqToJdbc {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		CamelContext context = new DefaultCamelContext();
		HikariConfig config = new HikariConfig();
		config.setDriverClassName("com.mysql.cj.jdbc.Driver");
		config.setUsername("root");
		config.setPassword("root");
		config.setJdbcUrl("jdbc:mysql://localhost:3307/employee");
				
		try {
			context.addRoutes(new RouteBuilder() {

				@Override
				public void configure() throws Exception {
					String query = "insert into employee (EmployeeId,EmployeeName,EmployeeSalary,Date_Of_Joining) values ('${body[EmployeeId]}','${body[EmployeeName]}','${body[EmployeeSalary]}','${body[Date_Of_Joining]}')";
					DataSource dataSource = new HikariDataSource(config);
					bindToRegistry("mydataSource", dataSource);
					from("activemq:queue:Activemq1").process(new TransformerActivemqToJdbc()).split(body())
							.setBody(simple(query)).to("jdbc:mydataSource")
							.log("Sucess inserted from Activemq to Database").end();

				}
			});
			context.start();
			Thread.sleep(1000);
			context.stop();

		} catch (Exception e) {

		}
	}

}
