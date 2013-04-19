<?xml version='1.0'?>
<xsl:stylesheet version="1.1" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
xmlns:fo="http://www.w3.org/1999/XSL/Format"  xmlns:av="Ashtavarga" exclude-result-prefixes="fo">	

	<xsl:template match="Ashtavargas">
		
		<fo:block margin-top="64pt" margin-bottom="43pt" >
			<xsl:apply-templates select="Ashtavarga[@Name='Sun']/av:Chart[1]" />
		</fo:block>
		
		<fo:block margin-top="43pt" margin-bottom="43pt" >
			<xsl:apply-templates select="Ashtavarga[@Name='Moon']/av:Chart[1]" />
		</fo:block>

		<fo:block margin-top="43pt" margin-bottom="43pt" >
			<xsl:apply-templates select="Ashtavarga[@Name='Mars']/av:Chart[1]" />
		</fo:block>

		<fo:block margin-top="43pt" margin-bottom="43pt" >
			<xsl:apply-templates select="Ashtavarga[@Name='Mercury']/av:Chart[1]" />
		</fo:block>
		
		<!-- ASHTAVARGA TITLE -->
		<fo:table width="220pt" padding-top="35pt" table-layout="fixed">	<fo:table-body>
				<fo:table-row>
					<fo:table-cell>
						<fo:block font-weight="700" text-align="center"> 
							Ashtavarga
						</fo:block>
					</fo:table-cell>
				</fo:table-row>
		</fo:table-body></fo:table>

		<fo:block margin-top="15pt" margin-bottom="43pt" >
			<xsl:apply-templates select="Ashtavarga[@Name='Sun']/av:Chart[@Name='Trikona']" />
		</fo:block>
		
		<fo:block margin-top="43pt" margin-bottom="43pt" >
			<xsl:apply-templates select="Ashtavarga[@Name='Moon']/av:Chart[@Name='Trikona']" />
		</fo:block>

		<fo:block margin-top="43pt" margin-bottom="43pt" >
			<xsl:apply-templates select="Ashtavarga[@Name='Mars']/av:Chart[@Name='Trikona']" />
		</fo:block>

		<fo:block margin-top="43pt" margin-bottom="43pt" >
			<xsl:apply-templates select="Ashtavarga[@Name='Mercury']/av:Chart[@Name='Trikona']" />
		</fo:block>

		<fo:block margin-top="64pt" margin-bottom="43pt" >
			<xsl:apply-templates select="Ashtavarga[@Name='Sun']/av:Chart[@Name='Ekathipathya']" />
		</fo:block>
		
		<fo:block margin-top="43pt" margin-bottom="43pt" >
			<xsl:apply-templates select="Ashtavarga[@Name='Moon']/av:Chart[@Name='Ekathipathya']" />
		</fo:block>

		<fo:block margin-top="43pt" margin-bottom="43pt" >
			<xsl:apply-templates select="Ashtavarga[@Name='Mars']/av:Chart[@Name='Ekathipathya']" />
		</fo:block>

		<fo:block margin-top="43pt" margin-bottom="43pt" >
			<xsl:apply-templates select="Ashtavarga[@Name='Mercury']/av:Chart[@Name='Ekathipathya']" />
		</fo:block>

		<fo:block margin-top="64pt" margin-bottom="43pt" >
			<xsl:apply-templates select="Ashtavarga[@Name='Sun']/Gunaharas" />
		</fo:block>

		<fo:block margin-top="43pt" margin-bottom="43pt" >
			<xsl:apply-templates select="Ashtavarga[@Name='Moon']/Gunaharas" />
		</fo:block>

		<fo:block margin-top="43pt" margin-bottom="43pt" >
			<xsl:apply-templates select="Ashtavarga[@Name='Mars']/Gunaharas" />
		</fo:block>

		<fo:block margin-top="43pt" margin-bottom="43pt" >
			<xsl:apply-templates select="Ashtavarga[@Name='Mercury']/Gunaharas" />
		</fo:block>

		<fo:block margin-top="64pt" margin-bottom="43pt" >
			<xsl:apply-templates select="Ashtavarga[@Name='Jupiter']/av:Chart[1]" />
		</fo:block>
		
		<fo:block margin-top="43pt" margin-bottom="43pt" >
			<xsl:apply-templates select="Ashtavarga[@Name='Venus']/av:Chart[1]" />
		</fo:block>

		<fo:block margin-top="43pt" margin-bottom="43pt" >
			<xsl:apply-templates select="Ashtavarga[@Name='Saturn']/av:Chart[1]" />
		</fo:block>

		<fo:block margin-top="43pt" margin-bottom="43pt" >
			<xsl:apply-templates select="Ashtavarga[@Name='SarvaAshtavarga']/av:Chart[1]" />
		</fo:block>

		<fo:block margin-top="64pt" margin-bottom="43pt" >
			<xsl:apply-templates select="Ashtavarga[@Name='Jupiter']/av:Chart[@Name='Trikona']" />
		</fo:block>
		
		<fo:block margin-top="43pt" margin-bottom="43pt" >
			<xsl:apply-templates select="Ashtavarga[@Name='Venus']/av:Chart[@Name='Trikona']" />
		</fo:block>

		<fo:block margin-top="43pt" margin-bottom="43pt" >
			<xsl:apply-templates select="Ashtavarga[@Name='Saturn']/av:Chart[@Name='Trikona']" />
		</fo:block>

		<fo:block margin-top="43pt" margin-bottom="43pt" >
			<xsl:apply-templates select="Ashtavarga[@Name='SarvaAshtavarga']/av:Chart[@Name='Trikona']" />
		</fo:block>

		<fo:block margin-top="64pt" margin-bottom="43pt" >
			<xsl:apply-templates select="Ashtavarga[@Name='Jupiter']/av:Chart[@Name='Ekathipathya']" />
		</fo:block>
		
		<fo:block margin-top="43pt" margin-bottom="43pt" >
			<xsl:apply-templates select="Ashtavarga[@Name='Venus']/av:Chart[@Name='Ekathipathya']" />
		</fo:block>

		<fo:block margin-top="43pt" margin-bottom="43pt" >
			<xsl:apply-templates select="Ashtavarga[@Name='Saturn']/av:Chart[@Name='Ekathipathya']" />
		</fo:block>

		<fo:block margin-top="43pt" margin-bottom="43pt" >
			<xsl:apply-templates select="Ashtavarga[@Name='SarvaAshtavarga']/av:Chart[@Name='Ekathipathya']" />
		</fo:block>

		<fo:block margin-top="64pt" margin-bottom="43pt" >
			<xsl:apply-templates select="Ashtavarga[@Name='Jupiter']/Gunaharas" />
		</fo:block>

		<fo:block margin-top="43pt" margin-bottom="43pt" >
			<xsl:apply-templates select="Ashtavarga[@Name='Venus']/Gunaharas" />
		</fo:block>

		<fo:block margin-top="43pt" margin-bottom="43pt" >
			<xsl:apply-templates select="Ashtavarga[@Name='Saturn']/Gunaharas" />
		</fo:block>

		<fo:block margin-top="43pt" margin-bottom="43pt" >
			<xsl:apply-templates select="Ashtavarga[@Name='SarvaAshtavarga']/Gunaharas" />
		</fo:block>

	</xsl:template>

	<xsl:template match="Gunaharas" >

		<fo:table width="135pt" padding-top="30pt" padding-bottom="27pt" table-layout="fixed"> <fo:table-body>
		
			<xsl:for-each select="Gunahara" >

				<fo:table-row height="20pt">

					<fo:table-cell width="90pt" >

						<fo:block   font-size="10pt" font-family="Tahoma">
							<xsl:value-of select="@Name" />
						</fo:block>

					</fo:table-cell>

					<fo:table-cell width="43pt">

						<fo:block   font-size="10pt" font-family="Tahoma">
							<xsl:value-of select="." />
						</fo:block>

					</fo:table-cell>

				</fo:table-row>
			</xsl:for-each>

		 </fo:table-body> </fo:table>

	</xsl:template>

	<xsl:template match="av:Chart" >

		<fo:table  width="120pt" border-separation="1pt" table-layout="fixed">

			<fo:table-body >

				<fo:table-row> 
					<xsl:apply-templates select="av:House[@No = 12]" />
					<xsl:apply-templates select="av:House[@No = 1]" />
					<xsl:apply-templates select="av:House[@No = 2]" />
					<xsl:apply-templates select="av:House[@No = 3]" />
				</fo:table-row>

				<fo:table-row> 
					<xsl:apply-templates select="av:House[@No = 11]" />

					<fo:table-cell number-columns-spanned="2" number-rows-spanned="2" height="43pt" width="60pt" > 
						<fo:block padding-top="25" text-align="center"  font-size="10pt" font-family="Tahoma" font-weight="700"> 
							<xsl:value-of select="@Name" /> 
						</fo:block> 
					</fo:table-cell>
					
					<xsl:apply-templates select="av:House[@No = 4]" />
				</fo:table-row>

				<fo:table-row> 
					<xsl:apply-templates select="av:House[@No = 10]" />
					
					<xsl:apply-templates select="av:House[@No = 5]" />
				</fo:table-row>

				<fo:table-row> 
					<xsl:apply-templates select="av:House[@No = 9]" />
					<xsl:apply-templates select="av:House[@No = 8]" />
					<xsl:apply-templates select="av:House[@No = 7]" />
					<xsl:apply-templates select="av:House[@No = 6]" />
				</fo:table-row>

		    </fo:table-body>
	    </fo:table>

	</xsl:template>

	<xsl:template match="av:House" >

		<fo:table-cell  border-width="0.3pt" border-style="solid" height="21pt" width="30pt" padding-top="7pt"  padding-left="13pt" >
			<fo:block  font-size="10pt" font-family="Tahoma" >
				<xsl:value-of select="." />
			</fo:block>
		</fo:table-cell>

	</xsl:template>

</xsl:stylesheet>