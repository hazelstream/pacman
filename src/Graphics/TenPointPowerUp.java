package Graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class TenPointPowerUp extends PowerUp {
	
	private int x;
	private int y;
	private Rectangle spuRectangle;
	private Image img;
	
	public TenPointPowerUp (int x, int y) {
		this.x = x;
		this.y = y;
		spuRectangle = new Rectangle(x, y, 35, 35);
		
		ImageIcon i = new ImageIcon("pics/broccoli.png");
		img = i.getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT);
		
	}
	
	public void paintComponent (Graphics g) {
//		g.setColor(Color.YELLOW);
//		g.fillOval(this.x, this.y, 35, 35);		
		g.drawImage(img, x, y, null);
	}

	
	public Rectangle getRectangle () {
		return spuRectangle;
	}
}
