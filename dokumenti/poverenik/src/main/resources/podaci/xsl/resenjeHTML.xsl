<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:d="http://www.ftn.uns.ac.rs/xpath/examples"
	xmlns:fo="http://www.w3.org/1999/XSL/Format" version="2.0">

	<xsl:template match="/">
		<html>
            <head>
                <title>RESENJE</title>
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
            <xsl:choose>
					<xsl:when test="d:dokument_resenje/d:zalba_odluke_uri">
						<xsl:variable name="zalbaURI" select="d:dokument_resenje/d:zalba_odluke_uri"/>
            	
            		<p><a href="http://localhost:4201/appeal_decision_review/{$zalbaURI}">
							Referencirana zalba
						</a></p>
					</xsl:when>
					<xsl:otherwise>
						<xsl:variable name="zalba2URI" select="d:dokument_resenje/d:zalba_cutanje_uri"/>
            	
            		<p><a href="http://localhost:4201/appeal_silence_review/{$zalba2URI}">
							Referencirana zalba
						</a></p>
					</xsl:otherwise>
				</xsl:choose>
            
        
            	<br></br>
            	<table style="width=100%">
            		<tr>
            		<td>
	            	<div style="float:left;">
		            	<p><u>
		            	
						<xsl:text>  </xsl:text>
						<xsl:value-of
										select="d:dokument_resenje/d:naziv_resenja/d:odluka" />
		            	</u></p>
		            	
			           	<p>
							BR.
							<xsl:value-of
								select="d:dokument_resenje/d:zaglavlje/d:broj_resenja" />
						</p>
	            	</div>
	            	</td>
	            	<td >
	            	
	            	</td>
	            	
	            	</tr>
            	</table>
            	<div style="float:right; text-align:right;">
	            		Datum:
								<xsl:value-of
									select="d:dokument_resenje/d:zaglavlje/d:datum" />          	
	            	</div>
            	
            	<br></br>
            	<br></br>
            	<div style="width=100%">
            	
            	<p><xsl:value-of select="d:dokument_resenje/d:opis_postupka" /></p>
            	</div>
            	<div style="text-align:center"><h3>R E S E NJ E</h3></div>

            	<xsl:for-each
					select="d:dokument_resenje/d:tekst_resenja/d:p">
					<p><xsl:value-of select="text()"></xsl:value-of></p>
										
				</xsl:for-each>
            	<div style="text-align:center"><h4>Obrazlozenje</h4></div>
            	
            	<xsl:for-each
						select="d:dokument_resenje/d:tekst_obrazlozenja/d:p">
						<p><xsl:value-of select="text()"></xsl:value-of></p>	
				</xsl:for-each>
				<br></br>
				<div style="float:right">
					<p>POVERENIK</p>
					<xsl:value-of
							select="d:dokument_resenje/d:potpis_poverenika" />
				
				</div>
            	            
            </body>
        </html>

	</xsl:template>

</xsl:stylesheet>