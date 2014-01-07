package cct.gui;

import java.util.ResourceBundle;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class TimerMainWindowUI extends JPanel
{
	private static final ResourceBundle STRINGS = CustomCountdownTimerUI.getResourceBundle();
	
	private JLabel lTimeRemaining;
	private static final JLabel lTimeRemainingLabel = new JLabel(STRINGS.getString("remaining"));
	
	public TimerMainWindowUI()
	{
		//Displays the amount of time remaining
		JPanel displayPanel = new JPanel();
		
		
		//Contains start/pause/stop buttons
		JPanel controlPanel = new JPanel();
	}
}
