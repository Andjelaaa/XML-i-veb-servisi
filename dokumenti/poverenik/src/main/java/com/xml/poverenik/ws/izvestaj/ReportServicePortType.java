package  com.xml.poverenik.ws.izvestaj;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 3.2.0
 * 2021-02-04T19:44:58.847+01:00
 * Generated source version: 3.2.0
 * 
 */
@WebService(targetNamespace = "http://izvestaj", name = "ReportServicePortType")
@XmlSeeAlso({ com.xml.poverenik.ws.izvestaj.ObjectFactory.class})
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface ReportServicePortType {

    @WebMethod(action = "http://izvestaj/ws/sendReport")
    public void sendReport(
        @WebParam(partName = "message", name = "message")
        com.xml.poverenik.ws.izvestaj.Message message
    );
}