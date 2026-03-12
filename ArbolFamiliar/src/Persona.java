
    public class Persona {
        private String nombre;
        private String fechaNacimiento;
        private char genero;


        public Persona(String nombre, String fechaNacimiento, char genero) {
            this.nombre = nombre;
            this.fechaNacimiento = fechaNacimiento;
            this.genero = genero;
        }


        public Persona(String nombre, String fechaNacimiento) {
            this(nombre, fechaNacimiento, 'O');
        }


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


        public String getGeneroTexto() {
            switch (genero) {
                case 'M': return "Masculino";
                case 'F': return "Femenino";
                default: return "No especificado";
            }
        }

        public String obtenerInfo() {
            return nombre + " (" + getGeneroTexto() + ") - Nac: " + fechaNacimiento;
        }


        public String obtenerNombre() {
            return nombre;
        }

        @Override
        public String toString() {
            return obtenerInfo();
        }
    }



