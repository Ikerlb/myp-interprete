package mx.unam.ciencias.myp;

public class InvalidExpressionException extends Exception{

        /**
         * Constructor de la excepcion
         *
         *
         */
        public InvalidExpressionException(){
            super();
        }

        /**
         * Constructor de la excepcion con parametro
         *
         *@paran s mensaje
         */
        public InvalidExpressionException(String s){
            super(s);
        }
}
