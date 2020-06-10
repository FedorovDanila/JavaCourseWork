package main.form;

import main.exception.AccesDeniedException;
import main.exception.DataTransformFailureException;
import main.exception.RequestFailureException;
import main.model.DiagnosisModel;
import main.model.PatientModel;
import main.model.TokenModel;
import main.web.DiagnosisRequestController;
import main.web.WardRequestController;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class DiagnosisMenu extends Form {
    public static void switchForm(JFrame frame, TokenModel token) {
        JButton add = new JButton("add diagnosis");
        JButton delete = new JButton("delete diagnosis");
        JButton list = new JButton("show diagnoses");
        JButton patients = new JButton(" get patients with diagnosis");
        JButton back = new JButton("return");

        add.addActionListener(e -> {
            add(frame, token);
        });

        delete.addActionListener(e -> {
            delete(frame, token);
        });

        list.addActionListener(e -> {
            list(frame, token);
        });

        patients.addActionListener(e -> {
            patients(frame, token);
        });

        back.addActionListener(e -> {
            MainMenu.switchForm(frame, token);
        });

        final int gridWidth = 3, gridHeight = 7;

        Container cont = frame.getContentPane();
        cont.removeAll();
        cont.setLayout(new GridLayout(gridHeight, gridWidth));
        addSpaces(cont, gridWidth * (gridHeight - 5) / 2);
        addSpaces(cont, (gridWidth-1) / 2);
        cont.add(add);
        addSpaces(cont, gridWidth - 1);
        cont.add(delete);
        addSpaces(cont, gridWidth - 1);
        cont.add(list);
        addSpaces(cont, gridWidth - 1);
        cont.add(patients);
        addSpaces(cont, gridWidth - 1);
        cont.add(back);
        addSpaces(cont, (gridWidth-1) / 2);
        addSpaces(cont, gridWidth * (gridHeight - 5) / 2);

        frame.validate();
    }

    private static void add(JFrame frame, TokenModel token) {
        JLabel nameLabel = new JLabel("diagnosis name:");
        JTextField name = new JTextField();
        JButton back = new JButton("return");
        JButton add = new JButton("add");

        back.addActionListener(e -> {
            DiagnosisMenu.switchForm(frame, token);
        });

        add.addActionListener(e -> {
            String nameS = name.getText();
            if (nameS == "") {
                return;
            }

            DiagnosisModel diagnosis = new DiagnosisModel(nameS);
            try {
                DiagnosisRequestController.add(token, diagnosis);
            } catch(AccesDeniedException ex) {
                JOptionPane.showMessageDialog(null, "Your token is not relevant. Please authorise again");
            } catch (RequestFailureException ex) {
                JOptionPane.showMessageDialog(null, "Request failed with " + ex.getMessage());
            } catch (DataTransformFailureException ex) {
                ex.printStackTrace();
            }
        });

        final int gridWidth = 4, gridHeight = 5;

        Container cont = frame.getContentPane();
        cont.removeAll();
        cont.setLayout(new GridLayout(gridHeight, gridWidth));
        addSpaces(cont, gridWidth * (gridHeight - 3) / 2);
        addSpaces(cont, gridWidth/2 - 1);
        cont.add(nameLabel);
        cont.add(name);
        addSpaces(cont, gridWidth - 2);
        addSpaces(cont, gridWidth);
        cont.add(back);
        cont.add(add);
        addSpaces(cont, gridWidth/2 - 1);
        addSpaces(cont, gridWidth * (gridHeight - 3) / 2);

        frame.validate();
    }

    private static void delete(JFrame frame, TokenModel token) {
        JLabel idLabel = new JLabel("diagnosis id:");
        JTextField id = new JTextField();
        JButton back = new JButton("return");
        JButton add = new JButton("delete");

        back.addActionListener(e -> {
            WardsMenu.switchForm(frame, token);
        });

        add.addActionListener(e -> {
            String idS = id.getText();
            long idL = Long.parseLong(idS);
            if (idL < 1) {
                return;
            }

            try {
                DiagnosisRequestController.delete(token, idL);
            } catch(AccesDeniedException ex) {
                JOptionPane.showMessageDialog(null, "Your token is not relevant. Please authorise again");
            } catch (RequestFailureException ex) {
                JOptionPane.showMessageDialog(null, "Request failed with " + ex.getMessage());
            } catch (DataTransformFailureException ex) {
                ex.printStackTrace();
            }
        });

        final int gridWidth = 4, gridHeight = 5;

        Container cont = frame.getContentPane();
        cont.removeAll();
        cont.setLayout(new GridLayout(gridHeight, gridWidth));
        addSpaces(cont, gridWidth * (gridHeight - 3) / 2);
        addSpaces(cont, gridWidth/2 - 1);
        cont.add(idLabel);
        cont.add(id);
        addSpaces(cont, gridWidth - 2);
        addSpaces(cont, gridWidth);
        cont.add(back);
        cont.add(add);
        addSpaces(cont, gridWidth/2 - 1);
        addSpaces(cont, gridWidth * (gridHeight - 3) / 2);

        frame.validate();
    }

    private static void list(JFrame frame, TokenModel token) {
        try {
            List<String> strings = DiagnosisRequestController.getAll(token)
                    .stream()
                    .map(DiagnosisModel::toString)
                    .collect(Collectors.toList());
            printList(frame, token, strings, 1);
        } catch(AccesDeniedException ex) {
            JOptionPane.showMessageDialog(null, "Your token is not relevant. Please authorise again");
        } catch (RequestFailureException ex) {
            JOptionPane.showMessageDialog(null, "Request failed with " + ex.getMessage());
        } catch (DataTransformFailureException ex) {
            ex.printStackTrace();
        }
    }

    private static void patients(JFrame frame, TokenModel token) {
        JLabel idLabel = new JLabel("diagnosis id:");
        JTextField id = new JTextField();
        JButton back = new JButton("return");
        JButton add = new JButton("delete");

        back.addActionListener(e -> {
            WardsMenu.switchForm(frame, token);
        });

        add.addActionListener(e -> {
            String idS = id.getText();
            long idL = Long.parseLong(idS);
            if (idL < 1) {
                return;
            }

            List<String> models;
            try {
                models = DiagnosisRequestController.getPatientsWithDiagnosis(token, idL)
                        .stream()
                        .map(PatientModel::toString)
                        .collect(Collectors.toList());
                        printList(frame, token, models, 1);
            } catch(AccesDeniedException ex) {
                JOptionPane.showMessageDialog(null, "Your token is not relevant. Please authorise again");
            } catch (RequestFailureException ex) {
                JOptionPane.showMessageDialog(null, "Request failed with " + ex.getMessage());
            } catch (DataTransformFailureException ex) {
                ex.printStackTrace();
            }
        });

        final int gridWidth = 4, gridHeight = 5;

        Container cont = frame.getContentPane();
        cont.removeAll();
        cont.setLayout(new GridLayout(gridHeight, gridWidth));
        addSpaces(cont, gridWidth * (gridHeight - 3) / 2);
        addSpaces(cont, gridWidth/2 - 1);
        cont.add(idLabel);
        cont.add(id);
        addSpaces(cont, gridWidth - 2);
        addSpaces(cont, gridWidth);
        cont.add(back);
        cont.add(add);
        addSpaces(cont, gridWidth/2 - 1);
        addSpaces(cont, gridWidth * (gridHeight - 3) / 2);

        frame.validate();
    }
}
