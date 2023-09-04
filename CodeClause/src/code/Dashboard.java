
package code;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.*;
import java.util.Queue;
import java.awt.*;

public class Dashboard extends JPanel implements ActionListener {
	JButton tabs[];//
	String tabitems[];
	public Queue list;

	int y;

	public Dashboard() {
		list = SubframeRecords.getList();
		y = 20;
		tabitems = new String[] { "Home", "Manage Flights", "Manage Passanger", "Ticket Booking", "Ticket Cancelation" };
		this.setBackground(Color.red);
		Font font = new Font("Arial", Font.PLAIN, 17);

		this.setLayout(null);
		tabs = new JButton[5];
		for (int i = 0; i < 5; i++) {
			tabs[i] = new JButton(tabitems[i]);
			tabs[i].setBounds(16, y, 170, 30);
			tabs[i].setFont(font);
			tabs[i].setBackground(new Color(174, 97, 224));
			tabs[i].setForeground(Color.black);
			tabs[i].setBorder(new EmptyBorder(0, 0, 0, 0));
			tabs[i].addActionListener(this);
			y += 40;
			this.add(tabs[i]);
		}

	}

	public void actionPerformed(ActionEvent ae) {
		String command = (String) ae.getActionCommand();

		if (tabs[0].getText().equals(command)) {
			list.add(new Home());
			Subframe.tt.start();
			Window.windowObject.originalSize(); 

		} else if (tabs[1].getText().equals(command)) {
			list.add(new ManageFlight());
			Subframe.tt.start();
			Window.windowObject.originalSize(); 
		}
		else if(tabs[2].getText().equals(command)) {
			
			list.add(new ManagePasanger());
			Subframe.tt.start();
			Window.windowObject.originalSize(); 
		}
		else if(tabs[3].getText().equals(command)) {
			list.add(new Ticketbook());
			Window.windowObject.additionalSize(); 
			Subframe.tt.start();
		}
		else if(tabs[4].getText().equals(command)) {
			list.add(new Ticketcancel());
			Window.windowObject.originalSize(); 
			Subframe.tt.start();
		}

	}

}
