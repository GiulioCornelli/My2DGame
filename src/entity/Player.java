package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity{
	
	//----Oggetti----//
	
	GamePanel gp;
	KeyHandler keyH;
	
	public Player(GamePanel gp,KeyHandler keyH) {
		
		this.gp = gp;
		this.keyH = keyH;
		this.setDefaultVariable();
		this.getPlayerImage();
	}
	
	//----Metodi----//
	
	public void setDefaultVariable() {
		
		x = 100;
		y = 100;
		speed = 4;
		direction = "down";
		
	}
	
	public void getPlayerImage() {//funzione che prende le immagini dalla cartella indicata
		
		try {
			
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));
			
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void update() {
		
		
		if(keyH.upP == true || keyH.downP == true || keyH.leftP == true || keyH.rightP) {

			if(keyH.upP == true) {	// movimeto su e giu
				direction = "up";
				y -= speed;
			}
			if(keyH.downP == true) {
				direction = "down";
				y += speed;
			}
			
			if(keyH.leftP) { // movimento destra sinistra
				direction = "left";
				x -= speed;
			}
			if(keyH.rightP) {
				direction = "right";
				x += speed;
			}
			
			spriteCounter++;
			if(spriteCounter> 12) {//permette di cambiare l'immagine se e' in movimento
				if(spriteNum == 1) {
					spriteNum=2;
				}
				else if(spriteNum == 2) {
					spriteNum=1;
				}
				spriteCounter= 0 ;
			}
		}
		
		
		
	}
	
	public void draw(Graphics2D g2) {
		
		//g2.setColor(Color.white);
		//g2.fillRect(x, y, gp.tileSize, gp.tileSize);disegna un rettangolo con dimesioni e posizioni indicate
		
		BufferedImage image = null;
		
		switch(direction) {//in base al stasto schiacciato disegna un immagine
			
			case "up":
				if(spriteNum ==1) {
					image = up1;
				}
				else if(spriteNum ==2) {
					image = up2;
				}
				break;
				
			case "down":
				if(spriteNum ==1) {
					image = down1;
				}
				else if(spriteNum ==2) {
					image = down2;
				}
				break;
				
			case "left":
				if(spriteNum ==1) {
					image = left1;
				}
				else if(spriteNum ==2) {
					image = left2;
				}
				break;
				
			case "right":
				if(spriteNum ==1) {
					image = right1;
				}
				else if(spriteNum ==2) {
					image = right2;
				}
				break;
			
			
		
		}
		g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
		
		
	}

}
