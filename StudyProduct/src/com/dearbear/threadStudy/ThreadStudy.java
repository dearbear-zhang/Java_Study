package com.dearbear.threadStudy;

public class ThreadStudy {
	
	private volatile static int Count = 0;
	public static void main(String[] args){		
		for(int j = 0; j < 10; j++){
			new Thread(new Runnable() {
				int i = 0;				
				@Override
				public void run() {
					for(int x = 0; x < 10000; x++){
//						Count++;
						i++;
					}
				}
			}).start();		
		}
		while(Thread.activeCount() > 1){			
		}
		System.out.println("Count¸öÊı:"+Count);
	}
}
