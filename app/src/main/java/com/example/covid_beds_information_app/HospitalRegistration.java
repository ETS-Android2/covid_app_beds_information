package com.example.covid_beds_information_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class HospitalRegistration extends AppCompatActivity {
    EditText et_Hr_reg,et_Hr_name,et_Hr_Address,et_Hr_Beds,et_Hr_Ventilators,et_Hr_Phone;
    Button btn_Hr_Reg;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_registration);

        et_Hr_reg = findViewById(R.id.et_Hr_reg);
        et_Hr_name = findViewById(R.id.et_Hr_name);
        et_Hr_Address = findViewById(R.id.et_Hr_Address);
        et_Hr_Beds = findViewById(R.id.et_Hr_Beds);
        et_Hr_Ventilators = findViewById(R.id.et_Hr_Ventilators);
        et_Hr_Phone = findViewById(R.id.et_Hr_Phone);
        btn_Hr_Reg = findViewById(R.id.btn_Hr_Reg);
        firebaseDatabase = FirebaseDatabase.getInstance();

        databaseReference=firebaseDatabase.getReference("hospital");
        btn_Hr_Reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int Reg_No = Integer.parseInt(et_Hr_reg.getText().toString());
                 String Hospital_Name = et_Hr_name.getText().toString();
                 String Hospital_Address = et_Hr_Address.getText().toString();
                 int Hospital_No_Of_Beds = Integer.parseInt(et_Hr_Beds.getText().toString());
                int Hospital_No_Of_Ventilators = Integer.parseInt(et_Hr_Ventilators.getText().toString());
                long Hospital_Phone = Long.parseLong(et_Hr_Phone.getText().toString());
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull  DataSnapshot snapshot) { 
                        if(snapshot.hasChild(String.valueOf(Reg_No))){
                            Toast.makeText(HospitalRegistration.this, "already exists ", Toast.LENGTH_SHORT).show();
                        } else {
                            Hospital h = new Hospital(Reg_No,
                                    Hospital_Name,
                                    Hospital_Address,
                                    Hospital_No_Of_Beds,
                                    Hospital_No_Of_Ventilators,
                                    Hospital_Phone);
                            databaseReference.child(String.valueOf(Reg_No)).setValue(h);
                            Toast.makeText(HospitalRegistration.this, "record added", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull  DatabaseError error) {

                    }
                });


            }
        });

    }
}