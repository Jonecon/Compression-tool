//Name: Connor Jones, Mason Elliott
//ID: 1351782, 1347257

import java.io.*;
import java.lang.Math;

public class LZWunpack{

	public static void main (String[] args)
	{
        	try{
        		//Setup
            		BufferedInputStream byteReader = new BufferedInputStream(System.in);
            		final int BYTESIZE = 8;
            		final int STARTINPUT = 32;
            		
			int phraseLength = 256;
			int bitsPhrase = 8;
          		int line;  
  			int bitsLeft = bitsPhrase;
  			int shift = 24;
  			int input;
            		int unpack = 0;
            		int bitsCounted = 0;
            
            		//while there are bytes to read.
            		while((line = byteReader.read()) != -1){ 
            			//Increase the amount of bits counted by a byte
            			bitsCounted += BYTESIZE;
            			
            			//Shift the input left to prepare to be packed.
            			input = line << shift;
            			
            			//Put the byte into the packing int
            			unpack = unpack | input;
            			
            			//Adjust shift for the next number.
            			shift -= BYTESIZE;
            			
            			//Decrease the bits left needed for the phrasenumber. 
            			bitsLeft -= BYTESIZE;

				//While you still need more bits for the phrase number
				while (bitsLeft > 0){
					//Read another byte
					line = byteReader.read();
					
					//Increase bits counted
					bitsCounted += BYTESIZE;
					
					//shift the input to prepare to be packed
					input = line << shift;
					
					//Pack it into the unpack int
					unpack = unpack | input;
					
					//Decrease bitsLeft by another byte
					bitsLeft -= BYTESIZE;
					
					//Decrease shit by another byte.
					shift -= BYTESIZE;
				}
				
				//Since we now have enough bits to output the phrase number decrease bits counted by this phrase number bit size.
				bitsCounted -= bitsPhrase;
				
				//Calculate how much we need to shift output to the right.
				shift = STARTINPUT - bitsPhrase;
				
				//Output the phrase number.
 				output(bitsPhrase, shift, unpack);
 				
 				//Remove the output from the unpacker
 				unpack = unpack << bitsPhrase;
                		
                		//Increase the amount of phrase numbers we have now read.
                		phraseLength++;
                		
                		//Calculate bits needed.
                		bitsPhrase = (int)(Math.ceil(Math.log(phraseLength) / Math.log(2))); 
                		
                		//See how many bits we need to read next loop taking into account what is already in the unpacker int
                		bitsLeft = bitsPhrase - bitsCounted;
                		//Setup shift for the next loop.
                		shift = 24 - bitsCounted;
                		
            		}
            		//If there is any bits leftover in unpack
            		if (unpack != 0)
            			output(bitsPhrase, shift, unpack);
      
            		System.out.flush();
            		byteReader.close();
        	}
        	catch(Exception e){
            		System.out.println(e);
        	}
    	}
    	
    	//Outputs the phrase numbers to the console.
    	public static void output(int bitsPhrase, int shift, int unpack){
    		int mask, output;
    		//Gets the mask for this output based on the bits needed.
    		mask = calcMask(bitsPhrase);
    		
    		//Masks the phrasenumber into output
            	output = unpack & mask;
            	
            	//Shifts this ouptput right by the required amount of bits.
            	output = output >>> shift;
            	
            	//Prints the phrase number
            	System.out.println(output);
    	}
    	
    	//Sets the mask to the required most significant bits.
    	public static int calcMask(int bits){
    		//Sets the mask to 0xF0000000
    		int mask = -2147483648;
    		int curr = mask;
    		
    		//Halves the number everytime therefore setting the next most significant bit to 1
    		for (int i = 1; i < bits; i++)
    			mask /= 2;
    		
    		return mask;
    	}
}
