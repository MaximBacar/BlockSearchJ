package ca.stronegroup.BlockSearchJ.bip;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;

import org.bitcoinj.core.Base58;
import org.bitcoinj.core.Bech32;
import org.bitcoinj.crypto.ChildNumber;
import org.bitcoinj.crypto.DeterministicKey;
import org.bitcoinj.crypto.HDKeyDerivation;
//import org.bitcoinj.core.Bech32;
import org.web3j.crypto.Sign;

import ca.stronegroup.BlockSearchJ.AddressFormat;
import ca.stronegroup.BlockSearchJ.address.GenerateAddress;
import ca.stronegroup.BlockSearchJ.hash.PBKDF2;
import ca.stronegroup.BlockSearchJ.hash.SHA;
import ca.stronegroup.BlockSearchJ.keys.Key;
import ca.stronegroup.BlockSearchJ.math.ConvertBase;
import ca.stronegroup.BlockSearchJ.mnemonic.Mnemonic;
import ca.stronegroup.BlockSearchJ.mnemonic.MnemonicOperations;


public class Algorithms {
	
	public static String getAddress(String phrase) {
		
		Mnemonic mnemonic = new Mnemonic(phrase);
		Key masterKey = mnemonic.getMasterKeys();
		Key derivatedKey = masterKey.derivateFirstKey(AddressFormat.BIP84);
		String publicKey = derivatedKey.getPublicKey();
		
		
		return generateAddress(publicKey, AddressFormat.BIP84);
		
	}
	
	public static DeterministicKey generateMasterKeys (String seed) {
		byte[] seedByte = ConvertBase.binaryToBytes(ConvertBase.hexToBinary(seed));
		DeterministicKey keys = HDKeyDerivation.createMasterPrivateKey(seedByte);
		return keys;
	}
	
	
	public static String generateAddress(String publicKey, AddressFormat format) {
		
		
		if (format == AddressFormat.BIP44) {
			return GenerateAddress.generateBip44(publicKey);
		}
		if (format == AddressFormat.BIP49) {
			return GenerateAddress.generateBip49(publicKey);
		}
		if (format == AddressFormat.BIP84) {
			return GenerateAddress.generateBip84(publicKey);
		}

		return null;
		
		
		
		
	}

}
