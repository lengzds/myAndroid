package com.zds;

import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) {

//        Scanner in = new Scanner(System.in);
//        String s = in.next();
        ArrayList<String> save = new ArrayList<String>();
//        String ins = in.next();
//        for (String ss : ins.split(",")) {
//            save.add(ss);
//        }
//
        for(String s1:save){
            System.out.println(s1);

        }


//        String s="第001 被游戏附身了\n" +
//                "\n" +
//                "将拦路雪糕筒在车后几十米的位置摆好后，张劲晃晃悠悠的走回车里，重重的把自己扔回驾驶座上，嘴里碎碎的嘀咕着，“我咋就这么背？这车在哪里坏不好？偏偏它就在高速公路上给自己玩了一次罢工！在高速公路上坏车有多危险暂且不说，单单是拖车费就够叫人心疼的了。那些垄断了高速上拖车服务的家伙们别提要价多黑了，就拿自己屁股下面的这辆牧马人来说，从这里拖到高速路口的维修点不过是八公里多一点的距离，但是就是这么一点距离，那帮家伙就至少得宰自己三四千！简直就是一群贪得无厌的吸血鬼！”\n" +
//                "但是无论心里如何的不情愿，却还是不得不掏出手机拨通高速计费卡上的电话呼叫拖车服务。这些家伙也够嚣张，自持这生意是蝎子拉屎——独一份，服务态度奇差无比，在张劲说完自己所在地点后，电话那边的那个更年期老女人就不耐烦的回到：“行了，知道了。几辆拖车现在都不在家，你等着吧！”说着就挂了电话。\n" +
//                "放下电话后，张劲一肚子火的等了足足有快两个小时了，却还没有等到前来救援的拖车。这两个小时的等待中，在指天画地的咒骂、诅咒耗干了张劲口水的同时也终于磨平了张劲心底的窝火，开始无聊起来。给自己的同事、朋友打了十几个搔扰电话，重头到尾翻过两遍早晨刚刚买来的报纸后，百无聊赖的张劲最终又拿起了放在副驾驶座上的电脑，趴在方向盘上玩起了游戏。\n" +
//                "张劲玩的是一款名为《武林三";

        File f=new File("file.txt");
        try {
            FileInputStream is=new FileInputStream(f);
            InputStreamReader isr=new InputStreamReader(is,"utf16");
            BufferedReader br=new BufferedReader(isr);
            String line=null;
            while((line=br.readLine())!=null){


                System.out.println(new String(line));
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        String s=new String("屈服");
        System.out.println(s);
        s=s.replaceAll("第([0-9]{1,4})","第$1章");
        System.out.println(s);

    }

}