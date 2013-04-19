<?xml version='1.0'?>
<xsl:stylesheet version="1.1" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
xmlns:fo="http://www.w3.org/1999/XSL/Format"  exclude-result-prefixes="fo">	


	<xsl:template match="Shadbalas">
	
		<xsl:apply-templates select="Shadbala" />	
		<xsl:apply-templates select="Bhavabala" />
		<xsl:apply-templates select="Sthanabala" />
		<xsl:apply-templates select="Kalabala" />

	</xsl:template>

	<xsl:template match="Shadbala">

		<!-- TABLE TITLE -->
		<fo:table width="500pt" margin-top="30pt" table-layout="fixed">
			<fo:table-body> <fo:table-row> <fo:table-cell padding-top="5" padding-after="5" >
				<fo:block text-align="center">
					 Shadbala
				</fo:block>
			</fo:table-cell></fo:table-row></fo:table-body>
		</fo:table>

		<fo:table width="500pt"  margin-top="10pt" table-layout="fixed" border-color="lightgray" border-left-style="solid" border-top-style="solid">
		
			<fo:table-column column-width="95pt"/>
			<fo:table-column column-width="45pt"/>
			<fo:table-column column-width="45pt"/>
			<fo:table-column column-width="45pt"/>
			<fo:table-column column-width="45pt"/>
			<fo:table-column column-width="45pt"/>
			<fo:table-column column-width="45pt"/>
			<fo:table-column column-width="45pt"/>
			<fo:table-column column-width="45pt"/>
			<fo:table-column column-width="45pt"/>
		
			<!-- TABLE HEADER -->

			<fo:table-header> <fo:table-row height="17pt" >

				<xsl:for-each select="Bala[1]/child::node()" >

					<xsl:call-template name="table-header-row" />

				</xsl:for-each>

			</fo:table-row></fo:table-header>

			<fo:table-body  border-collapse="collapse">

				<xsl:for-each select="Bala" >

					<xsl:apply-templates select="." />

				</xsl:for-each>

			</fo:table-body>
		</fo:table>


	</xsl:template>

	<xsl:template match="Bhavabala">

		<!-- TABLE TITLE -->

		<fo:table width="500pt" margin-top="30pt" table-layout="fixed">
			<fo:table-body> <fo:table-row> <fo:table-cell padding-top="5" padding-after="5" >
				<fo:block text-align="center">
					 Bhavabala
				</fo:block>
			</fo:table-cell></fo:table-row></fo:table-body>
		</fo:table>

		<fo:table width="500pt"   margin-top="10pt" table-layout="fixed" border-color="lightgray" border-left-style="solid" border-top-style="solid" >
		
			<fo:table-column column-width="40pt"/>
			<fo:table-column column-width="50pt"/>
			<fo:table-column column-width="85pt"/>
			<fo:table-column column-width="75pt"/>
			<fo:table-column column-width="75pt"/>
			<fo:table-column column-width="75pt"/>
			<fo:table-column column-width="50pt"/>
			<fo:table-column column-width="50pt"/>
		
			<!-- TABLE HEADER -->
			<fo:table-header> <fo:table-row height="15pt" >

				<xsl:for-each select="Bala[1]/child::node()" >

					<fo:table-cell padding-left="5pt" padding-top="5pt"   border-color="lightgray" border-after-style="groove" border-right-style="solid">
			<fo:block  font-size="8pt" font-family="Tahoma" font-weight="700">
				<xsl:value-of select="name()" />
			</fo:block>
		</fo:table-cell>
					
				</xsl:for-each>

			</fo:table-row></fo:table-header>

			<fo:table-body >

				<xsl:for-each select="Bala" >

					<xsl:apply-templates select="." />

				</xsl:for-each>

			</fo:table-body>
		</fo:table>

	</xsl:template>
	
	<xsl:template match="Sthanabala">

		<!-- TABLE TITLE -->
		<fo:table width="500pt" margin-top="30" table-layout="fixed">
			<fo:table-body> <fo:table-row> <fo:table-cell padding-top="5" padding-after="5" >
				<fo:block text-align="center">
					 Sthanabala
				</fo:block>
			</fo:table-cell></fo:table-row></fo:table-body>
		</fo:table>

		<fo:table width="500pt"  margin-top="10pt" table-layout="fixed" border-color="lightgray" border-left-style="solid" border-top-style="solid" >
		
			<fo:table-column column-width="95pt"/>
			<fo:table-column column-width="45pt"/>
			<fo:table-column column-width="45pt"/>
			<fo:table-column column-width="45pt"/>
			<fo:table-column column-width="45pt"/>
			<fo:table-column column-width="45pt"/>
			<fo:table-column column-width="45pt"/>
			<fo:table-column column-width="45pt"/>
			<fo:table-column column-width="45pt"/>
			<fo:table-column column-width="45pt"/>
		
			<!-- TABLE HEADER -->

			<fo:table-header> <fo:table-row height="17pt" >

				<xsl:for-each select="Bala[1]/child::node()" >

					<xsl:call-template name="table-header-row" />


				</xsl:for-each>

			</fo:table-row></fo:table-header>

			<fo:table-body >

				<xsl:for-each select="Bala" >

					<xsl:apply-templates select="." />

				</xsl:for-each>

			</fo:table-body>
		</fo:table>


	</xsl:template>

	<xsl:template match="Kalabala" >

		<!-- TABLE TITLE -->
		<fo:table width="500pt" margin-top="30pt" table-layout="fixed">
			<fo:table-body> <fo:table-row> <fo:table-cell padding-top="5" padding-after="5" >
				<fo:block text-align="center">
					 Kalabala
				</fo:block>
			</fo:table-cell></fo:table-row></fo:table-body>
		</fo:table>

		<fo:table width="500pt"  margin-top="10pt" table-layout="fixed" border-color="lightgray" border-left-style="solid" border-top-style="solid" >
		
			<fo:table-column column-width="95pt"/>
			<fo:table-column column-width="45pt"/>
			<fo:table-column column-width="45pt"/>
			<fo:table-column column-width="45pt"/>
			<fo:table-column column-width="45pt"/>
			<fo:table-column column-width="45pt"/>
			<fo:table-column column-width="45pt"/>
			<fo:table-column column-width="45pt"/>
			<fo:table-column column-width="45pt"/>
			<fo:table-column column-width="45pt"/>
		
			<!-- TABLE HEADER -->

			<fo:table-header> <fo:table-row height="17pt" >

				<xsl:for-each select="Bala[1]/child::node()" >

					<xsl:call-template name="table-header-row" />

				</xsl:for-each>

			</fo:table-row></fo:table-header>

			<fo:table-body >

				<xsl:for-each select="Bala" >

					<xsl:apply-templates select="." />

				</xsl:for-each>

			</fo:table-body>
		</fo:table>


	</xsl:template>


	<xsl:template match="Bala" >

		<xsl:call-template name="table-row" />

	</xsl:template>

</xsl:stylesheet>