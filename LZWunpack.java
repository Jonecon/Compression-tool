//Name: Connor Jones, Mason Elliott
//ID: 1351782, 1347257

import java.io.*;

public class LZWunpack{

	public static void main (String[] args)
	{
        	try{
            		BufferedInputStream byteReader = new BufferedInputStream(System.in);

    	   		int initialPhraseLength = 128;
			int phraseLength = 255;
			int bitsNeededForPhrase = 8;
            		int inputBit;
           		int startOfBitInput = 32;
          		int line;  
  		
  			int bitsLeft = bitsNeededForPhrase;
  			int shift = 24;
  			int input;
            		int unpack = 0;
            		int output;
            		int outputTest = 0;
            		int mask;
            		int leftOver = 0;
            		int bitsCounted = 0;
            		int byteSize = 8;
            		
            		int count = 0;
            
            
            		while((line = byteReader.read()) != -1){ 
            			bitsCounted += byteSize;
            			//Setting up mask
            			mask = calcMask(bitsNeededForPhrase);
            			
            			input = line << shift;
            			unpack = unpack | input;
            			shift -= byteSize;
            			bitsLeft -= byteSize;

				
				while (bitsLeft > 0){
					line = byteReader.read();
					bitsCounted += byteSize;
					input = line << shift;
					unpack = unpack | input;
					bitsLeft -= byteSize;
					shift -= byteSize;
				}
				
				bitsCounted -= bitsNeededForPhrase;
				output = unpack & mask;
				unpack = unpack << bitsNeededForPhrase;
				shift = startOfBitInput - bitsNeededForPhrase;
				output = output >>> shift;
				
 				System.out.println(output);
 
                		if((phraseLength + 1) == (initialPhraseLength * 2)){
                    			bitsNeededForPhrase++;
                    			initialPhraseLength = ((phraseLength + 1));
                		}
                		phraseLength++;
                		
                		bitsLeft = bitsNeededForPhrase - bitsCounted;
                		shift = 24 - bitsCounted;
                		count++;
                		
            		}
            		
            		if (unpack != 0){
            			//System.out.println("unpack value is: " + unpack);
            			mask = calcMask(bitsNeededForPhrase);
            			output = unpack & mask;
            			output = output >>> shift;
            			System.out.println(output);
            		}
            			
      
            		System.out.flush();
            		byteReader.close();
        	}
        	catch(Exception e){
            		System.out.println(e);
        	}
    	}
    	
    	public static int calcMask(int bits){
    		int mask = -2147483648;
    		int curr = mask;
    		
    		for (int i = 1; i < bits; i++){
    			curr = curr/2;	
    			mask = mask - curr;
    		}
    		
    		return mask;
    	}
}
