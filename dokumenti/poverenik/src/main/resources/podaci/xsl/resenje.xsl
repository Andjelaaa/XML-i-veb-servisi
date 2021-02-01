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
					<fo:block font-family="sans-serif" font-size="12px"
						padding="1px" text-decoration="underline" text-align="left">
						<fo:inline>
							<xsl:value-of
								select="d:dokument_resenje/d:naziv_resenja" />
							<xsl:text>  </xsl:text>
							<xsl:value-of
								select="d:dokument_resenje/d:naziv_resenja/d:odluka" />
						</fo:inline>
					</fo:block>

					<fo:block font-family="sans-serif" font-size="12px"
						padding="1px" text-align="left">
						<fo:inline>
							BR.
							<xsl:value-of
								select="d:dokument_resenje/d:zaglavlje/d:broj_resenja" />

						</fo:inline>
					</fo:block>
					<fo:block font-family="sans-serif" font-size="12px"
						padding="1px" text-align="right">
						<fo:inline>
							Datum:
							<xsl:value-of
								select="d:dokument_resenje/d:zaglavlje/d:datum" />
						</fo:inline>
					</fo:block>

					<fo:block font-family="sans-serif" font-size="12px"
						padding="1px" text-align="center">
						<fo:inline>
							<xsl:value-of
								select="d:dokument_resenje/d:opis_postupka" />
						</fo:inline>
					</fo:block>

					<br></br>

					<fo:block font-family="sans-serif" font-size="12px"
						padding="1px" text-align="center">
						<xsl:value-of
							select="d:dokument_resenje/d:tekst_resenja" />
					</fo:block>
					<br></br>
					
					<br>
					</br>
					<fo:block font-family="sans-serif" font-size="12px"
						padding="1px" text-align="center">
						<xsl:value-of
							select="d:dokument_resenje/d:tekst_obrazlozenja" />
					</fo:block>
					<br></br>
					
					
					<fo:block text-align="right">
						POVERENIK
					</fo:block>


					<fo:block text-align="right">
						<xsl:value-of
							select="d:dokument_resenje/d:potpis_poverenika" />
					</fo:block>


				</fo:flow>
			</fo:page-sequence>
		</fo:root>
	</xsl:template>

</xsl:stylesheet>