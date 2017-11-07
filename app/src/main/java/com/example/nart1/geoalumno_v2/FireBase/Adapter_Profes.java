package com.example.nart1.geoalumno_v2.FireBase;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nart1.geoalumno_v2.R;

import java.util.ArrayList;
import java.util.Locale;

public class Adapter_Profes extends RecyclerView.Adapter<Adapter_Profes.ProfesViewHolder>{

    private ArrayList<Profesores> profes;

    public Adapter_Profes(ArrayList<Profesores> p) {

        profes = p;
    }

    public ProfesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from (parent.getContext ()).inflate (R.layout.recycler_view, null , false);
        return new ProfesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProfesViewHolder holder, int position) {

        Profesores profe = profes.get(position);
        holder.setData (profe.id,profe.nombre,profe.carrera);
    }

    @Override
    public int getItemCount() {

        return profes.size();
    }


    class ProfesViewHolder extends RecyclerView.ViewHolder{

        TextView textView1,textView2,textView3;

        public ProfesViewHolder(View itemView) {
            super(itemView);

            textView1 = itemView.findViewById (R.id.textview1);
            textView2 = itemView.findViewById (R.id.textview2);
            textView3 = itemView.findViewById (R.id.textview3);
        }

        public void setData(int id, String nombre, String carrera) {

            String s = String.format (Locale.US, "%s", id);
            textView1.setText(s);
            textView2.setText(nombre);
            textView3.setText(carrera);
        }
    }
}
