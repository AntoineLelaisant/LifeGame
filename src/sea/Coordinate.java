/**
 * 
 */
package sea;

import java.util.LinkedList;
import java.util.Random;

/**
 * Coordinate Class
 * Represents a coordinate in the sea's area
 * 
 * @author antoine
 */
public class Coordinate
{
	/** the x index */
	private int x;
	
	/** the y index */
	private int y;
	
	/**
	 * Creates a coordinate according to
	 * the given indexes
	 * 
	 * @param x the x index
	 * @param y the y index
	 */
	public Coordinate(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	/**
	 * Gets the x index
	 * 
	 * @return the x index
	 */
	public int getX()
	{
		return x;
	}

	/**
	 * Sets the x index
	 * 
	 * @param x the x index
	 */
	public void setX(int x)
	{
		this.x = x;
	}

	/**
	 * Gets the y index
	 * 
	 * @return the y index
	 */
	public int getY()
	{
		return y;
	}
	
	/**
	 * Sets the y index
	 * 
	 * @param y the y index
	 */
	public void setY(int y)
	{
		this.y = y;
	}

	/**
	 * Generates a random coordinate into the given bounds
	 * 
	 * @param boundX the bound of the x index
	 * @param boundY the bound of the y index
	 * @return a new random Coordinate
	 */
	public static Coordinate getRandomCoord(int boundX, int boundY)
	{
		Random rand = new Random();
		int x = rand.nextInt(boundX);
		int y = rand.nextInt(boundY);
		return new Coordinate(x, y);
	}
	
	/**
	 * Retrieve a random Coordinate from the given LinkedList
	 * 
	 * @param coords the list of Coordinate
	 * @return a Coordinate or null if coords is empty
	 */
	public static Coordinate getRandomCoord(LinkedList<Coordinate> coords)
	{
		int size = coords.size();
		if (size > 0) {
			Random rand = new Random();
			int index = rand.nextInt(size);
			return coords.get(index);
		} else {
			return null;
		}
	}
	
	/**
	 * Get the upper Coordinate of the given one
	 * 
	 * @param initial the coordinate from which to search
	 * @return the retrived Coordinate
	 */
	public static Coordinate getUpCoord(Coordinate initial)
	{
		return new Coordinate(initial.getX()+1,initial.getY());
	}
	
	/**
	 * Get the bottom Coordinate of the given one
	 * 
	 * @param initial the coordinate from which to search
	 * @return the retrived Coordinate
	 */
	public static Coordinate getBottomCoord(Coordinate initial)
	{
		return new Coordinate(initial.getX()-1,initial.getY());
	}
	
	/**
	 * Get the left Coordinate of the given one
	 * 
	 * @param initial the coordinate from which to search
	 * @return the retrived Coordinate
	 */
	public static Coordinate getLeftCoord(Coordinate initial)
	{
		return new Coordinate(initial.getX(),initial.getY()-1);
	}
	
	/**
	 * Get the left Coordinate of the given one
	 * 
	 * @param initial the coordinate from which to search
	 * @return the retrived Coordinate
	 */
	public static Coordinate getRightCoord(Coordinate initial)
	{
		return new Coordinate(initial.getX(),initial.getY()+1);
	}
	
	/**
	 * Get the upper left Coordinate of the given one
	 * 
	 * @param initial the coordinate from which to search
	 * @return the retrived Coordinate
	 */
	public static Coordinate getUpLeftCoord(Coordinate initial)
	{
		return new Coordinate(initial.getX()+1,initial.getY()-1);
	}
	
	/**
	 * Get the upper right Coordinate of the given one
	 * 
	 * @param initial the coordinate from which to search
	 * @return the retrived Coordinate
	 */
	public static Coordinate getUpRightCoord(Coordinate initial)
	{
		return new Coordinate(initial.getX()+1,initial.getY()+1);
	}
	
	/**
	 * Get the bottom left Coordinate of the given one
	 * 
	 * @param initial the coordinate from which to search
	 * @return the retrived Coordinate
	 */
	public static Coordinate getBottomLeftCoord(Coordinate initial)
	{
		return new Coordinate(initial.getX()-1,initial.getY()-1);
	}
	
	/**
	 * 
	 * @param intial the coordinate from which to search
	 * @return the retrived Coordinate
	 */
	public static Coordinate getBottomRightCoord(Coordinate initial)
	{
		return new Coordinate(initial.getX()-1,initial.getY()+1);
	}
	
	/**
	 * Gets the coordinate neightbours
	 * 
	 * @param intial the coordinate from which to search
	 * @return the neighbours coordinates
	 */
	public static LinkedList<Coordinate> getNeighbours(Coordinate initial)
	{
		LinkedList<Coordinate> coords = new LinkedList<Coordinate>();
		
		// Get the upper coordinate
		coords.add(Coordinate.getUpCoord(initial));
		// Get the bottom coordinate
		coords.add(Coordinate.getBottomCoord(initial));
		// Get the lef coordinate
		coords.add(Coordinate.getLeftCoord(initial));
		// Get the right coordinate
		coords.add(Coordinate.getRightCoord(initial));
		// Get the upper left coordinate
		coords.add(Coordinate.getUpLeftCoord(initial));
		// Get the upper right coordinate
		coords.add(Coordinate.getUpRightCoord(initial));
		// Get the bottom left coordinate
		coords.add(Coordinate.getBottomLeftCoord(initial));
		// Get the bottom right coordinate
		coords.add(Coordinate.getBottomRightCoord(initial));
		
		return coords;
	}
	
	/**
	 * Gets the distance between two Coordinate
	 * (assuming we can move diagonally)
	 * 
	 * @param coord1 A coordinate
	 * @param coord2 A coordinate
	 * @return the distance between the two Coordinate
	 */
	public static int getDistance(Coordinate coord1, Coordinate coord2)
	{
		int deltaX = Math.abs(coord1.getX() - coord2.getX());
		int deltaY = Math.abs(coord1.getY() - coord2.getY());
		
		int step = 0;
		
		while(deltaX > 0 || deltaY > 0) {
			if (deltaX > 0) {
				deltaX--;
				if (deltaY > 0) {
					deltaY--;
				}
			} else {
				deltaY--;
			}
			step++;
		}
		return step;
	}
	
	/**
	 * Gets the list of the nearest Coordinates from 
	 * the target Coordinate
	 * 
	 * @param coords a list of Coordinate
	 * @param target the target Coordinate
	 * @return LinkedList<Coordinate> 
	 */
	public static LinkedList<Coordinate> getNearestCoords(LinkedList<Coordinate> coords, Coordinate target)
	{
		LinkedList<Coordinate> nearest = new LinkedList<Coordinate>();
		
		for (Coordinate coordinate : coords) {
			if (nearest.size() == 0) {
				nearest.add(coordinate);
			} else {
				int nearestDistance = Coordinate.getDistance(target, nearest.getFirst());
				int currentDistance = Coordinate.getDistance(target, coordinate);
				
				if (nearestDistance == currentDistance) {
					nearest.add(coordinate);
				} else if (nearestDistance > currentDistance) {
					nearest.clear();
					nearest.add(coordinate);
				}
			}
		}
		return nearest;
	}
	
	@Override
	public String toString()
	{
		return "["+this.x+";"+this.y+"]";
	}
}
