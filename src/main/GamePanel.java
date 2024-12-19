package main;

import java.awt.*;

import javax.swing.JPanel;

import entity.Player;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{
	
	//----variabili----//
	
	final int originalTileSize = 16; //ogni elemento del gioco sara' di 16px*16px
	final int scale = 3;
	
	public final int tileSize = this.originalTileSize*this.scale; //permette di avere un gioco in 16*16 ma con una sacala superiore
	
	public final int maxScreenCol = 16;//dimensione schermo verticale
	public final int maxScreenRow = 12;//dimensione schermo orizzontale
	public final int screenWidth = this.tileSize * this.maxScreenCol;
	public final int screenHeight = this.tileSize * this.maxScreenRow;
	
	//FPS
	int FPS = 60;
	

	
	
	//----Oggetti----//
	
	TileManager tileM = new TileManager(this);
	KeyHandler keyH = new KeyHandler();
	Thread gameThread;
	Player player = new Player (this,this.keyH);
	
	
	//----Metodi----//
	
	public GamePanel() {
		
		this.setPreferredSize(new Dimension(this.screenWidth,this.screenHeight));//inserisce le dimensioni scelte
		this.setBackground(Color.DARK_GRAY);
		this.setDoubleBuffered(true);//mettendolo di defalult, nei giochi 2D, renderizza maglio
		
		this.addKeyListener(keyH);
		this.setFocusable(true);//indica che questo GameP e' in ascolto per ricevere input da tastiera
		
	}

	
	public void startGameThread() {// metodo che fa partire il thread (gameLoop)
		
		gameThread = new Thread(this);
		gameThread.start();
		
	}
	
	@Override
	public void run() {
		
		double drowInterval = 1000000000/this.FPS;
		double nextDrawTime = System.nanoTime() + drowInterval;
	
		while(gameThread != null) { // game loop
			
			System.out.println("FPS:60");

			update();
			
			repaint();// richiama paintComponent, essendo che e' una calsse base di java
			
			//intervallo in millisecondi tra un disegno e l'altro
			try {
				
				double remainingTime = nextDrawTime - System.nanoTime();
				remainingTime = remainingTime/1000000 ;
				
				if(remainingTime <0) {
					remainingTime=0;
				}
				
				Thread.sleep((long)remainingTime);
				
				nextDrawTime += drowInterval;
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	
	public void update() {
		
		player.update();
		
	}
	
	public void paintComponent(Graphics g) { //metodo presente in java per disegnare sul JPanel
		
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g; // e' una calsse piu' sofisticata di ghrafics e permette di modellare meglio l'immagine
		
		tileM.draw(g2);//prima del player va disegnata la mappa, perche' cosi' facendo si creano diversi leier
		
		player.draw(g2);
		g2.dispose();//pratica da usare una volta disegnata l'immagine
	}
	

}
