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
					<fo:block font-family="sans-serif" font-size="18px"
						font-weight="bold" padding="10px" text-align="center">

						<xsl:value-of select="d:zalba_cutanje/d:naslov" />

					</fo:block>

					<fo:block font-family="sans-serif" font-size="16px"
						font-weight="bold" padding="10px" text-align="center">
						<xsl:value-of
							select="d:zalba_cutanje/d:poverenik/d:naziv_poverenika" />
					</fo:block>

					<fo:block font-family="sans-serif" font-size="16px"
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
					<xsl:for-each
						select="d:zalba_cutanje/d:tekst_zalbe/d:p">
						<fo:block>
							<fo:block>
								<xsl:choose>
									<xsl:when test="d:naziv_organa">
										<fo:block font-family="sans-serif" font-size="12px"
											padding="1px" text-align="left">
											Navedenom odlukom organa vlasti (rešenjem, zaključkom,
											obaveštenjem u pisanoj
											formi sa elementima odluke) , suprotno
											zakonu, odbijen-odbačen je
											moj zahtev koji
											sam
											podneo/la-uputio/la dana

											<fo:inline text-decoration="underline">
												<xsl:value-of select="d:datum" />

											</fo:inline>
											godine i tako mi uskraćeno-onemogućeno
											ostvarivanje ustavnog i
											zakonskog prava na slobodan pristup informacijama
											od javnog
											značaja. Odluku pobijam u celosti, odnosno u delu kojim
											<fo:inline text-decoration="underline">
												<xsl:value-of select="d:razlog" />

											</fo:inline>
											jer nije zasnovana na Zakonu o slobodnom pristupu
											informacijama od javnog značaja.


										</fo:block>
									</xsl:when>
									<xsl:when test="d:broj_zalbe">
										<fo:block font-family="sans-serif" font-size="12px"
											padding="1px" text-align="left">
											Broj
											<fo:inline text-decoration="underline">
												<xsl:value-of select="d:broj_zalbe" />

											</fo:inline>
											od
											<fo:inline text-decoration="underline">
												<xsl:value-of select="d:godina_odbijanja" />

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

					<fo:block font-family="sans-serif" font-size="12px"
						padding="10px">
						Na osnovu clana 15. st. 1. Zakona o slobodnom pristupu
						informacijama od javnog znacaja („Službeni glasnik RS“, br.
						120/04, 54/07, 104/09 i 36/10), od gore navedenog organa
						zahtevam:*


						<fo:list-block margin-left="20px">
							<fo:list-item>
								<fo:list-item-label>
									<fo:block>-</fo:block>
								</fo:list-item-label>
								<fo:list-item-body>
									<fo:block> obaveštenje da li poseduje traženu informaciju;
									</fo:block>
								</fo:list-item-body>
							</fo:list-item>
							<fo:list-item>
								<fo:list-item-label>
									<fo:block>-</fo:block>
								</fo:list-item-label>
								<fo:list-item-body>
									<fo:block> uvid u dokument koji sadrži traženu informaciju;
									</fo:block>
								</fo:list-item-body>
							</fo:list-item>

							<fo:list-item>
								<fo:list-item-label>
									<fo:block>-</fo:block>
								</fo:list-item-label>
								<fo:list-item-body>
									<fo:block> kopiju dokumenta koji sadrži traženu informaciju;
									</fo:block>
								</fo:list-item-body>
							</fo:list-item>

							<fo:list-item>
								<fo:list-item-label>
									<fo:block>-</fo:block>
								</fo:list-item-label>
								<fo:list-item-body>
									<fo:block> dostavljanje kopije dokumenta koji sadrži traženu
										informaciju:**
									</fo:block>


									<fo:list-block margin-left="50px">
										<fo:list-item>
											<fo:list-item-label>
												<fo:block>-</fo:block>
											</fo:list-item-label>
											<fo:list-item-body>
												<fo:block> poštom</fo:block>
											</fo:list-item-body>
										</fo:list-item>
										<fo:list-item>
											<fo:list-item-label>
												<fo:block>-</fo:block>
											</fo:list-item-label>
											<fo:list-item-body>
												<fo:block>elektronskom poštom</fo:block>
											</fo:list-item-body>
										</fo:list-item>

										<fo:list-item>
											<fo:list-item-label>
												<fo:block>-</fo:block>
											</fo:list-item-label>
											<fo:list-item-body>
												<fo:block>faksom</fo:block>
											</fo:list-item-body>
										</fo:list-item>

										<fo:list-item>
											<fo:list-item-label>
												<fo:block>-</fo:block>
											</fo:list-item-label>
											<fo:list-item-body>
												<fo:block>
													na drugi nacin:***
													<fo:inline text-decoration="underline">
														<xsl:text> 				</xsl:text>
													</fo:inline>
												</fo:block>
											</fo:list-item-body>
										</fo:list-item>

									</fo:list-block>
								</fo:list-item-body>
							</fo:list-item>
						</fo:list-block>

						Ovaj zahtev se odnosi na sledece informacije:

					</fo:block>
					<fo:block font-family="sans-serif" font-size="12px"
						padding="1px" text-decoration="underline">
						<xsl:value-of
							select="d:zahtev/d:tekst_zahteva/d:trazene_informacije" />

					</fo:block>

					<br></br>
					<fo:block font-family="sans-serif" font-size="12px"
						padding="1px" text-align="left">
						<fo:inline>
							<xsl:text>U </xsl:text>
							<xsl:value-of
								select="d:zahtev/d:tekst_zahteva/d:podaci_o_vremenu_i_mestu_podnosenja_zahteva/d:mesto" />
						</fo:inline>
					</fo:block>
					<fo:block font-family="sans-serif" font-size="12px"
						padding="1px" text-align="left">
						<fo:inline>
							<xsl:text>dana </xsl:text>
							<xsl:value-of
								select="d:zahtev/d:tekst_zahteva/d:podaci_o_vremenu_i_mestu_podnosenja_zahteva/d:datum" />
							<xsl:text>godine</xsl:text>
						</fo:inline>
					</fo:block>


					<fo:block font-family="sans-serif" font-size="12px"
						padding="1px" text-align="right" text-decoration="underline">
						<fo:inline>
							<xsl:value-of
								select="d:zahtev/d:podnosilac_zahteva/d:naziv_podnosioca/d:ime" />
							<xsl:text>  </xsl:text>
							<xsl:value-of
								select="d:zahtev/d:podnosilac_zahteva/d:naziv_podnosioca/d:prezime" />
						</fo:inline>
					</fo:block>
					<fo:block font-family="sans-serif" font-size="12px"
						padding="1px" text-align="right">
						Trazilac informacije/Ime i prezime

					</fo:block>

					<fo:block font-family="sans-serif" font-size="12px"
						padding="1px" text-align="right" text-decoration="underline">
						<fo:inline>
							<xsl:value-of
								select="d:zahtev/d:podnosilac_zahteva/d:adresa/d:ulica" />
							<xsl:text>  </xsl:text>
							<xsl:value-of
								select="d:zahtev/d:podnosilac_zahteva/d:adresa/d:broj" />
							<xsl:text>  </xsl:text>
							<xsl:value-of
								select="d:zahtev/d:podnosilac_zahteva/d:adresa/d:grad" />
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
								select="d:zahtev/d:podnosilac_zahteva/d:drugi_podaci_za_kontakt" />
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
						Potpis
					</fo:block>
					<fo:block font-family="sans-serif" font-size="12px"
						padding="1px" text-align="left">
						* U kucici oznaciti koja zakonska prava
						na pristup informacijama
						želite da ostvarite.

					</fo:block>
					<fo:block font-family="sans-serif" font-size="12px"
						padding="1px" text-align="left">
						** U kucici oznaciti nacin dostavljanja
						kopije dokumenata.


					</fo:block>

					<fo:block font-family="sans-serif" font-size="12px"
						padding="1px" text-align="left">
						*** Kada zahtevate drugi nacin
						dostavljanja obavezno upisati koji
						nacin dostavljanja zahtevate.

					</fo:block>



				</fo:flow>
			</fo:page-sequence>
		</fo:root>
	</xsl:template>

</xsl:stylesheet>