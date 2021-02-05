
package com.xml.projekat.ws.resenje;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the dokument_resenje package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _DokumentResenjeTekstObrazlozenjaP_QNAME = new QName("http://dokument_resenje", "p");
    private final static QName _DokumentResenjeZaglavljeBrojResenja_QNAME = new QName("http://dokument_resenje", "broj_resenja");
    private final static QName _DokumentResenjeZaglavljeDatum_QNAME = new QName("http://dokument_resenje", "datum");
    private final static QName _DokumentResenjeNazivResenjaOdluka_QNAME = new QName("http://dokument_resenje", "odluka");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: dokument_resenje
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DokumentResenje }
     * 
     */
    public DokumentResenje createDokumentResenje() {
        return new DokumentResenje();
    }

    /**
     * Create an instance of {@link DokumentResenje.Zaglavlje }
     * 
     */
    public DokumentResenje.Zaglavlje createDokumentResenjeZaglavlje() {
        return new DokumentResenje.Zaglavlje();
    }

    /**
     * Create an instance of {@link DokumentResenje.URI }
     * 
     */
    public DokumentResenje.URI createDokumentResenjeURI() {
        return new DokumentResenje.URI();
    }

    /**
     * Create an instance of {@link DokumentResenje.ZalbaCutanjeUri }
     * 
     */
    public DokumentResenje.ZalbaCutanjeUri createDokumentResenjeZalbaCutanjeUri() {
        return new DokumentResenje.ZalbaCutanjeUri();
    }

    /**
     * Create an instance of {@link DokumentResenje.ZalbaOdlukeUri }
     * 
     */
    public DokumentResenje.ZalbaOdlukeUri createDokumentResenjeZalbaOdlukeUri() {
        return new DokumentResenje.ZalbaOdlukeUri();
    }

    /**
     * Create an instance of {@link DokumentResenje.NazivResenja }
     * 
     */
    public DokumentResenje.NazivResenja createDokumentResenjeNazivResenja() {
        return new DokumentResenje.NazivResenja();
    }

    /**
     * Create an instance of {@link DokumentResenje.TekstResenja }
     * 
     */
    public DokumentResenje.TekstResenja createDokumentResenjeTekstResenja() {
        return new DokumentResenje.TekstResenja();
    }

    /**
     * Create an instance of {@link DokumentResenje.TekstObrazlozenja }
     * 
     */
    public DokumentResenje.TekstObrazlozenja createDokumentResenjeTekstObrazlozenja() {
        return new DokumentResenje.TekstObrazlozenja();
    }

    /**
     * Create an instance of {@link DokumentResenje.KorisnickoIme }
     * 
     */
    public DokumentResenje.KorisnickoIme createDokumentResenjeKorisnickoIme() {
        return new DokumentResenje.KorisnickoIme();
    }

    /**
     * Create an instance of {@link DokumentResenje.Zaglavlje.BrojResenja }
     * 
     */
    public DokumentResenje.Zaglavlje.BrojResenja createDokumentResenjeZaglavljeBrojResenja() {
        return new DokumentResenje.Zaglavlje.BrojResenja();
    }

    /**
     * Create an instance of {@link DokumentResenje.Zaglavlje.Datum }
     * 
     */
    public DokumentResenje.Zaglavlje.Datum createDokumentResenjeZaglavljeDatum() {
        return new DokumentResenje.Zaglavlje.Datum();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dokument_resenje", name = "p", scope = DokumentResenje.TekstObrazlozenja.class)
    public JAXBElement<String> createDokumentResenjeTekstObrazlozenjaP(String value) {
        return new JAXBElement<String>(_DokumentResenjeTekstObrazlozenjaP_QNAME, String.class, DokumentResenje.TekstObrazlozenja.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dokument_resenje", name = "p", scope = DokumentResenje.TekstResenja.class)
    public JAXBElement<String> createDokumentResenjeTekstResenjaP(String value) {
        return new JAXBElement<String>(_DokumentResenjeTekstObrazlozenjaP_QNAME, String.class, DokumentResenje.TekstResenja.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DokumentResenje.Zaglavlje.BrojResenja }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dokument_resenje", name = "broj_resenja", scope = DokumentResenje.Zaglavlje.class)
    public JAXBElement<DokumentResenje.Zaglavlje.BrojResenja> createDokumentResenjeZaglavljeBrojResenja(DokumentResenje.Zaglavlje.BrojResenja value) {
        return new JAXBElement<DokumentResenje.Zaglavlje.BrojResenja>(_DokumentResenjeZaglavljeBrojResenja_QNAME, DokumentResenje.Zaglavlje.BrojResenja.class, DokumentResenje.Zaglavlje.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DokumentResenje.Zaglavlje.Datum }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dokument_resenje", name = "datum", scope = DokumentResenje.Zaglavlje.class)
    public JAXBElement<DokumentResenje.Zaglavlje.Datum> createDokumentResenjeZaglavljeDatum(DokumentResenje.Zaglavlje.Datum value) {
        return new JAXBElement<DokumentResenje.Zaglavlje.Datum>(_DokumentResenjeZaglavljeDatum_QNAME, DokumentResenje.Zaglavlje.Datum.class, DokumentResenje.Zaglavlje.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dokument_resenje", name = "odluka", scope = DokumentResenje.NazivResenja.class)
    public JAXBElement<String> createDokumentResenjeNazivResenjaOdluka(String value) {
        return new JAXBElement<String>(_DokumentResenjeNazivResenjaOdluka_QNAME, String.class, DokumentResenje.NazivResenja.class, value);
    }

}
