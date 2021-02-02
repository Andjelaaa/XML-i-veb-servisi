<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet  xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:d="http://www.ftn.uns.ac.rs/xpath/examples" 
    xmlns:fo="http://www.w3.org/1999/XSL/Format" version="2.0">
    
    <xsl:template match="/">
        <fo:root>
            <fo:layout-master-set>
                <fo:simple-page-master master-name="bookstore-page">
                    <fo:region-body margin="0.75in"/>
                </fo:simple-page-master>
            </fo:layout-master-set>
            
            <fo:page-sequence master-reference="bookstore-page">
                <fo:flow flow-name="xsl-region-body">
                    <fo:block font-family="sans-serif" font-size="12px" padding="1px"  text-decoration="underline" text-align="center">
                        <fo:inline >
                            <xsl:value-of select="d:zahtev/d:organ_vlasti/d:naziv_organa_vlasti"/>
                            <xsl:text>  </xsl:text>
                            <xsl:value-of select="d:zahtev/d:organ_vlasti/d:sediste_organa"/>
                        </fo:inline>            
                    </fo:block>
                    <fo:block font-family="sans-serif" font-size="12px" padding="1px" text-align="center">
                        naziv i sediste organa kome se zahtev upucuje
    
                    </fo:block>
                    <fo:block font-family="sans-serif" font-size="24px" font-weight="bold" padding="10px" text-align="center">
                        Z A H T E V             
                    </fo:block>
                    <fo:block font-family="sans-serif" font-size="24px" font-weight="bold" padding="10px" text-align="center">
                        za pristup informaciji od javnog znacaja            
                    </fo:block>
                    <fo:block font-family="sans-serif" font-size="12px" padding="10px">
                        Na osnovu clana 15. st. 1. Zakona o slobodnom pristupu informacijama od javnog znacaja („Službeni glasnik RS“, br. 120/04, 54/07, 104/09 i 36/10), od gore navedenog organa zahtevam:*


						<fo:list-block margin-left="20px">
							<xsl:for-each select="d:zahtev/d:tekst_zahteva/d:p">
								<xsl:choose>
									<xsl:when test="d:izbori">
										<xsl:for-each select="d:izbori/d:izbor">
											<fo:list-item>
											 <fo:list-item-label>
											   <fo:block>-<xsl:text>  </xsl:text></fo:block>
											 </fo:list-item-label>
											 <fo:list-item-body>
											   <fo:block><xsl:value-of select="text()"></xsl:value-of></fo:block>
											 </fo:list-item-body>
											</fo:list-item>
											<xsl:choose>
											<xsl:when test="d:podizbori">
												<fo:list-item>
											 <fo:list-item-label>
											   <fo:block><xsl:text>  </xsl:text>-<xsl:text>  </xsl:text></fo:block>
											 </fo:list-item-label>
											 <fo:list-item-body>
											   <fo:block><xsl:value-of select="d:podizbori/d:podizbor"></xsl:value-of></fo:block>
											 </fo:list-item-body>
											</fo:list-item>
											</xsl:when>
											</xsl:choose>
										</xsl:for-each>
									</xsl:when>
			
								</xsl:choose>
							</xsl:for-each>
						
						
						
						
						</fo:list-block>

						Ovaj zahtev se odnosi na sledece informacije:
           
                    </fo:block>
                    <fo:block font-family="sans-serif" font-size="12px" padding="1px"  text-decoration="underline">
                            <xsl:value-of select="d:zahtev/d:tekst_zahteva/d:trazene_informacije"/>
           
                    </fo:block>
                    
                    <br></br>
                    <fo:block font-family="sans-serif" font-size="12px" padding="1px" text-align="left">
           				<fo:inline >
                            <xsl:text>U </xsl:text>
                            <xsl:value-of select="d:zahtev/d:tekst_zahteva/d:podaci_o_vremenu_i_mestu_podnosenja_zahteva/d:mesto"/>
                        </fo:inline> 
                    </fo:block>
                    <fo:block font-family="sans-serif" font-size="12px" padding="1px" text-align="left">
           				<fo:inline >
                            <xsl:text>dana </xsl:text>
                            <xsl:value-of select="d:zahtev/d:tekst_zahteva/d:podaci_o_vremenu_i_mestu_podnosenja_zahteva/d:datum"/>
                            <xsl:text>godine</xsl:text>
                        </fo:inline> 
                    </fo:block>
                    
                    
                    <fo:block font-family="sans-serif" font-size="12px" padding="1px" text-align="right" text-decoration="underline">
           				<fo:inline >
                            <xsl:value-of select="d:zahtev/d:podnosilac_zahteva/d:naziv_podnosioca/d:ime"/>
                            <xsl:text>  </xsl:text>
                            <xsl:value-of select="d:zahtev/d:podnosilac_zahteva/d:naziv_podnosioca/d:prezime"/>
                        </fo:inline> 
                    </fo:block>
                    <fo:block font-family="sans-serif" font-size="12px" padding="1px" text-align="right">
                        Trazilac informacije/Ime i prezime
    
                    </fo:block>
                    
                    <fo:block font-family="sans-serif" font-size="12px" padding="1px" text-align="right" text-decoration="underline">
           				<fo:inline >
                            <xsl:value-of select="d:zahtev/d:podnosilac_zahteva/d:adresa/d:ulica"/>
                            <xsl:text>  </xsl:text>
                            <xsl:value-of select="d:zahtev/d:podnosilac_zahteva/d:adresa/d:broj"/>
                            <xsl:text>  </xsl:text>
                            <xsl:value-of select="d:zahtev/d:podnosilac_zahteva/d:adresa/d:grad"/>
                        </fo:inline> 
                    </fo:block>
                    <fo:block font-family="sans-serif" font-size="12px" padding="1px" text-align="right">
                        Adresa   
                    </fo:block>
                    
                    <fo:block font-family="sans-serif" font-size="12px" padding="1px" text-align="right" text-decoration="underline">
           				<fo:inline >
                            
                            <xsl:value-of select="d:zahtev/d:podnosilac_zahteva/d:drugi_podaci_za_kontakt"/>
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
                    <fo:block font-family="sans-serif" font-size="12px" padding="1px" text-align="left">
                        * U kucici oznaciti koja zakonska prava na pristup informacijama želite da ostvarite.
						
                    </fo:block>
                    <fo:block font-family="sans-serif" font-size="12px" padding="1px" text-align="left">
                        ** U kucici oznaciti nacin dostavljanja kopije dokumenata.
						
						
                    </fo:block>
                    
                    <fo:block font-family="sans-serif" font-size="12px" padding="1px" text-align="left">
                       *** Kada zahtevate drugi nacin dostavljanja obavezno upisati koji nacin dostavljanja zahtevate.
						
                    </fo:block>
                    
                    
                   
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
    
</xsl:stylesheet>