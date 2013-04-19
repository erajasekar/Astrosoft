/**
 * AstroConsts.java
 *
 * Created on October 01, 2005, 5:15 PM
 *
 * @author  E. Rajasekar
 */
package app.astrosoft.consts;

import swisseph.SweConst;

/** Represents all astro consts used in this application
 *
 */
public class AstroConsts {

    /*public static final int[] planetOwners =
        { 2, 5, 3, 1, 0, 3, 5, 2, 4, 6, 6, 4
         };*/
    
    // Corresponds to planet numbers in swiss eph.
    /*public static final int[] planetNo = { 0, 1, 4, 2, 5, 3, 6, 11, 12
         };*/

	public static final double nakLength = ( double ) ( 360.00 / 27.0 );
	
	public static final double padaLength = ( double ) ( 360.00 / 108.0 );
	
	public static final double thithiLength = 12.0;
	
	public static final double karanaLength = 6.0;
	
	public static final double yogaLength = nakLength;
	
	public static final double rasiLength = 30.0;
	
	public static final double MILLIS_IN_DAY = 24 * 60 * 60 * 1000;
	
	public static final long MILLIS_IN_HR = (1000 * 60 * 60);
	
	/*public static int[] maxKutas = { 3, 6, 1, 1, 4, 7, 5, 2, 1, 1, 1, 8
	 };

	public static int[] monthLength =
	{ 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
	 };*/

	/*public static final String[] months =
	    {
	        "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct",
	        "Nov", "Dec"
	    
	    };*/
	
	public static final int iflag = SweConst.SEFLG_SWIEPH | SweConst.SEFLG_SPEED  | SweConst.SEFLG_SIDEREAL ;
	
	public static int permanentRel(Planet i, Planet j){
		int[][] permRel =
        {
            { 2, 1, 1, 0, 1, -1, -1
             },
            { 1, 2, 0, 1, 0, 0, 0
             },
            { 1, 1, 2, -1, 1, 0, 0
             },
            { 1, -1, 0, 2, 0, 1, 0
             },
            { 1, 1, 1, -1, 2, -1, 0
             },
            { -1, -1, 0, 1, 0, 2, 1
             },
            { -1, -1, -1, 1, 0, 1, 2
             },
        
        };
		
		return permRel[i.ordinal()][j.ordinal()];
	}

	
}
