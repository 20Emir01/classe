//import java.io.BufferedWriter;
//import java.io.FileOutputStream;
//import java.io.OutputStreamWriter;
import java.util.*;
//import com.google.gson.Gson;
//import net.sf.json.*;
//import java.io.IOException;
//import java.util.Arrays;
//import java.util.HashSet;
//import java.util.Set;
public class Main {
    public static void main(String[] args) {

        Scuola school = Scuola.loadData("Registro.txt");

        if (school == null) {
            school = new Scuola();
        }

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("---GESTIONE SCUOLA---");
            System.out.println("1. Aggiungi studenti");
            System.out.println("2. Aggiungi voti");
            System.out.println("3. Visualizza studenti");
            System.out.println("4. Modifica");
            System.out.println("5. Cancellazione");
            System.out.println("6. Salva ed Esci");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    System.out.println("Nome dello studente:");
                    String name = scanner.nextLine();
                    System.out.println("Classe:");
                    String className = scanner.nextLine();
                    school.addStudent(new Studenti(name, className));
                    System.out.println("\nStudente aggiunto");
                    break;
                case 2:
                    System.out.println("Nome dello studente:");
                    String studentName = scanner.nextLine();
                    System.out.println("Materia:");
                    String subject = scanner.nextLine();
                    System.out.println("Voto:");
                    int grade = scanner.nextInt();
                    scanner.nextLine(); // Consume newline character

                    Studenti foundStudent = null;
                    for (Studenti student : school.students) {
                        if (student.getName().equals(studentName)) {
                            foundStudent = student;
                            break;
                        }
                    }

                    if (foundStudent != null) {
                        school.addGrade(foundStudent, new Voti(subject, grade));
                        System.out.println("\nVoto aggiunto");
                    } else {
                        System.out.println("\nStudente non trovato");
                    }
                    break;
                case 3:
                    school.displayStudents();
                    break;
                case 4:
                    System.out.println("---GESTIONE MODIFICA---");
                    System.out.println("1. Modifica nome o classe");
                    System.out.println("2. Modifica voti");
                    int opz = scanner.nextInt();
                    scanner.nextLine();

                    switch (opz) {
                        case 1:
                            System.out.println("Nome dello studente da modificare:");
                            name = scanner.nextLine();
                            System.out.println("Classe:");
                            className = scanner.nextLine();
                            Studenti vecchio = (new Studenti(name, className));

                            System.out.println("Nuovo nome dello studente:");
                            String newName = scanner.nextLine();
                            System.out.println("Classe:");
                            String newClassName = scanner.nextLine();
                            Studenti nuovo = (new Studenti(newName, newClassName));

                            school.modifyStudent(vecchio, nuovo);
                            break;

                        case 2:
                            System.out.println("Nome dello studente:");
                            studentName = scanner.nextLine();
                            System.out.println("Materia:");
                            String subject2 = scanner.nextLine();
                            System.out.println("Voto:");
                            int grade2 = scanner.nextInt();
                            scanner.nextLine();

                            System.out.println("Nuova materia:");
                            String newSubject = scanner.nextLine();
                            System.out.println("Nuovo voto:");
                            int newGrade = scanner.nextInt();
                            scanner.nextLine();
                            Voti newGrades = new Voti(newSubject, newGrade);


                            foundStudent = null;
                            for (Studenti student : school.students) {
                                if (student.getName().equals(studentName)) {
                                    foundStudent = student;
                                    break;
                                }
                            }
                            if (foundStudent != null) {
                                Voti oldGrades = new Voti(subject2, grade2);
                                school.removeGrade(foundStudent, oldGrades);
                                school.addGrade(foundStudent, newGrades);
                                System.out.println("\nVoto modificato");
                            } else {
                                System.out.println("\nStudente non trovato");
                            }
                            break;
                    }
                    break;
                case 5:
                    System.out.println("---GESTIONE CANCELLAZIONE---");
                    System.out.println("1. Cancella studenti");
                    System.out.println("2. Cancella voti");
                    opz = scanner.nextInt();
                    scanner.nextLine();

                    switch (opz) {
                        case 1:
                            System.out.println("Nome dello studente:");
                            studentName = scanner.nextLine();

                            //foundStudent = null;
                            for (Studenti student : school.students) {
                                if (student.getName().equals(studentName)) {
                                    school.removeStudent(student);
                                    System.out.println("\nStudente rimosso");
                                    break;
                                }
                            }
                            break;
                        case 2:
                            System.out.println("Nome dello studente:");
                            studentName = scanner.nextLine();
                            System.out.println("Materia:");
                            subject = scanner.nextLine();
                            System.out.println("Voto:");
                            grade = scanner.nextInt();
                            scanner.nextLine(); // Consume newline character

                            foundStudent = null;
                            for (Studenti student : school.students) {
                                if (student.getName().equals(studentName)) {
                                    foundStudent = student;
                                    break;
                                }
                            }

                            if (foundStudent != null) {
                                school.removeGrade(foundStudent, new Voti(subject, grade));
                                System.out.println("\nVoto cancellato");
                            } else {
                                System.out.println("\nStudente non trovato");
                            }
                            break;
                    }
                    break;
                case 6:
                    /*try {
                        FileOutputStream outFile = new FileOutputStream("/RegistroElettronico.txt");
                        OutputStreamWriter uscita = new OutputStreamWriter(outFile);
                        BufferedWriter buffUscita = new BufferedWriter(uscita);

                        buffUscita.write(school.displayStudents());
                        buffUscita.close(); // chiude il file

                    }
                    catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;*/



                    school.saveData("Registro.txt");
                    exit = true;
                    break;
                default:
                    System.out.println("Opzione non esistente");
                    break;
            }

                    System.out.println();
            }
        }
    }
