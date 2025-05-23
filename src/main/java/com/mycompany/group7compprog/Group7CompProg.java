package com.mycompany.group7compprog;
import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;

public class Group7CompProg {
    static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {
        CheckEmp bread = new CheckEmp();
        bread.check();
        boolean wood = true;
        
        new Login().setVisible(true);
        
        
        /*if (!admin()) {
            System.out.println("");
            return;
        }*/
        
        while (wood) {
            System.out.println("\n1. View Employees\n2. Add Employee\n3. Remove Employee\n4. Exit");
            System.out.print("Choose an Option: ");
            int wa = scan.nextInt();
            scan.nextLine();
            
            switch (wa) {
                case 1:
                    bread.read();
                    break;
                case 2:
                    System.out.println("Add Employee"); // finish later
                    bread.show();
                    break;
                case 3:
                    System.out.print("Remove Employee Number: "); // finish later
                    int pick = scan.nextInt();
                    scan.nextLine();
                    bread.select(pick);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    wood = false;
                    scan.close();
                    return;
                default:
                    System.out.println("what?");
            }
        }
    }
    
    private static boolean admin() {
        String name = "MotorPHAdmin";
        String pw = "Broom890";
        System.out.println("LOGIN");
        
        for (int i = 3; i > 0; i--) {
            System.out.print("Enter username: ");
            String user = scan.nextLine();
            System.out.print("Enter password: ");
            String pass = scan.nextLine();

            if (user.equals(name) && pass.equals(pw)) {
                System.out.println("Login successful!");
                return true;
            }

            else {
                System.out.println("Incorrect credentials. Attempts left: " + (i - 1));
            }
        }
        return false;
    }
}

class Iforgor {
    String name, position;
    int age;

    public Iforgor(String name, int age, String position) {
        this.name = name;
        this.age = age;
        this.position = position;
    }   
}

class CheckEmp {
    private final ArrayList<Iforgor> banana = new ArrayList<>();
    
    public void check() {
        String file = "yes.csv";
        String line;
            
        try (BufferedReader renx = new BufferedReader(new FileReader(file))) {
            banana.clear();
            
            while((line = renx.readLine()) != null) {
                //System.out.println(line);
                String[] data = line.split(",");
                
                if (data.length == 3) {
                    String name = data[0].trim();
                    int age = Integer.parseInt(data[1].trim());
                    String position = data[2].trim();

                    banana.add(new Iforgor(name, age, position));
                }
            }
        } catch(FileNotFoundException e) {
            System.out.println("I forgot what I lost");
        } catch (IOException e) {
        }
    }
    
    public void read() {
        if (banana.isEmpty()) {
            System.out.println("No employees to display.");
        } else {
            System.out.println("\nEmployee List:");
            for (Iforgor emp : banana) {
                System.out.println("Name: " + emp.name + ", Age: " + emp.age + ", Position: " + emp.position);
            }
        }
    }
    public void select(int rowNumber) {
        if (rowNumber > 0 && rowNumber <= banana.size()) {
            Iforgor er = banana.get(rowNumber - 1);
            System.out.println("\nRemoved Employe: " + er.name);
            banana.remove(rowNumber - 1);
            
        } else {
            System.out.println("Oops. wrong number. I think.");
        }
    }
    
    //Show Employees Test
    public void show() {
        JFrame frame = new JFrame("Item List");
        frame.setSize(600, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(banana.size(), 1));
        
        for (Iforgor item : banana) {
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            
            JLabel label = new JLabel("Name: " + item.name + ", Age: " + item.age + ", Position: " + item.position, SwingConstants.CENTER);
            JButton button = new JButton("Tickle");
            JButton pay = new JButton("Pay");
            button.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Tickled " + item.name));
            
            panel.add(label);
            panel.add(button);
            panel.add(pay);
            
            frame.add(panel);
        }
        
        frame.setVisible(true);
    }
    
}
