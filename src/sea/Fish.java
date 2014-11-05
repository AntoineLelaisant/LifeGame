/**
 * 
 */
package sea;

/**
 * @author antoine
 *
 */
public abstract class Fish
{
	protected static int deathAge;
	protected static int reproductionFrequency;
	
	protected int age;
	

	/**
	 * 
	 */
	public Fish()
	{
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Moves to an adjacent square 
	 */
	public abstract void move();

}
