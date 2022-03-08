package ca.stronegroup.BlockSearchJ;

public enum AddressFormat {
	
	
	BIP84("84"),
	BIP49("49"),
	BIP44("44");
	
	public final String bip;

    private AddressFormat(String bip) {
        this.bip = bip;
    }
	

}
