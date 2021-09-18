package cs536;
/**
 * SymTable is a LinkedList whose elements are HashMap. 
 * It stores the type of the variable with the name of the variable as a key.
 * Initialize the SymTable's List field to contain a single, empty HashMap. 
 * 
 * @author yohei nishimura
 *
 */
import java.util.*;

public class SymTable 
{
	LinkedList<HashMap<String, Sym>> symTable = 
			new LinkedList<HashMap<String, Sym>>();
		
	public SymTable() 
	{
		HashMap<String, Sym> tmp = new HashMap<>();
		tmp.put(null, null);
		this.symTable.add(tmp);
	}
	
	/** 
	 * Adds the given name and sym to the first HashMap in the list. 
	 * @param the name of variable
	 * @param the name of type
	 * @throws EmptySymTableException if this SymTable's list is empty
	 * @throws NullPointerException if either name or sym (or both) is null
	 * @throws DuplicateSymException if the first HashMap in the list 
	 * 		already contains the given name as a key
	 */
	public void addDecl(String name, Sym sym) 
			throws DuplicateSymException, EmptySymTableException
	{
		if(this.symTable.isEmpty()) 
		{
			throw new EmptySymTableException();
		}
		if(name == null || sym == null) 
		{
			throw new NullPointerException();
		}
		if(this.symTable.getFirst().containsKey(name)) 
		{
			throw new DuplicateSymException();
		}
		this.symTable.getFirst().put(name, sym);
	}
	
	/** 
	 * 	Adds a new, empty HashMap to the front of the list. 
	 */
	public void addScope() 
	{
		HashMap<String, Sym> tmp = new HashMap<>();
		tmp.put(null, null);
		this.symTable.addFirst(tmp);
	}
	
	/** 
	 * Looks for the argument in the first HashMap in the list. 
	 * @param the name of variable
	 * @returns the associated Sym if the first HashMap in the list 
	 *  contains name as a key. Otherwise, null.
	 * @throws EmptySymTableException if this SymTable's list is empty
	 */
	public Sym lookupLocal(String name) throws EmptySymTableException 
	{
		if(this.symTable.isEmpty()) 
		{
			throw new EmptySymTableException();
		}
		HashMap<String, Sym> tmpFirst = new HashMap<>();
		tmpFirst = this.symTable.getFirst();
		return tmpFirst.get(name);
	}
	
	/** 
	 * Looks for the first argument in the all of HashMap in the list. 
	 * @param the name of variable
	 * @returns the first associated Sym if any HashMap in the list 
	 *   contains name as a key. Otherwise, null.
	 * @throws EmptySymTableException if this SymTable's list is empty
	 */
	public Sym lookupGlobal(String name) throws EmptySymTableException 
	{
		if(this.symTable.isEmpty()) 
		{
			throw new EmptySymTableException();
		}
		Iterator<HashMap<String, Sym>> it = this.symTable.iterator();
		while(it.hasNext()) 
		{
			HashMap<String, Sym> tmp = it.next();
			if(tmp.containsKey(name)) 
			{
				return tmp.get(name);
			}
		}
		return null;
	}
	
	/** 
	 * Removes the HashMap from the front of the list.
	 * @param the name of variable
	 * @throws EmptySymTableException if this SymTable's list is empty.
	 *   Throw an exception only if before attempting to remove, 
	 *   the list is empty.
	 */	
	public void removeScope() throws EmptySymTableException 
	{
		if(this.symTable.isEmpty()) 
		{
			throw new EmptySymTableException();
		}
		this.symTable.removeFirst();
	}
	
	/** 
	 * Prints SymTable for debugging.
	 */	
	public void print() {
		System.out.println("\nSym Table\n");
		Iterator<HashMap<String, Sym>> it = this.symTable.iterator();
		while(it.hasNext()) {
			System.out.println(it.next().toString());
		}
		System.out.println("");
	}
}
