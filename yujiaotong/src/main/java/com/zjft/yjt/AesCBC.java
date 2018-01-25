package com.zjft.yjt;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.tomcat.util.codec.binary.Base64;

/**
 *
 *AES  CBC  加密
 * @Package: com.zjft.yjt
 * @author liuming
 * @date 2018年1月25日
 *
 */
public class AesCBC {
	/*已确认
	* 加密用的Key 可以用26个字母和数字组成
	* 此处使用AES-128-CBC加密模式，key需要为16位。
	*/
//	    private static String sKey="1234567890123456";
//	    private static String ivParameter="1234567890123456";
	    private static AesCBC instance=null;
	    //private static 
	    private AesCBC(){

	    }
	    public static AesCBC getInstance(){
	        if (instance==null)
	            instance= new AesCBC();
	        return instance;
	    }
	    // 加密
	    /**	aes 128加密    base64 32位加密
	     * pram：
	     * 		sSrc 加密字符串
	     * 		encodingFormat 字体
	     * 		sKey  		密钥
	     * 		ivParameter	向量
	     * return ： 加密值
	     */
	    public String encrypt(String sSrc, String encodingFormat, String sKey, String ivParameter) throws Exception {
	        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
	        byte[] raw = sKey.getBytes();
	        
	        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
	        IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());//使用CBC模式，需要一个向量iv，可增加加密算法的强度
	        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
	        byte[] encrypted = cipher.doFinal(sSrc.getBytes(encodingFormat));
	        
	        return encodeStr(encrypted);
//	        return new BASE64Encoder().encode(encrypted);//此处使用BASE64做转码。
	}

	    // 解密
//	    public String decrypt(String sSrc, String encodingFormat, String sKey, String ivParameter) throws Exception {
//	        try {
//	            byte[] raw = sKey.getBytes("ASCII");
//	            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
//	            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
//	            IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
//	            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
//	            byte[] encrypted1 = new BASE64Decoder().decodeBuffer(sSrc);//先用base64解密
//	            byte[] original = cipher.doFinal(encrypted1);
//	            String originalString = new String(original,encodingFormat);
//	            return originalString;
//	        } catch (Exception ex) {
//	            return null;
//	        }
//	}
   
	    /* 
	     * 创建日期2011-4-25上午10:12:38
	     * 修改日期
	     * 作者：dh *TODO 使用Base64加密算法加密字符串
	     *return
	     */
	    public static String encodeStr(byte[] encrypted){
	        byte[] b=encrypted;
	        Base64 base64=new Base64();
	       
	        b=base64.encode(b);
	        String s=new String(b);
	        return  s;
	    }
	    

}
