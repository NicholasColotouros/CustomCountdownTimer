package cct.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import cct.model.CountdownTimer;
import cct.model.TimeInterval;
import cct.model.TimerModel;

@SuppressWarnings("serial")
public class TimerMainWindowUI extends JPanel implements java.util.Observer
{
	//TODO: Optional: find a way for the start button to take up the space 
	//of the pause button when not visible and vice versa
	private JLabel lTimeRemaining;
	private JLabel lNextReminder;

	private JButton startButton;
	private JButton pauseButton;
	private JButton stopButton;
	
	private static final ResourceBundle STRINGS = CustomCountdownTimerUI.getResourceBundle();
	
	private static final double TIMER_PANEL_HEIGHT_FACTOR = .5;
	private static final double CONTROL_PANEL_HEIGHT_FACTOR = 1 - TIMER_PANEL_HEIGHT_FACTOR;
	private static final int HORIZONTAL_COMPONENT_SPACE = 25;
	private static final int VERTICAL_COMPONENT_SPACE = 80;
	
	public TimerMainWindowUI(Dimension containerSize)
	{
		//TODO: remove this debug code
		try
		{
			TimeInterval duration = new TimeInterval(11,0);
			ArrayList<TimeInterval> reminders = new ArrayList<TimeInterval>();
			reminders.add(new TimeInterval(10,0));
			reminders.add(new TimeInterval(1,0));
			TimerModel TIMER_INSTANCE = TimerModel.getInstance();
			TIMER_INSTANCE.setTimer(new CountdownTimer(duration, reminders));
		}catch(Exception e){e.printStackTrace();}
		
		//END DEBUG
		
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
		//TODO: scale text and monospace it
		
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
		startButton = new JButton(STRINGS.getString("start"));
		pauseButton = new JButton(STRINGS.getString("pause"));
		pauseButton.setVisible(false);
		stopButton = new JButton(STRINGS.getString("stop"));
		
		//action listeners for start, pause and stop
		startButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent pEvent)
			{
				TimerModel.getInstance().start();
				//If the timer is NOT 00 -- it won't run if it is
				if(TimerModel.getInstance().isRunning())
				{
					startButton.setVisible(false);
					pauseButton.setVisible(true);
				}
			}
		});
		
		pauseButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent pEvent)
			{
				TimerModel.getInstance().pause();
				startButton.setVisible(true);
				pauseButton.setVisible(false);
			}
		});
		
		stopButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent pEvent)
			{
				TimerModel.getInstance().stopAndReset();
				startButton.setVisible(true);
				pauseButton.setVisible(false);
			}
		});

		buttonPanel.add(startButton);
		buttonPanel.add(pauseButton);
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
		String timeRemaining = TimerModel.getInstance().getTimeRemainingAsString();
		String nextReminder = TimerModel.getInstance().getNextAlertAsString();
		
		if(TimerModel.getInstance().isRunning() & timeRemaining.equals(nextReminder))
		{
			//TODO: trigger a better alert -- current one is for debug purposes
			Toolkit.getDefaultToolkit().beep();
		}
		//the text might have changed from making a new timer or resetting
		lTimeRemaining.setText(timeRemaining);
		
		if(TimerModel.getInstance().getTimeRemainingInSeconds() == 0)
		{
			pauseButton.setVisible(false);
			startButton.setVisible(true);
		}
		
		//If something changed, in almost every scenario the time remaining
		//has also changed. May as well update it each time
		lNextReminder.setText(TimerModel.getInstance().getNextAlertAsString());
		
		//TODO: if the timer is running and there is currently a start button, change it to pause
		//TODO: else if the timer is not running and there is currently a pause button, change it to start
	}
}