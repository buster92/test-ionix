package com.andresgarrido.testionix.util;

import android.util.Base64;

import androidx.annotation.Nullable;

import com.andresgarrido.testionix.BuildConfig;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class Utils {

	/**
	 * Encodes a string using a secured key and using a DES algorithm
	 * @param input string to encode
	 * @return input encoded
	 */
	@Nullable
	public static String encodeString(String input) {
		try {
			DESKeySpec keySpec = new DESKeySpec(BuildConfig.IONIX_KEY.getBytes(StandardCharsets.UTF_8));
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			byte[] cleartext = input.getBytes(StandardCharsets.UTF_8);

			SecretKey securekey = keyFactory.generateSecret(keySpec);
			SecureRandom sr = new SecureRandom();
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);

			return Base64.encodeToString(cipher.doFinal(cleartext), Base64.DEFAULT);
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | BadPaddingException |
				IllegalBlockSizeException | InvalidKeySpecException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static byte[] toByte(String string){
		return string.getBytes(Charset.forName("ISO-8859-1"));
	}
}
