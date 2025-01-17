package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {

	GamePanel gp;
	Tile[] tile;
	int mapTileNum[][];
	
	
	public TileManager(GamePanel gp) {
		
		this.gp = gp;
		
		this.tile = new Tile[10];
		mapTileNum = new int[this.gp.tileSize][this.gp.tileSize];
		
		getTileImage();
		loadMap("/maps/map01.txt");
	}
	
	public void getTileImage() {
		try {
			
			tile[0] = new Tile();
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));
			
			tile[1] = new Tile();
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
			
			tile[2] = new Tile();
			tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));
			
			
		}catch(IOException e) {
			e.printStackTrace();

		}
	}
	
	public void loadMap(String path) {//carica la mappa
		
		try {
			InputStream is = getClass().getResourceAsStream(path);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));// legge il contenuto del file inportato
			
			int col = 0, row = 0;
			
			while(col < gp.maxScreenCol && row < gp.maxScreenRow) {
				
				String line = br.readLine();
				
				while(col < gp.maxScreenCol) {
					
					String numbers[] = line.split(" ");
					
					int num = Integer.parseInt(numbers[col]);// converte il singolo valore della riga in un intero
					
					mapTileNum[col][row] = num;
					col++;
				}
				if(col == gp.maxScreenCol) {
					col = 0;
					row++;
					
				}
				
			}
			br.close();
			
			
		}catch(Exception e) {
			
		}
		
	}
	
	public void draw(Graphics2D g2) {
		
		int col = 0, row = 0, x = 0, y = 0;
		
		while(col < gp.maxScreenCol && row < gp.maxScreenRow) { //genera la mappa in base al numero massimo delle colonne e delle righe possibili
			
			int tileNum = mapTileNum[col][row];// prende il singolo valore della mappa caricata
			
			g2.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
			col++;
			x += gp.tileSize;
			
			if(col == gp.maxScreenCol) {
				col = 0 ;
				x = 0;
				row++;
				y += gp.tileSize;
			}
		}
		
	}
	
}
