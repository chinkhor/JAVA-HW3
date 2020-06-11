import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main {

	public static void main(String[] args) {
		
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		JFrame frame = new JFrame();
		
		frame.setTitle("Calculator");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(480,480);
		frame.add(panel, BorderLayout.CENTER);
		frame.setVisible(true);
		

	}

}
