package dev.keith.swing;

import javax.swing.*;

public class Screen extends JFrame {
    public void setup(JTextArea area) {
        this.getContentPane().add(area);
        this.setSize(480, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
