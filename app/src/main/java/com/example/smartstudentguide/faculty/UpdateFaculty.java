package com.example.smartstudentguide.faculty;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.smartstudentguide.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UpdateFaculty extends AppCompatActivity {

    FloatingActionButton fab;
    private RecyclerView electricalDept, cseDept, mechanicalDept, civilDept;
    private LinearLayout electricalNoData, cseNoData, mechanicalNoData, civilNoData;
    private List<TeacherData> list1, list2, list3, list4;
    private DatabaseReference reference, dbref;
    private TeacherAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_faculty);

        electricalNoData = findViewById(R.id.electricalNoData);
        cseNoData = findViewById(R.id.cseNoData);
        mechanicalNoData = findViewById(R.id.mechanicalNoData);
        civilNoData = findViewById(R.id.civilNoData);

        electricalDept = findViewById(R.id.electricalDept);
        cseDept = findViewById(R.id.cseDept);
        mechanicalDept = findViewById(R.id.mechanicalDept);
        civilDept = findViewById(R.id.civilDept);

        reference = FirebaseDatabase.getInstance().getReference().child("teacher");

        electricalDept();

        cseDept();

        mechanicalDept();

        civilDept();

        fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UpdateFaculty.this, AddTeacher.class));
            }
        });
    }

            private void civilDept() {
                dbref = reference.child("Civil Engineering");
                dbref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        list4 = new ArrayList<>();
                        if(!snapshot.exists()){
                            civilNoData.setVisibility(View.VISIBLE);
                            civilDept.setVisibility(View.GONE);
                        }else{
                            civilNoData.setVisibility(View.GONE);
                            civilDept.setVisibility(View.VISIBLE);
                            for(DataSnapshot snap: snapshot.getChildren()){
                                TeacherData data = snap.getValue(TeacherData.class);
                                list4.add(data);
                            }
                            civilDept.setHasFixedSize(true);
                            civilDept.setLayoutManager(new LinearLayoutManager(UpdateFaculty.this));
                            adapter = new TeacherAdapter(list4, UpdateFaculty.this, "Civil Engineering");
                            civilDept.setAdapter(adapter);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(UpdateFaculty.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }

            private void mechanicalDept() {
                dbref = reference.child("Mechanical Engineering");
                dbref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        list3 = new ArrayList<>();
                        if(!snapshot.exists()){
                            mechanicalNoData.setVisibility(View.VISIBLE);
                            mechanicalDept.setVisibility(View.GONE);
                        }else{
                            mechanicalNoData.setVisibility(View.GONE);
                            mechanicalDept.setVisibility(View.VISIBLE);
                            for(DataSnapshot snap: snapshot.getChildren()){
                                TeacherData data = snap.getValue(TeacherData.class);
                                list3.add(data);
                            }
                            mechanicalDept.setHasFixedSize(true);
                            mechanicalDept.setLayoutManager(new LinearLayoutManager(UpdateFaculty.this));
                            adapter = new TeacherAdapter(list3, UpdateFaculty.this, "Mechanical Engineering");
                            mechanicalDept.setAdapter(adapter);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(UpdateFaculty.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }

            private void cseDept() {
                dbref = reference.child("Computer Science & Engineering");
                dbref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        list2 = new ArrayList<>();
                        if(!snapshot.exists()){
                            cseNoData.setVisibility(View.VISIBLE);
                            cseDept.setVisibility(View.GONE);
                        }else{
                            cseNoData.setVisibility(View.GONE);
                            cseDept.setVisibility(View.VISIBLE);
                            for(DataSnapshot snap: snapshot.getChildren()){
                                TeacherData data = snap.getValue(TeacherData.class);
                                list2.add(data);
                            }
                            cseDept.setHasFixedSize(true);
                            cseDept.setLayoutManager(new LinearLayoutManager(UpdateFaculty.this));
                            adapter = new TeacherAdapter(list2, UpdateFaculty.this, "Computer Science & Engineering");
                            cseDept.setAdapter(adapter);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(UpdateFaculty.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }

            private void electricalDept() {
                dbref = reference.child("Electrical & Electronic Engineering");
                dbref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        list1 = new ArrayList<>();
                        if(!snapshot.exists()){
                            electricalNoData.setVisibility(View.VISIBLE);
                            electricalDept.setVisibility(View.GONE);
                        }else{
                            electricalNoData.setVisibility(View.GONE);
                            electricalDept.setVisibility(View.VISIBLE);
                            for(DataSnapshot snap: snapshot.getChildren()){
                                TeacherData data = snap.getValue(TeacherData.class);
                                list1.add(data);
                            }
                            electricalDept.setHasFixedSize(true);
                            electricalDept.setLayoutManager(new LinearLayoutManager(UpdateFaculty.this));
                            adapter = new TeacherAdapter(list1, UpdateFaculty.this, "Electrical & Electronic Engineering");
                            electricalDept.setAdapter(adapter);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(UpdateFaculty.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
}