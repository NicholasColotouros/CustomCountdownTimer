package cct.gui;

import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TimerMainWindowUI extends JPanel
{
	private static final ResourceBundle STRINGS = CustomCountdownTimerUI.getResourceBundle();
	
	private JLabel lTimeRemaining = new JLabel("00:00");
	
	public TimerMainWindowUI()
	{
		//Displays the amount of time remaining
		JPanel displayPanel = new JPanel();
		displayPanel.setBorder(BorderFactory.createEtchedBorder());
		
		lTimeRemaining.setSize(displayPanel.getMinimumSize());
		displayPanel.add(lTimeRemaining);
		
		
		//Contains start/pause/stop buttons
		JPanel controlPanel = new JPanel();
		add(displayPanel);
		add(controlPanel);
	}
}
