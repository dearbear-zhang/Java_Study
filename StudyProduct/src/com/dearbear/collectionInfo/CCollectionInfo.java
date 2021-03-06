package com.dearbear.collectionInfo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.jws.soap.SOAPBinding.Style;
import javax.security.auth.kerberos.KeyTab;

public class CCollectionInfo {
	public static void main(String args[]) {
		
		List<String> nameList = new ArrayList<>();
		nameList.add("a");
		nameList.add("b");
		nameList.add("c");
		Iterator<String> iter = nameList.iterator();
		while (iter.hasNext()) {
			String name = (String) iter.next();
			System.out.println(name);
		}
		int i = (int)(Math.random()* nameList.size());
		System.out.println(nameList);
		System.out.printf("随机出来的名字为%s\n", nameList.get(i));
		nameList.remove(i);
		System.out.println(nameList);
		
		UpdataStu stu1 = new UpdataStu("zhangshan", 1);
		UpdataStu stu2 = new UpdataStu("lisi", 5);
		UpdataStu stu3 = new UpdataStu("shui", 2);
		UpdataStu stu4 = new UpdataStu("shui", 2);
		
		TreeSet<UpdataStu> stuSet = new TreeSet<>();
		stuSet.add(stu1);
		stuSet.add(stu2);
		stuSet.add(stu3);
		
		Iterator<UpdataStu> iterSet = stuSet.iterator();
		while(iterSet.hasNext()){
			UpdataStu stu = (UpdataStu)iterSet.next();
			System.out.println("Id:" + stu.m_id + " name:" +stu.m_name);
		}
		//截取排在stud2前的集合
		iterSet = stuSet.headSet(stu2).iterator();
		while(iterSet.hasNext()){
			UpdataStu xx = iterSet.next();
			System.out.println("Id:" + xx.m_id + xx.m_name);
		}
		iterSet = stuSet.subSet(stu3, stu2).iterator();
		while(iterSet.hasNext()){
			UpdataStu xx = iterSet.next();
			System.out.println("Id:" + xx.m_id + xx.m_name);
		}
		
		System.out.println("正在测试TrueMap");
		Map<UpdataStu, String> stuMap = new HashMap<>();
		stuMap.put(stu1, "第一个插入");
		stuMap.put(stu2, "第二个插入");
		stuMap.put(stu3, "第三个插入");
		stuMap.put(stu4, "第四个插入");
		Iterator<UpdataStu> itrMap = stuMap.keySet().iterator();
		while(itrMap.hasNext()){
			UpdataStu keyTemp = itrMap.next();
			System.out.println(keyTemp + "," + stuMap.get(keyTemp));
		}
		System.out.println("正在测试HashMap");
		Map<UpdataStu, String> stuHashMap = new TreeMap<>(stuMap);
		itrMap = stuHashMap.keySet().iterator();
		while(itrMap.hasNext()){
			UpdataStu keyTemp = itrMap.next();
			System.out.println(keyTemp + "," + stuMap.get(keyTemp));
		}
		System.out.println(stu3.hashCode());
		System.out.println(stu4.hashCode());
		System.out.println(stu3.equals(stu4));
	}
}

class UpdataStu implements Comparable<Object>{
	String m_name;
	long m_id;
	public UpdataStu(String name, long id){
		m_name = name;
		m_id = id;
	}
	@Override
	public int compareTo(Object o){
		UpdataStu bStu = (UpdataStu)o;
		int result = this.m_id > bStu.m_id ? 1 : ( this.m_id == bStu.m_id ? 0 : -1);
		return result;
	}
	@Override
	public String toString(){
		return m_name;
	}
	@Override
	public boolean equals(Object bObject){
		return this.m_name.equals(((UpdataStu)bObject).m_name) && this.m_id == ((UpdataStu)bObject).m_id;
	}
	@Override
	public int hashCode(){
		return this.m_name.hashCode() * 37 + (int)this.m_id;
	}
}

