package app.astrosoft.ui.cal;

import java.awt.*;
import java.util.Calendar;
import javax.swing.*;

import app.astrosoft.ui.util.UIConsts;

public final class JCalendar extends JPanel
{

    protected JCalendar()
    {

        days = new int[7][6];
        startYear = 1901;
        endYear = 2099;
        firstDay = 0;
        showCurrentDate = false;
        initializeVariables();
	    setBackground(new Color(255,255,255));
    }

    protected JCalendar(int firstDay, boolean showCurrentDate)
    {
        days = new int[7][6];
        startYear = 1901;
        endYear = 2099;
        this.firstDay = firstDay;
        this.showCurrentDate = showCurrentDate;
        initializeVariables();

    }

    protected JCalendar(int firstDay, boolean showCurrentDate, int startYear, int endYear)
    {
        days = new int[7][6];
        this.startYear = startYear;
        this.endYear = endYear;
        this.firstDay = firstDay;
        this.showCurrentDate = showCurrentDate;
        initializeVariables();
    }

    private void initializeVariables()
    {

	    buttonItemListener = new ButtonItemListener(this);
        comboBoxListener = new ComboBoxItemListener(this);
        calendar = Calendar.getInstance();
        showForYear = calendar.get(1);
        showForMonth = calendar.get(2);
        daySelected = (new Integer(calendar.get(5))).toString();
        monthSelected = (new Integer(calendar.get(2) + 1)).toString();
        yearSelected = (new Integer(calendar.get(1))).toString();
        datePanel = new JPanel();
        datePanel.setLayout(new BorderLayout());
        yearCombo = createYearCombo();
        yearCombo.setFont(yearCombo.getFont().deriveFont(1, 11F));
        monthCombo = createMonthCombo();
        monthCombo.setFont(monthCombo.getFont().deriveFont(1, 11F));
        datePanel.add(yearCombo, "East");
        datePanel.add(monthCombo, "Center");
        centrePanel = new JPanel();
        centrePanel.setBackground(new Color(255,255,255));
        centrePanel.setLayout(new BorderLayout(5, 5));
        centrePanel.add(datePanel, "North");
        if(showCurrentDate)
        {
            currentDateLabel = new JLabel("Today's Date : " + daySelected + "/" + monthSelected + "/" + yearSelected);
            currentDateLabel.setFont(currentDateLabel.getFont().deriveFont(1, 11F));
            currentDatePanel = new JPanel();
            currentDatePanel.setLayout(new FlowLayout(1));
            currentDatePanel.setBorder(BorderFactory.createEtchedBorder());
            currentDatePanel.add(currentDateLabel);
            centrePanel.add(currentDatePanel, "South");
        }
        add(centrePanel);
        yearCombo.addItemListener(comboBoxListener);
        monthCombo.addItemListener(comboBoxListener);
    }

    protected final void initializeCalendar()
    {
        monthCombo.removeItemListener(comboBoxListener);
        yearCombo.removeItemListener(comboBoxListener);
        if(showCalendarForDateSelected)
            monthCombo.setSelectedIndex(showForMonth - 1);
        else
            monthCombo.setSelectedIndex(showForMonth);
        yearCombo.setSelectedItem(new Integer(showForYear));
        if(showCalendarForDateSelected)
            days = createCalendar(showForYear, showForMonth - 1);
        else
            days = createCalendar(showForYear, showForMonth);
        if(daysPanel != null)
            centrePanel.remove(daysPanel);
        createDaysPanel();
        centrePanel.add(daysPanel, "Center");
        setBorder(BorderFactory.createLineBorder(new Color(0,0,0)));
        monthCombo.addItemListener(comboBoxListener);
        yearCombo.addItemListener(comboBoxListener);
    }

    private final int[][] createCalendar(int year, int month)
    {
        boolean calendarCompleted = false;
        days = new int[7][6];
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, 1);
        int number = calendar.get(7);
        int k = 1;
        for(int j = 0; j < 6; j++)
        {
            int i = 0;
            if(j == 0)
                if(firstDay == 0)
                {
                    i = number - 1;
                } else
                {
                    i = number - 2;
                    if(i < 0)
                        i = 6;
                }
            for(; i < 7; i++)
            {
                days[i][j] = k;
                if(month <= 6)
                {
                    if(month % 2 == 0)
                    {
                        if(k == 31)
                        {
                            calendarCompleted = true;
                            break;
                        }
                    } else
                    if(month == 1)
                    {
                        if(year % 4 == 0)
                        {
                            if(k == 29)
                            {
                                calendarCompleted = true;
                                break;
                            }
                        } else
                        if(k == 28)
                        {
                            calendarCompleted = true;
                            break;
                        }
                    } else
                    if(k == 30)
                    {
                        calendarCompleted = true;
                        break;
                    }
                } else
                if(month % 2 == 0)
                {
                    if(k == 30)
                    {
                        calendarCompleted = true;
                        break;
                    }
                } else
                if(k == 31)
                {
                    calendarCompleted = true;
                    break;
                }
                k++;
            }

            if(calendarCompleted)
                break;
        }

        return days;
    }

    private final JComboBox createYearCombo()
    {
        JComboBox combo = new JComboBox();
        for(int i = startYear; i <= endYear; i++)
            combo.addItem(new Integer(i));
		combo. setBackground(new Color(255,255,255));
        return combo;
    }

    private final JComboBox createMonthCombo()
    {
        JComboBox list = new JComboBox();
        list.addItem("January");
        list.addItem("February");
        list.addItem("March");
        list.addItem("April");
        list.addItem("May");
        list.addItem("June");
        list.addItem("July");
        list.addItem("August");
        list.addItem("September");
        list.addItem("October");
        list.addItem("November");
        list.addItem("December");
        list. setBackground(new Color(255,255,255));
        return list;
    }

    private final void createDaysPanel()
    {
        daysPanel = new JPanel();
        daysPanel.setBackground(UIConsts.CAL_COMBO_BACKGROUND);
        daysPanel.setBorder(BorderFactory.createEtchedBorder());
        
        daysPanel.setLayout(new GridLayout(7, 7));
        JDayLabel sunday = new JDayLabel(" Sun ");
        JDayLabel monday = new JDayLabel(" Mon ");
        JDayLabel tuesday = new JDayLabel(" Tue ");
        JDayLabel wednesday = new JDayLabel(" Wed ");
        JDayLabel thursday = new JDayLabel(" Thu ");
        JDayLabel friday = new JDayLabel(" Fri ");
        JDayLabel saturday = new JDayLabel(" Sat ");
        sunday.setForeground(Color.RED);
        if(firstDay == 0)
        {
            daysPanel.add(sunday);
            daysPanel.add(monday);
            daysPanel.add(tuesday);
            daysPanel.add(wednesday);
            daysPanel.add(thursday);
            daysPanel.add(friday);
            daysPanel.add(saturday);
        } else
        {
            daysPanel.add(monday);
            daysPanel.add(tuesday);
            daysPanel.add(wednesday);
            daysPanel.add(thursday);
            daysPanel.add(friday);
            daysPanel.add(saturday);
            daysPanel.add(sunday);
        }
        ButtonGroup buttonGroup = new ButtonGroup();
        for(int i = 0; i < 6; i++)
        {
            for(int k = 0; k < 7; k++)
                if(days[k][i] == 0)
                {
                    JDayButton dayButton = new JDayButton();
                    daysPanel.add(dayButton);
                } else
                {
                    JDayButton dayButton = new JDayButton((new Integer(days[k][i])).toString());
                    Integer selectedYear = (Integer)yearCombo.getSelectedItem();
                    //System.out.println(days[k][i] + "," + daySelected  + "," + monthCombo.getSelectedIndex()  + "," +  ((new Integer(monthSelected)).intValue() - 1)  + "," + selectedYear  + "," + yearSelected);
                    if(days[k][i] == (new Integer(daySelected)).intValue() && monthCombo.getSelectedIndex() == (new Integer(monthSelected)).intValue() - 1 && selectedYear.intValue() == (new Integer(yearSelected)).intValue()){
                    	dayButton.setForeground(Color.RED);
                    	dayButton.setSelected(true);
                    }
                        
                    dayButton.addItemListener(buttonItemListener);
                    buttonGroup.add(dayButton);
                    daysPanel.add(dayButton);
                }

        }

    }

    protected void showCalendarForDate(int year, int month)
    {
        showForMonth = month;
        showForYear = year;
        showCalendarForDateSelected = true;
    }

    protected void setDay(String daySelected)
    {
        this.daySelected = daySelected;
    }

    protected void setMonth(String monthSelected)
    {
        this.monthSelected = monthSelected;
    }

    protected void setYear(String yearSelected)
    {
        this.yearSelected = yearSelected;
    }

    protected String getDay()
    {
        return daySelected;
    }

    protected String getMonth()
    {
        return monthSelected;
    }

    protected String getYear()
    {
        return yearSelected;
    }

    private int days[][];
    private JPanel daysPanel;
    private JPanel datePanel;
    private JPanel currentDatePanel;
    private String daySelected;
    private String monthSelected;
    private String yearSelected;
    private JPanel centrePanel;
    public JComboBox yearCombo;
    public JComboBox monthCombo;
    private Calendar calendar;
    protected ButtonItemListener buttonItemListener;
    private int showForYear;
    private int showForMonth;
    private ComboBoxItemListener comboBoxListener;
    private boolean showCalendarForDateSelected;
    private boolean showCurrentDate;
    private int firstDay;
    private JLabel currentDateLabel;
    private int startYear;
    private int endYear;
}