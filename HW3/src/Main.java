import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

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
		
		// create frame
		JFrame frame = new JFrame();
		frame.setTitle("Calculator");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(480,480);
		frame.add(panel, BorderLayout.CENTER);
		frame.setVisible(true);
		

	}

}
