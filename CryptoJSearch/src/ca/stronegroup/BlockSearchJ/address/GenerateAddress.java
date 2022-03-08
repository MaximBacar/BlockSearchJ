package ca.stronegroup.BlockSearchJ.address;

import java.math.BigInteger;
import java.util.Arrays;

import org.bitcoinj.core.Base58;
import org.bitcoinj.core.Bech32;

import ca.stronegroup.BlockSearchJ.hash.SHA;
import ca.stronegroup.BlockSearchJ.math.ConvertBase;

public class GenerateAddress {
	
	public static String generateBip84(String publicKey) {
		
		byte[] hashed = hash160(publicKey);
		BigInteger hex = new BigInteger(ConvertBase.bytesToHex(hashed),16);
		String hexdecimal = hex.toString(16);
		if (hexdecimal.length()<40) {
			hexdecimal = "0"+hexdecimal;
		}
		String binary = ConvertBase.hexToBinary(hexdecimal);
		
		String w = "00"+ConvertBase.binaryTo5Bit(binary);
		
		
		byte[] data = ConvertBase.binaryToBytes(ConvertBase.hexToBinary(w));
		
		String address = Bech32.encode("bc", data);
		
		return address;
		
	}
	
	public static String generateBip49(String publicKey) {
		
		byte[] hash160 = hash160(publicKey);
		
		byte [] script = hash160("0014"+ConvertBase.bytesToHex(hash160));
		
		byte[] prefixed = new byte[script.length+1];
		prefixed[0] = 5;
		for (int i = 0; i < script.length; i++) {
			prefixed[i+1] = script[i];
		}
		
		byte[] checkSum = checkSum(prefixed);
		
		byte[] address = new byte[(prefixed.length + checkSum.length)];
		
		for (int i = 0; i < prefixed.length; i++) {
			address[i] = prefixed[i];
		}
		for(int i = 0; i < checkSum.length;i++) {
			address[i+prefixed.length] = checkSum[i];
		}
		
		
		String add = Base58.encode(address);
		return add;
	}
	
	public static String generateBip44(String publicKey) {
		
		byte[] hash160 = hash160(publicKey);
		
		
		byte[] prefixed = new byte[hash160.length+1];
		prefixed[0] = 0;
		for (int i = 0; i < hash160.length; i++) {
			prefixed[i+1] = hash160[i];
		}
		
		byte[] checkSum = checkSum(prefixed);
		
		byte[] address = new byte[(prefixed.length + checkSum.length)];
		
		for (int i = 0; i < prefixed.length; i++) {
			address[i] = prefixed[i];
		}
		for(int i = 0; i < checkSum.length;i++) {
			address[i+prefixed.length] = checkSum[i];
		}
		
		
		String add = Base58.encode(address);
		return add;
	}
	
	private static byte[] hash160(String publicKey) {
		return SHA.md160(SHA.sha256(ConvertBase.binaryToBytes(ConvertBase.hexToBinary(publicKey))));
	}
	private static byte[] checkSum (byte[] data) {
		String checksum = "";
		
		data = SHA.sha256(SHA.sha256(data));
		byte[] checkSumBytes = {data[0],data[1],data[2],data[3]};
		
		return checkSumBytes;
	}
}
