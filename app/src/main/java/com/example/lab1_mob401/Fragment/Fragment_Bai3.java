package com.example.lab1_mob401.Fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.lab1_mob401.Asyntask.AsynctaskBai3;
import com.example.lab1_mob401.Interface.Bai3_Interface;
import com.example.lab1_mob401.R;


public class Fragment_Bai3 extends Fragment implements Bai3_Interface {
    private Button btn_click;
    private ImageView imageView;
    private TextView tvMessage;
    private String urlImage = "https://www.facebook.com/images/fb_icon_325x325.png";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment__bai3, container, false);

        initViewId(view);

        this.btn_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AsynctaskBai3(Fragment_Bai3.this, getContext()).execute(urlImage);
            }
        });


        return view;
    }

    private void initViewId(View view) {
        this.btn_click = view.findViewById(R.id.btn);
        this.imageView = view.findViewById(R.id.image);
        this.tvMessage = view.findViewById(R.id.tv_Message);
    }

    @Override
    public void loadImage(Bitmap bitmap) {
        imageView.setImageBitmap(bitmap);
    }

    @Override
    public void error() {
        tvMessage.setText("Error");

    }
}