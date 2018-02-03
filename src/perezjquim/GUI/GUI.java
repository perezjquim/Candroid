package perezjquim.GUI;
import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame
{
	private Container con;
	
	public GUI(String name)
	{
		super(name);
		init();
	}
	public void add(Panel p)
	{
		con.add(p);
	}
	public void init()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		con = this.getContentPane();
		con.setLayout(new BoxLayout(con,BoxLayout.Y_AXIS));
	}	
	public void start()
	{
		pack();
		setVisible(true);
	}
}
