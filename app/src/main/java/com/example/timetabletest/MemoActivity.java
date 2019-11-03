package com.example.timetabletest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.timetabletest.Util.Task_memo_post;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

public class MemoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo);

        Intent intent = getIntent();
        final String code = intent.getStringExtra("memocode");

        Button memoBtn = (Button) findViewById(R.id.memoBtn);
        final EditText title_edit = (EditText) findViewById(R.id.title_edit);
        final EditText des_edit = (EditText) findViewById(R.id.des_edit);

        final String date = doYearMonthDay();
        Log.d("date", date);

        memoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    //TODO 메모API type key 존재하지만 추가할때 정보가 없음, 한 type 당 하나의 메모만 등록가능
                    new Task_memo_post().execute(code,title_edit.getText().toString(),des_edit.getText().toString(),date).get();
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
            }
        });
    }

    public String doYearMonthDay() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
        Date date = new Date();
        String currentDate = formatter.format(date);
        return currentDate;
    }


}
