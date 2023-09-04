package code;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.util.Random;

public class Ticketbook extends JPanel implements ActionListener {

	JLabel psidl, psnamel, flcodel, gender, psnumberl, amtl, nationalityl;
	JTextField psidf, psnamef, psnumberf, amtf;
	JRadioButton genderm, genderf;
	JComboBox flightcode, nationalityf;
	JButton book, reset, records;

	Color buttoncolor, color;
	Font font;
	EmptyBorder border;
	ButtonGroup genders;
	JTable table; 
	DefaultTableModel model;
	JScrollPane scrollpane;

	public Ticketbook() {
		buttoncolor = new Color(174, 97, 224);
		color = new Color(205, 196, 230);
		this.setBackground(color);
		font = new Font("Arial", Font.PLAIN, 16);
		border = new EmptyBorder(0, 0, 0, 0);
		this.setLayout(null);

		psidl = new JLabel("Passanger ID");
		psidl.setBounds(20, 20, 100, 30);
		psidl.setFont(font);
		psidf = new JTextField(psId());
		psidf.setBounds(20, 50, 100, 30);
		psidf.setBorder(border);
		psidf.setFont(font);
		psidf.setEditable(false);
		psidf.setBackground(Color.white);

		psnamel = new JLabel("Passanger Name");
		psnamel.setFont(font);
		psnamel.setBounds(130, 20, 130, 30);
		psnamef = new JTextField();
		psnamef.setFont(font);
		psnamef.setBorder(border);
		psnamef.setBounds(130, 50, 130, 30);

		flcodel = new JLabel("Flight Code");
		flcodel.setFont(font);
		flcodel.setBounds(280, 20, 100, 30);
		flightcode = new JComboBox(Database.getFlightCode());
		flightcode.setFont(font);
		flightcode.setBorder(border);
		flightcode.setBounds(280, 50, 100, 29);

		gender = new JLabel("Gender");
		gender.setFont(font);
		gender.setBounds(390, 20, 100, 30);
		genderf = new JRadioButton("F");
		genderf.setBorder(border);
		genderf.setFont(font);
		genderf.setBounds(390, 50, 30, 30);
		genderf.setBackground(this.getBackground());
		genderm = new JRadioButton("M");
		genderm.setBorder(border);
		genderm.setFont(font);
		genderm.setBounds(420, 50, 30, 30);
		genderm.setBackground(this.getBackground());
		genders = new ButtonGroup();
		genders.add(genderm);
		genders.add(genderf);

		psnumberl = new JLabel("Passport No.");
		psnumberl.setFont(font);
		psnumberl.setBounds(470, 20, 100, 30);
		psnumberf = new JTextField();
		psnumberf.setBorder(border);
		psnumberf.setFont(font);
		psnumberf.setBounds(470, 50, 100, 30);

		amtl = new JLabel("Amount");
		amtl.setFont(font);
		amtl.setBounds(600, 20, 100, 30);
		amtf = new JTextField();
		amtf.setFont(font);
		amtf.setBorder(border);
		amtf.setBounds(580, 50, 100, 30);

		String country[] = { "Indian", "American", "Canadian", "Mexican", "British", "French", "German", "Spanish",
				"Italian", "Chinese", "Japanese", "Australin", "Brazilian", "Russian" };
		nationalityl = new JLabel("Nationality");
		nationalityl.setFont(font);
		nationalityl.setBounds(700, 20, 100, 30);
		nationalityf = new JComboBox(country);
		nationalityf.setFont(font);
		nationalityf.setBorder(border);
		nationalityf.setBounds(700, 50, 100, 30);

		book = new JButton("Book");
		book.setFont(font);
		book.setBorder(border);
		book.setBackground(buttoncolor);
		book.setBounds(20, 100, 250, 30);
		book.addActionListener(this);

		reset = new JButton("Reset");
		reset.setFont(font);
		reset.setBorder(border);
		reset.setBackground(buttoncolor);
		reset.setBounds(285, 100, 250, 30);
		reset.addActionListener(this);

		records = new JButton("Records");
		records.setFont(font);
		records.setBorder(border);
		records.setBackground(buttoncolor);
		records.setBounds(550, 100, 250, 30);
		records.addActionListener(this);
		
		String rows[][] = new String[0][0];
		String cols[] = {"Passanger ID","Passanger Name","Flight Code","Gender","Passport No.","Amount","Nationality"};
		model = new DefaultTableModel(rows, cols);
		table = new JTable(model);
		scrollpane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_NEVER,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollpane.setBounds(1, 150, 830, 410);

		this.add(scrollpane);
		this.add(records);
		this.add(book);
		this.add(reset);
		this.add(nationalityf);
		this.add(nationalityl);
		this.add(amtf);
		this.add(amtl);
		this.add(psnumberf);
		this.add(psnumberl);
		this.add(genderm);
		this.add(genderf);
		this.add(gender);
		this.add(flightcode);
		this.add(flcodel);
		this.add(psnamel);
		this.add(psnamef);
		this.add(psidf);
		this.add(psidl);

	}

	public String psId() {
		return "PS-" + new Random().nextInt(100, 900);
	}

	public void actionPerformed(ActionEvent ae) {

		String command = ae.getActionCommand();
		String gender = genderm.isSelected() ? "Male" : "Female";
		String ele[] = { psidf.getText(), psnamef.getText(), (String) flightcode.getSelectedItem(), gender,
				psnumberf.getText(), amtf.getText(), (String) nationalityf.getSelectedItem() };
		if (command.equals("Book")) {
			if (checkValid(ele)) {
				Database.insertTicketDetail(ele);
				reset(); 

			}
		}
		else if(command.equals("Reset")) {
			reset(); 
		}
		else if(command.equals("Records")) {
			model.setRowCount(0);
			Database.DataForTickets(model);
			
		}

	}

	public void reset() {
		JTextField ele[]= {psidf, psnamef, psnumberf, amtf};
		for(JTextField i: ele) {
			i.setText("");
		}
		psidf.setText(psId());
		
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

}
