package today.smarthealthcare.myhealth.utils;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import com.google.common.io.BaseEncoding;

import java.util.Random;

public class PasswordUtils {
	private static final Random RANDOM = new Random();

	public static String generateSalt() {
		byte[] buffer = new byte[7];
		RANDOM.nextBytes(buffer);
		return BaseEncoding.base64Url().omitPadding().encode(buffer);
	}

	public static String getHash(String salt, String raw) {
		return  Hashing.sha1().hashString(String.format("%s%s", salt, raw), Charsets.UTF_8).toString();
	}
}
