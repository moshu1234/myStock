package andrewlt.mystock.utils.httpUtils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by liut1 on 7/2/16.
 */
public class MyHttpUrlConnection extends HttpConnectionBase{
    private static String TAG = "CustomHttpUrlConnection";
    private static HttpURLConnection conn;
    private MyHttpRequestItem myHttpRequestItem;
    public MyHttpUrlConnection(MyHttpRequestItem myHttpRequestItem){
        this.myHttpRequestItem = myHttpRequestItem;
    }

    @Override
    public String GetFromServer(){
        String result = "";
        try {
            URL url = new URL(myHttpRequestItem.getUrl());
            try {
                conn = (HttpURLConnection)url.openConnection();
                conn.setDoInput(true);
                conn.setConnectTimeout(myHttpRequestItem.getConnectionTimeout());
                conn.setReadTimeout(myHttpRequestItem.getReadTimeout());
                conn.setRequestProperty("accept", "*/*");
                conn.connect();

                InputStream stream=conn.getInputStream();
                InputStreamReader inReader=new InputStreamReader(stream);
                BufferedReader buffer=new BufferedReader(inReader);
                String strLine=null;
                while((strLine=buffer.readLine())!=null)
                {
                    result+=strLine;
                }
                inReader.close();
                conn.disconnect();
                return result;
            } catch (IOException e) {
                Log.e(TAG, "getFromWebByHttpUrlCOnnection:"+e.getMessage());
                e.printStackTrace();
                return null;
            }
        } catch (MalformedURLException e) {

            Log.e(TAG, "getFromWebByHttpUrlCOnnection:"+e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public Bitmap GetPictureFromServer(){
        try {
            URL url = new URL(myHttpRequestItem.getUrl());
            try {
                conn = (HttpURLConnection)url.openConnection();
                conn.setDoInput(true);
                conn.setConnectTimeout(myHttpRequestItem.getConnectionTimeout());
                conn.setReadTimeout(myHttpRequestItem.getReadTimeout());
                conn.setRequestProperty("accept", "*/*");
                conn.connect();

                InputStream stream=conn.getInputStream();
                return BitmapFactory.decodeStream(stream);
            } catch (IOException e) {
                Log.e(TAG, "getFromWebByHttpUrlCOnnection:"+e.getMessage());
                e.printStackTrace();
                return null;
            }
        } catch (MalformedURLException e) {

            Log.e(TAG, "getFromWebByHttpUrlCOnnection:"+e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    public String PostFromServer(){
        String result = "";
        try {
            URL url = new URL(myHttpRequestItem.getUrl());
            try {
                conn = (HttpURLConnection)url.openConnection();
                // 设置是否从httpUrlConnection读入，默认情况下是true;
                conn.setDoInput(true);
                // 设置是否向httpUrlConnection输出，因为这个是post请求，参数要放在
                // http正文内，因此需要设为true, 默认情况下是false;
                conn.setDoOutput(true);
                // 设定请求的方法为"POST"，默认是GET
                conn.setRequestMethod(myHttpRequestItem.getMethod());
                //设置超时
                conn.setConnectTimeout(myHttpRequestItem.getConnectionTimeout());
                conn.setReadTimeout(myHttpRequestItem.getReadTimeout());
                // Post 请求不能使用缓存
                conn.setUseCaches(false);
                conn.setInstanceFollowRedirects(true);
                // 设定传送的内容类型是可序列化的java对象
                // (如果不设此项,在传送序列化对象时,当WEB服务默认的不是这种类型时可能抛java.io.EOFException)
                conn.setRequestProperty("Content-Type",
                        "application/x-www-form-urlencoded");
                conn.connect();

                InputStream in = conn.getInputStream();
                InputStreamReader inStream=new InputStreamReader(in);
                BufferedReader buffer=new BufferedReader(inStream);
                String strLine=null;
                while((strLine=buffer.readLine())!=null)
                {
                    result+=strLine;
                }
                conn.disconnect();
                return result;
            } catch (IOException e) {
                Log.e(TAG,"PostFromWebByHttpURLConnection："+ e.getMessage());
                e.printStackTrace();
                return null;
            }
        } catch (MalformedURLException e) {
            Log.e(TAG,"PostFromWebByHttpURLConnection："+ e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
