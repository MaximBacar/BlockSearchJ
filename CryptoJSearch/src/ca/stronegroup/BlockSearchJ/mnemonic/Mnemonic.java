package ca.stronegroup.BlockSearchJ.mnemonic;

import java.math.BigInteger;

import ca.stronegroup.BlockSearchJ.keys.Key;

public class Mnemonic {
	
	private String entropy;
	private String phrase;
	private String[] words;
	private String seed;
	private Key masterKeys;
	
	public Mnemonic (BigInteger entropy) {
		
		
		this.entropy = MnemonicOperations.entropyFromInteger(entropy);
		this.words = MnemonicOperations.getWords(this.entropy);
		this.phrase = MnemonicOperations.wordlistToPhrase(words);
		this.seed = MnemonicOperations.generateSeedfromPhrase(phrase);
		
		this.masterKeys = new Key(seed);
		
	}
	
	public Mnemonic (String phrase) {
		
		this.phrase = phrase;
		this.words = MnemonicOperations.phraseToWords(phrase);
		this.entropy = MnemonicOperations.phraseToEntropy(phrase);
		this.seed = MnemonicOperations.generateSeedfromPhrase(phrase);
		
		this.masterKeys = new Key(seed);
		
		
	}
	
	public static Mnemonic randomMnemonic() {
		
		return new Mnemonic(MnemonicOperations.randomEntropy());
		
	}
	
	public String getEntropy() {
		return entropy;
	}

	public void setEntropy(String entropy) {
		this.entropy = entropy;
	}

	public String getPhrase() {
		return phrase;
	}

	public void setPhrase(String phrase) {
		this.phrase = phrase;
	}

	public String[] getWords() {
		return words;
	}

	public void setWords(String[] words) {
		this.words = words;
	}

	public String getSeed() {
		return seed;
	}

	public void setSeed(String seed) {
		this.seed = seed;
	}

	public Key getMasterKeys() {
		return masterKeys;
	}

	public void setMasterKeys(Key masterKeys) {
		this.masterKeys = masterKeys;
	}

	public String toString() {
		String str = entropy + "\n" + phrase + "\n" + seed;
		return str;
	}

}
