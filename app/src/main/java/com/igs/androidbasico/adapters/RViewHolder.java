package com.igs.androidbasico.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.igs.androidbasico.R;

/**
 * Created by isgarsi on 07/07/15.
 */
public class RViewHolder extends RecyclerView.ViewHolder{

    private TextView txtName;


    public RViewHolder(View itemView,Context context){
        super(itemView);
        txtName=(TextView)itemView.findViewById(R.id.item_name);
    }

    public void bindItem(String name){
        txtName.setText(name);
    }
}
