package andrewlt.mystock.utils.StockRequest;

import android.graphics.Bitmap;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.BinaryHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;

import andrewlt.mystock.utils.httpUtils.MyAsyncHttpRequest;
import andrewlt.mystock.utils.httpUtils.MyHttpRequestItem;
import andrewlt.mystock.utils.httpUtils.MyHttpUrlConnection;

/**
 * Created by liut1 on 7/3/16.
 */
public class MyStockInfoAsyncRequest {
    private String url="http://hq.sinajs.cn/list=";
    private String urlGif="http://image.sinajs.cn/newchart/daily/n/";
    public MyStockInfoAsyncRequest(){
    }
    private String resetStockCode(String stockCode){
        //TODO:find out where is this code from sh, sz or not exist
        if((stockCode.substring(0,1).equals("6"))) {
            return "sh" + stockCode;
        }else if((stockCode.substring(0,1).equals("9"))){//B stock
            return "sh" + stockCode;
        }else if((stockCode.substring(0,1).equals("3"))){
            return "sz" + stockCode;
        }else if((stockCode.substring(0,1).equals("0"))){
            return "sz" + stockCode;
        }else if((stockCode.substring(0,1).equals("2"))){//B stock
            return "sz" + stockCode;
        }
        else {
            return "sh"+stockCode;
        }
    }
    public void getStockInfo(String code, AsyncHttpResponseHandler handler){
        MyAsyncHttpRequest.get(url+resetStockCode(code),handler);
    }
    public void getStockBitmap(String code, BinaryHttpResponseHandler handler){
        MyAsyncHttpRequest.get(urlGif+resetStockCode(code),handler);
    }
}
