import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Main {

	public static void main(String[] args) {
		
		//create panel
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		// create TextField
		JTextField txtf = new JTextField();
		txtf.setPreferredSize(new Dimension(400,50));
		panel.add(txtf);
		
		
		// create buttons
		ArrayList<CButton> buttonlist = new ArrayList<CButton>();
		
		buttonlist.add(new CButton("+", 100, 50));
		buttonlist.add(new CButton("-", 100, 50));
		buttonlist.add(new CButton("*", 100, 50));
		buttonlist.add(new CButton("/", 100, 50));
		for (int i=1; i<10; i++)
		{
			buttonlist.add(new CButton(Integer.toString(i)));
		}
		buttonlist.add(new CButton("0"));
		buttonlist.add(new CButton("."));
		buttonlist.add(new CButton("CLR"));
		buttonlist.add(new CButton("ENTER",200, 50));
		
		for (CButton button: buttonlist)
		{
			panel.add(button);
		}
		
		// create frame
		JFrame frame = new JFrame();
		frame.setTitle("Calculator");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(480,480);
		frame.add(panel, BorderLayout.CENTER);
		frame.setVisible(true);
		

	}

}
