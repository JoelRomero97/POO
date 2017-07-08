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
//// Para modificar la imagen, llamar a imagen deseada "rompecabezas".				////
////                                                                                ////
////                                                                                ////
//// Autor: Romero Gamarra Joel Mauricio                                            ////
////////////////////////////////////////////////////////////////////////////////////////

public class Rompecabezas extends Applet implements ActionListener
{
    private JButton rompecabezas [];                                    //Arreglo de botones del rompecabezas
    private int filasRompecabezas = 4, columnasRompecabezas = 4;        //Filas y columnas que tendrá el rompecabezas
    private int anchoImagen, alturaImagen;                              //Ancho y Alto de la imagen
    private int anchoCelda, altoCelda;                                  //Ancho y Alto de una celda
    private int nuevaNegra;												//Entero para actualizar la posicion de la celda negra
    private int ultimaCelda, celdaNegra;                      			//Ultima celda que irá de color negro
    private int numeroCeldas;                                           //Numero de celdas
    private Image [] celda;                                             //Arreglo de imagenes
    private int [] orden;                                               //Orden de las imagenes
    private Image imagen;                                               //Imagen a usar como rompecabezas
    private Image imagenAuxiliar;										//Imagen para obtener la imagen del botón presionado
    private ImageIcon iconoAuxiliar;									//Icono para poner imagen al boton presionado
    private CropImageFilter imagenCortada;                              //Filtro de imagen cortada
    private CropImageFilter imagenNegra;                                //Filtro de imagen negra cortada
    private FilteredImageSource imagenFiltrada;                         //Imagen con el filtro aplicado
    private FilteredImageSource imagenNegraFiltrada;                    //Imagen negra con el filtro aplicado
    private Image imagenNegraFinal;                                     //Imagen que usaremos para ultima celda
    private Image negro;                                                //Imagen de color negro que usaremos en la ultima celda
    private int ordenAleatorio [];                                      //Arreglo de numeros aleatorio
    private Random rand;                                                //Para obtener los valores aleatorios
    private JButton boton;                                              //Botón para obtener el evento en el ActionPerformed
    private JLabel ganaste;                                             //Etiqueta para mostrar resultado

    public void inicializaVariables ()
    {
        imagen = (new ImageIcon ("Rompecabezas.jpg")).getImage ();                          //Seleccionamos la imagen para armar
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
        ordenAleatorio = new int [numeroCeldas];											//Creamos un arreglo de enteros
        rand = new Random ();																//Inicializamos un Random
        ganaste = new JLabel ("");															//Inicializamos la etiqueta
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
        for (int i = 0; i < numeroCeldas; i ++)
        {
            if (ordenAleatorio [i] == orden[ultimaCelda])
            {
                celdaNegra = i;
                break;
            }
        }
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

    public void consruyeVentana ()
    {
        JFrame rompeCabezas = new JFrame ("Rompecabezas");
        rompeCabezas.add ("Center", this);
        rompeCabezas.setDefaultCloseOperation (WindowConstants.EXIT_ON_CLOSE); 
        rompeCabezas.setSize (450, 340);
        rompeCabezas.setVisible (true);
    }

    public boolean esCeldaAdyacente (JButton boton, int filaNegra, int columnaNegra)
    {
    	boolean resp = false;
    	if (columnaNegra == 0)													//Estamos en la primera columna
        {
        	if (filaNegra == 0)													//Estamos en la primera fila
        	{
        		if (boton == rompecabezas [celdaNegra + 1] || boton == rompecabezas [celdaNegra + columnasRompecabezas])
        			resp = true;
        	}else if (filaNegra == filasRompecabezas - 1)						//Estamos en la ultima fila
        	{
        		if (boton == rompecabezas [celdaNegra + 1] || boton == rompecabezas [celdaNegra - columnasRompecabezas])
        			resp = true;
        	}else																//Estamos en cualquier otra fila
        	{
        		if (boton == rompecabezas [celdaNegra + 1] || boton == rompecabezas [celdaNegra + columnasRompecabezas] 
        		|| boton == rompecabezas [celdaNegra - columnasRompecabezas])
        			resp = true;
        	}
        }else if (columnaNegra == columnasRompecabezas - 1)						//Estamos en la ultima columna
        {
        	if (filaNegra == 0)													//Estamos en la primera fila
        	{
        		if (boton == rompecabezas [celdaNegra - 1] || boton == rompecabezas [celdaNegra + columnasRompecabezas])
        			resp = true;
        	}else if (filaNegra == filasRompecabezas - 1)						//Estamos en la ultima fila
        	{
        		if (boton == rompecabezas [celdaNegra - 1] || boton == rompecabezas [celdaNegra - columnasRompecabezas])
        			resp = true;
        	}else																//Estamos en cualquier otra fila
        	{
        		if (boton == rompecabezas [celdaNegra - 1] || boton == rompecabezas [celdaNegra + columnasRompecabezas] 
        		|| boton == rompecabezas [celdaNegra - columnasRompecabezas])
        			resp = true;
        	}
        }else																	//Estamos en cualquier otra columna
        {
        	if (filaNegra == 0)													//Estamos en la primera fila
        	{
        		if (boton == rompecabezas [celdaNegra - 1] || boton == rompecabezas [celdaNegra + columnasRompecabezas] 
        		|| boton == rompecabezas [celdaNegra + 1])
        			resp = true;
        	}else if (filaNegra == filasRompecabezas - 1)						//Estamos en la ultima fila
        	{
        		if (boton == rompecabezas [celdaNegra - 1] || boton == rompecabezas [celdaNegra - columnasRompecabezas] 
        		|| boton == rompecabezas [celdaNegra + 1])
        			resp = true;
        	}else																//Estamos en cualquier otra fila
        	{
        		if (boton == rompecabezas [celdaNegra - 1] || boton == rompecabezas [celdaNegra - columnasRompecabezas] 
        		|| boton == rompecabezas [celdaNegra + 1] || boton == rompecabezas [celdaNegra + columnasRompecabezas])
        			resp = true;
        	}
        }
        return resp;
    }

    public int obtenerNuevaCeldaNegra (JButton boton)
    {
    	int i;
    	Image imagenBoton = ((ImageIcon)boton.getIcon()).getImage();;
    	Image imagenesRompecabezas;
    	for (i = 0; i < numeroCeldas; i ++)
    	{
    		imagenesRompecabezas = ((ImageIcon)rompecabezas[i].getIcon()).getImage();
    		if (imagenesRompecabezas.equals (imagenBoton))
    			break;
    	}
    	return i;
    }

    public void actionPerformed (ActionEvent e)
    {
    	int filaNegra, columnaNegra;											//Enteros para comparar el boton presionado con la celda negra
        boton = (JButton) e.getSource ();                                       //Objeto de tipo JButton en el que se produce el evento
        columnaNegra = celdaNegra % columnasRompecabezas;						//Obtenemos la columna de la celda negra
        filaNegra = celdaNegra / columnasRompecabezas;							//Obtenemos la fila de la celda negra
        if (esCeldaAdyacente (boton, filaNegra, columnaNegra))
        {
        	imagenAuxiliar = ((ImageIcon)boton.getIcon()).getImage();			//Obtenemos la imagen del boton presionado
        	iconoAuxiliar = new ImageIcon (imagenAuxiliar);						//Convertimos la imagen del boton a un ImageIcon
        	nuevaNegra = obtenerNuevaCeldaNegra (boton);
        	rompecabezas[celdaNegra].setIcon (iconoAuxiliar);					//Ponemos el ImageIcon en la celda negra
        	boton.setIcon (new ImageIcon (imagenNegraFinal));					//Ponemos en el boton presionado, la imagen negra
        	celdaNegra = nuevaNegra;
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