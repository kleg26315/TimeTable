package com.example.timetabletest;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.timetabletest.Model.LectureData;
import com.example.timetabletest.Util.Task;
import com.example.timetabletest.Util.Task_delete;
import com.example.timetabletest.Util.Task_detail;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class DetailActivity extends AppCompatActivity {
    String post_code =null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        String userToken = "0035d7d225158bfb9a5b3b4437961b9e";

        TextView lecture = (TextView) findViewById(R.id.textLecture);
        TextView strTime = (TextView) findViewById(R.id.textStrtime);
        TextView endTime = (TextView) findViewById(R.id.textEndtime);
        TextView dayofweek = (TextView) findViewById(R.id.textDayofweek);
        TextView code = (TextView) findViewById(R.id.textCode);
        TextView professor = (TextView) findViewById(R.id.textProfessor);
        TextView location = (TextView) findViewById(R.id.textLocation);
        TextView detail = (TextView) findViewById(R.id.textDetail);

        final Button addBtn = (Button) findViewById(R.id.addbtn);
        Button delBtn = (Button) findViewById(R.id.deleteBtn);

        String mylecture =null;

        Intent intent = getIntent();
        lecture.setText(intent.getStringExtra("lecture"));
        strTime.setText(intent.getStringExtra("strTime"));
        endTime.setText(intent.getStringExtra("endTime"));
        dayofweek.setText(intent.getStringExtra("dayofweek"));
        code.setText(intent.getStringExtra("code"));
        professor.setText(intent.getStringExtra("professor"));
        location.setText(intent.getStringExtra("location"));

        post_code = intent.getStringExtra("code");

        try {
            mylecture = new Task().execute("/timetable?user_key="+userToken).get();
            if(mylecture.contains(post_code)) {
                addBtn.setText("메모 추가");
            } else {
                addBtn.setText("강의 추가");
            }
        } catch (InterruptedException e){
            e.printStackTrace();
        } catch (ExecutionException e){
            e.printStackTrace();
        }

        String result_Detail=null;
        try {
            result_Detail = new Task().execute("/lectures?code="+intent.getStringExtra("code")).get();
        } catch (InterruptedException e){
            e.printStackTrace();
        } catch (ExecutionException e){
            e.printStackTrace();
        }
        detail.setText(result_Detail);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(addBtn.getText().toString().equals("강의 추가"))
                {
                    try {
                        new Task_detail().execute(post_code).get();
                    } catch (InterruptedException e){
                        e.printStackTrace();
                    } catch (ExecutionException e){
                        e.printStackTrace();
                    }

                    Intent intenthome = new Intent(getApplicationContext(), MainActivity.class);
                    intenthome.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intenthome.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(intenthome);
                    finish();
                } else {
                    //TODO 메모추가
                }

            }
        });

        delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    new Task_delete().execute(post_code).get();
                } catch (InterruptedException e){
                    e.printStackTrace();
                } catch (ExecutionException e){
                    e.printStackTrace();
                }
                Toast.makeText(DetailActivity.this, "삭제 완료", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
