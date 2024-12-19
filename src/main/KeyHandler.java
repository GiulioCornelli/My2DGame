package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
	
	//----Variabili----//
	
	public boolean upP, downP, leftP, rightP;
	
	//----Metodi----//
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {//premuto
		
		int code = e.getKeyCode();//prende in input sotto forma di intero , il tasto premuto dall'utente
		
		if(code == KeyEvent.VK_W) {
			this.upP=true;
		}
		if(code == KeyEvent.VK_S) {
			this.downP=true;
		}
		if(code == KeyEvent.VK_A) {
			this.leftP=true;
		}
		if(code == KeyEvent.VK_D) {
			this.rightP=true;
		}
			
		
	}

	@Override
	public void keyReleased(KeyEvent e) {//rilasciato

		int code = e.getKeyCode();
		
		if(code == KeyEvent.VK_W) {
			this.upP=false;
		}
		if(code == KeyEvent.VK_S) {
			this.downP=false;
		}
		if(code == KeyEvent.VK_A) {
			this.leftP=false;
		}
		if(code == KeyEvent.VK_D) {
			this.rightP=false;
		}
		
	}

}
