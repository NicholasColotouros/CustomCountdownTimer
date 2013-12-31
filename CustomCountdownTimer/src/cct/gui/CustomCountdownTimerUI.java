package cct.gui;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;

public class CustomCountdownTimerUI extends JFrame
{
	private static final String TITLE = "Countdown Timer";

	public CustomCountdownTimerUI()
	{
		super(TITLE);
		
		//Menu options
		final JMenuBar menuBar = new JMenuBar();
		
		final JMenu fileMenu = new JMenu("File");
		final JMenuItem newTimer = new JMenuItem("New Timer");
		final JMenuItem loadTimer = new JMenuItem("Load Timer");
		final JCheckBoxMenuItem autoLoadLastTimer = new JCheckBoxMenuItem("Load last timer on start");
		final JMenuItem exit = new JMenuItem("Exit");
		final JFileChooser newTimerFileChooser = new JFileChooser();
		final JFileChooser loadTimerFileChooser = new JFileChooser();
		
		final JMenu helpMenu = new JMenu("Help");
		final JMenuItem gettingStarted = new JMenuItem("Getting Started");
		final JMenuItem aboutMenu = new JMenuItem("About");

		//File Menu construction
		//TODO: implement
		fileMenu.add(newTimer);
		fileMenu.add(loadTimer);
		fileMenu.add(autoLoadLastTimer);
		fileMenu.add(exit);
		menuBar.add(fileMenu);
		
		//Help Menu construction
		//TODO: implement gettingstarted
		helpMenu.add(gettingStarted);
		helpMenu.add(aboutMenu);
		menuBar.add(helpMenu);
		
		setJMenuBar(menuBar);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public static void main(String[] pArgs) 
	{
		SwingUtilities.invokeLater(new Runnable() {
			@SuppressWarnings("unused")
			@Override
			public void run() 
			{
				new CustomCountdownTimerUI();				
			}
		});
	}	
}
