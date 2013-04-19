package app.astrosoft.ui.cal;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class CalendarTest extends JFrame
{
	CalendarTest()
	{
		JPanel panel1 = new JPanel();
		JLabel label1 = new JLabel("Name : ");
		JLabel label2 = new JLabel("Birth Date : ");
		JLabel label3 = new JLabel("Sun Sign : ");
		JTextField name = new JTextField();
		final JCalendarCombo jCalendarCombo = new JCalendarCombo();
		jCalendarCombo.setSelectedDate(2003,3,31);
		JTextField sign = new JTextField();
		JButton okay = new JButton("OK");
		JButton cancel = new JButton("Cancel");
		panel1.setLayout(new GridLayout(4,2,5,5));
		getContentPane().setLayout(new GridLayout(1,1));
		panel1.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		panel1.add(label1);
		panel1.add(name);
		panel1.add(label2);
		panel1.add(jCalendarCombo);
		panel1.add(label3);
		panel1.add(sign);
		panel1.add(okay);
		panel1.add(cancel);
		getContentPane().add(panel1);
		pack();
		setTitle("JCalendarCombo Demo");
		setVisible(true);

		okay.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.out.println(jCalendarCombo.getSelectedDate());
				System.out.println(jCalendarCombo.getSelectedDay());
				System.out.println(jCalendarCombo.getSelectedMonth());
				System.out.println(jCalendarCombo.getSelectedYear());
			}

		});

		cancel.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
		});

		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});

	}
	public static void main(String args[]) throws Exception
	{
		//UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		//UIManager.setLookAndFeel(new com.incors.plaf.kunststoff.KunststoffLookAndFeel());
		CalendarTest test = new CalendarTest();
	}
}
