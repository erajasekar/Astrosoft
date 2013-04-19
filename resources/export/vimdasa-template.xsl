<?xml version='1.0'?>
<xsl:stylesheet version="1.1" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
xmlns:fo="http://www.w3.org/1999/XSL/Format"  exclude-result-prefixes="fo">	

	<xsl:template match="Vimshottari-Dasas">
	
		<xsl:for-each select="Major-Dasa" >
			<xsl:apply-templates select="." />	
		</xsl:for-each>
		
	</xsl:template>
	
	<xsl:template match="Major-Dasa">
	
		<fo:table width="160pt"   table-layout="fixed">
			<fo:table-body> <fo:table-row> <fo:table-cell padding-top="49pt"  >
		
				<fo:block />
				
			 </fo:table-cell></fo:table-row></fo:table-body>
		</fo:table>
		
		<xsl:apply-templates select="Sub-Dasa[1]" />
		<xsl:apply-templates select="Sub-Dasa[4]" />
		<xsl:apply-templates select="Sub-Dasa[7]" />
		
		<!-- DASA TITLE -->
		<fo:table width="160pt"   table-layout="fixed">
			<fo:table-body> 
					<fo:table-row> <fo:table-cell  padding-top="25pt" >
			
						<fo:block text-align="center" font-size="10pt"  font-weight="700" >
							 <xsl:value-of select="@Dasa" />
						</fo:block>
					
					 </fo:table-cell></fo:table-row>
					 
					 <fo:table-row> <fo:table-cell  >
			
						<fo:block text-align="center" font-size="10pt"  font-weight="700">
							 <xsl:value-of select="@Period" />
						</fo:block>
					
					 </fo:table-cell></fo:table-row>
					 
			 </fo:table-body>
		</fo:table>
		
		<xsl:apply-templates select="Sub-Dasa[2]" />
		<xsl:apply-templates select="Sub-Dasa[5]" />
		<xsl:apply-templates select="Sub-Dasa[8]" />
		
		<fo:table width="160pt"   table-layout="fixed">
			<fo:table-body> <fo:table-row> <fo:table-cell  padding-top="49pt">
		
				<fo:block />
				
			 </fo:table-cell></fo:table-row></fo:table-body>
		</fo:table>
		
		<xsl:apply-templates select="Sub-Dasa[3]" />
		<xsl:apply-templates select="Sub-Dasa[6]" />
		<xsl:apply-templates select="Sub-Dasa[9]" />
		
				
	</xsl:template>
	
	<xsl:template match="Sub-Dasa">
	
		<!-- TABLE TITLE -->
		
		<fo:table width="160pt" margin-top="27pt" table-layout="fixed">
			<fo:table-body> <fo:table-row> <fo:table-cell padding-top="5" padding-after="5" >
			
				<fo:block text-align="center" font-size="8pt" font-weight="700">
					 <xsl:value-of select="@Dasa" />
				</fo:block>
				
			</fo:table-cell></fo:table-row></fo:table-body>
		</fo:table>
	
		<fo:table width="160pt"  margin-top="2pt" table-layout="fixed"  border-color="lightgray" border-left-style="solid" border-top-style="solid">
		
			<!-- TABLE HEADER -->

			<fo:table-header> <fo:table-row height="15pt" >

				<xsl:for-each select="Anthara-Dasa[1]/child::node()" >

					<fo:table-cell padding-left="3pt" padding-top="5pt"   border-color="lightgray" border-after-style="groove" border-right-style="solid">
			<fo:block  font-size="10pt" font-family="Tahoma" font-weight="700">
				<xsl:value-of select="name()" />
			</fo:block>
		</fo:table-cell>

				</xsl:for-each>

			</fo:table-row></fo:table-header>
			
			<fo:table-body  border-collapse="collapse">	
	
				<xsl:for-each select="Anthara-Dasa" >
				
					<xsl:apply-templates select="." />	
					
				</xsl:for-each>
			
			</fo:table-body>
		</fo:table>
		
	</xsl:template>
	
	<xsl:template match="Anthara-Dasa" >

		<fo:table-row height="12pt"   >

			<xsl:for-each select="child::node()" >

				<fo:table-cell padding-left="3pt" padding-top="3pt" border-color="lightgray" border-right-style="solid" border-after-style="solid" >
					<fo:block  font-size="8pt" font-family="Tahoma">
						<xsl:value-of select="." />
					</fo:block>
				</fo:table-cell>

			</xsl:for-each>

		</fo:table-row>

	</xsl:template>

</xsl:stylesheet>
