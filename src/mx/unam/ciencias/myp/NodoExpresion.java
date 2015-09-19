package mx.unam.ciencias.myp;
public class NodoExpresion{

    private Token tok;
    private NodoExpresion operando1;
    private NodoExpresion operando2;

    /**
     * Inicializa el Token tok (como no tiene operandos es terminal)
     *
     *
     *
     * @param tok el nodo terminal.
     */
    public NodoExpresion(Token tok){
        this.tok=tok;
        operando1=null;
        operando2=null;
    }

    /**
     * Inicializa el Token tok, y un nodo operando2.
     *
     *
     *
     * @param tok el operador, operando2 el argumento de el operador(funcion).
     */
    public NodoExpresion(Token tok,NodoExpresion operando2){
        this.tok=tok;
        this.operando2=operando2;
        operando1=null;
    }

    /**
     * Inicializa el Token tok, y ambos nodos
     *
     *
     *
     * @param tok el operador, operando1 y operando2 (operandos de el operador binario).
     */
    public NodoExpresion(Token tok,NodoExpresion operando1,NodoExpresion operando2){
        this.tok=tok;
        this.operando1=operando1;
        this.operando2=operando2;
    }

    /**
     * Getter de token
     *
     *
     *
     * @return tok (operador u operando).
     */
    public Token getToken(){
        return tok;
    }

    /**
     * Getter de operando1
     *
     *
     *
     * @return operando1.
     */
    public NodoExpresion getOperando1(){
        return operando1;
    }

    /**
     * Getter de operando2
     *
     *
     *
     * @return operando2.
     */
    public NodoExpresion getOperando2(){
        return operando2;
    }

    /**
     * toString
     *
     *
     *
     * @return regresa una representacion en cadena de el nodo.
     */
    public String toString(){
        return tok.toString();
    }

    /**
     *
     * Clone
     *
     *
     * @return operando1.
     */
    public NodoExpresion clone(){
        NodoExpresion ne1=null,ne2=null;
        Token token;
        if(operando1!=null)
            ne1=operando1.clone();
        token=tok.clone();
        if(operando2!=null)
            ne2=operando2.clone();
        return new NodoExpresion(token,ne1,ne2);
    }

}
