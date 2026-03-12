//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

                System.out.println("==================================================");
                System.out.println("🌳 SISTEMA DE GESTIÓN DE ÁRBOL GENEALÓGICO");
                System.out.println("==================================================\n");

                // Crear el árbol genealógico
                ArbolGenealogico familia = new ArbolGenealogico();

                // ==========================================================
                // 1. CREACIÓN DEL ÁRBOL
                // ==========================================================
                System.out.println("--- CREANDO EL ÁRBOL GENEALÓGICO ---\n");

                // Generación 1: El patriarca
                familia.agregarRaiz("Carlos Ruiz", "15/03/1950", 'M', 3);

                // Generación 2: Hijos de Carlos
                familia.agregarHijo("Carlos Ruiz", "Ana Ruiz", "20/07/1975", 'F', 2);
                familia.agregarHijo("Carlos Ruiz", "Luis Ruiz", "10/11/1978", 'M', 3);
                familia.agregarHijo("Carlos Ruiz", "Marta Ruiz", "05/02/1982", 'F', 2);

                // Generación 3: Hijos de Ana
                familia.agregarHijo("Ana Ruiz", "Pedro Ruiz", "12/04/2000", 'M', 0);
                familia.agregarHijo("Ana Ruiz", "Sofía Ruiz", "25/09/2003", 'F', 0);

                // Generación 3: Hijos de Luis
                familia.agregarHijo("Luis Ruiz", "Diego Ruiz", "30/06/2005", 'M', 0);
                familia.agregarHijo("Luis Ruiz", "Valentina Ruiz", "18/01/2008", 'F', 0);
                familia.agregarHijo("Luis Ruiz", "Javier Ruiz", "22/11/2010", 'M', 0);

                // Generación 3: Hijos de Marta
                familia.agregarHijo("Marta Ruiz", "Camila Ruiz", "07/03/2012", 'F', 0);
                familia.agregarHijo("Marta Ruiz", "Nicolás Ruiz", "19/08/2015", 'M', 0);

                // Generación 4: Hijos de Diego (para hacer más profundo el árbol)
                familia.agregarHijo("Diego Ruiz", "Lucía Ruiz", "10/05/2030", 'F', 0);

                System.out.println("\n--- ÁRBOL CREADO CORRECTAMENTE ---");

                // ==========================================================
                // MOSTRAR ÁRBOL COMPLETO
                // ==========================================================
                familia.mostrarArbolCompleto();

                // ==========================================================
                // 6. RECORRIDO POR GENERACIONES (BFS)
                // ==========================================================
                familia.recorridoPorGeneraciones();

                // ==========================================================
                // 5. PROFUNDIDAD DEL ÁRBOL
                // ==========================================================
                familia.mostrarProfundidad();

                // ==========================================================
                // 2. BÚSQUEDA DE ANTEPASADOS
                // ==========================================================
                familia.mostrarAntepasados("Diego Ruiz");
                familia.mostrarAntepasados("Lucía Ruiz");
                familia.mostrarAntepasados("Carlos Ruiz");  // Raíz
                familia.mostrarAntepasados("Persona Inexistente");

                // ==========================================================
                // 3. LISTADO DE DESCENDIENTES
                // ==========================================================
                familia.mostrarDescendientes("Luis Ruiz");
                familia.mostrarDescendientes("Ana Ruiz");
                familia.mostrarDescendientes("Diego Ruiz");  // Tiene 1 hija

                // ==========================================================
                // 4. ELIMINACIÓN DE UNA RAMA
                // ==========================================================
                System.out.println("\n==================================================");
                System.out.println("🧪 PRUEBA DE ELIMINACIÓN");
                System.out.println("==================================================");

                familia.eliminarRama("Marta Ruiz");

                System.out.println("\n--- ÁRBOL DESPUÉS DE ELIMINAR A MARTA Y SUS HIJOS ---");
                familia.mostrarArbolCompleto();

                // Verificar profundidad después de eliminar
                familia.mostrarProfundidad();

                // Intentar eliminar a alguien que ya no existe
                familia.eliminarRama("Camila Ruiz");

                System.out.println("\n==================================================");
                System.out.println("✅ FIN DEL PROGRAMA");
                System.out.println("==================================================");
            }
        }


