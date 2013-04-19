<?xml version='1.0'?>
<xsl:stylesheet version="1.1" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
xmlns:fo="http://www.w3.org/1999/XSL/Format"  exclude-result-prefixes="fo">	


<xsl:template name='panchang-template' >

	<xsl:for-each select="Month" >
		<xsl:call-template name="panchang-month-template" />
	</xsl:for-each>

</xsl:template>
	
	
<xsl:template name="panchang-month-template"	 >

	<fo:page-sequence master-reference="LandscapePage">
	
		 <xsl:call-template name="panchang-header-footer" /> 
		 
		<fo:flow flow-name="xsl-region-body">
					
					<fo:block >
						
							<xsl:call-template name="panchang-page-template" >
							
								<xsl:with-param name="start"  select="'1'"/>
								<xsl:with-param name="end"  select="'8'"/>
								
							</xsl:call-template>
							
					</fo:block>
					
		</fo:flow>

	</fo:page-sequence>
	
	<fo:page-sequence master-reference="LandscapePage">
	
		<xsl:call-template name="panchang-header-footer" /> 
		
		<fo:flow flow-name="xsl-region-body">
					
					<fo:block >
						
							<xsl:call-template name="panchang-page-template" >
							
								<xsl:with-param name="start"  select="'9'"/>
								<xsl:with-param name="end"  select="'16'"/>
								
							</xsl:call-template>
							
					</fo:block>
					
		</fo:flow>

	</fo:page-sequence>
	<fo:page-sequence master-reference="LandscapePage">
	
		<xsl:call-template name="panchang-header-footer" /> 
		
		<fo:flow flow-name="xsl-region-body">
					
					<fo:block >
						
							<xsl:call-template name="panchang-page-template" >
							
								<xsl:with-param name="start"  select="'17'"/>
								<xsl:with-param name="end"  select="'24'"/>
								
							</xsl:call-template>
							
					</fo:block>
					
		</fo:flow>

	</fo:page-sequence>
	
	<fo:page-sequence master-reference="LandscapePage">
	
		<xsl:call-template name="panchang-header-footer" /> 
		
		<fo:flow flow-name="xsl-region-body">
					
					<fo:block >
						
							<xsl:call-template name="panchang-page-template" >
							
								<xsl:with-param name="start"  select="'25'"/>
								<xsl:with-param name="end"  select="'31'"/>
								
							</xsl:call-template>
							
					</fo:block>
					
		</fo:flow>

	</fo:page-sequence>
	
</xsl:template>	
	
	<!--<fo:table width="500pt" margin-top="40" table-layout="fixed">
		<fo:table-body> <fo:table-row> <fo:table-cell padding-top="5" padding-after="5" >
			<fo:block text-align="center" >
				 Panchang
			</fo:block>
		</fo:table-cell></fo:table-row></fo:table-body>
	</fo:table>-->

<xsl:template name="panchang-page-template" >

<xsl:param name="start"  />
<xsl:param name="end"  />

	<fo:table width="785pt" margin-top="25" table-layout="fixed" border-color="lightgray" >
	
			<fo:table-column column-width="45pt"/>
			<fo:table-column column-width="50pt"/>
			<fo:table-column column-width="95pt"/>
			<fo:table-column column-width="75pt"/>
			<fo:table-column column-width="45pt"/>
			<fo:table-column column-width="75pt"/>
			<fo:table-column column-width="75pt"/>
			<fo:table-column column-width="45pt"/>
			<fo:table-column column-width="45pt"/>
			<fo:table-column column-width="75pt"/>
			<fo:table-column column-width="80pt"/>
			<fo:table-column column-width="80pt"/>


		<fo:table-header> <fo:table-row height="17pt" >

			<xsl:for-each select="Panchang-Information[$start]/Information" >

				<fo:table-cell padding-left="5pt" padding-top="5pt"    border-color="lightgray" border-before-style="solid" border-after-style="solid" >
					<fo:block  font-size="8pt" font-family="Tahoma" font-weight="700">
						<xsl:value-of select="@Name" />
					</fo:block>
				</fo:table-cell>

			</xsl:for-each>

		</fo:table-row></fo:table-header>
		
		<fo:table-body >

			<xsl:for-each select="Panchang-Information[position() &gt;= $start and position() &lt;= $end]" >

				<fo:table-row height="15pt" >
				
					<xsl:for-each select="Information" >
		
						<fo:table-cell padding-left="5pt" padding-top="5pt" border-color="lightgray" border-after-style="solid" >
							<fo:block  font-size="8pt" font-family="Tahoma" >
								<xsl:value-of select="." />
							</fo:block>
						</fo:table-cell>
		
					</xsl:for-each>
		
				</fo:table-row>
				

			</xsl:for-each>
		</fo:table-body>
	</fo:table>

</xsl:template>

</xsl:stylesheet>