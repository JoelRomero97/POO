import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.*;

public class Mosaico extends JFrame implements ActionListener 
{
    JLabel j1;
    JButton B[];
    ImageIcon img[];  
    JPanel a, b;
    JFrame c;
  
	public Mosaico()
	{
	    JPanel a=new JPanel(new GridLayout(4,15));
	    JPanel b=new JPanel();
	    JFrame c=new JFrame();
	    B=new JButton[40];
	    for(int i=0;i<40;i++)
	    {
	        B[i]=new JButton();
	        System.out.println(""+getClass().getResource("resources/img/"+i+".jpg"));
	        B[i].setIcon(new ImageIcon(getClass().getResource("resources/img/"+i+".jpg")));
	        B[i].addActionListener(this);
	        B[i].setBackground(Color.RED);
	        a.add(B[i]);
	    }
	    j1=new JLabel("Selecciona una Imagen");
	    j1.setFont(new Font("Arial",Font.ITALIC,55));
	    j1.setForeground(new Color(128,128,128));
	    b.add(j1);
	    c.add(a,BorderLayout.NORTH);
	    c.add(b,BorderLayout.CENTER);
	    a.setBackground(Color.YELLOW);
	    b.setBackground(Color.YELLOW);
	    c.setSize(1300,700);  
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
	}
	    public static void main(String[] args)
	    {
	        Mosaico a=new Mosaico();
	    }
}