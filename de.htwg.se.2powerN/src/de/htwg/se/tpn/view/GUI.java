package de.htwg.se.tpn.view;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
	private static final long serialVersionUID = 1L;
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
				    	JOptionPane.showMessageDialog(GUI.this, "Wrong input", "Game creation failed", JOptionPane.WARNING_MESSAGE);;
				    }
				}
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
		this.setSize(600, 600);
		this.setVisible(true);
	}
	
	private void createTiles() {
		tileLabels = new JLabel[controller.getSize()][controller.getSize()];
		GridLayout layout = new GridLayout(controller.getSize(), controller.getSize());
		layout.setHgap(10);
		layout.setVgap(10);
		this.tilePanel = new JPanel(layout);
		tilePanel.setBorder(new EmptyBorder(30, 30, 30, 30));;
		for (int i = 0; i < controller.getSize(); ++i) {
			for (int j = 0; j < controller.getSize(); ++j) {
				JLabel tileLabel = initLabel(new JLabel(""));
				tileLabels[i][j] = tileLabel;
				tilePanel.add(tileLabel);
			}
		}
		tilePanel.setOpaque(true);
		tilePanel.setBackground(new Color(0xBCB39F));
		updateValues();
	}
	
	private JLabel initLabel(JLabel label) {
		label.setBorder(BorderFactory.createRaisedBevelBorder());
		label.setOpaque(true);
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setVerticalAlignment(JLabel.CENTER);
		label.setVerticalTextPosition(JLabel.CENTER);
		label.setBackground(new Color(0xCAC5BA));
		label.setForeground(new Color(0x716B60));
		Font labelFont = label.getFont();
		label.setFont(new Font(labelFont.getName(), Font.BOLD + Font.ROMAN_BASELINE, getfontsize()));
		
		return label;
	}
	
	private int getfontsize() {
		if (controller.getSize() < 4) {
			return 50;
		} else if (controller.getSize() == 4) {
			return 31;
		} else if (controller.getSize() < 8) {
			return 26;
		} else if (controller.getSize() < 11) {
			return 16;
		}
		return 10;
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
				switch (value) {
					case 0:
						tileLabels[i][j].setBackground(new Color(0xCAC5BA));
						break;
					case 2:
						tileLabels[i][j].setBackground(new Color(0xE8E6D8));
						tileLabels[i][j].setForeground(new Color(0x716B60));
						break;
					case 4:
						tileLabels[i][j].setBackground(new Color(0xE5D8BD));
						tileLabels[i][j].setForeground(new Color(0x716B60));
						break;
					case 8:
						tileLabels[i][j].setBackground(new Color(0xF0AF66));
						tileLabels[i][j].setForeground(Color.WHITE);
						break;
					case 16:
						tileLabels[i][j].setBackground(new Color(0xF2965A));
						tileLabels[i][j].setForeground(Color.WHITE);
						break;
					case 32:
						tileLabels[i][j].setBackground(new Color(0xF57A5B));
						tileLabels[i][j].setForeground(Color.WHITE);
						break;
					case 64:
						tileLabels[i][j].setBackground(new Color(0xF04D24));
						tileLabels[i][j].setForeground(Color.WHITE);
						break;
					case 128:
						tileLabels[i][j].setBackground(new Color(0xE9D37C));
						tileLabels[i][j].setForeground(Color.WHITE);
						break;
					case 256:
						tileLabels[i][j].setBackground(new Color(0xF2D76E));
						tileLabels[i][j].setForeground(Color.WHITE);
						break;
					case 512:
						tileLabels[i][j].setBackground(new Color(0xE5CB43));
						tileLabels[i][j].setForeground(Color.WHITE);
						break;
					case 1024:
						tileLabels[i][j].setBackground(new Color(0xF4E43E));
						tileLabels[i][j].setForeground(Color.WHITE);
						break;
					case 2048:
						tileLabels[i][j].setBackground(new Color(0xE9F028));
						tileLabels[i][j].setForeground(Color.WHITE);
						break;
					default:
						tileLabels[i][j].setBackground(new Color(0x6FD8C0));
						tileLabels[i][j].setForeground(Color.BLACK);
						break;
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
