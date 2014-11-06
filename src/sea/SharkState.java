package sea;

public abstract class SharkState
{
	protected Shark shark;
	
	/**
	 * 
	 * @param shark
	 */
	public SharkState(Shark shark)
	{
		this.shark = shark;
	}
	
	/**
	 * 
	 */
	public abstract void move();

}
