import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;
import javax.swing.event.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.JSlider;
import javax.swing.JFrame;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Mosaico extends JFrame implements ActionListener 
{
    static JLabel j1;
    static JButton B[];
    ImageIcon img[]; 
    JPanel a, b,panel;
    JFrame c;
    static JSlider slider; 
    static JLabel label; 
  
public Mosaico()
{
    setLayout(new BorderLayout());
    JPanel a=new JPanel(new GridLayout(4,15));
    JPanel b=new JPanel();
    JFrame c=new JFrame();
    JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 5, 2);
    B=new JButton[40];
    for(int i=0;i<40;i++)
    {
        B[i]=new JButton();
        System.out.println(""+getClass().getResource("resources/img/"+i+".jpg"));
        B[i].setIcon(new ImageIcon(getClass().getResource("resources/img/"+i+".jpg")));
        B[i].setPreferredSize(new Dimension(110,110));
        /*BufferedImage bi = new BufferedImage(2 * B[i].getWidth(null),2* B[i].getHeight(null),BufferedImage.TYPE_INT_ARGB);
        Graphics2D grph = (Graphics2D) bi.getGraphics();
        grph.scale(2, 2);
        grph.drawImage(B[i], 0, 0, null);
        grph.dispose();*/
        B[i].repaint();
        B[i].addActionListener(this);
        B[i].setBackground(Color.RED);
        a.add(B[i]);
    }
    label = new JLabel("Escala de Imagen");
    j1=new JLabel("Selecciona una Imagen"); //TEXTO A MOSTRAR
    j1.setFont(new Font("Arial",Font.ITALIC,30));  //TAMAÑO Y FUENTE
    j1.setForeground(new Color(128,128,128));   //COLOR DEL TEXTO
    b.add(j1);
    c.add(a,BorderLayout.NORTH);
    c.add(b,BorderLayout.CENTER);
    a.setBackground(Color.YELLOW);
    b.setBackground(Color.YELLOW);
    c.setSize(1000,850); 
    slider.setInverted(false);
	slider.setBounds(10,300,230,35);
	slider.setPaintTicks(true); 
	slider.setMajorTickSpacing(1); 
	slider.setMinorTickSpacing(1); 
	slider.setPaintLabels(true);
    slider.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));
	slider.addChangeListener(new Escala()); 
    b.add(label);
	b.add(slider);  
    c.add(b);
    c.setVisible(true);
    c.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   
}

public void actionPerformed(ActionEvent e)
{
    JButton b=(JButton)e.getSource();
    String a;
    j1.setText("");
    a=""+b.getIcon(); 
    String a1[]=a.split("/");
    j1.setIcon(new ImageIcon(getClass().getResource("resources/img/"+a1[a1.length-1])));
    j1.setPreferredSize(new Dimension(110,110));

}



    public static void main(String[] args) 
    {
        Mosaico a=new Mosaico();
	}
	public static class Escala implements ChangeListener
	{
		public void stateChanged(ChangeEvent e)
		{
            JSlider slider = (JSlider)e.getSource();   //SE RECIBE EL EVENTO DEL SLIDER
			int evaluo = slider.getValue();
            j1.setPreferredSize(new Dimension((evaluo+1)*60,(evaluo+1)*60));
			String nose = Integer.toString(evaluo);
			label.setText(nose);
		}
	} 
}    