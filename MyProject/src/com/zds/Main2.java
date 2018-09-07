package com.zds;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;

public class Main2 {

    public static void main(String[] args) {
        String s="https://www.miaobige.com/search/?s=%B4%F3%B5%C0%B3%AF%CC%EC";

        String ss="https://www.miaobige.com/search/?s=大道朝天";

        try {
            System.out.println(URLEncoder.encode(s,"gb2312"));
            System.out.println("https://www.miaobige.com/search/?s="+URLEncoder.encode("大道朝天","gb2312"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}