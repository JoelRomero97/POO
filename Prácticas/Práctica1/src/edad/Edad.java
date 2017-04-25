
package edad;
import javax.swing.*;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener; 

public class Edad extends JFrame implements ActionListener{

public JTextField tfy1,tfy2,tfm1,tfm2,tfd1,tfd2;
private JLabel year1, tx, txa,td, ti, year2, month1, month2, day1, day2, men;
private JButton boted;

 

public Edad (){ 
      setSize (770,200);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      FlowLayout v= new FlowLayout ();
      setLayout (v);
      
      tx= new JLabel ("Bienvenido");
      ti= new JLabel("Los datos que introduzca deben ser numericos y tener el siguiente formato: DD/MM/AAAA.");
      txa= new JLabel ("\nDatos de tu fecha de nacimiento");
      day1= new JLabel ("Dia: ");
      tfd1= new JTextField(2);
      month1= new JLabel ("Mes:");
      tfm1= new JTextField(2);
      year1= new JLabel ("Año: ");
      tfy1= new JTextField (4);
      td= new JLabel ("Datos de la fecha de hoy");
      day2= new JLabel ("Dia: ");
      tfd2= new JTextField(2);
      month2= new JLabel ("Mes:");
      tfm2= new JTextField(2);
      year2= new JLabel ("Año: ");
      tfy2= new JTextField (4);
      boted= new JButton ("Calcular edad");
      men= new JLabel();
      
      
      add(tx);
      add(ti);
      add(txa);
      add(day1);
      add(tfd1);
      add(month1);
      add(tfm1);
      add(year1);
      add(tfy1);
      add(td);
      add(day2);
      add(tfd2);
      add(month2);
      add(tfm2);
      add(year2);
      add(tfy2);
      add(boted);
      add(men);
     
    
      boted.addActionListener(this); 
 }
 
 public static void main (String[] args){
    Edad i= new Edad();
    i.setVisible(true);
 }
    
    public void actionPerformed(ActionEvent e) {
         JButton but=(JButton) e.getSource();
         String cy1,cy2,cm1,cm2,cd1,cd2;
         int y1,y2,m1,m2,d1,d2,yt,mt,dt,r;
  
         cd1=tfd1.getText();
         cd2=tfd2.getText();
         cm1=tfm1.getText();
         cm2=tfm2.getText();
         cy1=tfy1.getText();
         cy2=tfy2.getText();
         
         d1=Integer.parseInt(cd1);
         d2=Integer.parseInt(cd2);
         m1=Integer.parseInt(cm1);
         m2=Integer.parseInt(cm2);
         y1=Integer.parseInt(cy1);
         y2=Integer.parseInt(cy2);
         
           if (but==boted){
         
         if (y2<y1||m1>12||m2>12||d1>31||d2>31){
             men.setText("Los datos son erroneos. Corrijalos por favor");
         }
         
         else if (y2>=y1){  
             
             if(y2==y1&&m1>m2){
                men.setText("Estos datos no son válidos. Rectifiquelos por favor.");
             }
             
             else if (m1==m2&&d1==d2){
               yt=y2-y1;
               mt=0;
               men.setText("Tienes " +yt+" años con " +mt+ " meses");
               }
             
             else if (m1!=m2&&d1==d2){
                 if (m1<m2){
                  mt=m2-m1  ;
                  yt=y2-y1;
                  men.setText("Tienes "+yt+" años con " +mt+ " meses");
             }
                 else if (m1>m2){
                   yt=y2-y1;
                   mt=(12-m1)+m2;
                     if(mt<=12){
                         yt--;
                         }
                   men.setText("Tienes "+yt+" años con " +mt+ " meses");
                 }
             }
             
             else if (m1==m2&&d1!=d2){
               yt=y2-y1;
               mt=0;
               men.setText("Tienes " +yt+" años con " +mt+ " meses");
               }
             
             else if (m1!=m2&&d1!=d2){
                 if (m1<m2){
                  mt=m2-m1  ;
                  yt=y2-y1;
                  men.setText("Tienes "+yt+" años con " +mt+ " meses");
             }
                 else if (m1>m2){
                   yt=y2-y1;
                   mt=(12-m1)+m2;
                     if(mt<=12){
                         yt--;
                         }
                   men.setText("Tienes "+yt+" años con " +mt+ " meses");
                 }
             }
            
         }
         
}
}
}
