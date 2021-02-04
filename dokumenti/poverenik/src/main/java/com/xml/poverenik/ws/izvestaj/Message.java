
package com.xml.poverenik.ws.izvestaj;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element name="godina" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="br_podnetih_zahteva" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="br_odbijenih_zahteva" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="br_zalbi" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
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
    "godina",
    "brPodnetihZahteva",
    "brOdbijenihZahteva",
    "brZalbi"
})
@XmlRootElement(name = "message")
public class Message {

    @XmlElement(required = true)
    protected String godina;
    @XmlElement(name = "br_podnetih_zahteva", required = true)
    protected String brPodnetihZahteva;
    @XmlElement(name = "br_odbijenih_zahteva", required = true)
    protected String brOdbijenihZahteva;
    @XmlElement(name = "br_zalbi", required = true)
    protected String brZalbi;

    public Message(String godina2, String brPodnetihZahteva2, String brOdbijenihZahteva2, String brZalbi2) {
		this.godina = godina2;
		this.brPodnetihZahteva = brPodnetihZahteva2;
		this.brOdbijenihZahteva = brOdbijenihZahteva2;
		this.brZalbi = brZalbi2;
	}

	public Message() {
		// TODO Auto-generated constructor stub
	}

	/**
     * Gets the value of the godina property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGodina() {
        return godina;
    }

    /**
     * Sets the value of the godina property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGodina(String value) {
        this.godina = value;
    }

    /**
     * Gets the value of the brPodnetihZahteva property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBrPodnetihZahteva() {
        return brPodnetihZahteva;
    }

    /**
     * Sets the value of the brPodnetihZahteva property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBrPodnetihZahteva(String value) {
        this.brPodnetihZahteva = value;
    }

    /**
     * Gets the value of the brOdbijenihZahteva property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBrOdbijenihZahteva() {
        return brOdbijenihZahteva;
    }

    /**
     * Sets the value of the brOdbijenihZahteva property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBrOdbijenihZahteva(String value) {
        this.brOdbijenihZahteva = value;
    }

    /**
     * Gets the value of the brZalbi property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBrZalbi() {
        return brZalbi;
    }

    /**
     * Sets the value of the brZalbi property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBrZalbi(String value) {
        this.brZalbi = value;
    }

}
