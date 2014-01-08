package cct.gui;

import java.awt.Dimension;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class TimerMainWindowUI extends JPanel
{
	private static final ResourceBundle STRINGS = CustomCountdownTimerUI.getResourceBundle();
	
	private JLabel lTimeRemaining = new JLabel("00:00");
	
	public TimerMainWindowUI(Dimension containerSize)
	{
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		System.out.println(containerSize.height+" "+containerSize.width);
		setSize(containerSize);
		
		//Displays the amount of time remaining
		JPanel displayPanel = new JPanel();
		displayPanel.setPreferredSize(
				new Dimension
				(        //TODO: remove magic numbers (they're tests for spacing)
						(int)(Math.abs(containerSize.width -50)), //don't want a negative number 
						(int)(containerSize.height * 0.6)
				));
		displayPanel.setBorder(BorderFactory.createEtchedBorder());
		
		lTimeRemaining.setSize(displayPanel.getPreferredSize());//TODO: scale text and monospace it
		//TODO: support for longer than 99 minutes? ==> or just limit, design decision!
		//TODO: implement update feature (observer)		
		
		
		displayPanel.add(lTimeRemaining);
		
		
		//Contains start/pause/stop buttons
		JPanel controlPanel = new JPanel();
		controlPanel.setSize(
				new Dimension
				(
						(int)(Math.abs(containerSize.width -50)), 
						(int)(containerSize.height * 0.3)
				));
		
		//next reminder
		JPanel nextReminderPanel = new JPanel();
		nextReminderPanel.setBorder(BorderFactory.createTitledBorder(STRINGS.getString("next_reminder")));
		nextReminderPanel.setPreferredSize(
				new Dimension
				(
						(int)(controlPanel.getWidth()*.3),
						controlPanel.getHeight()
				));
		
		JLabel nextReminderLabel = new JLabel("00:00"); //TODO: scale text, monospace, support design decision from above, implement update
		nextReminderPanel.add(nextReminderLabel);
		controlPanel.add(nextReminderPanel);
		
		//TODO: fix sizes, add buttons
		
		//buttons
		
		//adding everything to the main panel
		add(displayPanel);
		add(controlPanel);
	}
}
