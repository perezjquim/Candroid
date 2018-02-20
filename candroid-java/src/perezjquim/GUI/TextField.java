package perezjquim.GUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TextField extends JFormattedTextField
{
	public static final int LARGE = 40;
	public static final int MEDIUM = 20;
	public static final int SMALL = 5;
	public TextField(int width)
	{
		super();
		this.setColumns(width);
	}
	public TextField()
	{
		super();
		this.setColumns(TextField.MEDIUM);
	}
}
