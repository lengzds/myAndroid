package com.zds.readeronline.data;

import android.util.Log;


import com.zds.readeronline.data.Book;
import com.zds.readeronline.data.HtmlWorker;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.security.auth.callback.Callback;

/**
 * Created by 76933 on 2017/12/24.
 */

public class NetDataHelper {

    /**
     * jdom解析，有问题，未实现
     */
    public static void JdomAnalysis(String in) {
        SAXBuilder builder = new SAXBuilder(false);
        try {
            Document doc = builder.build(in);
            Element books = doc.getRootElement();
            List booklist = books.getChildren("book");

            for (Iterator iter = booklist.iterator(); iter.hasNext(); ) {
                Element book = (Element) iter.next();

                String email = book.getAttributeValue("email");
                System.out.println(email);
                String name = book.getChildTextTrim("name");
                System.out.println(name);
                book.getChild("name").setText("alterrjzjh");
            }
            XMLOutputter outputter = new XMLOutputter();
            outputter.output(doc, new FileOutputStream(in));

        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试函数
     */
    public String HtmlAnalysis(String in) throws ParserException {
        Book mBook = new Book();
        mBook.setUrl("https://www.miaobige.com/read/18992/");
//        getBook(in, mBook);
//        getBookList(in, mBook);
//        checkUrl(mBook);
//        Iterator<Map.Entry<String, String>> iterator = mBook.getList().entrySet().iterator();

//        while (iterator.hasNext()) {
//            Map.Entry entry = iterator.next();
//            Log.e("list", entry.getKey() + " " + entry.getValue());


//        String content = getContent(mBook.getUrl().replace(".html", "") + mBook.getChapterList().get("第七章第一堂课"));
//        Log.e("con", content);
//        }
        return "";
    }


    /**
     * 解析目录网页，得到名称信息
     */
    public static void parseBookInfo(String in, Book mBook) {
        try {
            Parser parser = new Parser(in);
            NodeFilter filterDIV = new TagNameFilter("div");
            NodeFilter filterID = new HasAttributeFilter("id", "smallcons");
            NodeFilter filter = new AndFilter(filterDIV, filterID);
            NodeList nodes = parser.extractAllNodesThatMatch(filter);
            Node node = nodes.elementAt(0);
            NodeList nodesChild = node.getChildren();

            Node[] all = nodesChild.toNodeArray();
            mBook.setName(all[1].toPlainTextString());
            for (int i = 2; i < all.length; i++) {
                String s = all[i].toPlainTextString();
//            Log.d("mes", s);
                if (s.contains("分类"))
                    mBook.setFenlei(all[++i].toPlainTextString());
                else if (s.contains("作者"))
                    mBook.setZuozhe(all[++i].toPlainTextString());
                else if (s.contains("字数"))
                    mBook.setZishu(all[++i].toPlainTextString());
                else if (s.contains("更新时间"))
                    mBook.setGengxin(all[++i].toPlainTextString());
            }
            Log.e("book message", mBook.toString());
        } catch (ParserException e) {
            e.printStackTrace();
        }
    }

    /**
     * 解析目录源码，得到目录
     */
    public static void parseChapterList(String in, chapterData chapterData) {
        try {
            Parser parser = new Parser(in);
            NodeFilter filterDIV = new TagNameFilter("div");
            NodeFilter filterID = new HasAttributeFilter("id", "readerlists");
            NodeFilter filter = new AndFilter(filterDIV, filterID);
            NodeList nodes = parser.extractAllNodesThatMatch(filter);
            Node node = nodes.elementAt(0);
            NodeList nodesChild = node.getChildren();

            Node[] all = nodesChild.toNodeArray();
            for (int i = 0; i < all.length; i++) {
                NodeList ns = all[i].getChildren();
                if (ns != null) {
                    for (Node n : ns.toNodeArray()) {
                        NodeList nss = n.getChildren();
                        if (nss != null) {
                            for (Node nn : nss.toNodeArray()) {
                                if (nn.toHtml().contains("章")) {
                                    if (nn.toHtml().contains("<a href=")) {
                                        String[] ss = nn.toHtml().split("\"");
                                        String name = ss[4].replace("</a>", "").replace(">", "");
                                        String url = ss[1].replaceAll(".*/", "");
//                                    Log.e(name, url);
                                        chapterData.addChapterNameList(name);
                                        chapterData.addChapterList(name, url);
                                    } else {
                                        String name = nn.toHtml().split(" ")[0];
//                                    Log.e(name, " null");
                                        chapterData.addChapterNameList(name);
                                        chapterData.addChapterList(name, "null");
                                    }
                                }
                            }
                        }
                    }
                }
            }
//            checkUrl(chapterData);
        } catch (ParserException e) {
            e.printStackTrace();
        }
    }

    /**
     * 得到未解析出的目录url
     */
    private static void checkUrl(final chapterData chapterData) {
        String beforeUrl = "null";
        for (final String chapterName : chapterData.getChapterNameList()) {
            if ("null".equals(chapterData.getChapterList().get(chapterName))) {
                if (!beforeUrl.equals("null")) {
//                        网络回调
                    NetReceiveCallback callback = new NetReceiveCallback() {
                        @Override
                        public void onNetReceive(String receive) {
                            chapterData.getChapterList().put(chapterName, getErrorUrl(receive));
                        }
                    };
                    HtmlWorker.httpClientWebData(chapterData.getUrl().replace(".html", "") + beforeUrl, callback);
                }
            } else {
                beforeUrl = chapterData.getChapterList().get(chapterName);
            }
        }
    }


    /**
     * 从下一章得到url，用于解析未得到的目录url
     */
    private static String getErrorUrl(String in) {
        Parser parser = null;
        try {
            parser = new Parser(in);
            NodeFilter filterDIV = new TagNameFilter("div");
            NodeFilter filterID = new HasAttributeFilter("class", "jump jumptop");
            NodeFilter filter = new AndFilter(filterDIV, filterID);
            NodeList nodes = parser.extractAllNodesThatMatch(filter);
            Node node = nodes.elementAt(0);
            Node nodesChild = null;
            nodesChild = node.getLastChild();
            return nodesChild.toHtml().split("\"")[1];
        } catch (ParserException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 通过网址得到章节内容
     */
    public static String getContent(String url, final NetReceiveCallback adapterCallback) {
        Log.e("url", url);

//        网络回调
        NetReceiveCallback callback = new NetReceiveCallback() {
            @Override
            public void onNetReceive(String receive) {
                try {
                    Parser parser = new Parser(receive);
                    NodeFilter filterDIV = new TagNameFilter("div");
                    NodeFilter filterID = new HasAttributeFilter("id", "content");
                    NodeFilter filter = new AndFilter(filterDIV, filterID);
                    NodeList nodes = parser.extractAllNodesThatMatch(filter);
                    Node node = nodes.elementAt(0);
                    NodeList nodesChild = node.getChildren();
                    Node[] all = nodesChild.toNodeArray();

                    StringBuffer sb = new StringBuffer();
                    for (int i = 0; i < all.length; i++) {
                        sb.append(all[i].toPlainTextString());
                    }
                    adapterCallback.onNetReceive(sb.toString());
                } catch (ParserException e) {
                    e.printStackTrace();
                }
            }
        };
        HtmlWorker.httpClientWebData(url, callback);
        return null;
    }


}
