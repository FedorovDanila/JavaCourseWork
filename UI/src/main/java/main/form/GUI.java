package main.form;

import javax.swing.*;

public class GUI {
    JFrame frame;
    public GUI() {
        frame = new JFrame("Hospital");
        frame.setSize(800, 300);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        Authorise.swithc(frame);
    }
}
