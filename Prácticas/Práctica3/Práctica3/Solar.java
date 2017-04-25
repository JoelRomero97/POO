import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.universe.*;
import javax.media.j3d.*;
import javax.vecmath.*;
import java.awt.*;
import javax.swing.*;
import java.util.*;

public class Solar
{	
	public Solar()
	{	 
		BranchGroup group = new BranchGroup();
		Appearance appsol = new Appearance();
		Appearance appearth = new Appearance();
	        Appearance appjupiter = new Appearance();
	        Appearance appsaturno = new Appearance();

	        TextureLoader tex=new TextureLoader("TIERRA.JPG", null);
		appearth.setTexture(tex.getTexture());
	        tex=new TextureLoader("JUPITER.JPG", null);
		appjupiter.setTexture(tex.getTexture());
	        tex=new TextureLoader("SATURNO.JPG", null);
		appsaturno.setTexture(tex.getTexture());
		tex=new TextureLoader("SOL.JPG", null);
		appsol.setTexture(tex.getTexture());

		Sphere jupiter= new Sphere(0.20f, Primitive.GENERATE_NORMALS | 	
		Primitive.GENERATE_TEXTURE_COORDS, 32, appjupiter);
	        Sphere saturno= new Sphere(0.10f, Primitive.GENERATE_NORMALS | 	
		Primitive.GENERATE_TEXTURE_COORDS, 32, appsaturno);
	        Sphere earth = new Sphere(0.055f, Primitive.GENERATE_NORMALS | 		
		Primitive.GENERATE_TEXTURE_COORDS, 32, appearth);
		Sphere sol = new Sphere(0.35f, Primitive.GENERATE_NORMALS | 
		Primitive.GENERATE_TEXTURE_COORDS, 32, appsol);

		TransformGroup earthRotXformGroup = Posi.rotate(earth, new Alpha(-1, 1250));
	        TransformGroup jupiterRotXformGroup = Posi.rotate(jupiter, new Alpha(-1, 1250));
	        TransformGroup saturnoRotXformGroup = Posi.rotate(saturno, new Alpha(-1, 1250));
		TransformGroup solRotXformGroup = Posi.rotate(sol, new Alpha(-1, 1000));

		TransformGroup earthTransXformGroup = Posi.translate(earthRotXformGroup,
		new Vector3f(0.0f, 0.0f, 0.7f));
	        TransformGroup jupiterTransXformGroup = Posi.translate(jupiterRotXformGroup,
		new Vector3f(0.0f, 0.0f, 0.8f));
	        TransformGroup saturnoTransXformGroup = Posi.translate(saturnoRotXformGroup,
		new Vector3f(0.0f, 0.0f, 0.9f));
	        
		TransformGroup earthRotGroupXformGroup = Posi.rotate(earthTransXformGroup, new Alpha(-1, 5000));
		    	group.addChild(earthRotGroupXformGroup);    	
		TransformGroup jupiterRotGroupXformGroup = Posi.rotate(jupiterTransXformGroup, new Alpha(-1, 8000));
		    	group.addChild(jupiterRotGroupXformGroup);
		    	group.addChild(solRotXformGroup);
		TransformGroup saturnoRotGroupXformGroup = Posi.rotate(saturnoTransXformGroup, new Alpha(-1, 10000));
		    	group.addChild(saturnoRotGroupXformGroup);
	    	GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
	    	Canvas3D canvas = new Canvas3D(config); canvas.setSize(700, 700);
	    	SimpleUniverse universe = new SimpleUniverse(canvas);
	    	universe.getViewingPlatform().setNominalViewingTransform();
	    	universe.addBranchGraph(group);  
	    	JFrame f = new JFrame("Planetario");
	    	f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);  
	    	f.add(canvas); f.pack(); f.setVisible(true); 
	    }
	public static void main(String a[]) 
	{ 
		new Solar();
	}
}                                                                         
