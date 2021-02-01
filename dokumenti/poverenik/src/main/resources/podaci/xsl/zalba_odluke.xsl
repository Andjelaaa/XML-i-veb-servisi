<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet  xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:d="http://www.ftn.uns.ac.rs/xpath/examples" 
    xmlns:fo="http://www.w3.org/1999/XSL/Format" version="2.0">
    
    <xsl:template match="/">
        <fo:root>
            <fo:layout-master-set>
                <fo:simple-page-master master-name="zalbaodluke-page">
                    <fo:region-body margin="0.75in"/>
                </fo:simple-page-master>
            </fo:layout-master-set>
            
            <fo:page-sequence master-reference="zalbaodluke-page">
                <fo:flow flow-name="xsl-region-body">

					<fo:block font-family="sans-serif" font-size="13px" font-weight="bold" padding="10px" text-align="center">
                        ŽALBA  PROTIV  ODLUKE ORGANA  VLASTI KOJOM JE 
						<fo:inline text-decoration="underline">ODBIJEN ILI ODBACEN ZAHTEV</fo:inline> ZA PRISTUP INFORMACIJI            
                    </fo:block>
                    <fo:block font-family="sans-serif" font-size="12px" font-weight="bold" padding="1px" text-align="left">
                        <fo:inline >
                            Povereniku za informacije od javnog znacaja i zastitu podataka o licnosti
                        </fo:inline>            
                    </fo:block>
                    <fo:block font-family="sans-serif" font-size="12px" padding="1px" text-align="left">
                        <fo:inline >
                            Adresa za postu: Beograd, Bulevar kralja Aleksandra br. 15
                        </fo:inline>            
                    </fo:block>

                    
                    <fo:block font-family="sans-serif" font-size="24px" font-weight="bold" padding="10px" text-align="center">
                        Ž A L B A            
                    </fo:block>
                   <fo:block font-family="sans-serif" font-size="12px" padding="1px"  text-decoration="underline" text-align="center">
                        <fo:inline >(
                            <xsl:value-of select="d:zalba_odluke/d:podnosilac_zalbe/d:naziv_podnosioca/d:ime"/>
                            <xsl:text>  </xsl:text>
                            <xsl:value-of select="d:zalba_odluke/d:podnosilac_zalbe/d:naziv_podnosioca/d:prezime"/>
                            <xsl:text>  </xsl:text>
                            
                            <xsl:value-of select="d:zalba_odluke/d:podnosilac_zalbe/d:adresa/d:ulica"/>
                            <xsl:text>  </xsl:text>
                            <xsl:value-of select="d:zalba_odluke/d:podnosilac_zalbe/d:adresa/d:broj"/>
                            <xsl:text>  </xsl:text>
                            
                            <xsl:value-of select="d:zalba_odluke/d:podnosilac_zalbe/d:adresa/d:grad"/>
                            )
                        </fo:inline>            
                    </fo:block>
                    <fo:block font-family="sans-serif" font-size="12px" padding="1px" text-align="center">
                        Ime i prezime/naziv/i adresa ili sediste zalioca
                    </fo:block>
                    <fo:block font-family="sans-serif" font-size="12px" padding="1px" text-align="center">
                        protiv resenja-zakljucka
                    </fo:block>
                    <fo:block font-family="sans-serif" font-size="12px" padding="1px"  text-decoration="underline" text-align="center">
                        <fo:inline >(
                            <xsl:value-of select="d:zalba_odluke/d:organ_vlasti/d:naziv_organa"/>
                            )
                        </fo:inline>            
                    </fo:block>
                    <fo:block font-family="sans-serif" font-size="12px" padding="1px" text-align="center">
                        (naziv organa koji je doneo odluku)
                    </fo:block>
     
                    <xsl:for-each select="d:zalba_odluke/d:tekst_zalbe/d:p">
                    	<fo:block>
                    	
                    	
                    	<fo:block >
                            <xsl:choose>
                                <xsl:when test="d:razlog">
                                     <fo:block font-family="sans-serif" font-size="12px" padding="1px"   text-align="left">
				                        Navedenom odlukom organa vlasti (rešenjem, zakljuckom, obaveštenjem u pisanoj 
				                        formi sa elementima odluke) , suprotno zakonu, odbijen-odbacen je moj zahtev koji 
				                        sam podneo/la-uputio/la dana 
				                        
				                        <fo:inline text-decoration="underline">
				                            <xsl:value-of select="d:datum"/>
				                            
				                        </fo:inline>
				                        godine i tako mi uskraceno-onemoguceno 
				                        ostvarivanje ustavnog i zakonskog prava na slobodan pristup informacijama od javnog
				                         znacaja. Odluku pobijam u celosti, odnosno u delu kojim
				                        <fo:inline text-decoration="underline">
				                            <xsl:value-of select="d:razlog"/>
				                            
				                        </fo:inline>
				                        jer nije zasnovana na Zakonu o slobodnom pristupu informacijama od javnog značaja.
				
				                                 
				                    </fo:block>
                                </xsl:when>
                                <xsl:when test="d:broj_zalbe">
                                    <fo:block font-family="sans-serif" font-size="12px" padding="1px"   text-align="left">
				                        Broj
				                        <fo:inline text-decoration="underline">
				                            <xsl:value-of select="d:broj_zalbe"/>
				                            
				                        </fo:inline>  
				                        od
				                        <fo:inline text-decoration="underline">
				                            <xsl:value-of select="d:godina_odbijanja"/>
				                            
				                        </fo:inline> 
				                        godine.          
				                    </fo:block>
                                </xsl:when>
                                <xsl:otherwise>
                                	<xsl:value-of select="text()"></xsl:value-of> 
                                </xsl:otherwise>
                            </xsl:choose>
                            
                        </fo:block>
                    	</fo:block>          
                    </xsl:for-each>
      				<fo:block font-family="sans-serif" font-size="12px" padding="1px" text-align="left">
           				<fo:inline >
                            <xsl:text>U </xsl:text>
                            <xsl:value-of select="d:zalba_odluke/d:tekst_zalbe/d:podaci_o_trenutku/d:mesto"/>
                        </fo:inline> 
                    </fo:block>
                    <fo:block font-family="sans-serif" font-size="12px" padding="1px" text-align="left">
           				<fo:inline >
                            <xsl:text>dana </xsl:text>
                            <xsl:value-of select="d:zalba_odluke/d:tekst_zalbe/d:podaci_o_trenutku/d:datum"/>
                            <xsl:text>godine</xsl:text>
                        </fo:inline> 
                    </fo:block>
                    
                    
                    <fo:block font-family="sans-serif" font-size="12px" padding="1px" text-align="right" text-decoration="underline">
           				<fo:inline >
                            <xsl:value-of select="d:zalba_odluke/d:podnosilac_zalbe/d:naziv_podnosioca/d:ime"/>
                            <xsl:text>  </xsl:text>
                            <xsl:value-of select="d:zalba_odluke/d:podnosilac_zalbe/d:naziv_podnosioca/d:prezime"/>
                        </fo:inline> 
                    </fo:block>
                    <fo:block font-family="sans-serif" font-size="12px" padding="1px" text-align="right">
                        Podnosilac zalbe/Ime i prezime
    
                    </fo:block>
                    
                    <fo:block font-family="sans-serif" font-size="12px" padding="1px" text-align="right" text-decoration="underline">
           				<fo:inline >
                            <xsl:value-of select="d:zalba_odluke/d:podnosilac_zalbe/d:adresa/d:ulica"/>
                            <xsl:text>  </xsl:text>
                            <xsl:value-of select="d:zalba_odluke/d:podnosilac_zalbe/d:adresa/d:broj"/>
                            <xsl:text>  </xsl:text>
                            <xsl:value-of select="d:zalba_odluke/d:podnosilac_zalbe/d:adresa/d:grad"/>
                        </fo:inline> 
                    </fo:block>
                    <fo:block font-family="sans-serif" font-size="12px" padding="1px" text-align="right">
                        Adresa   
                    </fo:block>
                    
                    <fo:block font-family="sans-serif" font-size="12px" padding="1px" text-align="right" text-decoration="underline">
           				<fo:inline >
                            
                            <xsl:value-of select="d:zalba_odluke/d:podnosilac_zalbe/d:drugi_podaci_za_kontakt"/>
                        </fo:inline> 
                    </fo:block>
                    <fo:block font-family="sans-serif" font-size="12px" padding="1px" text-align="right">
                        Drugi podaci za kontakt  
                    </fo:block>
                    <fo:block font-family="sans-serif" font-size="12px" padding="1px" text-align="right" text-decoration="underline">
           				<fo:inline >
                            
                            <xsl:text>       </xsl:text>
                        </fo:inline> 
                    </fo:block>
                    <fo:block font-family="sans-serif" font-size="12px" padding="1px" text-align="right">
                       Potpis
                    </fo:block>
            
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
    
</xsl:stylesheet>