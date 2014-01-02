package cct.gui;

import java.awt.Dialog.ModalityType;
import java.text.NumberFormat;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class TimerSetupWindow 
{
	private static ResourceBundle STRINGS = CustomCountdownTimerUI.getResourceBundle();
	
	private JFormattedTextField secondsField = new JFormattedTextField();	
	private JFormattedTextField minutesField = new JFormattedTextField();
	
	private NumberFormat timeFormat;
	
	public TimerSetupWindow()
	{
		timeFormat = NumberFormat.getIntegerInstance();
		timeFormat.setParseIntegerOnly(true);
		
		JDialog mainSetupFrame = new JDialog();
		mainSetupFrame.setTitle(STRINGS.getString("setup"));
		mainSetupFrame.setModalityType(ModalityType.APPLICATION_MODAL);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		
		//TODO: top -> input how long
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
		
		mainSetupFrame.add(durationPanel);
		mainSetupFrame.setVisible(true);
		
	}
}
