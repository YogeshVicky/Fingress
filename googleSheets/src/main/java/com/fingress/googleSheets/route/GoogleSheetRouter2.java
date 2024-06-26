package com.fingress.googleSheets.route;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.google.sheets.GoogleSheetsComponent;
import org.apache.camel.component.google.sheets.GoogleSheetsConfiguration;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.stereotype.Component;

import com.fingress.googleSheets.transformer.GoogleSheetsTransformer2;

@Component
public class GoogleSheetRouter2 {
	private static String clientId = "1094615859075-3stum4c78pljrnv50oru5e7khu6armrr.apps.googleusercontent.com";
	private static String clientSecret = "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";
	private static String accessToken = "ya29.a0AXooCgu_urtMscaRDesSYtzNQ3iRnGmFpvrIzEEpDNZKGSU_kz_u6XYnyVeX7m2DtWMhKzyIAhzZuHPu9G3gTjOg0SscoRfBXnKj5LyweQS2D4B3f1Dz9NLMl5z2PnUyuJfKfTSoIfzAXDjKsTzTdTUhhZBzjhRWFX9baCgYKAWcSARESFQHGX2MiIddJphmfdfOP9aBWpfbntw0171";
	private static String refreshToken = "1//04QlGna-pU_DLCgYIARAAGAQSNwF-L9IrCzRQCON4EoKsgk2hCcufkEfh1_Q_Rr6ScfIzBHdslYic9u5VqwMqu0b69-Es92c7Cuo";
	private static String applicationName = "Camel-Google-Sheets";

	public static void main(String[] args) throws Exception {
		CamelContext context = new DefaultCamelContext();
		context.addRoutes(new RouteBuilder() {

			@Override
			public void configure() throws Exception {
				GoogleSheetsConfiguration configuration = new GoogleSheetsConfiguration();
				configuration.setClientId(clientId);
				configuration.setRefreshToken(refreshToken);
				configuration.setClientSecret(clientSecret);
				configuration.setAccessToken(accessToken);
				configuration.setApplicationName(applicationName);
				String spreadsheetId = "1i8jAVG3r6BINzdU0NzgAIuDUuz3FiVRyFF1Wm6WQq_0";
				String range = "Camel Sheet1";

				GoogleSheetsComponent component = new GoogleSheetsComponent();
				component.setConfiguration(configuration);
				getContext().addComponent("google-sheets", component);

				from("file:file/Input?noop=true")
				.process(new GoogleSheetsTransformer2())
				.log("${body}")
				.to("google-sheets://data/append?spreadsheetId="+spreadsheetId+"&range="+range)
				.log("Success");

			}
		});
		context.start();
		Thread.sleep(10000);
		context.stop();
	}

}
