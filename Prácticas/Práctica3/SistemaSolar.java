import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.universe.*;
import javax.media.j3d.*;
import javax.vecmath.*;
import java.awt.*;
import javax.swing.*;

////////////////////////////////////////////////////////////////////////////////////////
////                                 SistemaSolar.java                              ////
////                                                                                ////
////                                                                                ////
//// Recreación del sistema solar con un sol en el centro y 4 planetas haciendo sus ////
//// respectivos movimientos de rotación y traslación haciendo uso de Java3D.		////
//// Compilación: javac SistemaSolar.java 											////
//// Ejecución: java -cp .;j3dcore.jar;j3dutils.jar;vecmath.jar SistemaSolar 		////
//// Ejecución en Linux: Cambiar los ";" por ":"									////
////                                                                                ////
////                                                                                ////
//// Autor: Romero Gamarra Joel Mauricio                                            ////
////////////////////////////////////////////////////////////////////////////////////////

public class SistemaSolar 
{
	private JFrame planetas;
	private BranchGroup planetario;
	private Appearance aparienciaSol;
	private Appearance aparienciaMercurio;
	private Appearance aparienciaVenus;
	private Appearance aparienciaTierra;
	private Appearance aparienciaMarte;
	private TextureLoader textura;
	private Sphere sol;
	private Sphere mercurio;
	private Sphere venus;
	private Sphere tierra;
	private Sphere marte;
	private TransformGroup rotacionSol;
	private TransformGroup rotacionMercurio;
	private TransformGroup traslacionMercurio;
	private TransformGroup rotacionMercurio2;
	private TransformGroup rotacionVenus;
	private TransformGroup traslacionVenus;
	private TransformGroup rotacionVenus2;
	private TransformGroup rotacionTierra;
	private TransformGroup traslacionTierra;
	private TransformGroup rotacionTierra2;
	private TransformGroup rotacionMarte;
	private TransformGroup traslacionMarte;
	private TransformGroup rotacionMarte2;
	private GraphicsConfiguration configuracion;
	private Canvas3D lienzo;
	private SimpleUniverse universo;

	public void inicializaVariables ()
	{
		planetas = new JFrame("Planetario");
    	planetas.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);  
		planetario = new BranchGroup ();
		configuracion = SimpleUniverse.getPreferredConfiguration();
		lienzo = new Canvas3D(configuracion);
		lienzo.setSize(400, 400);
		universo = new SimpleUniverse(lienzo);
		universo.getViewingPlatform().setNominalViewingTransform();
    	planetas.add(lienzo); 
    	planetas.pack();
    	planetas.setSize (700, 500);
    	planetas.setVisible(true); 
		aparienciaSol = new Appearance ();
		aparienciaMercurio = new Appearance ();
		aparienciaVenus = new Appearance ();
		aparienciaTierra = new Appearance ();
		aparienciaMarte = new Appearance ();
	}

	public void ponerTexturas ()
	{
		textura = new TextureLoader("Sol.jpg", null);
		aparienciaSol.setTexture(textura.getTexture());
		sol = new Sphere(0.45f, Primitive.GENERATE_NORMALS | Primitive.GENERATE_TEXTURE_COORDS, 32, aparienciaSol);
		textura = new TextureLoader ("Mercurio.jpg", null);
		aparienciaMercurio.setTexture (textura.getTexture ());
		mercurio = new Sphere(0.035f, Primitive.GENERATE_NORMALS | Primitive.GENERATE_TEXTURE_COORDS, 32, aparienciaMercurio);
		textura = new TextureLoader ("Venus.jpg", null);
		aparienciaVenus.setTexture (textura.getTexture ());
		venus = new Sphere(0.095f, Primitive.GENERATE_NORMALS | Primitive.GENERATE_TEXTURE_COORDS, 32, aparienciaVenus);
		textura = new TextureLoader("Tierra.jpg", null);
		aparienciaTierra.setTexture(textura.getTexture());
		tierra = new Sphere (0.085f, Primitive.GENERATE_NORMALS | Primitive.GENERATE_TEXTURE_COORDS, 32, aparienciaTierra);
		textura = new TextureLoader ("Marte.jpg", null);
		aparienciaMarte.setTexture (textura.getTexture ());
		marte = new Sphere(0.075f, Primitive.GENERATE_NORMALS | Primitive.GENERATE_TEXTURE_COORDS, 32, aparienciaMarte);
	}

	public void moverPlanetas ()
	{
		rotacionSol = Movimiento.rotar (sol, new Alpha (-1, 1250));
		rotacionMercurio = Movimiento.rotar (mercurio, new Alpha (-1, 1250));
		traslacionMercurio = Movimiento.trasladar (rotacionMercurio, new Vector3f (0.0f, 0.0f, 0.5f));
		rotacionMercurio2 = Movimiento.rotar(traslacionMercurio, new Alpha(-1, 5000));
		rotacionTierra = Movimiento.rotar (tierra, new Alpha (-1, 1250));
		traslacionTierra = Movimiento.trasladar (rotacionTierra, new Vector3f (0.0f, 0.0f, 0.9f));
		rotacionTierra2 = Movimiento.rotar(traslacionTierra, new Alpha(-1, 5000));
	}

	public void pintarPlanetas ()
	{
		planetario.addChild (rotacionSol);
		planetario.addChild (rotacionTierra2);
		planetario.addChild (rotacionMercurio2);
    	universo.addBranchGraph(planetario);
	}

	public SistemaSolar()
	{
		inicializaVariables ();
		ponerTexturas ();
		moverPlanetas ();
		pintarPlanetas ();
    }

	public static void main(String a[])
	{
		new SistemaSolar();
	}
}