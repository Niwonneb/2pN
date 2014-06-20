package de.htwg.se.tpn.view;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class GUI extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main (String[] args) {
		
	final JFrame jf = new JFrame();
	jf.setTitle("2048");
	jf.setSize(600, 600);
	jf.setResizable(false);
	jf.setVisible(true);
	jf.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	JMenuBar jmb = new JMenuBar();
	jf.setJMenuBar(jmb);
	JMenu menuFile = new JMenu ("File");
	jmb.add(menuFile);
	
	
	JMenuItem infoItem = new JMenuItem("Info");
	infoItem.addActionListener(new ActionListener() { 
		public void actionPerformed (ActionEvent e) {
			JOptionPane.showMessageDialog(jf, "NW & JM");
		}
		
	});
	menuFile.add(infoItem);
	
	
	
	JMenuItem exitItem = new JMenuItem("Exit...");
	infoItem.addActionListener(new ActionListener() {
		
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
			
		}
	});
	menuFile.add(exitItem);
	
	}
}
