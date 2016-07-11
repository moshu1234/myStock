package andrewlt.mystock.ui.FavoriteFragments;

import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.loopj.android.http.AsyncHttpResponseHandler;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import andrewlt.mystock.R;
import andrewlt.mystock.ui.adapters.FavoriteListAdapter;
import andrewlt.mystock.ui.adapters.FavoriteListItem;
import andrewlt.mystock.ui.adapters.favoriteListHeadAdapter;
import andrewlt.mystock.utils.MyToast;
import andrewlt.mystock.utils.StockRequest.MyStockInfoAsyncRequest;
import andrewlt.mystock.utils.StockRequest.SinaStockInfo;


/**
 * Created by liut1 on 7/3/16.
 */
public class FavoriteFragmentsSelected extends Fragment {
    private View view;
    private List<FavoriteListItem> mData = new ArrayList<>();
    private FavoriteListAdapter adapter;
    private favoriteListHeadAdapter headAdapter;
    private RecyclerView recyclerViewHead;
    private RecyclerView recyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.favorite_fragment_selected, container, false);
        initView();
        return view;
    }
    private void initView(){
        recyclerView = (RecyclerView)view.findViewById(R.id.favorite_list);
        recyclerViewHead = (RecyclerView)view.findViewById(R.id.favorite_list_head);
        FavoriteListItem item = new FavoriteListItem();
//        item.setName("aa");
//        item.setId("bb");
        item.setNowPrice("最新");
        item.setNowScale("涨幅");
        item.setOpenToday("开盘");
        item.setTradeCount("交易量");
        mData.add(item);

        LinearLayoutManager layoutHeadManager = new LinearLayoutManager(getContext());
        recyclerViewHead.setLayoutManager(layoutHeadManager);
        headAdapter = new favoriteListHeadAdapter(mData);
        recyclerViewHead.setAdapter(headAdapter);

        FullyLinearLayoutManager layoutManager= new FullyLinearLayoutManager(getContext());
//        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new FavoriteListAdapter(mData);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new FavoriteListAdapter.MyItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                new MyToast().shortToast(getContext(),"onItemClick "+mData.get(position).getNowPrice());
//                searchStock("002431");
            }
        });
        adapter.setOnItemLongClickListener(new FavoriteListAdapter.MyItemLongClickListener() {
            @Override
            public void onItemLongClick(View view, int position) {
                new MyToast().shortToast(getContext(),"onItemLongClick "+mData.get(position).getNowScale());
            }
        });
//        recyclerView.smoothScrollToPosition(mData.size());
    }
    public void deleteFavoriteStock(FavoriteListItem item){
        for (int i=0;i<mData.size();i++){
            if(mData.get(i).getId().equals(item.getId())){
                mData.remove(i);
                break;
            }
        }
        adapter.notifyDataSetChanged();
        headAdapter.notifyDataSetChanged();
    }
    public void addFavoriteStock(FavoriteListItem item){
        mData.add(item);
        adapter.notifyDataSetChanged();
        headAdapter.notifyDataSetChanged();
    }
    public Handler favoriteListHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what){
                case 1: //add
                    addFavoriteStock((FavoriteListItem) msg.obj);
                    break;
                case 2://delete
                    break;
            }
            return true;
        }
    });
    public Handler getFavoriteListHandler()
    {
        return favoriteListHandler;
    }
}
