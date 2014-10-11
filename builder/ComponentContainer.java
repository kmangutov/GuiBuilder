package builder;
import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import static builder.StringBuilder.*;

public class ComponentContainer 
{
	public Component component;
	String name = "";
	int id = -1;
	

	/**
	 * Initialize ComponentContainer to wrap element
	 * @param c Component to manage
	 */
	public ComponentContainer(Component c)
	{
		this.component = c;
	}
	public ComponentContainer(Component c, String name)
	{
		this(c);
		this.name = name;
	}
	
	/**
	 * Return declaration statement ie JButton button;
	 * @return
	 */
	public String getDeclaration()
	{
		String s = component.getClass().toString().replaceAll("class ", "") + " " + name + ";\n";
		return s;
	}
	
	/**
	 * Get instantiation code, eg "button = new JButton..."
	 * @return 
	 */
	public String getSetup()
	{
		String ret = "";
		
		ret += instantiate(name, component) + position(name, component);
		
		if(getText(component) != null)
			ret += text(name, getText(component));
		
		return ret;
	}
	
	/**
	 * String representation of component being wrapped.
	 */
	public String toString()
	{
		return getDeclaration() + getSetup();

	}
}
