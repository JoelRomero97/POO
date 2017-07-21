import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

////////////////////////////////////////////////////////////////////////////////////////
////                                 WhackAMole.java                                ////
////                                                                                ////
////                                                                                ////
//// Se realiza por medio de un Grid Layout una especie de mosaico en la que se     ////
//// simula el juego de pegarle al topo, mostrando primeramente la imagen de un     ////
//// topo vivo y al darle click se aparece la imagen de un topo muerto.             ////
////                                                                                ////
////                                                                                ////
//// Autor: Romero Gamarra Joel Mauricio                                            ////
////////////////////////////////////////////////////////////////////////////////////////

public class WhackAMole extends JFrame implements ActionListener 
{
    private JPanel panelCentral;									            //Panel para agregar el arreglo de botones
    private JButton botones [];												    //Arreglo de botones para realizar el tablero
    private Image topoVivo;                                                     //Imagen para mostrar al topo vivo
    private Image topoMuerto;                                                   //Imagen para mostrar al topo muerto
    private ImageIcon muertoEscala;                                             //Escala de la imagen del topo muerto
    private ImageIcon vivoEscala;                                               //Escala de la imagen del topo vivo
    private JFrame ventana;                                                     //Ventana para mostrar el tablero
    private JButton boton;                                                      //Boton donde se produce el evento
    private int filas, columnas;                                                //Enteros para saber filas y columnas
    private int alto, ancho;                                                    //Alto y ancho para las imagenes escaladas
    private int numBotones;                                                     //Filas * columnas

    public void inicializaVariables ()
    {
        filas = 5;
        columnas = 5;
        alto = -1;
        ancho = 120;
        numBotones = filas * columnas;
        botones = new JButton [numBotones];                                     //Arreglo de botones de filas * columas 
        topoVivo = (new ImageIcon ("Vivo.jpg")).getImage ();                    //Seleccionamos la imagen para mostrar antes de dar click
        topoMuerto = (new ImageIcon ("Muerto.jpg")).getImage ();                //Seleccionamos la imagen para mostrar despues de dar click
                                                                                //Escalamos las imagenes para que se ajusten al tamaño del boton
        muertoEscala = new ImageIcon(topoMuerto.getScaledInstance(ancho, alto, java.awt.Image.SCALE_DEFAULT));
        vivoEscala = new ImageIcon(topoVivo.getScaledInstance(ancho, alto, java.awt.Image.SCALE_DEFAULT));
    }

    public void construyeTablero ()
    {
        panelCentral = new JPanel ();                                           //Creamos un objeto de tipo JPanel
        panelCentral.setLayout (new GridLayout (filas, columnas, 2, 2));        //Agregamos un Layout al panel de tipo Grid con n filas y n columnas
        panelCentral.setBackground (Color.CYAN);                                //Agregamos un color de fondo cyan
        for(int i = 0; i < numBotones; i ++)
        {
            botones[i] = new JButton();
            botones[i].setIcon (vivoEscala);                                    //Agregamos a todos los botones la imagen del topo vivo
            botones[i].addActionListener (this);                                //Agregamos el escucha a cada uno de los botones
            botones[i].setBackground (Color.CYAN);                              //Ponemos color de fondo al arreglo de botones
            panelCentral.add (botones[i]);                                      //Agregamos los botones al panel
        }
    }

    public void construyeVentana ()
    {
        ventana = new JFrame ();                                                //Creamos un objeto de tipo JFrame
        ventana.setLayout (new BorderLayout ());                                //Agregamos un Layout que divide la ventana en 5
    	ventana.add (panelCentral, BorderLayout.CENTER);						//Agregamos el objeto de tipo JPanel a la parte central (construyeTablero)
    	ventana.setVisible (true);												//Hacemos visible el objeto de tipo JFrame
    	ventana.setSize (650, 670);												//Le asignamos un tamaño al objeto de tipo JFrame de 700 (ancho) x 500 (alto)
        ventana.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);                //Sale de la aplicación al cerrar el objeto de tipo JFrame
    }

    public void actionPerformed (ActionEvent e)
    {
    	boton = (JButton) e.getSource ();										//Objeto de tipo JButton en el que se produce el evento
        boton.setIcon (muertoEscala);
    }

    public static void main(String[] args)
    {
    	new WhackAMole ();															//Creamos un objeto de tipo WhackAMole
    }

    public WhackAMole ()
    {
        inicializaVariables ();                                                 //Invocamos al método inicializaVariables en el constructor de la clase WhackAMole
    	construyeTablero ();												    //Invocamos el método construyeTablero en el constructor de la clase WhackAMole
    	construyeVentana ();													//Invocamos el método construyeVentana en el constructor de la clase WhackAMole
    }
}