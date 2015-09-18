package mx.unam.ciencias.myp;

import mx.unam.ciencias.edd.ExcepcionIndiceInvalido;
import mx.unam.ciencias.edd.IteradorLista;
import mx.unam.ciencias.edd.Lista;

public class Tokenizer{

    public static Lista<Token> tokenize(String cad) throws InvalidExpressionException{
        cad.trim();
        cad.toLowerCase();
        return tokenize(cad.toCharArray());
    }

    private static Lista<Token> tokenize(char[] arr) throws InvalidExpressionException{
        int par=0;
        String s;
        Lista<Token> listaDeTokens=new Lista<Token>();
        for(int i=0;i<arr.length;i++){
            /*Si es digito, sigue checando hasta que acabe el numero*/
            if(Character.isDigit(arr[i])){
                s="";
                while(Character.isDigit(arr[i])||arr[i]=='.'){
                    s+=arr[i];
                    if(i+1==arr.length)
                        break;
                    i++;
                }
                if(i+1!=arr.length)
                    i--;

                listaDeTokens.agrega(new Token(s,5));
            }
            else if(arr[i]=='('){
                listaDeTokens.agrega(new Token("(",1));
                par++;
                //System.out.println(par);
            }
            else if(arr[i]==')'){
                    listaDeTokens.agrega(new Token(")",1));
                    par--;
                    //System.out.println(par);
            }
            else if(arr[i]=='+'||arr[i]=='-')
                listaDeTokens.agrega(new Token(Character.toString(arr[i]),2));
            else if(arr[i]=='^')
                listaDeTokens.agrega(new Token(Character.toString(arr[i]),4));
            else if(arr[i]=='*'||arr[i]=='/')
                listaDeTokens.agrega(new Token(Character.toString(arr[i]),3));
            else if(arr[i]=='x')
                listaDeTokens.agrega(new Token("x",5));
            else if(arr[i]=='s'){
                if(i+1!=arr.length){
                    i++;
                    if(arr[i]=='e'){
                        if(i+1!=arr.length){
                            i++;
                            listaDeTokens.agrega(new Token("sec",1));
                        }
                    }
                    else if(arr[i]=='q'){
                        if(i+1!=arr.length){
                            i++;
                            listaDeTokens.agrega(new Token("sqr",1));
                        }
                    }
                    else if(arr[i]=='i'){
                        if(i+1!=arr.length){
                            i++;
                            listaDeTokens.agrega(new Token("sin",1));
                        }
                    }
                }
            }
            else if(arr[i]=='c'){
                if(i+1!=arr.length){
                    i++;
                    if(arr[i]=='o'){
                        if(i+1!=arr.length){
                            i++;
                            if(arr[i]=='s')
                                listaDeTokens.agrega(new Token("cos",1));
                            else
                                listaDeTokens.agrega(new Token("cot",1));
                        }
                    }
                    else if(arr[i]=='s'){
                        if(i+1!=arr.length){
                            i++;
                            listaDeTokens.agrega(new Token("csc",1));
                        }
                    }
                }
            }
            else if(arr[i]=='t')
                if(i+2!=arr.length){
                    i+=2;
                    listaDeTokens.agrega(new Token("tan",1));
                }
            else{
                throw new InvalidExpressionException("Expresion invalida. Caracter no soportado");
            }
        }
        if(par!=0)
            throw new InvalidExpressionException("Expresion invalida. Error con los parentesis");
        return listaDeTokens;
    }
}
