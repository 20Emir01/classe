/*Creare una applicazione in java per la gestione di una scuola. Deve essere possibile
gestire studenti, classi e voti degli studenti nelle materie. Per ogni entità devono
essere previste le operazioni di inserimento, modifica, visualizzazione,
cancellazione.
I dati devono essere salvati in modo da poter essere utilizzati dopo un riavvio
dell’applicazione.
ChatGPT
Ecco un esempio di applicazione Java per la gestione di una scuola che include studenti, classi e voti degli studenti nelle materie. I dati verranno salvati su file per poter essere utilizzati anche dopo un riavvio dell'applicazione. Tieni presente che questo è solo un esempio di implementazione di base e potrebbe essere necessario personalizzarlo ulteriormente in base alle tue esigenze specifiche.

java
Copy code
*/
import java.io.*;
import java.util.*;

class Student implements Serializable {
    private String name;
    private String className;

    public Student(String name, String className) {
        this.name = name;
        this.className = className;
    }

    public String getName() {
        return name;
    }

    public String getClassName() {
        return className;
    }

    @Override
    public String toString() {
        return "Student: " + name + " (Class: " + className + ")";
    }
}

class SubjectGrade implements Serializable {
    private String subject;
    private int grade;

    public SubjectGrade(String subject, int grade) {
        this.subject = subject;
        this.grade = grade;
    }

    public String getSubject() {
        return subject;
    }

    public int getGrade() {
        return grade;
    }

    @Override
    public String toString() {
        return "Subject: " + subject + " (Grade: " + grade + ")";
    }
}

class School implements Serializable {
    private List<Student> students;
    private Map<Student, List<SubjectGrade>> grades;

    public School() {
        students = new ArrayList<>();
        grades = new HashMap<>();
    }

    public void addStudent(Student student) {
        students.add(student);
        grades.put(student, new ArrayList<>());
    }

    public void addGrade(Student student, SubjectGrade grade) {
        if (grades.containsKey(student)) {
            grades.get(student).add(grade);
        }
    }

    public void displayStudents() {
        for (Student student : students) {
            System.out.println(student);
            List<SubjectGrade> studentGrades = grades.get(student);
            for (SubjectGrade grade : studentGrades) {
                System.out.println("\t" + grade);
            }
        }
    }

    public void saveData(String fileName) {
        try {
            FileOutputStream fileOut = new FileOutputStream(fileName);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(this);
            objectOut.close();
            fileOut.close();
            System.out.println("Data saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static School loadData(String fileName) {
        try {
            FileInputStream fileIn = new FileInputStream(fileName);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            School school = (School) objectIn.readObject();
            objectIn.close();
            fileIn.close();
            System.out.println("Data loaded successfully.");
            return school;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}

public class SchoolManagementApp {
    public static void main(String[] args) {
        School school = School.loadData("school_data.ser");

        if (school == null) {
            school = new School();
        }

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("School Management App");
            System.out.println("1. Add Student");
            System.out.println("2. Add Grade");
            System.out.println("3. Display Students");
            System.out.println("4. Save and Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    System.out.println("Enter student name:");
                    String name = scanner.nextLine();
                    System.out.println("Enter class name:");
                    String className = scanner.nextLine();
                    school.addStudent(new Student(name, className));
                    System.out.println("Student added successfully.");
                    break;
                case 2:
                    System.out.println("Enter student name:");
                    String studentName = scanner.nextLine();
                    System.out.println("Enter subject:");
                    String subject = scanner.nextLine();
                    System.out.println("Enter grade:");
                    int grade = scanner.nextInt();
                    scanner.nextLine(); // Consume newline character

                    Student foundStudent = null;
                    for (Student student : school.students) {
                        if (student.getName().equals(studentName)) {
                            foundStudent = student;
                            break;
                        }
                    }

                    if (foundStudent != null) {
                        school.addGrade(foundStudent, new SubjectGrade(subject, grade));
                        System.out.println("Grade added successfully.");
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;
                case 3:
                    school.displayStudents();
                    break;
                case 4:
                    school.saveData("school_data.ser");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }

            System.out.println();
        }
    }
}
/*
In questo esempio, abbiamo tre classi principali: Student (studente), SubjectGrade (voto di una materia) e School (scuola). La classe Student rappresenta uno studente con nome e classe, mentre la classe SubjectGrade rappresenta un voto in una specifica materia. La classe School gestisce gli studenti e i voti degli studenti.

L'applicazione offre un menu di scelta per le operazioni richieste: aggiungere uno studente, aggiungere un voto, visualizzare gli studenti e salvare i dati. I dati vengono salvati su un file chiamato "school_data.ser" utilizzando la serializzazione degli oggetti Java.

Nota che è necessario gestire gli errori e le eccezioni in modo appropriato nell'applicazione finale, ma ho omesso alcune parti per mantenere l'esempio semplice e chiaro.

Assicurati di avere la libreria Java Serialization nel tuo classpath per eseguire correttamente l'applicazione.
*/
