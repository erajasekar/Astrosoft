package app.astrosoft.ui.cal;

import java.awt.*;

import javax.swing.*;

import app.astrosoft.ui.util.UIConsts;
import static javax.swing.BorderFactory.createEmptyBorder;
import static javax.swing.BorderFactory.createEtchedBorder;


public class JDayButton extends JToggleButton {

    JDayButton(  ) {

        setEnabled( false );
        //setBackground( new Color( 255, 255, 255 ) );
        setBackground(UIConsts.CAL_COMBO_BACKGROUND);
        //setBorder(BorderFactory.createLineBorder(UIConsts.THEME_CLR));
        setBorder(createEmptyBorder());
    }

    JDayButton( String text ) {
        super( text );
        setBackground( UIConsts.THEME_CLR );
        setBorder(createEtchedBorder());
        setFont( getFont(  ).deriveFont( 0, 9F ) );
    }

}
