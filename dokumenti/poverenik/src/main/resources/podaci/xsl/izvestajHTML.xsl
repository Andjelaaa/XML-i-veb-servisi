<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet  xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:d="http://www.ftn.uns.ac.rs/xpath/examples" version="2.0">
    
    <xsl:template match="/">
    	<html>
            <head>
                <title>IZVESTAJ</title>
                <style type="text/css">
                    body {
				        height: 842px;
				        width: 595px;
				        /* to centre page on screen*/
				        margin-left: auto;
				        margin-right: auto;
				    }
                </style>
            </head>
            <body>
            	<div>
            	<br></br>
            	<br></br>

	            	<div style="text-align:center;">
	            		<br></br>
	            		<br></br>
	                	<h1 style="text-align:center;"> I Z V E S T A J </h1>
	                	<h2 style="text-align:center;">Primena Zakona o slobodnom pristupu informacijama od javnog znacaja u
	                            <xsl:value-of select="d:message/d:godina"/>. god.</h2>
	                </div>
	               	 	<br></br>
	                	<br></br>
	                	<h4>1. Zahtevi</h4>
	                	<br></br>
	                	<table border="1px" style="border-collapse: collapse;">
	                		<thead>
	                			<tr style="background-color:#8cb3d9">
	                				<th background-color="#4caf50" font-family="sans-serif" color="white" padding="10px" font-weight="bold">Broj podnetih zahteva</th>
	                				<th background-color="#4caf50" font-family="sans-serif" color="white" padding="10px" font-weight="bold">Broj odbijenih zahteva</th>
	                				<th background-color="#4caf50" font-family="sans-serif" color="white" padding="10px" font-weight="bold">Broj usvojenih-delimicno usvojenih zahteva</th>
	                			</tr>
	                		</thead>
	                		<tbody>
	                			<xsl:variable name="usvojeni" select="d:message/d:br_podnetih_zahteva - d:message/d:br_odbijenih_zahteva"/>
	                			<tr style="text-align: center;  vertical-align: middle;">
	                				<td padding="10px"><xsl:value-of select="d:message/d:br_podnetih_zahteva"/></td>
	                				<td padding="10px"><xsl:value-of select="d:message/d:br_odbijenih_zahteva"/></td>
	                				<td padding="10px"><xsl:value-of select="$usvojeni"/></td>	                			
	                			</tr>
	                		</tbody>
	                	</table>
	                	<br></br>
	                	<br></br>
	                	<h4>1. Zalbe</h4>
	                	<p>Broj podnetih zalbi u godini: <xsl:value-of select="d:message/d:br_zalbi"/></p>
                	
            	</div>
      	   </body>
      	</html>
      
    </xsl:template>
    
</xsl:stylesheet>