
package com.xml.projekat.ws.resenje;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlMixed;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.namespace.QName;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="URI"&gt;
 *           &lt;complexType&gt;
 *             &lt;simpleContent&gt;
 *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;byte"&gt;
 *                 &lt;attribute name="datatype" type="{http://www.w3.org/2001/XMLSchema}QName" /&gt;
 *                 &lt;attribute name="property" type="{http://www.w3.org/2001/XMLSchema}QName" /&gt;
 *               &lt;/extension&gt;
 *             &lt;/simpleContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="zalba_cutanje_uri"&gt;
 *           &lt;complexType&gt;
 *             &lt;simpleContent&gt;
 *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;string"&gt;
 *                 &lt;attribute name="datatype" type="{http://www.w3.org/2001/XMLSchema}QName" /&gt;
 *                 &lt;attribute name="property" type="{http://www.w3.org/2001/XMLSchema}QName" /&gt;
 *               &lt;/extension&gt;
 *             &lt;/simpleContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="zalba_odluke_uri"&gt;
 *           &lt;complexType&gt;
 *             &lt;simpleContent&gt;
 *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;byte"&gt;
 *                 &lt;attribute name="datatype" type="{http://www.w3.org/2001/XMLSchema}QName" /&gt;
 *                 &lt;attribute name="property" type="{http://www.w3.org/2001/XMLSchema}QName" /&gt;
 *               &lt;/extension&gt;
 *             &lt;/simpleContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="naziv_resenja"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="odluka" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="zaglavlje"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="broj_resenja"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;simpleContent&gt;
 *                         &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;string"&gt;
 *                           &lt;attribute name="datatype" type="{http://www.w3.org/2001/XMLSchema}QName" /&gt;
 *                           &lt;attribute name="property" type="{http://www.w3.org/2001/XMLSchema}QName" /&gt;
 *                         &lt;/extension&gt;
 *                       &lt;/simpleContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="datum"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;simpleContent&gt;
 *                         &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;string"&gt;
 *                           &lt;attribute name="datatype" type="{http://www.w3.org/2001/XMLSchema}QName" /&gt;
 *                           &lt;attribute name="property" type="{http://www.w3.org/2001/XMLSchema}QName" /&gt;
 *                         &lt;/extension&gt;
 *                       &lt;/simpleContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="opis_postupka" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="tekst_resenja"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="p" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="tekst_obrazlozenja"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="p" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="potpis_poverenika" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="korisnicko_ime"&gt;
 *           &lt;complexType&gt;
 *             &lt;simpleContent&gt;
 *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;string"&gt;
 *                 &lt;attribute name="datatype" type="{http://www.w3.org/2001/XMLSchema}QName" /&gt;
 *                 &lt;attribute name="property" type="{http://www.w3.org/2001/XMLSchema}QName" /&gt;
 *               &lt;/extension&gt;
 *             &lt;/simpleContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "uri",
    "zalbaCutanjeUri",
    "zalbaOdlukeUri",
    "nazivResenja",
    "zaglavlje",
    "opisPostupka",
    "tekstResenja",
    "tekstObrazlozenja",
    "potpisPoverenika",
    "korisnickoIme"
})
@XmlRootElement(name = "dokument_resenje")
public class DokumentResenje {

    @XmlElement(name = "URI", required = true)
    protected DokumentResenje.URI uri;
    @XmlElement(name = "zalba_cutanje_uri", required = true)
    protected DokumentResenje.ZalbaCutanjeUri zalbaCutanjeUri;
    @XmlElement(name = "zalba_odluke_uri", required = true)
    protected DokumentResenje.ZalbaOdlukeUri zalbaOdlukeUri;
    @XmlElement(name = "naziv_resenja", required = true)
    protected DokumentResenje.NazivResenja nazivResenja;
    @XmlElement(required = true)
    protected DokumentResenje.Zaglavlje zaglavlje;
    @XmlElement(name = "opis_postupka", required = true)
    protected String opisPostupka;
    @XmlElement(name = "tekst_resenja", required = true)
    protected DokumentResenje.TekstResenja tekstResenja;
    @XmlElement(name = "tekst_obrazlozenja", required = true)
    protected DokumentResenje.TekstObrazlozenja tekstObrazlozenja;
    @XmlElement(name = "potpis_poverenika", required = true)
    protected String potpisPoverenika;
    @XmlElement(name = "korisnicko_ime", required = true)
    protected DokumentResenje.KorisnickoIme korisnickoIme;

    /**
     * Gets the value of the uri property.
     * 
     * @return
     *     possible object is
     *     {@link DokumentResenje.URI }
     *     
     */
    public DokumentResenje.URI getURI() {
        return uri;
    }

    /**
     * Sets the value of the uri property.
     * 
     * @param value
     *     allowed object is
     *     {@link DokumentResenje.URI }
     *     
     */
    public void setURI(DokumentResenje.URI value) {
        this.uri = value;
    }

    /**
     * Gets the value of the zalbaCutanjeUri property.
     * 
     * @return
     *     possible object is
     *     {@link DokumentResenje.ZalbaCutanjeUri }
     *     
     */
    public DokumentResenje.ZalbaCutanjeUri getZalbaCutanjeUri() {
        return zalbaCutanjeUri;
    }

    /**
     * Sets the value of the zalbaCutanjeUri property.
     * 
     * @param value
     *     allowed object is
     *     {@link DokumentResenje.ZalbaCutanjeUri }
     *     
     */
    public void setZalbaCutanjeUri(DokumentResenje.ZalbaCutanjeUri value) {
        this.zalbaCutanjeUri = value;
    }

    /**
     * Gets the value of the zalbaOdlukeUri property.
     * 
     * @return
     *     possible object is
     *     {@link DokumentResenje.ZalbaOdlukeUri }
     *     
     */
    public DokumentResenje.ZalbaOdlukeUri getZalbaOdlukeUri() {
        return zalbaOdlukeUri;
    }

    /**
     * Sets the value of the zalbaOdlukeUri property.
     * 
     * @param value
     *     allowed object is
     *     {@link DokumentResenje.ZalbaOdlukeUri }
     *     
     */
    public void setZalbaOdlukeUri(DokumentResenje.ZalbaOdlukeUri value) {
        this.zalbaOdlukeUri = value;
    }

    /**
     * Gets the value of the nazivResenja property.
     * 
     * @return
     *     possible object is
     *     {@link DokumentResenje.NazivResenja }
     *     
     */
    public DokumentResenje.NazivResenja getNazivResenja() {
        return nazivResenja;
    }

    /**
     * Sets the value of the nazivResenja property.
     * 
     * @param value
     *     allowed object is
     *     {@link DokumentResenje.NazivResenja }
     *     
     */
    public void setNazivResenja(DokumentResenje.NazivResenja value) {
        this.nazivResenja = value;
    }

    /**
     * Gets the value of the zaglavlje property.
     * 
     * @return
     *     possible object is
     *     {@link DokumentResenje.Zaglavlje }
     *     
     */
    public DokumentResenje.Zaglavlje getZaglavlje() {
        return zaglavlje;
    }

    /**
     * Sets the value of the zaglavlje property.
     * 
     * @param value
     *     allowed object is
     *     {@link DokumentResenje.Zaglavlje }
     *     
     */
    public void setZaglavlje(DokumentResenje.Zaglavlje value) {
        this.zaglavlje = value;
    }

    /**
     * Gets the value of the opisPostupka property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOpisPostupka() {
        return opisPostupka;
    }

    /**
     * Sets the value of the opisPostupka property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOpisPostupka(String value) {
        this.opisPostupka = value;
    }

    /**
     * Gets the value of the tekstResenja property.
     * 
     * @return
     *     possible object is
     *     {@link DokumentResenje.TekstResenja }
     *     
     */
    public DokumentResenje.TekstResenja getTekstResenja() {
        return tekstResenja;
    }

    /**
     * Sets the value of the tekstResenja property.
     * 
     * @param value
     *     allowed object is
     *     {@link DokumentResenje.TekstResenja }
     *     
     */
    public void setTekstResenja(DokumentResenje.TekstResenja value) {
        this.tekstResenja = value;
    }

    /**
     * Gets the value of the tekstObrazlozenja property.
     * 
     * @return
     *     possible object is
     *     {@link DokumentResenje.TekstObrazlozenja }
     *     
     */
    public DokumentResenje.TekstObrazlozenja getTekstObrazlozenja() {
        return tekstObrazlozenja;
    }

    /**
     * Sets the value of the tekstObrazlozenja property.
     * 
     * @param value
     *     allowed object is
     *     {@link DokumentResenje.TekstObrazlozenja }
     *     
     */
    public void setTekstObrazlozenja(DokumentResenje.TekstObrazlozenja value) {
        this.tekstObrazlozenja = value;
    }

    /**
     * Gets the value of the potpisPoverenika property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPotpisPoverenika() {
        return potpisPoverenika;
    }

    /**
     * Sets the value of the potpisPoverenika property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPotpisPoverenika(String value) {
        this.potpisPoverenika = value;
    }

    /**
     * Gets the value of the korisnickoIme property.
     * 
     * @return
     *     possible object is
     *     {@link DokumentResenje.KorisnickoIme }
     *     
     */
    public DokumentResenje.KorisnickoIme getKorisnickoIme() {
        return korisnickoIme;
    }

    /**
     * Sets the value of the korisnickoIme property.
     * 
     * @param value
     *     allowed object is
     *     {@link DokumentResenje.KorisnickoIme }
     *     
     */
    public void setKorisnickoIme(DokumentResenje.KorisnickoIme value) {
        this.korisnickoIme = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;simpleContent&gt;
     *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;string"&gt;
     *       &lt;attribute name="datatype" type="{http://www.w3.org/2001/XMLSchema}QName" /&gt;
     *       &lt;attribute name="property" type="{http://www.w3.org/2001/XMLSchema}QName" /&gt;
     *     &lt;/extension&gt;
     *   &lt;/simpleContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "value"
    })
    public static class KorisnickoIme {

        @XmlValue
        protected String value;
        @XmlAttribute(name = "datatype")
        protected QName datatype;
        @XmlAttribute(name = "property")
        protected QName property;

        /**
         * Gets the value of the value property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getValue() {
            return value;
        }

        /**
         * Sets the value of the value property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setValue(String value) {
            this.value = value;
        }

        /**
         * Gets the value of the datatype property.
         * 
         * @return
         *     possible object is
         *     {@link QName }
         *     
         */
        public QName getDatatype() {
            return datatype;
        }

        /**
         * Sets the value of the datatype property.
         * 
         * @param value
         *     allowed object is
         *     {@link QName }
         *     
         */
        public void setDatatype(QName value) {
            this.datatype = value;
        }

        /**
         * Gets the value of the property property.
         * 
         * @return
         *     possible object is
         *     {@link QName }
         *     
         */
        public QName getProperty() {
            return property;
        }

        /**
         * Sets the value of the property property.
         * 
         * @param value
         *     allowed object is
         *     {@link QName }
         *     
         */
        public void setProperty(QName value) {
            this.property = value;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="odluka" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "content"
    })
    @XmlRootElement(name = "naziv_resenja")
    public static class NazivResenja {
        @XmlElementRef(name = "odluka", namespace = "http://dokument_resenje", type = JAXBElement.class)
        @XmlMixed
        protected List<Serializable> content;

        
        /**
         * Gets the value of the content property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the content property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getContent().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link JAXBElement }{@code <}{@link String }{@code >}
         * {@link String }
         * 
         * 
         */
        public List<Serializable> getContent() {
            if (content == null) {
                content = new ArrayList<Serializable>();
            }
            return this.content;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="p" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "content"
    })
    public static class TekstObrazlozenja {

        @XmlElementRef(name = "p", namespace = "http://dokument_resenje", type = JAXBElement.class, required = false)
        @XmlMixed
        protected List<Serializable> content;

        /**
         * Gets the value of the content property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the content property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getContent().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link JAXBElement }{@code <}{@link String }{@code >}
         * {@link String }
         * 
         * 
         */
        public List<Serializable> getContent() {
            if (content == null) {
                content = new ArrayList<Serializable>();
            }
            return this.content;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="p" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "content"
    })
    public static class TekstResenja {

        @XmlElementRef(name = "p", namespace = "http://dokument_resenje", type = JAXBElement.class, required = false)
        @XmlMixed
        protected List<Serializable> content;

        /**
         * Gets the value of the content property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the content property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getContent().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link JAXBElement }{@code <}{@link String }{@code >}
         * {@link String }
         * 
         * 
         */
        public List<Serializable> getContent() {
            if (content == null) {
                content = new ArrayList<Serializable>();
            }
            return this.content;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;simpleContent&gt;
     *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;byte"&gt;
     *       &lt;attribute name="datatype" type="{http://www.w3.org/2001/XMLSchema}QName" /&gt;
     *       &lt;attribute name="property" type="{http://www.w3.org/2001/XMLSchema}QName" /&gt;
     *     &lt;/extension&gt;
     *   &lt;/simpleContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "value"
    })
    public static class URI {

        @XmlValue
        protected String value;
        @XmlAttribute(name = "datatype")
        protected QName datatype;
        @XmlAttribute(name = "property")
        protected QName property;

        /**
         * Gets the value of the value property.
         * 
         */
        public String getValue() {
            return value;
        }

        /**
         * Sets the value of the value property.
         * 
         */
        public void setValue(String value) {
            this.value = value;
        }

        /**
         * Gets the value of the datatype property.
         * 
         * @return
         *     possible object is
         *     {@link QName }
         *     
         */
        public QName getDatatype() {
            return datatype;
        }

        /**
         * Sets the value of the datatype property.
         * 
         * @param value
         *     allowed object is
         *     {@link QName }
         *     
         */
        public void setDatatype(QName value) {
            this.datatype = value;
        }

        /**
         * Gets the value of the property property.
         * 
         * @return
         *     possible object is
         *     {@link QName }
         *     
         */
        public QName getProperty() {
            return property;
        }

        /**
         * Sets the value of the property property.
         * 
         * @param value
         *     allowed object is
         *     {@link QName }
         *     
         */
        public void setProperty(QName value) {
            this.property = value;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="broj_resenja"&gt;
     *           &lt;complexType&gt;
     *             &lt;simpleContent&gt;
     *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;string"&gt;
     *                 &lt;attribute name="datatype" type="{http://www.w3.org/2001/XMLSchema}QName" /&gt;
     *                 &lt;attribute name="property" type="{http://www.w3.org/2001/XMLSchema}QName" /&gt;
     *               &lt;/extension&gt;
     *             &lt;/simpleContent&gt;
     *           &lt;/complexType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="datum"&gt;
     *           &lt;complexType&gt;
     *             &lt;simpleContent&gt;
     *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;string"&gt;
     *                 &lt;attribute name="datatype" type="{http://www.w3.org/2001/XMLSchema}QName" /&gt;
     *                 &lt;attribute name="property" type="{http://www.w3.org/2001/XMLSchema}QName" /&gt;
     *               &lt;/extension&gt;
     *             &lt;/simpleContent&gt;
     *           &lt;/complexType&gt;
     *         &lt;/element&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "content"
    })
    public static class Zaglavlje {

        @XmlElementRefs({
            @XmlElementRef(name = "broj_resenja", namespace = "http://dokument_resenje", type = JAXBElement.class),
            @XmlElementRef(name = "datum", namespace = "http://dokument_resenje", type = JAXBElement.class)
        })
        @XmlMixed
        protected List<Serializable> content;

        /**
         * Gets the value of the content property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the content property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getContent().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link JAXBElement }{@code <}{@link DokumentResenje.Zaglavlje.BrojResenja }{@code >}
         * {@link JAXBElement }{@code <}{@link DokumentResenje.Zaglavlje.Datum }{@code >}
         * {@link String }
         * 
         * 
         */
        public List<Serializable> getContent() {
            if (content == null) {
                content = new ArrayList<Serializable>();
            }
            return this.content;
        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType&gt;
         *   &lt;simpleContent&gt;
         *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;string"&gt;
         *       &lt;attribute name="datatype" type="{http://www.w3.org/2001/XMLSchema}QName" /&gt;
         *       &lt;attribute name="property" type="{http://www.w3.org/2001/XMLSchema}QName" /&gt;
         *     &lt;/extension&gt;
         *   &lt;/simpleContent&gt;
         * &lt;/complexType&gt;
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "value"
        })
        public static class BrojResenja {

            @XmlValue
            protected String value;
            @XmlAttribute(name = "datatype")
            protected QName datatype;
            @XmlAttribute(name = "property")
            protected QName property;

            /**
             * Gets the value of the value property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getValue() {
                return value;
            }

            /**
             * Sets the value of the value property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setValue(String value) {
                this.value = value;
            }

            /**
             * Gets the value of the datatype property.
             * 
             * @return
             *     possible object is
             *     {@link QName }
             *     
             */
            public QName getDatatype() {
                return datatype;
            }

            /**
             * Sets the value of the datatype property.
             * 
             * @param value
             *     allowed object is
             *     {@link QName }
             *     
             */
            public void setDatatype(QName value) {
                this.datatype = value;
            }

            /**
             * Gets the value of the property property.
             * 
             * @return
             *     possible object is
             *     {@link QName }
             *     
             */
            public QName getProperty() {
                return property;
            }

            /**
             * Sets the value of the property property.
             * 
             * @param value
             *     allowed object is
             *     {@link QName }
             *     
             */
            public void setProperty(QName value) {
                this.property = value;
            }

        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType&gt;
         *   &lt;simpleContent&gt;
         *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;string"&gt;
         *       &lt;attribute name="datatype" type="{http://www.w3.org/2001/XMLSchema}QName" /&gt;
         *       &lt;attribute name="property" type="{http://www.w3.org/2001/XMLSchema}QName" /&gt;
         *     &lt;/extension&gt;
         *   &lt;/simpleContent&gt;
         * &lt;/complexType&gt;
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "value"
        })
        public static class Datum {

            @XmlValue
            protected String value;
            @XmlAttribute(name = "datatype")
            protected QName datatype;
            @XmlAttribute(name = "property")
            protected QName property;

            /**
             * Gets the value of the value property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getValue() {
                return value;
            }

            /**
             * Sets the value of the value property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setValue(String value) {
                this.value = value;
            }

            /**
             * Gets the value of the datatype property.
             * 
             * @return
             *     possible object is
             *     {@link QName }
             *     
             */
            public QName getDatatype() {
                return datatype;
            }

            /**
             * Sets the value of the datatype property.
             * 
             * @param value
             *     allowed object is
             *     {@link QName }
             *     
             */
            public void setDatatype(QName value) {
                this.datatype = value;
            }

            /**
             * Gets the value of the property property.
             * 
             * @return
             *     possible object is
             *     {@link QName }
             *     
             */
            public QName getProperty() {
                return property;
            }

            /**
             * Sets the value of the property property.
             * 
             * @param value
             *     allowed object is
             *     {@link QName }
             *     
             */
            public void setProperty(QName value) {
                this.property = value;
            }

        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;simpleContent&gt;
     *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;string"&gt;
     *       &lt;attribute name="datatype" type="{http://www.w3.org/2001/XMLSchema}QName" /&gt;
     *       &lt;attribute name="property" type="{http://www.w3.org/2001/XMLSchema}QName" /&gt;
     *     &lt;/extension&gt;
     *   &lt;/simpleContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "value"
    })
    public static class ZalbaCutanjeUri {

        @XmlValue
        protected String value;
        @XmlAttribute(name = "datatype")
        protected QName datatype;
        @XmlAttribute(name = "property")
        protected QName property;

        /**
         * Gets the value of the value property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getValue() {
            return value;
        }

        /**
         * Sets the value of the value property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setValue(String value) {
            this.value = value;
        }

        /**
         * Gets the value of the datatype property.
         * 
         * @return
         *     possible object is
         *     {@link QName }
         *     
         */
        public QName getDatatype() {
            return datatype;
        }

        /**
         * Sets the value of the datatype property.
         * 
         * @param value
         *     allowed object is
         *     {@link QName }
         *     
         */
        public void setDatatype(QName value) {
            this.datatype = value;
        }

        /**
         * Gets the value of the property property.
         * 
         * @return
         *     possible object is
         *     {@link QName }
         *     
         */
        public QName getProperty() {
            return property;
        }

        /**
         * Sets the value of the property property.
         * 
         * @param value
         *     allowed object is
         *     {@link QName }
         *     
         */
        public void setProperty(QName value) {
            this.property = value;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;simpleContent&gt;
     *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;byte"&gt;
     *       &lt;attribute name="datatype" type="{http://www.w3.org/2001/XMLSchema}QName" /&gt;
     *       &lt;attribute name="property" type="{http://www.w3.org/2001/XMLSchema}QName" /&gt;
     *     &lt;/extension&gt;
     *   &lt;/simpleContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "value"
    })
    public static class ZalbaOdlukeUri {

        @XmlValue
        protected String value;
        @XmlAttribute(name = "datatype")
        protected QName datatype;
        @XmlAttribute(name = "property")
        protected QName property;

        /**
         * Gets the value of the value property.
         * 
         */
        public String getValue() {
            return value;
        }

        /**
         * Sets the value of the value property.
         * 
         */
        public void setValue(String value) {
            this.value = value;
        }

        /**
         * Gets the value of the datatype property.
         * 
         * @return
         *     possible object is
         *     {@link QName }
         *     
         */
        public QName getDatatype() {
            return datatype;
        }

        /**
         * Sets the value of the datatype property.
         * 
         * @param value
         *     allowed object is
         *     {@link QName }
         *     
         */
        public void setDatatype(QName value) {
            this.datatype = value;
        }

        /**
         * Gets the value of the property property.
         * 
         * @return
         *     possible object is
         *     {@link QName }
         *     
         */
        public QName getProperty() {
            return property;
        }

        /**
         * Sets the value of the property property.
         * 
         * @param value
         *     allowed object is
         *     {@link QName }
         *     
         */
        public void setProperty(QName value) {
            this.property = value;
        }

    }

}
