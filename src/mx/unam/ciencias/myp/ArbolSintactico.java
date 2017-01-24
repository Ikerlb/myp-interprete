//Todo make enum for token types for better readability

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

    public NodoExpresion getExpresion(){
        return this.expresion;
    }

    public void setExpresion(NodoExpresion ne){
        this.expresion=ne;
    }

    /**
     * Parsea la lista de tokens y crea el arbol sintactico
     *
     *
     * @param lista de tokens
     */
    public void parse(Lista<Token> lista){
        if(lista.getElementos()==0)
            return;
        Pila<NodoExpresion> expresiones=new Pila<NodoExpresion>();
        Pila<Token> oper=new Pila<Token>();
        NodoExpresion e1,e2;
        for(Token t:lista){
            if(t.getExpresion().equals("("))
                oper.mete(t);
            //si es variable o es cualquier numero, solo mete a la pila de expresiones
            else if(t.getValor()==5)
                expresiones.mete(new NodoExpresion(t));
            //checamos si es un operador
            else if(t.getValor()==2||t.getValor()==3||t.getValor()==4){
                if(!oper.esVacia())
                //checamos jerarquia
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
                if(!oper.esVacia()){
                    if(oper.mira().getValor()==1){
                        e2=expresiones.saca();
                        expresiones.mete(new NodoExpresion(oper.saca(),e2));
                    }
                }
            }
            else if(t.getValor()==1)
                oper.mete(t);
            else
                System.out.println("error"+t);
        }
        //los operadores que quedan estan ordenados de acuerdo a la jerarquia
        while(!oper.esVacia()){
            e2=expresiones.saca();
            e1=expresiones.saca();
            expresiones.mete(new NodoExpresion(oper.saca(),e1,e2));
        }
        expresion=expresiones.saca();
    }

    /**
     * Evalua el arbol sustituyendo con un double cualquier variable que encuentre
     *
     *
     * @param d el numero que sustituir por x
     * @return el arbol evaluado
     */
    public double evalua(double d){
        if(this.expresion!=null){
            NodoExpresion nuevoNodo=expresion.clone();
            sustituye(nuevoNodo,d);
            return evalua(nuevoNodo);
        }
        return 0.0;
    }

    private double evalua(NodoExpresion ne){
        double doub1=0;
        NodoExpresion operando1=ne.getOperando1();
        NodoExpresion operando2=ne.getOperando2();
        String s=ne.getToken().getExpresion();
        if(s.equals("+")){
            if((operando1!=null)&&(operando2!=null))
                return evalua(operando1)+evalua(operando2);
        }
        else if(s.equals("-")){
            if((operando1!=null)&&(operando2!=null))
            //if(operando2!=null)
                return evalua(operando1)-evalua(operando2);
        }
        else if(s.equals("*")){
            if((operando1!=null)&&(operando2!=null))
            //if(operando2!=null)
                return evalua(operando1)*evalua(operando2);
        }
        else if(s.equals("/")){
            if((operando1!=null)&&(operando2!=null))

                return evalua(operando1)/evalua(operando2);
        }
        else if(s.equals("^")){
            if((operando1!=null)&&(operando2!=null))
            //if(operando2!=null)
                return Math.pow(evalua(operando1),evalua(operando2));
        }
        else if(s.equals("sin")){
            if(operando2!=null)
                return Math.sin(evalua(operando2));
        }
        else if(s.equals("sec")){
            if(operando2!=null)
                return 1/Math.cos(evalua(operando2));
        }
        else if(s.equals("sqr")){
            if(operando2!=null)
                return Math.sqrt(evalua(operando2));
        }
        else if(s.equals("cos")){
            if(operando2!=null)
                return Math.cos(evalua(operando2));
        }
        else if(s.equals("csc")){
            if(operando2!=null)
                return 1/Math.sin(evalua(operando2));
        }
        else if(s.equals("cot")){
            if(operando2!=null)
                return 1/Math.tan(evalua(operando2));
        }
        else if(s.equals("tan")){
            if(operando2!=null)
                return Math.tan(evalua(operando2));
        }
        return Double.parseDouble(ne.getToken().getExpresion());
    }

    private void sustituye(NodoExpresion ne,double d){
        if(ne.getOperando1()!=null)
            sustituye(ne.getOperando1(),d);
        if(ne.getToken().getExpresion().equals("x"))
            ne.getToken().setExpresion(Double.toString(d));
        if(ne.getOperando2()!=null)
            sustituye(ne.getOperando2(),d);
    }

    /**
     * toString
     *
     *
     * @return representacion en cadena de el arbol
     */
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
