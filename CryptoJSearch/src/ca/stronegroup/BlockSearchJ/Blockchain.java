package ca.stronegroup.BlockSearchJ;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
/**
 * 
 * The Blockchain class allows to explore the blockchain
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
public class Blockchain {
	
	// Target URL of Blockchain.com
	private static final String TARGET_URL = "https://www.blockchain.com/";
	
	/**
	 * Searches the balance of a wallet on the Blockchain and returns it's value in USD or in it's currency
	 * 
	 * 
	 * @param address Address of the wallet
	 * @param coin	Currency of the wallet (BTC or ETH)
	 * @param inUsd	Returns the balance in USD if True
	 * @return
	 */
	public static double getBalance(String address, Coins coin, boolean inUsd) {
		double balance = 0;
		String url = TARGET_URL+""+coin.toString().toLowerCase()+"/address/"+address;
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
				.GET()
				.header("user-agent" , "Mozilla/5.0 (iPhone; CPU iPhone OS 10_3_1 like Mac OS X) AppleWebKit/603.1.30 (KHTML, like Gecko) Version/10.0 Mobile/14E304 Safari/602.1', value")
				.uri(URI.create(url))
				.build();
		try {
			HttpResponse<String> reponse = client.send(request, HttpResponse.BodyHandlers.ofString());
			String html = reponse.body();
			String balanceFinder = "The current value of this address is";
			int balanceIndex = html.indexOf(balanceFinder)+ balanceFinder.length();
			String bal = html.substring(balanceIndex, balanceIndex+300);
			if (inUsd) {
				String usdBalance = bal.substring(bal.indexOf('$')+1, bal.indexOf(')'));
				usdBalance = usdBalance.replaceAll(",", "");
				balance = Double.parseDouble(usdBalance);
			}
			else {
				String currencyBalance = bal.substring(0, bal.indexOf(coin.toString().toUpperCase())-1);
				balance = Double.parseDouble(currencyBalance);
				
				
			}
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		return balance;
	}

}
