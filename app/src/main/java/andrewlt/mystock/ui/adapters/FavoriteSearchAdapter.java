package andrewlt.mystock.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import andrewlt.mystock.R;

/**
 * Created by liut1 on 7/3/16.
 */
public class FavoriteSearchAdapter extends RecyclerView.Adapter<FavoriteSearchAdapter.MyViewHolder> implements View.OnClickListener {
    private MyItemClickListener mItemClickListener;
    public interface MyItemClickListener {
        public void onItemClick(View view, FavoriteListItem data);
    }
    private List<FavoriteListItem> mData;
    public FavoriteSearchAdapter(List<FavoriteListItem> mData){
        this.mData = mData;
    }
    @Override
    public FavoriteSearchAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.favorite_search_item, parent, false);
        view.setOnClickListener(this);
        holder = new MyViewHolder(view,mItemClickListener);
        return holder;
    }

    /**
     * 点击监听
     */
    @Override
    public void onClick(View v) {
        if(mItemClickListener != null){
            mItemClickListener.onItemClick(v,(FavoriteListItem)v.getTag());
        }
    }
    /**
     * 设置Item点击监听
     * @param listener
     */
    public void setOnItemClickListener(MyItemClickListener listener){
        this.mItemClickListener = listener;
    }

    @Override
    public void onBindViewHolder(FavoriteSearchAdapter.MyViewHolder holder, int position) {
        holder.tv_id.setText(mData.get(position).getId());
        holder.tv_name.setText(mData.get(position).getName());
        holder.ib_add.setImageBitmap(mData.get(position).getBitmap());
        holder.itemView.setTag(mData.get(position));
    }
    @Override
    public int getItemCount() {
        return mData.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        MyItemClickListener mlistener;
        TextView tv_name, tv_id;
        ImageButton ib_add;
        public MyViewHolder(View view,MyItemClickListener listener)
        {
            super(view);
            tv_name = (TextView) view.findViewById(R.id.favorite_search_result_name);
            tv_id = (TextView) view.findViewById(R.id.favorite_search_result_id);
            ib_add = (ImageButton) view.findViewById(R.id.favorite_search_result_ib);
            mlistener = listener;
            ib_add.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(mlistener != null){
                mlistener.onItemClick(v,mData.get(getAdapterPosition()));
            }
        }
    }
}
