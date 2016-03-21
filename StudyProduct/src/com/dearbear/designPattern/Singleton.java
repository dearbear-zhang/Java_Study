package com.dearbear.designPattern;import java.util.concurrent.SynchronousQueue;

//饿汉单例模式(静态对象,虚拟机载入的时候就生成)
public class Singleton {
	private static Singleton mSingleton = new Singleton();
	private Singleton(){
		
	};
	
	public static Singleton newInstance(){
		return mSingleton;
	}
	public static void main(String[] args){
		Singleton sing1 = newInstance();
		Singleton sing2 = newInstance();
		System.out.println(sing1 == sing2);
	}
}

//懒汉单例模式(内部对象,需要的时候再生成)
class Singleton2{
	private static Singleton2 mSingleton;
	private Singleton2(){
	}
	
	public static synchronized Singleton2 newInstance(){
		if (mSingleton == null) {
			mSingleton = new Singleton2();
		}
		return mSingleton;
	}
}