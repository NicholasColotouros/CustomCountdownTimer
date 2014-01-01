package cct.gui;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class ControlUI extends JPanel 
{
	public JPanel timeInputPanel;
	public JPanel timeIntervalPanel;
	
	public ControlUI()
	{		
		createTimeInputPanel();
		createTimeIntervalPanel();
	}
	
	private void createTimeInputPanel()
	{
		timeInputPanel = new JPanel();
		timeInputPanel.setBorder(BorderFactory.createTitledBorder(STRINGS.getString("timer")));

	}
	
	private void createTimeIntervalPanel()
	{
		timeIntervalPanel = new JPanel();		
		timeIntervalPanel.setBorder(BorderFactory.createTitledBorder(STRINGS.getString("reminders")));
	}
}
