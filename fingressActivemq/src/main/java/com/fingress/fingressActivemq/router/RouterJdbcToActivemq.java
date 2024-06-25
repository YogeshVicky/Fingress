package com.fingress.fingressActivemq.router;

import javax.sql.DataSource;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.fingress.fingressActivemq.transformer.TransformerJdbcToActivemq;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Component
public class RouterJdbcToActivemq {
	static Log log = LogFactory.getLog(RouterJdbcToActivemq.class);

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
					from("timer:sampleTimer?repeatCount=1").setBody(constant(query)).to("jdbc:dataSource")
							.process(new TransformerJdbcToActivemq()).to("activemq:queue:Activemq1")
							.log("Sucess inserted into Database to Activemq").end();
				}
			});

			context.start();
			Thread.sleep(10000);
			context.stop();

	}

}
