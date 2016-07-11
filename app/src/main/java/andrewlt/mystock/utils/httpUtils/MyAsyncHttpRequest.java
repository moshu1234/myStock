package andrewlt.mystock.utils.httpUtils;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.BinaryHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * Created by liut1 on 7/3/16.
 */
public class MyAsyncHttpRequest {
    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(String url, RequestParams params, JsonHttpResponseHandler responseHandler) {
        client.get(url, params, responseHandler);
    }

    public static void get(String url, AsyncHttpResponseHandler responseHandler) {
        client.get(url, responseHandler);
    }
    public static void get(String url, BinaryHttpResponseHandler responseHandler) {
        client.get(url, responseHandler);
    }
    public static void post(String url, RequestParams params, JsonHttpResponseHandler responseHandler) {
        client.post(url, params, responseHandler);
    }

}
