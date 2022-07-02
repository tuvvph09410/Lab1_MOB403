package com.example.lab1_mob401.Fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.lab1_mob401.R;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;


public class Fragment_Bai1 extends Fragment {
    ImageView imageView;
    Button btnLoad;
    TextView textView;
    String link = "https://images.news18.com/ibnlive/uploads/2022/01/youtube-logo.jpg";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_bai, container, false);

        initViewId(view);

        initClickListener();

        return view;
    }

    private void initClickListener() {
        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final Bitmap bitmap = loadImageFromNetwork(link);
                        imageView.post(new Runnable() {
                            @Override
                            public void run() {
                                textView.setText("Tải ảnh xuống thành công");
                                imageView.setImageBitmap(bitmap);
                            }
                        });
                    }
                });
                thread.start();
            }
        });

    }

    private Bitmap loadImageFromNetwork(String link) {
        URL url;
        Bitmap bitmap = null;
        try {
            url = new URL(link);
            bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }


    private void initViewId(View view) {
        btnLoad = view.findViewById(R.id.btn);
        imageView = view.findViewById(R.id.image);
        textView = view.findViewById(R.id.tv_Message);
    }

}