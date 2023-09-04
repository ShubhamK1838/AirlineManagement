package code;
import java.awt.Color;
import java.awt.Font;

import javax.swing.*;
import javax.swing.border.EmptyBorder; 
public class Home  extends JPanel{
	
	Font font; EmptyBorder border;
	Color color, buttoncolor;
	JLabel label,label2;
	JLabel anim;
	public Home() {
		this.setLayout(null);
		buttoncolor = new Color(174, 97, 224);
		color = new Color(205, 196, 230);
		this.setBackground(color);
		font = new Font("Arial", Font.PLAIN, 16);
		border = new EmptyBorder(0, 0, 0, 0);
		
		label=new JLabel("WellCome To...");
		label.setFont(new Font("Arial",Font.PLAIN,30));
		label.setBorder(border);
		label.setBounds(250,30,300,50);
		
		label2=new JLabel("Airline reservation system");
		label2.setFont(new Font("Arial",Font.PLAIN,30));
		label2.setBorder(border);
		label2.setBounds(170,80,400,50);
		
		
		ImageIcon img=new ImageIcon("images\\plane.gif");
		anim=new JLabel(img);
		anim.setLocation(100,170);
		anim.setSize(img.getIconWidth(),img.getIconHeight());
		
		
		this.add(anim);
		this.add(label2);
		this.add(label);
		
	}
	
	
}
