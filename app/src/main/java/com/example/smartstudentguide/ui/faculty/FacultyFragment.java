package com.example.smartstudentguide.ui.faculty;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.smartstudentguide.R;
import com.example.smartstudentguide.faculty.AddTeacher;
import com.example.smartstudentguide.faculty.TeacherAdapter;
import com.example.smartstudentguide.faculty.TeacherData;
import com.example.smartstudentguide.faculty.UpdateFaculty;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class FacultyFragment extends Fragment {

    private RecyclerView electricalDept, cseDept, mechanicalDept, civilDept;
    private LinearLayout electricalNoData, cseNoData, mechanicalNoData, civilNoData;
    private List<StudentTeacherData> list1, list2, list3, list4;
    private DatabaseReference reference, dbref;
    private StudentTeacherAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_faculty, container, false);

        electricalNoData = view.findViewById(R.id.electricalNoData);
        cseNoData = view.findViewById(R.id.cseNoData);
        mechanicalNoData = view.findViewById(R.id.mechanicalNoData);
        civilNoData = view.findViewById(R.id.civilNoData);

        electricalDept = view.findViewById(R.id.electricalDept);
        cseDept = view.findViewById(R.id.cseDept);
        mechanicalDept = view.findViewById(R.id.mechanicalDept);
        civilDept = view.findViewById(R.id.civilDept);

        reference = FirebaseDatabase.getInstance().getReference().child("teacher");

        electricalDept();

        cseDept();

        mechanicalDept();

        civilDept();

        return view;
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
                        StudentTeacherData data = snap.getValue(StudentTeacherData.class);
                        list4.add(data);
                    }
                    civilDept.setHasFixedSize(true);
                    civilDept.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new StudentTeacherAdapter(list4, getContext());
                    civilDept.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Database Can not be shown", Toast.LENGTH_SHORT).show();
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
                        StudentTeacherData data = snap.getValue(StudentTeacherData.class);
                        list3.add(data);
                    }
                    mechanicalDept.setHasFixedSize(true);
                    mechanicalDept.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new StudentTeacherAdapter(list3, getContext());
                    mechanicalDept.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Database Can not be shown", Toast.LENGTH_SHORT).show();
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
                        StudentTeacherData data = snap.getValue(StudentTeacherData.class);
                        list2.add(data);
                    }
                    cseDept.setHasFixedSize(true);
                    cseDept.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new StudentTeacherAdapter(list2, getContext());
                    cseDept.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Database Can not be shown", Toast.LENGTH_SHORT).show();
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
                        StudentTeacherData data = snap.getValue(StudentTeacherData.class);
                        list1.add(data);
                    }
                    electricalDept.setHasFixedSize(true);
                    electricalDept.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new StudentTeacherAdapter(list1, getContext());
                    electricalDept.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Database Can not be shown", Toast.LENGTH_SHORT).show();
            }
        });
    }
    }