//Connor Jones, Mason Elliott


import java.io.*;

public class LZWpack{
	
	
	public static void main (String[] args)	{
        	try{
            		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

			//setup variables.
            		int initialPhraseLength = 128;
            		int phraseLength = 255;
            		int bitsNeededForPhrase = 8;
            		int packingBit = 0;
            		int inputBit;
            		int outputMask = 0xFF000000;
            		int bitsInPacker = 0;
            		int startOfBitInput = 32;
            		int initailShiftBits = 0;
            		String line;  
  

            		//While there is phrase numbers to read.
            		while((line = in.readLine()) != null){  
            			//Getting the phrase number.
                		inputBit = Integer.parseInt(line);
                		
                		//Seeing how far we need to pack this phrase number into the int.
                		initailShiftBits = (startOfBitInput - bitsInPacker) - bitsNeededForPhrase;
                		
                		//Packing this phrase number
                		inputBit = inputBit << initailShiftBits;
                		
                		//Adding it to the packing bit
                		packingBit = packingBit | inputBit;
                		
				//Tracking the new bits added.
				bitsInPacker = bitsInPacker + bitsNeededForPhrase;

                		//While there is a byte to output.
                		while(bitsInPacker >= 8){
                    			//Output
                    			output(packingBit, outputMask);
                    			
                    			//Adjusting bits in packer
                    			bitsInPacker = bitsInPacker - 8; 
                    			packingBit = packingBit << 8;
                		}
                
                		//Checking if we need to use more bits.
                		if((phraseLength + 1) == (initialPhraseLength * 2)){
                    			bitsNeededForPhrase++;
                    			initialPhraseLength = ((phraseLength + 1));
                		}
                		phraseLength++;
                
            		}		 
            		//Making sure if there weren't 8 bits to output left to output the remaining bits.
            		if (bitsInPacker > 0)
            			output(packingBit, outputMask);
            		
                      	//Cleanup
            		System.out.flush();
            		in.close();
        	}
		catch(Exception e){
            		System.out.println("Error: " + e);
        	}
    	}
    	
    	//Takes the packing bit and mask and outputs a byte.
    	public static void output(int pack, int mask){
    		int output = pack & mask;
    		output >>>= 24;
    		System.out.write((byte)output);
    	}
}
