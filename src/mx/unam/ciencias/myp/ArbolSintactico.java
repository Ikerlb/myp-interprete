package mx.unam.ciencias.myp;
import mx.unam.ciencias.edd.ExcepcionIndiceInvalido;
import mx.unam.ciencias.edd.IteradorLista;
import mx.unam.ciencias.edd.Lista;
import mx.unam.ciencias.edd.MeteSaca;
import mx.unam.ciencias.edd.Pila;

public class ArbolSintactico{

    private NodoExpresion expresion;

    public ArbolSintactico(){
        expresion=null;
    }

    /*parsea la lista de tokens para crear el arbol sintactico. hasta el momento funciona unicamente con parentesis [solo funciona escapando ")"]*/
    public void parse(Lista<Token> lista){
        Pila<NodoExpresion> expresiones=new Pila<NodoExpresion>();
        Pila<Token> oper=new Pila<Token>();
        NodoExpresion e1,e2;
        for(Token t:lista){
            if(t.getExpresion().equals("("))
                oper.mete(t);
            //si es variable o es cualquier numero, solo mete a la pila de expresiones
            else if(t.getValor()==5)
                expresiones.mete(new NodoExpresion(t));
            //si es un operad
            else if(t.getValor()==2||t.getValor()==3||t.getValor()==4){
                if(!oper.esVacia())
                    while(oper.mira().getValor()>=t.getValor()){
                        e2=expresiones.saca();
                        e1=expresiones.saca();
                        expresiones.mete(new NodoExpresion(oper.saca(),e1,e2));
                        //vemos si es vacia para evitar excepciones.
                        if(oper.esVacia())
                            break;
                    }
                oper.mete(t);
            }
            else if(t.getExpresion().equals(")")){
                if(!oper.esVacia())
                    while(!oper.mira().getExpresion().equals("(")){
                        e2=expresiones.saca();
                        e1=expresiones.saca();
                        expresiones.mete(new NodoExpresion(oper.saca(),e1,e2));
                    }
                oper.saca();
            }
            else
                System.out.println("error"+t);
        }
        if(!oper.esVacia()){
            e2=expresiones.saca();
            e1=expresiones.saca();
            expresiones.mete(new NodoExpresion(oper.saca(),e1,e2));
        }
        expresion=expresiones.saca();
    }

    public String toString(){
        if(expresion==null)
            return "";
        return toString(this.expresion);
    }

    //metodo recursivo para imprimir el arbol (sirve para motivos de prueba)
    private String toString(NodoExpresion ne){
        String s="";
        if(ne.getOperando1()!=null)
            s+=toString(ne.getOperando1());
        s+=ne.getToken().toString();
        if(ne.getOperando2()!=null)
            s+=toString(ne.getOperando2());
        return s;
    }
}
