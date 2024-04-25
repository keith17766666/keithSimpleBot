package dev.keith.swing;

import javax.swing.*;
import java.io.OutputStream;

public class SwingOutputStream extends OutputStream {
    private final JTextArea area;
    public SwingOutputStream(JTextArea area) {
        this.area = area;
    }
    @Override
    public void write(int b) {
        area.append(String.valueOf((char) b));
    }
}
