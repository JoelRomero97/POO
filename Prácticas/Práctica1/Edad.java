import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

////////////////////////////////////////////////////////////////////////////////////////
////                                    Edad.java                                   ////
////                                                                                ////
////                                                                                ////
//// Realizamos el cálculo de una edad en java utilizando un JFrame con un Layout   ////
//// y creando un panel central y uno inferior, obteniendo el año de nacimiento     ////
//// del usuario al igual que el año actual y apretando un botón mostramos su edad  ////
//// en el panel inferior del BorderLayout.                                         ////
////                                                                                ////
////                                                                                ////
//// Autor: Romero Gamarra Joel Mauricio                                            ////
////////////////////////////////////////////////////////////////////////////////////////

public class Edad extends JFrame implements ActionListener 
{
	private JLabel etiquetaActual, etiquetaNacimiento, calculoEdad;				//Etiquetas para indicar que ingresar en cada campo
    private JPanel panelCentral, panelInferior;									//Paneles para agregar botones, etiquetas y campos de texto
    private JButton aceptar, boton;												//Botón para realizar el cálculo
    private JTextField anioActualIntroducido, anioNacimientoIntroducido;		//TextField para introducir los años
    private String anioNacimiento, anioActual;									//Cadenas para recibir la edad de los TextField
    private int fechaHoy, fechaUsuario, calculo;								//Enteros para hacer el calculo de edad

    public void construyePanelCentral ()
    {
    	panelCentral = new JPanel ();											//Creamos un objeto de tipo JPanel
    	etiquetaActual = new JLabel ("Introduce fecha actual");					//Creamos un objeto de tipo JLabel
    	anioActualIntroducido = new JTextField (4);								//Creamos un objeto de tipo JTextField
    	etiquetaNacimiento = new JLabel ("Introduce tu fecha de nacimiento");	//Creamos un objeto de tipo JLabel
    	anioNacimientoIntroducido = new JTextField (4);							//Creamos un objeto de tipo JTextField
    	panelCentral.setLayout (new FlowLayout ());								//Agregamos un Layout al panel
    	panelCentral.setBackground (Color.CYAN);								//Agregamos un color de fondo cyan
    	panelCentral.add (etiquetaActual);										//Agregamos el objeto de tipo JLabel
    	panelCentral.add (anioActualIntroducido);								//Agregamos el objeto de tipo JTextField
    	panelCentral.add (etiquetaNacimiento);									//Agregamos el objeto de tipo JLabel
    	panelCentral.add (anioNacimientoIntroducido);							//Agregamos el objeto de tipo JTextField
    }

    public void construyePanelInferior ()
    {
    	panelInferior = new JPanel();											//Creamos objeto tipo JPanel
        aceptar = new JButton("Calcular mi edad");								//Creamos un objeto tipo JButton
        calculoEdad = new JLabel ("");											//Creamos un objeto tipo JLabel
        panelInferior.setLayout (new FlowLayout ());							//Agregamos un Layout al panel
        panelInferior.setBackground (Color.BLACK);								//Agregamos color de fondo negro
        panelInferior.add (calculoEdad);										//Agregamos el objeto de tipo JLabel
        panelInferior.add (aceptar);											//Agregamos el objeto de tipo JButton
        aceptar.addActionListener (this);										//Agregamos el escucha al objeto de tipo JButton
    }

    public void construyeVentana ()
    {
    	JFrame ventana = new JFrame ();											//Creamos un objeto de tipo JFrame
    	ventana.setLayout (new BorderLayout ());								//Agregamos un Layout que divide la ventana en 5
    	ventana.add (panelInferior, BorderLayout.SOUTH);						//Agregamos el objeto de tipo JPanel a la parte baja (construyePanelInferior)
    	ventana.add (panelCentral, BorderLayout.CENTER);						//Agregamos el objeto de tipo JPanel a la parte central (construyePanelCentral)
    	ventana.setVisible (true);												//Hacemos visible el objeto de tipo JFrame
    	ventana.setSize (550, 300);												//Le asignamos un tamaño al objeto de tipo JFrame de 600 (ancho) x 300 (alto)
    	ventana.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);				//Sale de la aplicación al cerrar el objeto de tipo JFrame
    }

    public void actionPerformed (ActionEvent e)
    {
    	boton = (JButton) e.getSource ();										//Objeto de tipo JButton en el que se produce el evento
    	anioActual = anioActualIntroducido.getText ();							//Recibimos la cadena 1 introducida por el usuario
    	anioNacimiento = anioNacimientoIntroducido.getText ();					//Recibimos la cadena 2 introducida por el usuario
    	fechaHoy = Integer.parseInt (anioActual);								//Convertimos a entero la cadena 1 para hacer el cálculo
    	fechaUsuario = Integer.parseInt (anioNacimiento);						//Convertimos a entero la cadena 2 para hacer el cálculo
    	if (boton == aceptar)													//Igualamos el botón recien creado con el botón del método construyePanelInferior
    	{
    		calculo = fechaHoy - fechaUsuario;									//Realizamos el cálculo de la edad actual
    		calculoEdad.setText ("Su edad es de " + calculo + " a\u00f1os.");	//Imprimimos el resultado en el objeto de tipo JLabel del método construyePanelInferior
    	}
    }

    public static void main(String[] args)
    {
    	new Edad ();															//Creamos un objeto de tipo Edad
    }

    public Edad ()
    {
    	construyePanelCentral ();												//Invocamos el método construyePanelCentral en el constructor de la clase Edad
    	construyePanelInferior ();												//Invocamos el método construyePanelInferior en el constructor de la clase Edad
    	construyeVentana ();													//Invocamos el método construyeVentana en el constructor de la clase Edad
    }
}