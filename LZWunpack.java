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
            
            
            		while((line = byteReader.read()) != -1){ 
            			//Setting up mask
            			mask = calcMask(bitsNeededForPhrase);
            			
            			System.out.println("The mask is: "+mask);
            			
            			input = 0;
            			input = line << shift;
            			unpack = unpack | input;
            			shift -= 8;
            			bitsLeft -= 8;

				
				while (bitsLeft > 0){
					if (bitsLeft < 8)
						leftOver = 8 - bitsLeft;
					
					line = byteReader.read();
					input = line << shift;
					unpack = unpack | input;
					bitsLeft -= 8;
					shift -= 8;
				}
				
				output = unpack & mask;
				unpack = unpack << bitsNeededForPhrase;
				shift = startOfBitInput - bitsNeededForPhrase;
				System.out.println("Output before shift: " + output);
				System.out.println("Shifts output to the right: " + shift + " bits");
				output >>= shift;
				outputTest = 0x00000000;
				outputTest = outputTest | output;
				
 				System.out.println("Unpacked int: " + outputTest);
 				System.out.println();
 
                		if((phraseLength + 1) == (initialPhraseLength * 2)){
                    			bitsNeededForPhrase++;
                    			initialPhraseLength = ((phraseLength + 1) * 2);
                		}
                		phraseLength++;
                		bitsLeft = bitsNeededForPhrase;
                		shift = 24 - leftOver;
                		
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
