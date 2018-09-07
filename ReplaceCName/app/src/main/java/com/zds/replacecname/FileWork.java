package com.zds.replacecname;

import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class FileWork {
    public FileWork() {
        // TODO Auto-generated constructor stub
        File folder = new File("cycleOutput");
        if (folder.exists())
            folder.delete();
        folder.mkdir();
    }

    public static String readFileToString(File file) // 按行读文件
    {
        StringBuffer out = new StringBuffer("");
        try {
            FileInputStream is = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(is, "utf-16");//,""iso-8859-1
            BufferedReader in = new BufferedReader(isr);
            String line = null;
            while ((line = in.readLine()) != null) {
                out.append(line + "\n");
            }
            in.close();
            is.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
//		String utfout=null;
//		try {
//			byte[] jiema= out.toString().getBytes("iso-8859-1") ;
//			utfout=new String(jiema,"utf-16");
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
        return out.toString();
    }

    public static void SaveFile(String name, String con) {

        FileOutputStream fos = null;
        PrintStream ps = null;
        PrintWriter pw = null;
        File f = new File(name);
        if (f.exists()) {
            f.delete();
        }
//		String out=null;
//		try {
//			byte[] jiema= con.getBytes("utf-16") ;
//			out=new String(jiema,"iso-8859-1");
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
//		Log.e("out",out);
        try {
            String fileName = name;
            fos = new FileOutputStream(fileName, true);
//			pw =new PrintWriter(new OutputStreamWriter(fos, "utf-16"));
//			ps = new PrintStream(fos);
//			pw.println(con.toString());

            OutputStreamWriter write = new OutputStreamWriter(fos, "utf-16");//,""iso-8859-1
            BufferedWriter writer = new BufferedWriter(write);
            writer.write(con);
            writer.close();

        } catch (IOException e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
