package com.xml.projekat.ws.resenje;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 3.2.0
 * 2021-02-05T11:36:55.171+01:00
 * Generated source version: 3.2.0
 * 
 */
@WebService(targetNamespace = "http://resenje", name = "DecisionServicePortType")
@XmlSeeAlso({com.xml.projekat.ws.resenje.ObjectFactory.class})
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface DecisionServicePortType {

    @WebMethod(action = "http://resenjee/ws/sendDecision")
    public void sendDecision(
        @WebParam(partName = "dokument_resenje", name = "dokument_resenje")
        com.xml.projekat.ws.resenje.DokumentResenje dokumentResenje
    );
}
