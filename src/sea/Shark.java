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
	public final SharkStateChild stateChild = new SharkStateChild(this);
	
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
	
	@Override
	public void move()
	{
		this.state.move();
	}
	
	@Override
	public LinkedList<Coordinate> getAvailablePlaces()
	{
		LinkedList<Coordinate> coords = Coordinate.getNeighbours(this.coordinate);
		
		@SuppressWarnings("unchecked")
		LinkedList<Coordinate> coordsCloned = (LinkedList<Coordinate>) coords.clone();
		
		for (Coordinate coordinate : coordsCloned) {
			try{
				Fish fish = this.sea.getSquare(coordinate);
				
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
		return 5;
	}

}
