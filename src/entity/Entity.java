package entity;

import java.awt.image.BufferedImage;

public class Entity {//callasse padre di ogni entita
	
	public int x,y;
	public int speed;
	
	public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;//permette di tenere in una spece di arry delle immagini
	public String direction;
	
	public int spriteCounter = 0;
	public int spriteNum = 1;
}
