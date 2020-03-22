//Name: Connor Jones, Mason Elliott
//ID: 1351782, 

import java.util.*;


public class Trie{

	//Setup the global variables.
	final int SIZE = 256;
	int nextFree;
	private TrieNode root;
	
	//Setup the trieNode.
	class TrieNode{
		//Has a value which is the phase num.
		public int value;
		
		//Setup all the possible points to its children.
		TrieNode[] child = new TrieNode[SIZE];
		
		//Constructor which initializes all of the children to null, and assigns the node the next free phrase number.
		public TrieNode(){
			//For every child make it null.
			for (int i = 0; i < SIZE; i++)
				child[i] = null;
			
			//Give it the next phrase number.	
			value = nextFree;
			//Incriment that number by 1.
			nextFree++;
		}
	}
	
	//Creates a trie, populating all the children of the root node to every possible input from a byte.
	public Trie(){
		//Setting root node phrase num to -1
		nextFree = -1;
		//Creating root node.
		root = new TrieNode();
		//Setup every child of root.
		for(int i = 0; i < SIZE; i++){
			root.child[i] = new TrieNode();
		}
	}
	
	public void insert(ArrayList<Byte> key){
		TrieNode curr = root;
		int pos;
		//For every byte in this list, go down the trie until you find the missmatch, then insert.
		for(int i = 0; i < key.size(); i++){
			//Int value of the byte.
			pos = (int)key.get(i);
			
			//Missmatch was found, add this phrase.
			if(curr.child[pos] == null){
				curr.child[pos] = new TrieNode();
				return;
			}
			
			//Not a missmatch go to next level of nodes.	
			curr = curr.child[pos];
		}
	}
	
	public int find(ArrayList<Byte> key){
		TrieNode curr = root;
		int pos;
		for(int i = 0; i < key.size(); i++){
			//Int value of byte.
			pos = (int)key.get(i);
			
			//The pattern is not in the trie return -1
			if(curr.child[pos] == null)
				return -1;
			
			//Pattern is in the trie return the phrase number.	
			curr = curr.child[pos];
		}
	
	
		return curr.value;	
	}
}
