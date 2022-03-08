package ca.stronegroup.BlockSearchJ;

import java.math.BigInteger;

import ca.stronegroup.BlockSearchJ.bip.Algorithms;
import ca.stronegroup.BlockSearchJ.explorer.Blockchain;
import ca.stronegroup.BlockSearchJ.mnemonic.Mnemonic;

/**
 * The Wallet class allows access any wallet on the blockchain
 * Uses Blockchain.com Explorer (NO API REQUIRED)
 * 
 * Copyright (C) 2022 Strone Group
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at 
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and 
 * limitations under the License.
 * 
 * @author Maxim Bacar
 * @version 1.0
 *
 */
public class Wallet {
	
	private String address;
	private Mnemonic mnemonic;
	private AddressFormat addressFormat = AddressFormat.BIP84;
	
	/**
	 * Creates a new wallet instance to lookup on the Blockchain
	 * @param address Address of the wallet
	 * @param coin Currency of the wallet (BTC or ETH)
	 */
	public Wallet(BigInteger entropy) {
		
		this.mnemonic = new Mnemonic(entropy);
		this.address = Algorithms.generateAddress(this.mnemonic.getMasterKeys().derivateFirstKey(addressFormat).getPublicKey(),addressFormat);
		
	}
	
	public Wallet(String mnemonicPhrase) {
		
		this.mnemonic = new Mnemonic(mnemonicPhrase);
		this.address = Algorithms.generateAddress(this.mnemonic.getMasterKeys().derivateFirstKey(addressFormat).getPublicKey(),addressFormat);

	}
	
	public Wallet() {
		this.mnemonic = Mnemonic.randomMnemonic();
		this.address = Algorithms.generateAddress(this.mnemonic.getMasterKeys().derivateFirstKey(addressFormat).getPublicKey(),addressFormat);
	}
	
	public void changeAddressFormat(AddressFormat addressFormat) {
		this.addressFormat = addressFormat;
		this.address = Algorithms.generateAddress(this.mnemonic.getMasterKeys().derivateFirstKey(addressFormat).getPublicKey(),addressFormat);
	}
	
	/**
	 * Get the balance of the wallet
	 * Searches the balance of a wallet on the Blockchain and returns it's value in USD or in it's currency
	 * 
	 * @param inUsd Returns the balance in USD if True
	 * @return Balance of the wallet (USD or Currency)
	 */
	public double getBalance() {
		return Blockchain.getBalance(this.address);
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Mnemonic getMnemonic() {
		return mnemonic;
	}

	public void setMnemonic(Mnemonic mnemonic) {
		this.mnemonic = mnemonic;
	}

	public AddressFormat getAddressFormat() {
		return addressFormat;
	}

	public void setAddressFormat(AddressFormat addressFormat) {
		this.addressFormat = addressFormat;
	}
	
	

}
