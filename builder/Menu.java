package builder;

import builder.JSimpleMenu.JSimpleMenu;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/**
 * Menu to manager UI elements
 * @author Kirill
 *
 */
public class Menu extends JSimpleMenu
{
	private static String menu = "Add...->Add Button,Add Textfield,Add Label,Add Combobox,Add Scrollbar;Print Code;Run Mock";
	
	public Menu(ComponentManager manager)
	{
        super(manager, menu);
	}
}