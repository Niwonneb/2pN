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

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import akka.actor.ActorRef;
import akka.actor.dsl.Creators;
import de.htwg.se.tpn.controller.TpnControllerInterface;
import de.htwg.se.tpn.model.GameField;
import de.htwg.se.tpn.model.GameFieldInterface;
import de.htwg.se.tpn.util.observer.Event;
import de.htwg.se.tpn.util.observer.IObserver;

public class GUI extends JFrame implements KeyListener {

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

    private static final int NUMBER2 = 2;
    private static final int NUMBER4 = 4;
    private static final int NUMBER8 = 8;
    private static final int NUMBER16 = 16;
    private static final int NUMBER32 = 32;
    private static final int NUMBER64 = 64;
    private static final int NUMBER128 = 128;
    private static final int NUMBER256 = 256;
    private static final int NUMBER512 = 512;
    private static final int NUMBER1024 = 1024;
    private static final int NUMBER2048 = 2048;

    @SuppressWarnings("serial")
    private static final Map<Integer, Integer> FORECOLORS =
            new TreeMap<Integer, Integer>() {{
                put(0, FORE1);
                put(NUMBER2, FORE2);
                put(NUMBER4, FORE2);
                put(NUMBER8, FORE3);
                put(NUMBER16, FORE3);
                put(NUMBER32, FORE3);
                put(NUMBER64, FORE3);
                put(NUMBER128, FORE3);
                put(NUMBER256, FORE3);
                put(NUMBER512, FORE3);
                put(NUMBER1024, FORE3);
                put(NUMBER2048, FORE3);
                put(-1, FORE1);
            }};
    @SuppressWarnings("serial")
    private static final Map<Integer, Integer> BACKCOLORS =
            new TreeMap<Integer, Integer>() {{
                put(0, BACK0);
                put(NUMBER2, BACK2);
                put(NUMBER4, BACK4);
                put(NUMBER8, BACK8);
                put(NUMBER16, BACK16);
                put(NUMBER32, BACK32);
                put(NUMBER64, BACK64);
                put(NUMBER128, BACK128);
                put(NUMBER256, BACK256);
                put(NUMBER512, BACK512);
                put(NUMBER1024, BACK1024);
                put(NUMBER2048, BACK2048);
                put(-1, BACKREST);
            }};
    private JLabel[][] tileLabels;
    private JPanel tilePanel;
    private boolean end;
    transient GUIActor guiActor;

    public GUI(GameFieldInterface gameField, GUIActor guiActor) {
        this.guiActor = guiActor;
        tileLabels = new JLabel[gameField.getHeight()][gameField.getHeight()];
        this.addKeyListener(this);
        end = false;

        this.setTitle("Two Power N");
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JMenuBar jmb = new JMenuBar();
        this.setJMenuBar(jmb);
        JMenu menuFile = new JMenu("Game");
        jmb.add(menuFile);

        JMenuItem newGame = new JMenuItem("New Game");
        newGame.addActionListener(e -> handle());
        menuFile.add(newGame);

        JMenuItem saveItem = new JMenuItem("Save");
        saveItem.addActionListener(e -> this.showSaveGameDialog());
        menuFile.add(saveItem);

        JMenuItem loadItem = new JMenuItem("Load");
        loadItem.addActionListener(e -> this.showLoadGameDialog());
        menuFile.add(loadItem);

        JMenuItem exitItem = new JMenuItem("Exit...");
        exitItem.addActionListener(e -> System.exit(0));
        menuFile.add(exitItem);

        createTiles(gameField);
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
                guiActor.newGame(size, inserts);
            } catch (Exception err) {
                JOptionPane.showMessageDialog(GUI.this, "Wrong input",
                        "Game creation failed", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    private void showSaveGameDialog() {
        JTextField gameName = new JTextField();
        Object[] message = {
                "Game Name:", gameName,
        };
        int option = JOptionPane.showConfirmDialog(
                GUI.this,
                message,
                "Save Game",
                JOptionPane.OK_CANCEL_OPTION);


        if (option == JOptionPane.OK_OPTION) {
            guiActor.saveGame(gameName.getText());
        }
    }

    private void showLoadGameDialog() {
        JTextField gameName = new JTextField();
        Object[] message = {
                "Game Name:", gameName,
        };
        int option = JOptionPane.showConfirmDialog(
                GUI.this,
                message,
                "Load Game",
                JOptionPane.OK_CANCEL_OPTION);


        if (option == JOptionPane.OK_OPTION) {
            guiActor.loadGame(gameName.getText());
        }
    }

    private void createTiles(GameFieldInterface gameField) {
        int gameSize = gameField.getHeight();
        tileLabels = new JLabel[gameSize][gameSize];
        GridLayout layout = new GridLayout(gameSize, gameSize);
        layout.setHgap(INNERGAP);
        layout.setVgap(INNERGAP);
        this.tilePanel = new JPanel(layout);
        tilePanel.setBorder(new EmptyBorder(OUTERGAP, OUTERGAP,
                OUTERGAP, OUTERGAP));
        for (int i = 0; i < gameSize; ++i) {
            for (int j = 0; j < gameSize; ++j) {
                JLabel tileLabel = initLabel(new JLabel(""), gameSize);
                tileLabels[i][j] = tileLabel;
                tilePanel.add(tileLabel);
            }
        }
        tilePanel.setOpaque(true);
        tilePanel.setBackground(new Color(FRAMECOLOR));
        updateValues(gameField);
    }

    private JLabel initLabel(JLabel label, int gameSize) {
        label.setBorder(BorderFactory.createRaisedBevelBorder());
        label.setOpaque(true);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.CENTER);
        label.setBackground(new Color(BACKCOLORS.get(0)));
        Font labelFont = label.getFont();
        label.setFont(new Font(labelFont.getName(),
                Font.BOLD + Font.ROMAN_BASELINE, getfontsize(gameSize)));

        return label;
    }

    private int getfontsize(int gameSize) {
        if (gameSize < STDSIZE) {
            return LOWSIZEFONT;
        } else if (gameSize == STDSIZE) {
            return STDSIZEFONT;
        } else if (gameSize < HIGSIZE) {
            return HIGSIZEFONT;
        } else if (gameSize < VHISIZE) {
            return VHISIZEFONT;
        }
        return ELSSIZEFONT;
    }

    private void updateValues(GameFieldInterface gameField) {
        for (int i = 0; i < gameField.getHeight(); ++i) {
            for (int j = 0; j < gameField.getHeight(); ++j) {
                int value = gameField.getValue(i, j);
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
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            guiActor.actionUp();
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            guiActor.actionDown();
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            guiActor.actionLeft();
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            guiActor.actionRight();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void handleNewFieldEvent(GameFieldInterface gameField) {
        updateValues(gameField);
    }

    public void handleGameOverEvent(GameFieldInterface gameField) {
        if (!end) {
            end = true;
            JOptionPane.showMessageDialog(this, "Game over");
        }
    }

    public void handleNewGameEvent(GameFieldInterface gameField) {
        end = false;
    }

    public void handleErrorEvent(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
