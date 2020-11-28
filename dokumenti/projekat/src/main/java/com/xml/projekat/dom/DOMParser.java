package com.xml.projekat.dom;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
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

import static org.apache.xerces.jaxp.JAXPConstants.JAXP_SCHEMA_LANGUAGE;
import static org.apache.xerces.jaxp.JAXPConstants.W3C_XML_SCHEMA;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Scanner;;


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
	
	public Document buildDocumentFromFile(String filePath) throws SAXException, IOException, ParserConfigurationException {
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc =  builder.parse(new File(filePath));
		return doc;
	}
	
	public String getDocumentAsString(Document document) throws TransformerException {
		StringWriter sw = new StringWriter();
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer = tf.newTransformer();
		
		transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
		transformer.setOutputProperty(OutputKeys.METHOD,"xml");
		transformer.setOutputProperty(OutputKeys.INDENT,"yes");
		transformer.setOutputProperty(OutputKeys.ENCODING,"UTF-8");

		transformer.transform(new DOMSource(document), new StreamResult(sw));
		
		return sw.toString();
		
	}
	
	
	public void printElement() {
		
		System.out.println("Prikaz sadržaja DOM stabla parsiranog XML dokumenta.");
		Scanner scanner = new Scanner(System.in);
	    String elementName, attrName, choice = "";
	    Element element;
	    
	    while (!choice.equals("*")) {
		    
	    	System.out.println("\n[INPUT] Unesite 0 - za prikaz celog dokumenta, 1 - prikaz elemenata, 2 - prikaz atributa, * - kraj: ");
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
	    				System.out.println("\n" + (i+1) + ". element ima vrednost atributa \"" + attrName + "\": " + element.getAttribute(attrName) + ".");
	    			} else { 
	    				System.out.println("\n" + (i+1) + ". element \"" + elementName + "\" ne poseduje atribut \"" + attrName + "\".");
	    			}
	    		}
		    	
		    } else if (choice.equals("*")){
		    	break;
		    } else {
		    	System.out.println("Nepoznata komanda.");
		    }
	    }
	    
		scanner.close();
		System.out.println("[INFO] Kraj.");
	}
	
	/**
	 * A recursive helper method for iterating 
	 * over the elements of a DOM tree.
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
					if (i < attributes.getLength()-1)
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
			
		}
		else if (node instanceof Text) {
			Text text = (Text) node;
			
			if (text.getTextContent().trim().length() > 0)
				System.out.println("CHARACTERS: " + text.getTextContent().trim());
		}
		else if (node instanceof CDATASection) {
			System.out.println("CDATA: " + node.getNodeValue());
		}
		else if (node instanceof Comment) {
			System.out.println("COMMENT: " + node.getNodeValue());
		}
		else if (node instanceof ProcessingInstruction) {
			System.out.print("PROCESSING INSTRUCTION: ");

			ProcessingInstruction instruction = (ProcessingInstruction) node;
			System.out.print("data: " + instruction.getData());
			System.out.println(", target: " + instruction.getTarget());
		}
		else if (node instanceof Entity) {
			Entity entity = (Entity) node;
			System.out.println("ENTITY: " + entity.getNotationName());
		}
	}

}