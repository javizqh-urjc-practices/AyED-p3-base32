//package ayedd.e3;
// Es un programa que codifica y decodifica bytes en una codificación en Base16.

import edu.princeton.cs.algs4.*;

public class Base16 {

	public static String encode(byte[] content){
        String encoded = new String();
        for (int i=0; i < content.length; i++){
			byte b_1 = (byte) ((0x0f) & (content[i] >> 4)); //me quedo con los bits 4 5 6 y 7
			encoded+= encodeByte(b_1);
            byte b_2 = (byte)(content[i] & 0x0f); //me quedo con el byte menos significativo del entero
			encoded+= encodeByte(b_2);
        }
        return encoded;
    }

	public static char encodeByte(byte b){
		// Codifica un byte
		if (b>5){
			char c = (char)('A'+(b-6));
			return c;
		} else {
			return (char) b;
		}
	}

	public static int decodeChar(char c){
		// Decodifica un caracter
		if (Character.compare(c,'A')<0){
			return Character.compare(c,'A')+17;
		} else {
			for (int j=15; j>=5; j--){
				if (c == (char) ('A'+(j-5))){
					return j+1;
				}
			}
		}
		return 10;
	}

	public static byte[] decode(String text){
		byte[] decoded = new byte[text.length()/2];
		if (text.length()%2==1){
			//Detecta si la string tiene un numero impar de caracteres
			return null;
		}
		for (int i=0;i<text.length();i+=2){
			Integer b = 0;
			for (int bytes = 0 ; bytes < 2; bytes++){
				char c = text.charAt(i+bytes);
				if (Character.compare(c,'J')>0){
					//Detecta si tiene un caracter que no se es decodificable
					return null;
				}
				b += decodeChar(c);
				if (bytes == 0){
					b = b*16;
				}
			}
			byte final_byte = b.byteValue();
			decoded[i/2]=final_byte;
		}

		return decoded;
	}
	public static void main (String [] args){
		byte [] a = decode ("JJJ");
		//System.out.println(a[0]);
		//System.out.println(encode(a));
	}
}