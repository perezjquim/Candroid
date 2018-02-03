package perezjquim.GUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RadioButton extends JRadioButton
{
	public RadioButton(String label)
	{
		super(label);
		this.setActionCommand(label);
	}
}
