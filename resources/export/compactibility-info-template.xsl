<?xml version='1.0'?>
<xsl:stylesheet version="1.1" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
xmlns:fo="http://www.w3.org/1999/XSL/Format"  exclude-result-prefixes="fo">	

	<!-- ******************************************************************** -->
	<!-- Template for displaying compactibility information starts -->
	<!-- ******************************************************************** -->
	<xsl:template match="Horoscope-Information">
	<xsl:param name="title"  />


		<fo:table width="200pt" table-layout="fixed" margin-top="20pt">
			<fo:table-body> <fo:table-row> <fo:table-cell padding-top="5" padding-after="5">
				<fo:block text-align="center">
				
						<xsl:value-of select="$title" /> Information
						 
				</fo:block>
			</fo:table-cell></fo:table-row></fo:table-body>
		</fo:table>
		
		<fo:table width="200pt" table-layout="fixed">
			<fo:table-body>
				<fo:table-row>
					<fo:table-cell width="200pt" padding-left="20pt">
							
						<xsl:call-template name="info-template" >
							<xsl:with-param name="info-of" select="'Compactibility'" />
						</xsl:call-template>
						
						
					</fo:table-cell>
				</fo:table-row>
			</fo:table-body>
		</fo:table>

	</xsl:template>

</xsl:stylesheet>
