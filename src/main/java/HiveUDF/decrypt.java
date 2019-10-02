package HiveUDF;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;
import org.apache.hadoop.hive.ql.exec.UDF;

public class decrypt extends UDF {

    public String evaluate(String input) {
        try {
            byte[] password = new String("abcdefghijklmnol").getBytes();
            // The password need to be 8, 16, 32 or 64 characters long to be used in AES encryption. It should also be the same which is used while encryption.
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");

            SecretKeySpec keySpec = new SecretKeySpec(password, "AES");
            cipher.init(Cipher.DECRYPT_MODE, keySpec);
            String output = new String(cipher.doFinal(Base64.decodeBase64(input)));
            return output.trim();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}