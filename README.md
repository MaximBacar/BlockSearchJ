### Welcome to BlockSearchJ

BlockSearchJ is a simple Java library that allows to easily generate from a 12 word mnemonic phrase, the Bip44, Bip49 and Bip84 addresses aswell as get the associate wallet balance using Blockchain.info API.

### Technologies
* [BitcoinJ](https://github.com/bitcoinj/bitcoinj#getting-started) - for bitcoin algorithms
* [Blockchain API](https://www.blockchain.com/api/blockchain_api) - for Blockchain requests (Balance, Current BTC Price)

### Download

To get started, download [BlockSearchJ's Jar file](https://github.com/MaximBacar/BlockSearchJ/blob/master/BlockSearchJ.jar?raw=true) and add it to your Java Project Build Path.



### Wallet Example Code

#### Creating a Wallet from a 12 word mnemonic phrase

```
Wallet myWallet = new Wallet("word word word word word word word word word word word word");

```
#### Creating a Wallet from a BIP39 entropy

```
Wallet myWallet = new Wallet(new BigInteger("00"),2);

```

#### Get Wallet Informations

```
myWallet.getMnemonic().getPhrase()      //  Get wallet's 12 word mnemonic phrase
myWallet.getMnemonic().getEntropy()     //  Get wallet's binary entropy
myWallet.getMnemonic().getSeed()        //  Get wallet's BIP39 seed
myWallet.getMnemonic().getMasterKeys()  //  Get wallet's master keys (public and private)

myWallet.changeAddressFormat(AddressFormat.BIP84);     //  Change wallet's address format to BIP84
myWallet.getAddress()                   // Get wallet's BIP84 first address
myWallet.changeAddressFormat(AddressFormat.BIP49);     //  Change wallet's address format to BIP49
myWallet.getAddress()                   // Get wallet's BIP49 first address
myWallet.changeAddressFormat(AddressFormat.BIP44);     //  Change wallet's address format to BIP44
myWallet.getAddress()                   // Get wallet's BIP44 first address

myWallet.getBalance()                   //  Get wallet's balance of the first address of the selected address format

```

#### Blockchain explorer

```
Blockchain.getBalance("address")        //  Get the balance in BTC of an address

//  Convert a BTC ammount to another currency
Blockchain.btcToCurrency(0.34, "usd")   //  Convert 0.34BTC to USD

```
