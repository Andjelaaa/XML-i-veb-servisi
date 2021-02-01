<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet  xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
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
            	<div>
            	<br></br>
            	<br></br>
            	<div style="text-align:left;">
            		<u><xsl:value-of select="d:obavestenje/d:organ_vlasti/d:naziv_organa_vlasti"/></u>
                    <u><xsl:value-of select="d:obavestenje/d:organ_vlasti/d:sediste_organa"/></u>
                    <p>(naziv i sediste organa)</p>
                    <p>Broj predmeta: <u><xsl:value-of select="d:obavestenje/d:tekst_obavestenja/d:broj_predmeta"/></u></p>
                    <p>Datum: <u><xsl:value-of select="d:obavestenje/d:tekst_obavestenja/d:datum"/></u></p>
            		<p><u><xsl:value-of select="d:obavestenje/d:podnosilac_zahteva/d:naziv_podnosioca/d:ime"/><xsl:text> </xsl:text><xsl:value-of select="d:obavestenje/d:podnosilac_zahteva/d:naziv_podnosioca/d:prezime"/>
            		<xsl:text>, </xsl:text>
                    <xsl:value-of select="d:obavestenje/d:podnosilac_zahteva/d:adresa/d:ulica"/><xsl:text>, </xsl:text>
                     <xsl:value-of select="d:obavestenje/d:podnosilac_zahteva/d:adresa/d:broj"/><xsl:text> </xsl:text>
                     <xsl:value-of select="d:obavestenje/d:podnosilac_zahteva/d:adresa/d:grad"/>
                     </u></p>
                     <P>Ime i prezime/naziv/i adresa podnosioca zahteva</P>            
            	</div>
            	<div style="text-align:center;">
            		<br></br>
            		<br></br>
                	<h1 style="text-align:center;"> O B A V E S T E NJ E </h1>
                	<h2 style="text-align:center;">o stavljanju na uvid dokumenta koji sadrzi trazenu informaciju i o izradi kopije</h2>
                </div>
               	 	<br></br>
                	<br></br>
                	<p>Na osnovu clana 16. st. 1. Zakona o slobodnom pristupu informacijama od javnog znacaja,
                         postupajuci po vašem zahtevu za slobodan pristup informacijama od <u><xsl:value-of select="d:obavestenje/d:tekst_obavestenja/d:p/d:godina"/></u>
                         god., kojim ste tražili uvid u dokument/e sa informacijama o / u vezi sa:
                         <u><xsl:value-of select="d:obavestenje/d:tekst_obavestenja/d:p/d:trazena_informacija"/></u>
                    
                    </p>
                   
                    <p style="text-align:center;">(opis trazene informacije)</p>
                    <p>obavestavamo vas da dana <u><xsl:value-of select="d:obavestenje/d:tekst_obavestenja/d:p/d:dan"/></u>, u
                    <u><xsl:value-of select="d:obavestenje/d:tekst_obavestenja/d:p/d:sati"/></u> casova, odnnosno 
                    u vremenu od <u><xsl:value-of select="d:obavestenje/d:tekst_obavestenja/d:p/d:od"/></u> do
                     <u><xsl:value-of select="d:obavestenje/d:tekst_obavestenja/d:p/d:do"/></u> casova, u prostorijama organa 
                     u <u><xsl:value-of select="d:obavestenje/d:tekst_obavestenja/d:p/d:mesto"/></u> ul.
                     <u><xsl:value-of select="d:obavestenje/d:tekst_obavestenja/d:p/d:ulica"/></u> br.
                     <u><xsl:value-of select="d:obavestenje/d:tekst_obavestenja/d:p/d:broj_zgrade"/></u> 
                     , kancelarija broj <u><xsl:value-of select="d:obavestenje/d:tekst_obavestenja/d:p/d:broj_kancelarije"/></u>
                    mozete <b>izvrsiti uvid</b> u dokument/e u kome je sadrzana trazena informacija.
                    </p>
                    <p>Tom prilikom, na vas zahtev, moze vam se izdati i kopija dokumenta as trazenom informacijom.</p>
                 
                    <p>Troškovi su utvrdjeni Uredbom Vlade Republike Srbije („Sl. glasnik RS“, br. 8/06), i to:
                       kopija strane A4 formata iznosi 3 dinara, A3 formata 6 dinara, CD 35 dinara, diskete 20 dinara,
                       DVD 40 dinara, audio-kaseta – 150 dinara, video-kaseta 300 dinara, 
                      pretvaranje jedne strane dokumenta iz fizickog u elektronski oblik – 30 dinara.
                   </p>
                   <p>Iznos ukupnih troškova izrade kopije dokumenta po vašem zahtevu iznosi dinara
                      i uplacuje se na žiro-racun Budžeta Republike Srbije br. 840-742328-843-30, 
                      s pozivom na broj 97 – oznaka šifre opštine/grada gde se nalazi organ vlasti 
                      (iz Pravilnika o uslovima i nacinu vodjenja
                      racuna – „Sl. glasnik RS“, 20/07... 40/10).
                   </p>
                   <br></br>
                   <p>Dostavljeno:</p>
                   <ol margin-left="20px">
                   <li>Imenovanom</li>
                   <li>Arhivi</li>
                   </ol>
                   <br></br>
                   <table style="width:100%">
                   <tr>
                   <td>(M.P.)</td>
                   <td style="text-align:right;"><p>__________________________________</p>
                   	<p>(potpis ovlascenog lica, odnosno rukovodioca organa)</p></td>
                   </tr></table>
                   <br></br>
                   <br></br>
            	</div>
      	   </body>
      	</html>
      
    </xsl:template>
    
</xsl:stylesheet>