import java.io.*;

class LZWpack{

    public static void main (String[] args)
	{
        try{
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

            int initialPhraseLength = 128;
            int phraseLength = 255;
            int bitsNeededForPhrase = 8;
            int packingBit = 0;
            int inputBit;
            int outputMask = 0xFF000000;
            int bitsInPacker = 0;
            int startOfBitInput = 32;
            int initailShiftBits = 0;
            int output;
            byte outputByte;
            String line;  
  

            
            while((line = in.readLine()) != null){  
                inputBit = Integer.parseInt(line);
                //inputBit = 255;
                initailShiftBits = (startOfBitInput - bitsInPacker) - bitsNeededForPhrase;
                inputBit = inputBit << initailShiftBits;


                //Adding it to the packing bit
                packingBit = packingBit | inputBit;

                bitsInPacker = bitsInPacker + bitsNeededForPhrase;

                //Outputing bits
                while(bitsInPacker >= 8){
                    //Output
                    output = packingBit & outputMask;
                    //System.out.println(output);
                    output = output >> 24;
                    outputByte = (byte)output;
                    System.out.print(outputByte);
                    output = 0;
                    //Adjusting bits in packer
                    bitsInPacker = bitsInPacker - 8; 
                    packingBit = packingBit << 8;
                }
 
                if((phraseLength + 1) == (initialPhraseLength * 2)){
                    bitsNeededForPhrase++;
                    initialPhraseLength = ((phraseLength + 1) * 2);
                }
                phraseLength++;
                
            }           
            System.out.flush();
            in.close();
        }
        catch(Exception e){
            System.out.println(e);
        }

        
    }

}
