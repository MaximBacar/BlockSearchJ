package ca.stronegroup.BlockSearchJ;

import java.math.BigInteger;

import org.bitcoinj.core.NetworkParameters;

public class trs {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		

		
		Wallet w = new Wallet(new BigInteger("56495500000000000003431"));
		
		System.out.println(w.getMnemonic().getPhrase());
		System.out.println(w.getAddress());
		System.out.println(w.getBalance());
		w.changeAddressFormat(AddressFormat.BIP44);
		System.out.println(w.getAddress());
		System.out.println(w.getBalance());
		w.changeAddressFormat(AddressFormat.BIP49);
		System.out.println(w.getAddress());
		System.out.println(w.getBalance());
		
	}

}