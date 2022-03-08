package ca.stronegroup.BlockSearchJ.explorer;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.ProxySelector;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


/**
 * 
 * The Blockchain class allows to explore the blockchain
 * Uses Blockchain.com Explorer (NO API REQUIRED)
 * 
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
	
	// Target URL of Blockchain.info
	private static final String TARGET_URL = "https://blockchain.info/";
	
	
	
	public static double getBalance(String address) {
		
		double balance = 0;
		String url = TARGET_URL + "balance?active=" + address;
		String balanceTag = "\""+address+"\":{\"final_balance\":";
		
		String response = BlockchainHTTP.blockchainRequest(url);
		
		if (response.indexOf(balanceTag) != -1) {
			balance = Double.parseDouble(response.substring(response.indexOf(balanceTag)+balanceTag.length(),response.indexOf(',') ));
			balance = balance / 100000000f;
		}

		return balance;		
	}
	
	public static double btcToCurrency(double balance, String currency) {
		
		String url = TARGET_URL + "ticker";
		String response = BlockchainHTTP.blockchainRequest(url);
		String balanceTag = "\"sell\": ";
		double btcInCurrency;
		
		if (response.indexOf(currency.toUpperCase()) != -1) {
			response = response.substring(response.indexOf(currency.toUpperCase()));
			response = response.substring(response.indexOf(balanceTag));
			btcInCurrency = Double.parseDouble(response.substring(response.indexOf(balanceTag)+balanceTag.length(), response.indexOf(',')));
			balance = balance * btcInCurrency;
		}
		
		return balance;
	}
	
	
	

}
