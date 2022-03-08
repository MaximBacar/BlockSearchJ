package ca.stronegroup.BlockSearchJ.math;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;

public class ConvertBase {
	
	
	private static final byte[] HEX_ARRAY = "0123456789ABCDEF".getBytes(StandardCharsets.US_ASCII);
	
	public static String formatBinary(String binary, int length) {
		int binLength = binary.length();
		
		if (binLength != length) {
			for (int i = 0; i < length-binLength; i++) {
				binary = "0"+binary;
			}
		}
		return binary;
	}
	
	public static String bytesToHex(byte[] bytes) {
	    byte[] hexChars = new byte[bytes.length * 2];
	    for (int j = 0; j < bytes.length; j++) {
	        int v = bytes[j] & 0xFF;
	        hexChars[j * 2] = HEX_ARRAY[v >>> 4];
	        hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
	    }
	    return new String(hexChars, StandardCharsets.UTF_8);
	}
	
	
	public static String wordsTophrase(String[] phrase) {
		String words = "";
		
		for (int i = 0; i < phrase.length; i++) {
			words = words + " " + phrase[i];
		}
		
		words = words.substring(1);
		return words;
	}
	
	public static String hexToBinary(String hex)
    {
 
        // variable to store the converted
        // Binary Sequence
        String binary = "";
 
        // converting the accepted Hexadecimal
        // string to upper case
        hex = hex.toUpperCase();
 
        // initializing the HashMap class
        HashMap<Character, String> hashMap
            = new HashMap<Character, String>();
 
        // storing the key value pairs
        hashMap.put('0', "0000");
        hashMap.put('1', "0001");
        hashMap.put('2', "0010");
        hashMap.put('3', "0011");
        hashMap.put('4', "0100");
        hashMap.put('5', "0101");
        hashMap.put('6', "0110");
        hashMap.put('7', "0111");
        hashMap.put('8', "1000");
        hashMap.put('9', "1001");
        hashMap.put('A', "1010");
        hashMap.put('B', "1011");
        hashMap.put('C', "1100");
        hashMap.put('D', "1101");
        hashMap.put('E', "1110");
        hashMap.put('F', "1111");
 
        int i;
        char ch;
 
        // loop to iterate through the length
        // of the Hexadecimal String
        for (i = 0; i < hex.length(); i++) {
            // extracting each character
            ch = hex.charAt(i);
 
            // checking if the character is
            // present in the keys
            if (hashMap.containsKey(ch))
 
                // adding to the Binary Sequence
                // the corresponding value of
                // the key
                binary += hashMap.get(ch);
 
            // returning Invalid Hexadecimal
            // String if the character is
            // not present in the keys
            else {
                binary = "Invalid Hexadecimal String";
                return binary;
            }
        }
 
        // returning the converted Binary
        return binary;
    }
	
	public static BigDecimal binaryToDecimal (String binary) {
		BigDecimal sum = new BigDecimal("0");
	    BigDecimal base = new BigDecimal(2);
	    BigDecimal temp;
	    for(int i=0;i<binary.length();i++){
	        if(binary.charAt(i)== '1'){
	            int exponent= binary.length()-1-i;
	            temp=base.pow(exponent);
	            sum=sum.add(temp);
	        }

	    }
	    return sum;
	}

	
	public static byte[] binaryToBytes(String binary) {
		byte[] bytes = new byte[binary.length()/8];
		
		for (int i = 0; i < (binary.length()/8); i++ ) {
			bytes[i] = binaryToDecimal(binary.substring(i*8, i*8+8)).byteValue();
		}
		return bytes;
	}
	
	public static int[] byteToInt(byte[] bytes) {
		
		int[] bytesint = new int[bytes.length];
		
		for (int i = 0; i < bytes.length; i++) {
			bytesint[i] = Byte.toUnsignedInt(bytes[i]);
		}
		
		return bytesint;
	}
	public static String bytesToBinary(byte[] bytes) {
		String binary = "";
		
		for (int i = 0; i < bytes.length; i++) {
			int toDecimal = Byte.toUnsignedInt(bytes[i]);
			binary = binary + Integer.toBinaryString(toDecimal);
			if (binary.length() < 8) {
				binary = formatBinary(binary, 8);
				
						
			}
		}
		
		return binary;
	}
	

	public static String binaryTo5Bit(String binary) {
		String b = "";
		byte[] bytes = new byte[binary.length()/5];
		for (int i = 0; i < binary.length()/5;i++) {
			bytes[i] = binaryToDecimal(binary.substring(i*5,i*5+5)).byteValue();
		}
		
		for (int i = 0; i < bytes.length; i++) {
			String hex = Integer.toHexString(bytes[i]);
			if (hex.length() == 1) {
				hex = "0"+hex;
			}
			b = b+hex;
		}
		return b;
	}
	
}
