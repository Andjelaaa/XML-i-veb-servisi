<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet  xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:d="http://www.ftn.uns.ac.rs/xpath/examples" version="2.0">
    
    <xsl:template match="/">
    	<html>
            <head>
                <title>ZALBA ODLUKE</title>
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
            	<br></br>
            	<xsl:variable name="zahtevURI" select="d:zalba_odluke/d:zahtev_uri"/>
            		<p><a href="http://localhost:4200/zahtev/{$zahtevURI}">
							Referencirani zahtev
						</a></p>
            	<br></br>
            	<div style="text-align:center;">
	            	<h3>ZALBA  PROTIV  ODLUKE ORGANA  VLASTI KOJOM JE 
					<u>ODBIJEN ILI ODBACEN ZAHTEV</u> ZA PRISTUP INFORMACIJI</h3>
	            	<h3>Povereniku za informacije od javnog znacaja i zastitu podataka o licnosti</h3>
	            	<p>Adresa za postu: Beograd, Bulevar kralja Aleksandra br. 15</p>
	            	
	            	<h1>Z A L B A</h1>
	            	<p>(<u>
                            <xsl:value-of select="d:zalba_odluke/d:podnosilac_zalbe/d:naziv_podnosioca/d:ime"/>
                            <xsl:text> </xsl:text>
                            <xsl:value-of select="d:zalba_odluke/d:podnosilac_zalbe/d:naziv_podnosioca/d:prezime"/>
                            <xsl:text> </xsl:text>
                            
                            <xsl:value-of select="d:zalba_odluke/d:podnosilac_zalbe/d:adresa/d:ulica"/>
                            <xsl:text> </xsl:text>
                            <xsl:value-of select="d:zalba_odluke/d:podnosilac_zalbe/d:adresa/d:broj"/>
                            <xsl:text> </xsl:text>
                            
                            <xsl:value-of select="d:zalba_odluke/d:podnosilac_zalbe/d:adresa/d:grad"/>
                            )
                            </u></p>
                    <p>Ime i prezime/naziv/i adresa ili sediste zalioca </p>
                    <p>protiv resenja/zakljucka</p>
                    <p>(
                            <xsl:value-of select="d:zalba_odluke/d:organ_vlasti/d:naziv_organa"/>
                            )</p>
                    <p>(naziv organa koji je doneo odluku)</p>
            	</div>
            	
            	<xsl:for-each select="d:zalba_odluke/d:tekst_zalbe/d:p">
            		<xsl:choose>
            			 <xsl:when test="d:razlog">
            			  <p>
         					Navedenom odlukom organa vlasti (rešenjem, zakljuckom, obaveštenjem u pisanoj 
	                        formi sa elementima odluke) , suprotno zakonu, odbijen-odbacen je moj zahtev koji 
	                        sam podneo/la-uputio/la dana 
	                        
	                        <span><u> <xsl:value-of select="d:datum"/></u>
	                        </span>
	                        godine i tako mi uskraceno-onemoguceno 
	                        ostvarivanje ustavnog i zakonskog prava na slobodan pristup informacijama od javnog
	                         znacaja. Odluku pobijam u celosti, odnosno u delu kojim
	                        <span><u> <xsl:value-of select="d:razlog"/></u>
	                        </span>
	                        jer nije zasnovana na Zakonu o slobodnom pristupu informacijama od javnog značaja.
	                        </p>
						</xsl:when>
                        <xsl:when test="d:broj_zalbe">
                        <p>
	                        Broj
	                        <span><u><xsl:value-of select="d:broj_zalbe"/></u>
      
	                        </span>  
	                        od
	                        <span><u> <xsl:value-of select="d:godina_odbijanja"/>
	                            </u>                           
	                        </span> 
	                        godine.
	                        </p>   
                        </xsl:when>
           				<xsl:otherwise>
           				<p>
                         	<xsl:value-of select="text()"></xsl:value-of> </p>
                         </xsl:otherwise>
            		
            		</xsl:choose>    	
            	</xsl:for-each>
            	<br></br>
            	
            	<table style="width:100%">
				<tr>
				<td style="vertical-align:top"><div style="float: left;">
                	<span>U </span>
                	<u><xsl:value-of select="d:zalba_odluke/d:tekst_zalbe/d:podaci_o_trenutku/d:mesto"/></u>
                    <br></br>
                    <span>dana</span>
                    <u><xsl:value-of select="d:zalba_odluke/d:tekst_zalbe/d:podaci_o_trenutku/d:datum"/></u>
                    <span>godine</span>
                
                </div></td>
				<td style="text-align:right;"><div style="float: right" >
                	<u><xsl:value-of select="d:zalba_odluke/d:podnosilac_zalbe/d:naziv_podnosioca/d:ime"/>
                            <xsl:text> </xsl:text>
                            <xsl:value-of select="d:zalba_odluke/d:podnosilac_zalbe/d:naziv_podnosioca/d:prezime"/>
                        </u>
					<br></br>
					<span>Podnosilac zalbe/Ime i prezime</span>
					<br></br>
                    <u>
                    <xsl:value-of select="d:zalba_odluke/d:podnosilac_zalbe/d:adresa/d:ulica"/>
                    <xsl:text> </xsl:text>
                    <xsl:value-of select="d:zalba_odluke/d:podnosilac_zalbe/d:adresa/d:broj"/>
                    <xsl:text> </xsl:text>
                    <xsl:value-of select="d:zalba_odluke/d:podnosilac_zalbe/d:adresa/d:grad"/>
                        
                    </u>
                    <br></br>
                    <span>adresa</span>
                    <br></br>
                    <u><xsl:value-of select="d:zalba_odluke/d:podnosilac_zalbe/d:drugi_podaci_za_kontakt"/>
                    </u>
                    <br></br>
                    <span>drugi podaci za kontakt</span>
                    <br></br>
                    <span>________________</span>
                    <br></br>
                    <span>Potpis</span>

                
                </div></td>
				</tr>
  
                </table>
                
                <br></br>
                <br></br>
                <div style="text-align:left;">
                <p><b> Napomena:</b> </p>
                <p>U zalbi se mora navesti odluka koja se pobija (resenje, zakljucak, obavestenje), naziv organa koji je odluku doneo, kao i broj i datum odluke. Dovoljno je da zalilac navede u zalbi u kom pogledu je nezadovoljan odlukom, s tim da zalbu ne mora posebno obrazloziti. Ako zalbu izjavljuje na ovom obrascu, dodatno obrazlozenje može  posebno priloziti.</p>
                <p>Uz zalbu obavezno priloziti kopiju podnetog zahteva i dokaz o njegovoj predaji-upucivanju organu kao i kopiju odluke organa koja se osporava zalbom.</p>
                
                </div>
                <br></br>
                <br></br>
            
            </body>
        </html>
    
    </xsl:template>
    
</xsl:stylesheet>