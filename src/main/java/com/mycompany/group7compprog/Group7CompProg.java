package com.mycompany.group7compprog;
import java.io.*;
import java.util.*;

public class Group7CompProg {
    static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {
        CheckEmp bread = new CheckEmp();
        bread.check();
        boolean wood = true;
        
        if (!admin()) {
            System.out.println("");
            return;
        }
        
        while (wood) {
            System.out.println("\n1. View Employees\n2. Add Employee\n3. Remove Employee\n4. Exit");
            System.out.print("Choose an Option: ");
            int wa = scan.nextInt();
            scan.nextLine();
            
            switch (wa) {
                case 1:
                    bread.read();
                    break;
                /*case 2:
                    System.out.print("Who we tickling?: ");
                    int tickle = scan.nextInt();
                    scan.nextLine();
                    bread.select(tickle);
                    break;*/
                case 2:
                    System.out.println("Add Employee"); // finish later
                    break;
                case 3:
                    System.out.println("Remove Employee"); // finish later
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
        String name = "fidel";
        String pw = "amen";
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
    private final List<Iforgor> banana = new ArrayList<>();
    
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
    /*
    public void select(int rowNumber) {
        if (rowNumber > 0 && rowNumber <= banana.size()) {
            Iforgor er = banana.get(rowNumber - 1);
            int result = Math.random() < 0.5 ? 0 : 1;
            if (result == 0) {
                System.out.print("You tickled: " + er.name);
            } else {
                System.out.println("You tickled " + er.name + " too much. he died. ;[[");
                banana.remove(rowNumber - 1);
            }
            
        } else {
            System.out.println("Oops. wrong number. I think.");
        }
    }*/
}
