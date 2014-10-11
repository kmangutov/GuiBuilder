package builder;

import java.awt.Component;
import java.awt.Scrollbar;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollBar;

public class StringBuilder 
{
	public static String instantiate(String name, Component type)
	{
		if(type instanceof JScrollBar)
			return name + " = new " + type.getClass().toString().replaceAll("class ", "") + "(java.awt.Scrollbar.HORIZONTAL, 0, 60, 0, 60);\n";
		else
			return name + " = new " + type.getClass().toString().replaceAll("class ", "") + "();\n";
	}
	public static String position(String name, Component comp)
	{
		return name + ".setBounds(" + comp.getX() + "," + comp.getY() + ","+comp.getWidth()+"," + comp.getHeight() + ");\n";
	}
	public static String text(String name, String text)
	{
		return name + ".setText(\"" + text + "\");\n";
	}
	public static String getText(Component c)
	{
		if(c instanceof JButton)
			return ((JButton)c).getText();
		if(c instanceof JLabel)
			return ((JLabel)c).getText();
		return null;
	}
}
