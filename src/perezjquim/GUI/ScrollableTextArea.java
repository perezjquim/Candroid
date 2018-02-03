package perezjquim.GUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ScrollableTextArea extends JScrollPane
{
	private final TextArea textArea;

	public ScrollableTextArea(int height,int width)
	{
		super();
		textArea = new TextArea(height,width);
		this.getViewport().add(textArea,null);
	}
	public ScrollableTextArea()
	{
		super();
		textArea = new TextArea();
		this.getViewport().add(textArea,null);
	}
	public TextArea getTextArea() { return textArea; }
}
