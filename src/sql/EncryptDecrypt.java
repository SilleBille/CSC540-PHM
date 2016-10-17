package sql;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

/**
 * Created by mkdin on 16-10-2016.
 */
public class EncryptDecrypt {

    private static final String ALGORITHM = "AES";
    private static final String KEY = "1Hbfh667adfDEJ78";


    public static String encrypt(String value) {
        try {
            Key key = generateKey();
            Cipher cipher = Cipher.getInstance(EncryptDecrypt.ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encryptedByteValue = cipher.doFinal(value.getBytes("utf-8"));
            String encryptedValue64 = new BASE64Encoder().encode(encryptedByteValue);
            return encryptedValue64;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String decrypt(String value) {
        try {
            Key key = generateKey();
            Cipher cipher = Cipher.getInstance(EncryptDecrypt.ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decryptedValue64 = new BASE64Decoder().decodeBuffer(value);
            byte[] decryptedByteValue = cipher.doFinal(decryptedValue64);
            String decryptedValue = new String(decryptedByteValue, "utf-8");
            return decryptedValue;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Key generateKey() {
        Key key = new SecretKeySpec(EncryptDecrypt.KEY.getBytes(), EncryptDecrypt.ALGORITHM);
        return key;
    }
}
