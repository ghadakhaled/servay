package com.example.ghada.newdesigndentelclinic;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Adapter_reservation extends RecyclerView.Adapter<Adapter_reservation.ViewHolder> {
    private Context context;
    private ArrayList<ReservationClinic> Reserv;

    public Adapter_reservation(Context context, ArrayList<ReservationClinic> Reserv) {
        this.context = context;
        this.Reserv = Reserv;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Log.i("onCreate", "onCreateViewHolder: ");
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.iteamreservation, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i)
    {
        Log.i("onBind", "onBindViewHolder: ");
        final ReservationClinic Revrs = Reserv.get(i);
        viewHolder.date_reservation.setText(Revrs.getDate());
        viewHolder.time_reservation.setText(Revrs.getTime());
        viewHolder.num_reservation.setText(Revrs.getNumreserv());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Log.i("TestClick", "onClick: ");
                Toast.makeText(context,Revrs.getTime(),Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return Reserv.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView date_reservation, time_reservation, num_reservation;
        ImageView user, data, setting, clock;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            date_reservation = itemView.findViewById(R.id.data_reservation);
            num_reservation = itemView.findViewById(R.id.id_reservation);
            time_reservation = itemView.findViewById(R.id.time_reservation);
            user = itemView.findViewById(R.id.user_id);
            data = itemView.findViewById(R.id.data_im);
            setting = itemView.findViewById(R.id.setting);
            clock = itemView.findViewById(R.id.clock);
        }
    }

}
