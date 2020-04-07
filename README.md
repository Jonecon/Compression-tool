**Encoder and Decoder**
-
 - Encoder and Decoder based LZW compression Technique and Multi-Way Trie
 - Unpacker and Bitpacker, used to futher compression
 
 **Usage**
 -
 
 **Compressing Data**
 
 $ cat inputdata|java LZWencode|java LZWpack > outputPacked
 
 **Uncompressing Data**
 
 $ cat outputPacked|java LZWunpack|java LZWdecode > copyedInputData
