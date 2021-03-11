package Graphics;

import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JComponent;

public abstract class PowerUp extends JComponent {
	
	public PowerUp () {
	}
	
	public abstract void paintComponent (Graphics g);
	
	public abstract Rectangle getRectangle();

}
