package com.example.covid_beds_information_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

public class SignupActivity extends AppCompatActivity {
    EditText etSignupEmail,etSignupPassword1,etSignupPassword2;
    Button btnSignupSignup;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        etSignupEmail = findViewById(R.id.etSignupEmail);
        etSignupPassword1 = findViewById(R.id.etSignupPassword1);
        etSignupPassword2 = findViewById(R.id.etSignupPassword2);
        btnSignupSignup = findViewById(R.id.btnSignupSignup);
        firebaseAuth = FirebaseAuth.getInstance();

        btnSignupSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String em = etSignupEmail.getText().toString();
                String pw1 = etSignupPassword1.getText().toString();
                String pw2 = etSignupPassword2.getText().toString();
                if(pw1.equals(pw2)){
                    firebaseAuth.createUserWithEmailAndPassword(em,pw1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Intent intent = new Intent(SignupActivity.this,MainActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(SignupActivity.this, "issue "+task.getException(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    Toast.makeText(SignupActivity.this, "Password  did not match ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}