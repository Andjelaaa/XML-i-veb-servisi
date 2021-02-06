package com.xml.projekat.rdf;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.text.StringSubstitutor;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.RDFNode;

import com.xml.projekat.rdf.AuthenticationUtilities.ConnectionProperties;


public class FusekiReader {
	
	private static final String QUERY_FILEPATH = "src/main/resources/podaci/rdf/query.rq";
	private static final String RDF_TEMP_FILE = "src/main/resources/podaci/rdf/metadataRDF.xml";
	private static final String JSON_TEMP_FILE = "src/main/resources/podaci/rdf/metadataJSON.json";
		
	private FusekiReader() {}

	public static void executeQuery(String metadataGraphUri) throws IOException {
		ConnectionProperties conn = AuthenticationUtilities.loadProperties(metadataGraphUri);
		// Querying the first named graph with a simple SPARQL query
			System.out.println("[INFO] Selecting the triples from the named graph \"" + metadataGraphUri + "\".");
			String sparqlQuery = SparqlUtil.selectData(conn.dataEndpoint + metadataGraphUri, "?s ?p ?o");
			
			// Create a QueryExecution that will access a SPARQL service over HTTP
			QueryExecution query = QueryExecutionFactory.sparqlService(conn.queryEndpoint, sparqlQuery);
			
			// Query the SPARQL endpoint, iterate over the result set...
			ResultSet results = query.execSelect();
		
			String varName;
			RDFNode varValue;
			
			while(results.hasNext()) {
			    
				// A single answer from a SELECT query
				QuerySolution querySolution = results.next() ;
				Iterator<String> variableBindings = querySolution.varNames();
				
				// Retrieve variable bindings
			    while (variableBindings.hasNext()) {
			   
			    	varName = variableBindings.next();
			    	varValue = querySolution.get(varName);
			    	
			    	System.out.println(varName + ": " + varValue);
			    }
			    System.out.println();
			}
			
			// Querying the other named graph
			System.out.println("[INFO] Selecting the triples from the named graph \"" + metadataGraphUri + "\".");
			sparqlQuery = SparqlUtil.selectData(conn.dataEndpoint + metadataGraphUri, "?s ?p ?o");
			
			// Create a QueryExecution that will access a SPARQL service over HTTP
			query = QueryExecutionFactory.sparqlService(conn.queryEndpoint, sparqlQuery);

			
			// Query the collection, dump output response as XML
			results = query.execSelect();
			
			//ResultSetFormatter.outputAsXML(System.out, results);
			
			query.close() ;
			
			System.out.println("[INFO] End.");
	}
	
	public static ArrayList<String> executeQuery( Map<String, String> params, String metadataGraphUri) throws IOException {
		
		ConnectionProperties conn = AuthenticationUtilities.loadProperties(metadataGraphUri);
		
		String sparqlQueryTemplate = readFile(QUERY_FILEPATH, StandardCharsets.UTF_8);
		System.out.println("Query: " + sparqlQueryTemplate);
		String sparqlQuery = StringSubstitutor.replace(sparqlQueryTemplate, params, "{{", "}}");
		System.out.println("Query: " + sparqlQuery);
		
		// Create a QueryExecution that will access a SPARQL service over HTTP
		QueryExecution query = QueryExecutionFactory.sparqlService(conn.queryEndpoint, sparqlQuery);
		// Query the SPARQL endpoint, iterate over the result set...
		ResultSet results = query.execSelect();
		
		String varName;
		RDFNode varValue;
		ArrayList<String> foundURIs = new ArrayList<String>();
		
		while(results.hasNext()) {
		    
			// A single answer from a SELECT query
			QuerySolution querySolution = results.next() ;
			Iterator<String> variableBindings = querySolution.varNames();
			
			// Retrieve variable bindings
		    while (variableBindings.hasNext()) {
		    	varName = variableBindings.next();
		    	varValue = querySolution.get(varName);
		    	System.out.println(varName + ": " + varValue);
		    	if (varName.contains("URI")) {
		    		String value = varValue.toString();
//			    	value = value.substring(0, value.lastIndexOf("^")-1);
			    	if (!foundURIs.contains(value)) {
			    		foundURIs.add(value);
			    	}
		    	}
		    }
		    System.out.println();
		}
		
	    ResultSetFormatter.outputAsXML(System.out, results);
		query.close() ;
		System.out.println("[INFO] SPARQL Query End.");
		return foundURIs;
	}
	
	public static void findRDF( Map<String, String> params, String metadataGraphUri, String queryPath) throws IOException {
		
		ConnectionProperties conn = AuthenticationUtilities.loadProperties(metadataGraphUri);
		
		String sparqlQueryTemplate = readFile(queryPath, StandardCharsets.UTF_8);
		System.out.println("Query: " + sparqlQueryTemplate);
		String sparqlQuery = StringSubstitutor.replace(sparqlQueryTemplate, params, "{{", "}}");
		System.out.println("Query: " + sparqlQuery);
		
		// Create a QueryExecution that will access a SPARQL service over HTTP
		QueryExecution query = QueryExecutionFactory.sparqlService(conn.queryEndpoint, sparqlQuery);
		// Query the SPARQL endpoint, iterate over the result set...
		ResultSet results = query.execSelect();
		
	    ResultSetFormatter.outputAsXML(new FileOutputStream(new File(RDF_TEMP_FILE)), results);
		query.close() ;
		System.out.println("[INFO] SPARQL Query End.");
	}
	
	public static void findJsonMetadata( Map<String, String> params, String metadataGraphUri, String queryPath) throws IOException {
		
		ConnectionProperties conn = AuthenticationUtilities.loadProperties(metadataGraphUri);
		
		String sparqlQueryTemplate = readFile(queryPath, StandardCharsets.UTF_8);
		System.out.println("Query: " + sparqlQueryTemplate);
		String sparqlQuery = StringSubstitutor.replace(sparqlQueryTemplate, params, "{{", "}}");
		System.out.println("Query: " + sparqlQuery);
		
		// Create a QueryExecution that will access a SPARQL service over HTTP
		QueryExecution query = QueryExecutionFactory.sparqlService(conn.queryEndpoint, sparqlQuery);
		// Query the SPARQL endpoint, iterate over the result set...
		ResultSet results = query.execSelect();
		
	    ResultSetFormatter.outputAsJSON(new FileOutputStream(new File(JSON_TEMP_FILE)), results);
		query.close() ;
		System.out.println("[INFO] SPARQL Query End.");
	}
	public static String readFile(String path, Charset encoding) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}
}
