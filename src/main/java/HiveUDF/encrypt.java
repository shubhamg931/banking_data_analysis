package HiveUDF;

import org.apache.commons.codec.binary.Base64;
import org.apache.hadoop.hive.ql.exec.UDF;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class encrypt extends UDF {

    public String evaluate(String input) {
        try {
            byte[] password = new String("abcdefghijklmnol").getBytes();
            // The password need to be 8, 16, 32 or 64 characters long to be used in AES encryption
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            SecretKeySpec keySpec = new SecretKeySpec(password, "AES");
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);
            String output = Base64.encodeBase64String(cipher.doFinal(input.getBytes()));

            return output;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}