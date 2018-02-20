package perezjquim;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class IO
{
	public static void writeFile(String destination,String data)
	{
		try
		{
			PrintWriter out = new PrintWriter(destination,"UTF-8");
			out.println(data);
			out.close();
		}
		catch (FileNotFoundException | UnsupportedEncodingException e)
		{ e.printStackTrace(); }
	}
	public static File askFolder()
	{
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
		int state = chooser.showOpenDialog(new JFrame());
		if(state == JFileChooser.APPROVE_OPTION) 
		{
			return chooser.getSelectedFile();
		}
		else return null;
	}
	public static File askFile()
	{
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		
		int state = chooser.showOpenDialog(new JFrame());
		if(state == JFileChooser.APPROVE_OPTION) 
		{
			return chooser.getSelectedFile();
		}
		else return null;
	}
	public static void popup(String message)
	{
		JOptionPane.showMessageDialog(new JFrame(), message);
	}
}
