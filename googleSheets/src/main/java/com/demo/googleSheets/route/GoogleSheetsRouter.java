package com.demo.googleSheets.route;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.google.sheets.GoogleSheetsComponent;
import org.apache.camel.component.google.sheets.GoogleSheetsConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.demo.googleSheets.transformer.TransformerGoogleSheets;



@Component
public class GoogleSheetsRouter extends RouteBuilder {
	@Value("${camel.component.google-sheets.configuration.client-id}")
	private String clientId;

	@Value("${camel.component.google-sheets.configuration.client-secret}")
	private String clientSecret;
	
	@Value("${camel.component.google-sheets.configuration.access-token}")
	private String accessToken;

	@Value("${camel.component.google-sheets.configuration.refresh-token}")
	private String refreshToken;

	@Value("${camel.component.google-sheets.configuration.application-name}")
	private String applicationName;

	@Override
	public void configure() throws Exception {
		GoogleSheetsConfiguration configuration = new GoogleSheetsConfiguration();
		configuration.setClientId(clientId);
		configuration.setRefreshToken(refreshToken);
		configuration.setClientSecret(clientSecret);
		configuration.setAccessToken(accessToken);
		configuration.setApplicationName(applicationName);
		
		GoogleSheetsComponent component = new GoogleSheetsComponent();

		component.setConfiguration(configuration);
		getContext().addComponent("google-sheets", component);
		
		from("timer:run?repeatCount=1")
		.to("google-sheets://data/get?spreadsheetId=1i8jAVG3r6BINzdU0NzgAIuDUuz3FiVRyFF1Wm6WQq_0")
		.log("${body}").convertBodyTo(String.class).process(new TransformerGoogleSheets()).to("file:file/Input?fileName=employee.csv").log("Sucess");
		
	}
	
}
