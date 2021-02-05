<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:d="http://www.ftn.uns.ac.rs/xpath/examples" version="2.0">

	<xsl:template match="/">
	<html>
		<head>
			<title>ZALBA CUTANJE</title>
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
		<xsl:variable name="zahtevURI" select="d:zalba_cutanje/d:zahtev_uri"/>
            		<p><a href="http://localhost:4200/zahtev/{$zahtevURI}">
							Referencirani zahtev
						</a></p>
        <br></br>
        <div style="text-align:center;">
        	<h3>ZALBA KADA ORGAN VLASTI <u>NIJE POSTUPIO/nije postupio u celosti/PO ZAHTEVU</u><br></br>
        	TRAŽIOCA U ZAKONSKOM ROKU (ĆUTANJE UPRAVE)</h3>
        </div>
        <p><b>Povereniku za informacije od javnog znacaja i zastitu podatakao licnosti</b></p>
        <p>Adresa za postu: <xsl:value-of select="d:zalba_cutanje/d:poverenik/d:sediste_poverenika/d:grad" />
							<xsl:text>, </xsl:text>
							<xsl:value-of select="d:zalba_cutanje/d:poverenik/d:sediste_poverenika/d:ulica" />
							<xsl:text> br. </xsl:text>
							<xsl:value-of select="d:zalba_cutanje/d:poverenik/d:sediste_poverenika/d:broj" />
		</p>
		<p>U skladu sa članom 22. Zakona o slobodnom pristupu informacija od javnog značaja podnosim:</p>
		<div style="text-align:center;">
			<h3>Z A L B U</h3>
			<p>protiv</p>
		</div>
			
			<xsl:for-each select="d:zalba_cutanje/d:tekst_zalbe/d:p">
				<xsl:choose>
					<xsl:when test="d:naziv_organa">
						<p  style="text-align:center;" ><u><xsl:value-of select="d:naziv_organa" /></u></p>
					</xsl:when>
					<xsl:when test="d:podaci_o_zahtevu_i_informacijama">
						<p>po mom zahtevu za slobodan pristup informacijama od javnog značaja koji sam podneo tom organu dana
						<xsl:value-of select="d:datum"/>
						godine, a kojim sam tražio/la da mi se u skladu sa Zakonom o slobodnom 
						pristupu informacijama od javnog značaja omogući uvid- kopija dokumenta 
						koji sadrži informacije o/u vezi sa: </p>
						<p><u><xsl:value-of select="d:podaci_o_zahtevu_i_informacijama"/></u></p>
				    </xsl:when>
					<xsl:otherwise>
						<p><xsl:value-of select="text()"></xsl:value-of></p>
					</xsl:otherwise>
				</xsl:choose>
			</xsl:for-each>
			<table style="width:100%">
			<tr>
			<td style="vertical-align:bottom">
			 U  <xsl:value-of select="d:zalba_cutanje/d:podaci_o_vremenu_i_mestu_podnosenja_zalbe/d:mesto" /><xsl:text>, dana</xsl:text>
			 <xsl:value-of select="d:zalba_cutanje/d:podaci_o_vremenu_i_mestu_podnosenja_zalbe/d:datum" /><xsl:text> godine</xsl:text>
			</td>
			<td style="text-align:right;">
				<xsl:value-of select="d:zalba_cutanje/d:podnosilac_zalbe/d:naziv_podnosioca/d:ime" />
							<xsl:text>  </xsl:text>
				<xsl:value-of select="d:zalba_cutanje/d:podnosilac_zalbe/d:naziv_podnosioca/d:prezime" />
				<br></br>
				<xsl:value-of select="d:zalba_cutanje/d:podnosilac_zalbe/d:naziv_podnosioca/d:naziv_firme" />
				<xsl:text>Podnosilac zalbe/Ime i prezime</xsl:text>
				<p>_________________________</p>
				<p>potpis</p>
				<p>
					<xsl:value-of select="d:zalba_cutanje/d:podnosilac_zalbe/d:adresa/d:ulica" />
					<xsl:text>  </xsl:text>
					<xsl:value-of select="d:zalba_cutanje/d:podnosilac_zalbe/d:adresa/d:broj"/>
					<xsl:text>,  </xsl:text>
					<xsl:value-of select="d:zalba_cutanje/d:podnosilac_zalbe/d:adresa/d:grad"/>
				</p>
				<p>adresa</p>
				<p>	<xsl:value-of select="d:zalba_cutanje/d:podnosilac_zalbe/d:drugi_podaci_za_kontakt" /></p>
				<p>drugi podaci za kontakt</p>
				<p>________________________</p>
				<p>potpis</p>
			</td></tr>
			</table>
			<br></br>
			<br></br>
			<br></br>
		</body>
	</html>
				
	</xsl:template>

</xsl:stylesheet>
