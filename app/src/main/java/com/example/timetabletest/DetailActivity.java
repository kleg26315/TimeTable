package com.example.timetabletest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.timetabletest.Model.LectureData;
import com.example.timetabletest.Util.Task;
import com.example.timetabletest.Util.Task_detail;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView lecture = (TextView) findViewById(R.id.textLecture);
        TextView strTime = (TextView) findViewById(R.id.textStrtime);
        TextView endTime = (TextView) findViewById(R.id.textEndtime);
        TextView dayofweek = (TextView) findViewById(R.id.textDayofweek);
        TextView code = (TextView) findViewById(R.id.textCode);
        TextView professor = (TextView) findViewById(R.id.textProfessor);
        TextView location = (TextView) findViewById(R.id.textLocation);
        TextView detail = (TextView) findViewById(R.id.textDetail);
        Button addBtn = (Button) findViewById(R.id.addbtn);

        Intent intent = getIntent();
        lecture.setText(intent.getStringExtra("lecture"));
        strTime.setText(intent.getStringExtra("strTime"));
        endTime.setText(intent.getStringExtra("endTime"));
        dayofweek.setText(intent.getStringExtra("dayofweek"));
        code.setText(intent.getStringExtra("code"));
        professor.setText(intent.getStringExtra("professor"));
        location.setText(intent.getStringExtra("location"));

        String result_Detail=null;
        try {
            result_Detail = new Task_detail().execute("?code=PG1807-22").get();
        } catch (InterruptedException e){
            e.printStackTrace();
        } catch (ExecutionException e){
            e.printStackTrace();
        }
        detail.setText(result_Detail);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO 여기에 if 추가해서 id토큰 보내서 강의 있는지없는지 확인해서 인텐트를 어디로보낼지 구별해야함 버튼 setText도 해서 메모추가로 바꿔야함
                Intent intenthome = new Intent(getApplicationContext(), MainActivity.class);
                intenthome.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intenthome.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intenthome);
                finish();
            }
        });

    }

    public void getDetail()
    {
        String resultText;

        try {
            resultText = new Task().execute().get();


        } catch (InterruptedException e){
            e.printStackTrace();
        } catch (ExecutionException e){
            e.printStackTrace();
        }

    }
}
