package builder;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

public class MockFrame implements MouseListener {
    public JFrame frame;
    private Mover mover;

    private SnapPanel panel;

    public Menu popup;

    private Point lastClick = new Point(-1, -1);

    /*
     * Initializes mock frame UI with a panel that will contain all our UI elements.
     */
    public MockFrame(ComponentManager manager) {
        popup = new Menu(manager);


        frame = new JFrame();
        frame.setSize(500, 500);

        panel = new SnapPanel();
        panel.addMouseListener(this);
        panel.setSize(10, 200);
        mover = new Mover(panel);

        panel.setLayout(null);
        frame.add(panel);

        frame.show();
    }

    /**
     * Add a component and register it with mvoer listener.
     *
     * @param c Component
     */
    public void addComponent(Component c) {
        c.addMouseListener(this);

        c.setBounds(lastClick.x, lastClick.y, 100, 32);


        panel.add(c);
        mover.register(c);

        panel.show();
        frame.show();
    }

    /**
     * Handle right click on a custom ui element
     *
     * @param e
     */
    public void handleModify(MouseEvent e) {
        Component c = e.getComponent();

        if (c instanceof JLabel) {
            ((JLabel) c).setText(JOptionPane.showInputDialog("Input text"));
        } else if (c instanceof JButton) {
            ((JButton) c).setText(JOptionPane.showInputDialog("Input text"));
        }
    }

    public static void main(String[] args) {
        MockFrame mf = new MockFrame(null);
        mf.addComponent(new JButton("test"));
        mf.addComponent(new JButton("test2"));
    }

    /**
     * Mouse clicked
     */
    public void mouseClicked(MouseEvent e) {
        System.out.println(e.getComponent());

        lastClick = e.getPoint();

        if (e.getButton() == e.BUTTON3 && e.getComponent() instanceof JPanel)
            popup.show(e.getComponent(), e.getX(), e.getY());
        else if (e.getButton() == e.BUTTON3) {
            handleModify(e);
        }
    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {

    }

    public void mouseReleased(MouseEvent e) {

    }
}

