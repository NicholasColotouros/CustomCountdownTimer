package cct.gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;

import cct.model.TimerModel;


@SuppressWarnings("serial")
public class TimerSetupWindow extends JDialog {
	private static ResourceBundle STRINGS = CustomCountdownTimerUI.getResourceBundle();
	
	private static final int TEXT_FIELD_SIZE = 3;
	private static final int MAX_TEXT_FIELD_INPUT = 2;
	
	private static final int SPACER_SIZE = 15;
	
	private JFormattedTextField secondsField;	
	private JFormattedTextField minutesField;
	
	private NumberFormat timeFormat;
	
	public TimerSetupWindow(Dimension aSize){
		setTitle(STRINGS.getString("setup"));
		setModalityType(ModalityType.APPLICATION_MODAL);
		setSize(aSize);
		setPreferredSize(aSize);
		
		timeFormat = NumberFormat.getIntegerInstance();
		timeFormat.setParseIntegerOnly(true);
		timeFormat.setMaximumIntegerDigits(MAX_TEXT_FIELD_INPUT);
		
		//TODO: formatted text doesn't work
		
		int currentHeight = getSize().height;
		minutesField = new JFormattedTextField(timeFormat);
		secondsField = new JFormattedTextField(timeFormat);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		
		//TODO: DurationPanel -> input how long
		JPanel durationPanel = new JPanel();
		durationPanel.setLayout(new BoxLayout(durationPanel, BoxLayout.PAGE_AXIS));
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
		
		
		//OK and CANCEL buttons that are at the bottom of window
		//TODO: fix sizes of the buttons
		JButton okButton = new JButton(STRINGS.getString("ok"));
		JButton cancelButton = new JButton(STRINGS.getString("cancel"));
		
		JPanel confirmationContainer = new JPanel();
		//TODO set preferred size
		confirmationContainer.add(okButton);
		confirmationContainer.add(javax.swing.Box.createVerticalStrut(SPACER_SIZE));
		confirmationContainer.add(cancelButton);
		
		//ActionListeners for the two buttons
		okButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent pEvent){
				//TODO: set the timer and close it
				//TimerModel.getInstance().setTimer(/*ARGUMENTS*/);
				
				setVisible(false);
			}
		});
		
		cancelButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent pEvent){
				setVisible(false);
			}
		});
		
		
		add(durationPanel);
//		add(confirmationContainer);
		setVisible(true);
		
		
	}
}
