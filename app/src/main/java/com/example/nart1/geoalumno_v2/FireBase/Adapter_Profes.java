package com.example.nart1.geoalumno_v2.FireBase;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nart1.geoalumno_v2.Activities.MapsActivity;
import com.example.nart1.geoalumno_v2.R;

import java.util.ArrayList;

public class Adapter_Profes extends RecyclerView.Adapter<Adapter_Profes.ProfesViewHolder>{

    private ArrayList<Profesores> profes;
    private Context context;
    public static final double[] lat = {0};
    public static final double[] longi = {0};

    public Adapter_Profes(Context ctx,ArrayList<Profesores> p) {

        profes = p;
        context = ctx;
    }

    public ProfesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from (parent.getContext ()).inflate (R.layout.recycler_view, null , false);
        return new ProfesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProfesViewHolder holder, int position) {

        final Profesores profe = profes.get(position);
        holder.setData (profe.nombre,profe.carrera);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Intent intent = new Intent(context, MapsActivity.class);

                context.startActivity(intent);
                /*Intent intentmap = new Intent(v.getContext(), MapsActivity.class);
                context.startActivity(intentmap);
                // lat[0] = profe.latitud;
                //longi[0] = profe.longitud;*/

            }
        });
    }

    @Override
    public int getItemCount() {

        return profes.size();
    }


    class ProfesViewHolder extends RecyclerView.ViewHolder{

        TextView textView1,textView2;

        public ProfesViewHolder(View itemView) {
            super(itemView);

            textView1 = itemView.findViewById (R.id.textview1);
            textView2 = itemView.findViewById (R.id.textview2);
        }

        public void setData(String nombre, String carrera) {

            textView1.setText(nombre);
            textView2.setText(carrera);
        }
    }
}
