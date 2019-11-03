package com.example.timetabletest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.timetabletest.Model.MemoData;
import com.example.timetabletest.Util.MemoAdapter;
import com.example.timetabletest.Util.Task;
import com.example.timetabletest.Util.Task_timetable_delete;
import com.example.timetabletest.Util.Task_timetable_post;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class DetailActivity extends AppCompatActivity {
    ArrayList<MemoData> memoDataList = new ArrayList<MemoData>();
    String post_code =null;
    public ListView listView;
    public MemoAdapter memoAdapter;
    public TextView memo;

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
        listView = (ListView)findViewById(R.id.textMemo);

        memo = (TextView) findViewById(R.id.Memo);

        final Button addBtn = (Button) findViewById(R.id.addbtn);
        Button delBtn = (Button) findViewById(R.id.deleteBtn);

        String mylecture =null;

        Intent intent = getIntent();
        lecture.setText(intent.getStringExtra("lecture"));
        strTime.setText("강의 시간 : "+intent.getStringExtra("strTime"));
        endTime.setText("-  "+intent.getStringExtra("endTime")+"  |");
        dayofweek.setText(intent.getStringExtra("dayofweek"));
        code.setText("교과목 코드 : "+intent.getStringExtra("code"));
        professor.setText("담당 교수 : "+intent.getStringExtra("professor"));
        location.setText("강의실 : "+intent.getStringExtra("location"));

        post_code = intent.getStringExtra("code");
        InitializeLectureData();


        if(memoDataList.size() >= 1) {
            memo.setVisibility(View.VISIBLE);
            memoAdapter = new MemoAdapter(this, memoDataList);
            listView.setAdapter(memoAdapter);
        }



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
            //TODO 강의 상세설명 내용이 있어야하지만 API에 없어서 일단 이렇게 넣음.
            result_Detail = new Task().execute("/lectures?code="+intent.getStringExtra("code")).get();
        } catch (InterruptedException e){
            e.printStackTrace();
        } catch (ExecutionException e){
            e.printStackTrace();
        }
        //detail.setText(result_Detail);
        detail.setText("상세내용 (API에 해당내용 없음)");

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(addBtn.getText().toString().equals("강의 추가"))
                {
                    try {
                        new Task_timetable_post().execute(post_code).get();
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
                    Intent intentmemo = new Intent(getApplicationContext(), MemoActivity.class);
                    intentmemo.putExtra("memocode", post_code);
                    startActivity(intentmemo);

                }

            }
        });

        delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    new Task_timetable_delete().execute(post_code).get();
                } catch (InterruptedException e){
                    e.printStackTrace();
                } catch (ExecutionException e){
                    e.printStackTrace();
                }
                Toast.makeText(DetailActivity.this, "강의 삭제 완료", Toast.LENGTH_SHORT).show();
            }
        });


    }

    public String[] memojsonParser(String jsonString) {
        String[] arraysum = new String[8];

        try {
                JSONArray jarray = new JSONObject(jsonString).getJSONArray("Items");
                for (int i = 0; i < jarray.length(); i++) {
                    JSONObject jObject = jarray.getJSONObject(i);

                    arraysum[0] = jObject.getString("title");
                    arraysum[1] = jObject.getString("user_key");
                    arraysum[2] = jObject.getString("lecture_code");
                    arraysum[3] = jObject.getString("type");
                    arraysum[4] = jObject.getString("description");
                    arraysum[5] = jObject.getString("date");

                    if(arraysum[0] != null) {
                        memoDataList.add(new MemoData(arraysum[0], arraysum[1], arraysum[2], arraysum[3], arraysum[4], arraysum[5]));
                    }
                }


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return arraysum;
    }

    public void InitializeLectureData()
    {
        String recv_memo = null;
        try {
            recv_memo = new Task().execute("/memo?user_key=0035d7d225158bfb9a5b3b4437961b9e"+"&code="+post_code).get();
            memojsonParser(recv_memo);
        } catch (InterruptedException e){
            e.printStackTrace();
        } catch (ExecutionException e){
            e.printStackTrace();
        }

    }

}
