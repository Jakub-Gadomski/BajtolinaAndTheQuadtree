package com.example.unnamed_roguelike_game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class ProstyInterfejsGraficznyToNieCzescGryTylkoPrzyklad extends JFrame implements ActionListener {
    private JTextField nameField;
    private JButton greetButton;
    public ProstyInterfejsGraficznyToNieCzescGryTylkoPrzyklad() {
        setTitle("Prosty interfejs graficzny");
        setSize(300, 150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));
        panel.add(new JLabel("Imię:"));
        nameField = new JTextField();
        panel.add(nameField);
        greetButton = new JButton("Witaj!");
        greetButton.addActionListener(this);
        panel.add(greetButton);

        add(panel);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == greetButton) {
            String name = nameField.getText();
            JOptionPane.showMessageDialog(this, "Witaj, " + name + "!");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GameInterface().setVisible(true);
            }
        });
    }
}