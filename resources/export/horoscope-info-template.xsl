<?xml version='1.0'?>
<xsl:stylesheet version="1.1" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
xmlns:fo="http://www.w3.org/1999/XSL/Format"  exclude-result-prefixes="fo">	

	<!-- ******************************************************************** -->
	<!-- Template for displaying horoscope information starts -->
	<!-- ******************************************************************** -->
	<xsl:template match="Horoscope-Information">

		<fo:table width="500pt" table-layout="fixed" margin-top="40pt">
			<fo:table-body> <fo:table-row> <fo:table-cell padding-top="5" padding-after="5">
				<fo:block text-align="center">
						Horoscope Information
				</fo:block>
			</fo:table-cell></fo:table-row></fo:table-body>
		</fo:table>
		
		<fo:table width="500pt" table-layout="fixed">
			<fo:table-body>
				<fo:table-row>
					<fo:table-cell width="300pt" padding-left="100pt">
							
						<xsl:call-template name="info-template" >
							<xsl:with-param name="info-of" select="'Horoscope'" />
						</xsl:call-template>
						
					</fo:table-cell>
				</fo:table-row>
			</fo:table-body>
		</fo:table>

	</xsl:template>

	<!-- ******************************************************************** -->
	<!-- Template for displaying horoscope information ends -->
	<!-- ******************************************************************** -->
	
	

</xsl:stylesheet>
