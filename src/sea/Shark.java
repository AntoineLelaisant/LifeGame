/**
 * 
 */
package sea;

import java.util.LinkedList;

/**
 * @author antoine
 *
 */
public class Shark extends Fish
{
	public static final int AGE_SEMI_ADULT = 5;
	public static final int AGE_ADULT = 10;
	
	// Implementation of pattern state
	public final SharkStateChild stateChild = new SharkStateChild(this);
	public final SharkStateSemiAdult stateSemiAdult = new SharkStateSemiAdult(this);
	public final SharkStateAdult stateAdult = new SharkStateAdult(this);
	
	private SharkState state;
	
	/**
	 * 
	 */
	public Shark(Sea sea)
	{
		super(sea);
		this.state = this.stateChild;
	}
	
	@Override
	public void setCoordinate(Coordinate coord)
	{
		Coordinate initial = this.coordinate;
		
		/*
		 * If the target square is a Sardine
		 * it dies 
		 */
		if (this.sea.getSquare(coord) instanceof Sardine) {
			this.sea.getSquare(coord).die();
		}
		
		this.coordinate = coord;
		if (initial != null) {
			this.setChanged();
		}
		this.notifyObservers(new FishEvent(this, initial, FishEvent.EVENT_MOVED));
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
	
	@Override
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
		return 70;
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

}
