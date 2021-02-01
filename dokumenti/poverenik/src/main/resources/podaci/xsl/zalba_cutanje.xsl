
















<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:d="http://www.ftn.uns.ac.rs/xpath/examples"
	xmlns:fo="http://www.w3.org/1999/XSL/Format" version="2.0">

	<xsl:template match="/">
		<fo:root>
			<fo:layout-master-set>
				<fo:simple-page-master
					master-name="bookstore-page">
					<fo:region-body margin="0.75in" />
				</fo:simple-page-master>
			</fo:layout-master-set>

			<fo:page-sequence master-reference="bookstore-page">
				<fo:flow flow-name="xsl-region-body">
					<fo:block font-family="sans-serif" font-size="14px"
						font-weight="bold" padding="10px" text-align="center">

						<xsl:value-of select="d:zalba_cutanje/d:naslov" />

					</fo:block>

					<fo:block font-family="sans-serif" font-size="12px"
						font-weight="bold" padding="10px" text-align="center">
						<xsl:value-of
							select="d:zalba_cutanje/d:poverenik/d:naziv_poverenika" />
					</fo:block>

					<fo:block font-family="sans-serif" font-size="12px"
						padding="10px" text-align="center">
						<fo:inline>
							Adresa za postu:
							<xsl:value-of
								select="d:zalba_cutanje/d:poverenik/d:sediste_poverenika/d:grad" />
							<xsl:text>  </xsl:text>
							<xsl:value-of
								select="d:zalba_cutanje/d:poverenik/d:sediste_poverenika/d:ulica" />
							<xsl:text>  </xsl:text>
							<xsl:value-of
								select="d:zalba_cutanje/d:poverenik/d:sediste_poverenika/d:broj" />
						</fo:inline>
					</fo:block>
					
					<fo:block font-family="sans-serif" font-size="14px"
						 padding="5px" text-align="left">
						U skladu sa članom 22. Zakona o slobodnom pristupu informacija od javnog značaja podnosim: 
					</fo:block>
					
					<fo:block font-family="sans-serif" font-size="16px"
						font-weight="bold" padding="5px" text-align="center">
						ZALBU
					</fo:block>
					
					<fo:block font-family="sans-serif" font-size="14px" padding="10px" text-align="center">
						protiv
					</fo:block>
					
					
					<xsl:for-each
						select="d:zalba_cutanje/d:tekst_zalbe/d:p">
						<fo:block>
							<fo:block>
								<xsl:choose>
									<xsl:when test="d:naziv_organa">
										<fo:block font-family="sans-serif" font-size="12px"
											padding="1px" text-align="center">
												<xsl:value-of select="d:naziv_organa" />
										</fo:block>
									</xsl:when>
									<xsl:when test="d:podaci_o_zahtevu_i_informacijama">
										<fo:block font-family="sans-serif" font-size="12px"
											padding="5px" text-align="left">
											po mom zahtevu za slobodan pristup informacijama od javnog značaja koji sam podneo tom organu dana
											<fo:inline text-decoration="underline">
				                            <xsl:value-of select="d:datum"/>
				                            
				                        	</fo:inline>
											godine, a kojim sam tražio/la da mi se u skladu sa Zakonom o slobodnom 
											pristupu informacijama od javnog značaja omogući uvid- kopija dokumenta 
											koji sadrži informacije o/u vezi sa
											
											<fo:inline text-decoration="underline">
				                            <xsl:value-of select="d:podaci_o_zahtevu_i_informacijama"/>
				                            
				                        	</fo:inline>
											
										</fo:block>
									</xsl:when>
									<xsl:otherwise>
									<fo:block font-family="sans-serif" font-size="12px"
											padding="5px" text-align="left">
										<xsl:value-of select="text()"></xsl:value-of>
									</fo:block>
									</xsl:otherwise>
								</xsl:choose>

							</fo:block>

						</fo:block>


					</xsl:for-each>


					<br></br>
					<fo:block font-family="sans-serif" font-size="12px"
						padding="1px" text-align="left">
						<fo:inline>
							<xsl:text>U </xsl:text>
							<xsl:value-of
								select="d:zalba_cutanje/d:podaci_o_vremenu_i_mestu_podnosenja_zalbe/d:mesto" />
						</fo:inline>
					</fo:block>
					<fo:block font-family="sans-serif" font-size="12px"
						padding="1px" text-align="left">
						<fo:inline>
							<xsl:text>dana </xsl:text>
							<xsl:value-of
								select="d:zalba_cutanje/d:podaci_o_vremenu_i_mestu_podnosenja_zalbe/d:datum" />
							<xsl:text>godine</xsl:text>
						</fo:inline>
					</fo:block>


					<fo:block font-family="sans-serif" font-size="12px"
						padding="1px" text-align="right" text-decoration="underline">
						<fo:inline>
							<xsl:value-of
								select="d:zalba_cutanje/d:podnosilac_zalbe/d:naziv_podnosioca/d:ime" />
							<xsl:text>  </xsl:text>
							<xsl:value-of
								select="d:zalba_cutanje/d:podnosilac_zalbe/d:naziv_podnosioca/d:prezime" />
							<xsl:value-of
								select="d:zalba_cutanje/d:podnosilac_zalbe/d:naziv_podnosioca/d:naziv_firme" />
						</fo:inline>
					</fo:block>
					<fo:block font-family="sans-serif" font-size="12px"
						padding="1px" text-align="right">
						Podnosilac zalbe/Ime i prezime

					</fo:block>
					
					<fo:block font-family="sans-serif" font-size="12px"
						padding="1px" text-align="right">
							__________________________
					</fo:block>
					
					<fo:block font-family="sans-serif" font-size="12px"
						padding="1px" text-align="right">
						Potpis
					</fo:block>

					<fo:block font-family="sans-serif" font-size="12px"
						padding="1px" text-align="right" text-decoration="underline">
						<fo:inline>
							<xsl:value-of
								select="d:zalba_cutanje/d:podnosilac_zalbe/d:adresa/d:ulica" />
							<xsl:text>  </xsl:text>
							<xsl:value-of
								select="d:zalba_cutanje/d:podnosilac_zalbe/d:adresa/d:broj"/>
							<xsl:text>  </xsl:text>
							<xsl:value-of
								select="d:zalba_cutanje/d:podnosilac_zalbe/d:adresa/d:grad"/>
						</fo:inline>
					</fo:block>
					<fo:block font-family="sans-serif" font-size="12px"
						padding="1px" text-align="right">
						Adresa
					</fo:block>

					<fo:block font-family="sans-serif" font-size="12px"
						padding="1px" text-align="right" text-decoration="underline">
						<fo:inline>

							<xsl:value-of
								select="d:zalba_cutanje/d:podnosilac_zalbe/d:drugi_podaci_za_kontakt" />
						</fo:inline>
					</fo:block>
					<fo:block font-family="sans-serif" font-size="12px"
						padding="1px" text-align="right">
						Drugi podaci za kontakt
					</fo:block>
					<fo:block font-family="sans-serif" font-size="12px"
						padding="1px" text-align="right" text-decoration="underline">
						<fo:inline>
							<xsl:text>       </xsl:text>
						</fo:inline>
					</fo:block>
					<fo:block font-family="sans-serif" font-size="12px"
						padding="1px" text-align="right">
							__________________________
					</fo:block>
					
					<fo:block font-family="sans-serif" font-size="12px"
						padding="1px" text-align="right">
						Potpis
					</fo:block>
					

				</fo:flow>
			</fo:page-sequence>
		</fo:root>
	</xsl:template>

</xsl:stylesheet>















































































































