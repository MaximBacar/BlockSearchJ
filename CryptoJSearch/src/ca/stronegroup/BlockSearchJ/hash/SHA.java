package ca.stronegroup.BlockSearchJ.hash;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.crypto.digests.RIPEMD160Digest;
import org.bouncycastle.jcajce.provider.digest.RIPEMD160;

public class SHA {
	
	
	
	public static byte[] sha256(byte[] byt) {
		
		byte[] result = null;
		
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(byt);
			byte[] digest = md.digest();
			result = digest;
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static byte[] md160(byte[] byt) {
		
		RIPEMD160Digest d = new RIPEMD160Digest();
		
		d.update (byt, 0, byt.length);
        byte[] o = new byte[d.getDigestSize()];
        d.doFinal (o, 0);
        return o;
	}
	
	public static byte[] sha512(byte[] byt, String key) {
		
		Mac sha512Hmac;
		byte[] result = null;
        String HMAC_SHA512 = "HmacSHA512";

        try {
            final byte[] byteKey = key.getBytes();
            sha512Hmac = Mac.getInstance(HMAC_SHA512);
            SecretKeySpec keySpec = new SecretKeySpec(byteKey, HMAC_SHA512);
            sha512Hmac.init(keySpec);
            byte[] macData = sha512Hmac.doFinal(byt);

            // Can either base64 encode or put it right into hex
            result = macData;
            //result = bytesToHex(macData);
        } catch (InvalidKeyException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        } finally {
        }
        
        return result;
	}
	
	public static byte[] sha512(byte[] byt, byte[] key) {
		
		Mac sha512Hmac;
		byte[] result = null;
        String HMAC_SHA512 = "HmacSHA512";

        try {
            final byte[] byteKey = key;
            sha512Hmac = Mac.getInstance(HMAC_SHA512);
            SecretKeySpec keySpec = new SecretKeySpec(byteKey, HMAC_SHA512);
            sha512Hmac.init(keySpec);
            byte[] macData = sha512Hmac.doFinal(byt);

            // Can either base64 encode or put it right into hex
            result = macData;
            //result = bytesToHex(macData);
        } catch (InvalidKeyException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        } finally {
            // Put any cleanup here
            System.out.println("Done");
        }
        
        return result;
	}
	
	
	
	

}



