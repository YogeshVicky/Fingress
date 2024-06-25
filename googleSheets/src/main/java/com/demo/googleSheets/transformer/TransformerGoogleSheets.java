package com.demo.googleSheets.transformer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TransformerGoogleSheets implements Processor {
	Log log = LogFactory.getLog(TransformerGoogleSheets.class);

	@Override
	public void process(Exchange exchange){

		try {
			String filePath = exchange.getIn().getBody(String.class);

			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode jsonNode = objectMapper.readTree(filePath);
			JsonNode valuesNode = jsonNode.get("values");

			StringBuilder csv = new StringBuilder();
			for (JsonNode data : valuesNode) {
				Iterator<JsonNode> elements = data.elements();
				while (elements.hasNext()) {
					csv.append(elements.next().asText());
					if (elements.hasNext()) {
						csv.append(",");
					}
				}
				csv.append("\n");

				exchange.getMessage().setBody(csv);				
				
			}

		} catch (Exception e) {
			log.error("Invalid request", e);
			throw new RuntimeException();
		}

	}

}
