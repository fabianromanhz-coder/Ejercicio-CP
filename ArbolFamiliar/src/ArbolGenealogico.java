public class ArbolGenealogico {

        private NodoFamilia raiz;

        private class NodoFamilia {
            Persona persona;
            NodoFamilia[] hijos;
            int numHijos;
            int capacidad;

            public NodoFamilia(Persona persona, int maxHijos) {
                this.persona = persona;
                this.capacidad = maxHijos;
                this.hijos = new NodoFamilia[maxHijos];
                this.numHijos = 0;
            }

            public boolean agregarHijo(NodoFamilia hijo) {
                if (numHijos < capacidad) {
                    hijos[numHijos] = hijo;
                    numHijos++;
                    return true;
                }
                return false;
            }

            public String getNombre() {
                return persona.getNombre();
            }

            public Persona getPersona() {
                return persona;
            }
        }


        public ArbolGenealogico() {
            this.raiz = null;
        }

        public boolean agregarRaiz(String nombre, String fechaNacimiento, char genero, int maxHijos) {
            if (raiz != null) {
                System.out.println("  existe una raíz en el árbol");
                return false;
            }

            Persona persona = new Persona(nombre, fechaNacimiento, genero);
            raiz = new NodoFamilia(persona, maxHijos);
            System.out.println(" Raíz establecida: " + persona.obtenerInfo());
            return true;
        }

        public boolean agregarHijo(String nombrePadre, String nombreHijo,
                                   String fechaHijo, char generoHijo, int maxHijos) {
            if (raiz == null) {
                System.out.println(" El árbol está vacío.  agregue la raíz.");
                return false;
            }


            NodoFamilia padre = buscarNodo(raiz, nombrePadre);

            if (padre == null) {
                System.out.println(" No se encontró a '" + nombrePadre + "' en el árbol");
                return false;
            }


            Persona nuevaPersona = new Persona(nombreHijo, fechaHijo, generoHijo);
            NodoFamilia nuevoHijo = new NodoFamilia(nuevaPersona, maxHijos);

            boolean resultado = padre.agregarHijo(nuevoHijo);

            if (resultado) {
                System.out.println("  ✓ " + nombreHijo + " agregado como hijo de " + nombrePadre);
            } else {
                System.out.println(  nombrePadre + " ya tiene el máximo de hijos (" +
                        padre.capacidad + ")");
            }

            return resultado;
        }

        private NodoFamilia buscarNodo(NodoFamilia actual, String nombre) {
            if (actual == null) {
                return null;
            }

            if (actual.getNombre().equals(nombre)) {
                return actual;
            }

            for (int i = 0; i < actual.numHijos; i++) {
                NodoFamilia encontrado = buscarNodo(actual.hijos[i], nombre);
                if (encontrado != null) {
                    return encontrado;
                }
            }

            return null;
        }

        public void mostrarAntepasados(String nombre) {


            if (raiz == null) {
                System.out.println("El árbol está vacío");
                return;
            }


            NodoFamilia persona = buscarNodo(raiz, nombre);

            if (persona == null) {
                System.out.println(" No se encontró a '" + nombre + "' en el árbol");
                return;
            }

            System.out.println(" Persona encontrada: " + persona.persona.obtenerInfo());
            System.out.println(" Línea de antepasados:");


            java.util.ArrayList<NodoFamilia> antepasados = new java.util.ArrayList<>();
            boolean encontrado = buscarCamino(raiz, nombre, antepasados);

            if (encontrado && antepasados.size() > 1) {

                for (int i = 0; i < antepasados.size() - 1; i++) {
                    NodoFamilia antepasado = antepasados.get(i);
                    int generacion = antepasados.size() - 1 - i;

                    String titulo;
                    switch (generacion) {
                        case 1: titulo = "Padre/Madre"; break;
                        case 2: titulo = "Abuelo/a"; break;
                        case 3: titulo = "Bisabuelo/a"; break;
                        case 4: titulo = "Tatarabuelo/a"; break;
                        default: titulo = generacion + "ª generación arriba";
                    }

                    System.out.println("  " + titulo + ": " + antepasado.persona.obtenerInfo());
                }
            } else if (persona == raiz) {
                System.out.println("  " + nombre + " es la raíz del árbol, no tiene antepasados");
            } else {
                System.out.println("  Error al buscar la línea de antepasados");
            }
        }

        private boolean buscarCamino(NodoFamilia actual, String nombre,
                                     java.util.ArrayList<NodoFamilia> camino) {
            if (actual == null) {
                return false;
            }


            camino.add(actual);

            if (actual.getNombre().equals(nombre)) {
                return true;
            }


            for (int i = 0; i < actual.numHijos; i++) {
                if (buscarCamino(actual.hijos[i], nombre, camino)) {
                    return true;
                }
            }


            camino.remove(camino.size() - 1);
            return false;
        }


        public void mostrarDescendientes(String nombre) {
            System.out.println(" DESCENDIENTES DE: " + nombre  );

            NodoFamilia persona = buscarNodo(raiz, nombre);

            if (persona == null) {
                System.out.println("No se encontró a '" + nombre + "' en el árbol");
                return;
            }

            if (persona.numHijos == 0) {
                System.out.println(nombre + " no tiene descendientes");
                return;
            }


            LinkedQue<NodoFamilia> cola = new LinkedQue<>();
            LinkedQue<Integer> niveles = new LinkedQue<>();

            cola.add(persona);
            niveles.add(0);

            int generacionActual = -1;

            while (!cola.isEmpty()) {
                NodoFamilia actual = cola.poll();
                int nivel = niveles.poll();

                if (nivel > generacionActual) {
                    generacionActual = nivel;
                    if (nivel == 0) {
                        System.out.println(" " + actual.getNombre() + " (ella/él mismo):");
                    } else {
                        String titulo;
                        switch (nivel) {
                            case 1: titulo = "HIJOS"; break;
                            case 2: titulo = "NIETOS"; break;
                            case 3: titulo = "BISNIETOS"; break;
                            case 4: titulo = "TATARANIETOS"; break;
                            default: titulo = nivel + "ª GENERACIÓN";
                        }
                        System.out.println(  titulo );
                    }
                }

                for (int i = 0; i < actual.numHijos; i++) {
                    NodoFamilia hijo = actual.hijos[i];
                    System.out.println("  • " + hijo.persona.obtenerInfo());
                    cola.add(hijo);
                    niveles.add(nivel + 1);
                }
            }
        }


        public boolean eliminarRama(String nombre) {
            System.out.println(" ELIMINANDO RAMA FAMILIAR: " + nombre  );

            if (raiz == null) {
                System.out.println("El árbol está vacío");
                return false;
            }


            if (raiz.getNombre().equals(nombre)) {
                System.out.println("Se eliminará TODO el árbol genealógico");
                raiz = null;
                System.out.println(" Árbol eliminado completamente");
                return true;
            }

            NodoFamilia padre = buscarPadre(raiz, nombre);

            if (padre == null) {
                System.out.println("No se encontró a '" + nombre + "' en el árbol");
                return false;
            }


            int indiceEliminar = -1;
            for (int i = 0; i < padre.numHijos; i++) {
                if (padre.hijos[i].getNombre().equals(nombre)) {
                    indiceEliminar = i;
                    break;
                }
            }

            if (indiceEliminar == -1) {
                System.out.println(" Error ");
                return false;
            }


            int totalEliminados = contarDescendientes(padre.hijos[indiceEliminar]) + 1;


            for (int i = indiceEliminar; i < padre.numHijos - 1; i++) {
                padre.hijos[i] = padre.hijos[i + 1];
            }
            padre.hijos[padre.numHijos - 1] = null;
            padre.numHijos--;

            System.out.println(" Se eliminó a '" + nombre + "' y sus " +
                    (totalEliminados - 1) + " descendientes");
            return true;
        }


        private NodoFamilia buscarPadre(NodoFamilia actual, String nombreHijo) {
            if (actual == null) {
                return null;
            }


            for (int i = 0; i < actual.numHijos; i++) {
                if (actual.hijos[i].getNombre().equals(nombreHijo)) {
                    return actual;
                }
            }


            for (int i = 0; i < actual.numHijos; i++) {
                NodoFamilia encontrado = buscarPadre(actual.hijos[i], nombreHijo);
                if (encontrado != null) {
                    return encontrado;
                }
            }

            return null;
        }

        private int contarDescendientes(NodoFamilia nodo) {
            if (nodo == null) {
                return 0;
            }

            int total = 0;
            for (int i = 0; i < nodo.numHijos; i++) {
                total += 1 + contarDescendientes(nodo.hijos[i]);
            }
            return total;
        }


        public int calcularProfundidad() {
            if (raiz == null) {
                return 0;
            }
            return calcularProfundidadRec(raiz, 1);
        }

        private int calcularProfundidadRec(NodoFamilia nodo, int nivelActual) {
            if (nodo == null) {
                return nivelActual - 1;
            }

            if (nodo.numHijos == 0) {
                return nivelActual;
            }

            int maxProfundidad = nivelActual;
            for (int i = 0; i < nodo.numHijos; i++) {
                int profHijo = calcularProfundidadRec(nodo.hijos[i], nivelActual + 1);
                if (profHijo > maxProfundidad) {
                    maxProfundidad = profHijo;
                }
            }
            return maxProfundidad;
        }


        public void mostrarProfundidad() {
            System.out.println(" PROFUNDIDAD DEL ÁRBOL GENEALÓGICO ");
            int profundidad = calcularProfundidad();
            System.out.println("El árbol tiene " + profundidad + " generaciones");

            if (profundidad > 0) {
                System.out.println("  • Generación 1: Ancestro más antiguo");
                System.out.println("  • Generación " + profundidad + ": Descendiente más joven");
            }
        }


        public void recorridoPorGeneraciones() {
            System.out.println("RECORRIDO POR GENERACIONES ");

            if (raiz == null) {
                System.out.println("El árbol está vacío");
                return;
            }

            LinkedQue<NodoFamilia> cola = new LinkedQue<>();
            cola.add(raiz);

            int generacion = 1;

            while (!cola.isEmpty()) {
                int personasEnGeneracion = cola.size();
                System.out.print(" GENERACIÓN " + generacion + ": ");

                for (int i = 0; i < personasEnGeneracion; i++) {
                    NodoFamilia actual = cola.poll();

                    if (i > 0) {
                        System.out.print(" | ");
                    }
                    System.out.print(actual.persona.obtenerNombre());


                    for (int j = 0; j < actual.numHijos; j++) {
                        cola.add(actual.hijos[j]);
                    }
                }
                generacion++;
            }
            System.out.println();
        }


        public void mostrarArbolCompleto() {
            System.out.println(" ÁRBOL GENEALÓGICO COMPLETO ");
            mostrarRecursivo(raiz, 0);
        }

        private void mostrarRecursivo(NodoFamilia nodo, int nivel) {
            if (nodo != null) {
                for (int i = 0; i < nivel; i++) {
                    System.out.print("  ");
                }
                System.out.println(  nodo.persona.obtenerInfo());

                for (int i = 0; i < nodo.numHijos; i++) {
                    mostrarRecursivo(nodo.hijos[i], nivel + 1);
                }
            }
        }
    }





