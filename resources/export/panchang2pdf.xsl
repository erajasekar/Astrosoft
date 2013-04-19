<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.1" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
xmlns:fo="http://www.w3.org/1999/XSL/Format" exclude-result-prefixes="fo">	

<xsl:import href="page-layouts.xsl" />
<xsl:import href="panchang-template.xsl" />
<xsl:import href="panchang-header-footer-template.xsl" />

	<xsl:output method="xml" version="1.0" omit-xml-declaration="no" indent="yes"/>
	
	<xsl:template match="Panchang">

		<fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">

			<xsl:call-template name="page-layouts" />
			<xsl:call-template name="panchang-template" />
			
			<!--<fo:page-sequence master-reference="LandscapePage">
			
				 <xsl:call-template name="header-footer" /> 

				<fo:flow flow-name="xsl-region-body">
					
					<fo:block break-after="page">
						
							<xsl:apply-templates select="Month" /> 
							
					</fo:block>
					
				</fo:flow>

			</fo:page-sequence>
			-->
		</fo:root>
	</xsl:template>
 
</xsl:stylesheet>