import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JSlider;
import javax.swing.JFrame;
public class Slider
{
	static JSlider c,f; 
	static JLabel label,label1; 
	public static void main(String[] args)
	{
		JFrame ventana = new JFrame("Temperatura");
		ventana.setSize(400, 400);
		JPanel panel = new JPanel(new GridLayout(4,4));
		c = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
		c.setInverted(false);
		c.setPaintTicks(true); 
		c.setMajorTickSpacing(25); 
		c.setMinorTickSpacing(5); 
		c.setPaintLabels(true);
		c.addChangeListener(new Temperatura());
		f = new JSlider(JSlider.HORIZONTAL, 0, 100, 32);
		f.setInverted(false);
		f.setPaintTicks(true); 
		f.setMajorTickSpacing(25); 
		f.setMinorTickSpacing(5); 
		f.setPaintLabels(true);
		f.addChangeListener(new Temperatura());
		label = new JLabel("Centigrados");
		label1 = new JLabel("Farenheit");
		panel.add(label1);
		panel.add(label);
		panel.add(c);
		panel.add(f);
		ventana.add(panel);
		ventana.setVisible(true);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static class Temperatura implements ChangeListener
	{
		public void stateChanged(ChangeEvent e)
		{
			JSlider c = (JSlider)e.getSource();
			int evaluo = c.getValue();
			if( js.equals(f)) {
      		label1.setText( Integer.toString(evaluo) );
     		actcualizaBarraCG( valor );
    		}
    		else if( js.equals(c)) {
      		label.setText( Integer.toString(evaluo) );
      		actualizaBarraFH( evaluo );
    		}


			String nose = Integer.toString(evaluo);
			label.setText(nose);
		}
	} 
}




http://dis.um.es/~bmoros/Tutorial/parte14/cap14-20.html