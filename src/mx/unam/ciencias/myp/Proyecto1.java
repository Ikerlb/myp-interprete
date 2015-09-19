package mx.unam.ciencias.myp;

import mx.unam.ciencias.edd.ExcepcionIndiceInvalido;
import mx.unam.ciencias.edd.IteradorLista;
import mx.unam.ciencias.edd.Lista;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.SpinnerNumberModel;
import javax.swing.JSpinner;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.Color;
import java.awt.Graphics;

public class Proyecto1 extends JFrame implements ChangeListener{


    int width,height,alturaGrafica,anchoGrafica;
    String funcion;
    JPanel p,south,linea1,linea2;
    //Centro
     PlotFunction center;
     //Sur
     JButton grafica;
    JLabel inputLabel,largo,ancho,x1,x2,y1,y2;
    JTextField input;
    JSpinner largoS,anchoS,spinnerx1,spinnery1,spinnerx2,spinnery2;

    public static void main(String[] args){
        new Proyecto1();
    }

    /**
     * Constructor del frame
     *
     *
     */
    public Proyecto1(){
		super("Proyecto 1");
        initComponents();
	}

    private void initComponents(){
        width=720;
        height=400;
        funcion=".9";
        initSpinners();
        initPanels();
        initLabels();
        initLinea1();
        initLinea2();
        grafica.addActionListener((e)-> {funcion=input.getText();setVisible(true);center.repaint();});
        setSize(width,height);
        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        p.setLayout(new BorderLayout());
        p.add(center,BorderLayout.CENTER);
        south.setLayout(new GridLayout(2,1));
        anchoS.addChangeListener(this);
        largoS.addChangeListener(this);
        p.add(south,BorderLayout.SOUTH);
        add(p);
        setVisible(true);
    }

    private void initLinea1(){
        input=new JTextField(45);
        grafica=new JButton("Grafica");
        linea1.add(inputLabel);
        linea1.add(input);
        linea1.add(grafica);
        south.add(linea1);
    }

    private void initPanels(){
        p=new JPanel();
        center=new PlotFunction();
        south=new JPanel();//contain input along with variables
        linea1=new JPanel();
        linea2=new JPanel();
    }

    private void initLinea2(){
        linea2.add(ancho);
        linea2.add(anchoS);
        linea2.add(largo);
        linea2.add(largoS);
        linea2.add(x1);
        linea2.add(spinnerx1);
        linea2.add(y1);
        linea2.add(spinnery1);
        linea2.add(x2);
        linea2.add(spinnerx2);
        linea2.add(y2);
        linea2.add(spinnery2);
        south.add(linea2);
    }

    private void initSpinners(){
        largoS=new JSpinner(new SpinnerNumberModel(400,400,740,10));
        anchoS=new JSpinner(new SpinnerNumberModel(720,720,1200,10));
        spinnerx1=new JSpinner(new SpinnerNumberModel(-1.0,-100,100,.1));
        spinnery1=new JSpinner(new SpinnerNumberModel(-1.0,-100,100,.1));
        spinnerx2=new JSpinner(new SpinnerNumberModel(1.0,-100,100,.1));
        spinnery2=new JSpinner(new SpinnerNumberModel(1.0,-100,100,.1));
    }

    private void initLabels(){
        inputLabel=new JLabel("f(x) = ");
        x1=new JLabel("x1");
        x2=new JLabel("x2");
        y1=new JLabel("y1");
        y2=new JLabel("y2");
        largo=new JLabel("Alto");
        ancho=new JLabel("Ancho");
    }

    /**
     * Event para los jpsinners
     *
     *  evento event
     */
    public void stateChanged(ChangeEvent event) {
        Object src=event.getSource();
        if(src.equals(anchoS))
            width=(Integer)anchoS.getValue();
        if(src.equals(largoS))
            height=(Integer)largoS.getValue();
        setSize(width,height);
    }

    /*clase interna privada para sobreescribir metodo paintComponent*/
    private class PlotFunction extends JPanel{
        int vxmin,vymin,vxmax,vymax;
        double xmin,ymin,xmax,ymax;
        double dx;

        /**
         * Constructor
         *
         *
         */
        public PlotFunction(){
            super();
        }

        /**
         * metodo para pintar panel
         *
         *
         * @param graficos g
         */
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            init();
            pinta(g);
        }

        /* initializing */
        private void init(){
            xmin=(double)spinnerx1.getValue();xmax=(double)spinnerx2.getValue();
            ymin=(double)spinnery1.getValue();ymax=(double)spinnery2.getValue();
            vxmin=0;vxmax=getWidth();vymin=0;vymax=getHeight();
            dx=0.1;
        }

        private void pinta(Graphics g){
            double x1,y1,x2,y2;

            g.setColor(Color.red);
            linea(g,0,ymin,0,ymax);
            linea(g,xmin,0,xmax,0);


            g.setColor(Color.black);
            x2=xmin;y2=func(x2);
            for(x1=xmin;x1<=xmax;x1=x1+dx){
                y1=func(x1);
                linea(g,x1,y1,x2,y2);
                x2=x1;y2=y1;
             }
        }


        private int mapX(double x){
            int sx;
            sx= vxmin+(int)((x-xmin)/(xmax-xmin)*(double)(vxmax-vxmin)) ;
            return sx;
        }

        private int mapY(double y){
            int sy;
            sy=vymin+(int)((ymax-y)/(ymax-ymin)*(double)(vymax-vymin));
            return sy;
        }


        private void linea(Graphics g,double x1,double y1,double x2,double y2){
            g.drawLine(mapX(x1),mapY(y1),mapX(x2),mapY(y2));
        }

        private double func(double x){
            ArbolSintactico as=new ArbolSintactico();
            try{
                as.parse(Tokenizer.tokenize(funcion));
            }
            catch(InvalidExpressionException e){
                System.out.println(e);
            }
            return as.evalua(x);
        }
    }
}
