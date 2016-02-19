package com.igs.androidbasico.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.igs.androidbasico.R;

import java.util.ArrayList;

/**
 * Created by isgarsi on 07/07/15.
 */
public class RViewAdapter extends RecyclerView.Adapter<RViewHolder> implements View.OnClickListener{
    private ArrayList<String> items;
    private View.OnClickListener clickListener;
    private Context context;

    public RViewAdapter(Context context, ArrayList<String> data) {
        this.items = data;
        this.context = context;
    }

    @Override
    public RViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rec_view_item, viewGroup, false);
        itemView.setOnClickListener(this);
        RViewHolder tvh = new RViewHolder(itemView, context);
        return tvh;
    }

    @Override
    public void onBindViewHolder(RViewHolder viewHolder, int pos) {
        String name = items.get(pos);
        viewHolder.bindItem(name);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.clickListener = listener;
    }

    @Override
    public void onClick(View view) {
        if(clickListener != null) {
            clickListener.onClick(view);
        }
    }
}
