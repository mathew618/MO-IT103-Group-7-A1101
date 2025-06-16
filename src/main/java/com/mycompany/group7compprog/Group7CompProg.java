package com.mycompany.group7compprog;

import java.io.*;
import java.util.*;
import javax.swing.table.DefaultTableModel;

class Temp {

    //Duplicate CSV
    public static void duplicateCSV(String sourceFile, String destinationFile) {
        File file = new File(destinationFile);

        // Check if the file already exists
        if (file.exists()) {
            //System.out.println("File already exists: " + destinationFile);
            return;
        }

        //Duplicate the file
        try (BufferedReader br = new BufferedReader(new FileReader(sourceFile)); BufferedWriter bw = new BufferedWriter(new FileWriter(destinationFile))) {

            String line;
            while ((line = br.readLine()) != null) {
                bw.write(line);
                bw.newLine(); // Move to the next line
            }

        } catch (IOException e) {
            System.err.println("Error duplicating CSV: " + e.getMessage());
        }
    }
}

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

    private static final DefaultTableModel salaryModel = new DefaultTableModel(
            new Object[]{"Employee Number", "Last Name", "First Name", "Basic Salary", "Gross Semi-monthly Rate", "Hourly Rate"}, 0
    );

    // Load employee data from CSV file
    public static void load() {
        if (!empData.isEmpty()) {
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader("temp_emp.csv"))) {
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

    // Reload CSV and Model
    public static void reloadEmp() {
        empData.clear();
        empModel.setRowCount(0);
        salaryModel.setRowCount(0);
        load();
        EmpTable();
        SalaryTable();
    }

    // Create employee list model
    public static void EmpTable() {
        for (String[] data : get()) {
            if (data.length >= 12) {
                String[] filter = {data[0], data[1], data[2], data[3], data[4], data[5], data[10], data[11], data[6], data[8], data[7], data[9]};
                empModel.addRow(filter);
            }
        }
    }
    
    public static void SalaryTable() {
        for (String[] data : get()) {
            if (data.length >= 5) {
                String[] filter = {data[0], data[1], data[2], data[13], data[17], data[18]};
                salaryModel.addRow(filter);
            }
        }
    }

    // Add new employee record
    public static void addEmployee(String... employeeData) {
        String filePath = "temp_emp.csv";

        try (FileWriter writer = new FileWriter(filePath, true)) {
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < employeeData.length; i++) {
                sb.append(employeeData[i]);

                if (i < employeeData.length - 1) {
                    sb.append(",");
                }
            }
            sb.append("\n");

            writer.write(sb.toString());
            writer.flush();
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    // Retrieve employee data models
    public static DefaultTableModel getEmpModel() {
        return empModel;
    }
    
    public static DefaultTableModel getSalaryModel() {
        return salaryModel;
    }

    public static DefaultTableModel getModel() {
        return payslipModel;
    }

    public static List<String[]> get() {
        return empData;
    }

    // Add paid employee list
    public static void setData(String Emp, String LName, String FName, String Date, String Earn, String Deduct, String Total) {
        payslipModel.addRow(new Object[]{Emp, LName, FName, Date, Earn, Deduct, Total});
    }

}

public class Group7CompProg {

    public static void main(String[] args) {
        Temp.duplicateCSV("Employee.csv", "temp_emp.csv");
        Emp.reloadEmp();
        new Login().setVisible(true);

    }
}
