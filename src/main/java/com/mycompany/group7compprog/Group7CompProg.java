package com.mycompany.group7compprog;

import java.io.*;
import java.util.*;
import javax.swing.table.DefaultTableModel;

class Emp {

    private static final List<String[]> banana = new ArrayList<>();
    private static final DefaultTableModel model = new DefaultTableModel(new Object[]{"Employee Number", "Last Name", "First Name", "Date", "Earnings", "Deductions", "Total"}, 0);
    private static final DefaultTableModel empModel = new DefaultTableModel(new Object[]{"Employee Number", "Last Name", "First Name", "Birthdate", "Address", "Phone Number", "Status", "Position", "SSS", "Tin", "PhilHealth", "Pag-ibig"}, 0);

    public static void load() {
        if (!banana.isEmpty()) return;
        String file = "Employee.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = br.readLine()) != null) {
                //System.out.println(line);
                String[] data = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                if (data.length >= 5) {
                    banana.add(data);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
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

    public static void addEmployee(String Emp, String LName, String FName, String Birthdate, String Address, String Phone, String Status, String Position, String SSS, String Tin, String PhilHealth, String Pagibig) {
        empModel.addRow(new Object[]{Emp, LName, FName, Birthdate, Address, Phone, Status, Position, SSS, Tin, PhilHealth, Pagibig});
    }

    public static DefaultTableModel getEmpModel() {
        return empModel;
    }

    public static List<String[]> get() {
        return banana;
    }

    public static DefaultTableModel getModel() {
        return model;
    }

    public static void setData(String Emp, String LName, String FName, String Date, String Earn, String Deduct, String Total) {
        model.addRow(new Object[]{Emp, LName, FName, Date, Earn, Deduct, Total});
    }

}

public class Group7CompProg {

    public static void main(String[] args) {
        Emp.load();
        Emp.EmpTable();
        new Login().setVisible(true);

    }
}
