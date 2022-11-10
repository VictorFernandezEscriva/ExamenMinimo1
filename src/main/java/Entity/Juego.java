package Entity;

public class Juego {

    // Atributos

    String juegoId;
    String juegoDescription;
    int nivelMaximo;

    //Constructores

    public Juego(){} // Constructor vacio

    public Juego(String juegoId, String juegoDescription, int nivelMaximo) {
        this.juegoId = juegoId;
        this.juegoDescription = juegoDescription;
        this.nivelMaximo = nivelMaximo;
    }

    //Getter and Setters

    public String getJuegoId() {
        return juegoId;
    }

    public void setJuegoId(String juegoId) {
        this.juegoId = juegoId;
    }

    public String getJuegoDescription() {
        return juegoDescription;
    }

    public void setJuegoDescription(String juegoDescription) {
        this.juegoDescription = juegoDescription;
    }

    public int getNivelMaximo() {
        return nivelMaximo;
    }

    public void setNivelMaximo(int nivelMaximo) {
        this.nivelMaximo = nivelMaximo;
    }
}
