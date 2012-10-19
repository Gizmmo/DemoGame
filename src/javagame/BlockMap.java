package javagame;

import java.util.ArrayList;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;
 
/**
 * Connects Blocks to Imported TiledMaps, that will allow collision detection
 * on such things as walls. This is passed by any object that is placed onto the
 * bottom layer (layer 0).
 * 
 * BlockMap calls on block to create a polygonal shape to act as a collision detection
 * unit.
 * 
 * @author Denny Scott
 * @version 8/10/2012
 *
 */
public class BlockMap {
	
	public TiledMap tmap; //imported Tiled Map
	public int mapWidth; //map width * tile width
	public int mapHeight; //map height * tile height
	
	private int square[] = {1,1,15,1,15,15,1,15}; //square shaped tile
	public ArrayList<Block> entities;
 
	/**
	 * Constructor. This will assign the passed map as a tiled map, collect
	 * the necessary information, such as height and width, and detect which
	 * tiles need collision added to them.
	 * 
	 * @param ref The reference to a .tmx Tiled file.
	 * @throws SlickException
	 */
	public BlockMap(String ref) throws SlickException {
		
		entities = new ArrayList<Block>(); //Collect Blocks that need collision detection
		tmap = new TiledMap(ref, "res"); 
		init();
	}
	
	public void init(){
		
		//Get True width and Height
		mapWidth = tmap.getWidth() * tmap.getTileWidth();
		mapHeight = tmap.getHeight() * tmap.getTileHeight();
		
		//Detect which tiles in the Collision Layer have been drawn. All
		//tiles in this layer are unpassable.
		for (int x = 0; x < tmap.getWidth(); x++) {
			for (int y = 0; y < tmap.getHeight(); y++) {
				int tileID = tmap.getTileId(x, y, 0);
				if (tileID > 0) {
					entities.add(
                       new Block(x * 16, y * 16, square, "square")
                                );
				}
			}
		}
	}
	
	public void render(int x, int y){
		tmap.render(x,y);
	}
}