package javagame;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Polygon;
 
/**
 * Creates a polygon around passed coordinates (which will be a specific
 * tile). This Polygon will act as a collision detector between the said
 * tile and the character.
 *
 * @author Denny Scott
 * @version 8/10/2012
 *
 */
public class Block  {
	
	public Polygon poly;
	
	/**
	 * Constructor: Creates a new polygon that will be used for collision detection.
	 * @param x x-coordinate of tile detected in collision layer
	 * @param y y-coordinate of tile detected in collision layer
	 * @param test Shape of tile (generally square)
	 * @param type Name of the type of tile (generally Square).
	 */
	public Block(int x, int y, int test[],String type) {
        poly = new Polygon(new float[]{
				x+test[0], y+test[1],
				x+test[2], y+test[3],
				x+test[4], y+test[5],
				x+test[6], y+test[7],
        });   
	}

	/**
	 * Draw the polygon shape onto the specified tile.
	 * @param g
	 */
	public void draw(Graphics g) {
		g.draw(poly);
	}
}