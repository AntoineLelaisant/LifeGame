/**
 * 
 */
package sea;

/**
 * @author antoine
 *
 */
public class Shark extends Fish
{

	/**
	 * 
	 */
	public Shark(Sea sea)
	{
		super(sea);
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
