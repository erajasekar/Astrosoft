package app.astrosoft.ui.cal;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

import app.astrosoft.ui.util.UIConsts;


public class JDayLabel extends JLabel {

    JDayLabel( String text ) {
        super( text, 0 );
        //setBackground( new java.awt.Color( 0, 0, 0 ) );
        //setBorder(UIConsts.getChartBorder());
        setFont( getFont(  ).deriveFont( 0, 9F ) );

    }

}
