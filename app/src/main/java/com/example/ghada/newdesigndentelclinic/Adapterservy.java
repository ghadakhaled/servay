package com.example.ghada.newdesigndentelclinic;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class Adapterservy extends RecyclerView.Adapter<Adapterservy.MyViewHolder> {

    List<servy> servylist;
    LayoutInflater inflater;
    Context context;

    public Adapterservy(List<servy> servylist, Context context) {
        this.servylist = servylist;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view ;
        LayoutInflater mInflater = LayoutInflater.from(context);
        view = mInflater.inflate(R.layout.cardveiw_item_book,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.title_servy.setText(servylist.get(position).getTitle());
        holder.img_servy.setImageResource(servylist.get(position).getImage());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context,Book_Activity.class);

                // passing data to the book activity
                intent.putExtra("Title",servylist.get(position).getTitle());
                intent.putExtra("Description",servylist.get(position).getDescription());
                intent.putExtra("Thumbnail",servylist.get(position).getImage());
                // start the activity
                context.startActivity(intent);

            }
        });



    }

    @Override
    public int getItemCount() {
        return servylist.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title_servy,des_servy;
        ImageView img_servy;
        CardView cardView ;

        public MyViewHolder(View itemView) {
            super(itemView);
            title_servy = (TextView) itemView.findViewById(R.id.title_servy) ;
            img_servy = (ImageView) itemView.findViewById(R.id.book_img_id);
            cardView = (CardView) itemView.findViewById(R.id.cardview_id);


        }
    }


}
