package ayedd.e3;
// Es un programa que codifica y decodifica bytes en una codificación en Base16.

import edu.princeton.cs.algs4.*;

public class Base16 {

	public static String encode(byte[] content){
	//Codificador
        String encoded = new String();
        for (int i=0; i < content.length; i++){
			byte b_1 = (byte) ((0x0f) & (content[i] >> 4)); //me quedo con los bits 4 5 6 y 7
			if (b_1>5){
				char c = (char)('A'+(b_1-6));
				encoded+=c;
			} else {
				encoded+=b_1;
			}
           		byte b_2 = (byte)(content[i] & 0x0f); //me quedo con el byte menos significativo del entero
			if (b_2>5){
				char c = (char)('A'+(b_2-6));
				encoded+=c;
			} else {
				encoded+=b_2;
			}
        }
        return encoded;
    }

	public static byte[] decode(String text){
	//Decodificador
		byte[] decoded = new byte[text.length()/2];
		if (text.length()%2==1){
			return null;
		}
		for (int i=0;i<text.length();i+=2){
			Integer b = 0;
			for (int bytes = 0 ; bytes < 2; bytes++){
			//Para cada byte lo decodifico
				char c = text.charAt(i+bytes);
				if (Character.compare(c,'J')>0){
					return null;
				}
				if (Character.compare(c,'A')<0){
					b += Character.compare(c,'A')+17;
				} else if (Character.compare(c,'A')>=0){
					for (int j=15; j>=5; j--){
						if (c == (char) ('A'+(j-5))){
							b +=j+1;
						}
					}
				}
				if (bytes == 0){
					b = b*16;
				}
			}
			byte final_byte = b.byteValue();
			decoded[i/2]=final_byte;
		}
		return decoded;
	}
}
