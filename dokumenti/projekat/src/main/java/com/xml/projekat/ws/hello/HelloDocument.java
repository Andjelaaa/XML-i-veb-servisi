package com.xml.projekat.ws.hello;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 3.2.0
 * 2021-02-03T14:43:15.541+01:00
 * Generated source version: 3.2.0
 * 
 */
@WebService(targetNamespace = "http://projekat.xml.com/ws/hello", name = "HelloDocument")
@XmlSeeAlso({com.xml.projekat.ws.hello.types.ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface HelloDocument {

    @WebMethod
    @WebResult(name = "ResponseMiss", targetNamespace = "http://projekat.xml.com/ws/hello/types", partName = "ResponseMiss")
    public java.lang.String sayHelloMiss(
        @WebParam(partName = "RequestMiss", name = "RequestMiss", targetNamespace = "http://projekat.xml.com/ws/hello/types")
        com.xml.projekat.ws.hello.types.RequestMissType requestMiss
    );
}
