/* CRITTERS Main.java
 * EE422C Project 4 submission by
 * Replace <...> with your actual data.
 * <Student1 Name>
 * <Student1 EID>
 * <Student1 5-digit Unique No.>
 * <Student2 Name>
 * <Student2 EID>
 * <Student2 5-digit Unique No.>
 * Slip days used: <0>
 * Fall 2016
 */
package assignment4; // cannot be in default package
import java.util.List;
import java.util.Scanner;

import com.sun.org.apache.xpath.internal.operations.Equals;

import java.io.*;
import java.lang.reflect.InvocationTargetException;


/*
 * Usage: java <pkgname>.Main <input file> test
 * input file is optional.  If input file is specified, the word 'test' is optional.
 * May not use 'test' argument without specifying input file.
 */
public class Main {

    static Scanner kb;	// scanner connected to keyboard input, or input file
    private static String inputFile;	// input file, used instead of keyboard input if specified
    static ByteArrayOutputStream testOutputString;	// if test specified, holds all console output
    private static String myPackage;	// package of Critter file.  Critter cannot be in default pkg.
    private static boolean DEBUG = false; // Use it or not, as you wish!
    static PrintStream old = System.out;	// if you want to restore output to console


    // Gets the package name.  The usage assumes that Critter and its subclasses are all in the same package.
    static {
        myPackage = Critter.class.getPackage().toString().split(" ")[1];
    }

    /**
     * Main method.
     * @param args args can be empty.  If not empty, provide two parameters -- the first is a file name, 
     * and the second is test (for test output, where all output to be directed to a String), or nothing.
     */
    public static void main(String[] args) { 
        if (args.length != 0) {
            try {
                inputFile = args[0];
                kb = new Scanner(new File(inputFile));			
            } catch (FileNotFoundException e) {
                System.out.println("USAGE: java Main OR java Main <input file> <test output>");
                e.printStackTrace();
            } catch (NullPointerException e) {
                System.out.println("USAGE: java Main OR java Main <input file>  <test output>");
            }
            if (args.length >= 2) {
                if (args[1].equals("test")) { // if the word "test" is the second argument to java
                    // Create a stream to hold the output
                    testOutputString = new ByteArrayOutputStream();
                    PrintStream ps = new PrintStream(testOutputString);
                    // Save the old System.out.
                    old = System.out;
                    // Tell Java to use the special stream; all console output will be redirected here from now
                    System.setOut(ps);
                }
            }
        } else { // if no arguments to main
            kb = new Scanner(System.in); // use keyboard and console
        }

        /* Do not alter the code above for your submission. */
        /* Write your code below. */
        while(true){
        System.out.println("GLHF");
    
        String[] parts;
     //   Critter.displayWorld();
        
        int numCrits = 0;
        
        String input = kb.nextLine();
        //split input String
        if(input.contains(" ")){
	        parts = input.split(" ");
	      //  input1 = parts[0];
	     //   input2 = parts[1];
        }
        else{
        	parts = null;
        }
        //quit
        if(parts[0].equals("quit") && parts.length == 1){
        	System.exit(1);
        }
        //make<Critter><count>
        else if(parts[0].equals("make")){
        	if(parts.length == 3){
        		numCrits = Integer.parseInt(parts[2]);
        	}
        	else if(parts.length == 2 && parts[1] != null){
        		numCrits = 1;
        	}
        	for(int i = 0;i<numCrits;i++){
	            try{Critter.makeCritter(parts[1]);}
	            catch(InvalidCritterException a){
	            	System.out.println("error processing: " + parts);
	            }
        	}
        }
        //show
        else if(parts[0].equals("show") && parts.length == 1){
        	Critter.displayWorld();
        }
        //step <count>
        else if(parts[0].equals("step")){
        	if(parts.length == 2 && parts[1] != null){
        		int x = Integer.parseInt(parts[1]);
        		for(int i = 0;i<x;i++){
        			Critter.worldTimeStep();
        		}
        	}
        	else if(parts.length == 1){
        		Critter.worldTimeStep();
        	}
        }
        //seed <number>
        else if(parts[0].equals("seed")){
        	if(parts.length == 2 && parts[1] != null){
        		int x = Integer.parseInt(parts[1]);
        		Critter.setSeed(x);
        	}
        	else{
        		System.out.println("Illegal input");
        	}
        }
        else if(parts[0].equals("stats")){
        	if(parts.length == 2){
        		try {
        			Class<?> crit = Class.forName(myPackage + "." + parts[1]);
        			Class<?>[] types = {List.class};
					List<Critter> list = Critter.getInstances(parts[1]);
					crit.getMethod("runStats", types).invoke(null, list);
				} catch (InvalidCritterException e) {
					System.out.println("error processing: " + input);
				}
        		catch (ClassNotFoundException e1){
        			System.out.println("error processing: " + input);
        		} catch (IllegalAccessException e) {
        			System.out.println(e);
				} catch (IllegalArgumentException e) {
					System.out.println(e);
				} catch (InvocationTargetException e) {
					System.out.println(e);
				} catch (NoSuchMethodException e) {
					System.out.println(e);
				} catch (SecurityException e) {
					System.out.println(e);
				}
        		
        	}
        }
        else {
        	System.out.println("error processing: " + parts);
        }
        
        //Critter.displayWorld();
       
        /* Write your code above */
    }

    }
}
