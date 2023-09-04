package code;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.MatteBorder;

public class Window extends JFrame {

	Dashboard leftpanel;
	Subframe rightpanel;
	Color color;
	public static Window windowObject;

	public Window() {
		windowObject = this;
		this.setResizable(false);
		this.setSize(900, 600);
		this.setLayout(new BorderLayout());
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(3);

		color = new Color(205, 196, 230);
		leftpanel = new Dashboard();
		leftpanel.setPreferredSize(new Dimension(200, this.getHeight()));
		leftpanel.setBackground(color);
		MatteBorder border = new MatteBorder(1, 1, 1, 0, Color.BLACK);
		leftpanel.setBorder(border);

		rightpanel = new Subframe();
		rightpanel.setBackground(color);
		rightpanel.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		SubframeRecords.getList().add(new Home());
		Subframe.tt.start();

		this.add(leftpanel, BorderLayout.WEST);
		this.add(rightpanel);
		this.setVisible(true);
	}

	public static void additionalSize() {
		windowObject.setSize(1050, 600);
		windowObject.setLocationRelativeTo(null);
		windowObject.revalidate();

	}

	public static void originalSize() {
		windowObject.setSize(900, 600);
	}

}
