//Connor Jones, Mason Elliott


import java.io.*;
import java.util.*;

class LZWdecode{


    	private ArrayList<Integer> phraseNums = new ArrayList<Integer>();
    	private ArrayList<Character> mistMatchChars = new ArrayList<Character>(); 
    	private LZWStack stack = new LZWStack(); 
    
    	public static void main (String[] args){
        	LZWdecode e = new LZWdecode();
        	e.run(args);
    	}
    
    	//Finds the missmatch character by going to the start of this encodings pattern.
    	private char FindMisMatch(int index){
     	   	if(phraseNums.get(index) == -1)
     	       		return mistMatchChars.get(index);
      		else
            		return FindMisMatch(phraseNums.get(index));
    	}

	//Goes through each step of the phrase numbers indexing and pushes the miss match key character onto the stack.
    	private void printPhrase(int index){
        	//Getting the phrase to print out
        	while(true){
            		stack.push(mistMatchChars.get(index));
            		index = phraseNums.get(index);
            		if(index == -1)
               			break; 
        	}
        	//Pops each key off the stack.
        	printStack();
    	}

	//Prints the encoded pattern by popping each key off the stack and writing it the console.
   	private void printStack(){
        	while(stack.returnHead() != null)
            		System.out.write(stack.pop());
    	}

	//Initialises the dictionary to the pattern size.
    	private void initDictionary(){
        	for(int i = 0; i < 256; i++){
        		//Adding each patterns key into the missmatch chars.
            		mistMatchChars.add((char)i);
            		//Setting these phrases to be the end point of any pattern represented as negative 1.
            		phraseNums.add(-1);
        	}
    	}
    
    	public void run(String[] args){
    		try{
    			//Setup
            		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            		int n;
            		int inputKey;
            		char misMatchChar;
            		String line;  
           
            		//Initalise a dictionary
            		initDictionary();
            
            		//While there are phrase numbers to read.
            		while((line = in.readLine()) != null){
                		//Get the first phrase number
                		inputKey = Integer.parseInt(line);
                		
				//Add that number to the list
                		phraseNums.add((int)inputKey);
                		
                		//Add an unknown miss match character to the list to keep them parallel.
                		mistMatchChars.add(null);
                		
                		//Find the previous phrase numbers missmatch key.
                		misMatchChar = FindMisMatch(inputKey);
                		
				//Insert the missmatch key into the previous phrases missmatch index.
                		if(phraseNums.size() > 257)
                    			mistMatchChars.set(mistMatchChars.size() - 2, misMatchChar);
                    			
            			//Output the encoded pattern.
                		printPhrase(inputKey);
            		}
            		//Cleanup
            		System.out.flush();
            		in.close();
        	}
        	catch(Exception e){
            		System.out.println(e);
        	}
    	}
}
