public class NodoTorneo {
    private String equipo1;
    private String equipo2;
    private String ganador;
    private String ronda;

    private NodoTorneo izquierdo;
    private NodoTorneo derecho;

    public NodoTorneo(String equipo1, String equipo2) {
        this.equipo1 = equipo1;
        this.equipo2 = equipo2;
        this.ganador = null;
        this.ronda = "Cuartos";
        this.izquierdo = null;
        this.derecho = null;
    }

    public NodoTorneo(NodoTorneo izquierdo, NodoTorneo derecho, String ronda) {
        this.izquierdo = izquierdo;
        this.derecho = derecho;
        this.equipo1 = null;
        this.equipo2 = null;
        this.ganador = null;
        this.ronda = ronda;
    }

    public String getEquipo1() { return equipo1; }
    public void setEquipo1(String equipo1) { this.equipo1 = equipo1; }

    public String getEquipo2() { return equipo2; }
    public void setEquipo2(String equipo2) { this.equipo2 = equipo2; }

    public String getGanador() { return ganador; }
    public void setGanador(String ganador) { this.ganador = ganador; }

    public String getRonda() { return ronda; }

    public NodoTorneo getIzquierdo() { return izquierdo; }
    public NodoTorneo getDerecho() { return derecho; }

    public String getEquipos() {
        if (equipo1 != null && equipo2 != null) {
            return equipo1 + " vs " + equipo2;
        }
        return "Por definir vs Por definir";
    }

    @Override
    public String toString() {
        if (ganador != null) {
            return ronda + ": " + getEquipos() + " → Ganador: " + ganador;
        }
        return ronda + ": " + getEquipos() + " → No jugado";
    }
}