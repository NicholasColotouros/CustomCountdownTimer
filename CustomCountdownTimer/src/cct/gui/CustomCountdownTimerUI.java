package cct.gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.BoxLayout;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class CustomCountdownTimerUI extends JFrame
{
	public static final String RESOURCE_BUNDLE_NAME = "CCT_EN_CA";
	public static final Locale LOCALE = Locale.CANADA;
	
	public static final ResourceBundle STRINGS = ResourceBundle.getBundle(RESOURCE_BUNDLE_NAME, LOCALE);

	private static final String TITLE = "Countdown Timer";
	private static final double FRAME_HEIGHT_FACTOR = 0.4;
	private static final double FRAME_WIDTH_FACTOR = 0.2;

	public CustomCountdownTimerUI()
	{
		super(TITLE);
		
		//Menu options
		final JMenuBar menuBar = new JMenuBar();
		
		final JMenu fileMenu = new JMenu(STRINGS.getString("file"));
		final JMenuItem newTimer = new JMenuItem(STRINGS.getString("new_timer"));
		final JMenuItem loadTimer = new JMenuItem(STRINGS.getString("load_timer"));
		final JCheckBoxMenuItem autoLoadLastTimer = new JCheckBoxMenuItem(STRINGS.getString("load_on_start"));
		final JMenuItem exit = new JMenuItem(STRINGS.getString("exit"));
		final JFileChooser newTimerFileChooser = new JFileChooser();
		final JFileChooser loadTimerFileChooser = new JFileChooser();
		
		final JMenu helpMenu = new JMenu(STRINGS.getString("help"));
		final JMenuItem gettingStarted = new JMenuItem(STRINGS.getString("getting_started"));
		final JMenuItem aboutMenu = new JMenuItem(STRINGS.getString("about"));

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
		
		aboutMenu.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent pEvent)
			{
				JOptionPane.showMessageDialog(CustomCountdownTimerUI.this, "Made by Nicholas Nathan Colotouros.\nThis version of Custom Countdown Timer is still under development");
			}
		});
		
		setJMenuBar(menuBar);
		
		Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		setSize(
				(int) Math.round(screenSize.width * FRAME_WIDTH_FACTOR), 
				(int) Math.round(screenSize.height * FRAME_HEIGHT_FACTOR)
				);
		
		
		add(buildUI());
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	private JPanel buildUI()
	{
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		return mainPanel;
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
