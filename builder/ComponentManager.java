package builder;
import java.awt.Scrollbar;
import java.awt.TextField;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.JTextField;


public class ComponentManager implements ActionListener
{
	//display ui?
	boolean visual;
	
	String defaultText = "text";
	
	public enum Type{Label, Button, Textfield, Combobox, Scrollbar};
	
	String fileUrl = "src/Frame.java";
	
	//keep count of component types for naming (ie Button1, Label1, Label2...)
	HashMap<Type, Integer> componentSorter;
	
	ArrayList<ComponentContainer> components;
	MockFrame mockFrame;
	
	TreeDisplay tree;
	
	/**
	 * Initialize mockup ui and component array
	 */
	public ComponentManager(boolean visual)
	{
		this.visual = visual;
		
		if(visual)
		{
			mockFrame = new MockFrame(this);
			tree = new TreeDisplay();
		}
		
		components = new ArrayList<ComponentContainer>();
		componentSorter = new HashMap<Type, Integer>();
	}
	
	
	public String printFrameInit()
	{
		String init = "javax.swing.JFrame frame = new javax.swing.JFrame();\njavax.swing.JPanel panel = new javax.swing.JPanel();\nframe.add(panel);\npanel.setLayout(null);\n";
		return init;
	}
	
	
	public static void main(String[] args)
	{
		ComponentManager cm = new ComponentManager(true);	
	}



	/**
	 * Print state of component array
	 */
	public void printComponents()
	{
		for(ComponentContainer cc : components)
		{
			System.out.println(cc.id + ":" + cc.component.getLocation());
		}
	}
	
	/**
	 * Add component container to collection and repaint mock ui.
	 * @param c
	 */
	public void addComponent(ComponentContainer c)
	{
		if(visual)
		{
			mockFrame.addComponent(c.component);
			mockFrame.frame.show();
		}
		
		components.add(c);
		
		if(visual)
			tree.update(components);
	}
	
	/**
	 * Given a Type type, produce a ComponentContainer that wraps an instance of type.
	 * @param type
	 * @return ComponentContainer
	 */
	public ComponentContainer createComponent(Type type)
	{
		//component to wrap
		Component instance = null;
		//counter to label it
		int id = 1;
		
		if(componentSorter.containsKey(type))
		{
			id = componentSorter.get(type) + 1;
			componentSorter.put(type, id);
		}
		else
		{
			componentSorter.put(type, 1);
		}
		
		if(type == Type.Button)
		{
			instance = new JButton(defaultText);
		}
		else
		if(type == Type.Label)
		{
			instance = new JLabel(defaultText);
		}
		else
		if(type == Type.Textfield)
		{
			instance = new JTextField(defaultText);
			instance.setEnabled(false);
		}
		else
		if(type == Type.Combobox)
		{
			instance = new JComboBox();
			instance.setEnabled(false);
		}
		else
		if(type == Type.Scrollbar)
		{
			instance = new JScrollBar(Scrollbar.HORIZONTAL, 0, 60, 0, 60);
			instance.setEnabled(false);
		}
		
		ComponentContainer container = new ComponentContainer(instance, type.toString() + id);
		return container;
	}
	

	/**
	 * Returns a publicly accessable list of components
	 * @return
	 */
	public ArrayList<ComponentContainer> components()
	{
		return components;
	}
	
	/**
	 * Process an "Add" command
	 * @param txt Name of menu item
	 */
	public void processAddCommand(String txt)
	{
		ComponentContainer container = null;
		if(txt.equals("Add Button"))
		{
			container = createComponent(Type.Button);
		}
		else
		if(txt.equals("Add Textfield"))
		{
			container = createComponent(Type.Textfield);
		}
		else
		if(txt.equals("Add Label"))
		{
			container = createComponent(Type.Label);
		}
		else
		if(txt.equals("Add Combobox"))
		{
			container = createComponent(Type.Combobox);
		}
		else
		if(txt.equals("Add Scrollbar"))
		{
			container = createComponent(Type.Scrollbar);
		}
		addComponent(container);
	}
	
	String psvm = "public class Frame implements java.awt.event.ActionListener{\n\tpublic static void main(String[] args){new Frame();}\n\tpublic Frame(){";
	String action = "public void actionPerformed(java.awt.event.ActionEvent e){javax.swing.JOptionPane.showMessageDialog(null, e.toString());}";

	public void printCode() throws FileNotFoundException, UnsupportedEncodingException
	{
		PrintWriter writer = new PrintWriter(fileUrl, "UTF-8");
		
		for(String s : psvm.split("\n"))
			writer.println(s);
			
		String tab = "\t\t";
		
		for(String s : printFrameInit().split("\n"))
			writer.println(tab + s);
		
		writer.println(tab + "frame.setSize(" + mockFrame.frame.getWidth() + ", " + mockFrame.frame.getHeight() + ");");
		writer.println(tab + "frame.setResizable(false);");

		
		
		writer.println();
		
		for(ComponentContainer cc : components)
		{
			for(String s : cc.getSetup().split("\n"))//split into \n to print each with tabs
				writer.println(tab + s);
			if(cc.component instanceof JButton)
				writer.println(tab + cc.name + ".addActionListener(this);");
			writer.println();
		}
		
		writer.println("\n");
		for(ComponentContainer cc : components)
		{
			writer.println(tab + "panel.add(" + cc.name + ");");
		}
		writer.println(tab + "frame.show();");
		writer.println("\t}");
		writer.println("\t" + action);
		writer.println();
		

		for(ComponentContainer cc : components)
		{
			writer.println(tab + cc.getDeclaration());
		}
		
		writer.println("}");
		writer.close();
	}

	
	String dir = "C:\\Users\\Kirill\\Documents\\class\\cs242\\Assignment4.2\\src\\";
	String[] cmds = new String[]{"run.bat"};//"javac Frame.java", "java Frame"};//
	
	public void runMock()
	{
		try{
			printCode();
			Thread.sleep(500);
			
			for(String s : cmds)
			{
				System.out.println(dir + s);
				Runtime.getRuntime().exec(dir + s, null, new File(dir));

			}
			
		}catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());			
		}
	}
	
	/**
	 * Menu Item pressed
	 * (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent arg0) 
	{
		String txt = ((JMenuItem)arg0.getSource()).getText();
	
		//add something called
		if(txt.startsWith("Add "))
		{
			processAddCommand(txt);
			return;
		}
		else
		if(txt.startsWith("Print "))
		{
			try{
				printCode();
			}catch(Exception e)
			{
				JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
			}
		}
		else
		if(txt.startsWith("Run Mock"))
		{
			runMock();
		}
	}
}
