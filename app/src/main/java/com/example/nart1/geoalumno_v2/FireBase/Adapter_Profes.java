package com.example.nart1.geoalumno_v2.FireBase;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.nart1.geoalumno_v2.Coordinate;
import com.example.nart1.geoalumno_v2.R;

import java.util.ArrayList;

public class Adapter_Profes extends RecyclerView.Adapter<Adapter_Profes.ProfesViewHolder>{

    private final Coordinate coordinate;
    private ArrayList<Profesores> profes;
    public static double lat=0,longi=0;

    public Adapter_Profes(ArrayList<Profesores> p, Coordinate coordinate) {

        this.profes = p;
        this.coordinate = coordinate;
    }

    public ProfesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from (parent.getContext ()).inflate (R.layout.recycler_view, null , false);
        return new ProfesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProfesViewHolder holder, final int position) {

        final Profesores profe = profes.get(position);
        holder.setData (profe.nombre,profe.carrera);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                lat = profes.get(position).latitud;
                longi = profes.get(position).longitud;

                coordinate.onIconItemSelected(lat,longi);

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
