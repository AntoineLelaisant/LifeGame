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
	/**
	 * The age of the fish (in cycle)
	 */
	protected int age;
	
	/**
	 * The sea in which the fish is living
	 */
	protected Sea sea;
	

	/**
	 * 
	 * @param sea The {@link Sea} in which the fish live
	 */
	public Fish(Sea sea)
	{
		this.sea = sea;
		this.age = 0;
	}
	
	/**
	 * Moves to an adjacent square 
	 */
	public abstract void move();
	
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
		
		return ret;
	}
}