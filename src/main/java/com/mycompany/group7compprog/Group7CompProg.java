package com.mycompany.group7compprog;

import java.io.*;
import java.util.*;
import javax.swing.table.DefaultTableModel;

class Emp {

    // Employee data storage
    private static final List<String[]> empData = new ArrayList<>();

    // Table models for employee records and payslip
    private static final DefaultTableModel payslipModel = new DefaultTableModel(
        new Object[]{"Employee Number", "Last Name", "First Name", "Date", "Earnings", "Deductions", "Total"}, 0
    );

    private static final DefaultTableModel empModel = new DefaultTableModel(
        new Object[]{"Employee Number", "Last Name", "First Name", "Birthdate", "Address", "Phone Number", 
                     "Status", "Position", "SSS", "Tin", "PhilHealth", "Pag-ibig"}, 0
    );

    // Load employee data from CSV file
    public static void load() {
        if (!empData.isEmpty()) return;

        try (BufferedReader br = new BufferedReader(new FileReader("Employee.csv"))) {
            String line;

            while ((line = br.readLine()) != null) {
                //System.out.println(line);
                String[] data = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                if (data.length >= 5) {
                    empData.add(data);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error loading file: " + e.getMessage());
        }
    }

    public static void EmpTable() {
        for (String[] data : get()) {
            if (data.length >= 12) {
                String[] filter = {data[0], data[1], data[2], data[3], data[4], data[5], data[10], data[11], data[6], data[8], data[7], data[9]};
                empModel.addRow(filter);
            }
        }
    }

    // Add new employee record
    public static void addEmployee(String... employeeData) {
        empModel.addRow(employeeData);
    }

    // Retrieve employee data models
    public static DefaultTableModel getEmpModel() {
        return empModel;
    }
    
    public static DefaultTableModel getModel() {
        return payslipModel;
    }
    
    public static List<String[]> get() {
        return empData; 
    }

    public static void setData(String Emp, String LName, String FName, String Date, String Earn, String Deduct, String Total) {
        payslipModel.addRow(new Object[]{Emp, LName, FName, Date, Earn, Deduct, Total});
    }

}

public class Group7CompProg {

    public static void main(String[] args) {
        Emp.load();
        Emp.EmpTable();
        new Login().setVisible(true);

    }
}
