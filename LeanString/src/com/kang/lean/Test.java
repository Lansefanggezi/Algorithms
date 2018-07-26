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

		// HashMap��key��value���ǿ���Ϊnull��
		Map<String, String> hashMap = new HashMap<>();
		hashMap.put(null, null);

		// �������ظ�key�������Զ�����֮ǰ��key
		hashMap.put(null, "String");

		// hashMap�Ĵ�С
		System.out.println(hashMap.size());
		System.out.println(hashMap.get(null));

		// HashSet ���Լ���null
		Set<String> hashSet = new HashSet<>();
		hashSet.add(null);
		hashSet.remove(null);
		System.out.println("HashSet�Ĵ�С��" + hashSet.size());

		// hashTable ��key��value���ǲ�����Ϊ��
		Map<String, String> hashTable = new Hashtable<>();
		hashTable.put("null", "null");
		System.out.println(hashTable.get("null"));
		// HashTable�Ĵ�С
		System.out.println(hashTable.size());

		// concurrentHashMap��key��value���ǲ�����null��
		Map<String, String> concurrenHashMap = new ConcurrentHashMap<>();
		concurrenHashMap.put("null", "null");
		System.out.println(concurrenHashMap.get("null"));

		// ConcurrenHashTable�Ĵ�С
		System.out.println(concurrenHashMap.size());

		//int���͵�������ټ�һ�ͻᵼ��С�������
		int by = Integer.MAX_VALUE;
		System.out.println((by + 1 )< by);
		System.out.println(by + "              " + by + 1);

	}
}
