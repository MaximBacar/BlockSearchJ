package ca.stronegroup.BlockSearchJ;

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
	private Coins coin;
	
	/**
	 * Creates a new wallet instance to lookup on the Blockchain
	 * @param address Address of the wallet
	 * @param coin Currency of the wallet (BTC or ETH)
	 */
	public Wallet(String address, Coins coin) {
		
		this.address = address;
		this.coin = coin;
		
	}
	
	/**
	 * Get the balance of the wallet
	 * Searches the balance of a wallet on the Blockchain and returns it's value in USD or in it's currency
	 * 
	 * @param inUsd Returns the balance in USD if True
	 * @return Balance of the wallet (USD or Currency)
	 */
	public double getBalance(boolean inUsd) {
		return Blockchain.getBalance(this.address, this.coin, inUsd);
	}

}
