import java.io.Serializable;
import java.util.Objects;

class Voti implements Serializable {
    private String subject;
    private int grade;

    public Voti(String subject, int grade) {
        this.subject = subject;
        this.grade = grade;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Voti other = (Voti) obj;
        return grade == other.grade && subject.equals(other.subject);
    }

    // Override del metodo hashCode() per garantire coerenza con equals()
    @Override
    public int hashCode() {
        return Objects.hash(subject, grade);
    }

    public String getSubject() {
        return subject;
    }

    public int getGrade() {
        return grade;
    }

    @Override
    public String toString() {
        return "Materia: " + subject + " (Voto: " + grade + ")";
    }
}