package ca.stronegroup.BlockSearchJ.mnemonic;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.concurrent.ThreadLocalRandom;

import ca.stronegroup.BlockSearchJ.hash.PBKDF2;
import ca.stronegroup.BlockSearchJ.math.ConvertBase;
import ca.stronegroup.BlockSearchJ.hash.*;

public class MnemonicOperations {
	
	public static final int ENTROPY_SIZE = 128;
	
	public static BigInteger randomEntropy() {
		String entropy = "";
		for (int i = 0; i < ENTROPY_SIZE; i++) {
			int bit = ThreadLocalRandom.current().nextInt(0, 2);
			entropy = entropy + bit;
		}
		
		return new BigInteger(entropy,2);
	}
	
	public static String entropyFromInteger(BigInteger entropy) {
		
		String strEntropy = entropy.toString(2);
		
		strEntropy = ConvertBase.formatBinary(strEntropy, 128);
		
		return strEntropy;
		
	}
	
	public static String phraseToEntropy(String phrase) {
		String entropy = "";
		String[] words = phraseToWords(phrase);
		
		for (int i = 0; i < 12; i++) {
			entropy += BIP39WORDLIST.indexOfWord(words[i]);
		}
		entropy = entropy.substring(0, entropy.length()-4);
		return entropy;
	}
	
	public static String wordlistToPhrase(String[] words) {
		return ConvertBase.wordsTophrase(words);
	}
	
	public static String[] phraseToWords(String phrase) {
		String[] words = new String[12];
		
		for (int i = 0; i < 11; i++) {
			words[i] = phrase.substring(0, phrase.indexOf(" "));
			
			phrase = phrase.substring(phrase.indexOf(" ")+1);
			
		}
		words[11] = phrase;
		
		return words;
	}
	
	public static String genCheckSum(String entropy) {
		
		byte[] eBytes = ConvertBase.binaryToBytes(entropy);
		byte[] hashed = SHA.sha256(eBytes);
		String hashedBinary = ConvertBase.bytesToBinary(hashed);
		
		String checksum = hashedBinary.substring(0,4);
		return checksum;
	}
	
	
	public static String[] getWords(String entropy) {
		
		entropy = entropy + "" + genCheckSum(entropy);
		
		return entropyToWords(entropy);
		
	}
	
	public static String[] entropyToWords(String binaryEntropy) {
		String[] words = new String[12];
		
		
		
		for (int i = 0; i < words.length; i++) {
			
			words[i] = BIP39WORDLIST.words[ConvertBase.binaryToDecimal(binaryEntropy.substring(i*11,i*11+11)).intValue()];
		}
		
		return words;
	}
	
	public static String generateSeedfromPhrase(String phrase) {
		String seed = "";
		try {
			seed = PBKDF2.createHash(phrase);
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			e.printStackTrace();
		}
		
		return seed;
	}
	
	public static String generateMasterKeys() {
		return null;
	}
	
	

}
