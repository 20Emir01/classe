public class Voti implements Studenti{

    private int voto;
    private String colore;
    private String materia;

    Voti(){
    voto=0;
    colore="";
    materia="";
}
    public String getColore() {
        return colore;
    }

    public void setColore(String colore) {
        this.colore = colore;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }
    public int getVoto() {
        return voto;
    }

    public void setVoto(int voto) {
        this.voto = voto;
    }
}