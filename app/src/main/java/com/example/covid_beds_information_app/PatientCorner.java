package com.example.covid_beds_information_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class PatientCorner extends AppCompatActivity {
    ListView lvPatientCorner;
    Button btnDelete,btnCancel;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    ArrayList<Hospital> h = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_corner);

        lvPatientCorner = findViewById(R.id.lvPatientCorner);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("hospital");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {
                h.clear();
                for(DataSnapshot d : snapshot.getChildren()){
                    Hospital hospital = d.getValue(Hospital.class);
                    h.add(hospital);
                }
                ArrayAdapter<Hospital> a  = new ArrayAdapter<>(PatientCorner.this, android.R.layout.simple_list_item_1,h);
                lvPatientCorner.setAdapter(a);
            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

            }
        });

        lvPatientCorner.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                final Hospital hospital = h.get(i);
                Toast.makeText(PatientCorner.this, ""+hospital, Toast.LENGTH_SHORT).show();
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(PatientCorner.this);
                LayoutInflater layoutInflater = getLayoutInflater();
                View dv = layoutInflater.inflate(R.layout.del,null);
                alertDialog.setView(dv);
                alertDialog.setTitle("Do u want to delete ");
                final AlertDialog a = alertDialog.create();
                a.show();

                btnDelete =dv.findViewById(R.id.btnDelete);
                btnCancel=dv.findViewById(R.id.btnCancel);

                btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DatabaseReference d1 = FirebaseDatabase.getInstance().getReference("hospital").child(String.valueOf(hospital.getReg_No()));
                        d1.removeValue();
                        Toast.makeText(PatientCorner.this, "record deleted ", Toast.LENGTH_SHORT).show();
                        a.dismiss();
                    }
                });
                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        a.dismiss();
                    }
                });

                return true;
            }
        });
    }
}