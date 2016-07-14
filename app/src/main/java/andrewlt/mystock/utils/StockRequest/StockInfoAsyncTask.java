package andrewlt.mystock.utils.StockRequest;

import android.os.AsyncTask;
import android.os.Process;
import android.util.Log;

/**
 * Created by liut1 on 7/13/16.
 */
public class StockInfoAsyncTask extends AsyncTask {
    private static final String TAG = "MYSTOCK";
    @Override
    protected void onPreExecute() {
        Log.i(TAG, "onPreExecute() called");
    }
    @Override
    protected Object doInBackground(Object[] params) {
        return null;
    }
}
