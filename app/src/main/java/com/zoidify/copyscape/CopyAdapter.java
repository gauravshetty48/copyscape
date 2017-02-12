package com.zoidify.copyscape;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by gaurav on 04-02-2017.
 */

public class CopyAdapter extends RecyclerView.Adapter<CopyAdapter.DataObjectHolder> {

    private static String LOG_TAG = "MyRecyclerViewAdapter";
    private ArrayList<CopyData> mDataset;
    private static MyClickListener myClickListener;
    private Activity act;

    public static class DataObjectHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        TextView copiedText, dateTime, category;
        ImageButton pinned;

        public DataObjectHolder(View itemView) {
            super(itemView);
            copiedText = (TextView) itemView.findViewById(R.id.tv_copied_text);
            dateTime = (TextView) itemView.findViewById(R.id.tv_datetime);
            category = (TextView) itemView.findViewById(R.id.tv_category);
            pinned = (ImageButton) itemView.findViewById(R.id.ib_fav);
            Log.i(LOG_TAG, "Adding Listener");
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            myClickListener.onItemClick(getPosition(), v);
        }


    }

    public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    public CopyAdapter(Activity act, ArrayList<CopyData> myDataset) {
        this.act = act;
        mDataset = myDataset;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_copied, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
        holder.copiedText.setText(mDataset.get(position).getCopiedText());
        holder.category.setText(mDataset.get(position).getCategory());
        holder.dateTime.setText(mDataset.get(position).getDateTime());
        if(mDataset.get(position).getPinned()) {
            holder.pinned.setImageResource(R.drawable.ic_fav_checked);
        }

    }

    public void addItem(CopyData dataObj, int index) {
        mDataset.add(dataObj);
        notifyItemInserted(index);
    }

    public void deleteItem(int index) {
        mDataset.remove(index);
        notifyItemRemoved(index);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }
}
