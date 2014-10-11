package builder;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/**
 * Menu to manager UI elements
 * @author Kirill
 *
 */
public class Menu extends JPopupMenu
{
	ComponentManager manager;
	
	String menu = "Add...->Add Button,Add Textfield,Add Label,Add Combobox,Add Scrollbar;Print Code;Run Mock";
	
	/**
	 * Generate menu from String
	 */
	public void genMenu()
	{
		String[] firstLvl = menu.split(";");
		for(String s1 : firstLvl)
		{
			if(!s1.contains("->"))
				add(new JMenuItem(s1));
			else
			{
				JMenu parent = new JMenu(s1.split("->")[0]);
				this.add(parent);
				
				for(String s2 : s1.split("->")[1].split(","))
				{
					JMenuItem inst = new JMenuItem(s2);
					parent.add(inst);
					inst.addActionListener(manager);
				}
			}
		}
	}
	
	/**
	 * Add menuitem and add listeners
	 */
	public JMenuItem add(JMenuItem item)
	{
		super.add(item);
		item.addActionListener(manager);
		
		return item;
	}
	
	public Menu(ComponentManager manager)
	{
		this.manager = manager;
		genMenu();
	}

}