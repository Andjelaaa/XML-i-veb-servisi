package com.xml.poverenik.service;

import java.util.Base64;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;

import org.springframework.stereotype.Service;

import com.xml.poverenik.data.types.Message;


@Service
public class EmailService {

public void posaljiMejl(Message message) throws Exception{
		
		String soapEndpointUrl = "http://localhost:8083/ws/email";
        
        SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
        SOAPConnection soapConnection = soapConnectionFactory.createConnection();

        
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();
        
        SOAPPart soapPart = soapMessage.getSOAPPart();

        String myNamespace = "ps";
        String myNamespaceURI = "http://message";

        // SOAP Envelope
        SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.addNamespaceDeclaration(myNamespace, myNamespaceURI);

        SOAPBody soapBody = envelope.getBody();
        envelope.addNamespaceDeclaration(myNamespace, myNamespaceURI);
//        SOAPElement posaljiPismoElem = soapBody.addChildElement("posaljiPismo");
        SOAPElement pismoElem = soapBody.addChildElement("message", myNamespace);
        pismoElem.setAttribute("tipPriloga", message.getTipPriloga());
        SOAPElement primalacElem = pismoElem.addChildElement("primalac", myNamespace);
        primalacElem.addTextNode(message.getPrimalac());
        SOAPElement naslovElem = pismoElem.addChildElement("naslov", myNamespace);
        naslovElem.addTextNode(message.getNaslov());
        SOAPElement sadrzajElem = pismoElem.addChildElement("sadrzaj", myNamespace);
        sadrzajElem.addTextNode(message.getSadrzaj());
        SOAPElement prilogElem = pismoElem.addChildElement("prilog", myNamespace);
        
        System.out.println("PRILOG");
       
        String s = Base64.getEncoder().encodeToString(message.getPrilog());
        System.out.println(s);
        prilogElem.addTextNode(s);
        
        System.out.println();
        System.out.println(soapBody);
        
//        MimeHeaders headers = soapMessage.getMimeHeaders();
//        headers.addHeader("SOAPAction", soapAction);

        soapMessage.saveChanges();

        /* Print the request message, just for debugging purposes */
        System.out.println("Request SOAP Message:");
        soapMessage.writeTo(System.out);
        System.out.println("\n");
        // Send SOAP Message to SOAP Server
        SOAPMessage soapResponse = soapConnection.call(soapMessage, soapEndpointUrl);

        // Print the SOAP Response
        System.out.println("Response SOAP Message:");
        soapResponse.writeTo(System.out);
}
}
