package com.example.smartstudentguide.ui.notice;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.smartstudentguide.FullImageView;
import com.example.smartstudentguide.R;

import java.util.ArrayList;

public class StudentNoticeAdapter extends RecyclerView.Adapter<StudentNoticeAdapter.NoticeViewAdapter> {

    private Context context;
    private ArrayList<StudentNoticeData> list;

    public StudentNoticeAdapter(Context context, ArrayList<StudentNoticeData> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public NoticeViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.newsfeed_item_layout_student, parent, false);
        return new StudentNoticeAdapter.NoticeViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentNoticeAdapter.NoticeViewAdapter holder, int position) {

        StudentNoticeData currentitem = list.get(position);
        holder.noticeTitle.setText(currentitem.getTitle());
        holder.date.setText(currentitem.getDate());
        holder.time.setText(currentitem.getTime());
        try {
            if (currentitem.getImage() != null)
                Glide.with(context).load(currentitem.getImage()).into(holder.noticeImage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        holder.noticeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, FullImageView.class);
                intent.putExtra("image",currentitem.getImage());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class NoticeViewAdapter extends RecyclerView.ViewHolder {

        private TextView noticeTitle, date, time;
        private ImageView noticeImage;

        public NoticeViewAdapter(@NonNull View itemView) {
            super(itemView);
            noticeTitle = itemView.findViewById(R.id.noticeTitle);
            noticeImage = itemView.findViewById(R.id.noticeImage);
            date = itemView.findViewById(R.id.date);
            time = itemView.findViewById(R.id.time);

        }
    }

}
