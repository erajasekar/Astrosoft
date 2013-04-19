<?xml version='1.0'?>
<xsl:stylesheet version="1.1" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
xmlns:fo="http://www.w3.org/1999/XSL/Format" xmlns:java="java" exclude-result-prefixes="fo">	


	<xsl:template match="Varga-Chart">
	
		<fo:table width="500pt" table-layout="fixed" margin-top="30pt">	<fo:table-body>
				<fo:table-row>
					<fo:table-cell>
						<fo:block font-weight="700" font-size="12pt" text-align="center"> 
							Varga Charts
						</fo:block>
					</fo:table-cell>
				</fo:table-row>
		</fo:table-body></fo:table>
		
		<fo:block margin-top="15pt" margin-bottom="15pt" >

			<xsl:apply-templates select="Chart[@Name='Bhava']" />

		</fo:block>

		<fo:block margin-top="15pt" margin-bottom="15pt" >

			<xsl:apply-templates select="Chart[@Name='Hora']" />

		</fo:block>
			

		<fo:block margin-top="15pt" margin-bottom="15pt" >

			<xsl:apply-templates select="Chart[@Name='Chaturtamsa']" />

		</fo:block>

		<fo:block margin-top="57pt" margin-bottom="15pt" >

			<xsl:apply-templates select="Chart[@Name='Rasi']" />

		</fo:block>

		<fo:block margin-top="15pt" margin-bottom="15pt" >

			<xsl:apply-templates select="Chart[@Name='Drekkana']" />

		</fo:block>

		<fo:block margin-top="15pt" margin-bottom="15pt" >

			<xsl:apply-templates select="Chart[@Name='Navamsa']" />

		</fo:block>

		<fo:block margin-top="57pt" margin-bottom="15pt" >

			<xsl:apply-templates select="Chart[@Name='Saptamsa']" />

		</fo:block>

		<fo:block margin-top="15pt" margin-bottom="15pt" >

			<xsl:apply-templates select="Chart[@Name='Dwadasamsa']" />

		</fo:block>

		<fo:block margin-top="15pt" margin-bottom="15pt" >

			<xsl:apply-templates select="Chart[@Name='Vimshamsa']" />

		</fo:block>

		<fo:block margin-top="57pt" margin-bottom="15pt" >

			<xsl:apply-templates select="Chart[@Name='Dasamsa']" />

		</fo:block>

		<fo:block margin-top="15pt" margin-bottom="15pt" >

			<xsl:apply-templates select="Chart[@Name='Shodasamsa']" />

		</fo:block>

		<fo:block margin-top="15pt" margin-bottom="15pt" >

			<xsl:apply-templates select="Chart[@Name='ChaturVimshamsa']" />

		</fo:block>

		<fo:block margin-top="57pt" margin-bottom="15pt" >

			<xsl:apply-templates select="Chart[@Name='Bhamsa']" />

		</fo:block>

		<fo:block margin-top="15pt" margin-bottom="15pt" >

			<xsl:apply-templates select="Chart[@Name='Khavedamsa']" />

		</fo:block>

		<fo:block margin-top="15pt" margin-bottom="15pt" >

			<xsl:apply-templates select="Chart[@Name='Shastiamsa']" />

		</fo:block>

		<fo:block margin-top="57pt" margin-bottom="15pt" >

			<xsl:apply-templates select="Chart[@Name='Trimshamsa']" />

		</fo:block>

		<fo:block margin-top="15pt" margin-bottom="15pt" >

			<xsl:apply-templates select="Chart[@Name='Akshvedamsa']" />

		</fo:block>

		<fo:block margin-top="15pt" margin-bottom="15pt" >

			<xsl:apply-templates select="Chart[@Name='Panchamsa']" />

		</fo:block>

		<fo:block margin-top="57pt" margin-bottom="15pt" >

			<xsl:apply-templates select="Chart[@Name='Shashtamsa']" />

		</fo:block>

		<fo:block margin-top="15pt" margin-bottom="15pt" >

			<xsl:apply-templates select="Chart[@Name='Ashtamsa']" />

		</fo:block>

		<fo:block margin-top="15pt" margin-bottom="15pt" >

			<xsl:apply-templates select="Chart[@Name='Ekadamsa']" />

		</fo:block>
	
	</xsl:template>

</xsl:stylesheet>