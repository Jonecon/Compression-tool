//Connor Jones, Mason Elliott


public class LZWStack{

    	//FList class Fields
    	private Node head;
    	private int count = 0;

    	//Private Node class
     	private class Node{ 
     		//Node variables
        	private char Data;
        	Node next;

        	//Constructor
        	public Node(char newData){
        		Data = newData;
        	}
    	}

    	// Whatever properties are needed to implement the stack
    	public void push(char item){
        	//Node currStackNode = head;
       	 	Node newNode = new Node(item);//Creating new Node object
        	newNode.next = head;
        	//Assigning newNode to the head of the linked list
        	head = newNode;
        	//Increasing the count
        	count++;
        	return;
    	}
	
	//Removes and returns the head of the stack.
    	public char pop() {
         	Node currStackNode = head;
         	head = head.next;
         	count--;
        	return currStackNode.Data;
    	}

	//Checks to see whether or not the stack is empty by checking if the head is null
    	public boolean empty() {
        	// Return true if stack is empty, false otherwise
        	if(head == null)
            		return true;
        	else
            		return false;
    	}

    	//Testing Method
    	public Node returnHead(){
        	return head;
        }
}
