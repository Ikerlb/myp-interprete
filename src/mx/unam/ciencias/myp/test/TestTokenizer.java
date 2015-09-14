package mx.unam.ciencias.myp.test;

import mx.unam.ciencias.myp.Token;
import mx.unam.ciencias.myp.Tokenizer;
import mx.unam.ciencias.edd.ExcepcionIndiceInvalido;
import mx.unam.ciencias.edd.IteradorLista;
import mx.unam.ciencias.edd.Lista;
import org.junit.Assert;
import org.junit.Test;
import java.util.Random;
import mx.unam.ciencias.myp.InvalidExpressionException;

public class TestTokenizer{

    private Random random;
    private Lista<Token> lista;
    private final String[] EXPR={"sin", "cos", "tan","cot","csc","sqr","+","-","^","/","x"};

    public TestTokenizer(){
        random=new Random();
        lista=new Lista<Token>();
    }

    //testGetLista
    @Test public void testTokenizer(){
        int r=random.nextInt(1000);
        int num=random.nextInt(EXPR.length);
        String s="";
        Lista<Token> tokens=lista;
        try{
            for(int i=0;i<r;i++){
                s+=EXPR[num];
                lista.agrega(new Token(EXPR[num],1));
                num=random.nextInt(EXPR.length);
            }
            tokens=Tokenizer.tokenize(s);
        }
        catch(InvalidExpressionException e){
            System.out.println(e);
        }
        Assert.assertTrue(lista.equals(tokens));
        try{
            Tokenizer.tokenize("\\(");
            Assert.fail();
        }
        catch(InvalidExpressionException e){}
        try{
             for(String str:EXPR)
                Tokenizer.tokenize(str);
        }
        catch(InvalidExpressionException e){}
        //Assert.assertTrue(lista.getPrimero().equals(tok.getLista().getPrimero()));
        //String s="999sinsec";
    }

}
