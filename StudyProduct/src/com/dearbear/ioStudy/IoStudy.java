package com.dearbear.ioStudy;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Array;
import java.util.Arrays;

public class IoStudy {
	public static void main(String []args) {
//		StreamIOTest byteIO = new StreamIOTest();
//		byteIO.fileWrite();
//		byteIO.fileRead();
		CharIOTest byteIO = new CharIOTest();
		byteIO.fileWrite();
		byteIO.fileRead();
		//InputStream��OutputStreamϵ�в�֧�������ȡ,�����ȡҪ��RandomAccessFile,��seekʵ��
	}
	
}

class StreamIOTest{
	static final String fileName = "BytesIoTest.dat";
	void fileWrite(){
		String Tag = "Java IO Test";
		try {
			File file = new File(fileName);
			if (!file.exists()) {
				file.createNewFile();				
			}
			FileWriter fWriter = new FileWriter(file);
			BufferedWriter buffWriter = new BufferedWriter(fWriter);
			buffWriter.write(Tag);
			buffWriter.newLine();
			buffWriter.write(Tag);
			buffWriter.newLine();
			buffWriter.write(Tag);
			buffWriter.newLine();
			buffWriter.close();
			System.out.println("IO�ļ��洢�ɹ�");
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
	}
	void fileRead(){
		File file = new File(fileName);
		if (!file.exists()) {
			System.out.println("IO�ļ����洢�ڶ�ȡ����");
			return;
		}
		try {
			BufferedReader buffReader = new BufferedReader(new FileReader(file));
			String line = buffReader.readLine();
			while(line != null) {
				System.out.println(line);				
				line = buffReader.readLine();
			}
			buffReader.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getStackTrace());
		}
	}
}

class CharIOTest{
	static final String fileName = "StringIOTest.dat";
	void fileWrite(){
		byte[] Tag = new byte[]{11,23,43,66,65};
		try {
			File file = new File(fileName);
			if (!file.exists()) {
				file.createNewFile();				
			}
			BufferedOutputStream bfOut= new BufferedOutputStream(new FileOutputStream(file));
			bfOut.write(Tag);
			System.out.println("Char IO�ļ��洢�ɹ�");
			bfOut.close();
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
	}	
	void fileRead(){
		File file = new File(fileName);
		byte[] buff = new byte[10];
		if (!file.exists()) {
			System.out.println("IO�ļ����洢�ڶ�ȡ����");
			return;
		}
		try {
			BufferedInputStream bfIn = new BufferedInputStream(new FileInputStream(file));
			while(bfIn.read(buff, 0, 1) != -1){
				System.out.println(Arrays.toString(buff));
			}
			bfIn.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getStackTrace());
		}
	}
}