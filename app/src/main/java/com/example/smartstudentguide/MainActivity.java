package com.example.smartstudentguide;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.smartstudentguide.faculty.UpdateFaculty;
import com.example.smartstudentguide.notice.DeleteNotice;
import com.example.smartstudentguide.studentView.studentMain;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAnalytics mFirebaseAnalytics;

    CardView UploadNotice, addGalleryImage, addEbook, faculty, deleteNotice, studentView;
    private Button student_logout;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseUser user = mAuth.getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        student_logout = findViewById(R.id.student_logout);

        UploadNotice = findViewById(R.id.addNotice);
        addGalleryImage = findViewById(R.id.addGalleryImage);
        addEbook = findViewById(R.id.addEbook);
        faculty = findViewById(R.id.faculty);
        deleteNotice = findViewById(R.id.deleteNotice);
        studentView = findViewById(R.id.studentView);

        UploadNotice.setOnClickListener(this);
        addGalleryImage.setOnClickListener(this);
        addEbook.setOnClickListener(this);
        faculty.setOnClickListener(this);
        deleteNotice.setOnClickListener(this);
        studentView.setOnClickListener(this);

        student_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                startActivity(new Intent(MainActivity.this, Student_Login.class));
                finish();
            }
        });

    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.addNotice:
                intent = new Intent(MainActivity.this, com.example.smartstudentguide.notice.UploadNotice.class);
                startActivity(intent);
                break;

            case R.id.addGalleryImage:
                intent = new Intent(MainActivity.this, UploadImage.class);
                startActivity(intent);
                break;

                case R.id.addEbook:
                intent = new Intent(MainActivity.this, UploadPdf.class);
                startActivity(intent);
                break;

            case R.id.faculty:
                intent = new Intent(MainActivity.this, UpdateFaculty.class);
                startActivity(intent);
                break;

            case R.id.deleteNotice:
            intent = new Intent(MainActivity.this, DeleteNotice.class);
            startActivity(intent);
            break;

            case R.id.studentView:
                intent = new Intent(MainActivity.this, studentMain.class);
                startActivity(intent);
                break;

        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(user == null){
            startActivity(new Intent(this, Student_Login.class));
            finish();
        }

    }
}
