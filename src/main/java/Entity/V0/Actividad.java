package Entity.V0;


public class Actividad {

    // Atributos

    int nivel;
    int puntos;
    String date;

    //Constructores

    public Actividad() {} // Constructor vacio

    public Actividad(int nivel, int puntos, String date) {
        this.nivel = nivel;
        this.puntos = puntos;
        this.date = date;
    }

    //Getter and Setters

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    //Funciones

    public void sumarPuntos (int puntos){
        this.puntos=this.puntos+puntos;
    }
    public void sumarNivel (int nivel){
        this.nivel=
    }
}
