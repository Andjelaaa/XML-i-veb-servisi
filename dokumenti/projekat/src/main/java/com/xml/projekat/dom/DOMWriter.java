package com.xml.projekat.dom;

import java.io.OutputStream;
import java.text.SimpleDateFormat;

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

import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.xml.projekat.model.Obavestenje;
import com.xml.projekat.model.PObavestenje;
import com.xml.projekat.model.PZalbaOdluke;
import com.xml.projekat.model.Resenje;
import com.xml.projekat.model.ZalbaOdluke;

/**
 * 
 * Primer demonstrira metode API-ja za potrebe programskog kreiranja DOM stabla. 
 * Pored programskog kreiranja DOM stabla, primer demonstrira i serijalizaciju
 * DOM stabla na proizvoljan stream (npr. FileOutputStream, System.out, itd.).
 *
 */
@Component
public class DOMWriter {

	private static String TARGET_NAMESPACE = "zavrsni_rad";

	private static String XSI_NAMESPACE = "http://www.w3.org/2001/XMLSchema-instance";
	
	private static DocumentBuilderFactory factory;
	
	private static TransformerFactory transformerFactory;
	
	private Document document;
	
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
	 * Generates sample document object model 
	 * programmatically using DOM API methods. 
	 */
	public void generateDOM() {
		// Kreiranje i postavljanje korenskog elementa
		Element rad = document.createElementNS(TARGET_NAMESPACE, "rad");
		document.appendChild(rad);
		
		rad.setAttributeNS(XSI_NAMESPACE, "xsi:schemaLocation", "http://www.ftn.uns.ac.rs/zavrsni_rad ../xsd/zavrsni_rad.xsd");
		rad.setAttribute("vrsta_rada", "Diplomski rad");		
		
		Element naslovnaStrana = document.createElementNS(TARGET_NAMESPACE, "naslovna_strana");
		rad.appendChild(naslovnaStrana);
		
		Element institucija = document.createElementNS(TARGET_NAMESPACE, "institucija");
		naslovnaStrana.appendChild(institucija);
		
		Element univerzitet = document.createElementNS(TARGET_NAMESPACE, "univerzitet");
		univerzitet.appendChild(document.createTextNode("Univerzitet u Novom Sadu"));
		institucija.appendChild(univerzitet);

		Element fakultet = document.createElementNS(TARGET_NAMESPACE, "fakultet");
		fakultet.appendChild(document.createTextNode("Fakultet tehničkih nauka"));
		institucija.appendChild(fakultet);
		
		Element departman = document.createElementNS(TARGET_NAMESPACE, "departman");
		departman.appendChild(document.createTextNode("Računarstvo i automatika"));
		institucija.appendChild(departman);

		Element katedra = document.createElementNS(TARGET_NAMESPACE, "katedra");
		katedra.appendChild(document.createTextNode("Katedra za informatiku"));
		institucija.appendChild(katedra);
		
		Element autor = document.createElementNS(TARGET_NAMESPACE, "autor");
		naslovnaStrana.appendChild(autor);
		
		Element ime = document.createElementNS(TARGET_NAMESPACE, "ime");
		ime.appendChild(document.createTextNode("Petar"));
		autor.appendChild(ime);
		
		Element prezime = document.createElementNS(TARGET_NAMESPACE, "prezime");
		prezime.appendChild(document.createTextNode("Petrović"));
		autor.appendChild(prezime);
		
		Element broj_indeksa = document.createElementNS(TARGET_NAMESPACE, "broj_indeksa");
		broj_indeksa.appendChild(document.createTextNode("RA 1/2012"));
		autor.appendChild(broj_indeksa);
		
		Element temaSrpski = document.createElementNS(TARGET_NAMESPACE, "tema_rada");
		temaSrpski.setAttribute("jezik", "srpski");
		temaSrpski.appendChild(document.createTextNode("Implementacija podsistema banke u okviru sistema platnog prometa."));
		naslovnaStrana.appendChild(temaSrpski);

		Element temaEngleski = document.createElementNS(TARGET_NAMESPACE, "tema_rada");
		temaEngleski.setAttribute("jezik", "engleski");
		temaEngleski.appendChild(document.createTextNode("Implementation of banking subsystem in an electronic payment system."));
		naslovnaStrana.appendChild(temaEngleski);
		
		Element nivoStudija = document.createElementNS(TARGET_NAMESPACE, "nivo_studija");
		nivoStudija.appendChild(document.createTextNode("OAS"));
		naslovnaStrana.appendChild(nivoStudija);
		
		Element sadrzaj = document.createElementNS(TARGET_NAMESPACE, "sadrzaj");
		sadrzaj.appendChild(document.createComment("Generisati \"sadrzaj\" analogno."));
		rad.appendChild(sadrzaj);
		
		Element poglavlja = document.createElementNS(TARGET_NAMESPACE, "poglavlja");
		poglavlja.appendChild(document.createComment("Generisati \"poglavlja\" analogno."));
		rad.appendChild(poglavlja);
		
	}
	
	public void generateResenje(Resenje resenje) {
		createDocument();
		SimpleDateFormat formatter = new SimpleDateFormat("MM.dd.yyyy.");
		
		Element dokumentResenje = document.createElement("dokument_resenje");
		document.appendChild(dokumentResenje);
		
		dokumentResenje.setAttributeNS(XSI_NAMESPACE, "xsi:schemaLocation", "C:\\Users\\Admin\\Desktop\\VII semestar\\XML I VEB SERVISI\\XML-i-veb-servisi\\dokumenti\\resenje.xsd");
		
		Element nazivResenja = document.createElement("naziv_resenja");
		nazivResenja.appendChild(document.createTextNode(resenje.getNazivOdluka().getNazivResenja()));
		Element odluka = document.createElement("odluka");
		odluka.appendChild(document.createTextNode(resenje.getNazivOdluka().getOdluka()));
		
		nazivResenja.appendChild(odluka);
		
		Element zaglavlje = document.createElement("zaglavlje");
		zaglavlje.appendChild(document.createTextNode("BR"));
		Element brojResenja = document.createElement("broj_resenja");
		brojResenja.appendChild(document.createTextNode(resenje.getZaglavlje().getBrojResenja()));
		Element datum = document.createElement("datum");
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
		
		Element tekstObrazlozenja= document.createElement("tekst_obrazlozenja");
		tekstObrazlozenja.appendChild(document.createTextNode(resenje.getTekstObrazlozenja().getTekst()));
		
		Element paragraf = document.createElement("p");
		
		for(int i = 0; i<resenje.getTekstResenja().getParagrafi().size(); i++) {
			tekstResenja.appendChild(paragraf);
			paragraf.appendChild(document.createTextNode(resenje.getTekstResenja().getParagrafi().get(i)));
		}
		
		for(int i = 0; i<resenje.getTekstObrazlozenja().getParagrafi().size(); i++) {
			tekstObrazlozenja.appendChild(paragraf);
			paragraf.appendChild(document.createTextNode(resenje.getTekstObrazlozenja().getParagrafi().get(i)));
		}
		
		dokumentResenje.appendChild(nazivResenja);
		dokumentResenje.appendChild(zaglavlje);
		dokumentResenje.appendChild(opisPostupka);
		dokumentResenje.appendChild(tekstResenja);
		dokumentResenje.appendChild(tekstObrazlozenja);
		dokumentResenje.appendChild(potpisPoverenika);
		
	}
	
	public void generateDOMObavestenje(Obavestenje ob) {
		
		// Kreiranje i postavljanje korenskog elementa
		Element obavestenje = document.createElement("obavestenje");
		document.appendChild(obavestenje);
	
//		obavestenje.setAttribute("xsi:noNamespaceSchemaLocation", 
//				"C:\\Users\\Korisnik\\Desktop\\XML-i-veb-servisi\\dokumenti\\obavestenjee.xsd");

		//------
		//sad treba popuniti elemente
		Element podnosilacZaheva = document.createElement("podnosilac_zahteva");
		document.appendChild(podnosilacZaheva);
		
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
		
		
		
		//------
		Element organVlasti = document.createElement("organ_vlasti");
		document.appendChild(organVlasti);
		
		Element nazivOrganaVlasti = document.createElement("naziv_organa_vlasti");
		nazivOrganaVlasti.appendChild(document.createTextNode(ob.getNazivOrganaVlasti()));
		organVlasti.appendChild(nazivOrganaVlasti);
		Element sedisteOrgana = document.createElement( "sediste_organa");
		sedisteOrgana.appendChild(document.createTextNode(ob.getSedisteOrgana()));
		organVlasti.appendChild(sedisteOrgana);
			
		
		//-----
		
		Element dostavljeno = document.createElement("dostavljeno");
		dostavljeno.appendChild(document.createTextNode("Достављено:"));
		document.appendChild(dostavljeno);
		

		Element listaPonudjenih = document.createElement( "lista");
		dostavljeno.appendChild(listaPonudjenih);
		
		Element element1 = document.createElement( "element");
		element1.appendChild(document.createTextNode("Именованом"));
		element1.setAttribute("broj", "1");
		listaPonudjenih.appendChild(element1);
		
		Element element2 = document.createElement( "element");
		element2.appendChild(document.createTextNode("Архиви"));
		element2.setAttribute("broj", "2");
		listaPonudjenih.appendChild(element2);
		
		
		
		
		//-------
		Element tekstObavestenja = document.createElement("tekst_obavestenja");
		document.appendChild(tekstObavestenja);
		
		Element brojPredmeta = document.createElement("broj_predmeta");
		brojPredmeta.appendChild(document.createTextNode(ob.getBrojPredmeta()));
		tekstObavestenja.appendChild(brojPredmeta);
		
		Element datum = document.createElement("datum");
		datum.appendChild(document.createTextNode(ob.getDatum()));
		tekstObavestenja.appendChild(datum);
		
		//ovde ide tekst za naslov
		Element naslov = document.createElement("naslov");
		naslov.appendChild(document.createTextNode(" ОБАВЕШТЕЊЕ\r\n" + 
				"                о стављању на увид документа који садржи \r\n" + 
				"                тражену информацију и о изради копије"));
		tekstObavestenja.appendChild(naslov);
		
		
		//paragrafi
		Element paragraf = document.createElement("p");
		paragraf.appendChild(document.createTextNode("На основу члана 16. ст. 1. Закона о слободном приступу информацијама од јавног\r\n" + 
				"            значаја, поступајући по вашем захтеву за слободан приступ информацијама од\r\n"));
		tekstObavestenja.appendChild(paragraf);

		for(Object o : ob.getParagrafi()) {
			if(o instanceof PObavestenje) {
				Element godina = document.createElement("godina");
				godina.appendChild(document.createTextNode(((PObavestenje)o).getGodina()));
				paragraf.appendChild(godina);
				
				paragraf.appendChild(document.createTextNode("год.,којим сте тражили увид у документ/е са информацијама о / у вези са:\r\n" ));
						
				Element trazenaInformacija = document.createElement("trazena_informacija");
				trazenaInformacija.appendChild(document.createTextNode(((PObavestenje)o).getTrazenaInformacija()));
				paragraf.appendChild(trazenaInformacija);
				
				paragraf.appendChild(document.createTextNode("обавештавамо вас да дана " ));
				
				Element dan = document.createElement("dan");
				dan.appendChild(document.createTextNode(((PObavestenje)o).getDan()));
				paragraf.appendChild(dan);
				
				paragraf.appendChild(document.createTextNode(", у" ));
				
				Element sati = document.createElement("sati");
				sati.appendChild(document.createTextNode(((PObavestenje)o).getSati()));
				paragraf.appendChild(sati);
				
				paragraf.appendChild(document.createTextNode(" часова, односно у времену од" ));
				
				Element odSati = document.createElement("od");
				odSati.appendChild(document.createTextNode(((PObavestenje)o).getOdSati()));
				paragraf.appendChild(odSati);
				
				paragraf.appendChild(document.createTextNode("до"));
				
				Element doSati = document.createElement("do");
				doSati.appendChild(document.createTextNode(((PObavestenje)o).getDoSati()));
				paragraf.appendChild(doSati);
				
				paragraf.appendChild(document.createTextNode("часова, у просторијама органа у"));
				
				Element mesto = document.createElement("mesto");
				mesto.appendChild(document.createTextNode(((PObavestenje)o).getMesto()));
				paragraf.appendChild(mesto);
				
				paragraf.appendChild(document.createTextNode("ул."));
				
				Element ulicaOb = document.createElement("ulica");
				ulicaOb.appendChild(document.createTextNode(((PObavestenje)o).getUlica()));
				paragraf.appendChild(ulicaOb);
				
				paragraf.appendChild(document.createTextNode("бр."));
				
				Element brojZgrade = document.createElement("broj_zgrade");
				brojZgrade.appendChild(document.createTextNode(((PObavestenje)o).getBrojZgrade()));
				paragraf.appendChild(brojZgrade);
				
				paragraf.appendChild(document.createTextNode(", канцеларија бр."));
				
				Element brojKancelarije = document.createElement("broj_kancelarije");
				brojKancelarije.appendChild(document.createTextNode(((PObavestenje)o).getBrojKancelarije()));
				paragraf.appendChild(brojKancelarije);
				
				paragraf.appendChild(document.createTextNode("можете извршити увид у документ/е у коме је садржана тражена информација."));
				
			}
			
		}
		
		///------

		Element paragraf2 = document.createElement("p");
		paragraf2.appendChild(document.createTextNode("Том приликом, на ваш захтев, може вам се издати и копија документа са траженом \r\n" + 
				"            информацијом."));
		tekstObavestenja.appendChild(paragraf2);

		
	    Element paragraf3 = document.createElement("p");
	    paragraf3.appendChild(document.createTextNode("Трошкови су утврђени Уредбом Владе Републике Србије („Сл. гласник РС“, бр. 8/06), и\r\n" + 
	    		"            то: копија стране А4 формата износи 3 динара, А3 формата 6 динара, CD 35 динара, \r\n" + 
	    		"            дискете 20 динара, DVD 40 динара, аудио-касета – 150 динара, видео-касета 300 динара,\r\n" + 
	    		"            претварање једне стране документа из физичког у електронски облик – 30 динара.\r\n" + 
	    		"        "));
		tekstObavestenja.appendChild(paragraf3);
		
		//dodaj tekst
		Element paragraf4 = document.createElement("p");
		tekstObavestenja.appendChild(paragraf4);
		paragraf4.appendChild(document.createTextNode("Износ укупних трошкова израде копије документа по вашем захтеву износи "));
		Element novcanaNaknada = document.createElement("novcana_naknada");
		paragraf4.appendChild(document.createTextNode(" динара и уплаћује се на жиро-рачун Буџета Републике Србије бр. 840-742328-843-30, с позивом на број 97 – ознака шифре општине/града где се налази орган власти (из Правилника о условима и начину вођења рачуна – „Сл. гласник РС“, 20/07... 40/10). "));
		paragraf4.appendChild(novcanaNaknada);
		
		Element mestoPecata = document.createElement( "mesto_pecata");
		mestoPecata.appendChild(document.createTextNode("(М.П.)"));
		
		document.appendChild(mestoPecata);
		
		
	}
	
	public Document generateZalbaOdluke(ZalbaOdluke zo) {
		createDocument();
		// Kreiranje i postavljanje korenskog elementa
		Element zalbaOdluke = document.createElement("zalba_odluke");
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
			if(pzo.getBroj_zalbe() != null && pzo.getGodina_odbijanja() != null) {
				p.appendChild(document.createTextNode("Број"));
				Element brojZalbe = document.createElement("broj_zalbe");
				brojZalbe.appendChild(document.createTextNode(pzo.getBroj_zalbe()));
				p.appendChild(brojZalbe);
				p.appendChild(document.createTextNode("од"));
				Element godOdbijanja = document.createElement("godina_odbijanja");
				godOdbijanja.appendChild(document.createTextNode(pzo.getGodina_odbijanja()));
				p.appendChild(godOdbijanja);
				p.appendChild(document.createTextNode("године."));
				
				
			}else if(pzo.getDatum() != null && pzo.getRazlog() != null) {
				p.appendChild(document.createTextNode("Наведеном одлуком органа власти(решењем, закључком, обавештењем у писаној" + 
						" форми са елементима одлуке) , супротно закону, одбијен-одбачен је мој захтев који" + 
						" сам поднео/ла- упутио/ла дана"));
				Element datum = document.createElement("datum");
				datum.appendChild(document.createTextNode(pzo.getDatum()));
				p.appendChild(datum);
				p.appendChild(document.createTextNode("године и тако ми ускраћено-онемогућено остваривање уставног и законског права на" + 
						" слободан приступ информацијама од јавног значаја. Одлуку побијам у целости, односно" + 
						" у делу којим"));
				Element razlog = document.createElement("razlog");
				razlog.appendChild(document.createTextNode(pzo.getRazlog()));
				p.appendChild(razlog);
				p.appendChild(document.createTextNode("јер није заснована на Закону о слободном приступу информацијама од јавног значаја."));
				
			}else {
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
		datum.appendChild(document.createTextNode(zo.getDatum()));
		podaciOTrenutku.appendChild(datum);
		podaciOTrenutku.appendChild(document.createTextNode(" године"));
		tekstZalbe.appendChild(podaciOTrenutku);

		Element napomena = document.createElement("napomena");
		napomena.appendChild(document.createTextNode("Напомена:"));
		zalbaOdluke.appendChild(napomena);
		Element tacke = document.createElement("tacke");
		napomena.appendChild(tacke);
		
		for (int t = 0;t<zo.getTackeNapomene().size();t++) {
			Element tacka = document.createElement("tacka");
			tacka.setAttribute("broj",Integer.toString(t+1));
			tacka.appendChild(document.createTextNode(zo.getTackeNapomene().get(t)));
			tacke.appendChild(tacka);
		}
		
		// stampa na konzolu
		transform(System.out);
		
		// stampa u fajl po izboru
//		try {
//			transform(new FileOutputStream("C:\\Users\\teodo\\Desktop\\NOVIXMLZALBA.xml"));
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
		
		return document;
	
	}
	/**
	 * Serializes DOM tree to an arbitrary OutputStream.
	 */
	public void transform(OutputStream out) {
		try {

			// Kreiranje instance objekta zaduzenog za serijalizaciju DOM modela
			Transformer transformer = transformerFactory.newTransformer();

			// Indentacija serijalizovanog izlaza
			transformer.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "2");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");

			// Nad "source" objektom (DOM stablo) vrši se transformacija
			DOMSource source = new DOMSource(document);

			// Rezultujući stream (argument metode) 
			StreamResult result = new StreamResult(out);

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
	
	public static void main(String args[]) {
//
//		String filePath = null;
//
//		System.out.println("[INFO] DOM Parser");
//
//		if (args.length != 1) {
//
//			filePath = "data/xml/zavrsni_rad.xml";
//
//			System.out.println("[INFO] No input file, using default \""	+ filePath + "\"");
//
//		} else {
//			filePath = args[0];
//		}

		DOMWriter handler = new DOMWriter();

		// Kreiranje Document čvora
		handler.createDocument();

		// Generisanje DOM stabla
		handler.generateDOM();
		
		// Prikaz sadržaja (isprobati sa FileOutputStream-om)
		handler.transform(System.out);
		
		/*
		try {
			handler.transform(new FileOutputStream("data/xml/zavrsni_rad_out_3.xml"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		*/
	}
}
