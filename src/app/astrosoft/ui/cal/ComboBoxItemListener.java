package app.astrosoft.ui.cal;


import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;


public class ComboBoxItemListener
    implements ItemListener
{

    ComboBoxItemListener(JCalendar calendar)
    {
        this.calendar = calendar;
    }

    public void itemStateChanged(ItemEvent e)
    {
        if(e.getStateChange() == 1)
        {
            int month = calendar.monthCombo.getSelectedIndex();
            /*String daySelected = calendar.getDay();
            String monthSelected = calendar.getMonth();
            String yearSelected = calendar.getYear();*/
            Integer year = (Integer)calendar.yearCombo.getSelectedItem();
            calendar.showCalendarForDate(year.intValue(), month + 1);
            
            /*calendar.setDay(daySelected);
            //System.out.println(monthSelected + " , " + month);
            //System.out.println(yearSelected + " , " + year);
            calendar.setMonth(String.valueOf(new Integer(monthSelected) + 2));
            calendar.setYear(yearSelected);*/
            calendar.initializeCalendar();
            calendar.updateUI();
        }
    }

    JCalendar calendar;
}