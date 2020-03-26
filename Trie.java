//Name: Connor Jones, Mason Elliott
//ID: 1351782, 

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
	
	public void insert(ArrayList<Byte> key){
		Node currNode = root;
		int pos;
		byte keyCurr;
		int size = key.size();
		//For every byte in this list, go down the trie until you find the missmatch, then insert.
		for (int i = 0; i < size; i++){
			keyCurr = key.get(i);		
			//Fuck me in the ass and call me a donkey
			
			if (size - 1 == i){
				while (currNode.nextNode != null)
					currNode = currNode.nextNode;
				currNode.nextNode = new Node(keyCurr);
				return;
			}
			
			while (currNode.nextNode != null){
				int res = Byte.compare(keyCurr, currNode.contains);
				if (res == 0 && currNode.nextLevel == null){
					currNode.nextLevel = new Node(key.get(key.size() - 1));
					return;
				}
				
				if (res == 0){
					currNode = currNode.nextLevel;
					break;
				}
				
				currNode = currNode.nextNode;
			}
		}
	}
	
	public int find(ArrayList<Byte> key){
		Node currNode = root;
		byte keyCurr;
		int size = key.size();
		for(int i = 0; i < size; i++){
			keyCurr = key.get(i);
			//Shit cunt mother fucker.
			
			currNode = searchLevel(keyCurr, currNode);
			
			if (currNode == null)
				return -1;
				
			if (size - 1 == i)
				return currNode.value;
				
			if (currNode.nextLevel == null)
				return -1;
			
			currNode = currNode.nextLevel;
			
		}
		return -1;	
	}
	
	public Node searchLevel(byte key, Node curr){
		
		while (curr.nextNode != null){
			int res = Byte.compare(key, curr.contains);
			if (res == 0)
				return curr;
			curr = curr.nextNode;
		}
	
		return null;
	}
	
}
