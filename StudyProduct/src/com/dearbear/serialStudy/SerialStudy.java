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
		Student st = new Student("С��",1,19);
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
			System.out.println("���л��ļ��洢�ɹ�");
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
				System.out.printf("���л��ļ�%s������\n", FILENAME);
			}
			ObjectInputStream objectReader = new ObjectInputStream(new FileInputStream(fp));
			st = (Student)objectReader.readObject();
			System.out.printf("���л��ļ���ȡ�ɹ�:%s\n", st.toString());
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