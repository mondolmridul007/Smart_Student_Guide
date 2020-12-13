package com.example.smartstudentguide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Student_Register extends AppCompatActivity {

    private EditText student_username, student_register_email, student_register_password;
    private Button student_register;
    private TextView student_register_to_login;
    private String name, email, password;
    private FirebaseAuth mAuth;
    private DatabaseReference reference;
    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student__register);

        mAuth = FirebaseAuth.getInstance();
        student_username = findViewById(R.id.student_username);
        student_register_email = findViewById(R.id.student_register_email);
        student_register_password = findViewById(R.id.student_register_password);
        student_register = findViewById(R.id.student_register);
        student_register_to_login = findViewById(R.id.student_register_to_login);
        reference = FirebaseDatabase.getInstance().getReference();
        pd = new ProgressDialog(this);

        student_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateUser();
            }
        });

        student_register_to_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Student_Register.this, Student_Login.class));
                finish();
            }
        });

    }

    private void validateUser() {
        name = student_username.getText().toString();
        email = student_register_email.getText().toString();
        password = student_register_password.getText().toString();

        if(name.isEmpty() || email.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Please fill all the fields.", Toast.LENGTH_SHORT).show();
        }
        else{
            registerUser();
        }
    }

    private void registerUser() {

        pd.setMessage("Registering...");
        pd.show();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            pd.dismiss();

                            FirebaseUser user = mAuth.getCurrentUser();
                            user.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(Student_Register.this, "Verification email has been sent", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(Student_Register.this, "Error : "+e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                            /*String currentUserId=mAuth.getCurrentUser().getUid();
                            reference.child("User").child(currentUserId).setValue(name);*/
                            Toast.makeText(Student_Register.this,"Account created successfully...",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Student_Register.this, Student_Login.class));
                            finish();
                        }else{
                            pd.dismiss();
                            Toast.makeText(Student_Register.this, "Error : "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
}