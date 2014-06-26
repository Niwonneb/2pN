package de.htwg.se.tpn.view;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.google.inject.Inject;

import de.htwg.se.tpn.controller.TpnControllerInterface;
import de.htwg.se.tpn.util.observer.Event;
import de.htwg.se.tpn.util.observer.IObserver;

public class GUI extends JFrame implements IObserver, KeyListener{
	
	/**
	 * 
	 */
	private static final int WINDOWSIZE = 600;
	private static final int INNERGAP = 10;
	private static final int OUTERGAP = 30;
	private static final long serialVersionUID = 1L;
	private static final int STDSIZE = 4;
	private static final int HIGSIZE = 8;
	private static final int VHISIZE = 11;
	private static final int LOWSIZEFONT = 50;
	private static final int STDSIZEFONT = 31;
	private static final int HIGSIZEFONT = 26;
	private static final int VHISIZEFONT = 16;
	private static final int ELSSIZEFONT = 10;
	
	private static final int FRAMECOLOR = 0xBCB39F;
	
	private static final int FORE1 = 0x000000;
	private static final int FORE2 = 0x716B60;
	private static final int FORE3 = 0xFFFFFF;
	
	private static final int BACK0 = 0xCAC5BA;
	private static final int BACK2 = 0xE8E6D8;
	private static final int BACK4 = 0xE5D8BD;
	private static final int BACK8 = 0xF0AF66;
	private static final int BACK16 = 0xF2965A;
	private static final int BACK32 = 0xF57A5B;
	private static final int BACK64 = 0xF04D24;
	private static final int BACK128 = 0xE9D37C;
	private static final int BACK256 = 0xF2D76E;
	private static final int BACK512 = 0xE5CB43;
	private static final int BACK1024 = 0xF4E43E;
	private static final int BACK2048 = 0xE9F028;
	private static final int BACKREST = 0x6FD8C0;
	
	@SuppressWarnings("serial")
	private static final Map<Integer,Integer> FORECOLORS =
											new TreeMap<Integer, Integer>() {{
		put(0, FORE1);
		put(2, FORE2);
		put(4, FORE2);
		put(8, FORE3);
		put(16, FORE3);
		put(32, FORE3);
		put(64, FORE3);
		put(128, FORE3);
		put(265, FORE3);
		put(512, FORE3);
		put(1024, FORE3);
		put(2048, FORE3);
		put(-1, FORE1);
	}};
	@SuppressWarnings("serial")
	private static final Map<Integer,Integer> BACKCOLORS = 
											new TreeMap<Integer, Integer>() {{
		put(0, BACK0);
		put(2, BACK2);
		put(4, BACK4);
		put(8, BACK8);
		put(16, BACK16);
		put(32, BACK32);
		put(64, BACK64);
		put(128, BACK128);
		put(265, BACK256);
		put(512, BACK512);
		put(1024, BACK1024);
		put(2048, BACK2048);
		put(-1, BACKREST);
	}};
	private TpnControllerInterface controller;
	private JLabel[][] tileLabels;
	private JPanel tilePanel;
	private boolean end;

	@Inject
	public GUI(TpnControllerInterface controller) {
		this.controller = controller;
		tileLabels = new JLabel[controller.getSize()][controller.getSize()];
		controller.addObserver(this);
		this.addKeyListener(this);
		end = false;
		
		this.setTitle("Two Power N");
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
			
		JMenuBar jmb = new JMenuBar();
		this.setJMenuBar(jmb);
		JMenu menuFile = new JMenu ("Game");
		jmb.add(menuFile);
		
		JMenuItem newGame = new JMenuItem("New Game");
		newGame.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
					handle();
			}
		});
		menuFile.add(newGame);
		
		JMenuItem exitItem = new JMenuItem("Exit...");
		exitItem.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		menuFile.add(exitItem);
	
		createTiles();
		this.add(this.tilePanel);
        
		this.pack();
		this.setSize(WINDOWSIZE, WINDOWSIZE);
		this.setVisible(true);
	}

	private void handle() {

		JTextField fieldsize = new JTextField();
		JTextField inputs = new JTextField();
		fieldsize.setText("4");
		inputs.setText("1");
		Object[] message = {
		    "Field size:", fieldsize,
		    "Number of new Tiles:", inputs
		};
		int option = JOptionPane.showConfirmDialog(
				GUI.this,
				message,
                "Create new Game",
                JOptionPane.OK_CANCEL_OPTION);
	
	
		if (option == JOptionPane.OK_OPTION) {
		    try {
		    	int size = Integer.valueOf(fieldsize.getText());
		    	int inserts = Integer.valueOf(inputs.getText());
			    GUI.this.controller.gameInit(size, inserts);
			    new GUI(GUI.this.controller);
			    GUI.this.controller.removeObserver(GUI.this);
			    GUI.this.dispose();
		    } catch (Exception err) {
		    	JOptionPane.showMessageDialog(GUI.this, "Wrong input",
		    			"Game creation failed", JOptionPane.WARNING_MESSAGE);
		    }
		}
	}
	
	private void createTiles() {
		tileLabels = new JLabel[controller.getSize()][controller.getSize()];
		GridLayout layout = new GridLayout(controller.getSize(),
				                           controller.getSize());
		layout.setHgap(INNERGAP);
		layout.setVgap(INNERGAP);
		this.tilePanel = new JPanel(layout);
		tilePanel.setBorder(new EmptyBorder(OUTERGAP, OUTERGAP,
				                            OUTERGAP, OUTERGAP));
		for (int i = 0; i < controller.getSize(); ++i) {
			for (int j = 0; j < controller.getSize(); ++j) {
				JLabel tileLabel = initLabel(new JLabel(""));
				tileLabels[i][j] = tileLabel;
				tilePanel.add(tileLabel);
			}
		}
		tilePanel.setOpaque(true);
		tilePanel.setBackground(new Color(FRAMECOLOR));
		updateValues();
	}
	
	private JLabel initLabel(JLabel label) {
		label.setBorder(BorderFactory.createRaisedBevelBorder());
		label.setOpaque(true);
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setVerticalAlignment(JLabel.CENTER);
		label.setVerticalTextPosition(JLabel.CENTER);
		label.setBackground(new Color(BACKCOLORS.get(0)));
		Font labelFont = label.getFont();
		label.setFont(new Font(labelFont.getName(),
				      Font.BOLD + Font.ROMAN_BASELINE, getfontsize()));
		
		return label;
	}
	
	private int getfontsize() {
		if (controller.getSize() < STDSIZE) {
			return LOWSIZEFONT;
		} else if (controller.getSize() == STDSIZE) {
			return STDSIZEFONT;
		} else if (controller.getSize() < HIGSIZE) {
			return HIGSIZEFONT;
		} else if (controller.getSize() < VHISIZE) {
			return VHISIZEFONT;
		}
		return ELSSIZEFONT;
	}
	
	private void updateValues() {
		for (int i = 0; i < controller.getSize(); ++i) {
			for (int j = 0; j < controller.getSize(); ++j) {
				int value = controller.getValue(i, j);
				String text = String.valueOf(value);
				if (value == 0) {
					text = "";
				}
				tileLabels[i][j].setText(text);
				JLabel label = tileLabels[i][j];
				if (BACKCOLORS.containsKey(value)) {
					label.setBackground(new Color(BACKCOLORS.get(value)));
					label.setForeground(new Color(FORECOLORS.get(value)));
				} else {
					label.setBackground(new Color(BACKCOLORS.get(-1)));
					label.setForeground(new Color(FORECOLORS.get(-1)));
				}
			}
		}
	}

	@Override
	public void update(Event e) {
		if (e instanceof TpnControllerInterface.NewFieldEvent) {
			updateValues();
		} else if (e instanceof TpnControllerInterface.GameOverEvent && !end) {
			end = true;
			JOptionPane.showMessageDialog(this, "Game over");
		} else if (e instanceof TpnControllerInterface.NewGameEvent) {
			end = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			controller.actionUp();
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			controller.actionDown();
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			controller.actionLeft();
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			controller.actionRight();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}
}
