package cct.gui;

import java.awt.Dimension;
import java.text.NumberFormat;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class TimerSetupWindow extends JDialog 
{
	private static ResourceBundle STRINGS = CustomCountdownTimerUI.getResourceBundle();
	
	private static final int TEXT_FIELD_SIZE = 3;
	private static final int MAX_TEXT_FIELD_INPUT = 2;
	
	private JFormattedTextField secondsField = new JFormattedTextField();	
	private JFormattedTextField minutesField = new JFormattedTextField();
	
	private NumberFormat timeFormat;
	
	public TimerSetupWindow(Dimension aSize)
	{
		setTitle(STRINGS.getString("setup"));
		setModalityType(ModalityType.APPLICATION_MODAL);
		setSize(aSize);
		setPreferredSize(aSize);
		
		timeFormat = NumberFormat.getIntegerInstance();
		timeFormat.setParseIntegerOnly(true);
		timeFormat.setMaximumIntegerDigits(MAX_TEXT_FIELD_INPUT);
		
		//TODO: formatted text doesn't work
		
		int currentHeight = getSize().height;
		minutesField.setSize(new Dimension(50, currentHeight));
		minutesField.setPreferredSize(new Dimension(10, currentHeight));
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		
		//TODO: DurationPanel -> input how long
		JPanel durationPanel = new JPanel();
		durationPanel.setBorder(BorderFactory.createTitledBorder(STRINGS.getString("timer_settings")));
		
		JLabel instructionLabel = new JLabel(STRINGS.getString("timer_settings2"));
		JLabel minutes = new JLabel(STRINGS.getString("minutes"));
		JLabel seconds = new JLabel(STRINGS.getString("seconds"));
		minutes.setLabelFor(minutesField);
		seconds.setLabelFor(secondsField);
		
		durationPanel.add(instructionLabel);
		durationPanel.add(minutesField);
		durationPanel.add(secondsField);
		
		
		JPanel intervalPanel = new JPanel();
		intervalPanel.setBorder(BorderFactory.createTitledBorder(STRINGS.getString("interval_settings")));
		
		//TODO: bottom: interval setting
		//have it read from the model and have the current setting pre-loaded
		
		add(durationPanel);
		setVisible(true);
		
	}
}
