package com.example.lab1_mob401.Asyntask;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.TextView;

public class AsynctankBai4 extends AsyncTask<String, String, String> {
    private String message;
    private ProgressDialog progressDialog;
    private EditText edTime;
    private TextView tvResult;


    public AsynctankBai4(EditText edTime, TextView tvResult, Context context) {
        this.edTime = edTime;
        this.tvResult = tvResult;
        this.progressDialog = new ProgressDialog(context);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog.setMessage("Chờ trong " + edTime.getText().toString() + " Giây");
        progressDialog.show();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
        tvResult.setText(s);

    }

    @Override
    protected String doInBackground(String... strings) {
        publishProgress("Đang nghỉ ...");
        try {
            int time = Integer.parseInt(strings[0]) * 1000;
            Thread.sleep(time);
            message = "Ngủ cho " + strings[0] + " Giây";
        } catch (Exception e) {
            e.printStackTrace();
            message = e.getMessage();
        }
        return message;
    }
}
