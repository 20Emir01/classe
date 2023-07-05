import java.io.Serializable;
import java.util.Objects;

class Studenti implements Serializable {
    private String name;
    private String className;

    public Studenti(String name, String className) {
        this.name = name;
        this.className = className;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Studenti other = (Studenti) obj;
        return name.equals(other.name);
    }

    // Override del metodo hashCode() per garantire coerenza con equals()
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public String getName() {
        return name;
    }
    public String getClassName() {
        return className;
    }
    public void setName(String name) { this.name = name; }
    public void setClassName(String className) { this.className = className; }

    @Override
    public String toString() {
        return "Studente: " + name + " (Classe: " + className + ")";
    }
}

