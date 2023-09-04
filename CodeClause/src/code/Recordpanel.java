package code;

import java.awt.*;
import java.util.*;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;

public class Recordpanel extends JPanel {
	DefaultTableModel model;

	public Recordpanel() {
		this.setLayout(new FlowLayout());
//		this.setBackground(Color.BLACK);
		String rows[][] = new String[0][0];
		String cols[] = { "Name", "E-mail" };

		model = new DefaultTableModel(rows, cols);

		JTable tb = new JTable(model);
		
		tb.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {

				int row=tb.getSelectedRow();
//				 tb.getRow(row);
				ArrayList lst=new ArrayList();
						lst.add(tb.getValueAt(row, 0));
						lst.add(tb.getValueAt(row, 1));
						
						System.out.println(lst);
						
						
					
			}
		});

		this.add(new JScrollPane(tb));

		this.revalidate();

	}

	public DefaultTableModel getModel() {
		return this.model;
	}

}
