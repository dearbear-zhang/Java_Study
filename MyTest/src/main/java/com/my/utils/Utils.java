package com.my.utils;

import com.google.gson.*;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Utils {
    private static final String TAG = Utils.class.getName();
    public static final int ACCESSALARM = 1;
    private final static String sAES = "AES";
    private final static String sAesInfo = "AES/CBC/PKCS5Padding";                                    // 算法/模型/补码方式
    private final static String sDATE_FORMATE = "yyyy-MM-dd HH:mm:ss";                            // 日起固定格式
    private final static String sDATE_FORMATE_yyyy_MM_dd = "yyyy-MM-dd 00:00:00";                // 日起固定格式

    //	http body res 加密参数
    private final static byte[] sBodyAesKey = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private final static byte[] sBodyAesIv = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    //  http header res 加密参数
    private final static byte[] sHeaderAesKey = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private final static byte[] sHeaderAesIv = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /***
     * 字符串时间格式 ==> 时间戳
     * @param s
     * @return
     * @throws ParseException
     */
    public static long dateStringToStamp(String s) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(sDATE_FORMATE);
        Date date = simpleDateFormat.parse(s);
        return date.getTime();
    }

    /***
     * 时间戳 ==> 字符串时间格式
     * @param time
     * @return
     */
    public static String stampToDateString(long time) {
        Date date = new Date(time);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(sDATE_FORMATE);
        return simpleDateFormat.format(date);
    }

    public static String stampToDateString_yyyymmdd(long time) {
        Date date = new Date(time);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(sDATE_FORMATE_yyyy_MM_dd);
        return simpleDateFormat.format(date);
    }

    /***
     * 获取当前系统时间, 以 yyyy-MM-dd HH:mm:ss 格式输出
     * @return
     */
    public static String getCurrentDateFormat() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat(sDATE_FORMATE);
        return format.format(date);
    }

    /***
     * 网络请求Header加密
     * @param text
     * @return
     */
    public static String resEncryptToHeader(String text) {
        return resEncrypt(text, sHeaderAesKey, sHeaderAesIv, "utf-8");
    }

    /***
     * 网络请求Body加密
     * @param text
     * @return
     */
    public static String resEncryptToBody(String text) {
        return resEncrypt(text, sBodyAesKey, sBodyAesIv, "utf-8");
    }

    /***
     * 网络请求header解密
     * @param decryted
     * @return
     */
    public static String resDecryptFromHeader(String decryted) {
        return resDecrypt(decryted, sHeaderAesKey, sHeaderAesIv, "utf-8");
    }

    /***
     * 网络请求Body解密
     * @param decryted
     * @return
     */
    public static String resDecryptFromBody(String decryted) {
        return resDecrypt(decryted, sBodyAesKey, sBodyAesIv, "utf-8");
    }

    /***
     * 对text进行	RES + Base64 加密
     * @param text
     * @param key
     * @param iv
     * @return
     */
    public static String resEncrypt(String text, byte[] key, byte[] iv, String encodingFormat) {
        if (text == null || text.length() == 0) {
            return text;
        }
        try {
            byte[] result = encrypt(text, key, iv, encodingFormat);
            String encrypt = Base64.getEncoder().encodeToString(result);
            return encrypt;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /***
     * 字符串加密	加密方式:	RES/CBC/PKCS5Padding
     * @param text
     * @param key
     * @param iv
     * @return
     * @throws Exception
     */
    private static byte[] encrypt(String text, byte[] key, byte[] iv, String encodingFormat) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, sAES);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
        Cipher cipher = Cipher.getInstance(sAesInfo);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
        byte[] encrypted = cipher.doFinal(text.getBytes(encodingFormat));
        return encrypted;
    }

    /***
     * 对encrypted进行	Base64 + RES 解密
     * @param encrypted
     * @param key
     * @param iv
     * @return
     */
    public static String resDecrypt(String encrypted, byte[] key, byte[] iv, String encodingFormat) {
        if (encrypted == null || encrypted.length() == 0) {
            return encrypted;
        }
        try {
            byte[] enc = Base64.getDecoder().decode(encrypted);
            byte[] result = decrypt(enc, key, iv);
            String originalString = new String(result, encodingFormat);
            return originalString;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /***
     * 字符串解密	解密方式:	RES/CBC/PKCS5Padding
     * @param encrypted
     * @param key
     * @param iv
     * @return
     * @throws Exception
     */
    private static byte[] decrypt(byte[] encrypted, byte[] key, byte[] iv) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, sAES);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
        Cipher cipher = Cipher.getInstance(sAesInfo);
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
        return cipher.doFinal(encrypted);
    }

    /***
     * 字符串MD5加密
     * @param msg
     * @return
     */
    public static String md5(String msg) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(msg.getBytes());
            String result = "";
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result += temp;
            }
            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    private static String fillMD5(String md5) {
        return md5.length() == 32 ? md5 : fillMD5("0" + md5);
    }

    /***
     * json格式Gson解析
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T parsonJson(String json, Class<T> clazz) {
//		Gson gson = new Gson();
        Gson gson = new GsonBuilder().registerTypeHierarchyAdapter(List.class, new JsonDeserializer<List<?>>() {
            @Override
            public List<?> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                if (json.isJsonArray()) {
                    JsonArray array = json.getAsJsonArray();
                    Type itemType = ((ParameterizedType) typeOfT).getActualTypeArguments()[0];
                    List list = new ArrayList<>();
                    for (int i = 0; i < array.size(); i++) {
                        JsonElement element = array.get(i);
                        Object item = context.deserialize(element, itemType);
                        list.add(item);
                    }
                    return list;
                } else {
                    //和接口类型不符，返回空List
                    return Collections.EMPTY_LIST;
                }
            }
        }).create();
        return gson.fromJson(json, clazz);
    }
}
