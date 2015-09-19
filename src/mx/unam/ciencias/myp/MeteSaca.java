package mx.unam.ciencias.edd;

import java.util.NoSuchElementException;

/**
 * Clase abtracta para estructuras lineales restringidas a operaciones
 * mete/saca/mira.
 */
public abstract class MeteSaca<T> {

    /**
     * Clase Nodo protegida para uso interno de sus clases herederas.
     */
    protected class Nodo {
        /** El elemento del nodo. */
        public T elemento;
        /** El siguiente nodo. */
        public Nodo siguiente;

        /**
         * Construye un nodo con un elemento.
         * @param elemento el elemento del nodo.
         */
        public Nodo(T elemento) {
            this.elemento = elemento;
        }
    }

    /** La cabeza de la estructura. */
    protected Nodo cabeza;
    /** El rabo de la estructura. */
    protected Nodo rabo;
    /** El número de elementos en la estructra. */
    protected int elementos;

    /**
     * Agrega un elemento al extremo de la estructura.
     * @param elemento el elemento a agregar.
     */
    public abstract void mete(T elemento);

    /**
     * Elimina el elemento en un extremo de la estructura y lo regresa.
     * @return el elemento en un extremo de la estructura.
     * @throws NoSuchElementException si la estructura está vacía.
     */
    public T saca() throws NoSuchElementException{
	if(elementos==0)
	    throw new NoSuchElementException();
	T temporal=cabeza.elemento;
	if(elementos==1)
	    cabeza=rabo=null;
	else
	    cabeza=cabeza.siguiente;
	elementos--;
	return temporal;
    }

    /**
     * Nos permite ver el elemento en un extremo de la estructura, sin sacarlo
     * de la misma.
     * @return el elemento en un extremo de la estructura.
     * @throws NoSuchElementException si la estructura está vacía.
     */
    public T mira() throws NoSuchElementException{
        if(elementos==0)
	    throw new NoSuchElementException();
	return cabeza.elemento;
	    
    }

    /**
     * Nos dice si la estructura está vacía.
     * @return <tt>true</tt> si la estructura no tiene elementos,
     *         <tt>false</tt> en otro caso.
     */
    public boolean esVacia() {
	return elementos==0;
    }

    /**
     * Compara la estructura con un objeto.
     * @param o el objeto con el que queremos comparar la estructura.
     * @return <code>true</code> si el objeto recibido es una instancia de la
     *         misma clase que la estructura, y sus elementos son iguales en el
     *         mismo orden; <code>false</code> en otro caso.
     */
    @Override public boolean equals(Object o) {
	if(o==null)
	    return false;
	if(getClass()!=o.getClass())
	    return false;
	@SuppressWarnings("unchecked") MeteSaca<T> ms=(MeteSaca<T>)o;
	if(elementos!=ms.elementos)
	    return false;
	Nodo n1=cabeza;
	Nodo n2=ms.cabeza;
	for(int i=0;i<elementos;i++){
	    if(n1.elemento.equals(n2.elemento)){
		n1=n1.siguiente;
		n2=n2.siguiente;
	    }		
	    else
		return false;
	}
    return true;
    }
}
