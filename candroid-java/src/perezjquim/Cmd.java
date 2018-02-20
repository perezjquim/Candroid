package perezjquim;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class Cmd
{
	public static void exec(String cmd) throws IOException
	{
		try
		{
			Process p = Runtime.getRuntime().exec(cmd);
			p.waitFor();
		}
		catch (InterruptedException e)
		{ e.printStackTrace(); }
	}

	public static void exec(String[] cmd) throws IOException
	{
		try
		{
			Process p = Runtime.getRuntime().exec(cmd);
			p.waitFor();
		}
		catch (InterruptedException e)
		{ e.printStackTrace(); }
	}

	public static void exec(String cmd,File directory) throws IOException
	{
		try
		{
			Process p = Runtime.getRuntime().exec(cmd,null,directory);
			p.waitFor();
		}
		catch (InterruptedException e)
		{ e.printStackTrace(); }
	}

	public static void exec(String[] cmd,File directory) throws IOException
	{
		try
		{
			Process p = Runtime.getRuntime().exec(cmd,null,directory);
			p.waitFor();
		}
		catch (InterruptedException e)
		{ e.printStackTrace(); }
	}


	public static void exec(String cmd, JTextArea output) throws IOException
	{
		try
		{
			Process p = Runtime.getRuntime().exec(cmd);

			readOutput(p,output);
			readError(p,output);

			p.waitFor();
		}
		catch (InterruptedException e)
		{ e.printStackTrace(); }
	}

	public static void exec(String[] cmd, JTextArea output) throws IOException
	{
		try
		{
			Process p = Runtime.getRuntime().exec(cmd);

			readOutput(p,output);
			readError(p,output);

			p.waitFor();
		}
		catch (InterruptedException e)
		{ e.printStackTrace(); }
	}

	public static void exec(String cmd, JTextArea output, File directory) throws IOException
	{
		try
		{
			Process p = Runtime.getRuntime().exec(cmd,null,directory);
			
			readOutput(p,output);
			readError(p,output);

			p.waitFor();
		}
		catch (InterruptedException e)
		{ e.printStackTrace(); }
	}

	public static void exec(String[] cmd, JTextArea output, File directory) throws IOException
	{
		try
		{
			Process p = Runtime.getRuntime().exec(cmd,null,directory);

			readOutput(p,output);
			readError(p,output);

			p.waitFor();
		}
		catch (InterruptedException e)
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
			{ output.append(current); }
		}
		catch (IOException e)
		{ e.printStackTrace(); }
	}

	public static void readOutput(Process p, JTextArea output)
	{ readStream(p.getInputStream(),output); }

	public static void readError(Process p, JTextArea output)
	{ readStream(p.getErrorStream(),output); }
}
