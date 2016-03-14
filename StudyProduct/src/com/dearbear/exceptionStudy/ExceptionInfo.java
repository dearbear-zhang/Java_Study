package com.dearbear.exceptionStudy;

public class ExceptionInfo {
	public static void main(String args[]) {
		ExceptionInfo eTest = new ExceptionInfo();
		try {
			eTest.cal(-1, -1);			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		try {
			int i = Integer.parseInt("22l");
			System.out.println(i);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
//			throw e;
		}
	}
	public void	cal(int x, int y)throws MyException {
		if (x < 0 || y < 0) {
			throw new MyException("不可使用负数");
		}
	}
}

class MyException extends Exception{
	String m_message;
	public MyException(String ErrorMessage){
//		super(ErrorMessage);
		m_message = ErrorMessage;
	}
	@Override
	public String getMessage(){
		return m_message;
	}
}