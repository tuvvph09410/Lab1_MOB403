package com.example.lab1_mob401;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class SplashScreen extends AppCompatActivity {
    private ImageView imageView;
    private ProgressDialog progressDialog;
    private final static int SPLASH_TIME_OUT = 3000;
    private String link = "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e7/Instagram_logo_2016.svg/768px-Instagram_logo_2016.svg.png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        this.initViewID();

        this.initProgressDialog();

        this.initAsyncTask();
    }

    private void initAsyncTask() {
        new AsyncTaskLoadImage().execute(link);
    }

    private void initProgressDialog() {
        progressDialog = new ProgressDialog(this);
    }

    private void initViewID() {
        this.imageView = findViewById(R.id.imageView);
    }

    private class AsyncTaskLoadImage extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... strings) {
            try {
                return BitmapFactory.decodeStream((InputStream) new URL(strings[0]).getContent());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setMessage("Đang tải ...");
            progressDialog.show();

        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
            if (bitmap != null) {
                imageView.setImageBitmap(bitmap);
                postDelayed();

            } else {
                imageView.setImageResource(R.drawable.ic_baseline_report_gmailerrorred_24);
            }
        }
    }

    private void postDelayed() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}