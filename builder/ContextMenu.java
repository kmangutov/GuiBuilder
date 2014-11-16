package builder;

import builder.JSimpleMenu.JSimpleMenu;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/**
 * Menu to manage UI elements
 * @author Kirill
 *
 */
public class ContextMenu extends JSimpleMenu
{
	private static String menu = "Text;Delete";
	
	public ContextMenu(ComponentManager manager)
	{
		super(manager, menu);
	}
}