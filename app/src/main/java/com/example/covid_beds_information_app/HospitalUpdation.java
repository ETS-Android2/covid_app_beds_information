package com.example.covid_beds_information_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class HospitalUpdation extends AppCompatActivity {
    EditText et_Hu_Reg,et_Hu_Beds,et_Hu_Ventilators;
    Button btn_Hu_Update;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_updation);

        et_Hu_Reg = findViewById(R.id.et_Hu_Reg);
        et_Hu_Beds = findViewById(R.id.et_Hu_Beds);
        et_Hu_Ventilators = findViewById(R.id.et_Hu_Ventilators);
        btn_Hu_Update = findViewById(R.id.btn_Hu_Update);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("hospital");

        btn_Hu_Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int Reg_No = Integer.parseInt(et_Hu_Reg.getText().toString());
                int Hospital_No_Of_Beds = Integer.parseInt(et_Hu_Beds.getText().toString());
                int Hospital_No_Of_Ventilators = Integer.parseInt(et_Hu_Ventilators.getText().toString());
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull  DataSnapshot snapshot) {
                        if(snapshot.hasChild(String.valueOf(Reg_No))){
                            databaseReference.child(String.valueOf(Reg_No)).child("hospital_No_Of_Beds").setValue(Hospital_No_Of_Beds);
                            databaseReference.child(String.valueOf(Reg_No)).child("hospital_No_Of_Ventilators").setValue(Hospital_No_Of_Ventilators);
                            Toast.makeText(HospitalUpdation.this, "Record Updated", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(HospitalUpdation.this, "Hospital does not exist ", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull @NotNull DatabaseError error) {

                    }
                });


            }
        });
    }
}