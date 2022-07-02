package com.example.lab1_mob401.Asyntask;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import com.example.lab1_mob401.Interface.Bai3_Interface;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class AsynctaskBai3 extends AsyncTask<String, Void, Bitmap> {
    private Bai3_Interface lab1Interface;
    private ProgressDialog progressDialog;

    public AsynctaskBai3(Bai3_Interface lab1Interface, Context context) {
        this.lab1Interface = lab1Interface;
        this.progressDialog = new ProgressDialog(context);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog.setMessage("Đang tải ảnh ... ");
        progressDialog.show();
    }

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
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
        if (bitmap != null) {
            lab1Interface.loadImage(bitmap);
        } else {
            lab1Interface.error();
        }
    }
}
