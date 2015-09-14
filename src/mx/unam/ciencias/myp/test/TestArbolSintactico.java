package mx.unam.ciencias.myp.test;

import mx.unam.ciencias.myp.Token;
import mx.unam.ciencias.myp.Tokenizer;
import mx.unam.ciencias.myp.ArbolSintactico;
import mx.unam.ciencias.myp.NodoExpresion;
import mx.unam.ciencias.edd.ExcepcionIndiceInvalido;
import mx.unam.ciencias.edd.IteradorLista;
import mx.unam.ciencias.edd.Lista;
import org.junit.Assert;
import org.junit.Test;
import java.util.Random;
import mx.unam.ciencias.myp.InvalidExpressionException;

public class TestArbolSintactico{
    private ArbolSintactico as;
    private Random random;

    public TestArbolSintactico(){
        as=new ArbolSintactico();
        random=new Random();
    }

    @Test public void testParse(){
        try{
            as.parse(Tokenizer.tokenize("5 * 3 + (4 + 2 / 2 * 8\\)"));
        }
        catch(InvalidExpressionException e){
            System.out.println(e);
        }
        System.out.println(as);
        try{
            as.parse(Tokenizer.tokenize("2*3+1"));
        }
        catch(InvalidExpressionException e){
            System.out.println(e);
        }
        System.out.println(as);
        try{
            as.parse(Tokenizer.tokenize("(2*3+1\\)"));
        }
        catch(InvalidExpressionException e){
            System.out.println(e);
        }
        System.out.println(as);
        try{
            as.parse(Tokenizer.tokenize("2*3+1*5/5-4"));
        }
        catch(InvalidExpressionException e){
            System.out.println(e);
        }
        System.out.println(as);
        try{
            as.parse(Tokenizer.tokenize("(2*3\\)+(1+2\\)"));
        }
        catch(InvalidExpressionException e){
            System.out.println(e);
        }
        System.out.println(as);
    }
}
