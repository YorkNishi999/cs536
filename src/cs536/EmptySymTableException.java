package cs536;
/**
 * EmptySymTableException is an Exception 
 * when the names of variables overlap in a HashMap.
 * 
 * @author yohei nishimura
 *
 */
public class EmptySymTableException extends Exception {

	public EmptySymTableException() {
		System.out.println("Threw EmptySymTableException.");
	}
	
}
