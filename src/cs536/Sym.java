package cs536;

public class Sym {
	// field
	String type;
	
	// constructor
	/*  it should initialize the Sym to have the given type. 
	 */
	public Sym(String type) {
		this.type = type;
	}
	
	// method
	// Return this Sym's type. 
	public String getSym() {
		String res;
		res = this.type;
		return res;
	}
	
	/*Return this Sym's type. (This method will be changed 
	 * later in a future project when more information is stored in a Sym.) 
	 */
	public String toString() {
		String res;
		res = this.type;	// will be changed in the future.
		return res;
	}
	
} // end of Sym class