package com.itpvt.mediaplayer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by Hassan on 1/28/2019.
 */

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder>{

Context context;
ArrayList<VideoModel> arraylistvid;
Activity activity;

public  VideoAdapter(Context context, ArrayList<VideoModel>arraylistvid , Activity activity){

    this.context=context;
    this.arraylistvid=arraylistvid;
    this.activity=activity;




}

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
     View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom,parent,false);
     return  new VideoAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        Glide.with(context).load("file://" + arraylistvid.get(position).getStrthumb())
                .skipMemoryCache(false)
                .into(holder.img);
      //  holder.rela.setBackgroundColor(Color.parseColor("ffffff"));
        holder.rela.setAlpha(0);

        holder.rela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(context, VideoPlay.class);
                i.putExtra("video",arraylistvid.get(position).getStrpath());
                activity.startActivity(i);


            }
        });




    }

    @Override
    public int getItemCount() {
        return arraylistvid.size();
    }


    public static class  ViewHolder extends  RecyclerView.ViewHolder

{

ImageView img;
RelativeLayout rela;


    public ViewHolder(View itemView) {
        super(itemView);

        img=(ImageView)itemView.findViewById(R.id.imgg);
        rela=(RelativeLayout)itemView.findViewById(R.id.relasec);



    }
}



}
