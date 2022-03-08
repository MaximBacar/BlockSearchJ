# BlockSearchJ

BlockSearchJ is a simple Java library that allows to easily generate from a 12 word mnemonic phrase, the BIP44, BIP49 and BIP84 addresses and to get the associated BTC balance with Blochain.info API. The library is only meant to access existing wallets and NOT to create ones.

### Technologies
* [BitcoinJ](https://github.com/bitcoinj/bitcoinj#getting-started) - for bitcoin algorithms
* [Blockchain API](https://www.blockchain.com/api/blockchain_api) - for Blockchain requests (Balance, Current BTC Price)

### Download

To get started, download [BlockSearchJ's Jar file](https://github.com/MaximBacar/BlockSearchJ/blob/master/BlockSearchJ.jar?raw=true) and add it to your Java Project Build Path.

### Wallet Example Code

#### Creating a Wallet object from a 12 word mnemonic phrase

```java
Wallet myWallet = new Wallet("word word word word word word word word word word word word");
```
#### Creating a Wallet object from a BIP39 entropy
```java
Wallet myWallet = new Wallet(new BigInteger("00"),2);
```
#### Creating a Random Wallet object
```java
//  Only meant for test purposes, doesn't generate a real Bitcoin wallet
Wallet myWallet = new Wallet();
```

#### Get Wallet Informations
```java
String phrase = myWallet.getMnemonic().getPhrase()    //  Get wallet's 12 word mnemonic phrase
String entropy = myWallet.getMnemonic().getEntropy()  //  Get wallet's binary entropy
String seed = myWallet.getMnemonic().getSeed()        //  Get wallet's BIP39 seed
Key keys = myWallet.getMnemonic().getMasterKeys()     //  Get wallet's master keys (public and private)

String address;
myWallet.changeAddressFormat(AddressFormat.BIP84);    //  Change wallet's address format to BIP84
address = myWallet.getAddress()                   // Get wallet's BIP84 first address
myWallet.changeAddressFormat(AddressFormat.BIP49);    //  Change wallet's address format to BIP49
address = myWallet.getAddress()                   // Get wallet's BIP49 first address
myWallet.changeAddressFormat(AddressFormat.BIP44);    //  Change wallet's address format to BIP44
address = myWallet.getAddress()                   // Get wallet's BIP44 first address

double balance = myWallet.getBalance()                //  Get wallet's balance of the first address of the selected address format

```
#### Get Public Key of a Specific Key Derivation
```java
//  Get the public key of m/84'/0'/0'/0/1 and generate it's address
String publicKey = myWallet.getMnemonic().getMasterKeys().derivateKeys("m/84'/0'/0'/0/1").getPublicKey();
Algorithms.generateAddress(publicKey, AddressFormat.BIP84);

//  Get the public key of the first BIP44 key (m/44'/0'/0'/0/0) and generate it's address
String publicKey = myWallet.getMnemonic().getMasterKeys().derivateFirstKey(AddressFormat.BIP44).getPublicKey();
Algorithms.generateAddress(publicKey, AddressFormat.BIP44);
```

### Explorer Example Code

#### Get the Balance of a Bitcoin Address
```java
double balance = Blockchain.getBalance("address")        //  Get the balance in BTC of an address
```

#### Convert BTC to Another Currency
```java
double balanceUsd = Blockchain.btcToCurrency(0.34, "usd") //  Convert 0.34BTC to USD
double balanceCad = Blockchain.btcToCurrency(2, "cad")   //  Convert 2BTC to CAD
```
