package com.fingress.fingressFileReading.router;

import javax.sql.DataSource;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.stereotype.Component;

import com.fingress.fingressFileReading.transformer.TransformerJdbcToFile;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Component
public class RouterJdbcToFile {

	public static void main(String[] args) throws Exception {
		@SuppressWarnings("resource")
		CamelContext context = new DefaultCamelContext();
		context.addRoutes(new RouteBuilder() {

			@Override
			public void configure() throws Exception {
				String query = "select*from employee";
				HikariConfig config = new HikariConfig();
				config.setDriverClassName("com.mysql.cj.jdbc.Driver");
				config.setUsername("root");
				config.setPassword("root");
				config.setJdbcUrl("jdbc:mysql://localhost:3307/employee");
				DataSource dataSource = new HikariDataSource(config);
				bindToRegistry("mydataSource", dataSource);
				from("timer:run?period=2s").setBody(constant(query)).to("jdbc:dataSource")
						.process(new TransformerJdbcToFile()).to("file:file/Output?fileName=employees").log("Sucess")
						.end();
			}
		});
		
		context.start();
		Thread.sleep(10000);
		context.stop();


	}

}

/*
 * @Override public void configure() throws Exception { String query =
 * "select * from employee";
 * 
 * HikariConfig config = new HikariConfig();
 * config.setDriverClassName("com.mysql.cj.jdbc.Driver");
 * config.setUsername("root"); config.setPassword("root");
 * config.setJdbcUrl("jdbc:mysql://localhost:3307/employee");
 * 
 * DataSource dataSource = new HikariDataSource(config);
 * bindToRegistry("mydataSource", dataSource);
 * 
 * from("timer:run?period=2s").setBody(constant(query)).to("jdbc:dataSource").
 * process(new TransformerJdbcToFile())
 * .to("file:file/Output?fileName=employees").log("Sucess").end();
 * 
 * }
 */