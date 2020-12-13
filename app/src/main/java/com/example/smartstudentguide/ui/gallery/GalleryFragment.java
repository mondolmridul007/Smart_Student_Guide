package com.example.smartstudentguide.ui.gallery;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.smartstudentguide.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class GalleryFragment extends Fragment {

    RecyclerView semisterFinalRecycler, classTestRecycler;
    GalleryAdapter adapter;
    DatabaseReference reference;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gallery, container, false);

        classTestRecycler = view.findViewById(R.id.classTestRecycler);
        semisterFinalRecycler = view.findViewById(R.id.semisterFinalRecycler);
        reference = FirebaseDatabase.getInstance().getReference().child("gallery");

        classTestImage();

        semisterFinalImage();

        return view;
    }

    private void semisterFinalImage() {

        reference.child("Semister Final").addValueEventListener(new ValueEventListener() {

            List<String> imagelist = new ArrayList<>();

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    String data = (String) dataSnapshot.getValue();
                    imagelist.add(data);
                }
                adapter = new GalleryAdapter(getContext(), imagelist);
                semisterFinalRecycler.setLayoutManager(new GridLayoutManager(getContext(), 3));
                semisterFinalRecycler.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void classTestImage() {

        reference.child("Class Test").addValueEventListener(new ValueEventListener() {

            List<String> imagelist = new ArrayList<>();

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    String data = (String) dataSnapshot.getValue();
                    imagelist.add(data);
                }
                adapter = new GalleryAdapter(getContext(), imagelist);
                classTestRecycler.setLayoutManager(new GridLayoutManager(getContext(), 3));
                classTestRecycler.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}