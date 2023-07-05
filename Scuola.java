import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.IOException;

class Scuola implements Serializable {
    List<Studenti> students;
    private Map<Studenti, List<Voti>> grades;

    public Scuola() {
        students = new ArrayList<>();
        grades = new HashMap<>();
    }

    public List<Studenti> getStudentList() {
        return students;
    }

    public void addStudent(Studenti student) {
        students.add(student);
        grades.put(student, new ArrayList<>());
    }

    public void removeStudent(Studenti student) {
        students.remove(student);
        grades.put(student, new ArrayList<>());
    }

    public void modifyStudent(Studenti oldStudent, Studenti newStudent) {

        if (grades.containsKey(oldStudent)) {
            List<Voti> aux = grades.get(oldStudent);
            addStudent(newStudent);
            grades.put(newStudent, aux);
            students.remove(oldStudent);
            System.out.println("Studente modificato con successo");
        } else {
            System.out.println("Studente non trovato");
        }
    }

    public void addGrade(Studenti student, Voti grade) {
        if (grades.containsKey(student)) {
            grades.get(student).add(grade);
        }
    }

    public void removeGrade(Studenti student, Voti grade) {
        if (grades.containsKey(student)) {
            //grades.get(student).remove(grade);
            List<Voti> studentGrades = grades.get(student);
            studentGrades.remove(grade);
        }
    }

    public void modifyGrade(Studenti student, Voti oldGrade, Voti newGrade) {
        if (grades.containsKey(student)) {
            List<Voti> studentGrades = grades.get(student);
            int index = studentGrades.indexOf(oldGrade);
            if (index != -1) {
                studentGrades.set(index, newGrade);
                System.out.println("Voto modificato con successo");
            } else {
                System.out.println("Voto non trovato per lo studente specificato");
            }
        } else {
            System.out.println("Studente non trovato");
        }
    }

    public void displayStudents() {
        for (Studenti student : students) {
            System.out.println(student);
            List<Voti> studentGrades = grades.get(student);
            for (Voti grade : studentGrades) {
                System.out.println("\t" + grade);
            }
            System.out.println();
        }
    }


    public void saveData(String fileName) {
        try {
            FileOutputStream fileOut = new FileOutputStream(fileName);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(this);
            objectOut.close();
            fileOut.close();
            System.out.println("Salvato con successo.");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static Scuola loadData(String fileName) {
        try {
            FileInputStream fileIn = new FileInputStream(fileName);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            Scuola school = (Scuola) objectIn.readObject();
            objectIn.close();
            fileIn.close();
            System.out.println("Caricato con successo.");
            return school;
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }


}
