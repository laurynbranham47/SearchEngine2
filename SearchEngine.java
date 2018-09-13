//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           Search Engine
// Files:           None
// Course:          CS 300 Spring 2018
//
// Author:          Lauryn Branham
// Email:           lbranham@wisc.edu
// Lecturer's Name: Gary Dahl
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    ---
// Partner Email:   ---
// Lecturer's Name: ---
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   ___ Write-up states that pair programming is allowed for this assignment.
//   ___ We have both read and understand the course Pair Programming Policy.
//   ___ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully 
// acknowledge and credit those sources of help here.  Instructors and TAs do 
// not need to be credited here, but tutors, friends, relatives, room mates 
// strangers, etc do.  If you received no outside help from either type of 
// source, then please explicitly indicate NONE.
//
// Persons:         NONE
// Online Sources:  NONE
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

/**
 * This class's purpose is to use a Binary Search Tree to organize
 * this search engine.
 * 
 * @author lauryn
 */

import java.util.ArrayList;
import java.util.Scanner;

public class SearchEngine {
	private WebPageNode root; // root of the BST-based search engine
    
	/**
	 * This constructor creates an empty search engine.
	 */
    public SearchEngine() {
    	root = null;
	} 
	
    /**
     * This method returns whether the BST is empty or not.
     * @return
     */
	public boolean isEmpty() {
		return root == null; // returns true if the search engine is empty; false otherwise 
	}
		
    /**
     * This method inserts a new WebPageNode into the correct position
     * within the BST.
     * 
     * @param id
     * @param webLink
     */
    public void insert(String id, String webLink) {
    	if (isEmpty()) { //if empty, make the root the current node
			root = new WebPageNode(id, webLink);
    	}
		else { //if not empty, figure out where to put it
			insertHelper(new WebPageNode(id, webLink), root);
		}
    	
	} 
    
    private void insertHelper(WebPageNode newNode, WebPageNode currentRootNode) {
    	
    	//if the value of the newNode is less than the currentNode
    	if( newNode.getId().compareTo( currentRootNode.getId() ) < 0 ) {
        	//if null, insert the newNode into the left child
			if (currentRootNode.getLeftChild() == null) {
				currentRootNode.setLeftChild(newNode);
			} //if not null, check the next left child
			else { 
				insertHelper( newNode, currentRootNode.getLeftChild() );
			}
		} //if the value of the newNode is more than the currentNode
    	else if( newNode.getId().compareTo( currentRootNode.getId()) > 0 ) {
    		//if null, insert the newNode into the right child
			if (currentRootNode.getRightChild() == null) {
				currentRootNode.setRightChild(newNode);
			} //if not null, check the next right child
			else {
				insertHelper( newNode, currentRootNode.getRightChild() );
			}
		}
		else { //if not found, throw a duplicate item warning 
			throw new DuplicateItemException("WARNING! Duplicate entry " 
					+ newNode.toString() + " already present."); 
		}
    }
 
    /**
     * This method's purpose is to use the given ID and search through the BST
     * for a match. If a match is found, it returns the associated webLink. If not
     * found, then it let's the user know that their is no match.
     * 
     * @param id
     * @return webLink associated with the ID
     */
    public String searchWebPage(String id) {
    	String webLink;
    	webLink = searchHelper(id, root);
    	//if no webLink found, tell user their is no match
    	if(webLink == null) {
    		webLink = "No web link found for the web page " + id;
    	} //otherwise return the link
		return webLink;
    }
    
    /**
     * This method's purpose is to act as a helper method to the searchWebPage
     * method. This does the actual work of searching through the BST to find
     * the ID and webLink.
     * 
     * @param id
     * @param currentNode
     * @return webLink associated with the ID
     */
    private String searchHelper(String id, WebPageNode currentNode) {
    	if(currentNode == null) { //if a leaf is reached or binary search tree empty
			return null;
    	} //if the id given equals the currentNode's id
		if(id.equals(currentNode.getId())) {
			return currentNode.getWebLink();
		} //if the value of the ID is less than the currentNode given, checks left child
		if(id.compareTo(currentNode.getId()) < 0) {
			return searchHelper(id, currentNode.getLeftChild());
		} //if the value of the ID is more than the currentNode given, checks right child
		return searchHelper(id, currentNode.getRightChild());
    }
 
    /**
     * This method's purpose is to return the number of pages in the search engine
     * or if it's empty.
     * 
     * @return the number of pages in the search engine
     */
    public int getWebPageCount() {
    	if(root == null) { //if the root is null, return size of 0
    		return 0;
    	} //call helper method
    	return countHelper(root);
    }
    
    /**
     * This method's purpose is to act as a helper methid to the getWebPageCount
     * method. It does the actual work of counting the number of pages with
     * the engine. 
     * 
     * @param currentNode
     * @return the number of pages in the search engine
     */
    private int countHelper(WebPageNode currentNode) {
    	int count = 1; //each node has a count of 1
    	
    	if(currentNode.getLeftChild() != null) {
    		//add each node found to the current count
    		count += countHelper(currentNode.getLeftChild());
    	}
    	if(currentNode.getRightChild() != null) {
    		//add each node found to the current count
    		count += countHelper(currentNode.getRightChild());
    	}

    	return count;
    }

    /**
     * This method's purpose is to gather all the webpage's IDs
     * into an arraylist.
     * 
     * @return ArrayList<String> of IDs
     */
    public ArrayList<String> getAllWebPages(){
    	ArrayList<String> allPages = new ArrayList<String>();
    	if(root == null) { //if no root, then the engine if empty
    		System.out.println("Search Engine is empty");
    		return null;
    	} //call helper method
    	getAllHelper(root, allPages);
    	
		return allPages;   	
    }
    
    /**
     * This method's purpose is to act as a helper method to getAllWebPages.
     * It does the actual work of gathering all of the IDs and putting
     * them into the ArrayList provided.
     * 
     * @param currentNode
     * @param pages
     */
    private void getAllHelper(WebPageNode currentNode, ArrayList<String> pages){
    	
    	//if the left child isn't null, do a recursive call
    	if(currentNode.getLeftChild() != null) {	
    		getAllHelper(currentNode.getLeftChild(), pages);
    	}
    	
    	//add the current node to the arraylist
    	pages.add(currentNode.getId());
    	
    	//if the right child isn't null, do a recursive call
    	if(currentNode.getRightChild() != null) {
    		getAllHelper(currentNode.getRightChild(), pages);
    	}

    }
    
    /**
     * The main method of this program asks the user for input
     * and provides data based on that input. 
     * @param args
     */
    public static void main(String[] args) {
    	SearchEngine engine = new SearchEngine();
    	Scanner scnr = new Scanner(System.in);
    	String userInput = " "; //what the user enters
    	String id; //id of each webpage
    	String webLink; //link of each webpage
    	Scanner scnr2;
    	
    	while(userInput.toLowerCase().charAt(0) != 'q') {
    		
    		System.out.println(
    				"\n" +
    				"=========================== Search Engine ============================\n" + 
    				"1. Enter 'i <id> <webLink>' to insert a web page in the search engine\n" + 
    				"2. Enter 's <id>' to search a web link in the search engine\n" + 
    				"3. Enter 'p' to print all the web page ids in the search engine\n" + 
    				"4. Enter 'c' to get the count of all web pages in the search engine\n" + 
    				"5. Enter 'q' to quit the program\n" + 
    				"======================================================================" +
    				"\n"
    				);
    		
    		
    		System.out.print("Please enter your command:");
    		userInput = scnr.nextLine();
    		
    		//if q is entered, then the program quits
    		if(userInput.toLowerCase().charAt(0) == 'q') {
    			System.out.print("============================== END ===================================");
    			break;
    		} 
    		if(userInput.length() > 1) {
    			if(userInput.charAt(1) != ' ') {
    				System.out.println("WARNING: Unrecognized command.");
    				continue;
    			}
    		} //if an 'i' is entered, then put the provide id and weblink into the BST
    		if(userInput.toLowerCase().charAt(0) == 'i') {
    			scnr2 = new Scanner(userInput);
    			scnr2.next();
    			
    			try { //make sure the info has been provided correctly
    				id = scnr2.next().trim();
    				webLink = scnr2.next().trim();
    				engine.insert(id, webLink);
    			}
    			catch(Exception e) {
    				System.out.println("WARNING: failed to insert web page: "
    								+ "Id/web link can’t be blank!"
    								);
    			}
    			scnr2.close();
    			continue;
    		} //if the user enters 's', then use the id provided to search 
    		//the BST for that element
    		if(userInput.toLowerCase().charAt(0) == 's') {
    			scnr2 = new Scanner(userInput);
    			scnr2.next();
    			id = scnr2.next();
    			
    			webLink = engine.searchWebPage(id);
    			
    			if(webLink == null) {
    				System.out.println("No web link found for the web page " + id);
    			}else {
    				System.out.println(id + " - " + webLink);
    			}
    			scnr2.close();
    			continue;
    		} //if 'c' is entered (and only c), then provide the user with the BST count
    		if(userInput.toLowerCase().charAt(0) == 'c' && userInput.trim().length() == 1) {
    			System.out.println(engine.getWebPageCount());
    			continue;
    		} //if 'p' is entered (and only p), then print all of the IDs for the user
    		if(userInput.toLowerCase().charAt(0) == 'p' && userInput.trim().length() == 1) {
    			ArrayList<String> temp;
    			temp = engine.getAllWebPages();
    			
    			for(int i = 0; i < temp.size(); ++i) {
    				if(i == temp.size() - 1) {
    					System.out.print(temp.get(i));
    				}
    				else {
    					System.out.print(temp.get(i) + ", ");
    				}	
    			}
    			System.out.println("");
    			continue;
    		}
    		else {
    			System.out.println("WARNING: Unrecognized command.");
    		}
    	}
    	scnr.close();
    }
    
    /**
     * This class is used to throw an error when the user enters in
     * a duplicate ID.
     * 
     * @author Mouna
     *
     */
    @SuppressWarnings("serial") 
    public class DuplicateItemException extends RuntimeException{
    	
    	public DuplicateItemException() { 
    		super("WARNING: Duplicate Entry."); 
    	}
    	
        public DuplicateItemException(String message) { 
        	super(message); 
        }
    }
}

