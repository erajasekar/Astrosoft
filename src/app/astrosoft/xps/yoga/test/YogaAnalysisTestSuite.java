/**
 * YogaAnalysisTestSuite.java
 * Created On 2007, Oct 31, 2007 1:35:16 PM
 * @author E. Rajasekar
 */

package app.astrosoft.xps.yoga.test;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import junit.framework.Test;
import junit.framework.TestSuite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        /*AdhiYogaTestCase.class,
        AmalaYogaTestCase.class,
        AnaphaYogaTestCase.class,
        BudhaAdiyaYogaTestCase.class,
        ChandraManglaYogaTestCase.class,
        ChatussagaraYogaTestCase.class,
        DhurdhuraYogaTestCase.class,
        GajakesariYogaTestCase.class,
        KemadrumaYogaTestCase.class,
        RajalakshanaYogaTestCase.class,
        SunaphaYogaTestCase.class,
        VasumathiYogaTestCase.class,
		SakataYogaTestCase.class,
		VesiYogaTestCase.class,
		VasiYogaTestCase.class,
		ObhayachariYogaTestCase.class,
		PanchamahapurushaYogaTestCase.class,
		KusumaYogaTestCase.class,
		JayaYogaTestCase.class,
		SivaYogaTestCase.class, 
		ChapaYogaTestCase.class , 
		KahalaYogaTestCase.class, 
		MahabhagyaYogaTestCase.class,
		SankhaYogaTestCase.class,
		BheriYogaTestCase.class,
		LakshmiYogaTestCase.class, 
		SreenathaYogaTestCase.class, 
		GajaYogaTestCase.class, 
		AmsavataraYogaTestCase.class, 
		DevendraYogaTestCase.class,
		MakutaYogaTestCase.class, 
		VidyutYogaTestCase.class 
		IndraYogaTestCase.class 
		RaviYogaTestCase.class 
		GoYogaTestCase.class */
		ThrilochanaYogaTestCase.class
		
        })
public class YogaAnalysisTestSuite {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		YogaAnalysisTestHelper.setUpBeforeClass();
	}
	
}
