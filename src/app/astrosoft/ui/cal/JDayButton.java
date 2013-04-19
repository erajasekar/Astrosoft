package app.astrosoft.ui.cal;

import java.awt.*;

import javax.swing.*;

import app.astrosoft.ui.util.UIConsts;


public class JDayButton extends JToggleButton {

    JDayButton(  ) {

        setEnabled( false );
        //setBackground( new Color( 255, 255, 255 ) );
        setBackground(UIConsts.CAL_COMBO_BACKGROUND);
        //setBorder(BorderFactory.createLineBorder(UIConsts.THEME_CLR));
        setBorder(BorderFactory.createEmptyBorder());
    }

    JDayButton( String text ) {
        super( text );
        setBackground( UIConsts.THEME_CLR );
        setBorder(BorderFactory.createEtchedBorder());
        setFont( getFont(  ).deriveFont( 0, 9F ) );
    }

}
