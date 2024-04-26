package dev.keith.swing;

import javax.swing.*;
import java.io.OutputStream;

/**
 * A simple output stream that print to a swing text area.
 */
public class SwingOutputStream extends OutputStream {
    /**
     * the output.
     */
    private final JTextArea area;

    /**
     * init
     * @param area area
     */
    public SwingOutputStream(JTextArea area) {
        this.area = area;
    }

    /**
     * writing to the area
     * @param b   the {@code byte}.
     */
    @Override
    public void write(int b) {
        area.append(String.valueOf((char) b));
    }
}
