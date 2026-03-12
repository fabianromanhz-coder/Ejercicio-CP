public class ArbolTorneo {
    private NodoTorneo raiz;
    private LinkedStack<String> pilaCampeones;
    private int numeroTorneo;

    public ArbolTorneo(String[] equipos, int numeroTorneo) {
        if (equipos.length != 8) {
            System.out.println("Error: Se necesitan 8 equipos");
            return;
        }

        this.numeroTorneo = numeroTorneo;
        this.pilaCampeones = new LinkedStack<>();

        NodoTorneo qf1 = new NodoTorneo(equipos[0], equipos[1]);
        NodoTorneo qf2 = new NodoTorneo(equipos[2], equipos[3]);
        NodoTorneo qf3 = new NodoTorneo(equipos[4], equipos[5]);
        NodoTorneo qf4 = new NodoTorneo(equipos[6], equipos[7]);

        NodoTorneo sf1 = new NodoTorneo(qf1, qf2, "Semifinal");
        NodoTorneo sf2 = new NodoTorneo(qf3, qf4, "Semifinal");

        this.raiz = new NodoTorneo(sf1, sf2, "Final");

        System.out.println("Torneo #" + numeroTorneo + " creado");
    }

    public void simularTorneo() {
        System.out.println("\nSIMULACIÓN DEL TORNEO #" + numeroTorneo);

        System.out.println("\nCUARTOS DE FINAL:");
        simularCuartos(raiz.getIzquierdo().getIzquierdo());
        simularCuartos(raiz.getIzquierdo().getDerecho());
        simularCuartos(raiz.getDerecho().getIzquierdo());
        simularCuartos(raiz.getDerecho().getDerecho());

        System.out.println("\nSEMIFINALES:");
        simularSemifinal(raiz.getIzquierdo());
        simularSemifinal(raiz.getDerecho());

        System.out.println("\nFINAL:");
        simularFinal(raiz);

        String campeon = raiz.getGanador();
        pilaCampeones.Push(campeon);
        System.out.println("\nCampeón guardado: " + campeon);
    }

    private void simularCuartos(NodoTorneo nodo) {
        double random = Math.random();
        String ganador = (random < 0.5) ? nodo.getEquipo1() : nodo.getEquipo2();
        nodo.setGanador(ganador);
        System.out.println("  " + nodo);
    }

    private void simularSemifinal(NodoTorneo nodo) {
        String ganador1 = nodo.getIzquierdo().getGanador();
        String ganador2 = nodo.getDerecho().getGanador();

        nodo.setEquipo1(ganador1);
        nodo.setEquipo2(ganador2);

        double random = Math.random();
        String ganador = (random < 0.5) ? ganador1 : ganador2;
        nodo.setGanador(ganador);

        System.out.println("  " + nodo);
    }

    private void simularFinal(NodoTorneo nodo) {
        String ganador1 = nodo.getIzquierdo().getGanador();
        String ganador2 = nodo.getDerecho().getGanador();

        nodo.setEquipo1(ganador1);
        nodo.setEquipo2(ganador2);

        double random = Math.random();
        String ganador = (random < 0.5) ? ganador1 : ganador2;
        nodo.setGanador(ganador);

        System.out.println("  " + nodo);
    }

    public String getCampeon() {
        if (raiz != null && raiz.getGanador() != null) {
            return raiz.getGanador();
        }
        return "Torneo no jugado";
    }

    public void mostrarPorRondas() {
        System.out.println("\nRESULTADOS TORNEO #" + numeroTorneo + "\n");

        System.out.println("CUARTOS:");
        mostrarPartido(raiz.getIzquierdo().getIzquierdo());
        mostrarPartido(raiz.getIzquierdo().getDerecho());
        mostrarPartido(raiz.getDerecho().getIzquierdo());
        mostrarPartido(raiz.getDerecho().getDerecho());

        System.out.println("\nSEMIFINALES:");
        mostrarPartido(raiz.getIzquierdo());
        mostrarPartido(raiz.getDerecho());

        System.out.println("\nFINAL:");
        mostrarPartido(raiz);

        System.out.println("\nCAMPEÓN: " + getCampeon());
    }

    private void mostrarPartido(NodoTorneo nodo) {
        if (nodo.getGanador() != null) {
            System.out.println("  " + nodo.getRonda() + ": " +
                    nodo.getEquipo1() + " vs " + nodo.getEquipo2() +
                    " → Ganador: " + nodo.getGanador());
        }
    }
}