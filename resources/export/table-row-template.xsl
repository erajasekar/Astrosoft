<?xml version='1.0'?>
<xsl:stylesheet version="1.1" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
xmlns:fo="http://www.w3.org/1999/XSL/Format"  exclude-result-prefixes="fo">	

	<xsl:template name="table-row" >
	
		<fo:table-row height="15pt" >

			<xsl:for-each select="child::node()" >

				<fo:table-cell padding-left="5pt" padding-top="5pt" border-color="lightgray" border-right-style="solid" border-after-style="solid" >
					<fo:block  font-size="10pt" font-family="Tahoma">
						<xsl:value-of select="." />
					</fo:block>
				</fo:table-cell>

			</xsl:for-each>

		</fo:table-row>
	
	</xsl:template>

</xsl:stylesheet>
