package com.springbatchdemo.stepclass;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import javax.sql.DataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeWriter implements ItemWriter<List<Employee>> {
	Log log = LogFactory.getLog(EmployeeWriter.class);

	@Autowired
	DataSource dataSource;

	@Override
	public void write(Chunk<? extends List<Employee>> chunk) throws Exception {
		String query = "update employee set Is_Sync =? , Status=? where Is_Sync =?";

		boolean status = true;
		String is_Sync = "No";

		try {
			Connection con = dataSource.getConnection();
			PreparedStatement pst = con.prepareStatement(query);
			for (List<Employee> list : chunk) {
				for (Employee data : list) {
					pst.setString(1, data.getIs_Sync());
					pst.setBoolean(2, status);
					pst.setString(3, is_Sync);
				}
			}
			pst.executeUpdate();
			log.error("!!!!! Updated successfully !!!!!");
			con.close();
		} catch (Exception e) {
			log.error("Unable to update", e);
			throw new RuntimeException();
		}

	}
}
