

    /**
     * Clase Persona para el árbol genealógico
     * Almacena nombre, fecha de nacimiento y género
     */
    public class Persona {
        private String nombre;
        private String fechaNacimiento;  // Formato: DD/MM/AAAA
        private char genero;              // 'M' para masculino, 'F' para femenino, 'O' para otro

        /**
         * Constructor completo
         * @param nombre Nombre de la persona
         * @param fechaNacimiento Fecha en formato DD/MM/AAAA
         * @param genero 'M', 'F' u 'O'
         */
        public Persona(String nombre, String fechaNacimiento, char genero) {
            this.nombre = nombre;
            this.fechaNacimiento = fechaNacimiento;
            this.genero = genero;
        }

        /**
         * Constructor simplificado (sin género)
         * @param nombre Nombre de la persona
         * @param fechaNacimiento Fecha en formato DD/MM/AAAA
         */
        public Persona(String nombre, String fechaNacimiento) {
            this(nombre, fechaNacimiento, 'O');  // 'O' = Otro/No especificado
        }

        // Getters y Setters
        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getFechaNacimiento() {
            return fechaNacimiento;
        }

        public void setFechaNacimiento(String fechaNacimiento) {
            this.fechaNacimiento = fechaNacimiento;
        }

        public char getGenero() {
            return genero;
        }

        public void setGenero(char genero) {
            this.genero = genero;
        }

        /**
         * Obtiene el género como texto
         * @return "Masculino", "Femenino" u "Otro"
         */
        public String getGeneroTexto() {
            switch (genero) {
                case 'M': return "Masculino";
                case 'F': return "Femenino";
                default: return "No especificado";
            }
        }

        /**
         * Información básica de la persona
         */
        public String obtenerInfo() {
            return nombre + " (" + getGeneroTexto() + ") - Nac: " + fechaNacimiento;
        }

        /**
         * Información resumida (solo nombre)
         */
        public String obtenerNombre() {
            return nombre;
        }

        @Override
        public String toString() {
            return obtenerInfo();
        }
    }



