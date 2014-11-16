package builder;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

/*
 * Draws the grid
 */

public class SnapPanel extends JPanel
{
	private int snapX = 20;
	private int snapY = 20;
	
	public boolean dragging = true;
	
	public SnapPanel()
	{
	
	}

    public int getSnapDelta()
    {
        return snapX;
    }

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		if(dragging)
			paintSnap(g);
	}

	public void paintSnap(Graphics g)
	{
		g.setColor(Color.GRAY);
		
		for(int y = 0; y < this.getHeight(); y += snapY)
			g.drawLine(0, y, getWidth(), y);
		for(int x = 0; x < getWidth(); x += snapX)
			g.drawLine(x, 0, x, getHeight());
				
		g.setColor(Color.BLACK);
	}
}
