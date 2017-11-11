package com.example.nart1.geoalumno_v2.FireBase;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.nart1.geoalumno_v2.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Teachers_List extends AppCompatActivity{

    RecyclerView recyclerView;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_list);

        recyclerView = (RecyclerView) findViewById(R.id.recycler) ;

        LinearLayoutManager layoutManager = new LinearLayoutManager (this, LinearLayoutManager.VERTICAL, false);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration (this, layoutManager.getOrientation ());

        recyclerView.setLayoutManager (layoutManager);
        recyclerView.addItemDecoration (dividerItemDecoration);

        Data_Extraction();

    }
    public void Data_Extraction() {
        ref.child("FIUV").child("Maestros").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                ArrayList<Profesores> profesores = new ArrayList<>();

                for (DataSnapshot entry : dataSnapshot.getChildren()) {

                    Profesores profe = new Profesores();
                    DataSnapshot foo;

                    foo = entry.child("id");
                    profe.id = foo.getValue() != null ? foo.getValue().toString() : "";

                    foo = entry.child("Nombre");
                    profe.nombre = foo.getValue() != null ? foo.getValue().toString() : "";

                    foo = entry.child("Carrera");
                    profe.carrera = foo.getValue() != null ? foo.getValue().toString() : "";

                    foo = entry.child("Latitud");
                    profe.latitud = foo.getValue() != null ? Double.parseDouble(foo.getValue().toString()) : 0;

                    foo = entry.child("Longitud");
                    profe.longitud = foo.getValue() != null ? Double.parseDouble(foo.getValue().toString()) : 0;

                    profesores.add(profe);

                }

                recyclerView.setAdapter(new Adapter_Profes(getBaseContext(),profesores));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });
    }
}
