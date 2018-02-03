package candroid;
import perezjquim.GUI.*;
import perezjquim.*;
import java.io.*;
import javax.swing.*;

public class candroid
{
	private static GUI main;
	private static File projectDirectory;
	private static TextArea currentDir;
	private static TextField moduleName;
	private static ScrollableTextArea console;
	private static JComboBox<String> devicesList;
	private static String adb = "/home/"+System.getProperty("user.name")+"/Android-sdk/platform-tools/adb ";
	private static String[] devIDs, devNames;
	public static void main(String[] args)
	{
		main = new GUI("Candroid");


		Panel panProject = new Panel("Project root directory");
		currentDir = new TextArea(TextArea.HEIGHT_SMALL,TextArea.WIDTH_MEDIUM);
		Button btnChooseDir = new Button("Choose directory",()->
			{
				projectDirectory = IO.askFolder();
				currentDir.setText(projectDirectory.getAbsolutePath());
			});
		panProject.add(currentDir);
		panProject.add(btnChooseDir);
		main.add(panProject);

		Panel panDevices = new Panel("Select a device");
		devicesList = new JComboBox<String>(new String[]{""});
		Button btnDevices = new Button("Refresh devices list",()->
			{
				refreshDeviceList();
			});
		panDevices.add(devicesList);
		panDevices.add(btnDevices);
		main.add(panDevices);

		Panel panModule = new Panel("Module to be compiled");
		moduleName = new TextField();
		panModule.add(moduleName);
		Button btnCompile = new Button("Compile and execute",()->
			{
				compileProject();
			});
		panModule.add(btnCompile);
		main.add(panModule);


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
		Cmd.exec(cmdGetIDs,hidden);
		devIDs = hidden.getText().trim().split("\n");
		
		hidden.clear();

		String[] cmdGetNames = { "/bin/sh","-c", adb + "devices -l | awk '{print $5}' | cut -f2 -d ':' | awk 'NF' " };
		Cmd.exec(cmdGetNames,hidden);
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

	private static void compileProject()
	{
		console.getTextArea().setText("@@@@@COMPILING...@@@@@");
		Cmd.exec("./gradlew --stop",projectDirectory);
		Cmd.exec("./gradlew :"+moduleName.getText()+":assembleDebug", console.getTextArea(), projectDirectory);
		Cmd.exec("./gradlew --stop",projectDirectory);
		console.getTextArea().append("@@@@@DONE!@@@@@");
		IO.popup("Operation complete!");
	}

}
