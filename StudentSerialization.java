import java.io.*;

class Student implements Serializable {
    private int id;
    private String name;
    private double marks;

    public Student(int id, String name, double marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }

    public void display() {
        System.out.println("ID: " + id + ", Name: " + name + ", Marks: " + marks);
    }
}

public class StudentSerialization {
    public static void main(String[] args) {
        String filename = "student.ser";

        // Step 2: Create a student object
        Student s1 = new Student(11375, "Sameep Das", 88.5);

        // Step 3: Serialization (Write object to file)
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(s1);
            System.out.println("Serialization successful. Student object saved.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Step 4: Deserialization (Read object from file)
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            Student s2 = (Student) in.readObject();
            System.out.println("Deserialization successful. Student object read:");
            s2.display();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
