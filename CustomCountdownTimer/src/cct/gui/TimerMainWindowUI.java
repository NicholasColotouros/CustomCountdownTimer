package cct.gui;

import java.awt.Dimension;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class TimerMainWindowUI extends JPanel
{
	private static final ResourceBundle STRINGS = CustomCountdownTimerUI.getResourceBundle();
	
	private static final double TIMER_PANEL_HEIGHT_FACTOR = .5;
	private static final double CONTROL_PANEL_HEIGHT_FACTOR = 1 - TIMER_PANEL_HEIGHT_FACTOR;
	private static final int HORIZONTAL_COMPONENT_SPACE = 25;
	private static final int VERTICAL_COMPONENT_SPACE = 80;
	
	private JLabel lTimeRemaining = new JLabel("00:00"); //FOR TESTING: USE METHODS
	
	public TimerMainWindowUI(Dimension containerSize)
	{
		int width = Math.abs(containerSize.width - HORIZONTAL_COMPONENT_SPACE);
		int timerHeight = Math.abs((int)(containerSize.height * TIMER_PANEL_HEIGHT_FACTOR));
		int controlHeight = Math.abs((int)(containerSize.height * CONTROL_PANEL_HEIGHT_FACTOR - VERTICAL_COMPONENT_SPACE));
		System.out.println(timerHeight+" "+controlHeight);
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));		
		setPreferredSize(new Dimension(width, containerSize.height));
		setSize(getPreferredSize());
		setMaximumSize(getPreferredSize());
		
		//Displays the amount of time remaining
		JPanel displayPanel = new JPanel();
		displayPanel.setPreferredSize(new Dimension(width, timerHeight));
		displayPanel.setMaximumSize(displayPanel.getPreferredSize());
		displayPanel.setBorder(BorderFactory.createEtchedBorder());
		//lTimeRemaining.setPreferredSize(displayPanel.getPreferredSize());
		//TODO: scale text and monospace it
		//TODO: support for longer than 99 minutes? ==> or just limit, design decision!
		//TODO: implement update feature (observer)		
		
		displayPanel.add(lTimeRemaining);
		
		
		//Contains start/pause/stop buttons
		JPanel controlPanel = new JPanel();
		controlPanel.setSize(
				new Dimension
				(
						(int)(Math.abs(containerSize.width -50)), 
						(int)(containerSize.height * 0.5)
				));
		
		//next reminder
		JPanel nextReminderPanel = new JPanel();
		nextReminderPanel.setBorder(BorderFactory.createTitledBorder(STRINGS.getString("next_reminder")));
		nextReminderPanel.setPreferredSize(
				new Dimension((int)(width*.3), controlHeight));
		nextReminderPanel.setMaximumSize(nextReminderPanel.getPreferredSize());
		
		JLabel nextReminderLabel = new JLabel("00:00"); 
		//TODO: scale text, monospace, support design decision from above, implement update
		nextReminderPanel.add(nextReminderLabel);
		
		//buttons
		JPanel buttonPanel = new JPanel();
		JButton startButton = new JButton(STRINGS.getString("start"));
		JButton stopButton = new JButton();
		
		controlPanel.add(nextReminderPanel);
		controlPanel.add(startButton);
		//TODO: fix sizes, add buttons
		
		//buttons
		
		//adding everything to the main panel
		add(displayPanel);
		add(controlPanel);
	}
}
