<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet  xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:d="http://www.ftn.uns.ac.rs/xpath/examples" 
    xmlns:fo="http://www.w3.org/1999/XSL/Format" version="2.0">
    
    <xsl:template match="/">
        <fo:root>
            <fo:layout-master-set>
                <fo:simple-page-master master-name="d:obavestenje-page">
                    <fo:region-body margin="0.75in"/>
                </fo:simple-page-master>
            </fo:layout-master-set>
            
            <fo:page-sequence master-reference="d:obavestenje-page">
                <fo:flow flow-name="xsl-region-body">
                    <fo:block font-family="sans-serif" font-size="12px" padding="1px"  text-decoration="underline" text-align="left">
                        <fo:inline >
                            <xsl:value-of select="d:obavestenje/d:organ_vlasti/d:naziv_organa_vlasti"/>
                            <xsl:text>  </xsl:text>
                            <xsl:value-of select="d:obavestenje/d:organ_vlasti/d:sediste_organa"/>
                        </fo:inline>            
                    </fo:block>
                    <fo:block font-family="sans-serif" font-size="12px" padding="1px" text-align="left">
                        (naziv i sediste organa)
    
                    </fo:block>
                    
                    <fo:block font-family="sans-serif" font-size="12px" padding="1px"  text-decoration="underline" text-align="left">
                        <fo:inline >
                            <xsl:text> Broj predmeta: </xsl:text>
                            <xsl:value-of select="d:obavestenje/d:tekst_obavestenja/d:broj_predmeta"/>
                        </fo:inline>            
                    </fo:block>
                    
                    <fo:block font-family="sans-serif" font-size="12px" padding="1px"  text-decoration="underline" text-align="left">
                        <fo:inline >
                            <xsl:text> Datum: </xsl:text>
                            <xsl:value-of select="d:obavestenje/d:tekst_obavestenja/d:datum"/>
                        </fo:inline>            
                    </fo:block>
                    
                    <fo:block font-family="sans-serif" font-size="12px" padding="1px"  text-decoration="underline" text-align="left">
                        <fo:inline >
                            <xsl:value-of select="d:obavestenje/d:podnosilac_zahteva/d:naziv_podnosioca/d:ime"/>
                            <xsl:text>  </xsl:text>
                            <xsl:value-of select="d:obavestenje/d:podnosilac_zahteva/d:naziv_podnosioca/d:prezime"/>
                            <xsl:text>  </xsl:text>
                            
                            <xsl:value-of select="d:obavestenje/d:podnosilac_zahteva/d:adresa/d:ulica"/>
                            <xsl:text>  </xsl:text>
                            <xsl:value-of select="d:obavestenje/d:podnosilac_zahteva/d:adresa/d:broj"/>
                            <xsl:text>  </xsl:text>
                            
                            <xsl:value-of select="d:obavestenje/d:podnosilac_zahteva/d:adresa/d:grad"/>
                            
                        </fo:inline>            
                    </fo:block>
                    <fo:block font-family="sans-serif" font-size="12px" padding="1px" text-align="left">
                        Ime i prezime/naziv/i adresa podnosioca zahteva
    
                    </fo:block>
                    
                    
                    <fo:block font-family="sans-serif" font-size="24px" font-weight="bold" padding="10px" text-align="center">
                        O B A V E S T E NJ E             
                    </fo:block>
                    <fo:block font-family="sans-serif" font-size="12px" font-weight="bold" padding="10px" text-align="center">
                        o stavljanju na uvid dokumenta koji sadrzi trazenu informaciju i o izradi kopije            
                    </fo:block>
                    <fo:block font-family="sans-serif" font-size="12px" padding="10px">
                        Na osnovu clana 16. st. 1. Zakona o slobodnom pristupu informacijama od javnog znacaja,
                         postupajuci po vašem zahtevu za slobodan pristup informacijama od
                         
                        <fo:inline text-decoration="underline">
                            <xsl:value-of select="d:obavestenje/d:tekst_obavestenja/d:p/d:godina"/>                            
                        </fo:inline>            
						god., kojim ste tražili uvid u dokument/e sa informacijama o / u vezi sa: 
						<fo:inline text-decoration="underline">
                            <xsl:value-of select="d:obavestenje/d:tekst_obavestenja/d:p/d:trazena_informacija"/>                            
                        </fo:inline>

                    </fo:block>
                    <fo:block font-family="sans-serif" font-size="12px" padding="10px">
                        obavestavamo vas da dana
                        <fo:inline text-decoration="underline">
                            <xsl:value-of select="d:obavestenje/d:tekst_obavestenja/d:p/d:dan"/>                            
                        </fo:inline>            
						, u 
						<fo:inline text-decoration="underline">
                            <xsl:value-of select="d:obavestenje/d:tekst_obavestenja/d:p/d:sati"/>                            
                        </fo:inline>
                        casova, odnosno u vremenu od 
                        <fo:inline text-decoration="underline">
                            <xsl:value-of select="d:obavestenje/d:tekst_obavestenja/d:p/d:od"/>                            
                        </fo:inline>
                        do
                        <fo:inline text-decoration="underline">
                            <xsl:value-of select="d:obavestenje/d:tekst_obavestenja/d:p/d:do"/>                            
                        </fo:inline>
                        casova, u prostorijama organa u
                        <fo:inline text-decoration="underline">
                            <xsl:value-of select="d:obavestenje/d:tekst_obavestenja/d:p/d:mesto"/>                            
                        </fo:inline>
                        ul.
                        <fo:inline text-decoration="underline">
                            <xsl:value-of select="d:obavestenje/d:tekst_obavestenja/d:p/d:ulica"/>                            
                        </fo:inline>
                        br.
                        <fo:inline text-decoration="underline">
                            <xsl:value-of select="d:obavestenje/d:tekst_obavestenja/d:p/d:broj_zgrade"/>                            
                        </fo:inline>, kancelarija br. 
                        <fo:inline text-decoration="underline">
                            <xsl:value-of select="d:obavestenje/d:tekst_obavestenja/d:p/d:broj_kancelarije"/>                            
                        </fo:inline>
                        mozete 
                        <fo:inline font-weight="bold">
                            izvrsiti uvid                            
                        </fo:inline>
                        u dokument/e u kome je sadrzana trazena informacija.
						

                    </fo:block>

                    <fo:block font-family="sans-serif" font-size="12px" padding="1px">
                            Tom prilikom, na vas zahtev, moze vam se izdati i kopija dokumenta as trazenom informacijom.
           
                    </fo:block>
                    <fo:block font-family="sans-serif" font-size="12px" padding="1px">
                           Troškovi su utvrdjeni Uredbom Vlade Republike Srbije („Sl. glasnik RS“, br. 8/06), i to:
                            kopija strane A4 formata iznosi 3 dinara, A3 formata 6 dinara, CD 35 dinara, diskete 20 dinara,
                            DVD 40 dinara, audio-kaseta – 150 dinara, video-kaseta 300 dinara, 
                           pretvaranje jedne strane dokumenta iz fizickog u elektronski oblik – 30 dinara.
                    </fo:block>
                    <fo:block font-family="sans-serif" font-size="12px" padding="1px">
                           Iznos ukupnih troškova izrade kopije dokumenta po vašem zahtevu iznosi dinara
                            i uplacuje se na žiro-racun Budžeta Republike Srbije br. 840-742328-843-30, 
                            s pozivom na broj 97 – oznaka šifre opštine/grada gde se nalazi organ vlasti 
                            (iz Pravilnika o uslovima i nacinu vodjenja
                            racuna – „Sl. glasnik RS“, 20/07... 40/10).
                    </fo:block>
                    
                    <br></br>
                    <fo:block font-family="sans-serif" font-size="12px" padding="1px" text-align="left">
           				<fo:inline >
                            <xsl:text>Dostavljeno: </xsl:text>
                        </fo:inline> 
                    </fo:block>
                    <fo:block font-family="sans-serif" font-size="12px" padding="1px" text-align="left">
           				<fo:inline >
                            <xsl:text>1. Imenovanom </xsl:text>
                        </fo:inline> 

                    </fo:block>
                    <fo:block font-family="sans-serif" font-size="12px" padding="1px" text-align="left">
           				<fo:inline >
                            <xsl:text>2. Arhivi </xsl:text>
                        </fo:inline> 

                    </fo:block>
                    
                    <fo:block font-family="sans-serif" font-size="12px" padding="1px" text-align="right">
           				<fo:inline >
                            <xsl:text>(potpis ovlascenog lica, odnosno rukovodioca organa)</xsl:text>
                        </fo:inline> 
                         
                    </fo:block>
  
                   
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
    
</xsl:stylesheet>