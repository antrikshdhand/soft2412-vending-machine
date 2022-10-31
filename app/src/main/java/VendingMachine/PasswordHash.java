package VendingMachine;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

public class PasswordHash {

   public static byte[] createSalt() throws NoSuchAlgorithmException{
       SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
       byte[] salt = new byte[16];
       secureRandom.nextBytes(salt);
       return salt;

   }

   public static byte[] getSaltedHashSHA512(String password, byte[] salt) {
       try {
           MessageDigest md = MessageDigest.getInstance("SHA512");
           md.update(salt);
           byte byteData[] = md.digest(password.getBytes());
           md.reset();
           return byteData;

       } catch (NoSuchAlgorithmException NSAE) {
           NSAE.printStackTrace();
           return null;

       }

   }


   public static byte[] fromHex(String hex) {
       byte[] binary = new byte[hex.length() / 2];

       for(int i = 0; i < binary.length; i++) {
           binary[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
       }

       return binary;
   }


   public static String toHex(byte[] array) {
       BigInteger bigInt = new BigInteger(1, array);
       String hex = bigInt.toString(16);

       int paddingLength = (array.length * 2) - hex.length();

       if(paddingLength > 0) {
           return String.format("%0" + paddingLength + "d", 0) + hex;

       } else {
           return hex;
       }



   }

   public static Password createHashedPassword(String password) {

       byte salt[] = null;

       try {
           salt = createSalt();

       } catch (NoSuchAlgorithmException NSAE) {
           NSAE.printStackTrace();

       }

       byte[] byteDigestPassword = getSaltedHashSHA512(password, salt);


       String strHashPassword = toHex(byteDigestPassword);
       String strSalt = toHex(salt);

       Password pw = new Password(strSalt, strHashPassword);

       return pw;

   }

   public static boolean isValidUser(String salt, String hashPassword, String enteredPassword) {
       byte[] byteSalt = fromHex(salt);

       byte[] byteEnteredPassword = getSaltedHashSHA512(enteredPassword, byteSalt);
       byte[] byteHashPassword = fromHex(hashPassword);

       return Arrays.equals(byteHashPassword, byteEnteredPassword);

   }

    public static void main(String[] argv) {
        Password pass = createHashedPassword("pass");
        System.out.println(isValidUser(pass.strSalt(), pass.hashPassword(), "sdf"));


    }

}

record Password(String strSalt, String hashPassword) {

    public Password(String strSalt, String hashPassword) {
        this.strSalt = strSalt;
        this.hashPassword = hashPassword;
    }
}
