package Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Logic.MainMenuListener;
import Logic.MyKeyListener;

public class DrawingArea extends JPanel {

	private Model model;
	private int gameStage = 1;
	private int currentLevel = 1;
	
	private File hsFile;
	private Image img = ImageIO.read(new File("pics/background2.png"));
	
	
	public DrawingArea() throws IOException {
	
		this.setPreferredSize(new Dimension(625, 625));
		//this.setBackground(Color.black);
		
		this.setVisible(true);
		model = new Model(getDrawingArea());
		this.addMouseListener(new MainMenuListener(gameStage, getDrawingArea()));
		
		System.out.println(hsFile);
	}
	
	public DrawingArea(File highScoreFile) throws IOException {
		this.setPreferredSize(new Dimension(625, 625));
		//this.setBackground(Color.black);
		this.setVisible(true);
		model = new Model(getDrawingArea());
		this.addMouseListener(new MainMenuListener(gameStage, getDrawingArea()));
		hsFile = highScoreFile;
		//System.out.println(hsFile);
		System.out.println(getHsFile());
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0, null);
		if (gameStage == 1) {
			model.getMainMenu().paintComponent(g);
			
		}
		
		
		else if (gameStage == 2) {

			//Rita planen			
			model.getLevelList().get(currentLevel).paintComponent(g);
			
			//Rita ut spelaren
			for(Player p : model.getPlayerList()) {
				p.paintComponent(g);
			}
			
			//Rita powerups			
			for(PowerUp p : model.getLevelList().get(currentLevel).getFivePowerUpList()) {
				p.paintComponent(g);
			}
			
			for(PowerUp p : model.getLevelList().get(currentLevel).getTenPowerUpList()) {
				p.paintComponent(g);
			}
			//Rita fiender
			for (Enemies e: model.getEnemyList()) {
				e.paintComponent(g);
			}
			//Rita ut highscore
			model.getHSPanel().paintComponent(g);
		}
		
		else if (gameStage == 3) {		
			model.getHighScoresMenu().paintComponent(g);
			
		}
		
		else if (gameStage == 4) {	
			model.getHelpMenu().paintComponent(g);	
		}	
	}
	
	public DrawingArea getDrawingArea() {
		return this;
	}
	
	public Model getModel() {
		return model;
	}

	public int getGameStage() {
		return gameStage;
	}

	public void setGameStage(int gameStage) {
		this.gameStage = gameStage;
	}
	
	public String saveScore() {
		String name = JOptionPane.showInputDialog(this, "Du fick " + model.getHSPanel().getScore() + " poäng. Skriv in ditt namn");
		
		return name;
	}
	
	public void setCurrentLevel(int currentLevel) {
		this.currentLevel = currentLevel;
		model.getPlayer().setCurrentLevel(this.currentLevel);	
		for (Enemies e : model.getEnemyList()) {
			e.setCurrentLevel(this.currentLevel);
		}		
	}
	
	public File getHsFile() {
		return hsFile;
	}
	
}
