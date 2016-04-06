package com.dearbear.serialStudy;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.print.attribute.standard.MediaSize.NA;

public class SerialStudy {
	private static final String FILENAME = "SerialFile";
	public static void main(String[] args) {
		Student st = new Student("小明",1,19);
		Student	stRead = null;
		serialingSave(st);
		stRead = serialingRead();
		System.out.println(stRead.toString());
	}
	static void serialingSave(Student st){
		try {
			File fp = new File(FILENAME);
			if (fp.exists() == false) {
				fp.createNewFile();
			}
			ObjectOutputStream buffWrite = new ObjectOutputStream(new FileOutputStream(fp));
			buffWrite.writeObject(st);
			System.out.println("序列化文件存储成功");
			buffWrite.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getStackTrace());
		}
	}
	static Student serialingRead(){
		Student st = null;
		try {
			File fp = new File(FILENAME);
			if (fp.exists() == false) {
				System.out.printf("序列化文件%s不存在\n", FILENAME);
			}
			ObjectInputStream objectReader = new ObjectInputStream(new FileInputStream(fp));
			st = (Student)objectReader.readObject();
			System.out.printf("序列化文件读取成功:%s\n", st.toString());
		} catch (Exception e) {
			// TODO: handle exception
		}
		return st;
	}
}

class Student implements Serializable{
	private String mName;
	private int mId;
	private int mAge;
	public Student(){
		
	}
	public Student(String name, int id, int age){
		mName = name;
		mId = id;
		mAge = age;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return mName + "," + mId + "," + mAge;
	}
	
}