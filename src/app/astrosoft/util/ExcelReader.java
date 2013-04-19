/**
 * ExcelReader.java
 *
 * Created on Saturday, July 30, 2005 1:39 PM
 * @author  E. Rajasekar
 */
package app.astrosoft.util;

import java.io.*;

import java.sql.*;

import java.text.DecimalFormat;


public class ExcelReader {

    static final String[] states =
        {
            "Andaman", "Andhra Pradesh", "Arunachal Pradesh", "Assam", "Bihar",
            "Chandigarh", "Dadra", "Daman", "Delhi", "Goa", "Gujarat", "Haryana",
            "Himachal Pradesh", "Jammu and Kashmir", "Karnataka", "Kerala",
            "Lakshadweep", "Madhya Pradesh", "Maharashtra", "Manipur",
            "Meghalaya", "Mizoram", "Nagaland", "Orissa", "Pondicherry",
            "Punjab", "Rajasthan", "Sikkim", "Tamil Nadu", "Tripura",
            "Uttar Pradesh", "West Bengal"
        
        };
    Connection con = null;
    Statement st = null;
    ResultSet rs = null;
    PrintStream lats = null;
    FileOutputStream fos = null;

    public ExcelReader(  ) throws Exception {

        File f = new File( "LatitudeLongitudes.xml" );

        fos = new FileOutputStream( f );
        lats = new PrintStream( fos );

    }

    private void writeSTag( String tag ) {

        lats.println( "<" + tag + ">" );

    }
    
    private void writeSTag( 
            String tag, String attr, String attrval ) {

            lats.print( "<" + tag + " " + attr + "=\"" + attrval + "\" >" );
        }


    private void writeETag( String tag ) {

        lats.println( "</" + tag + ">" );

    }

    private void writeTag( String tag, String val ) {

        lats.println( "<" + tag + ">" + val + "</" + tag + ">" );

    }

    private void writeTag( 
        String tag, String tagval, String attr, String attrval ) {

        lats.print( "<" + tag + " " + attr + "=\"" + attrval + "\" >" );
        lats.print( tagval );
        lats.println( "</" + tag + ">" );

    }

    public static void main( String[] args ) {

        try {

            ExcelReader xr = new ExcelReader(  );

            xr.writeSTag( "world" );
            /*xr.writeSTag( "country" );
            xr.writeTag( "name", "india" );*/
            xr.writeSTag("country", "name", "india");
            xr.readAllStates(  );
            xr.writeETag( "country" );
            xr.writeETag( "world" );
            xr.lats.close(  );
            xr.fos.close(  );

        } catch ( Exception ex ) {

            ex.printStackTrace(  );

        }

    }

    private void readAllStates(  ) throws Exception {

        Class.forName( "sun.jdbc.odbc.JdbcOdbcDriver" );

        con = DriverManager.getConnection( "jdbc:odbc:readxl" );
        st = con.createStatement(  );

        for ( int s = 0; s < states.length; s++ ) {

            /*writeSTag( "state" );
            writeTag( "name", states[s] );*/
        	writeSTag("state", "name", states[s]);
            readState( states[s] );
            writeETag( "state" );

        }

        st.close(  );
        con.close(  );

    }

    private void readState( String state ) throws Exception {

        DecimalFormat df = new DecimalFormat( "00.00" );

        ResultSet rs = st.executeQuery( "Select * from [" + state + "$] " );

        while ( rs.next(  ) ) {

            /*writeSTag( "city" );
            writeTag( "name", rs.getString( 1 ) );*/
        	writeSTag("city", "name", rs.getString( 1 ));

            String str = rs.getString( 2 );
            Double d = Double.parseDouble( str.substring( 0, str.indexOf("N") ) );

            writeTag( "latitude", df.format( d ), "dir", "N" );
            str = rs.getString( 3 );
            d = Double.parseDouble( str.substring( 0, str.indexOf("E") ) );
            writeTag( "longitude", df.format( d ), "dir", "E" );

            writeETag( "city" );

        }

    }

}
