<?xml version='1.0'?>
<xsl:stylesheet version="1.1" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
xmlns:fo="http://www.w3.org/1999/XSL/Format" exclude-result-prefixes="fo">	


	<xsl:template name="compactibility-chart-template">
		
		<fo:table width="500pt" margin-top="40" table-layout="fixed" padding-left="40pt">
			<fo:table-body> <fo:table-row> <fo:table-cell padding-top="5" padding-after="5" >
				<fo:block text-align="center">
					 Boy Horoscope
				</fo:block>
			</fo:table-cell></fo:table-row></fo:table-body>
		</fo:table>
		
		<fo:table width="500pt" margin-top="10" table-layout="fixed" padding-left="20pt">
			<fo:table-body> <fo:table-row> 
				<fo:table-cell padding-top="5" padding-after="5"  >
					<fo:block margin-top="20pt" >
						
						<xsl:apply-templates select="Boy/Chart[@Name='Rasi']" /> 

					</fo:block>
				</fo:table-cell>
			
				<fo:table-cell padding-top="5" padding-after="5"  >
					<fo:block margin-top="20pt" >
						
						<xsl:apply-templates select="Boy/Chart[@Name='Navamsa']" /> 

					</fo:block>
			</fo:table-cell>
			
			</fo:table-row></fo:table-body>
		</fo:table>
		
		<fo:table width="500pt" margin-top="40" table-layout="fixed" padding-left="40pt">
			<fo:table-body> <fo:table-row> <fo:table-cell padding-top="5" padding-after="5" >
				<fo:block text-align="center">
					 Girl Horoscope
				</fo:block>
			</fo:table-cell></fo:table-row></fo:table-body>
		</fo:table>
		
		<fo:table width="500pt" margin-top="10" table-layout="fixed" padding-left="20pt">
			<fo:table-body> <fo:table-row> 
				<fo:table-cell padding-top="5" padding-after="5"  >
					<fo:block margin-top="20pt" >
						
						<xsl:apply-templates select="Girl/Chart[@Name='Rasi']" /> 

					</fo:block>
				</fo:table-cell>
			
				<fo:table-cell padding-top="5" padding-after="5"  >
					<fo:block margin-top="20pt" >
						
						<xsl:apply-templates select="Girl/Chart[@Name='Navamsa']" /> 

					</fo:block>
			</fo:table-cell>
			
			</fo:table-row></fo:table-body>
		</fo:table>
	
	</xsl:template>

</xsl:stylesheet>

