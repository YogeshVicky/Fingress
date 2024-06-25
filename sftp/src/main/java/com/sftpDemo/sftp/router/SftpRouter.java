package com.sftpDemo.sftp.router;

import org.apache.camel.builder.RouteBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

@Component
public class SftpRouter extends RouteBuilder {
	Log log = LogFactory.getLog(SftpRouter.class);

	@Override
	public void configure() throws Exception {

		/*
		 * try {
		 * 
		 * from(
		 * "sftp://195.154.173.28/sftp_test/inbox?username=demouser1&password=fujitsu123$%^&knownHostsFile=C:/Users/Admin/.ssh/known_hosts")
		 * .log("${body}") .to(
		 * "sftp://195.154.173.28/sftp_test/outbox?username=demouser1&password=fujitsu123$%^&knownHostsFile=C:/Users/Admin/.ssh/known_hosts")
		 * .log("sucess"); } catch (Exception e) { log.error("Unable to transfer", e);
		 * throw new RuntimeException(); }
		 * 
		 */
		from("sftp://demouser1@195.154.173.28:22/sftp_test/inbox?username=demouser1&password=fujitsu123$%^&knownHostsFile=C:/Users/Admin/.ssh/known_hosts")
				.log("transferring").log("${body}").to("file:files/Input?noop=true").log("sucess");

	}

}

//from("file:file?noop=true").log("${body}").to("sftp://demouser1@195.154.173.28:22/sftp_test/inbox?username=demouser1&password=fujitsu123$%^&knownHostsFile=C:/Users/Admin/.ssh/known_hosts")
// .log("sucess");
//"sftp://localhost:9999/root?username=admin&password=admin&preferredAuthentications=publickey,password"
//"sftp://my_host/?username=user_name&password=&preferredAuthentications=publickey&useUserKnownHostsFile=false&privateKeyFile=/Users/XXXX/.ssh/id_rsa")
//&knownHostsFile=C:/Users/Admin/.ssh/known_hosts
