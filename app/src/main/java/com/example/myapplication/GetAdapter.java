package com.example.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class GetAdapter extends RecyclerView.Adapter<GetAdapter.GetDatViewHolder>  {

    private Context mct;
    private List<GetDat> data;
    private GetDat datal;

    public GetAdapter(Context mct, List<GetDat> data){
        this.mct=mct;
        this.data=data;
    }

    @NonNull
    @Override
    public GetDatViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater=LayoutInflater.from(mct);
        View view=inflater.inflate(R.layout.second_values,null);
        return new GetDatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GetDatViewHolder datasViewHolder, int i) {

        datal=data.get(i);
        new ImageLoadTask(datal.getMessage(), datasViewHolder.subca).execute();
    }




    @Override
    public int getItemCount() {

        return data.size();
    }

    class GetDatViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {

        CardView cardView;
        ImageView subca;

        public GetDatViewHolder(@NonNull View itemView) {
            super(itemView);
            subca=itemView.findViewById(R.id.iv);
            cardView=itemView.findViewById(R.id.card_view);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        }
    }
}
