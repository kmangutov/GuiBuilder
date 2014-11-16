package builder.JSimpleMenu;

import builder.ComponentManager;

import javax.swing.*;

/**
 * Create a JPopupMenu easily with a string describing the structure of the menu
 * Created by kmangutov on 11/16/14.
 */
public class JSimpleMenu extends JPopupMenu {

    private ComponentManager manager;

    public JSimpleMenu(ComponentManager manager, String menu) {
        this.manager = manager;
        genMenu(menu);
    }

    private void genMenu(String menu) {
        String[] firstLvl = menu.split(";");
        for (String s1 : firstLvl) {
            if (!s1.contains("->"))
                add(new JMenuItem(s1));
            else {
                JMenu parent = new JMenu(s1.split("->")[0]);
                this.add(parent);

                for (String s2 : s1.split("->")[1].split(",")) {
                    JMenuItem inst = new JMenuItem(s2);
                    parent.add(inst);
                    inst.addActionListener(manager);
                }
            }
        }
    }

    public JMenuItem add(JMenuItem item) {
        super.add(item);
        item.addActionListener(manager);

        return item;
    }
}
