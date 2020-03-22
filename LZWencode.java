//Name: Connor Jones, Mason Elliott
//ID: 1351782, 1347257

import java.io.*;
import java.util.*;

public class LZWencode{
	
	private Trie t;
	
	public static void main(String[] args){
		//Make it so the program isn't static.
		LZWencode e = new LZWencode();
		e.run(args);
	}
	
	public void run(String[] args){
		//Initilising our trie data structure.
		t = new Trie();
		try{
			//Making our byte stream from standard input.
			BufferedInputStream byteStream = new BufferedInputStream(System.in);
			
			//The variable that will read the value of the byte.
			int c;
			
			//Looping variables.
			int index = 0;
			int phraseNum = -1;
			
			//Pattern building list.
			ArrayList<Byte> key = new ArrayList<Byte>();
			
			//While there are bytes to process.
			while((c = byteStream.read()) != -1){
			
				//Add the byte to the list for pattern processing.
				key.add((byte)c);
				
				//See if the current pattern is in the trie.
				index = t.find(key);
				
				//If the pattern isn't in the trie.
				if (index == -1){
					//Output the last passed phrase number. 
					System.out.println(phraseNum);
					
					//Insert this pattern into the trie in hopes of seeing it again.
					t.insert(key);
					
					//Clear this pattern from the list.
					key.clear();
					
					//Add this byte as the starting point for the next loop.
					key.add((byte)c);
					
					//Setup the index for the first byte in the list.
					index = t.find(key);
				}
				
				//The pattern has been found, so make this the current phrase number.
				phraseNum = index;
			}
			//System.out.println("--------");
			System.out.println(index);
			
			//Close the stream.
			byteStream.close();
		}
		catch (Exception ex){
		}
	}
}
