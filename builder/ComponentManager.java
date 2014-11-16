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


public class ComponentManager implements ActionListener {
    //display ui?
    private boolean visual;
    private String defaultText = "text";

    public enum Type {Label, Button, Textfield, Combobox, Scrollbar};

    private String fileUrl = "src/Frame.java";

    //keep count of component types for naming (ie Button1, Label1, Label2...)
    private HashMap<Type, Integer> componentSorter;

    private ArrayList<ComponentContainer> components;
    private MockFrame mockFrame;

    private TreeDisplay tree;

    public static void main(String[] args) {
        ComponentManager cm = new ComponentManager(true);
    }

    /**
     * Initialize mockup ui and component array
     */
    public ComponentManager(boolean visual) {
        this.visual = visual;

        if (visual) {
            mockFrame = new MockFrame(this);
            tree = new TreeDisplay();
        }

        components = new ArrayList<ComponentContainer>();
        componentSorter = new HashMap<Type, Integer>();
    }

    private String printFrameInit() {
        String init = "javax.swing.JFrame frame = new javax.swing.JFrame();\njavax.swing.JPanel panel = new javax.swing.JPanel();\nframe.add(panel);\npanel.setLayout(null);\n";
        return init;
    }

    /**
     * Print state of component array - for debugging
     */
    private void printComponents() {
        for (ComponentContainer cc : components) {
            System.out.println(cc.getId() + ":" + cc.component.getLocation());
        }
    }

    /**
     * Add component container to collection and repaint mock ui.
     *
     * @param c
     */
    public void addComponent(ComponentContainer c) {
        if (visual) {
            mockFrame.addComponent(c.component);
            mockFrame.frame.show();
        }

        components.add(c);

        if (visual)
            tree.update(components);
    }

    /**
     * Given a Type type, produce a ComponentContainer that wraps an instance of type.
     *
     * @param type
     * @return ComponentContainer
     */
    private ComponentContainer createComponent(Type type) {
        //component to wrap
        Component instance = null;

        //Label first textbox textbox1, next textbox textbox2, etc for buttons
        int id = componentSorter.containsKey(type) ? (componentSorter.get(type) + 1) : 1;
        componentSorter.put(type, id);

        switch (type) {
            case Button:
                instance = new JButton(defaultText);
                break;
            case Label:
                instance = new JLabel(defaultText);
                break;
            case Textfield:
                instance = new JTextField(defaultText);
                instance.setEnabled(false);
                break;
            case Combobox:
                instance = new JComboBox();
                instance.setEnabled(false);
                break;
            case Scrollbar:
                instance = new JScrollBar(Scrollbar.HORIZONTAL, 0, 60, 0, 60);
                instance.setEnabled(false);
                break;
            default:
                System.out.println("Unknown component type");
        }

        ComponentContainer container = new ComponentContainer(instance, type.toString() + id);
        return container;
    }

    /**
     * Returns a publicly accessable list of components
     *
     * @return
     */
    public ArrayList<ComponentContainer> components() {
        return components;
    }

    /**
     * Process an "Add" command
     *
     * @param txt Name of menu item
     */
    private void processAddCommand(String txt) {
        ComponentContainer container = null;
        Type type = Type.Button;

        if (txt.equals("Add Button")) {
            type = Type.Button;
        } else if (txt.equals("Add Textfield")) {
            type = Type.Textfield;
        } else if (txt.equals("Add Label")) {
            type = Type.Label;
        } else if (txt.equals("Add Combobox")) {
            type = Type.Combobox;
        } else if (txt.equals("Add Scrollbar")) {
            type = Type.Scrollbar;
        }

        container = createComponent(type);
        addComponent(container);
    }

    private String psvm = "public class Frame implements java.awt.event.ActionListener{\n\tpublic static void main(String[] args){new Frame();}\n\tpublic Frame(){";
    private String action = "public void actionPerformed(java.awt.event.ActionEvent e){javax.swing.JOptionPane.showMessageDialog(null, e.toString());}";

    private void printCode() throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter(fileUrl, "UTF-8");

        for (String s : psvm.split("\n"))
            writer.println(s);

        String tab = "\t\t";

        for (String s : printFrameInit().split("\n"))
            writer.println(tab + s);

        writer.println(tab + "frame.setSize(" + mockFrame.frame.getWidth() + ", " + mockFrame.frame.getHeight() + ");");
        writer.println(tab + "frame.setResizable(false);");


        writer.println();

        for (ComponentContainer cc : components) {
            for (String s : cc.getSetup().split("\n"))//split into \n to print each with tabs
                writer.println(tab + s);
            if (cc.component instanceof JButton)
                writer.println(tab + cc.getName() + ".addActionListener(this);");
            writer.println();
        }

        writer.println("\n");
        for (ComponentContainer cc : components) {
            writer.println(tab + "panel.add(" + cc.getName() + ");");
        }
        writer.println(tab + "frame.show();");
        writer.println("\t}");
        writer.println("\t" + action);
        writer.println();


        for (ComponentContainer cc : components) {
            writer.println(tab + cc.getDeclaration());
        }

        writer.println("}");
        writer.close();
    }


    private String dir = "C:\\Users\\Kirill\\Documents\\class\\cs242\\Assignment4.2\\src\\";
    private String[] cmds = new String[]{"run.bat"};

    public void setDir(String dir) {
        this.dir = dir;
    }

    public void runMock() {
        try {
            printCode();
            Thread.sleep(500);

            for (String s : cmds) {
                System.out.println(dir + s);
                Runtime.getRuntime().exec(dir + s, null, new File(dir));

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    /**
     * Menu Item pressed
     * (non-Javadoc)
     *
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
        String txt = ((JMenuItem) arg0.getSource()).getText();

        //add something called
        if (txt.startsWith("Add ")) {
            processAddCommand(txt);
            return;
        } else if (txt.startsWith("Print ")) {
            try {
                printCode();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            }
        } else if (txt.startsWith("Run Mock")) {
            runMock();
        }
    }
}
