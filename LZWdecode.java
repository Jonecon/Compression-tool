//Name: Connor Jones, Mason Elliott
//ID: 1351782, 1347257

import java.io.*;
import java.util.*;

class LZWdecode{


    private ArrayList<Integer> phraseNums = new ArrayList<Integer>();
    private ArrayList<Character> mistMatchChars = new ArrayList<Character>(); 
    private LZWStack stack = new LZWStack(); 
    
    private char FindMisMatch(int index){
        if(phraseNums.get(index) == -1){
            return mistMatchChars.get(index);
        }
        else{
            return FindMisMatch(phraseNums.get(index));
        }
    }

    private void printPhrase(int index){
        //Getting the phrase to print out
        while(true){
            stack.push(mistMatchChars.get(index));
            index = phraseNums.get(index);
            if(index == -1){
                break;
            }    
        }
    }

    private void printStack(){
        while(stack.returnHead() != null){
            System.out.print(stack.pop());
        }
    }

    private void initDictionary(){
        for(int i = 0; i < 256; i++){
            mistMatchChars.add((char)i);
            phraseNums.add(-1);//Inserting -1 into the phraseNum
        }
    }
   

    public static void main (String[] args){
        LZWdecode e = new LZWdecode();
        e.run(args);
    }
    
    public void run(String[] args){
    	try{
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            //BufferedReader in = new BufferedReader(new FileReader(System.in));
            int n;
            char inputKey;
            char misMatchChar;
            String line;  
  
            //Initalise a dictionary
            initDictionary();

            //while((n = Integer.parseInt(in.readLine())) != -1){
            
            while((line = in.readLine()) != null){
                
                n = Integer.parseInt(line);
                inputKey = n;

                phraseNums.add((int)inputKey);
                mistMatchChars.add(null);
                misMatchChar = FindMisMatch(inputKey);

                if(phraseNums.size() > 256){
                    mistMatchChars.set(mistMatchChars.size() - 2, misMatchChar);
                }
            
                printPhrase(inputKey);
                printStack();
            }

            
            System.out.flush();
            in.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}
