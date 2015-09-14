package mx.unam.ciencias.myp;

public class Token{

    private String expresion;          /*EXPRESION DEL TOKEN*/
    private int valorDelToken;         /*VALOR DEL TOKEN,CADA TIPO DE TOKEN TIENE UN VALOR DISTINTO*/

    public Token(String expresion,int valorDelToken){
        this.expresion=expresion;
        this.valorDelToken=valorDelToken;
    }

    public String getExpresion(){
        return expresion;
    }

    public int getValor(){
        return valorDelToken;
    }

    /*
    public String toString(){
        return expresion.toString()+"["+valorDelToken+"]";
    }
    */

    public String toString(){
        return expresion.toString();
    }

    public boolean equals(Object o){
        if (o == null)
            return false;
        if (getClass() != o.getClass())
            return false;
        @SuppressWarnings("unchecked") Token t = (Token)o;
        return expresion.equals(t.expresion);
    }
}
