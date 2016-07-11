package andrewlt.mystock.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.List;

import andrewlt.mystock.R;

/**
 * Created by liut1 on 7/3/16.
 */
public class FavoriteListAdapter extends RecyclerView.Adapter<FavoriteListAdapter.MyViewHolder>{
    private MyItemClickListener mItemClickListener;
    private MyItemLongClickListener mItemLongClickListener;
    public interface MyItemClickListener {
        public void onItemClick(View view,int position);
    }
    public interface MyItemLongClickListener {
        public void onItemLongClick(View view,int position);
    }
    private List<FavoriteListItem> mData;
    public FavoriteListAdapter(List<FavoriteListItem> mData){
        this.mData = mData;
    }
    @Override
    public FavoriteListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.favorite_list_item, parent, false);
        holder = new MyViewHolder(view,mItemClickListener,mItemLongClickListener);
        return holder;
    }

    /**
     * 设置Item点击监听
     * @param listener
     */
    public void setOnItemClickListener(MyItemClickListener listener){
        this.mItemClickListener = listener;
    }

    public void setOnItemLongClickListener(MyItemLongClickListener listener){
        this.mItemLongClickListener = listener;
    }
    @Override
    public void onBindViewHolder(FavoriteListAdapter.MyViewHolder holder, int position) {
        holder.tv_nowPrice.setText(mData.get(position).getNowPrice());
        holder.tv_nowScale.setText(mData.get(position).getNowScale());
        holder.tv_tradeCount.setText(mData.get(position).getTradeCount());
        holder.tv_openToday.setText(mData.get(position).getOpenToday());
        holder.itemView.setTag(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        private MyItemClickListener mClickListener;
        private MyItemLongClickListener mLongClickListener;
        TextView tv_nowPrice,tv_nowScale,tv_tradeCount,tv_openToday;
        LinearLayout linearLayout;
        public MyViewHolder(View view,MyItemClickListener clickListener,MyItemLongClickListener longClickListener)
        {
            super(view);
            mClickListener = clickListener;
            mLongClickListener = longClickListener;
            tv_nowPrice = (TextView) view.findViewById(R.id.favorite_list_stock_nowprice);
            tv_nowScale = (TextView) view.findViewById(R.id.favorite_list_stock_nowscale);
            tv_tradeCount = (TextView) view.findViewById(R.id.favorite_list_stock_tradecount);
            tv_openToday = (TextView) view.findViewById(R.id.favorite_list_stock_opentoday);
            linearLayout = (LinearLayout) view.findViewById(R.id.favorite_list_scroll);
            linearLayout.setOnClickListener(this);
            linearLayout.setOnLongClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if(mClickListener != null){
                mClickListener.onItemClick(v,getPosition());
            }
            return;
        }

        @Override
        public boolean onLongClick(View v) {
            if(mLongClickListener != null){
                mLongClickListener.onItemLongClick(v,getPosition());
            }
            return true;
        }
    }
}
