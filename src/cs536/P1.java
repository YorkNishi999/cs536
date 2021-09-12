package cs536;

import java.util.*;

public class P1 {
	
	public static void main(String[] args) throws DuplicateSymException, EmptySymTableException {

		// the test case table -> https://docs.google.com/spreadsheets/d/15gmQfPQ_IMWZD0iugLFALYMXRCFpBMjWTGcjoSNzwSU/edit#gid=0

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
	
		// check correct creation of constructor: 15
		// constructor test: table is null: 16
		System.out.print("1st ");
		symT0.print();
		System.out.println("expect a single list with HashMap by 'null'.");
		
		symT0.removeScope(); // success: 12
		try {
			symT0.addDecl(name00, sym00); // EmptySymTableException occur: 2
		} catch (EmptySymTableException e) {
			System.out.println("EmptySymTableException occurs: case num 2");
		}
		try {
			symT0.lookupLocal("x"); // EmptySymTableException occur: 7
		} catch (EmptySymTableException e) {
			System.out.println("EmptySymTableException occurs: case num 7");
		}
		try {
			symT0.lookupGlobal("x");// EmptySymTableException occur: 10
		} catch (EmptySymTableException e) {
			System.out.println("EmptySymTableException occurs: case num 10");
		}
		try {
			symT0.removeScope();// EmptySymTableException occur: 13
		} catch (EmptySymTableException e) {
			System.out.println("EmptySymTableException occurs: case num 13");
		}
		
		// check null arguments
		SymTable symT = new SymTable();
		try {
			symT.addDecl(null, null); // NullPointerException occur: 3
		} catch (NullPointerException e) {
			System.out.println("NullPointerException occurs: case num 3-1");
		}
		try {
			symT.addDecl(name00, null); // NullPointerException occur: 3
		} catch (NullPointerException e) {
			System.out.println("NullPointerException occurs: case num 3-2");
		}
		try {
			symT.addDecl(null, sym00); // NullPointerException occur: 3
		} catch (NullPointerException e) {
			System.out.println("NullPointerException occurs: case num 3-3");
		}

		// put hashmap
		symT.addDecl(name00, sym00); // success: 1
		symT.addDecl(name01, sym01); // success: 1
		symT.addDecl(name02, sym02); // success: 1
		System.out.print("2nd ");
		symT.print();
		System.out.println("expect a single list with HashMap by null, x: int, y: int, and dog:String.");
		System.out.println("Checked case 1.");
		
		try {
			symT.addDecl(name00e, sym00e); // DuplicateSymException occur: 4
		} catch (DuplicateSymException e) {
			System.out.println("DuplicateSymException occurs: case num 4");
		}
		
		symT.lookupLocal("x"); // success: 6
		symT.lookupLocal("z"); // success: 8
		symT.lookupLocal("dog"); // success: 6
		
		symT.addScope(); // success: 4
		symT.addDecl(name10, sym10); // success: 1
		symT.addDecl(name11, sym11); // success: 1
		System.out.print("3rd ");
		symT.print();
		System.out.println("expect a 2-indexed list with HashMap by [0]: null, x:long int, z:short int, and [1]: null, x: int, y: int, dog:String.");
		System.out.println("Checked case 1, 4, 6, 8.");
		
		System.out.println("locallookup for z is " + symT.lookupLocal("z") + ", expecting 'short int'"); // success: 6
		System.out.println("globallookup for x is " + symT.lookupGlobal("x") + ", expecting 'long int'"); // success: 9 expect "long int"
		System.out.println("globallookup for z is " + symT.lookupGlobal("z") + ", expecting 'short int'"); // success: 9 expect "short int"
		System.out.println("globallookup for dog is " + symT.lookupGlobal("dog") + ", expecting 'String'"); // success: 9 expect "String"
		System.out.println("globallookup for NULL is " + symT.lookupGlobal("NULL") + ", expecting 'null'"); // success: 11
		System.out.println("Checked case 6, 9, 11.");
		System.out.println("");
		
		
		symT.removeScope(); // success: 14
		System.out.print("4th ");
		symT.print();
		System.out.println("expect a single list with HashMap by null, x: int, y: int, dog:String.");
		System.out.println("Checked case 14.");
		
		symT.removeScope(); // success: 14
		System.out.print("5th ");
		symT.print();
		System.out.println("expect table is null.");
		System.out.println("Checked case 14.");
		
		symT.addScope(); // success: 5
		System.out.print("6th ");
		symT.print();
		System.out.println("expect a single list with HashMap by 'null'.");
		System.out.println("Checked case 5.");
	}

}
