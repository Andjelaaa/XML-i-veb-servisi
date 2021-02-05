<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet  xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:d="http://www.ftn.uns.ac.rs/xpath/examples" 
    xmlns:fo="http://www.w3.org/1999/XSL/Format" version="2.0">
    
    <xsl:template match="/">
        <fo:root>
            <fo:layout-master-set>
                <fo:simple-page-master master-name="izvestaj-page">
                    <fo:region-body margin="0.75in"/>
                </fo:simple-page-master>
            </fo:layout-master-set>
            
            <fo:page-sequence master-reference="izvestaj-page">
                <fo:flow flow-name="xsl-region-body">
                    <fo:block font-family="sans-serif" font-size="24px" padding="1px" text-align="center">
                            <xsl:text> GODIŠNJI IZVEŠTAJ </xsl:text>
                    </fo:block>
                    <fo:block font-family="sans-serif" font-size="16px" padding="1px" text-align="center">
                            <xsl:text> Primena Zakona o slobodnom pristupu informacijama od javnog znacaja u </xsl:text>
                            <xsl:value-of select="d:izvestaj/d:godina"/>. god. 
                    </fo:block>
                    
                    <fo:block font-weight="bold" margin-top="50px">
                    	<xsl:text>1. Zahtevi</xsl:text>
                    
                    </fo:block>
                    <fo:block>
                        <fo:table font-family="serif" margin="10px auto 50px auto" border="1px">
                            <fo:table-column column-width="40%"/>
                            <fo:table-column column-width="30%"/>
                            <fo:table-column column-width="30%"/>

                            <fo:table-body>
                                <fo:table-row border="1px solid darkgrey">
                                    <fo:table-cell background-color="#8cb3d9" font-family="sans-serif" color="white" padding="10px" font-weight="bold">
                                        <fo:block>Broj podnetih zahteva</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell background-color="#8cb3d9" font-family="sans-serif" color="white" padding="10px" font-weight="bold">
                                        <fo:block>Broj odbijenih zahteva</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell background-color="#8cb3d9" font-family="sans-serif" color="white" padding="10px" font-weight="bold">
                                        <fo:block>Broj usvojenih-delimicno usvojenih zahteva</fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                <xsl:variable name="usvojeni" select="d:izvestaj/d:br_podnetih_zahteva - d:izvestaj/d:br_odbijenih_zahteva"/>
                                
                                    
                          		<fo:table-row border="1px solid darkgrey">
                                  
                                  
                                  <fo:table-cell padding="10px">
                                      <fo:block font-weight="bold">
                                       
                                          <fo:inline vertical-align="super" font-size="12px">
                                              <xsl:value-of select="d:izvestaj/d:br_podnetih_zahteva"/>
                                          </fo:inline>
                                      </fo:block>
                                  </fo:table-cell>
                                  <fo:table-cell padding="10px">
                                      <fo:block font-weight="bold">
                                       
                                          <fo:inline vertical-align="super" font-size="12px">
                                              <xsl:value-of select="d:izvestaj/d:br_odbijenih_zahteva"/>
                                          </fo:inline>
                                      </fo:block>
                                  </fo:table-cell>
                                  <fo:table-cell padding="10px">
                                      <fo:block font-weight="bold">
                                       
                                          <fo:inline vertical-align="super" font-size="12px">
                                              <xsl:value-of select="$usvojeni"/>
                                          </fo:inline>
                                      </fo:block>
                                  </fo:table-cell>
                                  
                              </fo:table-row>
                            </fo:table-body>
                        </fo:table>
                    </fo:block>
                    
                    
                    
                    <fo:block font-weight="bold">
                    	<xsl:text>1. Žalbe</xsl:text>
                    
                    </fo:block>
                    <fo:block>
                        Broj podnetih žalbi u godini: <xsl:value-of select="d:izvestaj/d:br_zalbi"/>
                        
                    </fo:block>
                   
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
    
</xsl:stylesheet>