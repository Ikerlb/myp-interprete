package mx.unam.ciencias.myp;

public class Token{

    private String expresion;          /*EXPRESION DEL TOKEN*/
    private int valorDelToken;         /*VALOR DEL TOKEN,CADA TIPO DE TOKEN TIENE UN VALOR DISTINTO*/

    /**
     * Constructor de tokens para facilitar lectura mas adelante
     *
     *
     *
     *
     */
    public Token(String expresion,int valorDelToken){
        this.expresion=expresion;
        this.valorDelToken=valorDelToken;
    }

    /**
     * Getter de expresion
     *
     *
     *
     * @return expresion.
     */
    public String getExpresion(){
        return expresion;
    }

    /**
     * Getter de valor
     *
     *
     *
     * @return valor.
     */
    public int getValor(){
        return valorDelToken;
    }

    /**
     * Setter de expresion
     *
     *
     *
     * @param s la nueva cadena
     */
    public void setExpresion(String s){
        expresion=s;
    }

    /**
     * toString de token
     *
     *
     *
     * @return representacion en cadena de el token.
     */
    public String toString(){
        return expresion.toString();
    }

    /**
     * equals de token
     *
     *
     * @param o el objeto a comparar
     * @return cierto si el objeto recibido es igual a este objeto
     */
    public boolean equals(Object o){
        if (o == null)
            return false;
        if (getClass() != o.getClass())
            return false;
        @SuppressWarnings("unchecked") Token t = (Token)o;
        return expresion.equals(t.expresion);
    }

    /**
     * clone de token
     *
     *
     *
     * @return la copia de este objeto.
     */
    public Token clone(){
        return new Token(expresion,valorDelToken);
    }
}
