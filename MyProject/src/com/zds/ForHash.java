package com.zds;

import java.util.*;

public class ForHash {

	public static void main(String[] args) {
		HashMap<Integer, Integer> map = new HashMap<>();
		//����
		Iterator iter = map.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			Object key = entry.getKey();
			Object val = entry.getValue();
		}
		
		String[] a={"1 25","2 3","4 52","11 62","11 60","11 30","11 95","987 6","1141 35","35 1141"};
		for(String s:a){
			map.put(Integer.valueOf(s.split(" ")[0]),Integer.valueOf(s.split(" ")[1]));
		}
		//תlist Ӧ����key=vlue��ʽ
		List<Map.Entry<Integer, Integer>> infoIds = new ArrayList<>(map.entrySet());
		for (int i = 0; i < infoIds.size(); i++) {
			String id = infoIds.get(i).toString();
		System.out.println(id);
		}
	}
}
