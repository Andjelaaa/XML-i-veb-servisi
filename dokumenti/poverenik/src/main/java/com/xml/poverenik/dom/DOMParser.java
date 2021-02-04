package com.xml.poverenik.dom;

import static org.apache.xerces.jaxp.JAXPConstants.JAXP_SCHEMA_LANGUAGE;
import static org.apache.xerces.jaxp.JAXPConstants.W3C_XML_SCHEMA;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import com.xml.poverenik.model.Adresa;
import com.xml.poverenik.model.NazivOdluka;
import com.xml.poverenik.model.PZalbaCutanje;
import com.xml.poverenik.model.PZalbaOdluke;
import com.xml.poverenik.model.Podnosilac;
import com.xml.poverenik.model.Resenje;
import com.xml.poverenik.model.TTekst;
import com.xml.poverenik.model.TZaglavlje;
import com.xml.poverenik.model.ZalbaCutanje;
import com.xml.poverenik.model.ZalbaOdluke;
import com.xml.poverenik.rdf.FusekiReader;
import com.xml.poverenik.ws.izvestaj.Message;

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
	public Message parseIzvestaj(Document document) {
		String godina = document.getElementsByTagName("d:godina").item(0).getTextContent();
		String brPodnetihZahteva = document.getElementsByTagName("d:br_podnetih_zahteva").item(0).getTextContent();
		String brOdbijenihZahteva = document.getElementsByTagName("d:br_odbijenih_zahteva").item(0).getTextContent();
		String brZalbi = document.getElementsByTagName("d:br_zalbi").item(0).getTextContent();
		Message izvestaj = new Message(godina, brPodnetihZahteva, brOdbijenihZahteva, brZalbi);
		
		return izvestaj;
	}
	public Resenje parseResenje(Document document) throws ParseException, IOException {
		
		String uri = document.getElementsByTagName("d:URI").item(0).getTextContent();
		String uriZalbaCutanje = document.getElementsByTagName("d:zalba_cutanje_uri").item(0).getTextContent();
		String uriZalbaOdluke = document.getElementsByTagName("d:zalba_odluke_uri").item(0).getTextContent();

		String korisnickoIme = document.getElementsByTagName("d:korisnicko_ime").item(0).getTextContent();
		
		SimpleDateFormat formatter = new SimpleDateFormat("MM.dd.yyyy.");

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

		Resenje r = new Resenje(nazivOdluka, zaglavlje, opisPostupka, tekstResenja, tekstObrazlozenja,
				potpisPoverenika,korisnickoIme, uri, uriZalbaCutanje, uriZalbaOdluke);
		System.out.println(r);
		FusekiReader.executeQuery("/resenja");
		return r;
	}

	public ZalbaOdluke parseZalbaOdluke(Document document) throws IOException {
		
		String uri = document.getElementsByTagName("d:URI").item(0).getTextContent();
		String uriZahteva = document.getElementsByTagName("d:zahtev_uri").item(0).getTextContent();
		
		
		String ime = document.getElementsByTagName("d:ime").item(0).getTextContent();
		String prezime = document.getElementsByTagName("d:prezime").item(0).getTextContent();
		String nazivFirme = document.getElementsByTagName("d:naziv_firme").item(0).getTextContent();
		String korisnickoIme = document.getElementsByTagName("d:korisnicko_ime").item(0).getTextContent();
		Podnosilac podnosilac = new Podnosilac(ime, prezime, nazivFirme, korisnickoIme);

		String drugiPodaciZaKontakt = document.getElementsByTagName("d:drugi_podaci_za_kontakt").item(0).getTextContent();
		String nazivPoverenika = document.getElementsByTagName("d:naziv_poverenika").item(0).getTextContent();

		Element adresaElement = (Element) document.getElementsByTagName("d:adresa").item(0);
		String ulica = adresaElement.getElementsByTagName("d:ulica").item(0).getTextContent();
		String broj = adresaElement.getElementsByTagName("d:broj").item(0).getTextContent();
		String grad = adresaElement.getElementsByTagName("d:grad").item(0).getTextContent();
		Adresa adresa = new Adresa(ulica, broj, grad);

		Element sedisteElement = (Element) document.getElementsByTagName("d:sediste_poverenika").item(0);
		String ulicaSediste = sedisteElement.getElementsByTagName("d:ulica").item(0).getTextContent();
		String brojSediste = sedisteElement.getElementsByTagName("d:broj").item(0).getTextContent();
		String gradSediste = sedisteElement.getElementsByTagName("d:grad").item(0).getTextContent();
		Adresa adresaSediste = new Adresa(ulicaSediste, brojSediste, gradSediste);

		String naslov = document.getElementsByTagName("d:naslov").item(0).getTextContent();
		//String nazivOrganaVlasti = document.getElementsByTagName("naziv_organa").item(0).getTextContent();
		String nazivOrganaVlasti = "asdf";
		NodeList paragrafi = document.getElementsByTagName("d:p");
		ArrayList<PZalbaOdluke> paragrafiLista = new ArrayList<PZalbaOdluke>();
		for (int i = 0; i < paragrafi.getLength(); i++) {
			String paragrafTekst = paragrafi.item(i).getTextContent().trim();
			String datum = null;
			String razlog = null;
			String brojZalbe = null;
			String godinaOdbijanja = null;
			if (((Element) paragrafi.item(i)).getElementsByTagName("d:datum").item(0) != null)
				datum = ((Element) paragrafi.item(i)).getElementsByTagName("d:datum").item(0).getTextContent();
			if (((Element) paragrafi.item(i)).getElementsByTagName("d:razlog").item(0) != null)
				razlog = ((Element) paragrafi.item(i)).getElementsByTagName("razlog").item(0).getTextContent();
			if (((Element) paragrafi.item(i)).getElementsByTagName("d:broj_zalbe").item(0) != null)
				brojZalbe = ((Element) paragrafi.item(i)).getElementsByTagName("d:broj_zalbe").item(0).getTextContent();
			if (((Element) paragrafi.item(i)).getElementsByTagName("d:godina_odbijanja").item(0) != null)
				godinaOdbijanja = ((Element) paragrafi.item(i)).getElementsByTagName("d:godina_odbijanja").item(0)
						.getTextContent();

			PZalbaOdluke pzo = new PZalbaOdluke(paragrafTekst, datum, razlog, brojZalbe, godinaOdbijanja);
			paragrafiLista.add(pzo);

		}

		Element podaciDatumMesto = (Element) document.getElementsByTagName("d:podaci_o_trenutku").item(0);

		String datum = podaciDatumMesto.getElementsByTagName("d:datum").item(0).getTextContent();
		String mesto = podaciDatumMesto.getElementsByTagName("d:mesto").item(0).getTextContent();

		NodeList tacke = document.getElementsByTagName("d:tacka");
		ArrayList<String> tackeLista = new ArrayList<String>();
		for (int j = 0; j < tacke.getLength(); j++) {
			tackeLista.add(tacke.item(j).getTextContent());
		}

		ZalbaOdluke zalba = new ZalbaOdluke(uri, uriZahteva, podnosilac, adresa, drugiPodaciZaKontakt, nazivPoverenika, adresaSediste,
				naslov, nazivOrganaVlasti, paragrafiLista, mesto, datum, tackeLista);
		System.out.println(zalba);
		FusekiReader.executeQuery("/zalbeOdluke");
		return zalba;
	}

	public ZalbaCutanje parseZalbaCutanje(Document document) throws IOException {
		
		String uri = document.getElementsByTagName("d:URI").item(0).getTextContent();
		String uriZahteva = document.getElementsByTagName("d:zahtev_uri").item(0).getTextContent();
		
		
		String ime = document.getElementsByTagName("d:ime").item(0).getTextContent();
		String prezime = document.getElementsByTagName("d:prezime").item(0).getTextContent();
		String nazivFirme = document.getElementsByTagName("d:naziv_firme").item(0).getTextContent();
		String korisnickoIme = document.getElementsByTagName("d:korisnicko_ime").item(0).getTextContent();
		Podnosilac podnosilac = new Podnosilac(ime, prezime, nazivFirme, korisnickoIme);

		String drugiPodaciZaKontakt = document.getElementsByTagName("d:drugi_podaci_za_kontakt").item(0).getTextContent();
		String nazivPoverenika = document.getElementsByTagName("d:naziv_poverenika").item(0).getTextContent();

		Element adresaElement = (Element) document.getElementsByTagName("d:adresa").item(0);
		String ulica = adresaElement.getElementsByTagName("d:ulica").item(0).getTextContent();
		String broj = adresaElement.getElementsByTagName("d:broj").item(0).getTextContent();
		String grad = adresaElement.getElementsByTagName("d:grad").item(0).getTextContent();
		Adresa adresa = new Adresa(ulica, broj, grad);

		Element sedisteElement = (Element) document.getElementsByTagName("d:sediste_poverenika").item(0);
		String ulicaSediste = sedisteElement.getElementsByTagName("d:ulica").item(0).getTextContent();
		String brojSediste = sedisteElement.getElementsByTagName("d:broj").item(0).getTextContent();
		String gradSediste = sedisteElement.getElementsByTagName("d:grad").item(0).getTextContent();
		Adresa adresaSediste = new Adresa(ulicaSediste, brojSediste, gradSediste);

		String naslov = document.getElementsByTagName("d:naslov").item(0).getTextContent();

		NodeList paragrafi = document.getElementsByTagName("d:p");
		ArrayList<PZalbaCutanje> paragrafiLista = new ArrayList<PZalbaCutanje>();
		for (int i = 0; i < paragrafi.getLength(); i++) {
			String paragrafTekst = paragrafi.item(i).getTextContent().trim();
			if (((Element) paragrafi.item(i)).getElementsByTagName("d:datum").item(0) != null) {
				String datum = ((Element) paragrafi.item(i)).getElementsByTagName("d:datum").item(0).getTextContent();
				String podaciOZahtevuIInformacijama = ((Element) paragrafi.item(i))
						.getElementsByTagName("d:podaci_o_zahtevu_i_informacijama").item(0).getTextContent();
				PZalbaCutanje paragraf = new PZalbaCutanje(paragrafTekst, datum, podaciOZahtevuIInformacijama);
				paragrafiLista.add(paragraf);

			} else if (((Element) paragrafi.item(i)).getElementsByTagName("d:naziv_organa").item(0) != null) {
				String nazivOrgana = ((Element) paragrafi.item(i)).getElementsByTagName("d:naziv_organa").item(0)
						.getTextContent();
				PZalbaCutanje paragraf = new PZalbaCutanje(paragrafTekst, nazivOrgana);
				paragrafiLista.add(paragraf);
			} else {
				PZalbaCutanje paragraf = new PZalbaCutanje(paragrafTekst);
				paragrafiLista.add(paragraf);
			}

		}

		Element podaciDatumMesto = (Element) document.getElementsByTagName("d:podaci_o_vremenu_i_mestu_podnosenja_zalbe")
				.item(0);

		String datum = podaciDatumMesto.getElementsByTagName("d:datum").item(0).getTextContent();
		String mesto = podaciDatumMesto.getElementsByTagName("d:mesto").item(0).getTextContent();

		ZalbaCutanje zalba = new ZalbaCutanje(uri, uriZahteva, podnosilac, adresa, drugiPodaciZaKontakt, nazivPoverenika, adresaSediste,
				naslov, paragrafiLista, datum, mesto);
		System.out.println(zalba);
		FusekiReader.executeQuery("/zalbeCutanje");
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