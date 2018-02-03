package perezjquim.GUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Button extends JButton
{
	public Button(String label,Runnable action)
	{
		super(label);
		addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e)
		{
			action.run();	
		}});
	}
}
