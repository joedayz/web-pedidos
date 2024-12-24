package pe.joedayz.training.java.web.app.pedidos.utilitario;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

import sun.misc.BASE64Encoder;

public class Encrypt {
  private static char[] psw;
  private static final byte[] SALT = {
      (byte) 0xde, (byte) 0x33, (byte) 0x10, (byte) 0x12,
      (byte) 0xde, (byte) 0x33, (byte) 0x10, (byte) 0x12,
  };

  public static void init(String str){
    psw=str.toCharArray();
  }

  public static String encrypt(String property)
      throws GeneralSecurityException, UnsupportedEncodingException {
    SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
    SecretKey key = keyFactory.generateSecret(new PBEKeySpec(psw));
    Cipher pbeCipher = Cipher.getInstance("PBEWithMD5AndDES");
    pbeCipher.init(Cipher.ENCRYPT_MODE, key, new PBEParameterSpec(SALT, 20));
    return base64Encode(pbeCipher.doFinal(property.getBytes("UTF-8")));
  }

  private static String base64Encode(byte[] bytes) {
    return new BASE64Encoder().encode(bytes);
  }

  public static void main(String[] args)
      throws GeneralSecurityException, UnsupportedEncodingException {
    String encrypted = encrypt("cumple");
    System.out.println(encrypted);
  }

}


