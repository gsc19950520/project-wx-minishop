package com.shop.util;

import com.alibaba.fastjson.JSONObject;
import com.shop.config.Constant;
import com.shop.util.crypt.aes.AES;
import com.shop.util.crypt.aes.WxPKCS7Encoder;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class WxUtils {

    private static String token = Constant.token; // 定义Token 务必与服务器保持一致

    /**
     * 验证签名
     *
     * @param signature
     * @param timestamp
     * @param nonce
     * @return
     */
    public static boolean checkSignature(String signature, String timestamp,
                                         String nonce) {

        // 将token、timestamp、nonce三个参数进行字典排序  
        String[] arr = new String[]{token, timestamp, nonce};
        Arrays.sort(arr);

        // 将三个参数字符串拼接成一个字符串
        StringBuilder content = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            content.append(arr[i]);
        }
        try {
            //获取加密工具
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            // 对拼接好的字符串进行sha1加密
            byte[] digest = md.digest(content.toString().getBytes());
            String tmpStr = byteToStr(digest);
            //获得加密后的字符串与signature对比
            return tmpStr != null ? tmpStr.equals(signature.toUpperCase()) : false;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return false;
    }

    private static String byteToStr(byte[] byteArray) {
        String strDigest = "";
        for (int i = 0; i < byteArray.length; i++) {
            strDigest += byteToHexStr(byteArray[i]);
        }
        return strDigest;
    }

    private static String byteToHexStr(byte mByte) {
        char[] Digit = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A',
                'B', 'C', 'D', 'E', 'F'};
        char[] tempArr = new char[2];
        tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
        tempArr[1] = Digit[mByte & 0X0F];
        String s = new String(tempArr);
        return s;
    }

    /**
     * 解密数据
     *
     * @return
     * @throws Exception
     */
    public static String decrypt(String encryptedData, String sessionKey, String iv) {
        String result = "";
        try {
            AES aes = new AES();
            byte[] resultByte = aes.decrypt(Base64.decodeBase64(encryptedData), Base64.decodeBase64(sessionKey), Base64.decodeBase64(iv));
            if (null != resultByte && resultByte.length > 0) {
                result = new String(WxPKCS7Encoder.decode(resultByte));
                JSONObject jsonObject = JSONObject.parseObject(result);
                String decryptAppid = jsonObject.getJSONObject("watermark").getString("appid");
                if (!Constant.appId.equals(decryptAppid)) {
                    result = "";
                } else {
                    JSONObject jsonObject2 = JSONObject.parseObject(result);
                    String phoneNumber = jsonObject2.getString("phoneNumber");
                    return phoneNumber;
                }
            }
        } catch (Exception e) {
            result = "";
            e.printStackTrace();
        }
        return result;
    }


    public static String getSig(Map<String, Object> map, String key) throws Exception {
        List<String> list = new ArrayList<>();
        for (String mapKey : map.keySet()) {
            list.add(mapKey + "=" + map.get(mapKey));
        }
        Collections.sort(list);
        StringBuilder content = new StringBuilder();
        for (String s : list) {
            content.append(s).append("&");
        }
        String out = content.substring(0, content.length() - 1);
        return hmacSha256(out, key);
    }

    public static void main(String[] args) throws Exception {

        Map<String, Object> map = new HashMap<>();
        map.put("buyer_corpid", "ww66302cfadbdd3c64");
        map.put("buyer_userid", "invitetest");
        map.put("nonce_str", "129031823");
        map.put("num", "3");
        map.put("orderid", "ord7");
        map.put("product_detail", "product_detail_xxx");
        map.put("product_id", "product_id_xxx");
        map.put("product_name", "product_name_xxx");
        map.put("ts", "1548302135");
        map.put("unit_name", "台");
        map.put("unit_price", 1);

        String secret = "at23pxnPBNQY3JiA8N5U1gabiQqxZwqH_Gihg7a_wrULmlOPVP-iiRjv9JWYPrDk";
        System.out.println(getSig(map, secret));
    }

    private static String hmacSha256(String message, String secret) throws Exception {
        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
        sha256_HMAC.init(secret_key);

        String hash = Base64.encodeBase64String(sha256_HMAC.doFinal(message.getBytes()));
        return hash;
    }
}
