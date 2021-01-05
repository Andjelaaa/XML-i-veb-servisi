package com.xml.projekat.rdf;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.update.UpdateExecutionFactory;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateProcessor;
import org.apache.jena.update.UpdateRequest;

import com.xml.projekat.rdf.AuthenticationUtilities.ConnectionProperties;


public class FusekiWriter {
	
	//private static final String DOKUMENTI_METADATA_GRAPH_URI = "/dokumenti";
	
	public static void saveRDF(String rdfFilePath, String metadataGraphUri) throws IOException {
		
		System.out.println("[INFO] Loading triples from an RDF/XML to a model...");
		ConnectionProperties conn = AuthenticationUtilities.loadProperties();
		System.out.println("[INFO] Rendering model aaaaaa");
		// Creates a default model
		Model model = ModelFactory.createDefaultModel();
		System.out.println("[INFO] Rendering model as RDF/XML.bbbbbbbbbbbbbb.");
		model.read(rdfFilePath);
		System.out.println("[INFO] Rendering model as RDF/XMcccccccccccccccccc.");
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.out.println("[INFO] Rendering modeldvdgggdg");
		model.write(out, SparqlUtil.NTRIPLES);

		System.out.println("[INFO] Rendering model as RDF/XML...");
		model.write(System.out, SparqlUtil.RDF_XML);

		/*
		 * Create UpdateProcessor, an instance of execution of an UpdateRequest.
		 * UpdateProcessor sends update request to a remote SPARQL update service.
		 */
		
		UpdateRequest request = UpdateFactory.create() ;
		
		//TODO Remove this later
		// This will delete all of the triples in all of the named graphs 
		// request.add(SparqlUtil.dropAll()); 
		UpdateProcessor processor = UpdateExecutionFactory.createRemote(request, conn.updateEndpoint);
		processor.execute();

		// Creating the first named graph and updating it with RDF data
		System.out.println("[INFO] Writing the triples to a named graph \"" + metadataGraphUri + "\".");
		String sparqlUpdate = SparqlUtil.insertData(conn.dataEndpoint + metadataGraphUri,
				new String(out.toByteArray()));
		System.out.println(sparqlUpdate);

		// UpdateRequest represents a unit of execution
		UpdateRequest update = UpdateFactory.create(sparqlUpdate);

		processor = UpdateExecutionFactory.createRemote(update, conn.updateEndpoint);
		processor.execute();
	}
}
