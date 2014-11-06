/**
 * 
 */
package sea;

import java.util.LinkedList;
import java.util.Observable;

/**
 * @author antoine
 *
 */
public abstract class Fish extends Observable
{	
	/**
	 * The age of the fish (in cycle)
	 */
	protected int age;
	
	/**
	 * The sea in which the fish is living
	 */
	protected Sea sea;
	
	/**
	 * The coordinate of the fish in the sea
	 */
	protected Coordinate coordinate;

	/**
	 * 
	 * @param sea The {@link Sea} in which the fish live
	 */
	public Fish(Sea sea)
	{
		this.age = 0;
		this.sea = sea;
		this.addObserver(sea);	
	}
	
	/**
	 * Get the fish coordinate
	 * 
	 * @return the fish {@link Coordinate}
	 */
	public Coordinate getCoordinate()
	{
		return this.coordinate;
	}
	
	/**
	 * Set the coordinate of the fish
	 * 
	 * @param coord the new {@link Coordinate} of the fish
	 */
	public void setCoordinate(Coordinate coord)
	{
		Coordinate initial = this.coordinate;
		this.coordinate = coord;
		if (initial != null) {
			this.setChanged();
		}
		this.notifyObservers(new FishEvent(this, initial, FishEvent.EVENT_MOVED));
	}
	
	/**
	 * Retrieves the availables places in which
	 * the fish can move.
	 * 
	 * @return the list of availables places
	 */
	public LinkedList<Coordinate> getAvailablePlaces()
	{
		LinkedList<Coordinate> coords = Coordinate.getNeighbours(this.coordinate);
		
		/*
		 * Clone to prevent ConcurrentModificationException
		 */
		@SuppressWarnings("unchecked")
		LinkedList<Coordinate> coordsCloned = (LinkedList<Coordinate>) coords.clone();
		
		for (Coordinate coordinate : coordsCloned) {
			try{
				Fish fish = this.sea.getSquare(coordinate);
				
				if (fish != null) {
					coords.remove(coordinate);
				}
				
			} catch(IndexOutOfBoundsException e) {
				coords.remove(coordinate);
			}
		}	
		return coords;
	}
	
	/**
	 * Moves to an adjacent square 
	 */
	public void move() 
	{
		Coordinate target = Coordinate.getRandomCoord( this.getAvailablePlaces());
		
		/*
		 * If target is null it means
		 * that the fish have no available place
		 * to go. So we do nothing
		 */
		if (target != null) {
			// this.sea.moveFish(this, target);
			this.setCoordinate(target);	
		}
		
		/*
		 * At the end of the cycle
		 * the fish grow up
		 */
		this.age++;
	}
	
	/**
	 * 
	 */
	public void die()
	{
		System.out.println(this.getCoordinate()+" died");
		this.setChanged();
		this.notifyObservers(new FishEvent(this, this.coordinate, FishEvent.EVENT_DIED));
	}
	
	/**
	 * 
	 * @return The age of death (in cycle)
	 */
	public abstract int getDeathAge();
	
	/**
	 * 
	 * @return The reproduction frequency (in cycle)
	 */
	public abstract int getReproductionFrequency();
	
	@Override
	public String toString()
	{
		String ret = this.getClass().getSimpleName()+":\n";
		ret += "Age: "+this.age+"\n";
		ret += "Death age: "+this.getDeathAge()+"\n";
		ret += "Reproduction frequency: "+this.getReproductionFrequency()+"\n";
		ret += "Coordinate: ["+this.coordinate.getX()+";"+this.coordinate.getY()+"]\n";
		
		return ret;
	}
}