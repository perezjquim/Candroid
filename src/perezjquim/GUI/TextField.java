package perezjquim.GUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TextField extends JFormattedTextField
{
	private static final int WIDTH = 30;
	public TextField()
	{
		super();
		this.setColumns(WIDTH);
	}
}
