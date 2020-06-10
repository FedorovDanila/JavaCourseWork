package main.form;

import main.model.TokenModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;

public class MainMenu extends Form {
    public static void switchForm(JFrame frame, TokenModel token) {
        final List<JButton> options = new ArrayList<>() {{
            add(new JButton("wards menu"));
            add(new JButton("patients menu"));
            add(new JButton("diagnoses menu"));
            add(new JButton("log out"));
        }};

        Listener listener = new Listener(frame, token);
        for (JButton b : options) {
            b.addActionListener(listener);
        }

        final int gridWidth = 3, gridHeight = 6;

        Container cont = frame.getContentPane();
        cont.removeAll();
        cont.setLayout(new GridLayout(gridHeight, gridWidth));
        addSpaces(cont, gridWidth * (gridHeight - 4) / 2);

        for (JButton b : options) {
            addSpaces(cont, (gridWidth - 1) / 2);
            cont.add(b);
            addSpaces(cont, (gridWidth - 1) / 2);
        }

        addSpaces(cont, gridWidth * (gridHeight - 4) / 2);

        frame.validate();
    }

    static private class Listener implements ActionListener {
        private JFrame frame;
        private TokenModel token;

        public Listener(JFrame frame, TokenModel token) {
            this.frame = frame;
            this.token = token;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();

            switch(button.getText()) {
                case ("wards menu"):
                    WardsMenu.switchForm(frame, token);
                    break;
                case ("patients menu"):
                    PatientsMenu.switchForm(frame, token);
                    break;
                case ("diagnoses menu"):
                    DiagnosisMenu.switchForm(frame, token);
                    break;
                case ("log out"):
                    Authorise.swithc(frame);
                    break;
            }
        }
    }
}
