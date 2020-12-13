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
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Student_Login extends AppCompatActivity {

    private EditText student_login_email, student_login_password;
    private Button student_login;
    private TextView student_forget_password, student_registor;
    private String email, password;

    FirebaseAuth mAuth;
    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student__login);

        mAuth = FirebaseAuth.getInstance();

        student_login_email = findViewById(R.id.student_login_email);
        student_login_password = findViewById(R.id.student_login_password);
        student_login = findViewById(R.id.student_login);
        student_registor = (TextView)findViewById(R.id.student_registor);
        student_forget_password = (TextView)findViewById(R.id.student_forget_password);
        pd = new ProgressDialog(this);

        student_registor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Student_Login.this, Student_Register.class));
                finish();
            }
        });

        student_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateUser();
            }
        });

        student_forget_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Student_Login.this, Forget_Password.class));
                finish();
            }
        });

    }

    private void validateUser() {

        email = student_login_email.getText().toString();
        password = student_login_password.getText().toString();

        if(email.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Please fill all the fields.", Toast.LENGTH_SHORT).show();
        }
        else{
            loginUser();
        }

    }
/*
    @Override
    protected void onStart() {
        super.onStart();
        if(mAuth.getCurrentUser()!=null){
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }
*/
    private void loginUser() {

        pd.setMessage("Logging In...");
        pd.show();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            pd.dismiss();
                            if(mAuth.getCurrentUser().isEmailVerified()) {
                                Toast.makeText(Student_Login.this, "Successfully Logged In.", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Student_Login.this, MainActivity.class));
                                finish();
                            }else{
                                Toast.makeText(Student_Login.this, "Please verify your email address.", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            pd.dismiss();
                            Toast.makeText(Student_Login.this, "Error : " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });

    }
}