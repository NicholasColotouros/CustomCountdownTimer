package cct.gui;

import java.util.ResourceBundle;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class TimerSetupWindow 
{
	public static ResourceBundle STRINGS = CustomCountdownTimerUI.getResourceBundle();
	public TimerSetupWindow()
	{
		JFrame mainSetupFrame = new JFrame(STRINGS.getString("setup"));
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		
		//TODO: top -> input how long
		//TODO: bottom: interval setting
		//have it read from the model and have the current setting pre-loaded
		
		mainSetupFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainSetupFrame.setVisible(true);

	}
}
