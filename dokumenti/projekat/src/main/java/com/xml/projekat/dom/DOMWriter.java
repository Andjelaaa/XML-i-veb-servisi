package com.xml.projekat.dom;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xmldb.api.base.XMLDBException;

import com.xml.projekat.model.*;
import com.xml.projekat.rdf.FusekiWriter;
import com.xml.projekat.rdf.MetadataExtractor;
import com.xml.projekat.repository.*;

/**
 * 
 * Primer demonstrira metode API-ja za potrebe programskog kreiranja DOM stabla.
 * Pored programskog kreiranja DOM stabla, primer demonstrira i serijalizaciju
 * DOM stabla na proizvoljan stream (npr. FileOutputStream, System.out, itd.).
 *
 */
@Component
public class DOMWriter {

	private static String TARGET_NAMESPACE = "dokumenti";

	private static String XSI_NAMESPACE = "http://www.w3.org/2001/XMLSchema-instance";

	private static DocumentBuilderFactory factory;

	private static TransformerFactory transformerFactory;

	private Document document;
	
	@Autowired
	private MetadataExtractor metadataExtractor;

	@Autowired
	private ObavestenjeRepository obavestenjeRepository;
	
	@Autowired
	private ZahtevRepository zahtevRepository;
	
	/*
	 * Factory initialization static-block
	 */
	static {
		factory = DocumentBuilderFactory.newInstance();

		transformerFactory = TransformerFactory.newInstance();
	}

	/**
	 * Generates document object model for a given XML file.
	 */
	public void createDocument() {

		try {

			DocumentBuilder builder = factory.newDocumentBuilder();

			// Kreiranje novog dokumenta
			document = builder.newDocument();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Generates sample document object model programmatically using DOM API
	 * methods.
	 * @throws TransformerException 
	 * @throws IOException 
	 * @throws XMLDBException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */

	
	public String generateDOMObavestenje(Obavestenje ob) throws TransformerException, IOException, XMLDBException, ClassNotFoundException, InstantiationException, IllegalAccessException {

		// Kreiranje i postavljanje korenskog elementa
		createDocument();
		Element obavestenje = document.createElement("obavestenje");
		document.appendChild(obavestenje);
		
		obavestenje.setAttribute("xmlns", "http://www.w3.org/ns/rdfa#");
		obavestenje.setAttribute("xmlns:pred","http://www.ftn.uns.ac.rs/rdf/examples/predicate/");		
		obavestenje.setAttribute("xmlns:xs","http://www.w3.org/2001/XMLSchema#");

		Element uri = document.createElement("URI");
		uri.setAttribute("property","pred:URI");
		uri.setAttribute("datatype","xs:string");
		uri.appendChild(document.createTextNode(""+(obavestenjeRepository.getSize()+1)));
		
		obavestenje.appendChild(uri);
		
		Element zahtevUri = document.createElement("zahtev_uri");
		zahtevUri.setAttribute("property","pred:zahtev_uri");
		zahtevUri.setAttribute("datatype","xs:string");
		zahtevUri.appendChild(document.createTextNode(ob.getZahtevURI()));
		
		obavestenje.appendChild(zahtevUri);
		
		Element podnosilacZaheva = document.createElement("podnosilac_zahteva");

		obavestenje.appendChild(podnosilacZaheva);

		Element nazivPodnosioca = document.createElement("naziv_podnosioca");
		podnosilacZaheva.appendChild(nazivPodnosioca);

		Element ime = document.createElement("ime");
		ime.appendChild(document.createTextNode(ob.getPodnosilac().getIme()));
		nazivPodnosioca.appendChild(ime);
		Element prezime = document.createElement("prezime");
		prezime.appendChild(document.createTextNode(ob.getPodnosilac().getPrezime()));
		nazivPodnosioca.appendChild(prezime);
		Element nazivFirme = document.createElement("naziv_firme");
		nazivFirme.appendChild(document.createTextNode(ob.getPodnosilac().getNazivFirme()));
		nazivPodnosioca.appendChild(nazivFirme);
		Element korisnickoIme = document.createElement("korisnicko_ime");
		korisnickoIme.setAttribute("property","pred:korisnicko_ime");
		korisnickoIme.setAttribute("datatype","xs:string");
		korisnickoIme.appendChild(document.createTextNode(ob.getPodnosilac().getKorisnickoIme()));
		nazivPodnosioca.appendChild(korisnickoIme);
		
		Element adresa = document.createElement("adresa");

		podnosilacZaheva.appendChild(adresa);

		Element ulica = document.createElement("ulica");
		ulica.appendChild(document.createTextNode(ob.getAdresa().getUlica()));
		adresa.appendChild(ulica);
		Element broj = document.createElement("broj");
		broj.appendChild(document.createTextNode(ob.getAdresa().getBroj()));
		adresa.appendChild(broj);
		Element grad = document.createElement("grad");
		grad.appendChild(document.createTextNode(ob.getAdresa().getGrad()));
		adresa.appendChild(grad);

		// ------
		Element organVlasti = document.createElement("organ_vlasti");
		obavestenje.appendChild(organVlasti);

		Element nazivOrganaVlasti = document.createElement("naziv_organa_vlasti");
		nazivOrganaVlasti.setAttribute("property","pred:naziv_organa_vlasti");
		nazivOrganaVlasti.setAttribute("datatype","xs:string");
		nazivOrganaVlasti.appendChild(document.createTextNode(ob.getNazivOrganaVlasti()));
		organVlasti.appendChild(nazivOrganaVlasti);
		Element sedisteOrgana = document.createElement("sediste_organa");		
		sedisteOrgana.appendChild(document.createTextNode(ob.getSedisteOrgana()));
		organVlasti.appendChild(sedisteOrgana);

		// -----

		Element dostavljeno = document.createElement("dostavljeno");
		dostavljeno.appendChild(document.createTextNode("Достављено:"));
		obavestenje.appendChild(dostavljeno);

		Element listaPonudjenih = document.createElement("lista");
		dostavljeno.appendChild(listaPonudjenih);

		Element element1 = document.createElement("element");
		element1.appendChild(document.createTextNode("Именованом"));
		element1.setAttribute("broj", "1");
		listaPonudjenih.appendChild(element1);

		Element element2 = document.createElement("element");
		element2.appendChild(document.createTextNode("Архиви"));

		element2.setAttribute("broj", "2");
		listaPonudjenih.appendChild(element2);

		// -------
		Element tekstObavestenja = document.createElement("tekst_obavestenja");
		obavestenje.appendChild(tekstObavestenja);

		Element brojPredmeta = document.createElement("broj_predmeta");
		brojPredmeta.setAttribute("property","pred:brojPredmeta");
		brojPredmeta.setAttribute("datatype","xs:string");
		brojPredmeta.appendChild(document.createTextNode(ob.getBrojPredmeta()));
		tekstObavestenja.appendChild(brojPredmeta);

		Element datum = document.createElement("datum");
		datum.setAttribute("property","pred:datum");
		datum.setAttribute("datatype","xs:string");
		datum.appendChild(document.createTextNode(ob.getDatum()));
		tekstObavestenja.appendChild(datum);

		// ovde ide tekst za naslov
		Element naslov = document.createElement("naslov");
		naslov.appendChild(
				document.createTextNode(" ОБАВЕШТЕЊЕ" + "                о стављању на увид документа који садржи"
						+ "                тражену информацију и о изради копије"));
		tekstObavestenja.appendChild(naslov);

		// paragrafi
		Element paragraf = document.createElement("p");
		paragraf.appendChild(
				document.createTextNode("На основу члана 16. ст. 1. Закона о слободном приступу информацијама од јавног"
						+ "            значаја, поступајући по вашем захтеву за слободан приступ информацијама од\r\n"));
		tekstObavestenja.appendChild(paragraf);
		String naknada = null;
		for (Object o : ob.getParagrafi()) {
			if (o instanceof PObavestenje) {
				naknada = ((PObavestenje) o).getNovcanaNaknada();
				Element godina = document.createElement("godina");
				godina.appendChild(document.createTextNode(((PObavestenje) o).getGodina()));
				paragraf.appendChild(godina);

				paragraf.appendChild(document
						.createTextNode("год.,којим сте тражили увид у документ/е са информацијама о / у вези са:"));

				Element trazenaInformacija = document.createElement("trazena_informacija");
				trazenaInformacija.appendChild(document.createTextNode(((PObavestenje) o).getTrazenaInformacija()));
				paragraf.appendChild(trazenaInformacija);

				paragraf.appendChild(document.createTextNode("обавештавамо вас да дана "));

				Element dan = document.createElement("dan");
				dan.appendChild(document.createTextNode(((PObavestenje) o).getDan()));
				paragraf.appendChild(dan);

				paragraf.appendChild(document.createTextNode(", у"));

				Element sati = document.createElement("sati");
				sati.appendChild(document.createTextNode(((PObavestenje) o).getSati()));
				paragraf.appendChild(sati);

				paragraf.appendChild(document.createTextNode(" часова, односно у времену од"));

				Element odSati = document.createElement("od");
				odSati.appendChild(document.createTextNode(((PObavestenje) o).getOdSati()));
				paragraf.appendChild(odSati);

				paragraf.appendChild(document.createTextNode("до"));

				Element doSati = document.createElement("do");
				doSati.appendChild(document.createTextNode(((PObavestenje) o).getDoSati()));
				paragraf.appendChild(doSati);

				paragraf.appendChild(document.createTextNode("часова, у просторијама органа у"));

				Element mesto = document.createElement("mesto");
				mesto.appendChild(document.createTextNode(((PObavestenje) o).getMesto()));
				paragraf.appendChild(mesto);

				paragraf.appendChild(document.createTextNode("ул."));

				Element ulicaOb = document.createElement("ulica");
				ulicaOb.appendChild(document.createTextNode(((PObavestenje) o).getUlica()));
				paragraf.appendChild(ulicaOb);

				paragraf.appendChild(document.createTextNode("бр."));

				Element brojZgrade = document.createElement("broj_zgrade");
				brojZgrade.appendChild(document.createTextNode(((PObavestenje) o).getBrojZgrade()));
				paragraf.appendChild(brojZgrade);

				paragraf.appendChild(document.createTextNode(", канцеларија бр."));

				Element brojKancelarije = document.createElement("broj_kancelarije");
				brojKancelarije.appendChild(document.createTextNode(((PObavestenje) o).getBrojKancelarije()));
				paragraf.appendChild(brojKancelarije);

				paragraf.appendChild(document
						.createTextNode("можете извршити увид у документ/е у коме је садржана тражена информација."));

			}

		}

		/// ------
		Element paragraf2 = document.createElement("p");
		paragraf2.appendChild(
				document.createTextNode("Том приликом, на ваш захтев, може вам се издати и копија документа са траженом"
						+ "            информацијом."));
		tekstObavestenja.appendChild(paragraf2);

		Element paragraf3 = document.createElement("p");
		paragraf3.appendChild(document
				.createTextNode("Трошкови су утврђени Уредбом Владе Републике Србије („Сл. гласник РС“, бр. 8/06), и "
						+ "            то: копија стране А4 формата износи 3 динара, А3 формата 6 динара, CD 35 динара, "
						+ "            дискете 20 динара, DVD 40 динара, аудио-касета – 150 динара, видео-касета 300 динара,"
						+ "            претварање једне стране документа из физичког у електронски облик – 30 динара. "
						+ "        "));
		tekstObavestenja.appendChild(paragraf3);

		// dodaj tekst
		Element paragraf4 = document.createElement("p");
		tekstObavestenja.appendChild(paragraf4);
		paragraf4.appendChild(
				document.createTextNode("Износ укупних трошкова израде копије документа по вашем захтеву износи "));
		Element novcanaNaknada = document.createElement("novcana_naknada");

		novcanaNaknada.appendChild(document.createTextNode(naknada));

		paragraf4.appendChild(novcanaNaknada);
		paragraf4.appendChild(document.createTextNode(
				"динара и уплаћује се на жиро-рачун Буџета Републике Србије бр. 840-742328-843-30, с позивом на број 97 – ознака шифре општине/града где се налази орган власти (из Правилника о условима и начину вођења рачуна – „Сл. гласник РС“, 20/07... 40/10). "));

		Element mestoPecata = document.createElement("mesto_pecata");
		mestoPecata.appendChild(document.createTextNode("(М.П.)"));

		obavestenje.appendChild(mestoPecata);
		//transform(System.out);
		StringWriter sw = new StringWriter();
		transform(sw);
		
		String naziv = (obavestenjeRepository.getSize()+1) + ".rdf";
		String rdfFilePath = "src/main/resources/podaci/rdf/"+naziv;
		// extract metadata
		
		metadataExtractor.extractMetadata(sw.toString(), new FileOutputStream(new File(rdfFilePath)));
		FusekiWriter.saveRDF(rdfFilePath, "/obavestenja");
		
		return sw.toString();

	}

	

	public String generateZahtev(Zahtev z) throws TransformerException, IOException, XMLDBException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		createDocument();

		Element zahtev = document.createElement("zahtev");
		
		zahtev.setAttribute("xmlns", "http://www.w3.org/ns/rdfa#");
		zahtev.setAttribute("xmlns:pred","http://www.ftn.uns.ac.rs/rdf/examples/predicate/");		
		zahtev.setAttribute("xmlns:xs","http://www.w3.org/2001/XMLSchema#");
		
		
		document.appendChild(zahtev);

		Element uri = document.createElement("URI");
		uri.setAttribute("property","pred:URI");
		uri.setAttribute("datatype","xs:string");
		uri.appendChild(document.createTextNode(""+(zahtevRepository.getSize()+1)));
		
		Element podnosilacZahteva = document.createElement("podnosilac_zahteva");
		
		
		Element nazivPodnosioca = document.createElement("naziv_podnosioca");
		Element ime = document.createElement("ime");
		ime.appendChild(document.createTextNode(z.getPodnosilac().getIme()));
		Element prezime = document.createElement("prezime");
		prezime.appendChild(document.createTextNode(z.getPodnosilac().getPrezime()));
		Element nazivFirme = document.createElement("naziv_firme");
		nazivFirme.appendChild(document.createTextNode(z.getPodnosilac().getNazivFirme()));
		Element korisnickoIme = document.createElement("korisnicko_ime");
		korisnickoIme.setAttribute("property","pred:korisnicko_ime");
		korisnickoIme.setAttribute("datatype","xs:string");
		korisnickoIme.appendChild(document.createTextNode(z.getPodnosilac().getKorisnickoIme()));
		
		
		nazivPodnosioca.appendChild(ime);
		nazivPodnosioca.appendChild(prezime);
		nazivPodnosioca.appendChild(nazivFirme);
		nazivPodnosioca.appendChild(korisnickoIme);
		
		Element adresa = document.createElement("adresa");
		Element ulica = document.createElement("ulica");
		ulica.appendChild(document.createTextNode(z.getAdresa().getUlica()));
		Element broj = document.createElement("broj");
		broj.appendChild(document.createTextNode(z.getAdresa().getBroj()));
		Element grad = document.createElement("grad");
		grad.appendChild(document.createTextNode(z.getAdresa().getGrad()));

		adresa.appendChild(ulica);
		adresa.appendChild(broj);
		adresa.appendChild(grad);

		Element drugiPodaciZaKontakt = document.createElement("drugi_podaci_za_kontakt");
		drugiPodaciZaKontakt.appendChild(document.createTextNode(z.getDrugiPodaciZaKontakt()));

		podnosilacZahteva.appendChild(nazivPodnosioca);
		podnosilacZahteva.appendChild(adresa);
		podnosilacZahteva.appendChild(drugiPodaciZaKontakt);

		Element organVlasti = document.createElement("organ_vlasti");
		
		Element nazivOrgana = document.createElement("naziv_organa_vlasti");
		nazivOrgana.setAttribute("property","pred:naziv_organa_vlasti");
		nazivOrgana.setAttribute("datatype","xs:string");
		nazivOrgana.appendChild(document.createTextNode(z.getNazivOrganaVlasti()));
		Element sedisteOrgana = document.createElement("sediste_organa");
		sedisteOrgana.appendChild(document.createTextNode(z.getSedisteOrgana()));
		

		organVlasti.appendChild(nazivOrgana);
		organVlasti.appendChild(sedisteOrgana);

		Element tekstZahteva = document.createElement("tekst_zahteva");
		Element naslov = document.createElement("naslov");
		naslov.appendChild(document.createTextNode(z.getNaslov()));

		tekstZahteva.appendChild(naslov);

		for (int i = 0; i < z.getParagrafi().size(); i++) {
			Element paragraf = document.createElement("p");
			if (z.getParagrafi().get(i).getIzbori().size() == 0) {
				paragraf.appendChild(document.createTextNode(z.getParagrafi().get(i).getText()));
			} else {
				Element izbori = document.createElement("izbori");
				ArrayList<Izbor> izboriLista = z.getParagrafi().get(i).getIzbori();
				for (int j = 0; j < z.getParagrafi().get(i).getIzbori().size(); j++) {
					Element izbor = document.createElement("izbor");
					izbor.appendChild(document.createTextNode(z.getParagrafi().get(i).getIzbori().get(j).getTekst()));
					izbor.setAttribute("broj", Integer.toString(j + 1));

					if (j == 3) {
						Element podizbori = document.createElement("podizbori");
						izbor.appendChild(podizbori);
						for (String key : izboriLista.get(3).getPodizbori().keySet()) {
							Element podizbor = document.createElement("podizbor");
							podizbor.appendChild(document.createTextNode(izboriLista.get(3).getPodizbori().get(key)));
							podizbor.setAttribute("broj", key);
							podizbori.appendChild(podizbor);
						}
						Element drugiNacin = document.createElement("drugi_nacin");
						podizbori.appendChild(drugiNacin);
					}
					izbori.appendChild(izbor);
				}
				paragraf.appendChild(izbori);
			}
			tekstZahteva.appendChild(paragraf);
		}


		Element trazeneInformacije = document.createElement("trazene_informacije");
		trazeneInformacije.appendChild(document.createTextNode(z.getTrazeneInformacije()));
		
		Element vremeMesto = document.createElement("podaci_o_vremenu_i_mestu_podnosenja_zahteva");
		Element mesto = document.createElement("mesto");
		mesto.appendChild(document.createTextNode(z.getMesto()));
		Element datum = document.createElement("datum");
		datum.setAttribute("property","pred:datum");
		datum.setAttribute("datatype","xs:string");
		datum.appendChild(document.createTextNode(z.getDatum()));

		vremeMesto.appendChild(document.createTextNode("У"));
		vremeMesto.appendChild(mesto);
		vremeMesto.appendChild(document.createTextNode(",дана"));
		vremeMesto.appendChild(datum);
		vremeMesto.appendChild(document.createTextNode("године"));

		Element fusnote = document.createElement("fusnote");
		

		for (int i = 0; i<z.getFusnote().size(); i++) {
			Element fusnota = document.createElement("fusnota");
			fusnota.appendChild(document.createTextNode(z.getFusnote().get(i)));
			fusnota.setAttribute("broj", Integer.toString(i+1));
			fusnote.appendChild(fusnota);
		}
		

		tekstZahteva.appendChild(trazeneInformacije);
		tekstZahteva.appendChild(vremeMesto);
		tekstZahteva.appendChild(fusnote);
		
		zahtev.appendChild(uri);
		zahtev.appendChild(podnosilacZahteva);
		zahtev.appendChild(organVlasti);
		zahtev.appendChild(tekstZahteva);
		
		StringWriter sw = new StringWriter();
		transform(sw);
		String naziv = (zahtevRepository.getSize()+1) + ".rdf";
		String rdfFilePath = "src/main/resources/podaci/rdf/"+naziv;
		
		// extract metadata
		metadataExtractor.extractMetadata(sw.toString(), new FileOutputStream(new File(rdfFilePath)));
		FusekiWriter.saveRDF(rdfFilePath, "/zahtevi");
		return sw.toString();
	}


	/**
	 * Serializes DOM tree to an arbitrary OutputStream.
	 */
	public void transform(StringWriter sw) {
		try {

			// Kreiranje instance objekta zaduzenog za serijalizaciju DOM modela
			Transformer transformer = transformerFactory.newTransformer();

			// Indentacija serijalizovanog izlaza
			transformer.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "2");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");

			// Nad "source" objektom (DOM stablo) vrši se transformacija
			DOMSource source = new DOMSource(document);

			// Rezultujući stream (argument metode)
			StreamResult result = new StreamResult(sw);

			// Poziv metode koja vrši opisanu transformaciju
			transformer.transform(source, result);

		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}


}
