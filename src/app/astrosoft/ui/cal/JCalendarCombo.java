package app.astrosoft.ui.cal;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Observable;
import java.util.Observer;

import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.plaf.basic.BasicArrowButton;

import app.astrosoft.ui.util.UIUtil;


public class JCalendarCombo extends JPanel implements Observer {

    public static final int SUNDAY = 0;
    public static final int MONDAY = 1;
    private boolean condition;
    private JWindow window;
    private JCalendar calendar;
    private JTextField textField;
    private int startYear;
    private int endYear;

    public JCalendarCombo(  ) {

        condition = false;
        startYear = 1801;
        endYear = 2099;
        calendar = new JCalendar(  );

        initializeJCalendarCombo(  );

    }

    public JCalendarCombo( int firstDay, boolean showCurrentDate ) {

        condition = false;
        startYear = 1801;
        endYear = 2099;
        calendar = new JCalendar( firstDay, showCurrentDate );
        initializeJCalendarCombo(  );

    }

    public JCalendarCombo( 
        int firstDay, boolean showCurrentDate, int startYear, int endYear ) {

        condition = false;
        this.startYear = startYear;
        this.endYear = endYear;
        calendar =
            new JCalendar( firstDay, showCurrentDate, startYear, endYear );
        initializeJCalendarCombo(  );

    }

    private final void initializeJCalendarCombo(  ) {

        textField = new JTextField( 10 );
        textField.setEditable( false );
        textField.setBackground( new Color( 255, 255, 255 ) );

        javax.swing.JButton button =
            new BasicArrowButton( 
                5, new Color( 255, 255, 255 ), new Color( 0, 0, 0 ),
                new Color( 0, 0, 0 ), new Color( 0, 0, 0 ) );

        setLayout( new BorderLayout(  ) );
        add( textField, "Center" );
        add( button, "East" );
        setSelectedDate(  );
        button.addActionListener( 
            new ActionListener(  ) {

                public void actionPerformed( ActionEvent e ) {

                    if ( !condition ) {

                        condition = true;
                        window =
                            new JWindow( 
                                ( Window ) textField.getTopLevelAncestor(  ) );
                        window.getContentPane(  ).setLayout( 
                            new BorderLayout(  ) );

                        
                        //window.getContentPane().setBackground(new Color(0,0,0));
                        
                        calendar.initializeCalendar(  );
                        window.getContentPane(  ).add( calendar, "Center" );
                        window.pack(  );
                        
                        UIUtil.setWindowLocation(window, textField);

//                      TODO: Remove later since this is replaced by UIUtil.setWindowLocation();
                        
                        /*Point textFieldLocation =
                            textField.getLocationOnScreen(  );
                        Dimension size = textField.getSize(  );
                        Dimension windowSize = window.getSize(  );
                        Dimension screenSize =
                            Toolkit.getDefaultToolkit(  ).getScreenSize(  );

                        if ( 
                            ( ( textFieldLocation.x
                                - ( windowSize.width - size.width ) ) <= 0 )
                                && ( ( textFieldLocation.y + size.height
                                + windowSize.height ) >= screenSize.height ) ) {
                            window.setLocation( 
                                0, textFieldLocation.y - windowSize.height );
                        } else if ( 
                            ( textFieldLocation.x
                                - ( windowSize.width - size.width ) ) <= 0 ) {
                            window.setLocation( 
                                0, textFieldLocation.y + size.height );
                        } else if ( 
                            ( textFieldLocation.y + size.height
                                + windowSize.height ) >= screenSize.height ) {
                            window.setLocation( 
                                textFieldLocation.x
                                - ( windowSize.width - size.width ),
                                textFieldLocation.y - windowSize.height );
                        } else {
                            window.setLocation( 
                                textFieldLocation.x
                                - ( windowSize.width - size.width ),
                                textFieldLocation.y + size.height );
                        }*/

                        window.setVisible( true );

                    } else {

                        window.dispose(  );
                        setSelectedDate(  );
                        condition = false;

                    }

                }

            } );
        addAncestorListener( 
            new AncestorListener(  ) {

                public void ancestorAdded( AncestorEvent e ) {

                    if ( condition ) {

                        condition = false;
                        setSelectedDate(  );
                        window.dispose(  );

                    }

                }

                public void ancestorMoved( AncestorEvent e ) {

                    if ( condition ) {

                        condition = false;
                        setSelectedDate(  );
                        window.dispose(  );

                    }

                }

                public void ancestorRemoved( AncestorEvent e ) {

                    if ( condition ) {

                        condition = false;
                        setSelectedDate(  );
                        window.dispose(  );

                    }

                }

            } );
        calendar.buttonItemListener.addObserver( this );

    }

    private final void setSelectedDate(  ) {

        textField.setText( 
            calendar.getDay(  ) + "/" + calendar.getMonth(  ) + "/"
            + calendar.getYear(  ) );
        condition = false;

    }

    public final void setSelectedDate( int year, int month, int day ) {

        calendar.setDay( ( new Integer( day ) ).toString(  ) );
        calendar.setMonth( ( new Integer( month ) ).toString(  ) );
        calendar.setYear( ( new Integer( year ) ).toString(  ) );
        calendar.showCalendarForDate( year, month );
        textField.setText( 
            calendar.getDay(  ) + "/" + calendar.getMonth(  ) + "/"
            + calendar.getYear(  ) );
        condition = false;

    }

    public final String getSelectedDate(  ) {

        return textField.getText(  );

    }

    public final String getSelectedDay(  ) {

        return calendar.getDay(  );

    }

    public final String getSelectedMonth(  ) {

        return calendar.getMonth(  );

    }

    public final String getSelectedYear(  ) {

        return calendar.getYear(  );

    }

    public void update( Observable observable, Object object ) {

        window.dispose(  );
        setSelectedDate(  );

    }

}
