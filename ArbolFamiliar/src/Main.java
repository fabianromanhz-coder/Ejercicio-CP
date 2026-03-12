//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {


                ArbolGenealogico familia = new ArbolGenealogico();

                familia.agregarRaiz("Carlos Ruiz", "15/03/1950", 'M', 3);


                familia.agregarHijo("Carlos Ruiz", "Ana Ruiz", "20/07/1975", 'F', 2);
                familia.agregarHijo("Carlos Ruiz", "Luis Ruiz", "10/11/1978", 'M', 3);
                familia.agregarHijo("Carlos Ruiz", "Marta Ruiz", "05/02/1982", 'F', 2);


                familia.agregarHijo("Ana Ruiz", "Pedro Ruiz", "12/04/2000", 'M', 0);
                familia.agregarHijo("Ana Ruiz", "Sofía Ruiz", "25/09/2003", 'F', 0);

                familia.agregarHijo("Luis Ruiz", "Diego Ruiz", "30/06/2005", 'M', 0);
                familia.agregarHijo("Luis Ruiz", "Valentina Ruiz", "18/01/2008", 'F', 0);
                familia.agregarHijo("Luis Ruiz", "Javier Ruiz", "22/11/2010", 'M', 0);

                familia.agregarHijo("Marta Ruiz", "Camila Ruiz", "07/03/2012", 'F', 0);
                familia.agregarHijo("Marta Ruiz", "Nicolás Ruiz", "19/08/2015", 'M', 0);

                familia.agregarHijo("Diego Ruiz", "Lucía Ruiz", "10/05/2030", 'F', 0);


                familia.mostrarArbolCompleto();

                familia.recorridoPorGeneraciones();

                familia.mostrarProfundidad();

                familia.mostrarAntepasados("Diego Ruiz");
                familia.mostrarAntepasados("Lucía Ruiz");
                familia.mostrarAntepasados("Carlos Ruiz");  // Raíz
                familia.mostrarAntepasados("Persona Inexistente");


                familia.mostrarDescendientes("Luis Ruiz");
                familia.mostrarDescendientes("Ana Ruiz");
                familia.mostrarDescendientes("Diego Ruiz");  // Tiene 1 hija

                familia.eliminarRama("Marta Ruiz");

                System.out.println(" ÁRBOL DESPUÉS DE ELIMINAR A MARTA Y SUS HIJOS ");
                familia.mostrarArbolCompleto();

                familia.mostrarProfundidad();

                familia.eliminarRama("Camila Ruiz");

                
            }
        }


