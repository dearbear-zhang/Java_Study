package com.dearbear.classStudy;
public class CClassInfoStudy extends Shap implements ChangeShp{
	final double PI = 3.141592653;
	public InClass m_inclass = new InClass();		
	public static void main(String args[]) {
		CClassInfoStudy classInfoStudy = new CClassInfoStudy();
		CClassInfoStudy.InClass inClassInfo = classInfoStudy.new InClass();
		System.out.println("hello");
		System.out.println(classInfoStudy.m_inclass.m_x);
		OutClass outClass = new OutClass();
		OutInterfac outDiot = outClass.diot();
		outDiot.f();		
	}
	
	@Override 
	public void draw(){
		System.out.println("override draw now");
	}
	
	@Override 
	public void change(){
		System.out.println("override change now");
	}
	public class InClass{
		public int m_x = 2, m_y = 0;
		private void test() {
			double length = PI * 2;
		}
	}
}

abstract class Shap{
	int m_x, m_y;
	abstract void draw();
}

interface ChangeShp{
	void change();
}

//定义对外接口
interface OutInterfac{
	void f();
}

class OutClass{
	//定义一个内部类实现OutInterfac接口
	private class ClassInner implements OutInterfac{
		//实现接口中的f();
		@Override
		public void f(){
			System.out.println("内部私有类重载接口实现");
		}
	}
	//定义一个方法,对外返回 类型值为OutInterfac的接口
	public OutInterfac diot() {
		return new ClassInner();
	}
}

class Test{
	private int m_x = 0, m_y = 0;
}