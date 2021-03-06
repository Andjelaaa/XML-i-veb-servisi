package com.xml.projekat.dom;

import static org.apache.xerces.jaxp.JAXPConstants.JAXP_SCHEMA_LANGUAGE;
import static org.apache.xerces.jaxp.JAXPConstants.W3C_XML_SCHEMA;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.springframework.stereotype.Component;
import org.w3c.dom.Attr;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Entity;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.ProcessingInstruction;
import org.w3c.dom.Text;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.xml.projekat.model.NazivOdluka;
import com.xml.projekat.model.Resenje;
import com.xml.projekat.model.TTekst;
import com.xml.projekat.model.TZaglavlje;
import com.xml.projekat.model.*;
import com.xml.projekat.rdf.FusekiReader;

@Component
public class DOMParser {

	private DocumentBuilderFactory factory;
	private Document document;

	public DOMParser() {
		factory = DocumentBuilderFactory.newInstance();

		factory.setNamespaceAware(true);
		factory.setIgnoringComments(true);
		factory.setIgnoringElementContentWhitespace(true);

		factory.setAttribute(JAXP_SCHEMA_LANGUAGE, W3C_XML_SCHEMA);

	}

	public Document buildDocumentFromText(String text) throws SAXException, IOException, ParserConfigurationException {
		DocumentBuilder builder = factory.newDocumentBuilder();
		return builder.parse(new InputSource(new StringReader(text)));
	}

	public Document buildDocumentFromFile(String filePath)
			throws SAXException, IOException, ParserConfigurationException {
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(new File(filePath));
		return doc;
	}

	public String getDocumentAsString(Document document) throws TransformerException {
		StringWriter sw = new StringWriter();
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer = tf.newTransformer();

		transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
		transformer.setOutputProperty(OutputKeys.METHOD, "xml");
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

		transformer.transform(new DOMSource(document), new StreamResult(sw));

		return sw.toString();

	}

	public Resenje parseResenje(Document document) throws ParseException, IOException {

		String uri = document.getElementsByTagName("d:URI").item(0).getTextContent();
		String uriZalbaCutanje = document.getElementsByTagName("d:zalba_cutanje_uri").item(0).getTextContent();
		String uriZalbaOdluke = document.getElementsByTagName("d:zalba_odluke_uri").item(0).getTextContent();

		String korisnickoIme = document.getElementsByTagName("d:korisnicko_ime").item(0).getTextContent();


		String opisPostupka = document.getElementsByTagName("d:opis_postupka").item(0).getTextContent();

		String brojResenja = document.getElementsByTagName("d:broj_resenja").item(0).getTextContent();
		String datumString = document.getElementsByTagName("d:datum").item(0).getTextContent();
//
//		System.out.println(datumString);
//		String datum = formatter.format(datumString.replaceAll("\\s", ""));
		TZaglavlje zaglavlje = new TZaglavlje(brojResenja, datumString);

		String nazivResenja = document.getElementsByTagName("d:naziv_resenja").item(0).getChildNodes().item(0)
				.getTextContent();
		String odluka = document.getElementsByTagName("d:odluka").item(0).getTextContent();
		NazivOdluka nazivOdluka = new NazivOdluka(nazivResenja, odluka);

		String potpisPoverenika = document.getElementsByTagName("d:potpis_poverenika").item(0).getTextContent();

		String txtResenja = document.getElementsByTagName("d:tekst_resenja").item(0).getChildNodes().item(0)
				.getTextContent();
		String txtObrazlozenja = document.getElementsByTagName("d:tekst_obrazlozenja").item(0).getChildNodes().item(0)
				.getTextContent();

		ArrayList<String> paragrafiResenja = new ArrayList<String>();
		ArrayList<String> paragrafiObrazlozenja = new ArrayList<String>();

		NodeList paragrafi = document.getElementsByTagName("d:p");

		for (int i = 0; i < paragrafi.getLength(); i++) {
			if (paragrafi.item(i).getParentNode().equals(document.getElementsByTagName("d:tekst_resenja").item(0))) {
				paragrafiResenja.add(paragrafi.item(i).getTextContent());
			} else {
				paragrafiObrazlozenja.add(paragrafi.item(i).getTextContent());
			}
		}

		TTekst tekstResenja = new TTekst(txtResenja, paragrafiResenja);
		TTekst tekstObrazlozenja = new TTekst(txtObrazlozenja, paragrafiObrazlozenja);

		Resenje r = new Resenje(nazivOdluka, zaglavlje, opisPostupka, tekstResenja, tekstObrazlozenja, potpisPoverenika,
				korisnickoIme, uri, uriZalbaCutanje, uriZalbaOdluke);
		System.out.println(r);
		return r;
	}

	public Zahtev parseZahtev(Document document) throws IOException {

		String uri = document.getElementsByTagName("d:URI").item(0).getTextContent();
		String drugiPodaciZaKontakt = document.getElementsByTagName("d:drugi_podaci_za_kontakt").item(0)
				.getTextContent();
		String nazivOrganaVlasti = document.getElementsByTagName("d:naziv_organa_vlasti").item(0).getTextContent();
		String sedisteOrgana = document.getElementsByTagName("d:sediste_organa").item(0).getTextContent();
		String naslov = document.getElementsByTagName("d:naslov").item(0).getTextContent();
		String trazeneInformacije = document.getElementsByTagName("d:trazene_informacije").item(0).getTextContent();
		String datum = document.getElementsByTagName("d:datum").item(0).getTextContent();
		String mesto = document.getElementsByTagName("d:mesto").item(0).getTextContent();
		NodeList fusnote = document.getElementsByTagName("d:fusnota");
		ArrayList<String> fusnoteLista = new ArrayList<String>();
		for (int i = 0; i < fusnote.getLength(); i++) {
			fusnoteLista.add(fusnote.item(i).getTextContent());
		}

		NodeList paragrafi = document.getElementsByTagName("d:p");
		ArrayList<PZahtev> paragrafiLista = new ArrayList<PZahtev>();
		for (int i = 0; i < paragrafi.getLength(); i++) {
			String paragrafTekst = ((Element) paragrafi.item(i)).getFirstChild().getTextContent();

			NodeList izbori = paragrafi.item(i).getChildNodes();
			ArrayList<Izbor> izborLista = new ArrayList<Izbor>();
			if (izbori.getLength() > 1) {

				NodeList izbor = izbori.item(1).getChildNodes();
				for (int j = 0; j < izbor.getLength(); j++) {
					if (izbor.item(j) instanceof Element) {
						String broj = izbor.item(j).getAttributes().getNamedItem("d:broj").getTextContent();
						String tekst = izbor.item(j).getFirstChild().getTextContent();
						HashMap<String, String> podizboriMapa = new HashMap<String, String>();
						String drugiNacin = null;

						NodeList podizboriAllChildren = izbor.item(j).getChildNodes();
						Node podizbori = podizboriAllChildren.item(1);

						if (podizbori != null) {
							Element podizboriEl = (Element) podizbori;
							NodeList podizbori2 = podizboriEl.getElementsByTagName("d:podizbor");
							for (int k = 0; k < podizbori2.getLength(); k++) {
								String podizborBroj = podizbori2.item(k).getAttributes().getNamedItem("d:broj")
										.getTextContent();
								String podizborString = podizbori2.item(k).getTextContent();
								podizboriMapa.put(podizborBroj, podizborString);
							}

						}

						Izbor izborModel = new Izbor(broj, tekst, podizboriMapa, drugiNacin);
						izborLista.add(izborModel);
					}
				}

			}
			PZahtev paragraf = new PZahtev(paragrafTekst, izborLista);
			paragrafiLista.add(paragraf);
		}

		String ulica = document.getElementsByTagName("d:ulica").item(0).getTextContent();
		String broj = document.getElementsByTagName("d:broj").item(0).getTextContent();
		String grad = document.getElementsByTagName("d:grad").item(0).getTextContent();
		Adresa adresa = new Adresa(ulica, broj, grad);

		String ime = document.getElementsByTagName("d:ime").item(0).getTextContent();
		String prezime = document.getElementsByTagName("d:prezime").item(0).getTextContent();
		String nazivFirme = document.getElementsByTagName("d:naziv_firme").item(0).getTextContent();
		String korisnickoIme = document.getElementsByTagName("d:korisnicko_ime").item(0).getTextContent();
		Podnosilac podnosilac = new Podnosilac(ime, prezime, nazivFirme, korisnickoIme);

		Zahtev z = new Zahtev(podnosilac, adresa, drugiPodaciZaKontakt, nazivOrganaVlasti, sedisteOrgana, naslov,
				paragrafiLista, trazeneInformacije, datum, mesto, fusnoteLista);
		z.setURI(uri);
		// System.out.println(z);
		FusekiReader.executeQuery("/zahtevi");
		return z;
	}

	public void printElement() {

		System.out.println("Prikaz sadržaja DOM stabla parsiranog XML dokumenta.");
		Scanner scanner = new Scanner(System.in);
		String elementName, attrName, choice = "";
		Element element;

		while (!choice.equals("*")) {

			System.out.println(
					"\n[INPUT] Unesite 0 - za prikaz celog dokumenta, 1 - prikaz elemenata, 2 - prikaz atributa, * - kraj: ");
			choice = scanner.next();

			if (choice.equals("0")) {
				printNode(document);

			} else if (choice.equals("1")) {

				System.out.print("\n[INPUT] Unesite naziv elementa: ");
				elementName = scanner.next();
				NodeList nodes = document.getElementsByTagName(elementName);

				System.out.println("\nPronađeno " + nodes.getLength() + " elemenata. ");

				for (int i = 0; i < nodes.getLength(); i++)
					printNode(nodes.item(i));

			} else if (choice.equals("2")) {

				System.out.print("\n[INPUT] Unesite naziv elementa: ");
				elementName = scanner.next();

				System.out.print("\n[INPUT] Unesite naziv atributa: ");
				attrName = scanner.next();

				NodeList nodes = document.getElementsByTagName(elementName);

				System.out.println("\nPronađeno " + nodes.getLength() + " \"" + elementName + "\" elemenata.");

				for (int i = 0; i < nodes.getLength(); i++) {

					element = (Element) nodes.item(i);

					if (!element.getAttribute(attrName).equals("")) {
						System.out.println("\n" + (i + 1) + ". element ima vrednost atributa \"" + attrName + "\": "
								+ element.getAttribute(attrName) + ".");
					} else {
						System.out.println("\n" + (i + 1) + ". element \"" + elementName + "\" ne poseduje atribut \""
								+ attrName + "\".");
					}
				}

			} else if (choice.equals("*")) {
				break;
			} else {
				System.out.println("Nepoznata komanda.");
			}
		}

		scanner.close();
		System.out.println("[INFO] Kraj.");
	}

	public Obavestenje parseObavestenje(Document document) throws IOException {
		String uri = document.getElementsByTagName("d:URI").item(0).getTextContent();
		String uriZahteva = document.getElementsByTagName("d:zahtev_uri").item(0).getTextContent();
		String nazivOrganaVlasti = document.getElementsByTagName("d:naziv_organa_vlasti").item(0).getTextContent();
		String sedisteOrgana = document.getElementsByTagName("d:sediste_organa").item(0).getTextContent();
		String datum = document.getElementsByTagName("d:datum").item(0).getTextContent();
		String naslov = document.getElementsByTagName("d:naslov").item(0).getTextContent();
		String brojPredmeta = document.getElementsByTagName("d:broj_predmeta").item(0).getTextContent();
		String mestoPecata = document.getElementsByTagName("d:mesto_pecata").item(0).getTextContent();

		String ulica = document.getElementsByTagName("d:ulica").item(0).getTextContent();
		String broj = document.getElementsByTagName("d:broj").item(0).getTextContent();
		String grad = document.getElementsByTagName("d:grad").item(0).getTextContent();
		Adresa adresa = new Adresa(ulica, broj, grad);

		String ime = document.getElementsByTagName("d:ime").item(0).getTextContent();
		String prezime = document.getElementsByTagName("d:prezime").item(0).getTextContent();
		String nazivFirme = document.getElementsByTagName("d:naziv_firme").item(0).getTextContent();
		String korisnickoIme = document.getElementsByTagName("d:korisnicko_ime").item(0).getTextContent();
		Podnosilac podnosilac = new Podnosilac(ime, prezime, nazivFirme, korisnickoIme);

		NodeList dostavljenoXML = document.getElementsByTagName("d:dostavljeno");
		ArrayList<String> dostavljeno = new ArrayList<String>();
		for (int i = 0; i < dostavljenoXML.getLength(); i++) {
			dostavljeno.add(dostavljenoXML.item(i).getTextContent());
		}

		// jos paragrafi
		NodeList paragrafiXML = document.getElementsByTagName("d:p");
		ArrayList<PObavestenje> paragrafi = new ArrayList<PObavestenje>();

		for (int i = 0; i < paragrafiXML.getLength(); i++) {
			String text = paragrafiXML.item(i).getTextContent().trim();
			if (((Element) paragrafiXML.item(i)).getElementsByTagName("d:novcana_naknada").item(0) != null
					&& paragrafiXML.item(i) instanceof Element) {
				String novcanaNaknada = ((Element) paragrafiXML.item(i)).getElementsByTagName("d:novcana_naknada")
						.item(0).getTextContent();

				PObavestenje pObavestenje = new PObavestenje(text, novcanaNaknada);
				paragrafi.add(pObavestenje);
			} else if (((Element) paragrafiXML.item(i)).getElementsByTagName("d:godina").item(0) != null
					&& paragrafiXML.item(i) instanceof Element) {

				String godina = ((Element) paragrafiXML.item(i)).getElementsByTagName("d:godina").item(0)
						.getTextContent();
				String trazenaInformacija = ((Element) paragrafiXML.item(i))
						.getElementsByTagName("d:trazena_informacija").item(0).getTextContent();
				String dan = ((Element) paragrafiXML.item(i)).getElementsByTagName("d:dan").item(0).getTextContent();
				String sati = ((Element) paragrafiXML.item(i)).getElementsByTagName("d:sati").item(0).getTextContent();
				String odSati = ((Element) paragrafiXML.item(i)).getElementsByTagName("d:od").item(0).getTextContent();
				String doSati = ((Element) paragrafiXML.item(i)).getElementsByTagName("d:do").item(0).getTextContent();
				String mesto = ((Element) paragrafiXML.item(i)).getElementsByTagName("d:mesto").item(0)
						.getTextContent();
				String ulicaOb = ((Element) paragrafiXML.item(i)).getElementsByTagName("d:ulica").item(0)
						.getTextContent();
				String brojZgrade = ((Element) paragrafiXML.item(i)).getElementsByTagName("d:broj_zgrade").item(0)
						.getTextContent();
				String brojKancelarije = ((Element) paragrafiXML.item(i)).getElementsByTagName("d:broj_kancelarije")
						.item(0).getTextContent();

				PObavestenje pObavestenje = new PObavestenje(text, godina, trazenaInformacija, dan, sati, odSati,
						doSati, mesto, ulicaOb, brojZgrade, brojKancelarije);

				System.out.println(pObavestenje);
				paragrafi.add(pObavestenje);

			}
		}

		Obavestenje obavestenje = new Obavestenje(podnosilac, adresa, nazivOrganaVlasti, sedisteOrgana, dostavljeno,
				datum, naslov, brojPredmeta, paragrafi, mestoPecata);
		obavestenje.setURI(uri);
		obavestenje.setZahtevURI(uriZahteva);
		System.out.println(obavestenje);

		FusekiReader.executeQuery("/obavestenja");
		return obavestenje;
	}

	public Izvestaj parseIzvestaj(Document document) {
		String godina = document.getElementsByTagName("d:godina").item(0).getTextContent();
		String brPodnetihZahteva = document.getElementsByTagName("d:br_podnetih_zahteva").item(0).getTextContent();
		String brOdbijenihZahteva = document.getElementsByTagName("d:br_odbijenih_zahteva").item(0).getTextContent();
		String brZalbi = document.getElementsByTagName("d:br_zalbi").item(0).getTextContent();
		Izvestaj izvestaj = new Izvestaj(godina, brPodnetihZahteva, brOdbijenihZahteva, brZalbi);

		return izvestaj;
	}

	/**
	 * A recursive helper method for iterating over the elements of a DOM tree.
	 * 
	 * @param node current node
	 */
	private void printNode(Node node) {

		// Uslov za izlazak iz rekurzije
		if (node == null)
			return;

		// Ispis uopštenih podataka o čvoru iz Node interfejsa
		// printNodeDetails(node, indent);

		// Ako je upitanju dokument čvor (korenski element)
		if (node instanceof Document) {

			System.out.println("START_DOCUMENT");

			// Rekurzivni poziv za prikaz korenskog elementa
			Document doc = (Document) node;
			printNode(doc.getDocumentElement());
		} else if (node instanceof Element) {

			Element element = (Element) node;

			System.out.print("START_ELEMENT: " + element.getTagName());

			// Preuzimanje liste atributa
			NamedNodeMap attributes = element.getAttributes();

			if (attributes.getLength() > 0) {

				System.out.print(", ATTRIBUTES: ");

				for (int i = 0; i < attributes.getLength(); i++) {
					Node attribute = attributes.item(i);
					printNode(attribute);
					if (i < attributes.getLength() - 1)
						System.out.print(", ");
				}
			}

			System.out.println();

			// Prikaz svakog od child nodova, rekurzivnim pozivom
			NodeList children = element.getChildNodes();

			if (children != null) {
				for (int i = 0; i < children.getLength(); i++) {
					Node aChild = children.item(i);
					printNode(aChild);
				}
			}
		}
		// Za naredne čvorove nema rekurzivnog poziva jer ne mogu imati podelemente
		else if (node instanceof Attr) {

			Attr attr = (Attr) node;
			System.out.print(attr.getName() + "=" + attr.getValue());

		} else if (node instanceof Text) {
			Text text = (Text) node;

			if (text.getTextContent().trim().length() > 0)
				System.out.println("CHARACTERS: " + text.getTextContent().trim());
		} else if (node instanceof CDATASection) {
			System.out.println("CDATA: " + node.getNodeValue());
		} else if (node instanceof Comment) {
			System.out.println("COMMENT: " + node.getNodeValue());
		} else if (node instanceof ProcessingInstruction) {
			System.out.print("PROCESSING INSTRUCTION: ");

			ProcessingInstruction instruction = (ProcessingInstruction) node;
			System.out.print("data: " + instruction.getData());
			System.out.println(", target: " + instruction.getTarget());
		} else if (node instanceof Entity) {
			Entity entity = (Entity) node;
			System.out.println("ENTITY: " + entity.getNotationName());
		}
	}

}