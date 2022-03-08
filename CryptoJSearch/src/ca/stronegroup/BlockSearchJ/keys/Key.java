package ca.stronegroup.BlockSearchJ.keys;

import org.bitcoinj.crypto.ChildNumber;
import org.bitcoinj.crypto.DeterministicKey;
import org.bitcoinj.crypto.HDKeyDerivation;

import ca.stronegroup.BlockSearchJ.AddressFormat;
import ca.stronegroup.BlockSearchJ.bip.Algorithms;

public class Key {
	
	private DeterministicKey masterKeys;
	
	public Key (String seed) {
		masterKeys = Algorithms.generateMasterKeys(seed);
	}
	public Key(DeterministicKey masterKeys) {
		this.masterKeys = masterKeys;
	}
	
	public Key derivateKeys(String derivation) {
		
		int numberOfDerivation = 0;
		for (int i = 0; i < derivation.length();i++) {
			if (derivation.charAt(i) == '/') {
				numberOfDerivation++;
			}
		}
		
		DeterministicKey childKey = masterKeys;
		derivation = derivation.substring("m/".length()) + "/";
		
		for (int i = 0; i < numberOfDerivation; i++) {
			String child = derivation.substring(0, derivation.indexOf("/"));
			boolean hardened = false;
			if (child.indexOf("'") != -1) {
				hardened = true;
				child = child.substring(0,child.length()-1);
			}
			
			
			ChildNumber childNumber = new ChildNumber(Integer.parseInt(child), hardened);
			childKey = HDKeyDerivation.deriveChildKey(childKey, childNumber);
			
			derivation = derivation.substring(derivation.indexOf("/")+1);
			
		}
		
		return new Key(childKey);
		
	}
	
	public String getPublicKey() {
		return masterKeys.getPublicKeyAsHex();
	}
	public String getPrivateKey() {
		return masterKeys.getPrivateKeyAsHex();
	}
	
	public Key derivateFirstKey (AddressFormat format) {
		String path = "m/"+format.bip+"'/0'/0'/0/0";
		return derivateKeys(path);
	}
	
	

}
