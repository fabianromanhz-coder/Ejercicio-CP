public class Main {
    public static void main(String[] args) {

        System.out.println("SIMULADOR DE TORNEO");

        String[] equipos = {
                "Brasil", "Argentina", "Francia", "Alemania",
                "España", "Italia", "Inglaterra", "Países Bajos"
        };

        System.out.println("EQUIPOS PARTICIPANTES:");
        for (int i = 0; i < equipos.length; i++) {
            System.out.println("  " + (i+1) + ". " + equipos[i]);
        }

        LinkedStack<String> pilaGlobal = new LinkedStack<>();

        int numTorneos = 5;
        ArbolTorneo[] torneos = new ArbolTorneo[numTorneos];

        for (int i = 0; i < numTorneos; i++) {
            System.out.println(" TORNEO #" + (i+1));

            torneos[i] = new ArbolTorneo(equipos, i+1);
            torneos[i].simularTorneo();

            String campeon = torneos[i].getCampeon();
            pilaGlobal.Push(campeon);
        }

        String cima = pilaGlobal.Top();
        System.out.println("   La cima de la pila es: " + cima);
        System.out.println("   (Este fue el último campeón en agg al torneo #" + numTorneos + ")");


        System.out.println("   Los campeones en orden inverso:");
        LinkedStack<String> pilaTemp = new LinkedStack<>();
        int pos = 1;

        while (!pilaGlobal.isEmpty()) {
            String campeon = pilaGlobal.Pop();
            System.out.println("#" + pos + ": " + campeon);
            pilaTemp.Push(campeon);
            pos++;
        }

        while (!pilaTemp.isEmpty()) {
            String campeon = pilaTemp.Pop();
            pilaGlobal.Push(campeon);
        }

        System.out.println("RESULTADOS DETALLADOS DE CADA TORNEO");

        for (int i = 0; i < numTorneos; i++) {
            torneos[i].mostrarPorRondas();
        }
    }
}