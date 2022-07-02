package com.example.lab1_mob401.Fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.lab1_mob401.Asyntask.AsynctankBai4;
import com.example.lab1_mob401.R;


public class Fragment_Bai4 extends Fragment {
    private EditText edSeconds;
    private Button btnRun;
    private TextView tvMessage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bai4, container, false);

        this.initViewID(view);

        this.initClickListner();

        return view;
    }

    private void initClickListner() {
        this.btnRun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AsynctankBai4 asynctankBai4=new AsynctankBai4(edSeconds,tvMessage,getActivity());
                String sleepTime=edSeconds.getText().toString();
                asynctankBai4.execute(sleepTime);
            }
        });


    }

    private void initViewID(View view) {
        this.edSeconds = view.findViewById(R.id.ed_seconds);
        this.btnRun = view.findViewById(R.id.btn_Run);
        this.tvMessage = view.findViewById(R.id.message);
    }

}