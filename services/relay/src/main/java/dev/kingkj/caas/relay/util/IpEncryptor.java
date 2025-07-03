package dev.kingkj.caas.relay.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class IpEncryptor {

    /**
     * SHA-256 알고리즘을 이용해 Client의 IP를 단방향으로 암호화한다.
     * @param ip client ip
     * @return encrypted ip
     */
    public static String encrypt(final String ip) {
        try {
            MessageDigest sh = MessageDigest.getInstance("SHA-256");
            sh.update(ip.getBytes());
            byte[] byteData = sh.digest();

            StringBuilder sb = new StringBuilder();
            for (byte b : byteData) {
                sb.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * IP와 암호화된 IP의 동일여부를 확인한다.
     *
     * @param ip client ip
     * @param encrypted encrypted ip
     * @return does ip equals encrypted
     */
    public static boolean match(final String ip, final String encrypted) {
        return encrypt(ip).equals(encrypted);
    }
}
