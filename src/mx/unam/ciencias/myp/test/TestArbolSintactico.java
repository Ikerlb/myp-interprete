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
            as.parse(Tokenizer.tokenize("3+4*2/(1-5\\)^2^3"));
        }
        catch(InvalidExpressionException e){
            System.out.println(e);
        }
        //System.out.println(as);
        try{
            as.parse(Tokenizer.tokenize("x+sin(1\\)"));
        }
        catch(InvalidExpressionException e){
            System.out.println(e);
        }
        try{
            as.parse(Tokenizer.tokenize("cos(2*sin(2^2\\)\\)"));
            //System.out.println(as.getExpresion().getToken());
        }
        catch(InvalidExpressionException e){
            System.out.println(e);
        }
        //System.out.println(as);
        try{
            as.parse(Tokenizer.tokenize("2*3+1*5/5-4"));
        }
        catch(InvalidExpressionException e){
            System.out.println(e);
        }
        //System.out.println(as);
        try{
            as.parse(Tokenizer.tokenize("(2*3\\)+(1+2\\)"));
        }
        catch(InvalidExpressionException e){
            System.out.println(e);
        }
        //System.out.println(as);
        try{
            as.parse(Tokenizer.tokenize("(2*3\\)+(1+2\\)*(2*4\\)"));
        }
        catch(InvalidExpressionException e){
            System.out.println(e);
        }
        //System.out.println(as);
        try{
            as.parse(Tokenizer.tokenize("(2*3\\)*(1+2\\)+(2*4\\)"));
        }
        catch(InvalidExpressionException e){
            System.out.println(e);
        }
        //System.out.println(as);
    }

    @Test public void TestEvalua(){
        try{
            as.parse(Tokenizer.tokenize("x+3+4+5+4+3+2"));
            System.out.println(as.evalua(10));
        }
        catch(InvalidExpressionException e){
            System.out.println(e);
        }
        try{
            as.parse(Tokenizer.tokenize("x^3*4+6-x"));
            System.out.println(as.evalua(10));
        }
        catch(InvalidExpressionException e){
            System.out.println(e);
        }
        try{
            as.parse(Tokenizer.tokenize("x"));
            //System.out.println(as.evalua(2.0/720.0,-1,1));
        }
        catch(InvalidExpressionException e){
            System.out.println(e);
        }
        try{
            as.parse(Tokenizer.tokenize("sqr(x)"));
            System.out.println(as.evalua(-1));
        }
        catch(InvalidExpressionException e){
            System.out.println();
        }
    }
}
