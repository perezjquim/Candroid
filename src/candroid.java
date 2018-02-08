package candroid;
import perezjquim.GUI.*;
import perezjquim.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class candroid
{
	private static GUI main;
	private static File projectDirectory;
	private static TextArea currentDir;
	private static ScrollableTextArea console;
	private static JComboBox<String> devicesList;
	private static JComboBox<String> modulesList;
	private static String[] devIDs, devNames;
	private static final String[] notModules = { ".idea", ".git" , ".gradle" , "build" , "gradle" };
	private static final String adb = "/home/"+System.getProperty("user.name")+"/Android-sdk/platform-tools/adb ";
	public static void main(String[] args)
	{
		main = new GUI("Candroid");


		Panel panProject = new Panel("Project root directory");
		currentDir = new TextArea(TextArea.HEIGHT_SMALL,TextArea.WIDTH_MEDIUM);
		Button btnChooseDir = new Button("Choose directory",()->
			{
				projectDirectory = IO.askFolder();
				currentDir.setText(projectDirectory.getAbsolutePath());
				refreshModuleList();
			});
		panProject.add(currentDir);
		panProject.add(btnChooseDir);
		main.add(panProject);

		Panel panDevices = new Panel("Select a device");
		devicesList = new JComboBox<String>(new String[]{""});
		refreshDeviceList();
		Button btnDevices = new Button("Refresh devices list",()->
			{
				refreshDeviceList();
			});
		panDevices.add(devicesList);
		panDevices.add(btnDevices);
		main.add(panDevices);

		Panel panModules = new Panel("Module to be compiled");
		modulesList = new JComboBox<String>(new String[]{""});
		Button btnModules = new Button("Refresh modules list",()->
			{
				refreshModuleList();
			});
		panModules.add(modulesList);
		panModules.add(btnModules);
		main.add(panModules);

		Panel panOperations= new Panel("Operations");
		Button btnCompile = new Button("Compile and transfer",()->
			{
				console.getTextArea().clear();			
				compileProject();
				transferProject();
			});
		Button btnTransfer = new Button("Transfer only",()->
			{
				console.getTextArea().clear();					
				transferProject();
			});
		panOperations.add(btnCompile);
		panOperations.add(btnTransfer);
		main.add(panOperations);


		Panel panConsole = new Panel();
		console = new ScrollableTextArea(TextArea.HEIGHT_MEDIUM,TextArea.WIDTH_LARGE);
		panConsole.add(console);
		main.add(panConsole);

		main.start();
	}


	private static void refreshDeviceList()
	{
		TextArea hidden = new TextArea();

		String[] cmdGetIDs = { "/bin/sh","-c", adb + "devices -l | awk '{print $1}' | awk 'NF' | grep -v -x -F 'List' " };
		try
		{
			Cmd.exec(cmdGetIDs,hidden);
		}
		catch (IOException e)
		{ 
			e.printStackTrace();
			IO.popup("An error occured. Make sure:\n - Android SDK is located in ~/Android-sdk.");
		}		
		devIDs = hidden.getText().trim().split("\n");
		
		hidden.clear();

		String[] cmdGetNames = { "/bin/sh","-c", adb + "devices -l | awk '{print $5}' | cut -f2 -d ':' | awk 'NF' " };
		try
		{
			Cmd.exec(cmdGetNames,hidden);
		}
		catch (IOException e)
		{ 
			e.printStackTrace();
			IO.popup("An error occured. Make sure:\n - Android SDK is located in ~/Android-sdk.");
		}

		devNames = hidden.getText().trim().split("\n");

		devicesList.removeAllItems();

		if(devIDs.length == 0 || devIDs[0].contains("not found"))
		{
			IO.popup("An error occured. Make sure:\n - Your device is connected\n - Android SDK is located in ~/Android-sdk.");
			devicesList.addItem("");
		}
		else
		{
			for(String d : devNames)
				devicesList.addItem(d);
		}
	}

	private static void refreshModuleList()
	{
		modulesList.removeAllItems();

		File[] modulesDir = new File(currentDir.getText()).listFiles(new FileFilter() {
		    @Override
		    public boolean accept(File file) {
		        return file.isDirectory() 
		        && !Arrays.asList(notModules).contains(file.getName());
		    }
		});

		if(modulesDir.length > 0)
		{
			for(File directory : modulesDir)
				modulesList.addItem(directory.getName());
		}

		else
		{
			IO.popup("An error occured. Make sure you selected the correct folder.");
			modulesList.addItem("");
		}
	}

	private static void compileProject()
	{
		try
		{
			console.getTextArea().append("@@@@@BUILDING...@@@@@");			
			Cmd.exec("./gradlew --stop",projectDirectory);
			Cmd.exec("./gradlew :"+modulesList.getSelectedItem()+":assembleDebug", console.getTextArea(), projectDirectory);
			Cmd.exec("./gradlew --stop",projectDirectory);
			console.getTextArea().append("@@@@@BUILD COMPLETE!@@@@@");
		}
		catch(IOException e)
		{
			e.printStackTrace();
			IO.popup("An error occured. Make sure:\n - Your device is connected\n - You selected a valid Android Studio Project folder");
			console.getTextArea().append("@@@@@BUILD FAILED!@@@@@");
		}

	}

	private static void transferProject()
	{
		int selectedDevice = devicesList.getSelectedIndex();
		String cmdPush = adb +" -s "+devIDs[selectedDevice] +" push "+projectDirectory.getAbsolutePath() +"/"+modulesList.getSelectedItem()+"/build/outputs/apk/debug/"+modulesList.getSelectedItem()+"-debug.apk /data/local/tmp/candroid-uploaded-apk";
		String cmdInstall = adb +" -s "+devIDs[selectedDevice] +" shell pm install -t -r '/data/local/tmp/candroid-uploaded-apk'";
		try
		{
			console.getTextArea().append("@@@@@TRANSFERING...@@@@@");
			Cmd.exec(cmdPush,console.getTextArea());
			console.getTextArea().append("@@@@@INSTALLING...@@@@@");
			Cmd.exec(cmdInstall,console.getTextArea());
		}
		catch (IOException e)
		{ e.printStackTrace(); }
		console.getTextArea().append("@@@@@DONE!@@@@@");
		IO.popup("Done!");
	}
}
