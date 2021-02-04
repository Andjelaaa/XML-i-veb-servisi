package com.xml.poverenik.ws.endpoints;

import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.xml.poverenik.ws.izvestaj.ReportServiceSoapBindingImpl;



@Configuration
public class EndpointConfig {

	@Autowired
	private Bus bus;
	
	@Autowired
	ReportServiceSoapBindingImpl asdf;
	@Bean
	public Endpoint izvestajEndpoint() {
		EndpointImpl endpoint = new EndpointImpl(bus, asdf);
		endpoint.publish("/izvestaj");
		return endpoint;
	}

	
}
