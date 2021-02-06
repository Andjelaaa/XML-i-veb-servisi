package com.xml.poverenik;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.xmldb.api.base.XMLDBException;

import com.xml.poverenik.controller.ExistController;

@SpringBootApplication
public class PoverenikApplication {

	public static void main(String[] args) {
		System.setProperty("javax.xml.transform.TransformerFactory", "net.sf.saxon.TransformerFactoryImpl");
		//System.setProperty("javax.xml.bind.JAXBContext", "com.sun.xml.internal.bind.v2.ContextFactory");
		SpringApplication.run(PoverenikApplication.class, args);
	}

}
