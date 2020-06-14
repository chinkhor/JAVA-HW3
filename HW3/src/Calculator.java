import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;    
import java.lang.reflect.Method;  
import java.lang.reflect.InvocationTargetException;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Calculator implements ActionListener {
	
	private JTextField txtf;
	private double operand1 = 0, operand2 = 0;
	private String operator = "";
	private boolean operationPerformed = false;
	private Method func;
	private static Calculator cal;
	
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
	
	public double add(double op1, double op2)
	{
		return op1+op2;
	}
	
	public double substract(double op1, double op2)
	{
		return op1-op2;
	}
	
	public double multiply(double op1, double op2)
	{
		return op1*op2;
	}
	
	public double divide(double op1, double op2)
	{
		return op1/op2;
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
		
		// save operator method in method variable "func"
		try
		{
			if (operator.equals("+"))
			{
				func = Calculator.class.getDeclaredMethod("add", double.class, double.class);
			}
			else if (operator.equals("-"))
			{
				func = Calculator.class.getDeclaredMethod("substract", double.class, double.class);
			}
			else if (operator.equals("*"))
			{
				func = Calculator.class.getDeclaredMethod("multiply", double.class, double.class);
			}
			else if (operator.equals("/"))
			{
				func = Calculator.class.getDeclaredMethod("divide", double.class, double.class);
			}
		} catch (NoSuchMethodException | SecurityException e)
		{
			e.printStackTrace();
		}
		
	}
	
	
	public void performOperation()
	{
		String str = txtf.getText();
		double output = 0;
		
		// no entry yet or double click on Enter after operation is already performed
		if (str.equals("") || operationPerformed)
			return;
		
		int operatorIndex = str.indexOf(operator);
		String operand2str = str.substring(operatorIndex+1, str.length());
		operand2 = Double.valueOf(operand2str);
		
		if (operator.matches("[^+*/-]"))
		{
			txtf.setText(str + " = ERR ");
		}
		else
		{
			// invoke method to calculate based on operator +,-,*,/
			try
			{
				output = (double) func.invoke(cal, operand1, operand2);
			} catch (SecurityException | IllegalAccessException | InvocationTargetException e)
			{
				e.printStackTrace();
			}
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
	
	public static void main(String[] args)  {
		
		cal = new Calculator();
		
	}
	
	
}
