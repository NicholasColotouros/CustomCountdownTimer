package cct.gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.BoxLayout;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JDialog;
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
	
	private static CustomCountdownTimerUI MAIN_WINDOW;
	
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
		final JMenuItem saveTimer = new JMenuItem(STRINGS.getString("save_timer"));
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
		//exit implemented
		fileMenu.add(newTimer);
		fileMenu.add(saveTimer);
		fileMenu.add(loadTimer);
		fileMenu.add(autoLoadLastTimer);
		fileMenu.add(exit);
		menuBar.add(fileMenu);
		
		//Help Menu construction
		//TODO: implement gettingstarted
		helpMenu.add(gettingStarted);
		helpMenu.add(aboutMenu);
		menuBar.add(helpMenu);
		
		newTimer.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent pEvent)
			{
				new TimerSetupWindow();
				//TODO: have timer refresh afterwards
			}
		});
		
		exit.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent pEvent)
			{
				MAIN_WINDOW.dispatchEvent(new WindowEvent(MAIN_WINDOW, WindowEvent.WINDOW_CLOSING));
			}
		});
		
		aboutMenu.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent pEvent)
			{
				JOptionPane.showMessageDialog(CustomCountdownTimerUI.this, STRINGS.getString("about_text"));
			}
		});
		
		//TODO: add window listener that asks if user is sure about closing
		
		setJMenuBar(menuBar);
		
		Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		setSize(
					(int) Math.round(screenSize.width * FRAME_WIDTH_FACTOR), 
					(int) Math.round(screenSize.height * FRAME_HEIGHT_FACTOR)
				);
		
		add(buildUI(screenSize));
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter()
		{
			//TODO: FIX. Works on clicking x, but does not interrupt shutdown. Does not trigger when going through file menu to exit
			@Override 
			public void windowClosing(WindowEvent e)
			{
				Object[] options = { 
						STRINGS.getString("yes"),
						STRINGS.getString("no"),
						STRINGS.getString("cancel")
				};
								
				JOptionPane savePane = new JOptionPane();
				
				int n = JOptionPane.showOptionDialog(savePane, 
						STRINGS.getString("close_dialog"), 
						TITLE, 
						JOptionPane.YES_NO_CANCEL_OPTION, 
						JOptionPane.QUESTION_MESSAGE, 
						null, 
						options, 
						options[2]);
				
				if(n == 0) //yes is selected
				{
					//TODO: bring up save, then close program
				}
				
				//no is selected
				if(n == 1) System.exit(0);
				
				//cancel is selected
				else savePane.setVisible(false);
			}
		});
		setVisible(true);
	}
	
	private JPanel buildUI(Dimension screenSize)
	{
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		
		Dimension mainTimerSize = new Dimension
				(
						(int)(screenSize.getWidth() * FRAME_WIDTH_FACTOR),
						(int)(screenSize.getHeight()* FRAME_HEIGHT_FACTOR)
				);
		mainPanel.add(new TimerMainWindowUI(mainTimerSize));
		return mainPanel;
	}
	
	public static ResourceBundle getResourceBundle()
	{
		return STRINGS;
	}
	
	public static void main(String[] pArgs) 
	{
		SwingUtilities.invokeLater(new Runnable() {
			@SuppressWarnings("unused")
			@Override
			public void run() 
			{
				MAIN_WINDOW = new CustomCountdownTimerUI();				
			}
		});
	}	
}
