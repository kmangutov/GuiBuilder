package builder;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class TreeDisplay implements MouseListener
{
	private JFrame frame;
	private JList list;
	
	private DefaultListModel model;
	
	private ArrayList<ComponentContainer> components;
	
	public TreeDisplay()
	{
		initUI();
	}

	public void forceUpdate()
	{
		update(components);
	}

	public void initUI()
	{
		frame = new JFrame("Components");
		frame.setSize(100, 300);
		frame.setLayout(null);
		
		model = new DefaultListModel();
		
		list = new JList(model);
		list.setSize(100, 300);
		frame.add(list);

	
		frame.setResizable(false);
		frame.setLocation(500, 0);
		
		frame.show();
		
		list.addMouseListener(this);
	}

    /*
     * When update is called, cache componentlist
     */
	public void update(ArrayList<ComponentContainer> components)
	{
        if(components != null)
		    this.components = components;
        else
            components = this.components;
		
		model = new DefaultListModel();
		
		for(ComponentContainer component : components)
		{
			model.addElement(component.getName());
		}
		
		list.setModel(model);
		frame.repaint();
	}

	public ComponentContainer byName(String name)
	{
		for(ComponentContainer cc : components)
		{
			if(cc.getName().equals(name))
				return cc;
		}
		return null;
	}

	public void doubleClick()
	{
		String item = (String)list.getSelectedValue();
		ComponentContainer container = byName(item);
		
		if(container != null)
		{
			
			PropertyFrame properties = new PropertyFrame(this);
			properties.associate(container);
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) 
	{
		if(arg0.getClickCount() == 2)
		{
			doubleClick();
		}
		
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
