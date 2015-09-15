package com.tingler.challenge.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.tingler.challenge.R;
import com.tingler.challenge.util.OptionsData;

/**
 * Created by Rahul on 8/11/2015.
 */
public class OptionAdapter extends RecyclerView.Adapter<OptionAdapter.MyViewHolder> {
LayoutInflater inflater;
    List<OptionsData> itemDatas= Collections.emptyList();
    public OptionAdapter(Context context,List<OptionsData> datas)
    {
        this.itemDatas=datas;

        inflater = LayoutInflater.from(context);

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.custom_row,parent,false);
        MyViewHolder myViewHolder=new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
    	OptionsData data=itemDatas.get(position);
        holder.textView.setText(data.option);

    }

    @Override
    public int getItemCount() {
        return 8;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        public MyViewHolder(View itemView) {
            super(itemView);
            textView=(TextView)itemView.findViewById(R.id.itemText);


        }
    }
}
