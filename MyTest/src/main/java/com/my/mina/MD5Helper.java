package com.my.mina;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * MD5 帮助类
 *
 * @author huangjk
 * @date 2013-2-6
 */
public class MD5Helper {

    private static final String TAG = "MD5Helper";

    /**
     * 16进制字符集
     */
    private static final char HEX_DIGITS[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    /**
     * 获取单个文件的MD5值
     *
     * @param file 需要获取MD5 的文件的实例
     * @return 该文件的MD5值
     */
    public static String getFileMD5(File file) {
        String ret = "";
        FileInputStream in = null;
        FileChannel ch = null;
        try {
            in = new FileInputStream(file);
            ch = in.getChannel();
//            Log.d(TAG, "in.getChannel()");
            ByteBuffer byteBuffer = ch.map(FileChannel.MapMode.READ_ONLY, 0, file.length());
//            Log.d(TAG, "ch.map(FileChannel.MapMode.READ_ONLY, 0, file.length())");
            MessageDigest messageDigest = null;
            try {
                messageDigest = MessageDigest.getInstance("MD5");
                messageDigest.update(byteBuffer);
//                Log.d(TAG, "mMessageDigest.update(byteBuffer)");
                ret = bytesToHex(messageDigest.digest());
//                Log.d(TAG, "bytesToHex(mMessageDigest.digest())");
            } catch (NoSuchAlgorithmException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
//            Log.e(TAG, "getFileMD5 IOException e: " + e.toString());
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (ch != null) {
                try {
                    ch.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return ret.toLowerCase();
    }

    /**
     * @param inputStream
     * @return
     * @throws
     * @Title getLargeFileMD5
     * @Description TODO 通过文件流获取文件的MD5值
     */
    public static String getFileMD5(InputStream inputStream) {
        String res = "";
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            byte[] buffer = new byte[2048];
            int length;

            while ((length = inputStream.read(buffer)) != -1) {
                messageDigest.update(buffer, 0, length);
            }
            res = new String(encodeHex(messageDigest.digest(), HEX_DIGITS));
        } catch (IOException e) {
//            Log.e(TAG, "getLargeFileMD5 IOException e: " + e.toString());
        } catch (NoSuchAlgorithmException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return res;
    }

    /**
     * 获取大文件的MD5值
     *
     * @param file
     * @return
     */
    public static String getLargeFileMD5(File file) {
        String md5 = null;
        try {
            md5 = getFileMD5(new FileInputStream(file));
        } catch (FileNotFoundException e) {
//            Log.e(TAG, "getLargeFileMD5 FileNotFoundException e: " + e.toString());
            md5 = "";
        }
        return md5;
    }

    /**
     * @param data
     * @param toDigits
     * @return
     * @throws
     * @Title encodeHex
     * @Description TODO(这里用一句话描述这个方法的作用)
     */
    private static char[] encodeHex(final byte[] data, final char[] toDigits) {
        final int length = data.length;
        final char[] out = new char[length << 1];
        for (int i = 0, j = 0; i < length; i++) {
            out[j++] = toDigits[(0xF0 & data[i]) >>> 4];
            out[j++] = toDigits[0x0F & data[i]];
        }
        return out;
    }

    /**
     *
     * @Title getDirMD5
     * @Description TODO 获取文件夹中文件的MD5值
     * @param file
     * @param listChild
     * @return
     * @throws
     */

    /**
     * 获取文件夹中文件的MD5值
     *
     * @param file
     * @param listChild
     * @return
     */
    public static Map<String, String> getDirMD5(File file, boolean listChild) {
        if (!file.isDirectory()) {
            return null;
        }
        Map<String, String> map = new HashMap<String, String>();
        String md5;
        File files[] = file.listFiles();
        for (int i = 0; i < files.length; i++) {
            File f = files[i];
            if (f.isDirectory() && listChild) {
                map.putAll(getDirMD5(f, listChild));
            } else {
                md5 = getFileMD5(f);
                if (md5 != null) {
                    map.put(f.getPath(), md5);
                }
            }
        }
        return map;
    }

    /**
     * 将字符串进行MD5加密
     *
     * @param val 要加密的字符串
     * @return 返回使用MD5加密的字符串(长度为32位的小写16进制串)
     * @throws NoSuchAlgorithmException
     */
    public static String getMD5(String val) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = null;
        StringBuffer md5StrBuff = new StringBuffer();
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(val.getBytes());
            // MD5 的计算结果是一个 128 位的长整数，
            // 用字节表示就是 16 个字节
            byte tmp[] = messageDigest.digest();
            for (int i = 0; i < tmp.length; i++) {
                if (Integer.toHexString(0xFF & tmp[i]).length() == 1) {
                    md5StrBuff.append("0").append(Integer.toHexString(0xFF & tmp[i]));
                } else {
                    md5StrBuff.append(Integer.toHexString(0xFF & tmp[i]));
                }
            }
        } catch (NoSuchAlgorithmException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return md5StrBuff.toString();
    }

    /**
     * 获取文件的MD5值
     *
     * @param fileName 目标文件的完整名称
     * @return MD5字符串
     */
    public static String getFileMD5String(String fileName) {
        return getFileMD5(new File(fileName));
    }

    /**
     * MD5加密字符串
     *
     * @param str 目标字符串
     * @return MD5加密后的字符串
     */
    public static String getMD5String(String str) {
        return getMD5String(str.getBytes());
    }

    /**
     * MD5加密以byte数组表示的字符串
     *
     * @param bytes 目标byte数组
     * @return MD5加密后的字符串
     */
    public static String getMD5String(byte[] bytes) {
        MessageDigest messageDigest = null;
        String res = "";
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(bytes);
            res = bytesToHex(messageDigest.digest());
        } catch (NoSuchAlgorithmException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return res;
    }

    /**
     * * 校验密码与其MD5是否一致
     *
     * @param pwd 密码字符串
     * @param md5 基准MD5值
     * @return 检验结果
     */
    public static boolean checkPassword(String pwd, String md5) {
        return getMD5String(pwd).equalsIgnoreCase(md5);
    }

    /**
     * * 校验密码与其MD5是否一致
     *
     * @param pwd 以字符数组表示的密码
     * @param md5 基准MD5值
     * @return 检验结果
     */
    public static boolean checkPassword(char[] pwd, String md5) {
        return checkPassword(new String(pwd), md5);
    }

    /**
     * * 检验文件的MD5值
     *
     * @param file 目标文件
     * @param md5  基准MD5值
     * @return 检验结果
     */
    public static boolean checkFileMD5(File file, String md5) {
        return getFileMD5(file).equalsIgnoreCase(md5);
    }

    /**
     * * 检验文件的MD5值
     *
     * @param fileName 目标文件的完整名称
     * @param md5      基准MD5值
     * @return 检验结果
     */
    public static boolean checkFileMD5(String fileName, String md5) {
        return checkFileMD5(new File(fileName), md5);
    }

    /**
     * * 将字节数组转换成16进制字符串
     *
     * @param bytes 目标字节数组
     * @return 转换结果
     */
    public static String bytesToHex(byte bytes[]) {
        return bytesToHex(bytes, 0, bytes.length);
    }

    /**
     * * 将字节数组中指定区间的子数组转换成16进制字符串
     *
     * @param bytes 目标字节数组
     * @param start 起始位置（包括该位置）
     * @param end   结束位置（不包括该位置）
     * @return 转换结果
     */
    public static String bytesToHex(byte bytes[], int start, int end) {
        StringBuilder sb = new StringBuilder();
        for (int i = start; i < start + end; i++) {
            sb.append(byteToHex(bytes[i]));
        }
        return sb.toString();

    }

    /**
     * * 将单个字节码转换成16进制字符串
     *
     * @param bt 目标字节
     * @return 转换结果
     */
    public static String byteToHex(byte bt) {
        return HEX_DIGITS[(bt & 0xf0) >> 4] + "" + HEX_DIGITS[bt & 0xf];
    }
}
