package mx.unam.ciencias.myp;
public class NodoExpresion{

    private Token tok;
    private NodoExpresion operando1;
    private NodoExpresion operando2;

    public NodoExpresion(Token tok){
        this.tok=tok;
        operando1=null;
        operando2=null;
    }

    public NodoExpresion(Token tok,NodoExpresion operando2){
        this.tok=tok;
        this.operando2=operando2;
        operando1=null;
    }

    public NodoExpresion(Token tok,NodoExpresion operando1,NodoExpresion operando2){
        this.tok=tok;
        this.operando1=operando1;
        this.operando2=operando2;
    }

    public Token getToken(){
        return tok;
    }

    public NodoExpresion getOperando1(){
        return operando1;
    }

    public NodoExpresion getOperando2(){
        return operando2;
    }

    public String toString(){
        return tok.toString();
    }

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
