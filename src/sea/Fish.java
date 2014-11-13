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
	 * A counter to know
	 */
	private int reproductionCounter;

	/**
	 * The class constructor
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
	 * Gets the fish age
	 * 
	 * @return the fish age
	 */
	public int getAge()
	{
		return this.age;
	}
	
	/**
	 * The sea in which the fish lives
	 * 
	 * @return the sea in which the fish lives
	 */
	public Sea getSea()
	{
		return this.sea;
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
	 * Set the coordinate of the fish and notify the
	 * sea that it has moved
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
	 * Perform all the action a fish 
	 * must do during a cycle
	 */
	public void nextCycle()
	{
		this.move();
		this.reproduct();
		this.checkDeathAge();
	}
	
	/**
	 * Check if the fish should reproduce by checking
	 * wether the reproductionCounter has raised the 
	 * reproduction frequency.
	 * 
	 * If true, a new Fish appears next to this Fish,
	 * else, we increment the reproductionCounter.
	 */
	public void reproduct()
	{	
		if (this.reproductionCounter >= this.getReproductionFrequency()) {
			Coordinate target = Coordinate.getRandomCoord(this.getAvailablePlaces());
			
			if (target != null) {
				this.sea.addFish(this.newChild(), target);
				this.reproductionCounter = 0;
				return;
			}
		}
		this.reproductionCounter++;
	}
	
	/**
	 * Notify the sea that this Fish died
	 */
	public void die()
	{
		this.setChanged();
		this.notifyObservers(new FishEvent(this, this.coordinate, FishEvent.EVENT_DIED));
	}
	
	/**
	 * Check if the fish has raised his
	 * maximal age
	 */
	public void checkDeathAge()
	{
		if (this.getAge() >= this.getDeathAge()) {
			this.die();
		}
	}
	
	/**
	 * Gets the age at which this fish should die
	 * 
	 * @return The age of death (in cycle)
	 */
	public abstract int getDeathAge();
	
	/**
	 * Gets the reproduction frequency of this Fish
	 * 
	 * @return The reproduction frequency (in cycle)
	 */
	public abstract int getReproductionFrequency();
	
	/**
	 * Get a new instance of the fish
	 * 
	 * @return a new instance of Fish
	 */
	public abstract Fish newChild();
	
	/**
	 * A short to String fonction used in
	 * {@link Sea#toString()}
	 * 
	 * @return Should return "x" for Sardine and "S" for Shark
	 */
	public abstract String toStringShort();
}