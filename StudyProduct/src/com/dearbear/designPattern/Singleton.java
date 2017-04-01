package com.dearbear.designPattern;import java.util.concurrent.SynchronousQueue;

//��������ģʽ(��̬����,����������ʱ�������)
public class Singleton {
	private static Singleton mSingleton = new Singleton();
	private Singleton(){}
	
	public static Singleton newInstance(){
		return mSingleton;
	}
	public static void main(String[] args){
		Singleton3 sing1 = Singleton3.newInstance();
		Singleton3 sing2 = Singleton3.newInstance();
		System.out.println(sing1 == sing2);
	}
}

//��������ģʽ(�ڲ�����,��Ҫ��ʱ��������)����volatile ���ڷ�ֹDCLʧЧ
class Singleton2{
	private volatile static Singleton2 mSingleton;
	private Singleton2(){
	}
	
	public static Singleton2 newInstance(){
		if (mSingleton == null) {			
			synchronized(Singleton2.class){
				if (mSingleton == null) {
					mSingleton = new Singleton2();
				}
			}
		}
		return mSingleton;			
	}
}

//��������ģʽ,��̬�ڲ���ʵ��
//	������classloader�Ļ�������֤��ʼ��instanceʱֻ��һ���̣߳�
//	����Ҳ���̰߳�ȫ�ģ�ͬʱû��������ģ�����һ����������ʹ����һ��
class Singleton3{
	private Singleton3() {
		// TODO Auto-generated constructor stub
	}
	private static class InClass{
		static Singleton3 mInstance = new Singleton3();
	}
	
	public static Singleton3 newInstance(){
		return InClass.mInstance;
	}
}
