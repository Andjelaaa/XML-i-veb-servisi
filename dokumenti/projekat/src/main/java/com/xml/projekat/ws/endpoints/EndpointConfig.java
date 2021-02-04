package com.xml.projekat.ws.endpoints;

import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.xml.projekat.ws.hello.HelloDocumentImpl;


@Configuration
public class EndpointConfig {

	@Autowired
	private Bus bus;
	
	@Bean
	public Endpoint helloEndpoint() {
		EndpointImpl endpoint = new EndpointImpl(bus, new HelloDocumentImpl());
		endpoint.publish("/hello");
		return endpoint;
	}

	
}
