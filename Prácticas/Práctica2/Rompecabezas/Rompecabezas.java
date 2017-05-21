import java.applet.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.awt.image.*;
import javax.swing.*;
import java.util.*;

////////////////////////////////////////////////////////////////////////////////////////
////                                Rompecabezas.java                               ////
////                                                                                ////
////                                                                                ////
//// Realización de un rompecabezas de nxn con una imagen y teniendo una celda en   ////
//// color negro para presionar alguna de las celdas adyacentes y moverla a donde   ////
//// está la pieza de color negro para completar el rompecabezas.                   ////
//// Modiciar línea 41 para cambiar imagen.                                         ////
////                                                                                ////
////                                                                                ////
//// Autor: Romero Gamarra Joel Mauricio                                            ////
////////////////////////////////////////////////////////////////////////////////////////

public class Rompecabezas extends Applet implements ActionListener
{
    private JButton rompecabezas [];                                           //Arreglo de botones del rompecabezas
    private int filasRompecabezas = 4, columnasRompecabezas = 4;        //Filas y columnas que tendrá el rompecabezas
    private int anchoImagen, alturaImagen;                              //Ancho y Alto de la imagen
    private int anchoCelda, altoCelda;                                  //Ancho y Alto de una celda
    private int ultimaCelda, ultimaCeldaAleatoria;                      //Ultima celda que irá de color negro
    private int numeroCeldas;                                           //Numero de celdas
    private Image [] celda;                                             //Arreglo de imagenes
    private int [] orden;                                               //Orden de las imagenes
    private Image imagen;                                               //Imagen a usar como rompecabezas
    private CropImageFilter imagenCortada;                              //Filtro de imagen cortada
    private CropImageFilter imagenNegra;                                //Filtro de imagen negra cortada
    private FilteredImageSource imagenFiltrada;                         //Imagen con el filtro aplicado
    private FilteredImageSource imagenNegraFiltrada;                    //Imagen negra con el filtro aplicado
    private Image imagenNegraFinal;                                     //Imagen que usaremos para ultima celda
    private Image negro;                                                //Imagen de color negro que usaremos en la ultima celda
    private int ordenAleatorio [];                                      //Arreglo de numeros aleatorio
    private Random rand;                                                //Para obtener los valores aleatorios
    private JButton boton;                                              //Botón para obtener el evento en el ActionPerformed

    public void inicializaVariables ()
    {
        imagen = (new ImageIcon ("dalmata.jpg")).getImage ();                               //Seleccionamos la imagen para armar
        negro = (new ImageIcon ("negro.png")).getImage ();                                  //Seleccionamos la imagen de fondo negro
        anchoImagen = imagen.getWidth (null);                                               //Obtenemos el ancho de la imagen
        alturaImagen = imagen.getHeight (null);                                             //Obtenemos la altura de la imagen
        anchoCelda = anchoImagen / columnasRompecabezas;                                    //Calculamos el ancho de una celda
        altoCelda = alturaImagen / filasRompecabezas;                                       //Calculamos el alto de una celda
        numeroCeldas = filasRompecabezas * columnasRompecabezas;                            //Calculamos el número de celdas
        ultimaCelda = numeroCeldas - 1;                                                     //Obtenemos la celda que será de color negro
        celda = new Image [numeroCeldas];                                                   //Creamos un arreglo de objetos tipo Image
        orden = new int [numeroCeldas];                                                     //Creamos un arreglo de objetos tipo int
        rompecabezas = new JButton[numeroCeldas];                                           //Creamos un arreglo de objetos tipo JButton
        ordenAleatorio = new int [numeroCeldas];
        rand = new Random ();
    }

    public void construyeBotones ()
    {
        for (int y = 0; y < filasRompecabezas; y ++)
        {
            for (int x = 0; x < columnasRompecabezas; x ++)
            {
                imagenCortada = new CropImageFilter (anchoCelda * x, altoCelda * y, anchoCelda, altoCelda);
                imagenFiltrada = new FilteredImageSource (imagen.getSource (), imagenCortada);
                int i = y * columnasRompecabezas + x;
                orden[i] = i;
                celda[i] = createImage (imagenFiltrada);
            }
        }
        for (int i = 0; i < numeroCeldas; i ++)
        {
            add (rompecabezas[i] = new JButton (new ImageIcon (celda [orden [i] ])));
        }
        imagenNegra = new CropImageFilter (anchoCelda, altoCelda, anchoCelda, altoCelda);
        imagenNegraFiltrada = new FilteredImageSource (negro.getSource (), imagenCortada);
        imagenNegraFinal = createImage (imagenNegraFiltrada);
    }

    public void revolverRompecabezas ()
    {
        for (int i = 0; i < numeroCeldas; i ++)
        {
            ordenAleatorio [i] = orden [i];
        }
        for (int i = numeroCeldas; i > 0; i--) 
        {
            int posicion = rand.nextInt(i);
            int auxiliar = ordenAleatorio[i-1];
            ordenAleatorio[i - 1] = ordenAleatorio[posicion];
            ordenAleatorio[posicion] = auxiliar;
        }
        for (int j = 0; j < numeroCeldas; j ++)
        {
            System.out.println ("Numero: " + ordenAleatorio[j]);
        }
        for (int i = 0; i < numeroCeldas; i ++)
        {
            if (ordenAleatorio [i] == orden[ultimaCelda])
            {
                System.out.println ("Ultima celda: " + ordenAleatorio[i] + " en la posicion " + i);
                ultimaCeldaAleatoria = i;
                break;
            }
        }
    }

    public void consruyeVentana ()
    {
        JFrame rompeCabezas = new JFrame ("Rompecabezas");
        rompeCabezas.add ("Center", this);
        rompeCabezas.setDefaultCloseOperation (WindowConstants.EXIT_ON_CLOSE); 
        rompeCabezas.setSize (450, 340);
        rompeCabezas.setVisible (true);
    }

    private void dibujaRompecabezas ()
    {
        for (int i = 0; i < numeroCeldas; i ++)
        {
            if ( ordenAleatorio[i] != ultimaCelda) 
                rompecabezas[i].setIcon (new ImageIcon (celda[ordenAleatorio[i]]));         //Si no es la ultima celda, la dejamos normal
            else
            {
                rompecabezas[i].setIcon (new ImageIcon (imagenNegraFinal));                 //Si es la última celda, la ponemos de color negro
            }
            rompecabezas[i].addActionListener (this);                                       //Agregamos el escucha a cada uno de los botones
        }
    }

    public void actionPerformed (ActionEvent e)
    {
        boton = (JButton) e.getSource ();                                       //Objeto de tipo JButton en el que se produce el evento
        if (boton != rompecabezas [ultimaCeldaAleatoria])
        {
            if (boton == rompecabezas [ultimaCeldaAleatoria + columnasRompecabezas])
                System.out.println ("Se hizo click");
            else if (boton == rompecabezas [ultimaCeldaAleatoria - columnasRompecabezas])
                System.out.println ("Se hizo click");
            else if (boton == rompecabezas [ultimaCeldaAleatoria + 1])
                System.out.println ("Se hizo click");
            else if (boton == rompecabezas [ultimaCeldaAleatoria - 1])
                System.out.println ("Se hizo click");
        }
    }

    public static void main (String args[])
    {  
        new Rompecabezas ();                                            //Creamos un objeto de tipo Rompecabezas
    }

     public Rompecabezas()
    {
        inicializaVariables ();                                         //Llamamos al método inicializaVariables
        construyeBotones ();                                            //Llamamos al método construyeBotones
        revolverRompecabezas ();                                        //Llamamos al método revolverRompecabezas
        dibujaRompecabezas ();                                          //Llamamos al método dibujaRompecabezas
        consruyeVentana ();                                             //Llamamos al método construye ventana
    }
}