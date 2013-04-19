<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.1" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
xmlns:fo="http://www.w3.org/1999/XSL/Format" exclude-result-prefixes="fo">	

	<xsl:template name="page-layouts" >
		
		<!-- ******************* Define Page Layout *********************************** -->
			<fo:layout-master-set>
				<fo:simple-page-master master-name="SimplePage" page-height="29.7cm" page-width="21cm" margin-top="2cm" margin-bottom="2cm" margin-left="2cm" margin-right="2cm">

					<fo:region-body />
					<fo:region-before />
					<fo:region-after  />
					
				</fo:simple-page-master>
				
				<fo:simple-page-master master-name="LandscapePage" page-height="21cm" page-width="29.7cm" margin-top="2cm" margin-bottom="2cm" margin-left="1cm" margin-right="2cm">

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
	
	</xsl:template>

</xsl:stylesheet>
