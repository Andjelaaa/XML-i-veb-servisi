package com.xml.projekat.ws.endpoints;

import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.xml.projekat.ws.resenje.DecisionServiceSoapBindingImpl;




@Configuration
public class EndpointConfig {

	@Autowired
	private Bus bus;
	
	@Autowired
	DecisionServiceSoapBindingImpl service;
	@Bean
	public Endpoint resenjeEndpoint() {
		EndpointImpl endpoint = new EndpointImpl(bus, service);
		endpoint.publish("/resenjee");
		return endpoint;
	}
	
}
