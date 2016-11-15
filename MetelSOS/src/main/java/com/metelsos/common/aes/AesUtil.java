package com.metelsos.common.aes;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * 
* <pre>
* com.metelsos.common.aes
*   |_ AesUtil.java
* </pre>
* 
* Desc : 암호화 유틸 클래스
* @Author  : "Kim Kibeom"
* @Date    : 2016. 11. 14. 오후 2:52:11
* @Version :
 */
public class AesUtil {
	/** 암호화 키 16자리 */
	private String key = "fe8025947de7cd71";

    /**
     * 
     * Desc : 16진수 문자열을 바이트 배열로 변환한다.
     * @Method Name : hexToByteArray
     * @param hex - 16진수 문자열
     * @return byte[]
     */
    public byte[] hexToByteArray(String hex) {
    	
        if (hex == null || hex.length() == 0) {
            return null;
        }

        byte[] ba = new byte[hex.length() / 2];
        
        for (int i = 0; i < ba.length; i++) {
            ba[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        
        return ba;
    }

    /**
     * 
     * Desc : unsigned byte(바이트) 배열을 16진수 문자열로 바꾼다.
     * @Method Name : byteArrayToHex
     * @param ba - unsigned byte[]
     * @return 16진수 문자열
     */
    public String byteArrayToHex(byte[] ba) {
    	
        if (ba == null || ba.length == 0) {
            return null;
        }

        StringBuffer sb = new StringBuffer(ba.length * 2);
        String hexNumber;
        
        for (int x = 0; x < ba.length; x++) {
            hexNumber = "0" + Integer.toHexString(0xff & ba[x]);
            sb.append(hexNumber.substring(hexNumber.length() - 2));
        }
        
        return sb.toString();
    }

    /**
     * 
     * Desc : AES 방식의 암호화
     * @Method Name : encrypt
     * @param message - message 암호화 대상 문자열
     * @return String 암호화 된 문자열
     * @throws Exception
     */
    public String encrypt(String message) throws Exception {

        // use key coss2
        SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), "AES");

        // Instantiate the cipher
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);

        byte[] encrypted = cipher.doFinal(message.getBytes());
        
        return byteArrayToHex(encrypted);
    }
    
    /**
     * 
     * Desc : AES 방식의 복호화
     * @Method Name : decrypt
     * @param encrypted - message 복호화 대상 문자열
     * @return String 복호화 된 문자열
     * @throws Exception
     */
    public String decrypt(String encrypted) throws Exception {

        // use key coss2
        SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), "AES");

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, skeySpec);
        
        byte[] original = cipher.doFinal(hexToByteArray(encrypted));
        
        String originalString = new String(original);
        
        return originalString;
    }
}
