package code;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;


public class Ticketcancel extends JPanel implements ActionListener {

	JLabel psidl;
	JTextField psidf;
	JButton cancel,reset ;
	Color buttoncolor, color;
	Font font;
	EmptyBorder border;
	ButtonGroup genders;
	
	Ticketcancel(){
		buttoncolor = new Color(174, 97, 224);
		border = new EmptyBorder(0, 0, 0, 0);
		color = new Color(205, 196, 230);
		font = new Font("Arial", Font.PLAIN, 16);
		this.setBackground(color);
		this.setLayout(null);
		
		psidl=new JLabel("Passanger Id");
		psidl.setFont(font);
		psidl.setBounds(200,200,100,40);
		
		psidf=new JTextField();
		psidf.setFont(font);
		psidf.setBorder(border);
		psidf.setBounds(310,205,140,30);
		
		cancel=new JButton("Cancel");
		cancel.setFont(font);
		cancel.setBorder(border);
		cancel.setBounds(210,250,90,30);
		cancel.setBackground(buttoncolor);
		cancel.setForeground(Color.black);
		cancel.addActionListener(this);
	
		reset=new JButton("Reset");
		reset.setFont(font);
		reset.setBorder(border);
		reset.setBounds(350,250,90,30);
		reset.setBackground(buttoncolor);
		reset.setForeground(Color.black);
		reset.addActionListener(this);
		
		this.add(reset);
		this.add(cancel);
		this.add(psidf);
		this.add(psidl);
		
		
		
	}
	
	public void actionPerformed(ActionEvent ae) {
		String command =ae.getActionCommand();
		
		if(command.equals("Reset")) {
			psidf.setText("");
		}
		else if(command.equals("Cancel")) 
		{
			
			if(!(psidf.getText().length()<1)) {
				
				int rst=Database.cancelTicket(psidf.getText());
				if(rst!=0) {
					JOptionPane.showMessageDialog(this,"Ticket Are Cancelled...");
				}
				else {
					JOptionPane.showMessageDialog(this,"Please Enter Correct Passanger Id...");
					
				}
			}
			else {
			JOptionPane.showMessageDialog(this, "Enter Passanger Id...");
			}
		}
		
		
	}
}
