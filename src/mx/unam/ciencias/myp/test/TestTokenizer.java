package mx.unam.ciencias.myp.test;

import mx.unam.ciencias.myp.Token;
import mx.unam.ciencias.myp.Tokenizer;
import mx.unam.ciencias.edd.ExcepcionIndiceInvalido;
import mx.unam.ciencias.edd.IteradorLista;
import mx.unam.ciencias.edd.Lista;
import org.junit.Assert;
import org.junit.Test;
import java.util.Random;

public class TestTokenizer{

    private Random random;
    private Lista<Token> lista;
    private Tokenizer tok;

    public TestTokenizer(){
        random=new Random();
        lista=new Lista<Token>();
    }

    @Test public void testGetLista(){
        int r=random.nextInt(10000);
        String s="sinsec9992.12343secsin1829sin1tansqrcsccoscot*/()())^";
        //String s="999sinsec";
        tok=new Tokenizer(s);
        lista.agrega(new Token(s,1));
        System.out.println(lista);
        System.out.println(tok.getLista());
    }

}
