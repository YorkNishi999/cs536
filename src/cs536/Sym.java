package cs536;
/**
 * Sym is a class that stores the type of a variable.
 * It takes a type as an argument to create an instance.
 * 
 * @author yohei nishimura
 *
 */
public class Sym 
{
	String type;
	
	public Sym(String type) 
	{
		this.type = type;
	}
	
	/** 
	 * Returns this Sym's type.
	 * @return type.
	 */
	public String getType() 
	{
		String res;
		res = this.type;
		return res;
	}

	/**
	 * Returns this Sym's type. (This method will be changed) 
	 * @return type.
	 */

	public String toString() 
	{
		String res;
		res = this.type;	// will be changed in the future.
		return res;
	}	
}