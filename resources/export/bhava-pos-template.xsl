<?xml version='1.0'?>
<xsl:stylesheet version="1.1" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
xmlns:fo="http://www.w3.org/1999/XSL/Format" exclude-result-prefixes="fo">	


	<xsl:template match="Bhava-Positions">
		
		<fo:table width="500pt" margin-top="40" table-layout="fixed" >
			<fo:table-body> <fo:table-row> <fo:table-cell padding-top="5" padding-after="5" >
				<fo:block text-align="center">
					 Bhava Position
				</fo:block>
			</fo:table-cell></fo:table-row></fo:table-body>
		</fo:table>
	
		<fo:table width="500pt" margin-top="10" table-layout="fixed" border-color="lightgray" border-left-style="solid" border-top-style="solid">
	
			<fo:table-header> <fo:table-row height="17pt" >
	
				<xsl:for-each select="Bhava-Position[1]/child::node()" >
	
					<xsl:call-template name="table-header-row" />
	
				</xsl:for-each>
	
			</fo:table-row></fo:table-header>
			
			<fo:table-body >
	
				<xsl:for-each select="Bhava-Position" >
	
					<xsl:call-template name="table-row" />
	
				</xsl:for-each>
			</fo:table-body>
		</fo:table>
	
	</xsl:template>

</xsl:stylesheet>