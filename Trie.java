//Connor Jones, Mason Elliott


import java.util.*;


public class Trie{

	//Setup the global variables.
	final int SIZE = 256;
	int nextFree;
	private Node root;
	
	public class Node{
		//Has a value which is the phase num, and a byte that it's representing.
		public int value;
		public byte contains;
		
		//Can go across, and down levels since it's a trie
		public Node nextNode;
		public Node nextLevel;
		
		//Node constructor.
		public Node(byte b){
			contains = b;
			nextNode = null;
			nextLevel = null;
			value = nextFree;
			nextFree++;
		}
	}
	
	//Creates a trie, populating all the children of the root node to every possible input from a byte.
	public Trie(){
		//Setting root node phrase num to -1
		nextFree = 0;
		//Creating root node.
		root = new Node((byte)0);
		//Crawler to go cycle through root.
		Node crawl = root;
		//TrieNode TrieCrawl = root;
		//Setup every child of root.
		for(int i = 1; i < SIZE; i++){
			crawl.nextNode = new Node((byte)i);
			crawl = crawl.nextNode;
		}
	}
	
	//Inserts the key pattern to the bottom end of the linked list at the same level as the key's length
	public void insert(ArrayList<Byte> key){
		Node currNode = root;
		int pos;
		byte keyCurr;
		int size = key.size();
		int res;
		//For every byte in this list, go down the trie until you find the missmatch, then insert.
		for (int i = 0; i < size; i++){
			keyCurr = key.get(i);
			
			//If we're on the right level insert the byte at the end of the linked list.
			if (i == (size - 1)){
				//Find the end of the linked list
				while (currNode.nextNode != null)
					currNode = currNode.nextNode;
				//Insert the key
				currNode.nextNode = new Node(keyCurr);
				return;
			}
			
			//While the currNode isn't null.
			while (currNode != null){
				//See if this node stores the same byte as the current key.
				res = Byte.compare(keyCurr, currNode.contains);
				
				//If the byte is the same and this node doesn't have a next level create another level with this byte.
				if (res == 0 && currNode.nextLevel == null){
					currNode.nextLevel = new Node(key.get(size - 1));
					return;
				}
				
				//If the byte is the same but there is another level goto next level.
				if (res == 0){
					currNode = currNode.nextLevel;
					break;
				}
				
				//If the byte is not the same goto the next node.
				currNode = currNode.nextNode;
			}	
		}
	}
	
	//Checks if there is a pattern in the trie and if there is returns the phrasenum of this pattern.
	public int find(ArrayList<Byte> key){
		Node currNode = root;
		byte keyCurr;
		int size = key.size();
		
		//For the length of the pattern.
		for(int i = 0; i < size; i++){
			keyCurr = key.get(i);
			
			//Search this level for this key.
			currNode = searchLevel(keyCurr, currNode);
			
			//If the search returned nothing return that this pattern is not in the trie.
			if (currNode == null)
				return -1;
				
			//If it has returned a node and we're on the last key in the pattern return this nodes phrasenumber.
			if (size - 1 == i)
				return currNode.value;
				
			//If this node exists but there is no next level then return that this pattern is not in the trie.
			if (currNode.nextLevel == null)
				return -1;
			
			//Go to then next level.
			currNode = currNode.nextLevel;
			
		}
		//Should never be called but just incase it's here.
		return -1;	
	}
	
	//Searches the linked list level for a node with the same key.
	public Node searchLevel(byte key, Node curr){
		int res;
		//While there are nodes to check
		while (curr != null){
			//If they are the same return it.
			res = Byte.compare(key, curr.contains);
			if (res == 0)
				return curr;
				
			//Otherwise continue to the next node.
			curr = curr.nextNode;
		}
		//Nothing was found.
		return null;
	}
	
}
