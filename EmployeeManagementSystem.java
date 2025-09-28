import java.io.*;
import java.util.*;

class Employee {
    private int id;
    private String name;
    private double salary;

    public Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public String toCSV() {
        return id + "," + name + "," + salary;
    }

    public void display() {
        System.out.println("ID: " + id + " | Name: " + name + " | Salary: " + salary);
    }


    public static Employee fromCSV(String line) {
        String[] parts = line.split(",");
        return new Employee(Integer.parseInt(parts[0]), parts[1], Double.parseDouble(parts[2]));
    }
}

public class EmployeeManagementSystem {
    private static final String FILE_NAME = "employees.txt";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n==== Employee Management System ====");
            System.out.println("1. Add Employee");
            System.out.println("2. View All Employees");
            System.out.println("3. Search Employee by ID");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1 -> addEmployee(sc);
                case 2 -> viewAllEmployees();
                case 3 -> searchEmployee(sc);
                case 4 -> System.out.println("Exiting... Goodbye!");
                default -> System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 4);

        sc.close();
    }

    
    private static void addEmployee(Scanner sc) {
        try (FileWriter fw = new FileWriter(FILE_NAME, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {

            System.out.print("Enter Employee ID: ");
            int id = sc.nextInt();
            sc.nextLine(); 

            System.out.print("Enter Employee Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Salary: ");
            double salary = sc.nextDouble();

            Employee emp = new Employee(id, name, salary);
            out.println(emp.toCSV());

            System.out.println("Employee added successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void viewAllEmployees() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            System.out.println("\n--- Employee List ---");
            while ((line = br.readLine()) != null) {
                Employee emp = Employee.fromCSV(line);
                emp.display();
            }
        } catch (FileNotFoundException e) {
            System.out.println("No employees found (file not created yet).");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void searchEmployee(Scanner sc) {
        System.out.print("Enter Employee ID to search: ");
        int searchId = sc.nextInt();
        boolean found = false;

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                Employee emp = Employee.fromCSV(line);
                if (emp.getId() == searchId) {
                    System.out.println("Employee Found:");
                    emp.display();
                    found = true;
                    break;
                }
            }
            if (!found) {
                System.out.println("Employee with ID " + searchId + " not found.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("No employees found (file not created yet).");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
