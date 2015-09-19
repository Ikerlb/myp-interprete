package mx.unam.ciencias.edd;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * <p>Clase genérica para listas doblemente ligadas.</p>
 *
 * <p>Las listas nos permiten agregar elementos al inicio o final de la lista,
 * eliminar elementos de la lista, comprobar si un elemento está o no en la
 * lista, y otras operaciones básicas.</p>
 *
 * <p>Las instancias de la clase Lista implementan la interfaz {@link
 * Coleccion}, y por lo tanto también la interfaz {@link Iterator}, por lo que
 * el recorrerlas es muy sencillo:</p>
 *
<pre>
    for (String s : l)
        System.out.println(s);
</pre>
 *
 * <p>Además, se le puede pedir a una lista una instancia de {@link
 * IteradorLista} para recorrerla en ambas direcciones.</p>
 */
public class Lista<T> implements Coleccion<T> {

    /* Clase Nodo privada para uso interno de la clase Lista. */
    private class Nodo {

        /* El elemento del nodo. */
        public T elemento;
        /* El nodo anterior. */
        public Nodo anterior;
        /* El nodo siguiente. */
        public Nodo siguiente;

        /* Construye un nodo con el elemento especificado. */
        public Nodo(T elemento) {
            this.elemento = elemento;
        }
    }

   /* Clase Iterador privada para iteradores. */
    private class Iterador<T> implements IteradorLista<T> {

        /* La lista a iterar. */
        Lista<T> lista;
        /* Elemento anterior. */
        private Lista<T>.Nodo anterior;
        /* Elemento siguiente. */
        private Lista<T>.Nodo siguiente;

        /* El constructor recibe una lista para inicializar su siguiente con la
         * cabeza. */
        public Iterador(Lista<T> lista) {
	    this.lista=lista;
	    this.siguiente=lista.cabeza;
        }

        /* Existe un siguiente elemento, si siguiente no es nulo. */
        @Override public boolean hasNext() {
	    return siguiente!=null;
        }

        /* Regresa el elemento del siguiente, a menos que sea nulo, en cuyo caso
         * lanza la excepción NoSuchElementException. */
        @Override public T next() throws NoSuchElementException{
	    if(siguiente==null)
		throw new NoSuchElementException();
	    anterior=siguiente;
	    siguiente=siguiente.siguiente;
	    return anterior.elemento;
        }

        /* Existe un elemento anterior, si anterior no es nulo. */
        @Override public boolean hasPrevious() {
	    return anterior!=null;
	}

        /* Regresa el elemento del anterior, a menos que sea nulo, en cuyo caso
         * lanza la excepción NoSuchElementException. */
        @Override public T previous() {
	    if(anterior==null)
		throw new NoSuchElementException();
	    siguiente=anterior;
	    anterior=anterior.anterior;
	    return siguiente.elemento;
        }

        /* No implementamos el método remove(); sencillamente lanzamos la
         * excepción UnsupportedOperationException. */
        @Override public void remove() throws UnsupportedOperationException{
            throw new UnsupportedOperationException();
        }

        /* Mueve el iterador al inicio de la lista; después de llamar este
         * método, y si la lista no es vacía, hasNext() regresa verdadero y
         * next() regresa el primer elemento. */
        @Override public void start() {
	    siguiente=lista.cabeza;
	    anterior=null;
        }

        /* Mueve el iterador al final de la lista; después de llamar este
         * método, y si la lista no es vacía, hasPrevious() regresa verdadero y
         * previous() regresa el último elemento. */
        @Override public void end() {
	    anterior=lista.rabo;
	    siguiente=null;
        }
    }

    /* Primer elemento de la lista. */
    private Nodo cabeza;
    /* Último elemento de la lista. */
    private Nodo rabo;
    /* Número de elementos en la lista. */
    private int longitud;

    /**
     * Regresa la longitud de la lista. El método es idéntico a {@link
     * #getElementos}.
     * @return la longitud de la lista, el número de elementos que contiene.
     */
    public int getLongitud() {
	return longitud;
    }

    /**
     * Regresa el número de elementos en la lista. El método es idéntico a
     * {@link getLongitud}.
     * @return el número de elementos en la lista.
     */
    @Override public int getElementos() {
	return longitud;
    }

    /**
     * Agrega un elemento al final de la lista. El método es idéntico a {@link
     * #agregaFinal}.
     * @param elemento el elemento a agregar.
     */
    @Override public void agrega(T elemento) {
	Nodo n=new Nodo(elemento);
	if(longitud==0){
	    cabeza=rabo=n;
	}
	else{
	    rabo.siguiente=n;
	    n.anterior=rabo;
	    rabo=n;
	}
	longitud++;
    }

    /**
     * Agrega un elemento al final de la lista. Si la lista no tiene elementos,
     * el elemento a agregar será el primero y el último a la vez.
     * @param elemento el elemento a agregar.
     */
    public void agregaFinal(T elemento) {
	Nodo n=new Nodo(elemento);
	if(longitud==0){
	    this.cabeza=this.rabo=n;
	}
	else{
	    this.rabo.siguiente=n;
	    n.anterior=this.rabo;
	    this.rabo=n;
	}
	longitud++;
    }

    /**
     * Agrega un elemento al inicio de la lista. Si la lista no tiene elementos,
     * el elemento a agregar será el primero y el último a la vez.
     * @param elemento el elemento a agregar.
     */
    public void agregaInicio(T elemento) {
	Nodo n=new Nodo(elemento);
	if(longitud==0){
	    this.cabeza=this.rabo=n;
	}
	else{
	    this.cabeza.anterior=n;
	    n.siguiente=this.cabeza;
	    this.cabeza=n;
	}
	this.longitud++;
    }

    private Nodo encuentraNodo(T element){
	Nodo temp=cabeza;
	while(temp!=null){
	    if(temp.elemento.equals(element))
		return temp;
	    temp=temp.siguiente;
	}
	return null;
    }

    /**
     * Elimina un elemento de la lista. Si el elemento no está contenido en la
     * lista, el método no hace nada. Si el elemento aparece varias veces en la
     * lista, el método elimina el primero.
     * @param elemento el elemento a eliminar.
     */
    @Override public void elimina(T elemento) {
	Nodo n=encuentraNodo(elemento);
	if(n==null){
	    return;
	}else if(cabeza==rabo){
	    cabeza=rabo=null;
	}else if(n==rabo){
	    rabo=rabo.anterior;
	    rabo.siguiente=null;
	}else if(n==cabeza){
	    cabeza=cabeza.siguiente;
	    cabeza.anterior=null;
	}else{
	    n.anterior.siguiente=n.siguiente;
	    n.siguiente.anterior=n.anterior;
	}
	longitud--;
    }

    /**
     * Elimina el primer elemento de la lista y lo regresa.
     * @return el primer elemento de la lista antes de eliminarlo.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public T eliminaPrimero() throws NoSuchElementException{
	if(longitud==0)
	    throw new NoSuchElementException();
	T temp;
	if(longitud==1){
	    temp=cabeza.elemento;
	    cabeza=rabo=null;
	}
	else{
	    temp=cabeza.elemento;
	    this.cabeza=this.cabeza.siguiente;
	    this.cabeza.anterior=null;
	}
	longitud--;
	return temp;
    }

    /**
     * Elimina el último elemento de la lista y lo regresa.
     * @return el último elemento de la lista antes de eliminarlo.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public T eliminaUltimo() throws NoSuchElementException{
	if(longitud==0)
	    throw new NoSuchElementException();
	T temp;
	if(longitud==1){
	    temp=cabeza.elemento;
	    cabeza=rabo=null;
	}
	else{
	    temp=rabo.elemento;
	    this.rabo=this.rabo.anterior;
	    this.rabo.siguiente=null;
	}
	longitud--;
	return temp;
    }

    /**
     * Nos dice si un elemento está en la lista.
     * @param elemento el elemento que queremos saber si está en la lista.
     * @return <tt>true</tt> si <tt>elemento</tt> está en la lista,
     *         <tt>false</tt> en otro caso.
     */
    @Override public boolean contiene(T elemento) {
	Nodo temp=this.cabeza;
	while(temp!=null){
	    if(temp.elemento.equals(elemento))
		return true;
	    temp=temp.siguiente;
	}
	return false;
    }

    /**
     * Regresa la reversa de la lista.
     * @return una nueva lista que es la reversa de la que manda llamar el
     *         método.
     */
    public Lista<T> reversa() {
	Lista<T> l=new Lista<T>();
	for (T elemento : this)
	    l.agregaInicio(elemento);
	return l;
    }

    /**
     * Regresa una copia de la lista. La copia tiene los mismos elementos que la
     * lista que manda llamar el método, en el mismo orden.
     * @return una copiad de la lista.
     */
    public Lista<T> copia() {
	Lista<T> l=new Lista<T>();
	if(this.longitud==0)
	    return l;
	l.cabeza=this.cabeza;
	l.rabo=this.rabo;
	for(T elemento:this)
	    l.agregaFinal(elemento);
	return l;
    }

    /**
     * Limpia la lista de elementos. El llamar este método es equivalente a
     * eliminar todos los elementos de la lista.
     */
    public void limpia() {
	this.cabeza=null;
	rabo=null;
	this.longitud=0;
    }

    /**
     * Regresa el primer elemento de la lista.
     * @return el primer elemento de la lista.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public T getPrimero() throws NoSuchElementException{
	if(longitud==0)
	    throw new NoSuchElementException();
	return this.cabeza.elemento;
    }

    /**
     * Regresa el último elemento de la lista.
     * @return el último elemento de la lista.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public T getUltimo() throws NoSuchElementException {
	if(longitud==0)
	    throw new NoSuchElementException();
	return this.rabo.elemento;
    }

    /**
     * Regresa el <em>i</em>-ésimo elemento de la lista.
     * @param i el índice del elemento que queremos.
     * @return el <em>i</em>-ésimo elemento de la lista, si <em>i</em> es mayor
     *         o igual que cero y menor que el número de elementos en la lista.
     * @throws ExcepcionIndiceInvalido si el índice recibido es menor que cero,
     *         o mayor que el número de elementos en la lista menos uno.
     */
    public T get(int i) throws ExcepcionIndiceInvalido{
	if(longitud==0||i<0||i>=longitud)
	    throw new ExcepcionIndiceInvalido();
	Nodo n = this.cabeza;
	int contador=0;
	while(n!=null){
	    if(contador==i)
		return n.elemento;
	    n=n.siguiente;
	    contador++;
	}
	return null;
    }

    /**
     * Regresa el índice del elemento recibido en la lista.
     * @param elemento el elemento del que se busca el índice.
     * @return el índice del elemento recibido en la lista, o -1 si el elemento
     *         no está contenido en la lista.
     */
    public int indiceDe(T elemento) {
	int contador=0;
	Nodo r=this.cabeza;
	if(longitud>0){
	    for(int i=0; i<longitud; i++){
		contador=i;
		if(r.elemento.equals(elemento))
		    return contador;
		r=r.siguiente;
	    }
	}
	return -1;
    }

    /**
     * Nos dice si la lista es igual al objeto recibido.
     * @param o el objeto con el que hay que comparar.
     * @return <tt>true</tt> si la lista es igual al objeto recibido;
     *         <tt>false</tt> en otro caso.
     */
    @Override public boolean equals(Object o) {
	boolean b=true;
        if (o == null)
            return false;
        if (getClass() != o.getClass())
            return false;
        @SuppressWarnings("unchecked") Lista<T> lista = (Lista<T>)o;
	if(this.longitud!=lista.longitud)
	    return false;
	Nodo n1=this.cabeza;
	Nodo n2=lista.cabeza;;
	for(int i=0;i<longitud;i++){
	    if(n1.elemento.equals(n2.elemento)){
		n1=n1.siguiente;
		n2=n2.siguiente;
	    }else{
		b=false;
		break;
	    }
	}
	return b;
    }

    /**
     * Regresa una representación en cadena de la lista.
     * @return una representación en cadena de la lista.
     */
    @Override public String toString() {
	String cadena = "[";
	Nodo temp=cabeza;
	while(temp!=null){
	    if(temp.siguiente!=null)
		cadena+=temp.elemento+", ";
	    else
		cadena+=temp.elemento+"]";
	    temp=temp.siguiente;
	}
	return cadena;
    }

    /**
     * Regresa un iterador para recorrer la lista.
     * @return un iterador para recorrer la lista.
     */
    @Override public Iterator<T> iterator() {
        return iteradorLista();
    }

    /**
     * Regresa un iterador para recorrer la lista en ambas direcciones.
     * @return un iterador para recorrer la lista en ambas direcciones.
     */
    public IteradorLista<T> iteradorLista() {
        return new Iterador<T>(this);
    }

    private static <T extends Comparable<T>> Lista<T> merge(Lista<T> izquierda, Lista<T> derecha){
	Lista<T> l = new Lista<T>();
	IteradorLista<T> iteradorIzq = izquierda.iteradorLista();
	IteradorLista<T> iteradorDer = derecha.iteradorLista();
	do{
	    T primero = iteradorIzq.next();
	    T segundo = iteradorDer.next();
	    if (primero.compareTo(segundo)<=0){
		l.agrega(primero);
		T regresa = iteradorDer.previous();
	    }
	    else{
		l.agrega(segundo);
		T regresa1 = iteradorIzq.previous();
	    }
	}while (iteradorIzq.hasNext()&&iteradorDer.hasNext());
	
	if(iteradorDer.hasNext())
	    while(iteradorDer.hasNext())
		l.agrega(iteradorDer.next());
	else
	    while (iteradorIzq.hasNext())
		l.agrega(iteradorIzq.next());
	return l;
    }
	
    private static <T extends Comparable<T>> Lista<T> mitad(Lista<T> lista, int lon){
	if(lon==0)
	    return lista;
	IteradorLista<T> iterador = lista.iteradorLista();
	Lista<T> l1 = new Lista<T>();
	Lista<T> l2 = new Lista<T>();
	int indice = lon/2,i,j;
	for (i=0;i<=indice;i++)
	    l1.agrega(iterador.next());
	for (j=indice;j<lista.longitud-1;j++)
	    l2.agrega(iterador.next());
	return merge(mitad(l1,l1.longitud-1), mitad(l2,l2.longitud-1));
    }
    
    public static <T extends Comparable<T>> Lista<T> mergeSort(Lista<T> l){
	return mitad(l,l.longitud);
    }

    public static <T extends Comparable<T>> boolean busquedaLineal(Lista<T> l, T e){
	for (T elemento : l)
	    if (elemento.equals(e))
		return true;
	return false;
    }

}
