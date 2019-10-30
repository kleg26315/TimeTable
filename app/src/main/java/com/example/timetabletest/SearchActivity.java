package com.example.timetabletest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.timetabletest.Model.LectureData;
import com.example.timetabletest.Util.SearchAdapter;
import com.example.timetabletest.Util.Task;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class SearchActivity extends AppCompatActivity {

    ArrayList<LectureData> lectureDataList;
    public EditText editsearch;
    public ListView listView;
    public SearchAdapter searchAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        editsearch = (EditText)findViewById(R.id.editSearch);

        InitializeLectureData();

        listView = (ListView)findViewById(R.id.listView);
        listView.setTextFilterEnabled(true);

        searchAdapter = new SearchAdapter(this,lectureDataList);

        listView.setAdapter(searchAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id){
                Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                intent.putExtra("lecture", searchAdapter.getItem(position).getLecture());
                intent.putExtra("strTime", searchAdapter.getItem(position).getStrTime());
                intent.putExtra("endTime", searchAdapter.getItem(position).getEndTime());
                intent.putExtra("dayofweek", searchAdapter.getItem(position).getDayofweek());
                intent.putExtra("code", searchAdapter.getItem(position).getCode());
                intent.putExtra("professor", searchAdapter.getItem(position).getProfessor());
                intent.putExtra("location", searchAdapter.getItem(position).getLocation());

                startActivity(intent);
            }
        });

        editsearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(editsearch.getText().length() == 0){
                    searchAdapter.filter(editsearch.getText().toString());
                } else {
                    searchAdapter.filter(editsearch.getText().toString());
                }
            }
        });
    }

    public void InitializeLectureData()
    {
        String resultText;

        lectureDataList = new ArrayList<LectureData>();
        try {
            resultText = new Task().execute("/lectures").get();
            jsonParser(resultText);

        } catch (InterruptedException e){
            e.printStackTrace();
        } catch (ExecutionException e){
            e.printStackTrace();
        }

    }

    public String[] jsonParser(String jsonString) {
        String[] arraysum = new String[8];

        try {
            JSONArray jarray = new JSONObject(jsonString).getJSONArray("Items");
            for (int i = 0; i < jarray.length(); i++) {
                JSONObject jObject = jarray.getJSONObject(i);

                arraysum[0] = jObject.getString("lecture");
                arraysum[1] = jObject.getString("start_time");
                arraysum[2] = jObject.getString("end_time");
                arraysum[3] = jObject.getString("dayofweek");
                arraysum[4] = jObject.getString("code");
                arraysum[5] = jObject.getString("professor");
                arraysum[6] = jObject.getString("location");

                lectureDataList.add(new LectureData(arraysum[0],arraysum[1],arraysum[2],arraysum[3],arraysum[4],arraysum[5],arraysum[6]));

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return arraysum;
    }
}