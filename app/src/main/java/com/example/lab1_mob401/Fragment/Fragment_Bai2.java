package com.example.lab1_mob401.Fragment;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.lab1_mob401.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class Fragment_Bai2 extends Fragment {
    private ImageView imageView;
    private TextView textView;
    private Button btnLoad;
    private ProgressDialog progressDialog;
    private Bitmap bitmap = null;
    private String link = "https://media.makeameme.org/created/sss-1b48d116a2.jpg";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bai2, container, false);

        this.initViewByID(view);

        this.initClickListner();
        return view;
    }

    private void initClickListner() {
        this.btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog = ProgressDialog.show(getActivity(), "", "Đang tải xuống ... ");
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        bitmap = loadBitmap(link);
                        Message message = handler.obtainMessage();
                        Bundle bundle = new Bundle();
                        String threadMessage = "Đã tải ảnh thành công";
                        bundle.putString("message", threadMessage);
                        message.setData(bundle);
                        handler.sendMessage(message);
                    }
                };
                Thread thread = new Thread(runnable);
                thread.start();
            }
        });
    }

    private void initViewByID(View view) {
        imageView = view.findViewById(R.id.image);
        textView = view.findViewById(R.id.tv_Message);
        btnLoad = view.findViewById(R.id.btn);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            Bundle bundle = msg.getData();
            String message = bundle.getString("message");
            textView.setText(message);
            imageView.setImageBitmap(bitmap);
            progressDialog.dismiss();
        }
    };

    private Bitmap loadBitmap(String link) {
        try {
            URL url = new URL(link);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.connect();
            InputStream inputStream = httpURLConnection.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            return bitmap;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}