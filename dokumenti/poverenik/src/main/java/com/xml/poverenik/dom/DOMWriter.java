package com.xml.poverenik.dom;

import java.io.File;
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
import org.w3c.dom.ProcessingInstruction;
import org.xmldb.api.base.XMLDBException;

import com.xml.poverenik.model.Izbor;
import com.xml.poverenik.model.PZalbaCutanje;
import com.xml.poverenik.model.PZalbaOdluke;
import com.xml.poverenik.model.Resenje;
import com.xml.poverenik.model.TUser;
import com.xml.poverenik.model.ZalbaCutanje;
import com.xml.poverenik.model.ZalbaOdluke;
import com.xml.poverenik.rdf.FusekiWriter;
import com.xml.poverenik.rdf.MetadataExtractor;
import com.xml.poverenik.repository.ResenjeRepository;
import com.xml.poverenik.repository.ZalbaCutanjeRepository;
import com.xml.poverenik.repository.ZalbaOdlukeRepository;

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
	private ResenjeRepository resenjeRepository;
	
	@Autowired
	private ZalbaOdlukeRepository zalbaOdlukeRepository;
	
	@Autowired
	private ZalbaCutanjeRepository zalbaCutanjeRepository;
	
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

	public String generateResenje(Resenje resenje) throws TransformerException, IOException, XMLDBException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		createDocument();
		SimpleDateFormat formatter = new SimpleDateFormat("MM.dd.yyyy.");

		ProcessingInstruction newPI = document.createProcessingInstruction("xml-stylesheet", "type=\"text/xsl\" href=\"src/main/resources/podaci/xsl/resenje.xsl\"");
		document.insertBefore(newPI, document.getDocumentElement());
		
		Element dokumentResenje = document.createElement("d:dokument_resenje");
		document.appendChild(dokumentResenje);


		dokumentResenje.setAttribute("xmlns", "http://www.w3.org/ns/rdfa#");
		dokumentResenje.setAttribute("xmlns:pred","http://www.ftn.uns.ac.rs/rdf/examples/predicate/");		
		dokumentResenje.setAttribute("xmlns:xs","http://www.w3.org/2001/XMLSchema#");
		dokumentResenje.setAttribute("xmlns:d","http://www.ftn.uns.ac.rs/xpath/examples");
		
		Element uri = document.createElement("d:URI");
		uri.setAttribute("property","pred:URI");
		uri.setAttribute("datatype","xs:string");
		uri.appendChild(document.createTextNode(""+(resenjeRepository.getSize()+1)));
		
		dokumentResenje.appendChild(uri);
		
		Element zalbaCutanjeUri = document.createElement("d:zalba_cutanje_uri");
		zalbaCutanjeUri.setAttribute("property","pred:zalba_cutanje_uri");
		zalbaCutanjeUri.setAttribute("datatype","xs:string");
		zalbaCutanjeUri.appendChild(document.createTextNode(resenje.getURIZalbaCutanje()));
		
		dokumentResenje.appendChild(zalbaCutanjeUri);
		
		Element zalbaOdlukaUri = document.createElement("d:zalba_odluke_uri");
		zalbaOdlukaUri.setAttribute("property","pred:zalba_odluke_uri");
		zalbaOdlukaUri.setAttribute("datatype","xs:string");
		zalbaOdlukaUri.appendChild(document.createTextNode(resenje.getURIZalbaOdluke()));
		
		dokumentResenje.appendChild(zalbaOdlukaUri);
		
		Element nazivResenja = document.createElement("d:naziv_resenja");
		nazivResenja.appendChild(document.createTextNode(resenje.getNazivOdluka().getNazivResenja()));
		Element odluka = document.createElement("d:odluka");
		odluka.appendChild(document.createTextNode(resenje.getNazivOdluka().getOdluka()));

		nazivResenja.appendChild(odluka);

		Element zaglavlje = document.createElement("d:zaglavlje");
		zaglavlje.appendChild(document.createTextNode("BR"));
		Element brojResenja = document.createElement("d:broj_resenja");
		brojResenja.setAttribute("property","pred:brojResenja");
		brojResenja.setAttribute("datatype","xs:string");
		brojResenja.appendChild(document.createTextNode(resenje.getZaglavlje().getBrojResenja()));
		Element datum = document.createElement("d:datum");
		datum.setAttribute("property","pred:datum");
		datum.setAttribute("datatype","xs:string");
		datum.appendChild(document.createTextNode(resenje.getZaglavlje().getDatum()));

		zaglavlje.appendChild(brojResenja);
		zaglavlje.appendChild(document.createTextNode("Datum"));
		zaglavlje.appendChild(datum);

		Element opisPostupka = document.createElement("d:opis_postupka");
		opisPostupka.appendChild(document.createTextNode(resenje.getOpisPostupka()));
		Element potpisPoverenika = document.createElement("d:potpis_poverenika");
		potpisPoverenika.appendChild(document.createTextNode(resenje.getPotpisPoverenika()));

		Element tekstResenja = document.createElement("d:tekst_resenja");
		tekstResenja.appendChild(document.createTextNode(resenje.getTekstResenja().getTekst()));

		Element tekstObrazlozenja = document.createElement("d:tekst_obrazlozenja");
		tekstObrazlozenja.appendChild(document.createTextNode(resenje.getTekstObrazlozenja().getTekst()));

		

		for (int i = 0; i < resenje.getTekstResenja().getParagrafi().size(); i++) {
			Element paragraf = document.createElement("d:p");
			paragraf.appendChild(document.createTextNode(resenje.getTekstResenja().getParagrafi().get(i)));
			tekstResenja.appendChild(paragraf);
		}

		for (int i = 0; i < resenje.getTekstObrazlozenja().getParagrafi().size(); i++) {
			Element paragraf = document.createElement("d:p");
			paragraf.appendChild(document.createTextNode(resenje.getTekstObrazlozenja().getParagrafi().get(i)));
			tekstObrazlozenja.appendChild(paragraf);
		}

		dokumentResenje.appendChild(nazivResenja);
		dokumentResenje.appendChild(zaglavlje);
		dokumentResenje.appendChild(opisPostupka);
		dokumentResenje.appendChild(tekstResenja);
		dokumentResenje.appendChild(tekstObrazlozenja);
		dokumentResenje.appendChild(potpisPoverenika);
		
		Element korisnickoIme = document.createElement("d:korisnicko_ime");
		korisnickoIme.setAttribute("property","pred:korisnicko_ime");
		korisnickoIme.setAttribute("datatype","xs:string");
		korisnickoIme.appendChild(document.createTextNode(resenje.getKorisnickoIme()));
		dokumentResenje.appendChild(korisnickoIme);


		StringWriter sw = new StringWriter();
		transform(sw);
		
		String naziv = (resenjeRepository.getSize()+1) + ".rdf";
		String rdfFilePath = "src/main/resources/podaci/rdf/"+naziv;
		// extract metadata
		
		metadataExtractor.extractMetadata(sw.toString(), new FileOutputStream(new File(rdfFilePath)));
		FusekiWriter.saveRDF(rdfFilePath, "/resenja");
		
		return sw.toString();
		

	}

	public String generateZalbaOdluke(ZalbaOdluke zo) throws TransformerException, IOException, XMLDBException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		createDocument();

		ProcessingInstruction newPI = document.createProcessingInstruction("xml-stylesheet", "type=\"text/xsl\" href=\"src/main/resources/podaci/xsl/zalba_odluke.xsl\"");
		document.insertBefore(newPI, document.getDocumentElement());
		
		Element zalbaOdluke = document.createElement("d:zalba_odluke");
		
		zalbaOdluke.setAttribute("xmlns", "http://www.w3.org/ns/rdfa#");
		zalbaOdluke.setAttribute("xmlns:pred","http://www.ftn.uns.ac.rs/rdf/examples/predicate/");		
		zalbaOdluke.setAttribute("xmlns:xs","http://www.w3.org/2001/XMLSchema#");
		zalbaOdluke.setAttribute("xmlns:d","http://www.ftn.uns.ac.rs/xpath/examples");
		
		document.appendChild(zalbaOdluke);
		
		Element uri = document.createElement("d:URI");
		uri.setAttribute("property","pred:URI");
		uri.setAttribute("datatype","xs:string");
		uri.appendChild(document.createTextNode(""+(zalbaOdlukeRepository.getSize()+1)));
		
		zalbaOdluke.appendChild(uri);
		
		Element zahtevUri = document.createElement("d:zahtev_uri");
		zahtevUri.setAttribute("property","pred:zahtev_uri");
		zahtevUri.setAttribute("datatype","xs:string");
		zahtevUri.appendChild(document.createTextNode(zo.getZahtevURI()));
		
		zalbaOdluke.appendChild(zahtevUri);

		Element podnosilacZalbe = document.createElement("d:podnosilac_zalbe");
		Element nazivPodnosioca = document.createElement("d:naziv_podnosioca");
		Element ime = document.createElement("d:ime");
		ime.appendChild(document.createTextNode(zo.getNazivPodnosioca().getIme()));
		nazivPodnosioca.appendChild(ime);
		Element prezime = document.createElement("d:prezime");
		prezime.appendChild(document.createTextNode(zo.getNazivPodnosioca().getPrezime()));
		nazivPodnosioca.appendChild(prezime);
		Element naziv_firme = document.createElement("d:naziv_firme");
		naziv_firme.appendChild(document.createTextNode(zo.getNazivPodnosioca().getNazivFirme()));
		nazivPodnosioca.appendChild(naziv_firme);
		Element korisnicko_ime = document.createElement("d:korisnicko_ime");
		korisnicko_ime.setAttribute("property","pred:korisnicko_ime");
		korisnicko_ime.setAttribute("datatype","xs:string");
		korisnicko_ime.appendChild(document.createTextNode(zo.getNazivPodnosioca().getKorisnickoIme()));
		nazivPodnosioca.appendChild(korisnicko_ime);
		podnosilacZalbe.appendChild(nazivPodnosioca);

		Element adresaPodnosioca = document.createElement("d:adresa");
		Element ulica = document.createElement("d:ulica");
		ulica.appendChild(document.createTextNode(zo.getAdresaPodnosioca().getUlica()));
		adresaPodnosioca.appendChild(ulica);
		Element broj = document.createElement("d:broj");
		broj.appendChild(document.createTextNode(zo.getAdresaPodnosioca().getBroj()));
		adresaPodnosioca.appendChild(broj);
		Element grad = document.createElement("d:grad");
		grad.appendChild(document.createTextNode(zo.getAdresaPodnosioca().getGrad()));
		adresaPodnosioca.appendChild(grad);
		podnosilacZalbe.appendChild(adresaPodnosioca);
		Element drugiPodaciZaKontakt = document.createElement("d:drugi_podaci_za_kontakt");
		drugiPodaciZaKontakt.appendChild(document.createTextNode(zo.getDrugiPodaciZaKontakt()));
		podnosilacZalbe.appendChild(drugiPodaciZaKontakt);
		zalbaOdluke.appendChild(podnosilacZalbe);

		Element poverenik = document.createElement("d:poverenik");
		Element nazivPoverenika = document.createElement("d:naziv_poverenika");
		nazivPoverenika.setAttribute("property","pred:naziv_poverenika");
		nazivPoverenika.setAttribute("datatype","xs:string");
		nazivPoverenika.appendChild(document.createTextNode(zo.getNazivPoverenika()));
		poverenik.appendChild(nazivPoverenika);
		Element sedistePoverenika = document.createElement("d:sediste_poverenika");
		sedistePoverenika.appendChild(document.createTextNode("Aдреса за пошту:"));
		Element ulica2 = document.createElement("d:ulica");
		ulica2.appendChild(document.createTextNode(zo.getSedistePoverenika().getUlica()));
		Element broj2 = document.createElement("d:broj");
		broj2.appendChild(document.createTextNode(zo.getSedistePoverenika().getBroj()));
		Element grad2 = document.createElement("d:grad");
		grad2.appendChild(document.createTextNode(zo.getSedistePoverenika().getGrad()));
		sedistePoverenika.appendChild(ulica2);
		sedistePoverenika.appendChild(document.createTextNode("бр."));
		sedistePoverenika.appendChild(broj2);
		sedistePoverenika.appendChild(grad2);
		poverenik.appendChild(sedistePoverenika);
		zalbaOdluke.appendChild(poverenik);

		Element naslov = document.createElement("d:naslov");
		naslov.appendChild(document.createTextNode(zo.getNaslov()));
		zalbaOdluke.appendChild(naslov);

		Element tekstZalbe = document.createElement("d:tekst_zalbe");
		tekstZalbe.appendChild(document.createTextNode("Жалбa"));

		// kopiran podnosilac
		Element podnosilacZalbe2 = document.createElement("d:podnosilac_zalbe");
		Element nazivPodnosioca2 = document.createElement("d:naziv_podnosioca");
		Element ime2 = document.createElement("d:ime");
		ime2.appendChild(document.createTextNode(zo.getNazivPodnosioca().getIme()));
		nazivPodnosioca2.appendChild(ime2);
		Element prezime2 = document.createElement("d:prezime");
		prezime2.appendChild(document.createTextNode(zo.getNazivPodnosioca().getPrezime()));
		nazivPodnosioca2.appendChild(prezime2);
		Element naziv_firme2 = document.createElement("d:naziv_firme");
		naziv_firme2.appendChild(document.createTextNode(zo.getNazivPodnosioca().getNazivFirme()));
		nazivPodnosioca2.appendChild(naziv_firme2);
		podnosilacZalbe2.appendChild(nazivPodnosioca2);

		Element adresaPodnosioca2 = document.createElement("d:adresa");
		Element ulica1 = document.createElement("d:ulica");
		ulica1.appendChild(document.createTextNode(zo.getAdresaPodnosioca().getUlica()));
		adresaPodnosioca2.appendChild(ulica1);
		Element broj1 = document.createElement("d:broj");
		broj1.appendChild(document.createTextNode(zo.getAdresaPodnosioca().getBroj()));
		adresaPodnosioca2.appendChild(broj1);
		Element grad1 = document.createElement("d:grad");
		grad1.appendChild(document.createTextNode(zo.getAdresaPodnosioca().getGrad()));
		adresaPodnosioca2.appendChild(grad1);
		podnosilacZalbe2.appendChild(adresaPodnosioca2);
		Element drugiPodaciZaKontakt2 = document.createElement("d:drugi_podaci_za_kontakt");
		drugiPodaciZaKontakt2.appendChild(document.createTextNode(zo.getDrugiPodaciZaKontakt()));
		podnosilacZalbe2.appendChild(drugiPodaciZaKontakt2);

		// kraj podnosioca
		tekstZalbe.appendChild(podnosilacZalbe2);
		tekstZalbe.appendChild(document.createTextNode("против решења-закључка"));
		zalbaOdluke.appendChild(tekstZalbe);

		Element organVlasti = document.createElement("d:organ_vlasti");
		Element nazivOrgana = document.createElement("d:naziv_organa");
		organVlasti.appendChild(nazivOrgana);
		zalbaOdluke.appendChild(organVlasti);

		for (PZalbaOdluke pzo : zo.getParagrafi()) {
			Element p = document.createElement("d:p");
			if (pzo.getBrojZalbe() != null && pzo.getGodinaOdbijanja() != null) {
				p.appendChild(document.createTextNode("Број"));
				Element brojZalbe = document.createElement("d:broj_zalbe");
				brojZalbe.appendChild(document.createTextNode(pzo.getBrojZalbe()));
				p.appendChild(brojZalbe);
				p.appendChild(document.createTextNode("од"));
				Element godOdbijanja = document.createElement("d:godina_odbijanja");
				godOdbijanja.appendChild(document.createTextNode(pzo.getGodinaOdbijanja()));
				p.appendChild(godOdbijanja);
				p.appendChild(document.createTextNode("године."));

			} else if (pzo.getDatum() != null && pzo.getRazlog() != null) {
				p.appendChild(document
						.createTextNode("Наведеном одлуком органа власти(решењем, закључком, обавештењем у писаној"
								+ " форми са елементима одлуке) , супротно закону, одбијен-одбачен је мој захтев који"
								+ " сам поднео/ла- упутио/ла дана"));
				Element datum = document.createElement("d:datum");
				datum.appendChild(document.createTextNode(pzo.getDatum()));
				p.appendChild(datum);
				p.appendChild(document.createTextNode(
						"године и тако ми ускраћено-онемогућено остваривање уставног и законског права на"
								+ " слободан приступ информацијама од јавног значаја. Одлуку побијам у целости, односно"
								+ " у делу којим"));
				Element razlog = document.createElement("d:razlog");
				razlog.appendChild(document.createTextNode(pzo.getRazlog()));
				p.appendChild(razlog);
				p.appendChild(document.createTextNode(
						"јер није заснована на Закону о слободном приступу информацијама од јавног значаја."));

			} else {
				p.appendChild(document.createTextNode(pzo.getTekst()));
			}

			tekstZalbe.appendChild(p);
		}

		Element podaciOTrenutku = document.createElement("d:podaci_o_trenutku");
		podaciOTrenutku.appendChild(document.createTextNode("У"));
		Element mesto = document.createElement("d:mesto");
		mesto.appendChild(document.createTextNode(zo.getMesto()));
		podaciOTrenutku.appendChild(mesto);
		podaciOTrenutku.appendChild(document.createTextNode(",дана"));
		Element datum = document.createElement("d:datum");
		datum.setAttribute("property","pred:datum");
		datum.setAttribute("datatype","xs:string");
		datum.appendChild(document.createTextNode(zo.getDatum()));
		podaciOTrenutku.appendChild(datum);
		podaciOTrenutku.appendChild(document.createTextNode(" године"));
		tekstZalbe.appendChild(podaciOTrenutku);

		Element napomena = document.createElement("d:napomena");
		napomena.appendChild(document.createTextNode("Напомена:"));
		zalbaOdluke.appendChild(napomena);
		Element tacke = document.createElement("d:tacke");
		napomena.appendChild(tacke);

		for (int t = 0; t < zo.getTackeNapomene().size(); t++) {
			Element tacka = document.createElement("d:tacka");
			tacka.setAttribute("broj", Integer.toString(t + 1));
			tacka.appendChild(document.createTextNode(zo.getTackeNapomene().get(t)));
			tacke.appendChild(tacka);
		}

		StringWriter sw = new StringWriter();
		transform(sw);
		String naziv = (zalbaOdlukeRepository.getSize()+1) + ".rdf";
		String rdfFilePath = "src/main/resources/podaci/rdf/"+naziv;
		// extract metadata
		
		metadataExtractor.extractMetadata(sw.toString(), new FileOutputStream(new File(rdfFilePath)));
		FusekiWriter.saveRDF(rdfFilePath, "/zalbeOdluke");
		
		return sw.toString();

	}


	public String generateZalbaCutanje(ZalbaCutanje zc) throws TransformerException, IOException, XMLDBException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		createDocument();
		// Kreiranje i postavljanje korenskog elementa
		ProcessingInstruction newPI = document.createProcessingInstruction("xml-stylesheet", "type=\"text/xsl\" href=\"src/main/resources/podaci/xsl/zalba_cutanje.xsl\"");
		document.insertBefore(newPI, document.getDocumentElement());
		
		Element zalbaCutanje = document.createElement("d:zalba_cutanje");
		zalbaCutanje.setAttribute("xmlns", "http://www.w3.org/ns/rdfa#");
		zalbaCutanje.setAttribute("xmlns:pred","http://www.ftn.uns.ac.rs/rdf/examples/predicate/");		
		zalbaCutanje.setAttribute("xmlns:xs","http://www.w3.org/2001/XMLSchema#");
		zalbaCutanje.setAttribute("xmlns:d","http://www.ftn.uns.ac.rs/xpath/examples");
		
		document.appendChild(zalbaCutanje);
		
		Element uri = document.createElement("d:URI");
		uri.setAttribute("property","pred:URI");
		uri.setAttribute("datatype","xs:string");
		uri.appendChild(document.createTextNode(""+(zalbaCutanjeRepository.getSize()+1)));
		
		zalbaCutanje.appendChild(uri);
		
		Element zahtevUri = document.createElement("d:zahtev_uri");
		zahtevUri.setAttribute("property","pred:zahtev_uri");
		zahtevUri.setAttribute("datatype","xs:string");
		zahtevUri.appendChild(document.createTextNode(zc.getZahtevURI()));
		
		zalbaCutanje.appendChild(zahtevUri);

		Element podnosilacZalbe = document.createElement("d:podnosilac_zalbe");
		Element nazivPodnosioca = document.createElement("d:naziv_podnosioca");
		Element ime = document.createElement("d:ime");
		ime.appendChild(document.createTextNode(zc.getPodnosilac().getIme()));
		nazivPodnosioca.appendChild(ime);
		Element prezime = document.createElement("d:prezime");
		prezime.appendChild(document.createTextNode(zc.getPodnosilac().getPrezime()));
		nazivPodnosioca.appendChild(prezime);
		Element naziv_firme = document.createElement("d:naziv_firme");
		naziv_firme.appendChild(document.createTextNode(zc.getPodnosilac().getNazivFirme()));
		nazivPodnosioca.appendChild(naziv_firme);
		Element korisnicko_ime = document.createElement("d:korisnicko_ime");
		korisnicko_ime.setAttribute("property","pred:korisnicko_ime");
		korisnicko_ime.setAttribute("datatype","xs:string");
		korisnicko_ime.appendChild(document.createTextNode(zc.getPodnosilac().getKorisnickoIme()));
		nazivPodnosioca.appendChild(korisnicko_ime);
		podnosilacZalbe.appendChild(nazivPodnosioca);

		Element adresaPodnosioca = document.createElement("d:adresa");
		Element ulica = document.createElement("d:ulica");
		ulica.appendChild(document.createTextNode(zc.getAdresa().getUlica()));
		adresaPodnosioca.appendChild(ulica);
		Element broj = document.createElement("d:broj");
		broj.appendChild(document.createTextNode(zc.getAdresa().getBroj()));
		adresaPodnosioca.appendChild(broj);
		Element grad = document.createElement("d:grad");
		grad.appendChild(document.createTextNode(zc.getAdresa().getGrad()));
		adresaPodnosioca.appendChild(grad);
		podnosilacZalbe.appendChild(adresaPodnosioca);
		Element drugiPodaciZaKontakt = document.createElement("d:drugi_podaci_za_kontakt");
		drugiPodaciZaKontakt.appendChild(document.createTextNode(zc.getDrugiPodaciZaKontakt()));
		podnosilacZalbe.appendChild(drugiPodaciZaKontakt);
		zalbaCutanje.appendChild(podnosilacZalbe);

		Element poverenik = document.createElement("d:poverenik");
		Element nazivPoverenika = document.createElement("d:naziv_poverenika");
		nazivPoverenika.setAttribute("property","pred:naziv_poverenika");
		nazivPoverenika.setAttribute("datatype","xs:string");
		nazivPoverenika.appendChild(document.createTextNode(zc.getNazivPoverenika()));
		poverenik.appendChild(nazivPoverenika);
		Element sedistePoverenika = document.createElement("d:sediste_poverenika");
		sedistePoverenika.appendChild(document.createTextNode("Aдреса за пошту:"));
		Element ulica2 = document.createElement("d:ulica");
		ulica2.appendChild(document.createTextNode(zc.getSedistePoverenika().getUlica()));
		Element broj2 = document.createElement("d:broj");
		broj2.appendChild(document.createTextNode(zc.getSedistePoverenika().getBroj()));
		Element grad2 = document.createElement("d:grad");
		grad2.appendChild(document.createTextNode(zc.getSedistePoverenika().getGrad()));
		sedistePoverenika.appendChild(ulica2);
		sedistePoverenika.appendChild(document.createTextNode("бр."));
		sedistePoverenika.appendChild(broj2);
		sedistePoverenika.appendChild(grad2);
		poverenik.appendChild(sedistePoverenika);
		zalbaCutanje.appendChild(poverenik);

		Element naslov = document.createElement("d:naslov");
		naslov.appendChild(document.createTextNode(zc.getNaslov()));
		zalbaCutanje.appendChild(naslov);

		Element tekstZalbe = document.createElement("d:tekst_zalbe");
		for (PZalbaCutanje pzc : zc.getParagrafi()) {
			Element p = document.createElement("d:p");
			if (pzc.getNazivOrgana() != null) {
				p.appendChild(document.createTextNode(pzc.getText()));
				Element organ = document.createElement("d:naziv_organa");
				organ.appendChild(document.createTextNode(pzc.getNazivOrgana()));
				p.appendChild(organ);

			} else if (pzc.getDatum() != null && pzc.getPodaciOZahtevuIInformacijama() != null) {
				p.appendChild(document.createTextNode(pzc.getText()));
				Element datum = document.createElement("d:datum");
				datum.appendChild(document.createTextNode(pzc.getDatum()));
				p.appendChild(datum);
				p.appendChild(document
						.createTextNode("године, а којим сам тражио/ла да ми се у складу са Законом о слободном \r\n"
								+ "                приступу информацијама од јавног значаја омогући увид- копија документа \r\n"
								+ "                који садржи информације о/у вези са:"));
				Element informacije = document.createElement("d:podaci_o_zahtevu_i_informacijama");
				informacije.appendChild(document.createTextNode(pzc.getPodaciOZahtevuIInformacijama()));
				p.appendChild(informacije);

			} else {
				p.appendChild(document.createTextNode(pzc.getText()));
			}

			tekstZalbe.appendChild(p);
		}

		Element podaciOTrenutku = document.createElement("d:podaci_o_vremenu_i_mestu_podnosenja_zalbe");
		podaciOTrenutku.appendChild(document.createTextNode("У"));
		Element mesto = document.createElement("d:mesto");
		mesto.appendChild(document.createTextNode(zc.getMestoZalbe()));
		podaciOTrenutku.appendChild(mesto);
		podaciOTrenutku.appendChild(document.createTextNode(",дана"));
		Element datum = document.createElement("d:datum");
		datum.setAttribute("property","pred:datum");
		datum.setAttribute("datatype","xs:string");
		datum.appendChild(document.createTextNode(zc.getDatumZalbe()));
		podaciOTrenutku.appendChild(datum);
		podaciOTrenutku.appendChild(document.createTextNode(" године"));
		tekstZalbe.appendChild(podaciOTrenutku);
		zalbaCutanje.appendChild(tekstZalbe);

		StringWriter sw = new StringWriter();
		transform(sw);
		String naziv = (zalbaCutanjeRepository.getSize()+1) + ".rdf";
		String rdfFilePath = "src/main/resources/podaci/rdf/"+naziv;
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

		public String generateUser(TUser t) {
		createDocument();
		
		Element dokumentUs = document.createElement("ns2:user");

		dokumentUs.setAttribute("xmlns:ns2","https://github.com/Andjelaaa/XML-i-veb-servisi");
		document.appendChild(dokumentUs);
		Element korisnickoIme = document.createElement("ns2:username");
		korisnickoIme.appendChild(document.createTextNode(t.getUsername()));
		Element sifra = document.createElement("ns2:password");
		sifra.appendChild(document.createTextNode(t.getPassword()));
		Element email = document.createElement("ns2:email");
		email.appendChild(document.createTextNode(t.getEmail()));
		Element ime = document.createElement("ns2:firstName");
		ime.appendChild(document.createTextNode(t.getFirstName()));
		Element prezime = document.createElement("ns2:lastName");
		prezime.appendChild(document.createTextNode(t.getLastName()));
		
		Element tip = document.createElement("ns2:type");
		tip.appendChild(document.createTextNode(t.getTitle()));
		Element rola = document.createElement("ns2:role");
		rola.appendChild(document.createTextNode(t.getRole()));
		
		
		dokumentUs.appendChild(korisnickoIme);
		dokumentUs.appendChild(sifra);
		dokumentUs.appendChild(email);
		dokumentUs.appendChild(ime);
		dokumentUs.appendChild(prezime);
		dokumentUs.appendChild(tip);
		dokumentUs.appendChild(rola);
		
		StringWriter sw = new StringWriter();
		transform(sw);
		return sw.toString();
	}


}
