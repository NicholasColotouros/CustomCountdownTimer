package cct.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import cct.model.TimerModel;

@SuppressWarnings("serial")
public class TimerMainWindowUI extends JPanel implements java.util.Observer
{
	private JLabel lTimeRemaining; //FOR TESTING: USE METHODS
	private JLabel lNextReminder;

	
	private static final ResourceBundle STRINGS = CustomCountdownTimerUI.getResourceBundle();
	
	private static final double TIMER_PANEL_HEIGHT_FACTOR = .5;
	private static final double CONTROL_PANEL_HEIGHT_FACTOR = 1 - TIMER_PANEL_HEIGHT_FACTOR;
	private static final int HORIZONTAL_COMPONENT_SPACE = 25;
	private static final int VERTICAL_COMPONENT_SPACE = 80;
	
	public TimerMainWindowUI(Dimension containerSize)
	{
		TimerModel.getInstance().addObserver(this);
		
		int width = Math.abs(containerSize.width - HORIZONTAL_COMPONENT_SPACE);
		int timerHeight = Math.abs((int)(containerSize.height * TIMER_PANEL_HEIGHT_FACTOR));
		int controlHeight = Math.abs((int)(containerSize.height * CONTROL_PANEL_HEIGHT_FACTOR 
				- VERTICAL_COMPONENT_SPACE));
		
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
		//TODO: implement update feature (observer)		
		
		lTimeRemaining = new JLabel(TimerModel.getInstance().getTimeRemainingAsString());
		displayPanel.add(lTimeRemaining);
		
		
		//Contains start/pause/stop buttons
		//TODO: control panel sizes don't look so great - not uniform
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
		
		lNextReminder = new JLabel(TimerModel.getInstance().getNextAlertAsString()); 
		//TODO: scale text, monospace, support design decision from above, implement update
		nextReminderPanel.add(lNextReminder);
		
		//buttons
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(3,3));
		buttonPanel.setSize(new Dimension
				(
						controlPanel.getWidth() - (nextReminderPanel.getPreferredSize().width + HORIZONTAL_COMPONENT_SPACE),
						controlPanel.getPreferredSize().height
				));
		//TODO: consider using bag layout so that start button is larger?
		//TODO: turn start into pause button
		JButton startButton = new JButton(STRINGS.getString("start"));
		JButton stopButton = new JButton(STRINGS.getString("stop"));
		
		//action listeners for start and stop
		startButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent pEvent)
			{
				TimerModel.getInstance().start();
			}
		});
		
		stopButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent pEvent)
			{
				TimerModel.getInstance().stopAndReset();
			}
		});

		buttonPanel.add(startButton);
		buttonPanel.add(stopButton);
		
		controlPanel.add(nextReminderPanel);
		controlPanel.add(buttonPanel);
		
		//adding everything to the main panel
		add(displayPanel);
		add(controlPanel);
	}

	@Override
	public void update(Observable o, Object arg) 
	{
		String nextReminder = TimerModel.getInstance().getTimeRemainingAsString();
		if(TimerModel.getInstance().isRunning() && !nextReminder.equals(lTimeRemaining.getText()))
		{
			//TODO: trigger a better alert -- current one is for debug purposes
			Toolkit.getDefaultToolkit().beep();
		}
		//the text might have changed from making a new timer or resetting
		lTimeRemaining.setText(nextReminder);
		
		//If something changed, in almost every scenario the time remaining
		//has also changed. May as well update it each time
		lNextReminder.setText(TimerModel.getInstance().getNextAlertAsString());
		
		//TODO: if the timer is running and there is currently a start button, change it to pause
		//TODO: else if the timer is not running and there is currently a pause button, change it to start
	}
}