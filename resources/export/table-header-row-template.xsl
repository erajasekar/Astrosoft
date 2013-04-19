<?xml version='1.0'?>
<xsl:stylesheet version="1.1" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
xmlns:fo="http://www.w3.org/1999/XSL/Format"  exclude-result-prefixes="fo">	

	<xsl:template name="table-header-row" >
	
		<fo:table-cell padding-left="5pt" padding-top="5pt"    border-color="lightgray" border-after-style="groove" border-right-style="solid">
			<fo:block  font-size="10pt" font-family="Tahoma" font-weight="700">
				<xsl:value-of select="name()" />
			</fo:block>
		</fo:table-cell>
	
	</xsl:template>

</xsl:stylesheet>
