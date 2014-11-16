package builder;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;


/*
 * Class to manage draggable UI elements
 */
public class Mover implements MouseListener, MouseMotionListener {

    private boolean resize = false;
    private boolean drag = false;
    private int initX = 0;
    private int initY = 0;

    private Component component = null;
    private SnapPanel panel;

    private float resizeThreshold = 0.75f;

    public Mover(SnapPanel panel) {
        this.panel = panel;
    }

    /*
     * Register a component to listen for movement
     */
    public void register(Component c) {
        c.addMouseListener(this);
        c.addMouseMotionListener(this);
    }

    /*
     * Mouse dragged; check if component is selected and move it
     * (non-Javadoc)
     * @see java.awt.event.MouseMotionListener#mouseDragged(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseDragged(MouseEvent arg0) {
        if (drag) {
            Point dest = arg0.getLocationOnScreen();
            dest = new Point(dest.x - initX, dest.y - initY);

            component.setLocation(dest);
            component.getParent().repaint();
        }

        if (resize) {
            Point dest = arg0.getLocationOnScreen();
            dest = new Point(dest.x - initX, dest.y - initY);

            component.setBounds(component.getX(), component.getY(), (int) arg0.getPoint().getX(), (int) arg0.getPoint().getY());
        }
    }

    @Override
    public void mouseMoved(MouseEvent arg0) {

    }

    @Override
    public void mouseClicked(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    /*
     * Check which component was pressed and see if we need to move it.
     * (non-Javadoc)
     * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
     */
    @Override
    public void mousePressed(MouseEvent arg0) {
        Point p = arg0.getLocationOnScreen();


        component = arg0.getComponent();

        initX = p.x - component.getX();
        initY = p.y - component.getY();

        //System.out.println(initX);
        if (arg0.getPoint().getX() > component.getWidth() * resizeThreshold && arg0.getPoint().getY() > component.getHeight() * resizeThreshold)
            resize = true;
        else
            drag = true;
    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
        drag = false;
        resize = false;
        if (component != null) {
            component.setLocation(component.getX() - component.getX() % panel.getSnapDelta(),
                    component.getY() - component.getY() % panel.getSnapDelta());
            component = null;
        }
    }
}
