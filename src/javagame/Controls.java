package javagame;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Polygon;

public class Controls{
	
	public float speed = 3;
	
	private float playerX=0;
	private float playerY=0;
	private Polygon playerPoly;
	
	private int cameraX;;


	private int cameraY;;
	
	Animation player;
	BlockMap map;
	
	private GameContainer gc;
	
	public Controls(float playerX, float playerY, float speed, BlockMap map, GameContainer gc) throws SlickException{
		this.playerX = playerX;
		this.playerY = playerY;
		this.speed = speed;
		this.gc = gc;
		this.map = map;
		init();
	}
	
	public void init() throws SlickException{
		SpriteSheet sheet = new SpriteSheet("res/Karbonator.png",42,43);
		player = new Animation(sheet, 190);
		player.setAutoUpdate(true);
		cameraX = 0;
		cameraY = 0;
		
		playerPoly = new Polygon(new float[]{
				playerX,playerY,
				playerX+32,playerY,
				playerX+32,playerY+32,
				playerX,playerY+32});
	}
	
	public void render(Graphics g){
		g.drawAnimation(player, playerX + cameraX, playerY + cameraY);
	}
	
	public void update() throws SlickException{
		if (gc.getInput().isKeyDown(Input.KEY_LEFT)) {
			if((playerX - gc.getWidth()/2)  <= 0){
				playerX -= speed;			
				playerPoly.setX(playerX);
				if (entityCollisionWith()){
					playerX += speed;
					playerPoly.setX(playerX);
				}
			}
			else if((playerX + gc.getWidth()/2)  > map.mapWidth){
				playerX -= speed;			
				playerPoly.setX(playerX);
				if (entityCollisionWith()){
					playerX += speed;
					playerPoly.setX(playerX);
				}
			}
			else{
						
			playerX -= speed;
			cameraX += speed;
			playerPoly.setX(playerX);
			if (entityCollisionWith()){
				playerX += speed;
				cameraX -= speed;
				playerPoly.setX(playerX);
			}
			}
		}
		if (gc.getInput().isKeyDown(Input.KEY_RIGHT)) {
			if((playerX + gc.getWidth()/2)  >= map.mapWidth-2){
				playerX += speed;			
				playerPoly.setX(playerX);
				if (entityCollisionWith()){
					playerX -= speed;
					playerPoly.setX(playerX);
				}
			}
			else if((playerX - gc.getWidth()/2)  < 0){
				playerX += speed;			
				playerPoly.setX(playerX);
				if (entityCollisionWith()){
					playerX -= speed;
					playerPoly.setX(playerX);
				}
			}
			else{
			playerX += speed;
			cameraX -= speed;
			playerPoly.setX(playerX);
			if (entityCollisionWith()){
				playerX -= speed;
				cameraX += speed;
				playerPoly.setX(playerX);
			}
			}
		}
		if (gc.getInput().isKeyDown(Input.KEY_UP)) {			
			if((playerY - gc.getHeight()/2)  <= 0){
				playerY -= speed;
				playerPoly.setY(playerY + cameraY);
				if (entityCollisionWith()){
					playerY += speed;
					playerPoly.setY(playerY);
				}
			}
			else if((playerY + gc.getHeight()/2)  > map.mapHeight ){
				playerY -= speed;
				
				playerPoly.setY(playerY);
				if (entityCollisionWith()){
					playerY += speed;
					
					playerPoly.setY(playerY);
				}
			}
			else{
			playerY -= speed;
			cameraY += speed;
			playerPoly.setY(playerY);
			if (entityCollisionWith()){
				playerY += speed;
				cameraY -= speed;
				playerPoly.setY(playerY);
			}
			}
		}
		if (gc.getInput().isKeyDown(Input.KEY_DOWN)) {
			
			if((playerY + gc.getHeight()/2)  >= map.mapHeight-2){
				playerY += speed;
				
				playerPoly.setY(playerY);
				if (entityCollisionWith()){
					playerY -= speed;
					
					playerPoly.setY(playerY);
				}
			}
			else if((playerY - gc.getHeight()/2)  < 0){
				playerY += speed;
				playerPoly.setY(playerY);
				if (entityCollisionWith()){
					playerY -= speed;
					playerPoly.setY(playerY);
				}
			}
			else{	
			playerY += speed;
			cameraY -= speed;
			playerPoly.setY(playerY);
			if (entityCollisionWith()){
				playerY -= speed;
				cameraY += speed;
				playerPoly.setY(playerY);
			}
		}
		}
	}
		
		public boolean entityCollisionWith() throws SlickException {
			for (int i = 0; i < map.entities.size(); i++) {
				Block entity1 = (Block) map.entities.get(i);
				if (playerPoly.intersects(entity1.poly)) {
					return true;
				}       
			}       
			return false;
		
	}
		
		public float getSpeed() {
			return speed;
		}

		public void setSpeed(float speed) {
			this.speed = speed;
		}

		public float getPlayerX() {
			return playerX;
		}

		public void setPlayerX(float playerX) {
			this.playerX = playerX;
		}

		public float getPlayerY() {
			return playerY;
		}

		public void setPlayerY(float playerY) {
			this.playerY = playerY;
		}

		public Polygon getPlayerPoly() {
			return playerPoly;
		}

		public void setPlayerPoly(Polygon playerPoly) {
			this.playerPoly = playerPoly;
		}

		public int getCameraX() {
			return cameraX;
		}

		public void setCameraX(int cameraX) {
			this.cameraX = cameraX;
		}

		public int getCameraY() {
			return cameraY;
		}

		public void setCameraY(int cameraY) {
			this.cameraY = cameraY;
		}

		public Animation getPlayer() {
			return player;
		}

		public void setPlayer(Animation player) {
			this.player = player;
		}

		public GameContainer getGc() {
			return gc;
		}

		public void setGc(GameContainer gc) {
			this.gc = gc;
		}
	}


