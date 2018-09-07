package com.zds;

import java.io.*;
import java.util.*;

public class ThreadProduct {

	public static int i = 0;

	public static void main(String[] args) {
		// Scanner sc = new Scanner(System.in);
		// String s=sc.next();

		new ThreadA().start();
		new ThreadB().start();

	}

	public synchronized static void add() {
		if (i != 0) {
			try {
				ThreadProduct.class.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		ThreadProduct.class.notifyAll();
		i++;
		System.out.println(i);
	}

	public synchronized static void minu() {
		if (i != 1) {
			try {
				ThreadProduct.class.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		ThreadProduct.class.notifyAll();
		i--;
		System.out.println(i);
	}

	static class ThreadA extends Thread {
		public void run() {
			while (true) {
				add();
			}
		}
	}

	static class ThreadB extends Thread {
		public synchronized void run() {
			while (true) {
				minu();
			}
		}
	}

}
