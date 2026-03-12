
    /**
     * Árbol general que representa una genealogía familiar
     * Cada persona puede tener múltiples hijos
     */
    public class ArbolGenealogico {
        // ATRIBUTO
        private NodoFamilia raiz;  // El ancestro más antiguo

        /**
         * Clase interna para los nodos del árbol genealógico
         */
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

        /**
         * Constructor: árbol vacío
         */
        public ArbolGenealogico() {
            this.raiz = null;
        }

        // ==========================================================
        // 1. CREACIÓN DEL ÁRBOL - Agregar personas
        // ==========================================================

        /**
         * Agrega la primera persona (ancestro más antiguo)
         * @param nombre Nombre de la persona
         * @param fechaNacimiento Fecha de nacimiento
         * @param genero Género
         * @param maxHijos Máximo de hijos que puede tener
         * @return true si se creó correctamente
         */
        public boolean agregarRaiz(String nombre, String fechaNacimiento, char genero, int maxHijos) {
            if (raiz != null) {
                System.out.println("❌ Ya existe una raíz en el árbol");
                return false;
            }

            Persona persona = new Persona(nombre, fechaNacimiento, genero);
            raiz = new NodoFamilia(persona, maxHijos);
            System.out.println("🌳 Raíz establecida: " + persona.obtenerInfo());
            return true;
        }

        /**
         * Agrega una persona como hijo de otra
         * @param nombrePadre Nombre del padre/madre
         * @param nombreHijo Nombre del hijo/a
         * @param fechaHijo Fecha de nacimiento del hijo
         * @param generoHijo Género del hijo
         * @param maxHijos Máximo de hijos que puede tener esta persona
         * @return true si se agregó correctamente
         */
        public boolean agregarHijo(String nombrePadre, String nombreHijo,
                                   String fechaHijo, char generoHijo, int maxHijos) {
            if (raiz == null) {
                System.out.println("❌ El árbol está vacío. Primero agregue la raíz.");
                return false;
            }

            // Buscar al padre
            NodoFamilia padre = buscarNodo(raiz, nombrePadre);

            if (padre == null) {
                System.out.println("❌ No se encontró a '" + nombrePadre + "' en el árbol");
                return false;
            }

            // Crear la nueva persona
            Persona nuevaPersona = new Persona(nombreHijo, fechaHijo, generoHijo);
            NodoFamilia nuevoHijo = new NodoFamilia(nuevaPersona, maxHijos);

            // Agregar como hijo
            boolean resultado = padre.agregarHijo(nuevoHijo);

            if (resultado) {
                System.out.println("  ✓ " + nombreHijo + " agregado como hijo de " + nombrePadre);
            } else {
                System.out.println("  ❌ " + nombrePadre + " ya tiene el máximo de hijos (" +
                        padre.capacidad + ")");
            }

            return resultado;
        }

        /**
         * Busca un nodo por nombre (recursivo)
         * @param actual Nodo actual
         * @param nombre Nombre a buscar
         * @return Nodo encontrado o null
         */
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

        // ==========================================================
        // 2. BÚSQUEDA DE ANTEPASADOS (padres, abuelos, etc.)
        // ==========================================================

        /**
         * Busca una persona y muestra su línea de antepasados
         * @param nombre Nombre de la persona a buscar
         */
        public void mostrarAntepasados(String nombre) {
            System.out.println("\n🔍 === BUSCANDO ANTEPASADOS DE: " + nombre + " ===");

            if (raiz == null) {
                System.out.println("El árbol está vacío");
                return;
            }

            // Buscar la persona
            NodoFamilia persona = buscarNodo(raiz, nombre);

            if (persona == null) {
                System.out.println("❌ No se encontró a '" + nombre + "' en el árbol");
                return;
            }

            System.out.println("✓ Persona encontrada: " + persona.persona.obtenerInfo());
            System.out.println("\n📜 Línea de antepasados:");

            // Buscar antepasados (necesitamos encontrar el camino desde la raíz)
            java.util.ArrayList<NodoFamilia> antepasados = new java.util.ArrayList<>();
            boolean encontrado = buscarCamino(raiz, nombre, antepasados);

            if (encontrado && antepasados.size() > 1) {
                // Mostrar desde el más antiguo hasta el padre/madre
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

        /**
         * Busca el camino desde la raíz hasta una persona (para antepasados)
         * @param actual Nodo actual
         * @param nombre Nombre a buscar
         * @param camino Lista para guardar el camino
         * @return true si se encontró
         */
        private boolean buscarCamino(NodoFamilia actual, String nombre,
                                     java.util.ArrayList<NodoFamilia> camino) {
            if (actual == null) {
                return false;
            }

            // Agregar nodo actual al camino
            camino.add(actual);

            if (actual.getNombre().equals(nombre)) {
                return true;
            }

            // Buscar en hijos
            for (int i = 0; i < actual.numHijos; i++) {
                if (buscarCamino(actual.hijos[i], nombre, camino)) {
                    return true;
                }
            }

            // Si no se encontró, quitar el nodo actual del camino
            camino.remove(camino.size() - 1);
            return false;
        }

        // ==========================================================
        // 3. LISTADO DE DESCENDIENTES (por generaciones)
        // ==========================================================

        /**
         * Muestra todos los descendientes de una persona, organizados por generaciones
         * @param nombre Nombre de la persona
         */
        public void mostrarDescendientes(String nombre) {
            System.out.println("\n👪 === DESCENDIENTES DE: " + nombre + " ===");

            NodoFamilia persona = buscarNodo(raiz, nombre);

            if (persona == null) {
                System.out.println("❌ No se encontró a '" + nombre + "' en el árbol");
                return;
            }

            if (persona.numHijos == 0) {
                System.out.println(nombre + " no tiene descendientes");
                return;
            }

            // Usar BFS para mostrar por generaciones
            LinkedQue<NodoFamilia> cola = new LinkedQue<>();
            LinkedQue<Integer> niveles = new LinkedQue<>();

            cola.add(persona);
            niveles.add(0);  // Nivel 0 = la persona misma

            int generacionActual = -1;

            while (!cola.isEmpty()) {
                NodoFamilia actual = cola.poll();
                int nivel = niveles.poll();

                if (nivel > generacionActual) {
                    generacionActual = nivel;
                    if (nivel == 0) {
                        System.out.println("\n📌 " + actual.getNombre() + " (ella/él mismo):");
                    } else {
                        String titulo;
                        switch (nivel) {
                            case 1: titulo = "HIJOS"; break;
                            case 2: titulo = "NIETOS"; break;
                            case 3: titulo = "BISNIETOS"; break;
                            case 4: titulo = "TATARANIETOS"; break;
                            default: titulo = nivel + "ª GENERACIÓN";
                        }
                        System.out.println("\n--- " + titulo + " ---");
                    }
                }

                // Mostrar los hijos del nodo actual
                for (int i = 0; i < actual.numHijos; i++) {
                    NodoFamilia hijo = actual.hijos[i];
                    System.out.println("  • " + hijo.persona.obtenerInfo());
                    cola.add(hijo);
                    niveles.add(nivel + 1);
                }
            }
        }

        // ==========================================================
        // 4. ELIMINACIÓN DE UNA RAMA FAMILIAR
        // ==========================================================

        /**
         * Elimina una persona y todos sus descendientes
         * @param nombre Nombre de la persona a eliminar
         * @return true si se eliminó correctamente
         */
        public boolean eliminarRama(String nombre) {
            System.out.println("\n🗑️ === ELIMINANDO RAMA FAMILIAR: " + nombre + " ===");

            if (raiz == null) {
                System.out.println("El árbol está vacío");
                return false;
            }

            // Caso especial: eliminar la raíz
            if (raiz.getNombre().equals(nombre)) {
                System.out.println("Se eliminará TODO el árbol genealógico");
                raiz = null;
                System.out.println("✓ Árbol eliminado completamente");
                return true;
            }

            // Buscar al padre de la persona a eliminar
            NodoFamilia padre = buscarPadre(raiz, nombre);

            if (padre == null) {
                System.out.println("❌ No se encontró a '" + nombre + "' en el árbol");
                return false;
            }

            // Encontrar el índice del hijo a eliminar
            int indiceEliminar = -1;
            for (int i = 0; i < padre.numHijos; i++) {
                if (padre.hijos[i].getNombre().equals(nombre)) {
                    indiceEliminar = i;
                    break;
                }
            }

            if (indiceEliminar == -1) {
                System.out.println("❌ Error inesperado");
                return false;
            }

            // Contar cuántos descendientes se eliminarán (para información)
            int totalEliminados = contarDescendientes(padre.hijos[indiceEliminar]) + 1;

            // Eliminar: desplazar los elementos restantes
            for (int i = indiceEliminar; i < padre.numHijos - 1; i++) {
                padre.hijos[i] = padre.hijos[i + 1];
            }
            padre.hijos[padre.numHijos - 1] = null;
            padre.numHijos--;

            System.out.println("✓ Se eliminó a '" + nombre + "' y sus " +
                    (totalEliminados - 1) + " descendientes");
            return true;
        }

        /**
         * Busca el padre de una persona
         * @param actual Nodo actual
         * @param nombreHijo Nombre del hijo
         * @return Nodo del padre o null
         */
        private NodoFamilia buscarPadre(NodoFamilia actual, String nombreHijo) {
            if (actual == null) {
                return null;
            }

            // Revisar si alguno de los hijos es el buscado
            for (int i = 0; i < actual.numHijos; i++) {
                if (actual.hijos[i].getNombre().equals(nombreHijo)) {
                    return actual;
                }
            }

            // Buscar en los hijos
            for (int i = 0; i < actual.numHijos; i++) {
                NodoFamilia encontrado = buscarPadre(actual.hijos[i], nombreHijo);
                if (encontrado != null) {
                    return encontrado;
                }
            }

            return null;
        }

        /**
         * Cuenta todos los descendientes de un nodo
         * @param nodo Nodo raíz de la subrama
         * @return Número de descendientes
         */
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

        // ==========================================================
        // 5. PROFUNDIDAD DE LA GENERACIÓN (ALTURA DEL ÁRBOL)
        // ==========================================================

        /**
         * Calcula la generación más profunda del árbol (altura)
         * @return Número de generaciones
         */
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

        /**
         * Muestra la profundidad del árbol
         */
        public void mostrarProfundidad() {
            System.out.println("\n📏 === PROFUNDIDAD DEL ÁRBOL GENEALÓGICO ===");
            int profundidad = calcularProfundidad();
            System.out.println("El árbol tiene " + profundidad + " generaciones");

            if (profundidad > 0) {
                System.out.println("  • Generación 1: Ancestro más antiguo");
                System.out.println("  • Generación " + profundidad + ": Descendiente más joven");
            }
        }

        // ==========================================================
        // 6. RECORRIDO POR GENERACIONES (BFS)
        // ==========================================================

        /**
         * Realiza un recorrido por niveles mostrando todas las personas
         * agrupadas por generación
         */
        public void recorridoPorGeneraciones() {
            System.out.println("\n📋 === RECORRIDO POR GENERACIONES (BFS) ===");

            if (raiz == null) {
                System.out.println("El árbol está vacío");
                return;
            }

            LinkedQue<NodoFamilia> cola = new LinkedQue<>();
            cola.add(raiz);

            int generacion = 1;

            while (!cola.isEmpty()) {
                int personasEnGeneracion = cola.size();
                System.out.print("\n👥 GENERACIÓN " + generacion + ": ");

                for (int i = 0; i < personasEnGeneracion; i++) {
                    NodoFamilia actual = cola.poll();

                    if (i > 0) {
                        System.out.print(" | ");
                    }
                    System.out.print(actual.persona.obtenerNombre());

                    // Encolar hijos para siguiente generación
                    for (int j = 0; j < actual.numHijos; j++) {
                        cola.add(actual.hijos[j]);
                    }
                }
                generacion++;
            }
            System.out.println();
        }

        /**
         * Muestra el árbol completo con estructura jerárquica
         */
        public void mostrarArbolCompleto() {
            System.out.println("\n🌳 === ÁRBOL GENEALÓGICO COMPLETO ===\n");
            mostrarRecursivo(raiz, 0);
        }

        private void mostrarRecursivo(NodoFamilia nodo, int nivel) {
            if (nodo != null) {
                // Sangría
                for (int i = 0; i < nivel; i++) {
                    System.out.print("  ");
                }
                System.out.println("├─ " + nodo.persona.obtenerInfo());

                // Mostrar hijos
                for (int i = 0; i < nodo.numHijos; i++) {
                    mostrarRecursivo(nodo.hijos[i], nivel + 1);
                }
            }
        }
    }





