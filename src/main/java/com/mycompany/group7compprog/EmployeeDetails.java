/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.group7compprog;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class EmployeeDetails extends JFrame {

    private Object[] empData;
    private JComboBox<String> monthComboBox;
    private JTextArea salaryInfoArea;

    public EmployeeDetails(Object[] empData) {
        this.empData = empData;
        initUI();
    }

    private void initUI() {
        setTitle("Employee Details and Salary");
        setSize(450, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Employee details panel
        JPanel detailsPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        String[] labels = {
            "Employee Number:", "Last Name:", "First Name:", "Birthdate:",
            "Address:", "Phone Number:", "Status:", "Position:",
            "SSS:", "TIN:", "PhilHealth:", "Pag-ibig:"
        };
        for (int i = 0; i < labels.length; i++) {
            detailsPanel.add(new JLabel(labels[i]));
            detailsPanel.add(new JLabel(empData[i].toString()));
        }
        mainPanel.add(detailsPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        add(mainPanel);
    }
}
