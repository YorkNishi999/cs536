package cs536;

import java.util.*;

public class SymTable {
	// field
		LinkedList<HashMap<String, Sym>> symTable = new LinkedList<HashMap<String, Sym>>();
		
	// constructor
	/* it should initialize the SymTable's List field 
	 * to contain a single, empty HashMap. 
	 */
	public SymTable() {
		HashMap<String, Sym> tmp = new HashMap<>();
		tmp.put(null, null);
		this.symTable.add(tmp);
	}
	
	// method
	/* If this SymTable's list is empty, throw an EmptySymTableException. 
	 * If either name or sym (or both) is null, throw a NullPointerException. 
	 * If the first HashMap in the list already contains the given name as a key, 
	 * throw a DuplicateSymException. 
	 * Otherwise, add the given name and sym to the first HashMap in the list. 
	 */
	public void addDecl(String name, Sym sym) throws DuplicateSymException, EmptySymTableException{
		if(this.symTable.isEmpty()) {
			throw new EmptySymTableException();
		}
		
		if(name == null || sym == null) {
			throw new NullPointerException();
		}
		
		if(this.symTable.getFirst().containsKey(name)) {
			throw new DuplicateSymException();
		}
		
		this.symTable.getFirst().put(name, sym);
	}
	
	/* 	Add a new, empty HashMap to the front of the list. 
	 * 
	 */
	public void addScope() {
		HashMap<String, Sym> tmp = new HashMap<>();
		tmp.put(null, null);
		this.symTable.addFirst(tmp);
	}
	
	/* If this SymTable's list is empty, throw an EmptySymTableException. 
	 * Otherwise, if the first HashMap in the list contains name as a key, 
	 * return the associated Sym; otherwise, return null. 
	 */
	public Sym lookupLocal(String name) throws EmptySymTableException {
		if(this.symTable.isEmpty()) {
			throw new EmptySymTableException();
		}
		
		HashMap<String, Sym> tmpFirst = new HashMap<>();
		tmpFirst = this.symTable.getFirst();
		return tmpFirst.get(name);
	}
	
	/* If this SymTable's list is empty, throw an EmptySymTableException. 
	 * If any HashMap in the list contains name as a key, 
	 * return the first associated Sym (i.e., the one from the HashMap 
	 * that is closest to the front of the list); otherwise, return null. 
	 */
	public Sym lookupGlobal(String name) throws EmptySymTableException {
		if(this.symTable.isEmpty()) {
			throw new EmptySymTableException();
		}
		
		Iterator<HashMap<String, Sym>> it = this.symTable.iterator();
		
		while(it.hasNext()) {
			HashMap<String, Sym> tmp = it.next();
			if(tmp.containsKey(name)) {
				return tmp.get(name);
			}
		}
		return null;
	}
	
	/* If this SymTable's list is empty, throw an EmptySymTableException; 
	 * otherwise, remove the HashMap from the front of the list. To clarify, 
	 * throw an exception only if before attempting to remove, 
	 * the list is empty (i.e. there are no HashMaps to remove). 
	 */
	public void removeScope() throws EmptySymTableException {
		if(this.symTable.isEmpty()) {
			throw new EmptySymTableException();
		}
		
		this.symTable.removeFirst();
	}
	
	/* This method is for debugging. First, print “\nSym Table\n”. 
	 * Then, for each HashMap M in the list, print M.toString() 
	 * followed by a newline. Finally, print one more newline. 
	 * All output should go to System.out. 
	 */
	public void print() {
		System.out.println("\nSym Table\n");
		Iterator<HashMap<String, Sym>> it = this.symTable.iterator();
		
		while(it.hasNext()) {
			System.out.println(it.next().toString());
		}
		System.out.print("");
		
	}

}
