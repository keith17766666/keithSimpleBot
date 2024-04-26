package dev.keith.swing;

import javax.swing.*;


/**
 * A simple screen.
 */
public class Screen extends JFrame {
    /**
     * init a screen
     */
    public Screen() {
        super();
    }
    /**
     * set up the screen
     * @param area The output area.
     */
    public void setup(JTextArea area) {
        area.setEditable(false);
        this.getContentPane().add(area);
        this.setSize(480, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
