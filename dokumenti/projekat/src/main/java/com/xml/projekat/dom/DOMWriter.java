package com.xml.projekat.dom;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
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

import com.xml.projekat.model.Izbor;
import com.xml.projekat.model.Obavestenje;
import com.xml.projekat.model.PObavestenje;
import com.xml.projekat.model.PZalbaCutanje;
import com.xml.projekat.model.PZalbaOdluke;
import com.xml.projekat.model.Resenje;
import com.xml.projekat.model.Zahtev;
import com.xml.projekat.model.ZalbaCutanje;
import com.xml.projekat.model.ZalbaOdluke;
import com.xml.projekat.rdf.FusekiWriter;
import com.xml.projekat.rdf.MetadataExtractor;

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
	 */

	public String generateResenje(Resenje resenje) throws TransformerException, IOException {
		createDocument();
		SimpleDateFormat formatter = new SimpleDateFormat("MM.dd.yyyy.");

		Element dokumentResenje = document.createElement("dokument_resenje");
		document.appendChild(dokumentResenje);

//		dokumentResenje.setAttributeNS(XSI_NAMESPACE, "xsi:schemaLocation",
//				"./../resenje.xsd");

		dokumentResenje.setAttribute("xmlns", "http://www.w3.org/ns/rdfa#");
		dokumentResenje.setAttribute("xmlns:pred","http://www.ftn.uns.ac.rs/rdf/examples/predicate/");		
		dokumentResenje.setAttribute("xmlns:xs","http://www.w3.org/2001/XMLSchema#");
		
		Element nazivResenja = document.createElement("naziv_resenja");
		nazivResenja.appendChild(document.createTextNode(resenje.getNazivOdluka().getNazivResenja()));
		Element odluka = document.createElement("odluka");
		odluka.appendChild(document.createTextNode(resenje.getNazivOdluka().getOdluka()));

		nazivResenja.appendChild(odluka);

		Element zaglavlje = document.createElement("zaglavlje");
		zaglavlje.appendChild(document.createTextNode("BR"));
		Element brojResenja = document.createElement("broj_resenja");
		brojResenja.setAttribute("property","pred:brojResenja");
		brojResenja.setAttribute("datatype","xs:string");
		brojResenja.appendChild(document.createTextNode(resenje.getZaglavlje().getBrojResenja()));
		Element datum = document.createElement("datum");
		datum.setAttribute("property","pred:datum");
		datum.setAttribute("datatype","xs:string");
		datum.appendChild(document.createTextNode(formatter.format(resenje.getZaglavlje().getDatum())));

		zaglavlje.appendChild(brojResenja);
		zaglavlje.appendChild(document.createTextNode("Datum"));
		zaglavlje.appendChild(datum);

		Element opisPostupka = document.createElement("opis_postupka");
		opisPostupka.appendChild(document.createTextNode(resenje.getOpisPostupka()));
		Element potpisPoverenika = document.createElement("potpis_poverenika");
		potpisPoverenika.appendChild(document.createTextNode(resenje.getPotpisPoverenika()));

		Element tekstResenja = document.createElement("tekst_resenja");
		tekstResenja.appendChild(document.createTextNode(resenje.getTekstResenja().getTekst()));

		Element tekstObrazlozenja = document.createElement("tekst_obrazlozenja");
		tekstObrazlozenja.appendChild(document.createTextNode(resenje.getTekstObrazlozenja().getTekst()));

		

		for (int i = 0; i < resenje.getTekstResenja().getParagrafi().size(); i++) {
			Element paragraf = document.createElement("p");
			paragraf.appendChild(document.createTextNode(resenje.getTekstResenja().getParagrafi().get(i)));
			tekstResenja.appendChild(paragraf);
		}

		for (int i = 0; i < resenje.getTekstObrazlozenja().getParagrafi().size(); i++) {
			Element paragraf = document.createElement("p");
			paragraf.appendChild(document.createTextNode(resenje.getTekstObrazlozenja().getParagrafi().get(i)));
			tekstObrazlozenja.appendChild(paragraf);
		}

		dokumentResenje.appendChild(nazivResenja);
		dokumentResenje.appendChild(zaglavlje);
		dokumentResenje.appendChild(opisPostupka);
		dokumentResenje.appendChild(tekstResenja);
		dokumentResenje.appendChild(tekstObrazlozenja);
		dokumentResenje.appendChild(potpisPoverenika);


		StringWriter sw = new StringWriter();
		transform(sw);
		
		String rdfFilePath = "src/main/resources/podaci/rdf/resenje.rdf";
		// extract metadata
		
		metadataExtractor.extractMetadata(sw.toString(), new FileOutputStream(new File(rdfFilePath)));
		FusekiWriter.saveRDF(rdfFilePath, "/resenja");
		
		return sw.toString();
		

	}

	public String generateDOMObavestenje(Obavestenje ob) throws TransformerException, IOException {

		// Kreiranje i postavljanje korenskog elementa
		createDocument();
		Element obavestenje = document.createElement("obavestenje");
		document.appendChild(obavestenje);

//		obavestenje.setAttributeNS(XSI_NAMESPACE, "xsi:schemaLocation",
//				"./../obavestenje.xsd");
		obavestenje.setAttribute("xmlns", "http://www.w3.org/ns/rdfa#");
		obavestenje.setAttribute("xmlns:pred","http://www.ftn.uns.ac.rs/rdf/examples/predicate/");		
		obavestenje.setAttribute("xmlns:xs","http://www.w3.org/2001/XMLSchema#");

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
		
		String rdfFilePath = "src/main/resources/podaci/rdf/obavestenje.rdf";
		// extract metadata
		
		metadataExtractor.extractMetadata(sw.toString(), new FileOutputStream(new File(rdfFilePath)));
		FusekiWriter.saveRDF(rdfFilePath, "/obavestenja");
		
		return sw.toString();

	}

	public String generateZalbaOdluke(ZalbaOdluke zo) throws TransformerException, IOException {
		createDocument();
		// Kreiranje i postavljanje korenskog elementa
		Element zalbaOdluke = document.createElement("zalba_odluke");
//		zalbaOdluke.setAttributeNS(XSI_NAMESPACE, "xsi:schemaLocation",
//				"./../zalba_odluke.xsd");
		
		zalbaOdluke.setAttribute("xmlns", "http://www.w3.org/ns/rdfa#");
		zalbaOdluke.setAttribute("xmlns:pred","http://www.ftn.uns.ac.rs/rdf/examples/predicate/");		
		zalbaOdluke.setAttribute("xmlns:xs","http://www.w3.org/2001/XMLSchema#");
		
		document.appendChild(zalbaOdluke);

		Element podnosilacZalbe = document.createElement("podnosilac_zalbe");
		Element nazivPodnosioca = document.createElement("naziv_podnosioca");
		Element ime = document.createElement("ime");
		ime.appendChild(document.createTextNode(zo.getNazivPodnosioca().getIme()));
		nazivPodnosioca.appendChild(ime);
		Element prezime = document.createElement("prezime");
		prezime.appendChild(document.createTextNode(zo.getNazivPodnosioca().getPrezime()));
		nazivPodnosioca.appendChild(prezime);
		Element naziv_firme = document.createElement("naziv_firme");
		naziv_firme.appendChild(document.createTextNode(zo.getNazivPodnosioca().getNazivFirme()));
		nazivPodnosioca.appendChild(naziv_firme);
		podnosilacZalbe.appendChild(nazivPodnosioca);

		Element adresaPodnosioca = document.createElement("adresa");
		Element ulica = document.createElement("ulica");
		ulica.appendChild(document.createTextNode(zo.getAdresaPodnosioca().getUlica()));
		adresaPodnosioca.appendChild(ulica);
		Element broj = document.createElement("broj");
		broj.appendChild(document.createTextNode(zo.getAdresaPodnosioca().getBroj()));
		adresaPodnosioca.appendChild(broj);
		Element grad = document.createElement("grad");
		grad.appendChild(document.createTextNode(zo.getAdresaPodnosioca().getGrad()));
		adresaPodnosioca.appendChild(grad);
		podnosilacZalbe.appendChild(adresaPodnosioca);
		Element drugiPodaciZaKontakt = document.createElement("drugi_podaci_za_kontakt");
		drugiPodaciZaKontakt.appendChild(document.createTextNode(zo.getDrugiPodaciZaKontakt()));
		podnosilacZalbe.appendChild(drugiPodaciZaKontakt);
		zalbaOdluke.appendChild(podnosilacZalbe);

		Element poverenik = document.createElement("poverenik");
		Element nazivPoverenika = document.createElement("naziv_poverenika");
		nazivPoverenika.appendChild(document.createTextNode(zo.getNazivPoverenika()));
		poverenik.appendChild(nazivPoverenika);
		Element sedistePoverenika = document.createElement("sediste_poverenika");
		sedistePoverenika.appendChild(document.createTextNode("Aдреса за пошту:"));
		Element ulica2 = document.createElement("ulica");
		ulica2.appendChild(document.createTextNode(zo.getSedistePoverenika().getUlica()));
		Element broj2 = document.createElement("broj");
		broj2.appendChild(document.createTextNode(zo.getSedistePoverenika().getBroj()));
		Element grad2 = document.createElement("grad");
		grad2.appendChild(document.createTextNode(zo.getSedistePoverenika().getGrad()));
		sedistePoverenika.appendChild(ulica2);
		sedistePoverenika.appendChild(document.createTextNode("бр."));
		sedistePoverenika.appendChild(broj2);
		sedistePoverenika.appendChild(grad2);
		poverenik.appendChild(sedistePoverenika);
		zalbaOdluke.appendChild(poverenik);

		Element naslov = document.createElement("naslov");
		naslov.appendChild(document.createTextNode(zo.getNaslov()));
		zalbaOdluke.appendChild(naslov);

		Element tekstZalbe = document.createElement("tekst_zalbe");
		tekstZalbe.appendChild(document.createTextNode("Жалбa"));

		// kopiran podnosilac
		Element podnosilacZalbe2 = document.createElement("podnosilac_zalbe");
		Element nazivPodnosioca2 = document.createElement("naziv_podnosioca");
		Element ime2 = document.createElement("ime");
		ime2.appendChild(document.createTextNode(zo.getNazivPodnosioca().getIme()));
		nazivPodnosioca2.appendChild(ime2);
		Element prezime2 = document.createElement("prezime");
		prezime2.appendChild(document.createTextNode(zo.getNazivPodnosioca().getPrezime()));
		nazivPodnosioca2.appendChild(prezime2);
		Element naziv_firme2 = document.createElement("naziv_firme");
		naziv_firme2.appendChild(document.createTextNode(zo.getNazivPodnosioca().getNazivFirme()));
		nazivPodnosioca2.appendChild(naziv_firme2);
		podnosilacZalbe2.appendChild(nazivPodnosioca2);

		Element adresaPodnosioca2 = document.createElement("adresa");
		Element ulica1 = document.createElement("ulica");
		ulica1.appendChild(document.createTextNode(zo.getAdresaPodnosioca().getUlica()));
		adresaPodnosioca2.appendChild(ulica1);
		Element broj1 = document.createElement("broj");
		broj1.appendChild(document.createTextNode(zo.getAdresaPodnosioca().getBroj()));
		adresaPodnosioca2.appendChild(broj1);
		Element grad1 = document.createElement("grad");
		grad1.appendChild(document.createTextNode(zo.getAdresaPodnosioca().getGrad()));
		adresaPodnosioca2.appendChild(grad1);
		podnosilacZalbe2.appendChild(adresaPodnosioca2);
		Element drugiPodaciZaKontakt2 = document.createElement("drugi_podaci_za_kontakt");
		drugiPodaciZaKontakt2.appendChild(document.createTextNode(zo.getDrugiPodaciZaKontakt()));
		podnosilacZalbe2.appendChild(drugiPodaciZaKontakt2);

		// kraj podnosioca
		tekstZalbe.appendChild(podnosilacZalbe2);
		tekstZalbe.appendChild(document.createTextNode("против решења-закључка"));
		zalbaOdluke.appendChild(tekstZalbe);

		Element organVlasti = document.createElement("organ_vlasti");
		Element nazivOrgana = document.createElement("naziv_organa");
		organVlasti.appendChild(nazivOrgana);

		for (PZalbaOdluke pzo : zo.getParagrafi()) {
			Element p = document.createElement("p");
			if (pzo.getBrojZalbe() != null && pzo.getGodinaOdbijanja() != null) {
				p.appendChild(document.createTextNode("Број"));
				Element brojZalbe = document.createElement("broj_zalbe");
				brojZalbe.appendChild(document.createTextNode(pzo.getBrojZalbe()));
				p.appendChild(brojZalbe);
				p.appendChild(document.createTextNode("од"));
				Element godOdbijanja = document.createElement("godina_odbijanja");
				godOdbijanja.appendChild(document.createTextNode(pzo.getGodinaOdbijanja()));
				p.appendChild(godOdbijanja);
				p.appendChild(document.createTextNode("године."));

			} else if (pzo.getDatum() != null && pzo.getRazlog() != null) {
				p.appendChild(document
						.createTextNode("Наведеном одлуком органа власти(решењем, закључком, обавештењем у писаној"
								+ " форми са елементима одлуке) , супротно закону, одбијен-одбачен је мој захтев који"
								+ " сам поднео/ла- упутио/ла дана"));
				Element datum = document.createElement("datum");
				datum.appendChild(document.createTextNode(pzo.getDatum()));
				p.appendChild(datum);
				p.appendChild(document.createTextNode(
						"године и тако ми ускраћено-онемогућено остваривање уставног и законског права на"
								+ " слободан приступ информацијама од јавног значаја. Одлуку побијам у целости, односно"
								+ " у делу којим"));
				Element razlog = document.createElement("razlog");
				razlog.appendChild(document.createTextNode(pzo.getRazlog()));
				p.appendChild(razlog);
				p.appendChild(document.createTextNode(
						"јер није заснована на Закону о слободном приступу информацијама од јавног значаја."));

			} else {
				p.appendChild(document.createTextNode(pzo.getTekst()));
			}

			tekstZalbe.appendChild(p);
		}

		Element podaciOTrenutku = document.createElement("podaci_o_trenutku");
		podaciOTrenutku.appendChild(document.createTextNode("У"));
		Element mesto = document.createElement("mesto");
		mesto.appendChild(document.createTextNode(zo.getMesto()));
		podaciOTrenutku.appendChild(mesto);
		podaciOTrenutku.appendChild(document.createTextNode(",дана"));
		Element datum = document.createElement("datum");
		datum.setAttribute("property","pred:datum");
		datum.setAttribute("datatype","xs:string");
		datum.appendChild(document.createTextNode(zo.getDatum()));
		podaciOTrenutku.appendChild(datum);
		podaciOTrenutku.appendChild(document.createTextNode(" године"));
		tekstZalbe.appendChild(podaciOTrenutku);

		Element napomena = document.createElement("napomena");
		napomena.appendChild(document.createTextNode("Напомена:"));
		zalbaOdluke.appendChild(napomena);
		Element tacke = document.createElement("tacke");
		napomena.appendChild(tacke);

		for (int t = 0; t < zo.getTackeNapomene().size(); t++) {
			Element tacka = document.createElement("tacka");
			tacka.setAttribute("broj", Integer.toString(t + 1));
			tacka.appendChild(document.createTextNode(zo.getTackeNapomene().get(t)));
			tacke.appendChild(tacka);
		}

		StringWriter sw = new StringWriter();
		transform(sw);
		String rdfFilePath = "src/main/resources/podaci/rdf/zalbaOdluke.rdf";
		// extract metadata
		
		metadataExtractor.extractMetadata(sw.toString(), new FileOutputStream(new File(rdfFilePath)));
		FusekiWriter.saveRDF(rdfFilePath, "/zalbeOdluke");
		
		return sw.toString();

	}

	public String generateZahtev(Zahtev z) throws TransformerException, IOException {
		createDocument();

		Element zahtev = document.createElement("zahtev");
		zahtev.setAttribute("xmlns", "http://www.w3.org/ns/rdfa#");
		zahtev.setAttribute("xmlns:pred","http://www.ftn.uns.ac.rs/rdf/examples/predicate/");		
		zahtev.setAttribute("xmlns:xs","http://www.w3.org/2001/XMLSchema#");
		
		document.appendChild(zahtev);

//		zahtev.setAttributeNS(XSI_NAMESPACE, "xsi:schemaLocation",
//				"./../zahtev.xsd");

		Element podnosilacZahteva = document.createElement("podnosilac_zahteva");

		Element nazivPodnosioca = document.createElement("naziv_podnosioca");
		Element ime = document.createElement("ime");
		ime.appendChild(document.createTextNode(z.getPodnosilac().getIme()));
		Element prezime = document.createElement("prezime");
		prezime.appendChild(document.createTextNode(z.getPodnosilac().getPrezime()));
		Element nazivFirme = document.createElement("naziv_firme");
		nazivFirme.appendChild(document.createTextNode(z.getPodnosilac().getNazivFirme()));

		nazivPodnosioca.appendChild(ime);
		nazivPodnosioca.appendChild(prezime);
		nazivPodnosioca.appendChild(nazivFirme);

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
						for (Integer key : izboriLista.get(3).getPodizbori().keySet()) {
							Element podizbor = document.createElement("podizbor");
							podizbor.appendChild(document.createTextNode(izboriLista.get(3).getPodizbori().get(key)));
							podizbor.setAttribute("broj", Integer.toString(key));
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
		
		zahtev.appendChild(podnosilacZahteva);
		zahtev.appendChild(organVlasti);
		zahtev.appendChild(tekstZahteva);

		StringWriter sw = new StringWriter();
		transform(sw);
		String rdfFilePath = "src/main/resources/podaci/rdf/zahtev.rdf";
		// extract metadata
		
		metadataExtractor.extractMetadata(sw.toString(), new FileOutputStream(new File(rdfFilePath)));
		FusekiWriter.saveRDF(rdfFilePath, "/zahtevi");
		return sw.toString();
	}

	public String generateZalbaCutanje(ZalbaCutanje zc) throws TransformerException, IOException {
		createDocument();
		// Kreiranje i postavljanje korenskog elementa
		Element zalbaCutanje = document.createElement("zalba_cutanje");
		zalbaCutanje.setAttribute("xmlns", "http://www.w3.org/ns/rdfa#");
		zalbaCutanje.setAttribute("xmlns:pred","http://www.ftn.uns.ac.rs/rdf/examples/predicate/");		
		zalbaCutanje.setAttribute("xmlns:xs","http://www.w3.org/2001/XMLSchema#");
//		zalbaCutanje.setAttributeNS(XSI_NAMESPACE, "xsi:schemaLocation",
//				"./../zalba_cutanje.xsd");
		document.appendChild(zalbaCutanje);

		Element podnosilacZalbe = document.createElement("podnosilac_zalbe");
		Element nazivPodnosioca = document.createElement("naziv_podnosioca");
		Element ime = document.createElement("ime");
		ime.appendChild(document.createTextNode(zc.getPodnosilac().getIme()));
		nazivPodnosioca.appendChild(ime);
		Element prezime = document.createElement("prezime");
		prezime.appendChild(document.createTextNode(zc.getPodnosilac().getPrezime()));
		nazivPodnosioca.appendChild(prezime);
		Element naziv_firme = document.createElement("naziv_firme");
		naziv_firme.appendChild(document.createTextNode(zc.getPodnosilac().getNazivFirme()));
		nazivPodnosioca.appendChild(naziv_firme);
		podnosilacZalbe.appendChild(nazivPodnosioca);

		Element adresaPodnosioca = document.createElement("adresa");
		Element ulica = document.createElement("ulica");
		ulica.appendChild(document.createTextNode(zc.getAdresa().getUlica()));
		adresaPodnosioca.appendChild(ulica);
		Element broj = document.createElement("broj");
		broj.appendChild(document.createTextNode(zc.getAdresa().getBroj()));
		adresaPodnosioca.appendChild(broj);
		Element grad = document.createElement("grad");
		grad.appendChild(document.createTextNode(zc.getAdresa().getGrad()));
		adresaPodnosioca.appendChild(grad);
		podnosilacZalbe.appendChild(adresaPodnosioca);
		Element drugiPodaciZaKontakt = document.createElement("drugi_podaci_za_kontakt");
		drugiPodaciZaKontakt.appendChild(document.createTextNode(zc.getDrugiPodaciZaKontakt()));
		podnosilacZalbe.appendChild(drugiPodaciZaKontakt);
		zalbaCutanje.appendChild(podnosilacZalbe);

		Element poverenik = document.createElement("poverenik");
		Element nazivPoverenika = document.createElement("naziv_poverenika");
		nazivPoverenika.appendChild(document.createTextNode(zc.getNazivPoverenika()));
		poverenik.appendChild(nazivPoverenika);
		Element sedistePoverenika = document.createElement("sediste_poverenika");
		sedistePoverenika.appendChild(document.createTextNode("Aдреса за пошту:"));
		Element ulica2 = document.createElement("ulica");
		ulica2.appendChild(document.createTextNode(zc.getSedistePoverenika().getUlica()));
		Element broj2 = document.createElement("broj");
		broj2.appendChild(document.createTextNode(zc.getSedistePoverenika().getBroj()));
		Element grad2 = document.createElement("grad");
		grad2.appendChild(document.createTextNode(zc.getSedistePoverenika().getGrad()));
		sedistePoverenika.appendChild(ulica2);
		sedistePoverenika.appendChild(document.createTextNode("бр."));
		sedistePoverenika.appendChild(broj2);
		sedistePoverenika.appendChild(grad2);
		poverenik.appendChild(sedistePoverenika);
		zalbaCutanje.appendChild(poverenik);

		Element naslov = document.createElement("naslov");
		naslov.appendChild(document.createTextNode(zc.getNaslov()));
		zalbaCutanje.appendChild(naslov);

		Element tekstZalbe = document.createElement("tekst_zalbe");
		for (PZalbaCutanje pzc : zc.getParagrafi()) {
			Element p = document.createElement("p");
			if (pzc.getNazivOrgana() != null) {
				p.appendChild(document.createTextNode(pzc.getText()));
				Element organ = document.createElement("naziv_organa");
				organ.appendChild(document.createTextNode(pzc.getNazivOrgana()));
				p.appendChild(organ);

			} else if (pzc.getDatum() != null && pzc.getPodaciOZahtevuIInformacijama() != null) {
				p.appendChild(document.createTextNode(pzc.getText()));
				Element datum = document.createElement("datum");
				datum.appendChild(document.createTextNode(pzc.getDatum()));
				p.appendChild(datum);
				p.appendChild(document
						.createTextNode("године, а којим сам тражио/ла да ми се у складу са Законом о слободном \r\n"
								+ "                приступу информацијама од јавног значаја омогући увид- копија документа \r\n"
								+ "                који садржи информације о/у вези са:"));
				Element informacije = document.createElement("podaci_o_zahtevu_i_informacijama");
				informacije.appendChild(document.createTextNode(pzc.getPodaciOZahtevuIInformacijama()));
				p.appendChild(informacije);

			} else {
				p.appendChild(document.createTextNode(pzc.getText()));
			}

			tekstZalbe.appendChild(p);
		}

		Element podaciOTrenutku = document.createElement("podaci_o_vremenu_i_mestu_podnosenja_zalbe");
		podaciOTrenutku.appendChild(document.createTextNode("У"));
		Element mesto = document.createElement("mesto");
		mesto.appendChild(document.createTextNode(zc.getMestoZalbe()));
		podaciOTrenutku.appendChild(mesto);
		podaciOTrenutku.appendChild(document.createTextNode(",дана"));
		Element datum = document.createElement("datum");
		datum.setAttribute("property","pred:datum");
		datum.setAttribute("datatype","xs:string");
		datum.appendChild(document.createTextNode(zc.getDatumZalbe()));
		podaciOTrenutku.appendChild(datum);
		podaciOTrenutku.appendChild(document.createTextNode(" године"));
		tekstZalbe.appendChild(podaciOTrenutku);
		zalbaCutanje.appendChild(tekstZalbe);

		StringWriter sw = new StringWriter();
		transform(sw);
		String rdfFilePath = "src/main/resources/podaci/rdf/zalbaCutanje.rdf";
		// extract metadata
		
		metadataExtractor.extractMetadata(sw.toString(), new FileOutputStream(new File(rdfFilePath)));
		FusekiWriter.saveRDF(rdfFilePath, "/zalbeCutanje");
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
