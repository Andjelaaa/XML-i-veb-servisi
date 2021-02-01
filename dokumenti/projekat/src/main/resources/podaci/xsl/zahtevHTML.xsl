<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:d="http://www.ftn.uns.ac.rs/xpath/examples" version="2.0">

    <xsl:template match="/">
        <html>
            <head>
                <title>ZAHTEV</title>
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
            <div id="maindiv">
            	<br></br>
            	<br></br>
            	<p style="text-align:center;"><u><xsl:value-of select="d:zahtev/d:organ_vlasti/d:naziv_organa_vlasti"/>
                    <span> </span>
                   <xsl:value-of select="d:zahtev/d:organ_vlasti/d:sediste_organa"/></u></p>
            	<p style="text-align:center;">naziv i sediste organa kome se zahtev upucuje</p>
            	<br></br>
            	<br></br>
                <h1 style="text-align:center;">ZAHTEV</h1>
                <h2 style="text-align:center;">za pristup informaciji od javnog znacaja</h2>
                <br></br>
                <br></br>
                <p>Na osnovu clana 15. st. 1. Zakona o slobodnom pristupu informacijama od javnog znacaja ("Sluzbeni glasnik RS", br. 120/04, 54/07, 104/09 i 36/10), od gore navedenog organa zahtevam:*
                </p>
                
                <ul margin-left="20px">
						<li> obavestenje da li poseduje trazenu informaciju;</li>
						<li>  uvid u dokument koji sadrzi trazenu informaciju;</li>
						<li>  kopiju dokumenta koji sadrzi trazenu informaciju;</li>
						<li>  dostavljanje kopije dokumenta koji sadrzi trazenu informaciju:**</li>
						   
						   <ul>
						   		<li>postom</li>
								<li>elektronskom postom</li>
								<li>faksom</li>
								<li>na drugi nacin:***________________________</li>						   
						   </ul>
				</ul>
				<span>Ovaj zahtev se odnosi na sledece informacije: </span>
				 <u><xsl:value-of select="d:zahtev/d:tekst_zahteva/d:trazene_informacije"/></u>
                <br></br>
                <br></br>
                <table style="width:100%">
				<tr>
				<td style="vertical-align:top"><div style="float: left;">
                	<span>U </span>
                	<u><xsl:value-of select="d:zahtev/d:tekst_zahteva/d:podaci_o_vremenu_i_mestu_podnosenja_zahteva/d:mesto"/></u>
                    <br></br>
                    <span>dana</span>
                    <u><xsl:value-of select="d:zahtev/d:tekst_zahteva/d:podaci_o_vremenu_i_mestu_podnosenja_zahteva/d:datum"/></u>
                    <span>godine</span>
                
                </div></td>
				<td style="text-align:right;"><div style="float: right" >
                	<u><xsl:value-of select="d:zahtev/d:podnosilac_zahteva/d:naziv_podnosioca/d:ime"/>
                    <xsl:value-of select="d:zahtev/d:podnosilac_zahteva/d:naziv_podnosioca/d:prezime"/></u>
					<br></br>
					<span>Trazilac informacije/Ime i prezime</span>
					<br></br>
                    <u>
                    <xsl:value-of select="d:zahtev/d:podnosilac_zahteva/d:adresa/d:ulica"/>
                    <xsl:value-of select="d:zahtev/d:podnosilac_zahteva/d:adresa/d:broj"/>
                    <xsl:value-of select="d:zahtev/d:podnosilac_zahteva/d:adresa/d:grad"/>
                        
                    </u>
                    <br></br>
                    <span>adresa</span>
                    <br></br>
                    <u><xsl:value-of select="d:zahtev/d:podnosilac_zahteva/d:drugi_podaci_za_kontakt"/>
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
                <br></br>
                <div style="text-align:left;">
                <p >* U kucici oznaciti koja zakonska prava na pristup informacijama želite da ostvarite.
					</p>
                <p>** U kucici oznaciti nacin dostavljanja kopije dokumenata.
                </p>
                <p>*** Kada zahtevate drugi nacin dostavljanja obavezno upisati koji nacin dostavljanja zahtevate.</p>
				</div>	
                
              </div>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
