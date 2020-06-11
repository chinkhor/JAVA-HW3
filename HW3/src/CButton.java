import java.awt.Dimension;

import javax.swing.JButton;

public class CButton extends JButton {
	private final int WIDTH = 150, HEIGHT = 50;
	
	public CButton(String label)
	{
		super(label);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
	}
	
	public CButton(String label, int width, int height)
	{
		super(label);
		setPreferredSize(new Dimension(width, height));
	}

}
