package  com.xml.poverenik.ws.izvestaj;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;

/**
 * This class was generated by Apache CXF 3.2.0
 * 2021-02-04T19:44:58.856+01:00
 * Generated source version: 3.2.0
 * 
 */
@WebServiceClient(name = "ReportService", 
                  wsdlLocation = "src/main/resources/wsdl/zvestaj.wsdl",
                  targetNamespace = "http://izvestaj")
public class ReportService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://izvestaj", "ReportService");
    public final static QName ReportServiceSoapBinding = new QName("http://izvestaj", "ReportServiceSoapBinding");
    static {
        URL url = ReportService.class.getClassLoader().getResource("wsdl/izvestaj.wsdl");
        if (url == null) {
            java.util.logging.Logger.getLogger(ReportService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "classpath:wsdl/izvestaj.wsdl");
        }       
        WSDL_LOCATION = url;   
    }

    public ReportService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public ReportService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ReportService() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    public ReportService(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    public ReportService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    public ReportService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }    




    /**
     *
     * @return
     *     returns ReportServicePortType
     */
    @WebEndpoint(name = "ReportServiceSoapBinding")
    public ReportServicePortType getReportServiceSoapBinding() {
        return super.getPort(ReportServiceSoapBinding, ReportServicePortType.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ReportServicePortType
     */
    @WebEndpoint(name = "ReportServiceSoapBinding")
    public ReportServicePortType getReportServiceSoapBinding(WebServiceFeature... features) {
        return super.getPort(ReportServiceSoapBinding, ReportServicePortType.class, features);
    }

}
