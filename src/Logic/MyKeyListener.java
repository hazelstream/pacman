package Logic;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Graphics.DrawingArea;
import Graphics.Model;

public class MyKeyListener implements KeyListener {
	
	private DrawingArea d;

	public MyKeyListener(DrawingArea drawingArea) {
		this.d = drawingArea;
	}

	@Override
	public void keyPressed(KeyEvent e) {

	     


		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
	    {
			//H�ger
			//System.out.println("Höger");
			d.getModel().getPlayer().goRight();
			
	    }
		else if (e.getKeyCode() == KeyEvent.VK_UP) {
			//Up
			//System.out.println("Upp");
			d.getModel().getPlayer().goUp();
		}
		
		else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			//Ner
			//System.out.println("Ner");
			d.getModel().getPlayer().goDown();
		}


	    else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
	    	//V�nster
	    	//System.out.println("Vänster");
	    	d.getModel().getPlayer().goLeft();
	    }

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}
