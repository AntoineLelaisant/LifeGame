/**
 * 
 */
package sea;

import java.util.LinkedList;

/**
 * Shark Class.
 * 
 * 
 * 
 * @author antoine
 */
public class Shark extends Fish
{
	public static final int AGE_SEMI_ADULT = 5;
	public static final int AGE_ADULT = 10;
	
	public static final int MAX_HUNGER_CYCLE = 5;
	
	/**
	 * Counter used to know how long
	 * this Shark ate a {@link Sardine} 
	 * for the last time
	 */
	private int hungerCounter;
	
	// Implementation of the pattern state
	public final SharkStateChild stateChild = new SharkStateChild(this);
	public final SharkStateSemiAdult stateSemiAdult = new SharkStateSemiAdult(this);
	public final SharkStateAdult stateAdult = new SharkStateAdult(this);
	
	private SharkState state;
	
	/**
	 * The class constructor
	 * Use the {@link Fish#Fish(Sea)} constructor
	 * 
	 */
	public Shark(Sea sea)
	{
		super(sea);
		this.state = this.stateChild;
		this.hungerCounter = 0;
	}
	
	@Override
	public void setCoordinate(Coordinate coord)
	{
		/*
		 * If the target square is a Sardine
		 * it dies 
		 */
		if (this.sea.getSquare(coord) instanceof Sardine) {
			this.sea.getSquare(coord).die();
			this.hungerCounter = 0;
		} else {
			this.hungerCounter++;
		}
		
		super.setCoordinate(coord);
	}
	
	/**
	 * Set the state of the Shark
	 * 
	 * @param state the state of the Shark
	 */
	public void setState(SharkState state)
	{
		this.state = state;
	}
	
	/**
	 * Move the shark.
	 * 
	 * Delagate this action to the state
	 */
	public void move()
	{
		this.state.move();
		
		/*
		 * At the end of the cycle
		 * the fish grow up
		 */
		this.age++;
		
		this.state.checkAge();
	}
	
	/**
	 * Do the default {@link Fish} actions
	 * and call the {@link Shark#checkHunger()} method too.
	 * 
	 * @see Fish#nextCycle()
	 */
	public void nextCycle() 
	{
		super.nextCycle();
		this.checkHunger();
	}
	
	/**
	 * Check whether the Shark should starve to death
	 * If so, the Shark call the {@link Fish#die()} method
	 */
	public void checkHunger()
	{
		if (this.hungerCounter >= Shark.MAX_HUNGER_CYCLE) {
			this.die();
		}
	}
	
	@Override
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
				
				/*
				 * If the square is occupated and if this is not 
				 * a Sardine we remove this square from the availables squares
				 */
				if (fish != null && !(fish instanceof Sardine)) {
					coords.remove(coordinate);
				}
				
			} catch(IndexOutOfBoundsException e) {
				coords.remove(coordinate);
			}
		}	
		return coords;
	}

	@Override
	public int getDeathAge()
	{
		return 30;
	}

	@Override
	public int getReproductionFrequency()
	{
		return 17;
	}

	@Override
	public Fish newChild()
	{
		return new Shark(this.sea);
	}

	@Override
	public String toString()
	{
		return "(S) "+this.coordinate;
	}

	@Override
	public String toStringShort()
	{
		return "S";
	}
}
