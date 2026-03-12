
    /**
     * Implementación enlazada del TDA Cola (Conferencia 4)
     * @param <E> Tipo de elementos en la cola
     */
    public class LinkedQue<E> {
        private Nodo<E> first;
        private Nodo<E> last;

        public LinkedQue() {
            this.first = null;
            this.last = null;
        }

        public boolean isEmpty() {
            return first == null;
        }

        public void add(E item) {
            Nodo<E> n = new Nodo<>(item);

            if (isEmpty()) {
                first = n;
                last = n;
            } else {
                last.setNext(n);
                last = n;
            }
        }

        public E poll() {
            if (isEmpty()) {
                return null;
            } else {
                E aux = first.getInfo();
                first = first.getNext();

                if (first == null) {
                    last = null;
                }

                return aux;
            }
        }

        public E peek() {
            if (isEmpty()) {
                return null;
            } else {
                return first.getInfo();
            }
        }

        public int size() {
            int contador = 0;
            Nodo<E> actual = first;

            while (actual != null) {
                contador++;
                actual = actual.getNext();
            }

            return contador;
        }
    }



