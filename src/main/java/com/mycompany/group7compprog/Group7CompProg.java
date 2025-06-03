package com.mycompany.group7compprog;
import java.io.*;
import java.util.*;
import javax.swing.table.DefaultTableModel;

class Emp {
    private static final List<String[]> banana = new ArrayList<>();
    private static final DefaultTableModel model = new DefaultTableModel(new Object[]{"Employee Number", "Last Name", "First Name", "Date", "Earnings", "Deductions", "Total"}, 0);
    
    public static void load() {
        if (!banana.isEmpty()) return;
        String file = "Employee.csv";
        
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            
            while((line = br.readLine()) != null) {
                //System.out.println(line);
                String[] data = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                if (data.length >= 5) {
                    banana.add(data);
                }
            }
        } catch(FileNotFoundException e) {
            System.out.println("I forgot what I lost");
        } catch (IOException e) {
            System.out.println("Error");
        }
    }
    public static List<String[]> get() {
        return banana;
    }
    
    public static DefaultTableModel getModel() {
        return model;
    }
    
    public static void setData(String Emp, String LName, String FName, String Date, String Earn, String Deduct, String Total) {
        model.addRow(new Object[] {Emp, LName, FName, Date, Earn, Deduct, Total});
    }
} 

public class Group7CompProg {
    public static void main(String[] args) {
        Emp.load();
        new Login().setVisible(true);
        
    }
}
