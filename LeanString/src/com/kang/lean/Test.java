package com.kang.lean;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class Test {

	public static void main(String[] args) {
		// FileUtils.writeByteArrayToFile(file, data);

		// HashMap的key和value都是可以为null的
		Map<String, String> hashMap = new HashMap<>();
		hashMap.put(null, null);

		// 不会有重复key，它会自动覆盖之前的key
		hashMap.put(null, "String");

		// hashMap的大小
		System.out.println(hashMap.size());
		System.out.println(hashMap.get(null));

		// HashSet 可以加入null
		Set<String> hashSet = new HashSet<>();
		hashSet.add(null);
		hashSet.remove(null);
		System.out.println("HashSet的大小：" + hashSet.size());

		// hashTable 的key和value都是不可以为空
		Map<String, String> hashTable = new Hashtable<>();
		hashTable.put("null", "null");
		System.out.println(hashTable.get("null"));
		// HashTable的大小
		System.out.println(hashTable.size());

		// concurrentHashMap的key和value都是不允许null的
		Map<String, String> concurrenHashMap = new ConcurrentHashMap<>();
		concurrenHashMap.put("null", "null");
		System.out.println(concurrenHashMap.get("null"));

		// ConcurrenHashTable的大小
		System.out.println(concurrenHashMap.size());

		//int类型的最大数再加一就会导致小于最大数
		int by = Integer.MAX_VALUE;
		System.out.println((by + 1 )< by);
		System.out.println(by + "              " + by + 1);

	}
}
