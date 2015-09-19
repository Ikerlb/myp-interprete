package mx.unam.ciencias.edd;

/**
 * Clase para manipular arreglos genéricos de elementos comparables.
 */
public class Arreglos {

    private static <T extends Comparable<T>> void quickSort(T[] a, int i, int j){
	if (i==j||j<i)
	   return;
	int izq=i+1;
	int der=j;
	T pivote=a[i],elemento;
	while (izq<=der){
	    while (izq<=j&&a[izq].compareTo(pivote)<=0)
		izq++;
	    while (i<=der&&0<a[der].compareTo(pivote))
		der--;
	    if (izq<der){
		elemento=a[izq];
		a[izq]=a[der];
		a[der]=elemento;
		izq++;
		der--;
	    }
	}
	if (der+1==i)
	   der+=2;
	a[i]=a[der];
	a[der]=pivote;
	if (i<=der-1)
	    quickSort(a,i,der-1);
	if (der+1<j)
	    quickSort(a,der+1,j);
    }
    
    
    /**
     * Ordena el arreglo recibido usando QickSort.
     * @param <T> tipo del que puede ser el arreglo.
     * @param a un arreglo cuyos elementos son comparables.
     */
    
    public static <T extends Comparable<T>> void quickSort(T[] a) {
	if (a.length<2)
	    return;
	quickSort(a,0,a.length-1);
    }
    

    /**
     * Ordena el arreglo recibido usando SelectionSort.
     * @param <T> tipo del que puede ser el arreglo.
     * @param a un arreglo cuyos elementos son comparables.
     */
    public static <T extends Comparable<T>> void selectionSort(T[] a) {
	if (a.length < 2)
	    return;
	for (int primero=0;primero<(a.length)-1;primero++){
	    T elemento=a[primero];
	    T auxiliar=a[primero];
	    int aux=primero;
	    for (int i=primero+1;i< a.length;i++){
		if (a[i].compareTo(elemento)<0&&a[i].compareTo(auxiliar)<0){
		    auxiliar=a[i];
		    aux=i;
		}
	    }
	    if (aux!=primero){
		a[primero]=auxiliar;;
		a[aux]=elemento;
	    }
	}
		    
    }

    private static <T extends Comparable<T>> int busquedaBinaria(T[] a, T e, int primero, int segundo){
	if (primero==segundo){
	    if (a[primero].equals(e))
		return primero;
	    return -1;
	}
	int temp=(primero+segundo)/2;
	if (a[temp].equals(e))
	    return temp;
	if (temp==primero||temp==segundo)
	    return -1;
	if (0<a[temp].compareTo(e))
	    segundo=temp;
	else
	    primero=temp;
	return busquedaBinaria(a,e,primero,segundo);
    }
    /**
     * Hace una búsqueda binaria del elemento en el arreglo. Regresa el índice
     * del elemento en el arreglo, o -1 si no se encuentra.
     * @param <T> tipo del que puede ser el arreglo.
     * @param a el arreglo dónde buscar.
     * @param e el elemento a buscar.
     * @return el índice del elemento en el arreglo, o -1 si no se encuentra.
     */
    public static <T extends Comparable<T>> int busquedaBinaria(T[] a, T e) {
        if (a.length==0)
	    return -1;
	return busquedaBinaria(a,e,0,a.length); 
    }
}
