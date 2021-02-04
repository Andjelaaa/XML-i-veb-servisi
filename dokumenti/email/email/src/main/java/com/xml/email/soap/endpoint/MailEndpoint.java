package com.xml.email.soap.endpoint;

import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.xml.email.soap.EmailServiceSoapBindingImpl;

@Configuration
public class MailEndpoint {
	
	@Autowired 
	private Bus bus;
	
	@Autowired
	EmailServiceSoapBindingImpl emailServiceSoapBindingImpl;
	
	
	@Bean(name="emailEndpointBean")
	public Endpoint emailEndpoint() {
		EndpointImpl endpoint = new EndpointImpl(bus, emailServiceSoapBindingImpl);
		endpoint.publish("/email");
		return endpoint;
	}

}
