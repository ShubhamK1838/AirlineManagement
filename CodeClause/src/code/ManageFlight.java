package code;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class ManageFlight extends JPanel implements ActionListener {
	private JLabel flightcode, source, destination, label, takeof, no_ofseats;
	private JButton addnew, update, search, delete;
	private JTextField code, src, dest, tkof, seats;
	JTable table;
	DefaultTableModel model;
	Font font;
	Border border;
	private JScrollPane scrollpane;
	Color color, buttoncolor;public static boolean additionalSize =false; 

	public ManageFlight() {
		buttoncolor = new Color(174, 97, 224);
		color = new Color(205, 196, 230);
		this.setBackground(color);
		font = new Font("Arial", Font.PLAIN, 16);
		border = new EmptyBorder(0, 0, 0, 0);
		this.setLayout(null);
		label = new JLabel("Manage Flight");
		label.setBounds(280, 10, 320, 10);
		label.setFont(font);

		flightcode = new JLabel("Flight Code");
		flightcode.setBounds(30, 20, 90, 30);
		flightcode.setFont(font);
		code = new JTextField();
		code.setBounds(20, 50, 100, 30);
		code.setFont(font);
		code.setBorder(border);
		addnew = new JButton("Add New");
		addnew.setFont(font);
		addnew.setBorder(border);
		addnew.setBounds(70, 100, 100, 30);
		addnew.setBackground(buttoncolor);
		addnew.setForeground(Color.black);
		addnew.addActionListener(this);

		source = new JLabel("Source");
		source.setFont(font);
		source.setBounds(150, 20, 90, 30);
		src = new JTextField();
		src.setBorder(border);
		src.setFont(font);
		src.setBounds(140, 50, 100, 30);
		update = new JButton("Update");
		update.setBackground(addnew.getBackground());
		update.setForeground(addnew.getForeground());
		update.setBounds(200, 100, 100, 30);
		update.setFont(addnew.getFont());
		update.addActionListener(this);

		destination = new JLabel("Destination");
		destination.setFont(font);
		destination.setBounds(280, 20, 90, 30);
		dest = new JTextField();
		dest.setBorder(border);
		dest.setFont(font);
		dest.setBounds(270, 50, 100, 30);
		search = new JButton("Records");
		search.setFont(addnew.getFont());
		search.setBorder(border);
		search.setBackground(addnew.getBackground());
		search.setForeground(addnew.getForeground());
		search.setBounds(330, 100, 100, 30);
		search.addActionListener(this);
		
		takeof = new JLabel("Take Off");
		takeof.setFont(font);
		takeof.setBounds(420, 20, 90, 30);
		tkof = new JTextField();
		tkof.setBorder(border);
		tkof.setFont(font);
		tkof.setBounds(410, 50, 100, 30);
		delete = new JButton("Delete");
		delete.setFont(search.getFont());
		delete.setBackground(search.getBackground());
		delete.setForeground(search.getForeground());
		delete.setBounds(460, 100, 100, 30);
		delete.addActionListener(this);

		no_ofseats = new JLabel("No.of Seats");
		no_ofseats.setFont(font);
		no_ofseats.setBounds(540, 20, 90, 30);
		seats = new JTextField();
		seats.setBorder(border);
		seats.setFont(font);
		seats.setBounds(530, 50, 100, 30);

		// code for table
		String rows[][] = new String[0][0];
		String cols[] = { "Flight Code", "Source", "Destination", "Take OFF", "No.Seats" };
		model = new DefaultTableModel(rows, cols);
		table = new JTable(model);
		scrollpane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_NEVER,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollpane.setBounds(1, 150, 680, 410);

		this.add(scrollpane);
		this.add(delete);
		this.add(search);
		this.add(update);
		this.add(addnew);
		this.add(seats);
		this.add(no_ofseats);
		this.add(tkof);
		this.add(takeof);
		this.add(dest);
		this.add(destination);
		this.add(src);
		this.add(source);
		this.add(code);
		this.add(flightcode);
		
		this.revalidate();

	}

	public void actionPerformed(ActionEvent ae)
	{
		String command = ae.getActionCommand();
		String ele[] = { code.getText(), src.getText(), dest.getText(), tkof.getText(), seats.getText() };

		if (command.equals("Add New")) {
			if (checkValid(ele)) {
				Database.insert("flights",ele[0], ele[1], ele[2], ele[3], ele[4]);
				setRecord();
			}
		} else if (command.equals("Update"))
		{

			if (table.getSelectedRow() != -1) {
					int row = table.getSelectedRow();	
					String c1 = (String) table.getValueAt(row, 0);
					String c2 = (String) table.getValueAt(row, 1);
					String c3 = (String) table.getValueAt(row, 2);
					String c4 = (String) table.getValueAt(row, 3);
					String c5 = (String) table.getValueAt(row, 4);
					System.out.println(c5);
					Database.dataForFlightUpdate(c1, c2, c3, c4, c5);
					setRecord();
				}
		}
		else if(command.equals("Delete"))
		{
			if (table.getSelectedRow() != -1) {
				int row = table.getSelectedRow();
				String c1 = (String) table.getValueAt(row, 0);
				Database.delete(c1);
				setRecord();

			}
			
		}
		else if(command.equals("Records"))
		{
			setRecord();
		}

		setNull();
		}

	

	public boolean checkValid(String[] ele) {

		for (String i : ele) {

			if (i.length() < 1) {
				JOptionPane.showMessageDialog(this, "All Fileds Are Mandatory...", "Alert",
						JOptionPane.WARNING_MESSAGE);
				return false;
			}

		}
		return true;
	}

	public void setNull() {
		JTextField ele[] = { code, src, dest, tkof, seats };

		for (JTextField i : ele) {
			i.setText("");
		}
	}

	public void setRecord() {

		model.setRowCount(0);
		Database.DataForFlight(model,"flights");
		table.revalidate();
	}

}
