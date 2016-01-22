import java.util.*;
import java.io.*;

/**
 * This is the main class which takes the input and does a case passed on choice. It writes and inputs a file
 *
 *
 * @author Kyle Sadler
 */
public class RobotLogMain {
	/**
	 * This is the main method. It takes the input and does stuff with it. Can read from a file.
	 * Save to a file, remove from list, and shift positions
	 *
	 * @param args[]  allows for arguments
	 */
    public static void main(String args[]) throws FileNotFoundException, InvalidListPositionException {
        
        LinkedList<String> robotLog = new LinkedList<String>();
        
        Scanner stdin = new Scanner(System.in);  // for reading console input
        boolean done = false;
        while (!done) {
            System.out.print("Enter option - l, p, r, s, t, u, or x: ");
            String input = stdin.nextLine();
            
            if (input.length() > 0) {
                char choice = input.charAt(0);  // strip off option character
                String remainder = "";  // used to hold the remainder of input
                // trim off any leading or trailing spaces
                remainder = input.substring(1).trim();
                
                switch (choice) {
                
                case 'l' :
                    if (remainder.length() == 0) {
                        System.out.println("Command 'l': Takes one " +
                                "argument, the name of a file. Loads the " +
                                "RobotLog from that file.");
                        break;
                    }
                    
                   
                    try{
                    	robotLog = new LinkedList<String>(); //resets the list
                    	File srcFile = new File(remainder);  //creates object to get                  
                    	Scanner fileIn = new Scanner(srcFile);
                    	//goes through the file and saves it to the list
                    	while(fileIn.hasNextLine()){
                    	robotLog.add(fileIn.nextLine());
                    	}
                    fileIn.close();
                    }catch(FileNotFoundException e){
                    	System.out.println("Cannot find specific file");
                    }
                    break;
                    
                case 'p' :
                    if (remainder.length() > 0) {
                        System.out.println("Command 'p': Takes no " +
                                "arguments. Prints RobotLog.");
                        break;
                    }
                    
                    int pos = 1;  //creates the number to put at the beginning of the line
                    Iterator<String> print = robotLog.iterator();
                    print.next(); //gets rid of the null line created by iterator
                    if(!print.hasNext()){
                    	System.out.println("Empty");
                    }
                    while(print.hasNext()){
                    	System.out.println(pos + "." + " " + print.next());
                    	pos++;
                    }
                    
                    break;
                    
                case 'r' :
                    if (remainder.length() == 0) {
                        System.out.println("Command 'r': Takes one " +
                                "argument, an int. Removes the line at " +
                                "that position in the log.");
                        break;
                    }
                    
                    try{
                    	int k = Integer.parseInt(remainder); // changes the type from string to int
                    	if(k < 0 || k > robotLog.size()){
                        	throw new InvalidListPositionException();
                        }
                        	robotLog.remove(k);
                        	System.out.println("successfully removed line " + k);
                        break;
                    }
                    catch(NumberFormatException e){
                    	System.out.println("Please enter a valid integer");
                    }
                    catch(InvalidListPositionException n){
                    	System.out.println("invalide line number");
                    }
              
                    break;
                    
                case 's' :
                    if (remainder.length() == 0) {
                        System.out.println("Command 's': Takes one " +
                                "argument, the name of a file. Saves the " +
                                "current RobotLog to that file.");
                        break;
                    }
                    
                    
                    File dstFile = new File(remainder);
                    PrintStream outFile = new PrintStream(dstFile);
                    //makes sure there is something in the list before it starts
                    if(robotLog.isEmpty()){
                    	System.out.println("Cannot write to file, robot log is empty");
                    }
                    Iterator<String> itr = robotLog.iterator();
                    itr.next();  //skips the first thing because of a null position in iterator
                    while(itr.hasNext()){
                    	outFile.println(itr.next());
                    }
                    outFile.close();
                    System.out.println("Successfully saved ");
                    
                    break;
                    
                case 't' :
                    if (remainder.length() == 0) {
                        System.out.println("Command 't': Takes two " +
                                "arguments, both ints, i.e., 't 12 3'." +
                                "Takes the line at the first # and copies it " +
                                "to the position at the second #.");
                        break;
                    }
                    
                    // creates to seperate things for the input
                    String[] split = remainder.split(" ");
                    String first = split[0];
                    String second = split[1];
                    try {
                    //changes the two strings to ints
                    int pos1 = Integer.parseInt(first);
                    int pos2 = Integer.parseInt(second);
                    robotLog.add(pos2, robotLog.get(pos1));
                    System.out.println("Sucessfully copied");
                    } catch (NumberFormatException e) {
                        System.out.println("Please enter a valid integer.");
                    } catch (InvalidListPositionException n) {
                    	System.out.println("Invalid line number.");
                    }
                    break;
                    
                case 'u' :
                    if (remainder.length() == 0) {
                        System.out.println("Command 'u': Takes one " +
                                "argument, an int. Takes the line at " +
                                "that position in the log and copies it " +
                                "at the end of the log.");
                        break;
                    }

                    try{
                    	//converts to int from string
                    	int j = Integer.parseInt(remainder);
                    	//makes sure the value is valid first
                    	if(j < 0 || j > robotLog.size()){
                    		throw new InvalidListPositionException();
                    	}
                    	robotLog.add(robotLog.get(j));
                    	System.out.println("Successfully copied");
                    }
                    catch(NumberFormatException e){
                    	System.out.println("Please enter a valid integer");
                    }
                    catch(InvalidListPositionException n){
                    	System.out.println("invalid line number");
                    }
                    break;
                    
                case 'x' :
                    System.out.println("exit");
                    done = true;
                    break;
                    
                default :
                    System.out.println("Unknown Command");
                    break;
                }
            }
        }
    }
}