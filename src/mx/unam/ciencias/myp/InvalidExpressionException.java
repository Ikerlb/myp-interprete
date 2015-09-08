package mx.unam.ciencias.myp;

public class InvalidExpressionException extends Exception{
        public InvalidExpressionException(){
            super();
        }

        public InvalidExpressionException(String s){
            super(s);
        }
}
