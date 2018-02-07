package perezjquim.GUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TextArea extends JTextArea
{
  public static final int WIDTH_LARGE = 40;
  public static final int WIDTH_MEDIUM = 25;
  public static final int WIDTH_SMALL = 10;

  public static final int HEIGHT_LARGE = 20;
  public static final int HEIGHT_MEDIUM = 10;
  public static final int HEIGHT_SMALL = 1;

  public TextArea(int height, int width)
  {
  	super(height,width);
  	this.setLineWrap(true);
  	this.setWrapStyleWord(false);
  	this.setEditable(false);
  }

  public TextArea()
  {
    super(HEIGHT_MEDIUM,WIDTH_MEDIUM);
    this.setLineWrap(true);
    this.setWrapStyleWord(false);
    this.setEditable(false);
  }

  public void append(String s)
  {
    setText(getText()+s+"\n");
  }

  public void clear()
  {
    setText("");
  }
}
