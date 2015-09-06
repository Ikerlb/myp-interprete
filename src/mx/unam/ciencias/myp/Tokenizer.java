package mx.unam.ciencias.myp;

import mx.unam.ciencias.edd.ExcepcionIndiceInvalido;
import mx.unam.ciencias.edd.IteradorLista;
import mx.unam.ciencias.edd.Lista;

public class Tokenizer{
    private Lista<Token> listaDeTokens;    /*UNICO ATRIBUTO DE TOKENIZER*/

    public Tokenizer(String cad){
        listaDeTokens=new Lista<Token>();  /*INICIALIZAMOS LA LISTA*/
        tokenize(cad);
    }

    public Lista<Token> getLista(){
        return this.listaDeTokens;
    }

    public void tokenize(String cad){
        cad.trim();
        tokenize(cad.toCharArray());
    }

    private void tokenize(char[] arr){
        int
        String s;
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
                i--;
                listaDeTokens.agrega(new Token(s,1));
            }
            else if(arr[i]=='('||arr[i]==')')
                listaDeTokens.agrega(new Token(Character.toString(arr[i]),1));
            else if(arr[i]=='+'||arr[i]=='-')
                listaDeTokens.agrega(new Token(Character.toString(arr[i]),1));
            else if(arr[i]=='^')
                listaDeTokens.agrega(new Token(Character.toString(arr[i]),1));
            else if(arr[i]=='*'||arr[i]=='/')
                listaDeTokens.agrega(new Token(Character.toString(arr[i]),1));
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
        }
    }

}
