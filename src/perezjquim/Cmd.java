package perezjquim;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class Cmd
{
	public static void exec(String cmd, JTextArea output)
	{
		try
		{
			Process p = Runtime.getRuntime().exec(cmd);
			p.waitFor();
			
			readOutput(p,output);
			readError(p,output);
		}
		catch (InterruptedException | IOException e)
		{ e.printStackTrace(); }
	}
	
	public static void exec(String cmd)
	{
		try
		{
			Process p = Runtime.getRuntime().exec(cmd);
			p.waitFor();
		}
		catch (InterruptedException | IOException e)
		{ e.printStackTrace(); }
	}	
	
	public static void exec(String cmd,File directory)
	{
		try
		{
			Process p = Runtime.getRuntime().exec(cmd,null,directory);
			p.waitFor();
		}
		catch (InterruptedException | IOException e)
		{ e.printStackTrace(); }
	}	
	
	public static void readStream(InputStream stream, JTextArea output)
	{
		BufferedReader reader;
		String current;
		try
		{
			reader = new BufferedReader(new InputStreamReader(stream));
			while((current = reader.readLine()) != null) 
			{ output.setText(output.getText()+"\n"+current); }
		}
		catch (IOException e) 
		{ e.printStackTrace(); }
	}
	
	public static void readOutput(Process p, JTextArea output)
	{ readStream(p.getInputStream(),output); }
	
	public static void readError(Process p, JTextArea output)
	{ readStream(p.getErrorStream(),output); }
}
