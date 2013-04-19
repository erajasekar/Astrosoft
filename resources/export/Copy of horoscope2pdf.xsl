<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.1" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
xmlns:fo="http://www.w3.org/1999/XSL/Format" exclude-result-prefixes="fo">	

<xsl:import href="info-template.xsl"/>
<xsl:import href="chart-template.xsl"/>
<xsl:import href="varga-chart-template.xsl"/>
<xsl:import href="planet-pos-template.xsl"/>
<xsl:import href="bhava-pos-template.xsl"/>
<xsl:import href="ashtavarga-template.xsl"/>
<xsl:import href="shadbala-template.xsl"/>
<xsl:import href="vimdasa-template.xsl"/>
<xsl:import href="header-footer-template.xsl" />
<xsl:import href="table-header-row-template.xsl" />
<xsl:import href="table-row-template.xsl" />

	<xsl:output method="xml" version="1.0" omit-xml-declaration="no" indent="yes"/>
	
	<xsl:template match="Horoscope">
		<fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">

			<!-- ******************* Define Page Layout *********************************** -->
			<fo:layout-master-set>
				<fo:simple-page-master master-name="SimplePage" page-height="29.7cm" page-width="21cm" margin-top="2cm" margin-bottom="2cm" margin-left="2cm" margin-right="2cm">

					<fo:region-body />
					<fo:region-before />
					<fo:region-after  />
					
				</fo:simple-page-master>
				
				<fo:simple-page-master master-name="TwoColumnPage" page-height="29.7cm" page-width="21cm" margin-top="2cm" margin-bottom="2cm" margin-left="2cm" margin-right="2cm">
					
					<fo:region-body column-count="2" column-gap="25pt"  />
					<fo:region-before />
					<fo:region-after  />
					
				</fo:simple-page-master>
				
				<fo:simple-page-master master-name="ThreeColumnPage" page-height="29.7cm" page-width="21cm" margin-top="2cm" margin-bottom="2cm" margin-left="2cm" margin-right="2cm">
					
					<fo:region-body column-count="3" column-gap="25pt"  />
					<fo:region-before />
					<fo:region-after  />
					
				</fo:simple-page-master>
				
				<fo:simple-page-master master-name="FourColumnPage" page-height="29.7cm" page-width="21cm" margin-top="2cm" margin-bottom="2cm" margin-left="2cm" margin-right="2cm">
					
					<fo:region-body column-count="4" column-gap="30pt"  />
					<fo:region-before />
					<fo:region-after  />
					
				</fo:simple-page-master>
			</fo:layout-master-set>

			
			<fo:page-sequence master-reference="TwoColumnPage">
			
				<xsl:call-template name="header-footer" />

				<fo:flow flow-name="xsl-region-body">
					
					<fo:block>
						<xsl:apply-templates select="Horoscope-Information" />
						
					</fo:block>

					<fo:block margin-top="50pt" >
						
						<xsl:apply-templates select="Varga-Chart/Chart[@Name='Rasi']" /> 

					</fo:block>

					<fo:block margin-top="439pt" >
						
						<xsl:apply-templates select="Varga-Chart/Chart[@Name='Navamsa']" /> 
						
					</fo:block>
				</fo:flow>

			</fo:page-sequence>

			<!--
			<fo:page-sequence master-reference="SimplePage">
			
				 <xsl:call-template name="header-footer" />
			 
				<fo:flow flow-name="xsl-region-body">

					<fo:block>
						
						<xsl:apply-templates select="Planetary-Position" />
						<xsl:apply-templates select="Bhava-Positions" />
						
					</fo:block>
				</fo:flow>
			</fo:page-sequence>

						
			
			<fo:page-sequence master-reference="TwoColumnPage">
			
				<xsl:call-template name="header-footer" />
				
				<fo:flow flow-name="xsl-region-body">
					
					<xsl:apply-templates select="Varga-Chart" />
				
				</fo:flow>
			</fo:page-sequence> 
	
			
			<fo:page-sequence master-reference="FourColumnPage">
			
				<xsl:call-template name="header-footer" />
				
				<fo:flow flow-name="xsl-region-body">
					
					<fo:block>
						<xsl:apply-templates select="Ashtavargas" />
					</fo:block>

				</fo:flow>

			</fo:page-sequence> 

			<fo:page-sequence master-reference="SimplePage">
			
				 <xsl:call-template name="header-footer" />
				 
				<fo:flow flow-name="xsl-region-body">
					
					<fo:block>
						<xsl:apply-templates select="Shadbalas" />
					</fo:block>

				</fo:flow>

			</fo:page-sequence> 
			
			<fo:page-sequence master-reference="ThreeColumnPage">
				
				<xsl:call-template name="header-footer" />
				<fo:flow flow-name="xsl-region-body">
					
					<fo:block>
						<xsl:apply-templates select="Vimshottari-Dasas" />
					</fo:block>

				</fo:flow>

			</fo:page-sequence>-->

		</fo:root>
	</xsl:template>
 
</xsl:stylesheet>

