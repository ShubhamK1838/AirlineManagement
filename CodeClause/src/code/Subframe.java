package code;

import java.util.Queue;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.BorderLayout;
import java.awt.event.*;

public class Subframe extends JPanel {

	Subframe This;
	public static Timer tt;
	public static JPanel currentPanel;
	public Queue list;

	Subframe() {
		This = this;
		list = SubframeRecords.getList();
		this.setLayout(new BorderLayout());
		tt = new Timer(0, new ActionListener() {
			public void actionPerformed(ActionEvent ae) {

				if (list.size() > 1) {
					This.remove((JPanel) list.element());
					list.poll();
					This.add((JPanel) list.element());
					
					This.revalidate();
					This.repaint();
				
				} else {
					This.add((JPanel) list.element());
					This.revalidate();
					This.repaint();
				}
				Object ob=list.element();
				
				tt.stop();


			}
		});

	}

}
