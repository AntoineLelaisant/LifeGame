/**
 * 
 */
package sea;

/**
 * Sardine Class.
 * 
 * 
 * 
 * @author antoine
 */
public class Sardine extends Fish
{
	/**
	 * The class constructor.
	 * Use the {@link Fish#Fish(Sea)} constructor
	 * 
	 * @param sea The {@link Sea} in which the fish live 
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
		return 10;
	}

	@Override
	public Fish newChild()
	{
		return new Sardine(this.sea);
	}

	@Override
	public String toString()
	{
		return "(x) "+this.coordinate;
	}

	@Override
	public String toStringShort()
	{
		return "x";
	}
}
