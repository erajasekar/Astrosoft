<?xml version='1.0'?>
<xsl:stylesheet version="1.1" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
xmlns:fo="http://www.w3.org/1999/XSL/Format"  exclude-result-prefixes="fo">	

	<xsl:template name="info-template" >
	<xsl:param name="info-of"  />
	
	<xsl:choose>
		
		<xsl:when test="$info-of = 'Horoscope'">
		
		<fo:table width="300pt" margin-top="10" border-collapse="collapse"   border-color="lightgray" table-layout="fixed" border-left-style="solid" border-top-style="solid">
		
			<xsl:call-template name="info-body" />
			
		</fo:table>
				
				
		</xsl:when>
		<xsl:otherwise>
		
			<fo:table width="200pt" margin-top="10" border-collapse="collapse"   border-color="lightgray" table-layout="fixed" border-left-style="solid" border-top-style="solid">
			
				<xsl:call-template name="info-body" />
			
			</fo:table>
		
		</xsl:otherwise>
	
	</xsl:choose>
	
	</xsl:template>
	
	<xsl:template name="info-body" >
		
			<fo:table-body  >

				<xsl:for-each select="Information" >

					<fo:table-row height="15pt"  >

						<fo:table-cell padding-left="10pt" padding-top="5pt"  border-color="lightgray" border-right-style="solid" border-bottom-style="solid">
							<fo:block  text-align="left"  font-size="10pt" font-family="Tahoma">
								<xsl:value-of select="@Name" />
							</fo:block>
						</fo:table-cell>

						<fo:table-cell padding-left="10pt" padding-top="5pt" border-color="lightgray" border-right-style="solid" border-bottom-style="solid"  >
							<fo:block   font-size="10pt" font-family="Tahoma" text-align="left" >
								<xsl:value-of select="." />
							</fo:block>
						</fo:table-cell>

					</fo:table-row>

				</xsl:for-each>
			</fo:table-body>

	</xsl:template>

</xsl:stylesheet>
