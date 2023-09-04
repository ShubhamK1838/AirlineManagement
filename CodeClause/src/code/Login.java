package code;

import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;

public class Login extends JPanel implements ActionListener {

	JTextField first;
	JPasswordField second;
	JLabel namelb, passlb;
	JButton submit;
	JButton signup;
	JLabel usericon, exiticon;
	Login This;
	JFrame window;

	Login() {
		This = this;
		window = new JFrame();
		window.setSize(400, 400);
		window.setLocationRelativeTo(null);

		String path = "images";
		ImageIcon img = new ImageIcon(path + "\\user.png");
		ImageIcon img2 = new ImageIcon(path + "\\exit.png");

		window.setIconImage(img.getImage());

		this.setLayout(null);

		exiticon = new JLabel(img2);
		exiticon.setBounds(400 - 40, 20, 30, 30);
		exiticon.addMouseListener(new MouseAdapter() {// exit code
			public void mouseClicked(MouseEvent e) {

				System.exit(0);
			}
		});
		this.add(exiticon);
		usericon = new JLabel(img);
		usericon.setBounds(100, 0, 200, 200);
		this.add(usericon);

		first = new JTextField();
		first.setBorder(new EmptyBorder(0, 0, 0, 0));
		second = new JPasswordField();
		second.setBorder(first.getBorder());

		namelb = new JLabel("Name:");
		passlb = new JLabel("Pass:");

		passlb.setBounds(50, 250, 80, 30);

		namelb.setBounds(50, 200, 80, 30);
		namelb.setFont(new Font("Arial", 0, 17));
		passlb.setFont(new Font("Arial", 0, 17));
		namelb.setForeground(Color.black);
		passlb.setForeground(Color.black);

		first.setBounds(110, 200, 200, 30);
		first.setFont(new Font("Arial", Font.PLAIN, 18));
		second.setFont(new Font("Arial", Font.PLAIN, 18));
		second.setBounds(110, 250, 200, 30);

		submit = new JButton("Submit");
		submit.setBounds(90, 300, 80, 30);
		submit.setBackground(new Color(140, 40, 204));
		submit.setForeground(new Color(0, 0, 0));
		submit.addActionListener(this);

		signup = new JButton("SingUp");
		signup.setBounds(200, 300, 80, 30);
		signup.setBackground(submit.getBackground());
		signup.setForeground(submit.getForeground());
		signup.setSize(submit.getSize());
		signup.addActionListener(this);

		this.add(signup);
		this.add(submit);
		this.add(namelb);
		this.add(first);
		this.add(passlb);
		this.add(second);
		this.setBackground(new Color(173, 216, 230));

		this.revalidate();
		this.setSize(300, 300);
		window.add(this);
		window.setUndecorated(true);
		window.setVisible(true);

	}

	public static void main(String ar[]) {

		new Login();

	}

	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if (command.equals("Submit")) {
			try {
				Database db;
				boolean say = Database.authenticate(first.getText(), second.getText());
				
				if (say) {
					window.setVisible(false);
					new Window().setVisible(true);
					window.setVisible(false);

				}

				else
					JOptionPane.showMessageDialog(this, "User Are Not Found...");
			} catch (Exception de) {
			}
		} else {

			JOptionPane.showMessageDialog(null, "Please Contact With Admin...");
		}

	}

}
