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
 * This class's purpose is to store information about each node
 * such as their children and the data they hold.
 * 
 * @author lauryn
 *
 */

public class WebPageNode {
	private final String id; 		// The id of the web page
	private final String webLink;   // The web link of the web page
    private WebPageNode leftChild;  // The leftChild of the the current WebPageNode
    private WebPageNode rightChild; // The rightChild of the the current WebPageNode
 
    /**
     * This is the only constructor for this class.
     * @param id
     * @param webLink
     */
    public WebPageNode(String id, String webLink) { 
    	this.id = id; //identifier of the web page stored in this node
    	this.webLink = webLink; //link of the web page stored in this node
    	
    } 

	///// Public setters and getters methods /////
    
    /**
     * This method provides the left child of the current node.
     * @return leftChild
     */
	public WebPageNode getLeftChild() {
		return leftChild;		
	}
	
	/**
	 * This method allows for the left child to be set.
	 * @param leftChild
	 */
	public void setLeftChild(WebPageNode leftChild) {
		this.leftChild = leftChild;
	}
	
	/**
	 * This method provides the right child of the current node.
	 * @return
	 */
	public WebPageNode getRightChild() {
		return rightChild;	
	}
	
	/**
	 * This method allows for the right child to be set.
	 * @param rightChild
	 */
	public void setRightChild(WebPageNode rightChild) {
		this.rightChild = rightChild;
	}
	
	/**
	 * This method returns the ID of the current node.
	 * @return ID
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * This method returns the webLink associated with this node.
	 * @return webLink
	 */
	public String getWebLink() {
		return webLink;
	}
}
