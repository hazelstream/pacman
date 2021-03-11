package Graphics;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import Logic.MyKeyListener;

public class Game {


	private boolean isRunning = true;
	private File highScoreFile;
	private DrawingArea drawingArea;
	private GameFrame frame;
	private int scoreStyr = 0;
	private int gameStage = 1;
	private int monsterStyr = 0;
	private int monsterRandom = 0;
	private int currentLevel = 0;
	private ArrayList<String> scoreList = new ArrayList<String>();


	public Game() throws IOException {

		if (highScoreFile == null) {
			highScoreFile = new File("HIGHSCORES.txt");
			drawingArea = new DrawingArea(highScoreFile);
		}
		else {
			drawingArea = new DrawingArea();
		}
		
		

	}

	public void run() throws InterruptedException, IOException  {
		frame = new GameFrame(drawingArea);
		frame.addKeyListener(new MyKeyListener(drawingArea));
		setCurrentLevel(currentLevel);

		while(isRunning) {
			Thread.sleep(5);
			if (scoreStyr >= 200 && gameStage == 2) {
				updateScore();
				scoreStyr = 0;

			}
			if (gameStage == 2 && monsterStyr >= 5) {
				updateMonster();
				monsterStyr = 0;
				monsterRandom ++;
				if (monsterRandom >= 50) {
					for (Enemies e: drawingArea.getModel().getEnemyList()) {
						e.randomize();
						monsterRandom = 0;
					}
				}
			}
			scoreStyr += 1;
			monsterStyr ++;
			updateGameStage();
			if (drawingArea.getModel().getLevelList().get(currentLevel).getFivePowerUpList().size() == 0 && 
					drawingArea.getModel().getLevelList().get(currentLevel).getTenPowerUpList().size() == 0 && currentLevel == 0) {
				setCurrentLevel(1);
				this.currentLevel = 1;
				nextLevel();
			}
			else if (drawingArea.getModel().getLevelList().get(currentLevel).getFivePowerUpList().size() == 0 && 
					drawingArea.getModel().getLevelList().get(currentLevel).getTenPowerUpList().size() == 0 && currentLevel == 1) {
				isRunning = false;
				saveScore();
			}
			if (drawingArea.getModel().getPlayer().fivePoints()) {
				drawingArea.getModel().getHSPanel().addScore(5);
			}
			if (drawingArea.getModel().getPlayer().tenPoints()) {
				drawingArea.getModel().getHSPanel().addScore(10);
			}
			if (drawingArea.getModel().getPlayer().enemyCollision()) {
				drawingArea.getModel().getHSPanel().removeScore(30);
				isRunning = false;
				saveScore();
			}
			if (drawingArea.getModel().getHSPanel().getScore() <= 0) {
				isRunning = false;
				saveScore();
			}
			drawingArea.repaint();
			//System.out.println(drawingArea.getModel().getLevelList().get(currentLevel).getPowerUpList().size());
			
		}

	}

	//Uppdaterar spelarens position. 
	public void updatePlayer() {

	}

	//Uppdaterar fienders position. 
	public void updateMonster() {
		for(Enemies e : drawingArea.getModel().getEnemyList()) {
			e.move();
		}
	}

	//Uppdaterar power ups.
	public void updatePowerUp() {

	}
	
	public void setCurrentLevel(int currentLevel) {
		drawingArea.setCurrentLevel(currentLevel);
	}

	public void updateScore() {
		drawingArea.getModel().getHSPanel().removeScore(1);
	}

	public void saveScore() throws IOException {
		String name = drawingArea.saveScore();
		String score = Integer.toString(drawingArea.getModel().getHSPanel().getScore());
		String current = name + ":" + score;
		// insertSorted(current);
		scoreList.add(current);
		ObjectOutputStream outStream = new ObjectOutputStream(new FileOutputStream("HIGHSCORES.txt"));
		
		outStream.writeObject(scoreList);
		
		outStream.close();
	}

	
	public void loadScore() {
	
		ObjectInputStream inStream = null;
		
		try {
			inStream = new ObjectInputStream(new FileInputStream("HIGHSCORES.txt"));
			
			scoreList = (ArrayList<String>) inStream.readObject();
			
		} catch (FileNotFoundException e) {
			scoreList = new ArrayList<String>();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (inStream != null) {
					inStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();

			}
		}
	}
	
	public void updateGameStage() {
		this.gameStage = drawingArea.getGameStage();
	}

	public int getGameStage() {
		return gameStage;
	}

	public void setGameStage(int gameStage) {
		this.gameStage = gameStage;
	}
	
	public File getHighScoresFile() {
		return highScoreFile;
	}
	
	public void nextLevel() {
		drawingArea.getModel().getPlayer().setStandardPosition();
		for(Enemies e : drawingArea.getModel().getEnemyList()) {
			e.setStandardPosition();	     
	}
  }
}



