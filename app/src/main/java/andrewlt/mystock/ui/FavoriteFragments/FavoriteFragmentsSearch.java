package andrewlt.mystock.ui.FavoriteFragments;

import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.EditText;
import android.widget.ImageButton;

import com.loopj.android.http.AsyncHttpResponseHandler;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import andrewlt.mystock.R;
import andrewlt.mystock.ui.adapters.FavoriteListItem;
import andrewlt.mystock.ui.adapters.FavoriteSearchAdapter;
import andrewlt.mystock.ui.adapters.SlidelessViewPager;
import andrewlt.mystock.utils.StockRequest.MyStockInfoAsyncRequest;
import andrewlt.mystock.utils.StockRequest.SinaStockInfo;

/**
 * Created by liut1 on 7/3/16.
 */
public class FavoriteFragmentsSearch extends Fragment {
    private View view;
    private FavoriteSearchAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private List<FavoriteListItem> mData = new ArrayList<>();
    private SinaStockInfo mSinaStockInfo;
    private Handler handler;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.favorite_fragment_search, container, false);
        initView();
        return view;
    }
    public void initView(){
        mRecyclerView = (RecyclerView)view.findViewById(R.id.favorite_search_result);
//        FavoriteListItem item = new FavoriteListItem();
//        item.setId("002431");
//        item.setName("棕榈股份");
//        item.setBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.add));
//        mData.add(item);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new FavoriteSearchAdapter(mData);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new FavoriteSearchAdapter.MyItemClickListener() {
            @Override
            public void onItemClick(View view, FavoriteListItem data) {
                switch (view.getId()){
                    case R.id.favorite_search_result_ib:
                        Log.e("======","name="+data.getName());
                        Message message = new Message();
                        message.what = 1;//add
                        message.obj = data;
                        handler.sendMessage(message);
                        //TODO:add to favorite list
                        break;
                    default:

                        Log.e("======","id="+data.getId());
                        //TODO:get into the stock detail
                        break;
                }
            }
        });

        view.findViewById(R.id.favorite_search_bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText et = (EditText)view.findViewById(R.id.favorite_search_et);
                if(et.getText().toString().equals("")){
                    return;
                }else {
                    searchStock(et.getText().toString());
                }

            }
        });
    }

    public void searchStock(final String code){
//        AsyncHttpResponseHandler handler = new AsyncHttpResponseHandler();
        MyStockInfoAsyncRequest myStockInfoAsyncRequest = new MyStockInfoAsyncRequest();
        myStockInfoAsyncRequest.getStockInfo(code,new AsyncHttpResponseHandler(){
            @Override
            public void onSuccess(int i, cz.msebera.android.httpclient.Header[] headers, byte[] bytes) {
                try {
                    String s = new String(bytes, "GB2312");
                    mSinaStockInfo = SinaStockInfo.parseStockInfo(s);
                    displayInResultList(mSinaStockInfo,code);
                    Log.e("=======","canimeid "+i+ "    "+s);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (SinaStockInfo.ParseStockInfoException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int i, cz.msebera.android.httpclient.Header[] headers, byte[] bytes, Throwable throwable) {

            }

        });
    }
    public void displayInResultList(SinaStockInfo info, String id){
        float scale = 0;
        if(info.getNowPrice() == 0){
            scale = 0;
        }
        else {
            scale = (info.getNowPrice() - info.getYestodayPrice());
        }
        BigDecimal bd = new BigDecimal(Double.parseDouble(String.valueOf(scale)));
        FavoriteListItem item = new FavoriteListItem();
        item.setId(id);
        item.setName(info.getName());
        item.setNowPrice(String.valueOf(info.getNowPrice()));
        item.setNowScale(String.valueOf(bd.setScale(3,BigDecimal.ROUND_HALF_UP).floatValue()));
        item.setOpenToday(String.valueOf(info.getTodayPrice()));
        item.setTradeCount(String.valueOf(info.getTradeCount()));
        item.setBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.add));
        mData.add(item);
        mAdapter.notifyDataSetChanged();
    }
    public void setHandler(Handler handler){
        this.handler = handler;
    }
}
