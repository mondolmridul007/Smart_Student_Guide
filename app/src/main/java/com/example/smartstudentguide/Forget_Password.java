package com.example.smartstudentguide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Forget_Password extends AppCompatActivity {

    private EditText student_forget_password_email;
    private Button student_forget_password;
    private String email;
    FirebaseAuth mAuth;
    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget__password);

        mAuth = FirebaseAuth.getInstance();
        pd = new ProgressDialog(this);
        student_forget_password_email = findViewById(R.id.student_forget_password_email);
        student_forget_password = findViewById(R.id.student_forget_password);

        student_forget_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = student_forget_password_email.getText().toString();

                if(email.isEmpty()){
                    Toast.makeText(Forget_Password.this, "Enter your Email", Toast.LENGTH_SHORT).show();
                }else{
                    forgetpassword();
                }
            }
        });
    }

    private void forgetpassword() {

        pd.setMessage("Reseting Password...");
        pd.show();

        mAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            pd.dismiss();
                            Toast.makeText(Forget_Password.this, "Check your email", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Forget_Password.this, Student_Login.class));
                            finish();
                        }
                    }
                });

    }
}