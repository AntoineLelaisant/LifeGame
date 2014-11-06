package sea;

public class SharkStateChild extends SharkState
{
	
	public SharkStateChild(Shark shark)
	{
		super(shark);
	}

	@Override
	public void move()
	{	
		Coordinate target = Coordinate.getRandomCoord( this.shark.getAvailablePlaces());
		
		/*
		 * If target is null it means
		 * that the fish have no available place
		 * to go. So we do nothing
		 */
		if (target != null) {
			// this.sea.moveFish(this, target);
			this.shark.setCoordinate(target);	
		}
		
		/*
		 * At the end of the cycle
		 * the fish grow up
		 */
		this.shark.age++;
		
	}

}
