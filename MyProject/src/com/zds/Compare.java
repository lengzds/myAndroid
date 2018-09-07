package com.zds;

import java.util.*;
class point{
		public point(int a,int b){
			this.x=a;
			this.y=b;
		}
		public int x;
		public int y;
	}
public class Compare {
	public static void main(String[] args){
		Comparator zcpx=new Comparator<point>() {
			@Override
			public int compare(point arg0, point arg1) {
				// TODO Auto-generated method stub
				if(arg0.x==arg1.x)
					return arg0.y-arg1.y;
				return arg0.x-arg1.x;
			}
		};
		String[] a={"1 25","2 3","4 52","11 62","11 60","11 30","11 95","987 6","1141 35","35 1141"};
		ArrayList<point> ap=new ArrayList<>();
		for(String s:a){
			ap.add(new point(Integer.valueOf(s.split(" ")[0]),Integer.valueOf(s.split(" ")[1])));
		}
		Collections.sort(ap, zcpx);
		
		for(point p:ap){
			System.out.println(p.x+" "+p.y);
		}
		
	}
	
}
