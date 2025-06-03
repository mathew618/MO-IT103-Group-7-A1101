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

        // Month selection
        JPanel monthPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        monthPanel.add(new JLabel("Select Month:"));
        String[] months = {
            "January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"
        };
        monthComboBox = new JComboBox<>(months);
        monthPanel.add(monthComboBox);
        mainPanel.add(monthPanel);

        // Compute button
        JButton computeButton = new JButton("Compute");
        mainPanel.add(computeButton);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Salary info area
        salaryInfoArea = new JTextArea(8, 30);
        salaryInfoArea.setEditable(false);
        salaryInfoArea.setBorder(BorderFactory.createTitledBorder("Salary Information"));
        mainPanel.add(new JScrollPane(salaryInfoArea));

        add(mainPanel);

        // Button action
        computeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displaySalary();
            }

            private void displaySalary() {
                
                System.out.println("empData length: " + empData.length);
                for (int i = 0; i < empData.length; i++) {
                    System.out.println(i + ": " + empData[i]);
                }
                String selectedMonth = (String) monthComboBox.getSelectedItem();
                String employeeNumber = empData[0].toString();

                if (empData.length < 23) {
                    salaryInfoArea.setText("Not enough data to compute salary.");
                    return;
                }

                try {
                    double hourlyRate = Double.parseDouble(empData[18].toString());
                    double sssDeduction = Double.parseDouble(empData[19].toString());
                    double philhealthDeduction = Double.parseDouble(empData[20].toString());
                    double pagibigDeduction = Double.parseDouble(empData[21].toString());
                    double withholdingTax = Double.parseDouble(empData[22].toString());

                    double totalHours = computeHoursWorked(employeeNumber, selectedMonth);

                    double grossSalary = totalHours * hourlyRate;
                    double totalDeductions = sssDeduction + philhealthDeduction + pagibigDeduction + withholdingTax;
                    double netSalary = grossSalary - totalDeductions;

                    StringBuilder sb = new StringBuilder();
                    sb.append("Salary details for ").append(selectedMonth).append(":\n");
                    sb.append("----------------------------\n");
                    sb.append(String.format("Total Hours Worked: %.2f\n", totalHours));
                    sb.append(String.format("Hourly Rate: ₱%,.2f\n", hourlyRate));
                    sb.append(String.format("Gross Salary: ₱%,.2f\n", grossSalary));
                    sb.append(String.format("SSS Deduction: ₱%,.2f\n", sssDeduction));
                    sb.append(String.format("PhilHealth Deduction: ₱%,.2f\n", philhealthDeduction));
                    sb.append(String.format("Pag-ibig Deduction: ₱%,.2f\n", pagibigDeduction));
                    sb.append(String.format("Withholding Tax: ₱%,.2f\n", withholdingTax));
                    sb.append(String.format("Total Deductions: ₱%,.2f\n", totalDeductions));
                    sb.append(String.format("Net Salary: ₱%,.2f\n", netSalary));

                    salaryInfoArea.setText(sb.toString());
                } catch (NumberFormatException ex) {
                    salaryInfoArea.setText("Invalid salary data format.");
                    ex.printStackTrace();
                }
            }

            private double computeHoursWorked(String employeeNumber, String selectedMonth) {
                double totalHours = 0.0;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                try (BufferedReader br = new BufferedReader(new FileReader("AttendanceData.csv"))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        String[] parts = line.split(",");

                        if (parts.length < 3) continue;  // Skip malformed lines

                        String empNum = parts[0].trim();
                        LocalDate date = LocalDate.parse(parts[1].trim(), formatter);
                        double hours = Double.parseDouble(parts[2].trim());

                        System.out.printf("Checking record: empNum=%s, date=%s, hours=%.2f\n", empNum, date.getMonth().name(), hours);

                        if (empNum.equals(employeeNumber) && date.getMonth().name().equalsIgnoreCase(selectedMonth)) {
                            System.out.printf("Adding %.2f hours for %s on %s\n", hours, empNum, date);
                            totalHours += hours;
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                return totalHours;
            }
        });
    }
}




    
    
