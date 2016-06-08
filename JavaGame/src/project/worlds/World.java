package project.worlds;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import project.entities.EntityManager;
import project.entities.creatures.Bullet;
import project.entities.creatures.Player;
import project.entities.creatures.Zombie;
import project.entities.statics.GreenTower;
import project.entities.statics.RedTower;
import project.tileGame.Handler;
import project.tiles.Tile;
import project.utils.Utils;

public class World {

	private Handler handler;
	private int width, height;
	private int spawnX, spawnY;
	private int[][] tiles;
	//Entities
	private EntityManager entityManager;
	
	private GreenTower towerGreen; 
	private RedTower towerRed; 
	
	private Zombie zombie1; 
	private Zombie zombie2; 
	private Zombie zombie3; 
	private Zombie zombie4; 
	private Zombie zombie5; 
	private Zombie zombie6; 
	private Zombie zombie7; 
	private Zombie zombie8; 

	private Bullet bullet; 

	int xTower1;
	int yTower1;

	
	public World(Handler handler, String path){
		this.handler = handler;
		entityManager = new EntityManager(handler, new Player(handler, 100, 100));
		
		//Temporary entity code!
		//entityManager.addEntity(new Tree(handler, 100, 100));
		//entityManager.addEntity(new Rock(handler, 100, 450));
		
		loadWorld(path);
		

		//Init Turret Shot Entities
		zombie1 = new Zombie(handler, 1000, 1000);
		zombie2 = new Zombie(handler, 1000, 1000);
		zombie3 = new Zombie(handler, 1000, 1000);
		zombie4 = new Zombie(handler, 1000, 1000);
		zombie5 = new Zombie(handler, 1000, 1000);
		zombie6 = new Zombie(handler, 1000, 1000);
		zombie7 = new Zombie(handler, 1000, 1000);
		zombie8 = new Zombie(handler, 1000, 1000);

     	entityManager.addEntity(zombie1);
     	entityManager.addEntity(zombie2);
     	entityManager.addEntity(zombie3);
     	entityManager.addEntity(zombie4);
     	entityManager.addEntity(zombie5);
     	entityManager.addEntity(zombie6);
     	entityManager.addEntity(zombie7);
     	entityManager.addEntity(zombie8);

     	
		bullet = new Bullet(handler, 100, 100);
     	entityManager.addEntity(bullet);

		entityManager.getPlayer().setX(spawnX);
		entityManager.getPlayer().setY(spawnY);
		
	}
	
	public void tick(){
		
		if(entityManager.getPlayer().stat.health == 0){
			System.exit(0);
		}
		
		if(ammo == 0){
			loaded = false;
			reloading = true;
		}
		if(ammo == 10){
			reloading = false;
			loaded = true;
		}
		
		entityManager.tick();
		
		zombie1.followPlayer(entityManager.getPlayer());
		zombie2.followPlayer(entityManager.getPlayer());
		zombie3.followPlayer(entityManager.getPlayer());
		zombie4.followPlayer(entityManager.getPlayer());
		zombie5.followPlayer(entityManager.getPlayer());
		zombie6.followPlayer(entityManager.getPlayer());
		zombie7.followPlayer(entityManager.getPlayer());
		zombie8.followPlayer(entityManager.getPlayer());

		if(!bullet.up){
			bullet.setX(entityManager.getPlayer().getX());
			bullet.setY(entityManager.getPlayer().getY());
		}
		
		if(loaded){
		if(!bullet.up){
		if(entityManager.getPlayer().fireRight){
			bullet.fire(1);
			ammo--;
		}
		if(entityManager.getPlayer().fireUp){
			bullet.fire(0);
			ammo--;
		}
		if(entityManager.getPlayer().fireLeft){
			bullet.fire(2);
			ammo--;
		}
		if(entityManager.getPlayer().fireDown){
			bullet.fire(3);
			ammo--;
		}
		}
		}
		
		if(bullet.up){
		//BulletCollision
			//Z1
		if (((bullet.getY() > zombie1.getY() - 20) && (bullet.getY() < zombie1.getY() + 50))
				// Left Right
				&& ((bullet.getX() > zombie1.getX() - 20) && (bullet.getX() < zombie1.getX() + 30))) {
			score+=100;
			zombie1.alive = false;
			zombie1.setX(1000);
			zombie1.setY(1000);
			numOfZombies--;
			bullet.up = false;
		}
		//Z2
		if (((bullet.getY() > zombie2.getY() - 20) && (bullet.getY() < zombie2.getY() + 50))
				// Left Right
				&& ((bullet.getX() > zombie2.getX() - 20) && (bullet.getX() < zombie2.getX() + 30))) {
			score+=100;
			zombie2.alive = false;
			zombie2.setX(1000);
			zombie2.setY(1000);
			numOfZombies--;

			bullet.up = false;
		}
		//Z3
		if (((bullet.getY() > zombie3.getY() - 20) && (bullet.getY() < zombie3.getY() + 50))
				// Left Right
				&& ((bullet.getX() > zombie3.getX() - 20) && (bullet.getX() < zombie3.getX() + 30))) {
			score+=100;
			zombie3.alive = false;
			zombie3.setX(1000);
			zombie3.setY(1000);
			numOfZombies--;

			bullet.up = false;
		}
		//Z4
		if (((bullet.getY() > zombie4.getY() - 20) && (bullet.getY() < zombie4.getY() + 50))
				// Left Right
				&& ((bullet.getX() > zombie4.getX() - 20) && (bullet.getX() < zombie4.getX() + 30))) {
			score+=100;
			zombie4.alive = false;
			zombie4.setX(1000);
			zombie4.setY(1000);
			numOfZombies--;

			bullet.up = false;
		}
		//Z5
		if (((bullet.getY() > zombie5.getY() - 20) && (bullet.getY() < zombie5.getY() + 50))
				// Left Right
				&& ((bullet.getX() > zombie5.getX() - 20) && (bullet.getX() < zombie5.getX() + 30))) {
			score+=100;
			zombie5.alive = false;
			zombie5.setX(1000);
			zombie5.setY(1000);
			numOfZombies--;

			bullet.up = false;
		}
		//Z6
		if (((bullet.getY() > zombie6.getY() - 20) && (bullet.getY() < zombie6.getY() + 50))
				// Left Right
				&& ((bullet.getX() > zombie6.getX() - 20) && (bullet.getX() < zombie6.getX() + 30))) {
			score+=100;
			zombie6.alive = false;
			zombie6.setX(1000);
			zombie6.setY(1000);
			numOfZombies--;

			bullet.up = false;
		}
		//Z7
		if (((bullet.getY() > zombie7.getY() - 20) && (bullet.getY() < zombie7.getY() + 50))
				// Left Right
				&& ((bullet.getX() > zombie7.getX() - 20) && (bullet.getX() < zombie7.getX() + 30))) {
			score+=100;
			zombie7.alive = false;
			zombie7.setX(1000);
			zombie7.setY(1000);
			numOfZombies--;

			bullet.up = false;
		}
		
		//Z8
		if (((bullet.getY() > zombie8.getY() - 20) && (bullet.getY() < zombie8.getY() + 50))
				// Left Right
				&& ((bullet.getX() > zombie8.getX() - 20) && (bullet.getX() < zombie8.getX() + 30))) {
			score+=100;
			zombie8.alive = false;
			zombie8.setX(1000);
			zombie8.setY(1000);
			numOfZombies--;

			bullet.up = false;
		}
		
		}
		
	}
	
	private static int timeMin = 0;
	private static int timeSec = 0;
	private static int score = 0;
	private static int timeAllSec = 0;
	private static int timeNextSec = timeAllSec+1;

	public void render(Graphics g){

		int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILEWIDTH);
		int xEnd = (int) Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILEWIDTH + 1);
		int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILEHEIGHT);
		int yEnd = (int) Math.min(height, (handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILEHEIGHT + 1);
		
		for(int y = yStart;y < yEnd;y++){
			for(int x = xStart;x < xEnd;x++){
				getTile(x, y).render(g, (int) (x * Tile.TILEWIDTH - handler.getGameCamera().getxOffset()),
						(int) (y * Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()));
			}
		}
		
		
		if(startGame){
		g.setColor(Color.white);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 40));

		g.drawString(startGameTimer+"", 450, 80);
		}
	
		
		
		g.setColor(Color.white);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 25));
	
		if(timeSec == 60){
			timeSec=0;
			timeMin+=1;
		}
		
		if(timeSec < 10){
		g.drawString("Time:  "+timeMin +" : 0"+timeSec, 10, 20);
		}else{
		g.drawString("Time:  "+timeMin +" : "+timeSec, 10, 20);
		}
	
		//Entities
		entityManager.render(g);
		
		g.drawString("Score: ", 10, 40);

		//Colored Score
		if(score >= 0){
			g.setColor(Color.white);
		} 
		if(score >= 1000){
			g.setColor(Color.yellow);
		}
		if(score >= 3000){
			g.setColor(Color.orange);
		}
		if(score >= 5000){
			g.setColor(Color.red);
		}
		if(score >= 7000){
			g.setColor(Color.magenta);
		}
		if(score >= 10000){
			g.setColor(Color.pink);
		}
		if(score >= 20000){
			g.setColor(Color.green);
		}

		/////////////////Dmg Cooldown
		if(entityManager.getPlayer().available == true){
			entityManager.getPlayer().setTime = timeAllSec+4;
			//System.out.println(entityManager.getPlayer().setTime);
			entityManager.getPlayer().canTakeDMG = false;
			entityManager.getPlayer().available=  false;

		}
 		//System.out.println(""+entityManager.getPlayer().canTakeDMG);

		if(!entityManager.getPlayer().canTakeDMG){
			
		if(entityManager.getPlayer().setTime == timeAllSec){
 		entityManager.getPlayer().canTakeDMG = true;
 		
		}}
		
		//////////////
		
		g.drawString(""+score, 75, 40);
		
		g.setColor(Color.red);
		g.drawString("Health: "+entityManager.getPlayer().stat.health, 10, 60);
		

		
		//Speed
//		if(entityManager.getPlayer().sprintCharge < 3){
//			timeAllSec = 9;
//		}
//			
			if(timeAllSec == timeNextSec){
				//
				if(timeAllSec == 1){
					
					numOfZombies=0;
					System.out.println("DSADASDASDASDAdddS");

				}
				if(numOfZombies == 0){
				round++;
				zombie1.alive = true;
				zombie2.alive = true;
				zombie3.alive = true;
				zombie4.alive = true;
				zombie5.alive = true;
				zombie6.alive = true;
				zombie7.alive = true;
				zombie8.alive = true;
				switch(round){
				case 1:
					numOfZombies = 1;
					break;
				case 2:
					numOfZombies = 2;
					break;
				case 3:
					numOfZombies = 3;
					break;
				case 4:
					numOfZombies = 4;
					break;
				case 5:
					numOfZombies = 5;
					break;
				case 6:
					numOfZombies = 6;
					break;
				case 7:
					numOfZombies = 7;
					break;	
				default:
					numOfZombies = 8;
					break;
				}
				
				toBeSpawned = numOfZombies;
				}
				//
				if(entityManager.getPlayer().sprinting){
					entityManager.getPlayer().sprintCharge -= 1;

				}
				
				if(entityManager.getPlayer().sprintCharge < 3){
				if(!entityManager.getPlayer().sprinting){
					entityManager.getPlayer().sprintCharge += 1;
				}
				}
				
				if(reloading){
					ammo++;
				}
				
				// 1 then 2 then 3 then 4
				//minus
				
				if(toBeSpawned > 0){
					
				switch(toBeSpawned){
				case 1:
					//spawn 8 at 4
					zombie8.setX(800);
					zombie8.setY(800);
					break;
				case 2:
					//spawn 7 at 3
					zombie7.setX(65);
					zombie7.setY(800);
					break;
				case 3:
					//spawn 6 at 2
					zombie6.setX(800);
					zombie6.setY(65);
					break;
				case 4:
					//spawn 5 at 1
					zombie5.setX(65);
					zombie5.setY(65);
					break;
				case 5:
					//spawn 4 at 4
					zombie4.setX(800);
					zombie4.setY(800);
					break;
				case 6:
					//spawn 3 at 3
					zombie3.setX(65);
					zombie3.setY(800);
					break;
				case 7:
					//spawn 2 at 2
					zombie2.setX(800);
					zombie2.setY(65);
					break;
				default:
					//spawn 1 at 1
					zombie1.setX(65);
					zombie1.setY(65);
				//	System.out.println(x);
					break;
			
				
				}
				toBeSpawned--;
				}
				
				//
				timeNextSec++;
			}

			g.setColor(Color.yellow);
			g.drawString("Sprint: "+entityManager.getPlayer().sprintCharge, 10, 80);
			if(reloading){
				g.setColor(Color.gray);
			}else{
				g.setColor(Color.white);
			}
			g.drawString("Ammunition: "+ammo, 10, 100);
			g.setColor(Color.white);
			g.drawString("Round: "+round, 10, 120);


	}
	
	private boolean loaded = true;
	private boolean reloading = false;
	private int ammo = 10;
	
	
	public Tile getTile(int x, int y){
		if(x < 0 || y < 0 || x >= width || y >= height)
			return Tile.grassTile;
		
		Tile t = Tile.tiles[tiles[x][y]];
		if(t == null)
			return Tile.dirtTile;
		return t;
	}
	
	private void loadWorld(String path){
		String file = Utils.loadFileAsString(path);
		String[] tokens = file.split("\\s+");
		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);
		spawnX = Utils.parseInt(tokens[2]);
		spawnY = Utils.parseInt(tokens[3]);
		
		tiles = new int[width][height];
		for(int y = 0;y < height;y++){
			for(int x = 0;x < width;x++){
				tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);
			}
		}
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	private static boolean startGame = true;
	private static int startGameTimer = 15;
	public int round = 0;
	public int numOfZombies = 1;
	public int toBeSpawned = numOfZombies;

	public static void Timer() {
		if(startGame){
			startGameTimer--;
			if(startGameTimer == 0){
				startGame = false;
			}
		}else{
		timeSec+=1;
		timeAllSec ++;
		}	
	}

	
}








