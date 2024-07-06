package com.springbatchdemo.stepclass;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmployeeProcessor implements ItemProcessor<List<Employee>, List<Employee>> {
	Log log = LogFactory.getLog(EmployeeProcessor.class);

	@Override
	public List<Employee> process(List<Employee> employee) throws Exception {

		for (Employee data : employee) {
			data.setIs_Sync("Yes");
		}
		return employee;
	}

	@Bean
	public EmployeeProcessor processor() {
		return new EmployeeProcessor();

	}

}
