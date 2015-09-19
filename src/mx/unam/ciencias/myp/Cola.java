package mx.unam.ciencias.edd;

/**
 * Clase para colas genéricas.
 */
public class Cola<T> extends MeteSaca<T> {

    /**
     * Agrega un elemento al final de la cola.
     * @param elemento el elemento a agregar.
     */
    @Override public void mete(T elemento) {
	Nodo n = new Nodo(elemento);
	if (this.cabeza==null)
	    this.cabeza=this.rabo=n;
	else{
	    rabo.siguiente=n;
	    rabo =n;
	}
	elementos++;
    }
}
