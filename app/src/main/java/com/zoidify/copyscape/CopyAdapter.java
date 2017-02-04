package com.zoidify.copyscape;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by gaurav on 04-02-2017.
 */

public class CopyAdapter extends RecyclerView.Adapter<CopyAdapter.DataObjectHolder> {



    public static class DataObjectHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        TextView title, videoId;
        ImageView thumbailView, onlineOfflineView;

        public DataObjectHolder(View itemView) {
            super(itemView);
            thumbailView = (ImageView) itemView.findViewById(R.id.thumbnail_view);
            onlineOfflineView = (ImageView) itemView.findViewById(R.id.image_online_offline);
            title = (TextView) itemView.findViewById(R.id.tv_video_name);
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

    public VideoListAdapter(Activity act, ArrayList<VideoListData> myDataset) {
        this.act = act;
        mDataset = myDataset;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_single, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
//        Log.d("HIS ADAPTER", mDataset.get(position).getSalonName());
        holder.title.setText(mDataset.get(position).getTitle());

        ImageLoader imageLoader = ImageLoader.getInstance(); // Get singleton instance
        imageLoader.displayImage(mDataset.get(position).getThumbnailUrl(), holder.thumbailView);
        if(mDataset.get(position).getVideoId().equals("1")) {
            holder.onlineOfflineView.setImageResource(R.drawable.on_phone);
        } else {
            holder.onlineOfflineView.setImageResource(R.drawable.on_youtube);
        }

    }

    public void addItem(VideoListData dataObj, int index) {
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
