package com.dearbear.designPattern;import java.util.concurrent.SynchronousQueue;

//饿汉单例模式(静态对象,虚拟机载入的时候就生成)
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

//懒汉单例模式(内部对象,需要的时候再生成)加入volatile 用于防止DCL失效
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

//懒汉单例模式,静态内部类实现
//	利用了classloader的机制来保证初始化instance时只有一个线程，
//	所以也是线程安全的，同时没有性能损耗，所以一般我倾向于使用这一种
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
