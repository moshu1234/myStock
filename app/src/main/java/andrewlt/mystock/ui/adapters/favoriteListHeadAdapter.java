package andrewlt.mystock.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import andrewlt.mystock.R;

/**
 * Created by liut1 on 7/11/16.
 */
public class favoriteListHeadAdapter  extends RecyclerView.Adapter<favoriteListHeadAdapter.MyHeadViewHolder>{
    private List<FavoriteListItem> mData;
    public favoriteListHeadAdapter(List<FavoriteListItem> mData){
        this.mData = mData;
    }
    @Override
    public favoriteListHeadAdapter.MyHeadViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyHeadViewHolder holder;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.favorite_list_head_item, parent, false);
        holder = new MyHeadViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(favoriteListHeadAdapter.MyHeadViewHolder holder, int position) {
        holder.tv_name.setText(mData.get(position).getName());
        holder.tv_id.setText(mData.get(position).getId());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
    class MyHeadViewHolder extends RecyclerView.ViewHolder{
        TextView tv_name, tv_id;
        public MyHeadViewHolder(View view)
        {
            super(view);
            tv_name = (TextView) view.findViewById(R.id.favorite_list_stock_name);
            tv_id = (TextView) view.findViewById(R.id.favorite_list_stock_id);

        }
    }
}
