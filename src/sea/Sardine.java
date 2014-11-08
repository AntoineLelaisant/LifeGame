/**
 * 
 */
package sea;

/**
 * @author antoine
 *
 */
public class Sardine extends Fish
{
	/**
	 * 
	 */
	public Sardine(Sea sea)
	{
		super(sea);
	}

	@Override
	public int getDeathAge()
	{
		return 15;
	}

	@Override
	public int getReproductionFrequency()
	{
		return 5;
	}

	@Override
	public Fish newChild()
	{
		return new Sardine(this.sea);
	}

	/**
	 * 
	 */
	public String toString()
	{
		return "x";
	}
}
