import java.awt.Dimension;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class CButton extends JButton {
	private final int WIDTH = 150, HEIGHT = 50;
	private String label;
	
	public CButton(String label)
	{
		super(label);
		this.label = label;
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
	}
	
	public CButton(String label, int width, int height)
	{
		super(label);
		this.label = label;
		setPreferredSize(new Dimension(width, height));
	}
	
	public String getLabel()
	{
		return label;
	}
}
