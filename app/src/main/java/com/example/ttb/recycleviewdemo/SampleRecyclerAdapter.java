package com.example.ttb.recycleviewdemo;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by ttb on 16/6/6.
 */
public class SampleRecyclerAdapter extends RecyclerView.Adapter<SampleRecyclerAdapter.ViewHolder> {

    private  ArrayList<String> sampleData;//; = new ArrayList<>();

    public SampleRecyclerAdapter(ArrayList<String> sampleData) {
        this.sampleData = sampleData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.list_basic_item, parent, false);

        return new ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        final String rowData = sampleData.get(position);

        //  设置要显示的数据
        viewHolder.textViewSample.setText(rowData);
        viewHolder.itemView.setTag(rowData);
        viewHolder.textViewSample2.setText(rowData);

        ViewGroup vg = (ViewGroup)viewHolder.itemView;
        Log.d("TAG", "onBindViewHolder: "+vg.getChildCount());
        if(position%2 == 0){
            vg.removeView(viewHolder.textViewSample2);
        }
    }

    @Override
    public int getItemCount() {
        return sampleData.size();
    }

    //  删除指定的Item
    public void removeData(int position)
    {
        sampleData.remove(position);
        //  通知RecyclerView控件某个Item已经被删除
        notifyItemRemoved(position);

    }
    //  在指定位置添加一个新的Item
    public void addItem(int positionToAdd)
    {
        sampleData.add(positionToAdd,"新的列表项" + new Random().nextInt(10000));
        //  通知RecyclerView控件插入了某个Item
        notifyItemInserted(positionToAdd);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {

        private final TextView textViewSample;
        private final TextView textViewSample2;

        public ViewHolder(View itemView)
        {
            super(itemView);

            textViewSample = (TextView) itemView
                    .findViewById(R.id.content);
            textViewSample2 = (TextView)itemView.findViewById(R.id.content2);
        }
    }
}
