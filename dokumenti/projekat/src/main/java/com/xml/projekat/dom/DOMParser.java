package com.xml.projekat.dom;

import static org.apache.xerces.jaxp.JAXPConstants.JAXP_SCHEMA_LANGUAGE;
import static org.apache.xerces.jaxp.JAXPConstants.W3C_XML_SCHEMA;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

import com.xml.projekat.model.Adresa;
import com.xml.projekat.model.Izbor;
import com.xml.projekat.model.NazivOdluka;
import com.xml.projekat.model.Obavestenje;
import com.xml.projekat.model.PObavestenje;
import com.xml.projekat.model.PZahtev;
import com.xml.projekat.model.PZalbaCutanje;
import com.xml.projekat.model.PZalbaOdluke;
import com.xml.projekat.model.Podnosilac;
import com.xml.projekat.model.Resenje;
import com.xml.projekat.model.TTekst;
import com.xml.projekat.model.TZaglavlje;
import com.xml.projekat.model.Zahtev;
import com.xml.projekat.model.ZalbaCutanje;
import com.xml.projekat.model.ZalbaOdluke;
import com.xml.projekat.rdf.FusekiReader;;

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

	public Zahtev parseZahtev(Document document) {

		String drugiPodaciZaKontakt = document.getElementsByTagName("drugi_podaci_za_kontakt").item(0).getTextContent();
		String nazivOrganaVlasti = document.getElementsByTagName("naziv_organa_vlasti").item(0).getTextContent();
		String sedisteOrgana = document.getElementsByTagName("sediste_organa").item(0).getTextContent();
		String naslov = document.getElementsByTagName("naslov").item(0).getTextContent();
		String trazeneInformacije = document.getElementsByTagName("trazene_informacije").item(0).getTextContent();
		String datum = document.getElementsByTagName("datum").item(0).getTextContent();
		String mesto = document.getElementsByTagName("mesto").item(0).getTextContent();
		NodeList fusnote = document.getElementsByTagName("fusnota");
		ArrayList<String> fusnoteLista = new ArrayList<String>();
		for (int i = 0; i < fusnote.getLength(); i++) {
			fusnoteLista.add(fusnote.item(i).getTextContent());
		}

		NodeList paragrafi = document.getElementsByTagName("p");
		ArrayList<PZahtev> paragrafiLista = new ArrayList<PZahtev>();
		for (int i = 0; i < paragrafi.getLength(); i++) {
			String paragrafTekst = ((Element) paragrafi.item(i)).getFirstChild().getTextContent();

			NodeList izbori = paragrafi.item(i).getChildNodes();
			ArrayList<Izbor> izborLista = new ArrayList<Izbor>();
			if (izbori.getLength() > 1) {

				NodeList izbor = izbori.item(1).getChildNodes();
				for (int j = 0; j < izbor.getLength(); j++) {
					if (izbor.item(j) instanceof Element) {
						int broj = Integer.parseInt(izbor.item(j).getAttributes().getNamedItem("broj").getTextContent());
						String tekst = izbor.item(j).getFirstChild().getTextContent();
						HashMap<Integer, String> podizboriMapa = new HashMap<Integer, String>();
						String drugiNacin = null;

						NodeList podizboriAllChildren = izbor.item(j).getChildNodes();
						Node podizbori = podizboriAllChildren.item(1);

						if (podizbori != null) {
							Element podizboriEl = (Element) podizbori;
							NodeList podizbori2 = podizboriEl.getElementsByTagName("podizbor");
							for (int k = 0; k < podizbori2.getLength(); k++) {
								int podizborBroj = Integer.parseInt(podizbori2.item(k).getAttributes().getNamedItem("broj").getTextContent());
								String podizborString = podizbori2.item(k).getTextContent();
								podizboriMapa.put(podizborBroj, podizborString);
							}

							drugiNacin = podizboriEl.getElementsByTagName("drugi_nacin").item(0).getTextContent();

						}

						Izbor izborModel = new Izbor(broj, tekst, podizboriMapa, drugiNacin);
						izborLista.add(izborModel);
					}
				}

			}
			PZahtev paragraf = new PZahtev(paragrafTekst, izborLista);
			paragrafiLista.add(paragraf);
		}

		String ulica = document.getElementsByTagName("ulica").item(0).getTextContent();
		String broj = document.getElementsByTagName("broj").item(0).getTextContent();
		String grad = document.getElementsByTagName("grad").item(0).getTextContent();
		Adresa adresa = new Adresa(ulica, broj, grad);

		String ime = document.getElementsByTagName("ime").item(0).getTextContent();
		String prezime = document.getElementsByTagName("prezime").item(0).getTextContent();
		String nazivFirme = document.getElementsByTagName("naziv_firme").item(0).getTextContent();
		Podnosilac podnosilac = new Podnosilac(ime, prezime, nazivFirme);

		Zahtev z = new Zahtev(podnosilac, adresa, drugiPodaciZaKontakt, nazivOrganaVlasti, sedisteOrgana, naslov,
				paragrafiLista, trazeneInformacije, datum, mesto, fusnoteLista);
		System.out.println(z);
		return z;
	}

	public Resenje parseResenje(Document document) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("MM.dd.yyyy.");

		String opisPostupka = document.getElementsByTagName("opis_postupka").item(0).getTextContent();

		String brojResenja = document.getElementsByTagName("broj_resenja").item(0).getTextContent();
		String datumString = document.getElementsByTagName("datum").item(0).getTextContent();

		Date datum = formatter.parse(datumString.replaceAll("\\s", ""));
		TZaglavlje zaglavlje = new TZaglavlje(brojResenja, datum);

		String nazivResenja = document.getElementsByTagName("naziv_resenja").item(0).getChildNodes().item(0)
				.getTextContent();
		String odluka = document.getElementsByTagName("odluka").item(0).getTextContent();
		NazivOdluka nazivOdluka = new NazivOdluka(nazivResenja, odluka);

		String potpisPoverenika = document.getElementsByTagName("potpis_poverenika").item(0).getTextContent();

		String txtResenja = document.getElementsByTagName("tekst_resenja").item(0).getChildNodes().item(0)
				.getTextContent();
		String txtObrazlozenja = document.getElementsByTagName("tekst_obrazlozenja").item(0).getChildNodes().item(0)
				.getTextContent();

		ArrayList<String> paragrafiResenja = new ArrayList<String>();
		ArrayList<String> paragrafiObrazlozenja = new ArrayList<String>();

		NodeList paragrafi = document.getElementsByTagName("p");

		for (int i = 0; i < paragrafi.getLength(); i++) {
			if (paragrafi.item(i).getParentNode().equals(document.getElementsByTagName("tekst_resenja").item(0))) {
				paragrafiResenja.add(paragrafi.item(i).getTextContent());
			} else {
				paragrafiObrazlozenja.add(paragrafi.item(i).getTextContent());
			}
		}

		TTekst tekstResenja = new TTekst(txtResenja, paragrafiResenja);
		TTekst tekstObrazlozenja = new TTekst(txtObrazlozenja, paragrafiObrazlozenja);

		Resenje r = new Resenje(nazivOdluka, zaglavlje, opisPostupka, tekstResenja, tekstObrazlozenja,
				potpisPoverenika);
		System.out.println(r);
		return r;
	}

	public ZalbaOdluke parseZalbaOdluke(Document document) {
		String ime = document.getElementsByTagName("ime").item(0).getTextContent();
		String prezime = document.getElementsByTagName("prezime").item(0).getTextContent();
		String nazivFirme = document.getElementsByTagName("naziv_firme").item(0).getTextContent();
		Podnosilac podnosilac = new Podnosilac(ime, prezime, nazivFirme);

		String drugiPodaciZaKontakt = document.getElementsByTagName("drugi_podaci_za_kontakt").item(0).getTextContent();
		String nazivPoverenika = document.getElementsByTagName("naziv_poverenika").item(0).getTextContent();

		Element adresaElement = (Element) document.getElementsByTagName("adresa").item(0);
		String ulica = adresaElement.getElementsByTagName("ulica").item(0).getTextContent();
		String broj = adresaElement.getElementsByTagName("broj").item(0).getTextContent();
		String grad = adresaElement.getElementsByTagName("grad").item(0).getTextContent();
		Adresa adresa = new Adresa(ulica, broj, grad);

		Element sedisteElement = (Element) document.getElementsByTagName("sediste_poverenika").item(0);
		String ulicaSediste = sedisteElement.getElementsByTagName("ulica").item(0).getTextContent();
		String brojSediste = sedisteElement.getElementsByTagName("broj").item(0).getTextContent();
		String gradSediste = sedisteElement.getElementsByTagName("grad").item(0).getTextContent();
		Adresa adresaSediste = new Adresa(ulicaSediste, brojSediste, gradSediste);

		String naslov = document.getElementsByTagName("naslov").item(0).getTextContent();
		//String nazivOrganaVlasti = document.getElementsByTagName("naziv_organa").item(0).getTextContent();
		String nazivOrganaVlasti = "asdf";
		NodeList paragrafi = document.getElementsByTagName("p");
		ArrayList<PZalbaOdluke> paragrafiLista = new ArrayList<PZalbaOdluke>();
		for (int i = 0; i < paragrafi.getLength(); i++) {
			String paragrafTekst = paragrafi.item(i).getTextContent().trim();
			String datum = null;
			String razlog = null;
			String brojZalbe = null;
			String godinaOdbijanja = null;
			if (((Element) paragrafi.item(i)).getElementsByTagName("datum").item(0) != null)
				datum = ((Element) paragrafi.item(i)).getElementsByTagName("datum").item(0).getTextContent();
			if (((Element) paragrafi.item(i)).getElementsByTagName("razlog").item(0) != null)
				razlog = ((Element) paragrafi.item(i)).getElementsByTagName("razlog").item(0).getTextContent();
			if (((Element) paragrafi.item(i)).getElementsByTagName("broj_zalbe").item(0) != null)
				brojZalbe = ((Element) paragrafi.item(i)).getElementsByTagName("broj_zalbe").item(0).getTextContent();
			if (((Element) paragrafi.item(i)).getElementsByTagName("godina_odbijanja").item(0) != null)
				godinaOdbijanja = ((Element) paragrafi.item(i)).getElementsByTagName("godina_odbijanja").item(0)
						.getTextContent();

			PZalbaOdluke pzo = new PZalbaOdluke(paragrafTekst, datum, razlog, brojZalbe, godinaOdbijanja);
			paragrafiLista.add(pzo);

		}

		Element podaciDatumMesto = (Element) document.getElementsByTagName("podaci_o_trenutku").item(0);

		String datum = podaciDatumMesto.getElementsByTagName("datum").item(0).getTextContent();
		String mesto = podaciDatumMesto.getElementsByTagName("mesto").item(0).getTextContent();

		NodeList tacke = document.getElementsByTagName("tacka");
		ArrayList<String> tackeLista = new ArrayList<String>();
		for (int j = 0; j < tacke.getLength(); j++) {
			tackeLista.add(tacke.item(j).getTextContent());
		}

		ZalbaOdluke zalba = new ZalbaOdluke(podnosilac, adresa, drugiPodaciZaKontakt, nazivPoverenika, adresaSediste,
				naslov, nazivOrganaVlasti, paragrafiLista, mesto, datum, tackeLista);
		System.out.println(zalba);
		return zalba;
	}

	public ZalbaCutanje parseZalbaCutanje(Document document) {
		String ime = document.getElementsByTagName("ime").item(0).getTextContent();
		String prezime = document.getElementsByTagName("prezime").item(0).getTextContent();
		String nazivFirme = document.getElementsByTagName("naziv_firme").item(0).getTextContent();
		Podnosilac podnosilac = new Podnosilac(ime, prezime, nazivFirme);

		String drugiPodaciZaKontakt = document.getElementsByTagName("drugi_podaci_za_kontakt").item(0).getTextContent();
		String nazivPoverenika = document.getElementsByTagName("naziv_poverenika").item(0).getTextContent();

		Element adresaElement = (Element) document.getElementsByTagName("adresa").item(0);
		String ulica = adresaElement.getElementsByTagName("ulica").item(0).getTextContent();
		String broj = adresaElement.getElementsByTagName("broj").item(0).getTextContent();
		String grad = adresaElement.getElementsByTagName("grad").item(0).getTextContent();
		Adresa adresa = new Adresa(ulica, broj, grad);

		Element sedisteElement = (Element) document.getElementsByTagName("sediste_poverenika").item(0);
		String ulicaSediste = sedisteElement.getElementsByTagName("ulica").item(0).getTextContent();
		String brojSediste = sedisteElement.getElementsByTagName("broj").item(0).getTextContent();
		String gradSediste = sedisteElement.getElementsByTagName("grad").item(0).getTextContent();
		Adresa adresaSediste = new Adresa(ulicaSediste, brojSediste, gradSediste);

		String naslov = document.getElementsByTagName("naslov").item(0).getTextContent();

		NodeList paragrafi = document.getElementsByTagName("p");
		ArrayList<PZalbaCutanje> paragrafiLista = new ArrayList<PZalbaCutanje>();
		for (int i = 0; i < paragrafi.getLength(); i++) {
			String paragrafTekst = paragrafi.item(i).getTextContent().trim();
			if (((Element) paragrafi.item(i)).getElementsByTagName("datum").item(0) != null) {
				String datum = ((Element) paragrafi.item(i)).getElementsByTagName("datum").item(0).getTextContent();
				String podaciOZahtevuIInformacijama = ((Element) paragrafi.item(i))
						.getElementsByTagName("podaci_o_zahtevu_i_informacijama").item(0).getTextContent();
				PZalbaCutanje paragraf = new PZalbaCutanje(paragrafTekst, datum, podaciOZahtevuIInformacijama);
				paragrafiLista.add(paragraf);

			} else if (((Element) paragrafi.item(i)).getElementsByTagName("naziv_organa").item(0) != null) {
				String nazivOrgana = ((Element) paragrafi.item(i)).getElementsByTagName("naziv_organa").item(0)
						.getTextContent();
				PZalbaCutanje paragraf = new PZalbaCutanje(paragrafTekst, nazivOrgana);
				paragrafiLista.add(paragraf);
			} else {
				PZalbaCutanje paragraf = new PZalbaCutanje(paragrafTekst);
				paragrafiLista.add(paragraf);
			}

		}

		Element podaciDatumMesto = (Element) document.getElementsByTagName("podaci_o_vremenu_i_mestu_podnosenja_zalbe")
				.item(0);

		String datum = podaciDatumMesto.getElementsByTagName("datum").item(0).getTextContent();
		String mesto = podaciDatumMesto.getElementsByTagName("mesto").item(0).getTextContent();

		ZalbaCutanje zalba = new ZalbaCutanje(podnosilac, adresa, drugiPodaciZaKontakt, nazivPoverenika, adresaSediste,
				naslov, paragrafiLista, datum, mesto);
		System.out.println(zalba);
		return zalba;
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

		String nazivOrganaVlasti = document.getElementsByTagName("naziv_organa_vlasti").item(0).getTextContent();
		String sedisteOrgana = document.getElementsByTagName("sediste_organa").item(0).getTextContent();
		String datum = document.getElementsByTagName("datum").item(0).getTextContent();
		String naslov = document.getElementsByTagName("naslov").item(0).getTextContent();
		String brojPredmeta = document.getElementsByTagName("broj_predmeta").item(0).getTextContent();
		String mestoPecata = document.getElementsByTagName("mesto_pecata").item(0).getTextContent();

		String ulica = document.getElementsByTagName("ulica").item(0).getTextContent();
		String broj = document.getElementsByTagName("broj").item(0).getTextContent();
		String grad = document.getElementsByTagName("grad").item(0).getTextContent();
		Adresa adresa = new Adresa(ulica, broj, grad);

		String ime = document.getElementsByTagName("ime").item(0).getTextContent();
		String prezime = document.getElementsByTagName("prezime").item(0).getTextContent();
		String nazivFirme = document.getElementsByTagName("naziv_firme").item(0).getTextContent();
		Podnosilac podnosilac = new Podnosilac(ime, prezime, nazivFirme);

		NodeList dostavljenoXML = document.getElementsByTagName("dostavljeno");
		ArrayList<String> dostavljeno = new ArrayList<String>();
		for (int i = 0; i < dostavljenoXML.getLength(); i++) {
			dostavljeno.add(dostavljenoXML.item(i).getTextContent());
		}

		// jos paragrafi
		NodeList paragrafiXML = document.getElementsByTagName("p");
		ArrayList<PObavestenje> paragrafi = new ArrayList<PObavestenje>();

		for (int i = 0; i < paragrafiXML.getLength(); i++) {
			String text = paragrafiXML.item(i).getTextContent().trim();
			if (((Element) paragrafiXML.item(i)).getElementsByTagName("novcana_naknada").item(0) != null
					&& paragrafiXML.item(i) instanceof Element) {
				String novcanaNaknada = ((Element) paragrafiXML.item(i)).getElementsByTagName("novcana_naknada").item(0)
						.getTextContent();

				PObavestenje pObavestenje = new PObavestenje(text, novcanaNaknada);
				paragrafi.add(pObavestenje);
			} else if (((Element) paragrafiXML.item(i)).getElementsByTagName("godina").item(0) != null
					&& paragrafiXML.item(i) instanceof Element) {

				String godina = ((Element) paragrafiXML.item(i)).getElementsByTagName("godina").item(0)
						.getTextContent();
				String trazenaInformacija = ((Element) paragrafiXML.item(i)).getElementsByTagName("trazena_informacija")
						.item(0).getTextContent();
				String dan = ((Element) paragrafiXML.item(i)).getElementsByTagName("dan").item(0).getTextContent();
				String sati = ((Element) paragrafiXML.item(i)).getElementsByTagName("sati").item(0).getTextContent();
				String odSati = ((Element) paragrafiXML.item(i)).getElementsByTagName("od").item(0).getTextContent();
				String doSati = ((Element) paragrafiXML.item(i)).getElementsByTagName("do").item(0).getTextContent();
				String mesto = ((Element) paragrafiXML.item(i)).getElementsByTagName("mesto").item(0).getTextContent();
				String ulicaOb = ((Element) paragrafiXML.item(i)).getElementsByTagName("ulica").item(0)
						.getTextContent();
				String brojZgrade = ((Element) paragrafiXML.item(i)).getElementsByTagName("broj_zgrade").item(0)
						.getTextContent();
				String brojKancelarije = ((Element) paragrafiXML.item(i)).getElementsByTagName("broj_kancelarije")
						.item(0).getTextContent();

				PObavestenje pObavestenje = new PObavestenje(text, godina, trazenaInformacija, dan, sati, odSati,
						doSati, mesto, ulicaOb, brojZgrade, brojKancelarije);
				System.out.println(pObavestenje);
				paragrafi.add(pObavestenje);

			}
		}

		Obavestenje obavestenje = new Obavestenje(podnosilac, adresa, nazivOrganaVlasti, sedisteOrgana, dostavljeno,
				datum, naslov, brojPredmeta, paragrafi, mestoPecata);
		System.out.println(obavestenje);
		
		FusekiReader.executeQuery("/obavestenja");
		return obavestenje;
	}

	/**
	 * A recursive helper method for iterating over the elements of a DOM tree.
	 * 
	 * @param node
	 *            current node
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