package com.example.smartstudentguide.ui.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.transition.Slide;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.smartstudentguide.R;
import com.smarteist.autoimageslider.DefaultSliderView;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderLayout;

public class HomeFragment extends Fragment {

    private SliderLayout sliderLayout;
    private ImageView map;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        sliderLayout = view.findViewById(R.id.slider);
        sliderLayout.setIndicatorAnimation(IndicatorAnimations.FILL);
        sliderLayout.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderLayout.setScrollTimeInSec(1);

        setSliderViews();

        map = view.findViewById(R.id.map);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMap();
            }
        });

        return view;
    }

    private void openMap() {
        Uri uri = Uri.parse("geo:0, 0?q=Rajshahi University of Engineering & Technology");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.setPackage("com.google.android.apps.maps");
        startActivity(intent);
    }

    private void setSliderViews() {

        for(int i=0; i<5; i++){

            DefaultSliderView sliderView = new DefaultSliderView(getContext());

            switch (i){

                case 0:
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/smart-student-guide.appspot.com/o/gallery%2F%5BB%40c83c963jpg?alt=media&token=358cbb06-9563-4f7b-9b52-50a89b221f68");
                    break;
                    case 1:
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/smart-student-guide.appspot.com/o/gallery%2F%5BB%4056f637ajpg?alt=media&token=a2bef393-e6ce-4c60-8365-30ac66850459");
                    break;
                    case 2:
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/smart-student-guide.appspot.com/o/gallery%2F%5BB%40d6e4075jpg?alt=media&token=ec59b770-a341-4525-9085-aa345ccf2903");
                    break;
                    case 3:
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/smart-student-guide.appspot.com/o/gallery%2F%5BB%40d87189ajpg?alt=media&token=e1234492-8c03-4d62-8372-f5dc95a9264c");
                    break;
                    case 4:
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/smart-student-guide.appspot.com/o/gallery%2F%5BB%40f2c01ffjpg?alt=media&token=8415010b-a5e7-4d59-a98e-4ab0d3d8081a");
                    break;

            }

            sliderView.setImageScaleType(ImageView.ScaleType.CENTER_CROP);
            sliderLayout.addSliderView(sliderView);

        }

    }
}