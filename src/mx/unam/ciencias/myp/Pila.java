package mx.unam.ciencias.edd;

/**
 * Clase para pilas genéricas.
 */
public class Pila<T> extends MeteSaca<T> {

    /**
     * Agrega un elemento al tope de la pila.
     * @param elemento el elemento a agregar.
     */
    @Override public void mete(T elemento) {
	Nodo n=new Nodo(elemento);
	if(cabeza==null)
	    cabeza=rabo=n;
	else{
	    n.siguiente=cabeza;
	    cabeza=n;
	}
	elementos++;
    }
}
