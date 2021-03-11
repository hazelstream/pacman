package Graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JComponent;

public class HighScoreMenu extends JComponent{
	
	private DrawingArea drawingArea;
	private int gameStage;
	private String firstLine;
	private File hsFile;

	public HighScoreMenu(DrawingArea drawingArea, int gameStage) throws IOException {
		this.drawingArea = drawingArea;
		this.gameStage = gameStage;
		
		
		hsFile = drawingArea.getHsFile();
		
		//System.out.println(hsFile);
		//BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(hsFile)));
		
		//firstLine = reader.readLine();
		
		//reader.close();
		
	}
	
	public void paintComponent(Graphics g) {
		g.setFont(new Font("arial", Font.BOLD, 16));
		
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 0, 675, 675);

		
		
		g.setColor(Color.magenta);
		g.fillRect(245, 500, 185, 40);
		
		g.setColor(Color.pink);
		g.fillRect(200, 100, 275, 350);
		
		
		g.setColor(Color.black);
		g.drawString("1. ", 220, 120);
		
		
		
		g.setColor(Color.black);
		g.drawString("BACK", 310, 525);
	}

}
