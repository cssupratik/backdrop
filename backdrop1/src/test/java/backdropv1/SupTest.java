package backdropv1;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Base64;
import java.util.Optional;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class SupTest {

	private static final int ITERATIONS = 65536;
	private static final int KEY_LENGTH = 512;
	private static final String ALGORITHM = "PBKDF2WithHmacSHA512";


	  public static Optional<String> hashPassword (String password, String salt) {

	    char[] chars = password.toCharArray();
	    byte[] bytes = salt.getBytes();

	    PBEKeySpec spec = new PBEKeySpec(chars, bytes, ITERATIONS, KEY_LENGTH);
	    //Clear out the chars array with the null character
	    Arrays.fill(chars, Character.MIN_VALUE);

	    try {
	      SecretKeyFactory fac = SecretKeyFactory.getInstance(ALGORITHM);
	      byte[] securePassword = fac.generateSecret(spec).getEncoded();
	      System.out.println(securePassword);
	      return Optional.of(Base64.getEncoder().encodeToString(securePassword));

	    } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
	      System.err.println("Exception encountered in hashPassword()");
	      return Optional.empty();

	    } finally {
	      spec.clearPassword();
	    }
	  }
	public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException {
		// TODO Auto-generated method stub
		String password = "supra";
		String salty = "f@#k";
		String password2 = "supra";
		Optional<String> epwd = SupTest.hashPassword(password, salty);
		Optional<String> epwd2 = SupTest.hashPassword(password2, salty);
		System.out.println(epwd.get());
		System.out.println(epwd.get().length());
		System.out.println(epwd2.get());
		System.out.println(epwd2.get().length());
		System.out.println(epwd.get().equals(epwd2.get()));
	}

}
