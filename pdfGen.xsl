<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.1" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
xmlns:fo="http://www.w3.org/1999/XSL/Format" xmlns:java="java" exclude-result-prefixes="fo">	
	<xsl:output method="xml" version="1.0" omit-xml-declaration="no" indent="yes"/>
	<!-- ========================= -->
	<!-- root element: Catalogue-->
	<!-- ========================= -->
	<xsl:template match="Catalogue">
		<fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">

			<fo:layout-master-set>
<!-- (1. Define the page margins) -->
				<fo:simple-page-master master-name="simpleA4" page-height="29.7cm" page-width="21cm" 
margin-top="2cm" margin-bottom="2cm" margin-left="2cm" margin-right="2cm">
					<fo:region-body/>
				</fo:simple-page-master>
			</fo:layout-master-set>
<!-- (2. For the page layout refer to the master layout)-->
			<fo:page-sequence master-reference="simpleA4">
				<fo:flow flow-name="xsl-region-body">
					<fo:block font-size="16pt" font-weight="bold" space-after="5mm">Catalog Information
					</fo:block>
 
<!--(3. Defining the block with table definition to display data-->

					<fo:block font-size="10pt">
						<fo:table table-layout="fixed">
							<fo:table-column column-width="8cm"/>
							<fo:table-column column-width="8cm"/>
							<fo:table-column column-width="8cm"/>
							<fo:table-body>
								<xsl:apply-templates/>
							</fo:table-body>
						</fo:table>
					</fo:block>
				</fo:flow>
			</fo:page-sequence>
		</fo:root>
	</xsl:template>
 
	<!-- ========================= -->
	<!--Book Information    -->
	<!-- ========================= -->
	
	<xsl:template match="Book">
	 <fo:table-row>
		<fo:table-cell>
			<fo:block>
			  <xsl:value-of select="Title"/>
			</fo:block>
		</fo:table-cell>
		<fo:table-cell>
			<fo:block>
   			   <xsl:value-of select="Author"/>
			</fo:block>
		</fo:table-cell>
            <fo:table-cell>
			<fo:block>
   			   <xsl:value-of select="Price"/>
			</fo:block>
		</fo:table-cell>
 
 
	</fo:table-row>
	</xsl:template>	
	
</xsl:stylesheet>


