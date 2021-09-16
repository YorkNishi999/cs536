////////////////////////////////////////////////////////////////////////////////
//
// Title: The test cases for SymTable (P1.java)
// Files: P1.java, Sym.java, SymTable.java, DuplicateSymException.java, \
	// + EmptySymTableException.java
// Semester: CS536 Intro to PLs and compilers
// Author: Yohei Nishimura
// Email: ynishimura@wisc.edu
// CS Login: yohei_nishimura111
// Lecturer's Name: Aws Albarghouthi
//
//////////////////////////////////80 letters////////////////////////////////////

/**
 * This class was written to test Sym.java, SymTable.java, 
 * DuplicateSymException.java, EmptySymTableException.java
 * 
 * This class is written for cases where the above four classes work well and cases 
 * where they cause errors. 
 * See "P1,P2TestCasesTable" in this link (https://bit.ly/3ntmqvT) for the complete 
 * test case. 
 * Roughly, the following tests are implemented.
 * 1) whether Sym is generated correctly
 * 2) whether SymTable is generated correctly
 * 3) whether LinkedList elements can be added or removed correctly, 
 * 	and whether errors are generated correctly
 * 4) whether variable names and types are stored correctly, 
 * 	and whether errors are generated correctly
 * 5) whether Global, Local variables can be retrieved correctly
 * 
 * @param Sym in Sym.java is a class for representing the type of a variable
 * @param SymTable in SymTable.java is a LinkedList that stores variables 
 *  and types in the form of a HashTable for each scope.
 * @param HashTable<String, Sym> represents the name and type of the variable.
 * @throws DuplicateSymException is an error that occurs 
 * 	when the same variable name is assigned to SymTable.
 * @throws EmptySymTableException is an error that occurs when SymTable is null.
 * @throws NullPointerException is an error that occurs when either of the two 
 * 	arguments of the HashMap is null.
 * For details on each method, please refer to the documentation of each class.
 */

package cs536;

public class P1 
{
	
	public static void main(String[] args) throws DuplicateSymException,
		EmptySymTableException 
	{
		String name00 = "x";
		Sym sym00 = new Sym("int");
		String name00e = "x";
		Sym sym00e = new Sym("String");
		String name01 = "y";
		Sym sym01 = new Sym("int");
		String name02 = "dog";
		Sym sym02 = new Sym("String");
		String name10 = "x";
		Sym sym10 = new Sym("long int");
		String name11 = "z";
		Sym sym11 = new Sym("short int");
		
		// check Sym object
		System.out.println("Expect 'int': " + sym00.getSym());
		System.out.println("Expect 'int': " + sym01.getSym());
		System.out.println("Expect 'String': " + sym02.getSym());
		
		// check constructor
		SymTable symT0 = new SymTable();
	
		// check correct creation of constructor: 15 in the case table
		// constructor test: table is null: 16 in the case table
		System.out.print("1st ");
		symT0.print();
		System.out.println("expect a single list with HashMap by 'null'.");
		
		symT0.removeScope(); // success: 12 in the case table

		// check add, lookup, remove error 
		try 
		{
			symT0.addDecl(name00, sym00); 
			// EmptySymTableException occur: 2 in the case table
		} catch (EmptySymTableException e) {
			System.out.println("EmptySymTableException occurs: case num 2");
		}
		try 
		{
			symT0.lookupLocal("x"); 
			// EmptySymTableException occur: 7 in the case table
		} catch (EmptySymTableException e) {
			System.out.println("EmptySymTableException occurs: case num 7");
		}
		try 
		{
			symT0.lookupGlobal("x");
			// EmptySymTableException occur: 10 in the case table
		} catch (EmptySymTableException e) {
			System.out.println("EmptySymTableException occurs: case num 10");
		}
		try 
		{
			symT0.removeScope();
			// EmptySymTableException occur: 13 in the case table
		} catch (EmptySymTableException e) {
			System.out.println("EmptySymTableException occurs: case num 13");
		}
		
		// check null arguments error
		SymTable symT = new SymTable();
		try 
		{
			symT.addDecl(null, null); 
			// NullPointerException occur: 3 in the case table
		} catch (NullPointerException e) {
			System.out.println("NullPointerException occurs: case num 3-1");
		}
		try 
		{
			symT.addDecl(name00, null); 
			// NullPointerException occur: 3 in the case table
		} catch (NullPointerException e) {
			System.out.println("NullPointerException occurs: case num 3-2");
		}
		try 
		{
			symT.addDecl(null, sym00); 
			// NullPointerException occur: 3 in the case table
		} catch (NullPointerException e) {
			System.out.println("NullPointerException occurs: case num 3-3");
		}

		// put hashmap
		symT.addDecl(name00, sym00); // success: 1 in the case table
		symT.addDecl(name01, sym01); // success: 1 in the case table
		symT.addDecl(name02, sym02); // success: 1 in the case table
		System.out.print("2nd ");
		symT.print();
		System.out.println("expect a single list with HashMap by null, x: int, "
			+ "y: int, and dog:String.");
		System.out.println("Checked case 1.");
		
		// check duplicate error cases
		try 
		{
			symT.addDecl(name00e, sym00e); // DuplicateSymException occur: 4
		} catch (DuplicateSymException e) {
			System.out.println("DuplicateSymException occurs: case num 4");
		}
		
		// check success cases
		symT.lookupLocal("x"); // success: 6 in the case table
		symT.lookupLocal("z"); // success: 8 in the case table
		symT.lookupLocal("dog"); // success: 6 in the case table 
		
		symT.addScope(); // success: 4 in the case table
		symT.addDecl(name10, sym10); // success: 1 in the case table
		symT.addDecl(name11, sym11); // success: 1 in the case table
		System.out.print("3rd ");
		symT.print();
		System.out.println("expect a 2-indexed list with HashMap by [0]: null,"
			+ "x:long int, z:short int, and [1]: null, x: int, y: int, dog:String.");
		System.out.println("Checked case 1, 4, 6, 8.");
		
		System.out.println("locallookup for z is " + symT.lookupLocal("z") + 
			", expecting 'short int'"); // success: 6 in the case table
		System.out.println("globallookup for x is " + symT.lookupGlobal("x") +
			", expecting 'long int'"); // success: expect "long int" : 9 in the case table
		System.out.println("globallookup for z is " + symT.lookupGlobal("z") + 
			", expecting 'short int'"); // success: expect "short int" : 9 in the case table
		System.out.println("globallookup for dog is " + symT.lookupGlobal("dog") 
			+ ", expecting 'String'"); // success: expect "String": 9 in the case table
		System.out.println("globallookup for NULL is " + symT.lookupGlobal("NULL") 
			+ ", expecting 'null'"); // success: 11 in the case table
		System.out.println("Checked case 6, 9, 11.");
		System.out.println("");
		
		
		symT.removeScope(); // success: 14 in the case table
		System.out.print("4th ");
		symT.print();
		System.out.println("expect a single list with HashMap by null, x: int, " + 
			"y: int, dog:String.");
		System.out.println("Checked case 14.");
		
		symT.removeScope(); // success: 14 in the case table
		System.out.print("5th ");
		symT.print();
		System.out.println("expect table is null.");
		System.out.println("Checked case 14.");
		
		symT.addScope(); // success: 5 in the case table
		System.out.print("6th ");
		symT.print();
		System.out.println("expect a single list with HashMap by 'null'.");
		System.out.println("Checked case 5.");
	} // end of main

} // end of class
