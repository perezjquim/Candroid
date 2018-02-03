package candroid;
import perezjquim.GUI.*;
import perezjquim.*;
import java.io.*;

public class candroid
{
	private static GUI main;
	private static File projDir;
	private static TextArea currentDir;

	public static void main(String[] args)
	{
		main = new GUI("Candroid");

		Panel panProject = new Panel("Project directory");
		currentDir = new TextArea(TextArea.HEIGHT_SMALL,TextArea.WIDTH_MEDIUM);
		Button btnChooseDir = new Button("Choose directory",()->
				{
					projDir = IO.askFolder();
					currentDir.setText(projDir.getAbsolutePath());
				});
		panProject.add(currentDir);
		panProject.add(btnChooseDir);
		main.add(panProject);

		Panel panModule = new Panel("Module to be compiled");
		TextField modField = new TextField();
		panModule.add(modField);
		main.add(panModule);

		Panel panConsole = new Panel();
		ScrollableTextArea console = new ScrollableTextArea(TextArea.HEIGHT_MEDIUM,TextArea.WIDTH_LARGE);
		panConsole.add(console);
		main.add(panConsole);

		main.start();
	}
}
