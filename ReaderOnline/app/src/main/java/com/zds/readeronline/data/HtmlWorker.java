package com.zds.readeronline.data;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.ExecutionContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by 76933 on 2017/12/24.
 */

public class HtmlWorker {

    /**
     * 通过HttpClient读取网页源码
     */
    public static void httpClientWebData(String path, NetReceiveCallback callback) {
        Log.e("zds httpClientWebData", path);
        myTask mt = new myTask(callback);
        mt.execute(path);
    }

    /**
     * 通过HttpClient得到真实url
     */
    public static void httpClientGetUrl(String path, NetReceiveCallback callback) {
        Log.e("zds httpClientGetUrl", path);
        getUrlTask mt = new getUrlTask(callback);
        mt.execute(path);
    }

    public static void httpClientWebDataWithSearch(String path, NetReceiveCallback callback) {
        Log.e("zds WebDataWithSearch", path);
        getWithSearchTask mt = new getWithSearchTask(callback);
        mt.execute(path);
    }

    //TODO 添加获取真实url的task，获取并赋值url
    static class getUrlTask extends AsyncTask<String, Integer, String> {

        private NetReceiveCallback callback;

        public getUrlTask(NetReceiveCallback callback) {
            this.callback = callback;
        }

        @Override
        protected String doInBackground(String... params) {
            HttpUriRequest realRequest = null;
//        path="https://www.miaobige.com/search/?s=%B4%F3%B5%C0%B3%AF%CC%EC";
            try {
//                Log.e("zds resp", params[0]);
                HttpContext httpContext = new BasicHttpContext();
                HttpGet httpGet = new HttpGet(params[0]);
                HttpClient client = new DefaultHttpClient();
                HttpResponse resp = client.execute(httpGet, httpContext);
                HttpEntity entity = resp.getEntity();
                realRequest = (HttpUriRequest) httpContext.getAttribute(ExecutionContext.HTTP_REQUEST);
                httpGet.abort();
                client.getConnectionManager().shutdown();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "https://www.miaobige.com" + realRequest.getURI().getPath().replace("book", "read");
        }

        @Override
        protected void onPostExecute(String s) {
//            Log.e("zds resp", "onPostExecute  " + s);
            callback.onNetReceive(s);
        }
    }


    static class myTask extends AsyncTask<String, Integer, String> {

        private NetReceiveCallback callback;

        public myTask(NetReceiveCallback callback) {
            this.callback = callback;
        }

        @Override
        protected String doInBackground(String... params) {
            String respContent = "";
            try {
                Log.e("zds resp", params[0]);
                HttpContext httpContext = new BasicHttpContext();
                HttpGet httpGet = new HttpGet(params[0]);
                HttpClient client = new DefaultHttpClient();
                HttpResponse resp = client.execute(httpGet, httpContext);
                HttpEntity entity = resp.getEntity();
                HttpUriRequest realRequest = (HttpUriRequest) httpContext.getAttribute(ExecutionContext.HTTP_REQUEST);
                respContent = EntityUtils.toString(entity, "GBK").trim();

                httpGet.abort();
                client.getConnectionManager().shutdown();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return respContent;
        }

        @Override
        protected void onPostExecute(String s) {
//            Log.e("zds resp", "onPostExecute " + s);
            callback.onNetReceive(s);
        }
    }

    static class getWithSearchTask extends AsyncTask<String, Integer, String> {

        private NetReceiveCallback callback;

        public getWithSearchTask(NetReceiveCallback callback) {
            this.callback = callback;
        }

        @Override
        protected String doInBackground(String... params) {
            String respContent = "";
//        path="https://www.miaobige.com/search/?s=%B4%F3%B5%C0%B3%AF%CC%EC";
            String realUrl = null;
            try {
//                Log.e("zds resp", params[0]);
                HttpContext httpContext = new BasicHttpContext();
                HttpGet httpGet = new HttpGet(params[0]);
                HttpClient client = new DefaultHttpClient();
                client.execute(httpGet, httpContext);
                HttpUriRequest realRequest = (HttpUriRequest) httpContext.getAttribute(ExecutionContext.HTTP_REQUEST);
                realUrl="https://www.miaobige.com"+realRequest.getURI().getPath().replace("book","read");
                httpGet.abort();
                client.getConnectionManager().shutdown();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
//                Log.e("zds resp real", realUrl);
                HttpContext httpContext = new BasicHttpContext();
                HttpGet httpGet = new HttpGet(realUrl);
                HttpClient client = new DefaultHttpClient();
                HttpResponse resp = client.execute(httpGet, httpContext);
                HttpEntity entity = resp.getEntity();
                respContent = EntityUtils.toString(entity, "GBK").trim();
                httpGet.abort();
                client.getConnectionManager().shutdown();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return respContent;
        }

        @Override
        protected void onPostExecute(String s) {
//            Log.e("zds resp", "onPostExecute " + s);
            callback.onNetReceive(s);
        }
    }

    /**
     * 通过urlconnection读取网页源码，字符串与浏览器不同
     */
    public static String httpurlWebData(String path) throws Exception {
        // 通过网络地址创建URL对象
        URL url = new URL(path);
        // 根据URL
        // 打开连接，URL.openConnection函数会根据URL的类型，返回不同的URLConnection子类的对象，这里URL是一个http，因此实际返回的是HttpURLConnection
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        // 设定URL的请求类别，有POST、GET 两类
        conn.setRequestMethod("GET");
        //设置从主机读取数据超时（单位：毫秒）
        conn.setConnectTimeout(5000);
        //设置连接主机超时（单位：毫秒）
        conn.setReadTimeout(5000);
        // 通过打开的连接读取的输入流,获取html数据
        InputStream inStream = conn.getInputStream();
        // 得到html的二进制数据
        byte[] data = readInputStream(inStream);
        // 是用指定的字符集解码指定的字节数组构造一个新的字符串
        String html = new String(data, "GBK");
        return html;
    }

    /**
     * 读取输入流，得到html的二进制数据
     *
     * @param inStream
     * @return
     * @throws Exception
     */
    public static byte[] readInputStream(InputStream inStream) throws Exception {

        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        inStream.close();
        return outStream.toByteArray();
    }

    /**
     * Created by 76933 on 2017/12/25.
     */

}
