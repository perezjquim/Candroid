package perezjquim.GUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ScrollableTextArea extends JScrollPane
{
	public ScrollableTextArea(int height,int width)
	{
		super(new TextArea(height,width));
	}
}
