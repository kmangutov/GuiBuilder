/*package testing;

import static org.junit.Assert.*;

import javax.swing.JButton;
import javax.swing.JLabel;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import builder.ComponentContainer;
import builder.ComponentManager;

public class TestCode 
{
	ComponentManager manager;
	
	String addCombo = "Add Combobox";
	String addButton = "Add Button";
	String addLabel = "Add Label";
	
	int intValue = 56;
	
	String strValue = "Text Value";
	
	
	
	@Before
	public void setUp()
	{
		manager = new ComponentManager(false);
	}
	
	@Test
	public void testSimpleAdd()
	{
		assertTrue(manager.components().size() == 0);
		manager.processAddCommand(addButton);
		assertTrue(manager.components().size() == 1);
	}
	
	@Test
	public void testSimpleFunctionality()
	{
		manager.processAddCommand(addButton);
		
		ComponentContainer wrapper = manager.components().get(0);
		JButton inst = (JButton)wrapper.component;
		inst.setLocation(intValue, intValue);
		
		assertTrue(manager.components().get(0).component.getLocation().x == intValue);
	}
	
	@Test
	public void testTextMod()
	{
		manager.processAddCommand(addLabel);
		
		ComponentContainer wrapper = manager.components().get(0);
		JLabel inst = (JLabel)wrapper.component;
		inst.setText(strValue);
		
		assertTrue(((JLabel)manager.components().get(0).component).getText() == strValue);
	}
	
	@Test public void testNew()
	{
		manager.processAddCommand(addCombo);
		
		ComponentContainer wrapper = manager.components().get(0);
		
		assertTrue(wrapper != null);
	}
}
*/