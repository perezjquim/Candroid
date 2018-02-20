package perezjquim.GUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Panel extends JPanel
{
	public Panel()
	{
		super();
	}
	public Panel(String label)
	{
		super();
		JPanel panel = new JPanel();
		panel.add(new JLabel("<html><i>"+label+"</i></html>"));
		this.add(panel);
	}
	public void addButtonGroup (AbstractButton ... buttons)
	{
		ButtonGroup group = new ButtonGroup();
		for (AbstractButton b : buttons) 
		{
			group.add(b);
			this.add(b);
		}
	}
}
