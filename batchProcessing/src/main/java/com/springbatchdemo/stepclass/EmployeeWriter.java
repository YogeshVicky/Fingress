package com.springbatchdemo.stepclass;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
public class EmployeeWriter implements ItemWriter<List<String>> {
	Log log = LogFactory.getLog(EmployeeWriter.class);

	@Override
	public void write(Chunk<? extends List<String>> chunk) throws Exception {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("file/Input/employee.csv"))) {
			for (List<String> linesItem : chunk) {
				for (String line : linesItem) {
					writer.write(line);
					writer.newLine();
					log.error("Success");
				}
			}

		} catch (Exception e) {
			log.error("Invalid Request " + e);
		}
	}
}
