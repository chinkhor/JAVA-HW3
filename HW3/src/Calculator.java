import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

public class Calculator implements ActionListener {
	
	private JTextField txtf;
	private double operand1 = 0, operand2 = 0;
	private String operator = "";
	private boolean operationPerformed = false;
	
	
	public Calculator ()
	{
		//create panel
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
				
		// create TextField
		txtf = new JTextField();
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
			button.addActionListener(this);
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
	
	
	public void clrOperation()
	{
		operand1 = 0;
		operand2 = 0;
		operator = "";
		operationPerformed = false;
		txtf.setText("");
	}
	
	public void setOperator(String op)
	{
		String str = txtf.getText();
		if (str.equals("") || operationPerformed)
			return;
		else
		{
			if (operator.equals(""))
			{
				operator = op;
				operand1 = Double.valueOf(str);
				txtf.setText(str + " " + operator + " ");
			}
			else
			{
				String newstr = str.replace(operator, op);
				operator = op;
				txtf.setText(newstr);
			}
		}
	}
	
	public void performOperation()
	{
		String str = txtf.getText();
		double output = 0;
		boolean error = false;
		
		// no entry yet or double click on Enter after operation is already performed
		if (str.equals("") || operationPerformed)
			return;
		
		int operatorIndex = str.indexOf(operator);
		String operand2str = str.substring(operatorIndex+1, str.length());
		operand2 = Double.valueOf(operand2str);
		
		if (operator.equals("+"))
		{
			output = operand1 + operand2;
		}
		else if (operator.equals("-"))
		{
			output = operand1 - operand2;
		}
		else if (operator.equals("*"))
		{
			output = operand1 * operand2;
		}
		else if (operator.equals("/"))
		{
			output = operand1 / operand2;
		}
		else
		{
			error = true;
			txtf.setText(str + " = ERR ");
		}
		
		if (!error)
		{
			txtf.setText(str + " = " + output);
		}
		
		operationPerformed = true;
	}
	
	public void actionPerformed (ActionEvent e)
	{
		CButton button = (CButton) e.getSource();
		String label = button.getLabel();
		
		if (label.equals("CLR"))
		{
			clrOperation();
		}
		else if (label.matches("[0-9.]"))
		{
			if (operationPerformed)
			{
				clrOperation();
			}
			String str = txtf.getText();
			txtf.setText(str + label);
		}
		else if (label.matches("[+*/-]"))
		{
			setOperator(label);
		}
		else if (label.equals("ENTER"))
		{
			performOperation();
		}
	}
	
	public static void main(String[] args) {
		
		new Calculator();
		
	}
	
	
}
