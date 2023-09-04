package code;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;

public class ManagePasanger extends JPanel implements ActionListener {

	private JLabel psname, gender, nationility, psnumber, phone;
	private JButton addnew, update, records, delete;
	private JTextField psnamef, psnumberf, phonef;
	private JComboBox nationilityf;
	private JRadioButton genderf, genderm;
	ButtonGroup genders;
	JTable table;
	DefaultTableModel model;
	Font font;
	Border border;
	private JScrollPane scrollpane;
	Color color, buttoncolor;
	public static boolean additionalSize =false;

	public ManagePasanger() {
		buttoncolor = new Color(174, 97, 224);
		color = new Color(205, 196, 230);
		this.setBackground(color);
		font = new Font("Arial", Font.PLAIN, 16);
		border = new EmptyBorder(0, 0, 0, 0);
		this.setLayout(null);

		psname = new JLabel("Passanger Name");
		psname.setBounds(30, 20, 130, 30);
		psname.setFont(font);
		psnamef = new JTextField();
		psnamef.setBounds(30, 50, 130, 30);
		psnamef.setFont(font);
		psnamef.setBorder(border);
		addnew = new JButton("Add New");
		addnew.setFont(font);
		addnew.setBorder(border);
		addnew.setBounds(100, 100, 100, 30);
		addnew.setBackground(buttoncolor);
		addnew.setForeground(Color.black);
		addnew.addActionListener(this);

		gender = new JLabel("Gender");
		gender.setFont(font);
		gender.setBounds(190, 20, 90, 30);
		genderf = new JRadioButton("F");
		genderf.setBorder(border);
		genderf.setFont(font);
		genderf.setBounds(180, 50, 30, 30);
		genderf.setBackground(this.getBackground());
		genderm = new JRadioButton("M");
		genderm.setBorder(border);
		genderm.setFont(font);
		genderm.setBounds(220, 50, 30, 30);
		genderm.setBackground(this.getBackground());
		genders = new ButtonGroup();
		genders.add(genderm);
		genders.add(genderf);
		update = new JButton("Update");
		update.setBackground(addnew.getBackground());
		update.setForeground(addnew.getForeground());
		update.setBounds(200 + 30, 100, 100, 30);
		update.setFont(addnew.getFont());
		update.addActionListener(this);

		String country[] = { "Indian", "American", "Canadian", "Mexican", "British", "French", "German", "Spanish",
				"Italian", "Chinese", "Japanese", "Australin", "Brazilian", "Russian" };
		nationility = new JLabel("Nationility");
		nationility.setFont(font);
		nationility.setBounds(290, 20, 900, 30);
		nationilityf = new JComboBox(country);
		nationilityf.setBorder(border);
		nationilityf.setFont(font);
		nationilityf.setBounds(270, 50, 100, 30);
		records = new JButton("Records");
		records.setFont(addnew.getFont());
		records.setBorder(border);
		records.setBackground(addnew.getBackground());
		records.setForeground(addnew.getForeground());
		records.setBounds(360, 100, 100, 30);
		records.addActionListener(this);

		psnumber = new JLabel("Passport No");
		psnumber.setFont(font);
		psnumber.setBounds(420, 20, 100, 30);
		psnumberf = new JTextField();
		psnumberf.setBorder(border);
		psnumberf.setFont(font);
		psnumberf.setBounds(410, 50, 100, 30);
		delete = new JButton("Delete");
		delete.setFont(records.getFont());
		delete.setBackground(records.getBackground());
		delete.setForeground(records.getForeground());
		delete.setBounds(490, 100, 100, 30);
		delete.addActionListener(this);

		phone = new JLabel("Phone No.");
		phone.setFont(font);
		phone.setBounds(540, 20, 90, 30);
		phonef = new JTextField();
		phonef.setBorder(border);
		phonef.setFont(font);
		phonef.setBounds(530, 50, 100, 30);

		// code for table
		String rows[][] = new String[0][0];
		String cols[] = { "Passanger name", "Gender", "Nationility", "Passport Number", "Phone Number" };
		model = new DefaultTableModel(rows, cols);
		table = new JTable(model);
		scrollpane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_NEVER,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollpane.setBounds(1, 150, 680, 410);

		this.add(scrollpane);
		this.add(phone);
		this.add(phonef);
		this.add(delete);
		this.add(psnumber);
		this.add(psnumberf);
		this.add(records);
		this.add(nationilityf);
		this.add(nationility);
		this.add(genderm);
		this.add(genderf);
		this.add(update);
		this.add(gender);
		this.add(psname);
		this.add(psnamef);
		this.add(addnew);

	}

	public void actionPerformed(ActionEvent ae) {
		String command = ae.getActionCommand();
//		JTextField psnamef, psnumberf, phonef;
		String gender = genderm.isSelected() ? "Male" : "Female";
		String ele[] = { psnamef.getText(), gender, (String) nationilityf.getSelectedItem(), psnumberf.getText(),
				phonef.getText() };
		
		if (command.equals("Add New")) {

			if (checkValid(ele)) {
				Database.insert("passanger",ele[0], ele[1], ele[2], ele[3], ele[4]);
				setRecord();
			}
		}
		else if(command.equals("Update")) {
			if (table.getSelectedRow() != -1) {
				int row = table.getSelectedRow();	
				String c1 = (String) table.getValueAt(row, 0);
				String c2 = (String) table.getValueAt(row, 1);
				String c3 = (String) table.getValueAt(row, 2);
				String c4 = (String) table.getValueAt(row, 3);
				String c5 = (String) table.getValueAt(row, 4);
				Database.dataForpasUpdate(c1, c2,c3, c4, c5);
				setRecord();
			}
		}
		else if(command.equals("Delete")) {
			if (table.getSelectedRow() != -1) {
				int row = table.getSelectedRow();
				String c1 = (String) table.getValueAt(row, 3);
				Database.deletePas(c1);
				setRecord();

			}
			
		}
		else if(command.equals("Records")) {
			setRecord();
		}
		setNull();

	}

	
	public void setNull() {
		JTextField ele[] = {psnamef, psnumberf, phonef};
		genderf.setSelected(false);
		genderm.setSelected(false);

		for (JTextField i : ele) {
			i.setText("");
		}
	}

	public boolean checkValid(String[] ele) {

		for (String i : ele) {

			if (i.length() < 1) {
				JOptionPane.showMessageDialog(this, "All Fileds Are Mandatory...", "Alert",
						JOptionPane.WARNING_MESSAGE);
				return false;
			}
		}
		if (genderm.isSelected() || genderf.isSelected()) {
			return true;
		} else {
			JOptionPane.showMessageDialog(this, "All Fileds Are Mandatory...", "Alert", JOptionPane.WARNING_MESSAGE);
			return false;
		}
	}
	public void setRecord() {

		model.setRowCount(0);
		Database.DataForFlight(model,"passanger");
	}

}
