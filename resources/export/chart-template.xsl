<?xml version='1.0'?>
<xsl:stylesheet version="1.1" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
xmlns:fo="http://www.w3.org/1999/XSL/Format"  exclude-result-prefixes="fo">	


	<!-- ******************************************************************** -->
	<!-- Template for drawing chart starts -->
	<!-- ******************************************************************** -->

	<xsl:template match="Chart">
	
		<fo:table width="180pt" border-separation="1pt" table-layout="fixed">

			<fo:table-body >

				<fo:table-row> 
					<xsl:apply-templates select="House[@No = 12]" />
					<xsl:apply-templates select="House[@No = 1]" />
					<xsl:apply-templates select="House[@No = 2]" />
					<xsl:apply-templates select="House[@No = 3]" />
				</fo:table-row>

				<fo:table-row> 
					<xsl:apply-templates select="House[@No = 11]" />

					<fo:table-cell number-columns-spanned="2" number-rows-spanned="2" height="50pt" width="60pt" > 
						<fo:block padding-top="40" text-align="center"  font-size="12pt" font-family="Tahoma" font-weight="700"> 
							<xsl:value-of select="@Name" /> 
						</fo:block> 
					</fo:table-cell>
					
					<xsl:apply-templates select="House[@No = 4]" />
				</fo:table-row>

				<fo:table-row> 
					<xsl:apply-templates select="House[@No = 10]" />
					
					<xsl:apply-templates select="House[@No = 5]" />
				</fo:table-row>

				<fo:table-row> 
					<xsl:apply-templates select="House[@No = 9]" />
					<xsl:apply-templates select="House[@No = 8]" />
					<xsl:apply-templates select="House[@No = 7]" />
					<xsl:apply-templates select="House[@No = 6]" />
				</fo:table-row>

		    </fo:table-body>
	    </fo:table>
	</xsl:template>	

	

	<!-- ******************************************************************** -->
	<!-- Template for drawing house in chart begins -->
	<!-- ******************************************************************** -->

    <xsl:template match="House" >
	<fo:table-cell  border-width="0.3pt" border-style="solid" height="50pt" width="60pt" >

		<fo:table height="50pt" width="60pt" table-layout="fixed"> <fo:table-body>
		
			<xsl:choose>

				<xsl:when test="count(Planet) = 1">

					<fo:table-row> 
						<fo:table-cell padding-top="11pt" number-columns-spanned="3" > 
							<fo:block /> 
						</fo:table-cell>
					</fo:table-row>

					<fo:table-row> 
						<fo:table-cell padding-top="5pt"> <fo:block /> </fo:table-cell>
							<xsl:apply-templates select="Planet[1]" />	
						<fo:table-cell padding-top="5pt"> <fo:block /> </fo:table-cell>
					</fo:table-row>
				
					<fo:table-row> 
						<fo:table-cell padding-top="5pt" number-columns-spanned="3" > 
							<fo:block /> 
						</fo:table-cell>
					</fo:table-row>

				</xsl:when>

				<xsl:when test="count(Planet) = 2">

					<fo:table-row> 
						<xsl:apply-templates select="Planet[1]" />
						<fo:table-cell padding-top="5pt" number-columns-spanned="2"> <fo:block /> </fo:table-cell>
					</fo:table-row>

					<fo:table-row> 
						<fo:table-cell padding-top="11pt" number-columns-spanned="3" > 
							<fo:block /> 
						</fo:table-cell>
					</fo:table-row>

					<fo:table-row> 
						<fo:table-cell padding-top="5pt" number-columns-spanned="2"> <fo:block /> </fo:table-cell>
						<xsl:apply-templates select="Planet[2]" />
					</fo:table-row>

				</xsl:when>

				<xsl:when test="count(Planet) = 3">

					<fo:table-row> 
						<fo:table-cell padding-top="5pt"> <fo:block /> </fo:table-cell>
							<xsl:apply-templates select="Planet[1]" />	
						<fo:table-cell padding-top="5pt"> <fo:block /> </fo:table-cell>
					</fo:table-row>

					<fo:table-row> 
						<fo:table-cell padding-top="11pt" number-columns-spanned="3" > 
							<fo:block /> 
						</fo:table-cell>
					</fo:table-row>

					<fo:table-row> 
						<xsl:apply-templates select="Planet[2]" />	
						<fo:table-cell padding-top="5pt"> <fo:block /> </fo:table-cell>
						<xsl:apply-templates select="Planet[3]" />		
					</fo:table-row>

				</xsl:when>

				<xsl:when test="count(Planet) = 4">

					<fo:table-row> 
						<xsl:apply-templates select="Planet[1]" />	
						<fo:table-cell padding-top="5pt"> <fo:block /> </fo:table-cell>
						<xsl:apply-templates select="Planet[2]" />		
					</fo:table-row>

					<fo:table-row> 
						<fo:table-cell padding-top="11pt" number-columns-spanned="3" > 
							<fo:block /> 
						</fo:table-cell>
					</fo:table-row>

					<fo:table-row> 
						<xsl:apply-templates select="Planet[3]" />	
						<fo:table-cell padding-top="5pt"> <fo:block /> </fo:table-cell>
						<xsl:apply-templates select="Planet[4]" />		
					</fo:table-row>

				</xsl:when>

				<xsl:when test="count(Planet) = 5">

					<fo:table-row> 
						<xsl:apply-templates select="Planet[1]" />	
						<fo:table-cell padding-top="5pt"> <fo:block /> </fo:table-cell>
						<xsl:apply-templates select="Planet[2]" />		
					</fo:table-row>

					<fo:table-row> 
						<fo:table-cell padding-top="5pt"> <fo:block /> </fo:table-cell>
							<xsl:apply-templates select="Planet[5]" />	
						<fo:table-cell padding-top="5pt"> <fo:block /> </fo:table-cell>
					</fo:table-row>

					<fo:table-row> 
						<xsl:apply-templates select="Planet[3]" />	
						<fo:table-cell padding-top="5pt"> <fo:block /> </fo:table-cell>
						<xsl:apply-templates select="Planet[4]" />		
					</fo:table-row>

				</xsl:when>

				<xsl:when test="count(Planet) = 6">

					<fo:table-row> 
						<xsl:apply-templates select="Planet[1]" />	
						<fo:table-cell padding-top="5pt"> <fo:block /> </fo:table-cell>
						<xsl:apply-templates select="Planet[2]" />		
					</fo:table-row>

					<fo:table-row> 
						<xsl:apply-templates select="Planet[5]" />	
						<fo:table-cell padding-top="5pt"> <fo:block /> </fo:table-cell>
						<xsl:apply-templates select="Planet[6]" />		
					</fo:table-row>

					<fo:table-row> 
						<xsl:apply-templates select="Planet[3]" />	
						<fo:table-cell padding-top="5pt"> <fo:block /> </fo:table-cell>
						<xsl:apply-templates select="Planet[4]" />		
					</fo:table-row>

				</xsl:when>

				<xsl:when test="count(Planet) = 7">

					<fo:table-row> 
						<xsl:apply-templates select="Planet[1]" />	
						<fo:table-cell padding-top="5pt"> <fo:block /> </fo:table-cell>
						<xsl:apply-templates select="Planet[2]" />		
					</fo:table-row>

					<fo:table-row> 
						<xsl:apply-templates select="Planet[5]" />	
						<xsl:apply-templates select="Planet[6]" />	
						<xsl:apply-templates select="Planet[7]" />		
					</fo:table-row>

					<fo:table-row> 
						<xsl:apply-templates select="Planet[3]" />	
						<fo:table-cell padding-top="5pt"> <fo:block /> </fo:table-cell>
						<xsl:apply-templates select="Planet[4]" />		
					</fo:table-row>

				</xsl:when>

				<xsl:when test="count(Planet) = 8">

					<fo:table-row> 
						<xsl:apply-templates select="Planet[1]" />	
						<xsl:apply-templates select="Planet[2]" />	
						<xsl:apply-templates select="Planet[3]" />
					</fo:table-row>

					<fo:table-row> 
						<xsl:apply-templates select="Planet[7]" />	
						<fo:table-cell padding-top="5pt"> <fo:block /> </fo:table-cell>
						<xsl:apply-templates select="Planet[8]" />			
					</fo:table-row>

					<fo:table-row> 
						<xsl:apply-templates select="Planet[4]" />	
						<xsl:apply-templates select="Planet[5]" />	
						<xsl:apply-templates select="Planet[6]" />	
					</fo:table-row>

				</xsl:when>

				<xsl:when test="count(Planet) = 9">

					<fo:table-row>
						<xsl:apply-templates select="Planet[1]" />
						<xsl:apply-templates select="Planet[2]" />
						<xsl:apply-templates select="Planet[3]" />
					</fo:table-row>

					<fo:table-row>
						<xsl:apply-templates select="Planet[4]" />
						<xsl:apply-templates select="Planet[5]" />
						<xsl:apply-templates select="Planet[6]" />
					</fo:table-row>

					<fo:table-row>
						<xsl:apply-templates select="Planet[7]" />
						<xsl:apply-templates select="Planet[8]" />
						<xsl:apply-templates select="Planet[9]" />
					</fo:table-row>

				</xsl:when>

				<xsl:otherwise>

					<fo:table-row> <fo:table-cell>

						<fo:block  font-size="10pt" font-family="Tahoma">
							 <xsl:value-of select="." /> 
						</fo:block>

					</fo:table-cell></fo:table-row>

				</xsl:otherwise>

			</xsl:choose>
		
		</fo:table-body> </fo:table>
		
	</fo:table-cell>
	</xsl:template>

	

	<!-- ******************************************************************** -->
	<!-- Template for drawing planet in house begins -->
	<!-- ******************************************************************** -->

	<xsl:template match="Planet" >

		<fo:table-cell padding-top="4pt"  >
		
			<xsl:choose>
				
				<xsl:when test=". ='Asc' ">
				
					<fo:block text-align="center" font-family="Courier" font-size="10pt" font-weight="bold" font-style="italic">
						<xsl:value-of select="." />
					</fo:block>
				
				</xsl:when>
				
				<xsl:when test=". ='Mo' ">
				
					<fo:block text-align="center" font-family="Courier" font-size="10pt"  font-style="italic">
						<xsl:value-of select="." />
					</fo:block>
				
				</xsl:when>
				
				<xsl:otherwise>
				
					<fo:block text-align="center" font-family="Tahoma" font-size="10pt">
						<xsl:value-of select="." />
					</fo:block>
			
				</xsl:otherwise>
			
			</xsl:choose>
			
			
			

		</fo:table-cell>

	</xsl:template>

	<!-- ******************************************************************** -->
	<!-- Template for drawing planet in house ends -->
	<!-- ******************************************************************** -->

	<!-- ******************************************************************** -->
	<!-- Template for drawing house in chart ends -->
	<!-- ******************************************************************** -->

	<!-- ******************************************************************** -->
	<!-- Template for drawing chart ends -->
	<!-- ******************************************************************** -->


</xsl:stylesheet>