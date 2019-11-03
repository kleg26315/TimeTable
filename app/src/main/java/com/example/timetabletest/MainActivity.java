package com.example.timetabletest;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class MainActivity extends AppCompatActivity {
    private TextView main_date;
    private TextView mon;
    private TextView tue;
    private TextView wed;
    private TextView thu;
    private TextView fri;
    private TextView text_mon;
    private TextView text_tue;
    private TextView text_wed;
    private TextView text_thu;
    private TextView text_fri;
    public Integer count =0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        android.support.v7.app.ActionBar ab = getSupportActionBar();
        ab.setTitle("시간표");
        setContentView(R.layout.activity_main);

        main_date = (TextView) findViewById(R.id.main_day);
        mon = (TextView) findViewById(R.id.Mon);
        tue = (TextView) findViewById(R.id.Tue);
        wed = (TextView) findViewById(R.id.Wed);
        thu = (TextView) findViewById(R.id.Thu);
        fri = (TextView) findViewById(R.id.Fri);
        text_mon = (TextView) findViewById(R.id.day_Mon);
        text_tue = (TextView) findViewById(R.id.day_Tue);
        text_wed = (TextView) findViewById(R.id.day_Wed);
        text_thu = (TextView) findViewById(R.id.day_Thu);
        text_fri = (TextView) findViewById(R.id.day_Fri);
        Button left = (Button) findViewById(R.id.left_btn);
        Button right = (Button) findViewById(R.id.right_btn);
        Button today = (Button) findViewById(R.id.today_btn);

        datesetting(0);
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count -= 1;
                datesetting(count);
            }
        });

        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count +=1;
                datesetting(count);
            }
        });

        today.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count = 0;
                datesetting(count);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id==R.id.action_search){
            Intent intent = new Intent(this, SearchActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }


    public void datesetting(int count){
        Calendar[] cal = new GregorianCalendar[11];
        for(int i =0; i<11; i++){
            cal[i] = new GregorianCalendar();
            cal[i].add(Calendar.DATE, i-6+count);
        }
        main_date.setText(cal[6].get(Calendar.YEAR)+"년 "+(cal[6].get(Calendar.MONTH)+1)+"월");
        switch (cal[6].get(Calendar.DAY_OF_WEEK)){
            case 2:
                mon.setTextColor(Color.parseColor("#03A9F4"));
                text_mon.setTextColor(Color.parseColor("#03A9F4"));
                tue.setTextColor(Color.parseColor("#000000"));
                text_tue.setTextColor(Color.parseColor("#000000"));
                wed.setTextColor(Color.parseColor("#000000"));
                text_wed.setTextColor(Color.parseColor("#000000"));
                thu.setTextColor(Color.parseColor("#000000"));
                text_thu.setTextColor(Color.parseColor("#000000"));
                fri.setTextColor(Color.parseColor("#000000"));
                text_fri.setTextColor(Color.parseColor("#000000"));
                text_mon.setText(String.valueOf(cal[6].get(Calendar.DAY_OF_MONTH)));
                text_tue.setText(String.valueOf(cal[7].get(Calendar.DAY_OF_MONTH)));
                text_wed.setText(String.valueOf(cal[8].get(Calendar.DAY_OF_MONTH)));
                text_thu.setText(String.valueOf(cal[9].get(Calendar.DAY_OF_MONTH)));
                text_fri.setText(String.valueOf(cal[10].get(Calendar.DAY_OF_MONTH)));
                break;
            case 3:
                mon.setTextColor(Color.parseColor("#000000"));
                text_mon.setTextColor(Color.parseColor("#000000"));
                tue.setTextColor(Color.parseColor("#03A9F4"));
                text_tue.setTextColor(Color.parseColor("#03A9F4"));
                wed.setTextColor(Color.parseColor("#000000"));
                text_wed.setTextColor(Color.parseColor("#000000"));
                thu.setTextColor(Color.parseColor("#000000"));
                text_thu.setTextColor(Color.parseColor("#000000"));
                fri.setTextColor(Color.parseColor("#000000"));
                text_fri.setTextColor(Color.parseColor("#000000"));
                text_mon.setText(String.valueOf(cal[5].get(Calendar.DAY_OF_MONTH)));
                text_tue.setText(String.valueOf(cal[6].get(Calendar.DAY_OF_MONTH)));
                text_wed.setText(String.valueOf(cal[7].get(Calendar.DAY_OF_MONTH)));
                text_thu.setText(String.valueOf(cal[8].get(Calendar.DAY_OF_MONTH)));
                text_fri.setText(String.valueOf(cal[9].get(Calendar.DAY_OF_MONTH)));
                break;
            case 4:
                mon.setTextColor(Color.parseColor("#000000"));
                text_mon.setTextColor(Color.parseColor("#000000"));
                tue.setTextColor(Color.parseColor("#000000"));
                text_tue.setTextColor(Color.parseColor("#000000"));
                wed.setTextColor(Color.parseColor("#03A9F4"));
                text_wed.setTextColor(Color.parseColor("#03A9F4"));
                thu.setTextColor(Color.parseColor("#000000"));
                text_thu.setTextColor(Color.parseColor("#000000"));
                fri.setTextColor(Color.parseColor("#000000"));
                text_fri.setTextColor(Color.parseColor("#000000"));
                text_mon.setText(String.valueOf(cal[4].get(Calendar.DAY_OF_MONTH)));
                text_tue.setText(String.valueOf(cal[5].get(Calendar.DAY_OF_MONTH)));
                text_wed.setText(String.valueOf(cal[6].get(Calendar.DAY_OF_MONTH)));
                text_thu.setText(String.valueOf(cal[7].get(Calendar.DAY_OF_MONTH)));
                text_fri.setText(String.valueOf(cal[8].get(Calendar.DAY_OF_MONTH)));
                break;
            case 5:
                mon.setTextColor(Color.parseColor("#000000"));
                text_mon.setTextColor(Color.parseColor("#000000"));
                tue.setTextColor(Color.parseColor("#000000"));
                text_tue.setTextColor(Color.parseColor("#000000"));
                wed.setTextColor(Color.parseColor("#000000"));
                text_wed.setTextColor(Color.parseColor("#000000"));
                thu.setTextColor(Color.parseColor("#03A9F4"));
                text_thu.setTextColor(Color.parseColor("#03A9F4"));
                fri.setTextColor(Color.parseColor("#000000"));
                text_fri.setTextColor(Color.parseColor("#000000"));
                text_mon.setText(String.valueOf(cal[3].get(Calendar.DAY_OF_MONTH)));
                text_tue.setText(String.valueOf(cal[4].get(Calendar.DAY_OF_MONTH)));
                text_wed.setText(String.valueOf(cal[5].get(Calendar.DAY_OF_MONTH)));
                text_thu.setText(String.valueOf(cal[6].get(Calendar.DAY_OF_MONTH)));
                text_fri.setText(String.valueOf(cal[7].get(Calendar.DAY_OF_MONTH)));
                break;
            case 6:
                mon.setTextColor(Color.parseColor("#000000"));
                text_mon.setTextColor(Color.parseColor("#000000"));
                tue.setTextColor(Color.parseColor("#000000"));
                text_tue.setTextColor(Color.parseColor("#000000"));
                wed.setTextColor(Color.parseColor("#000000"));
                text_wed.setTextColor(Color.parseColor("#000000"));
                thu.setTextColor(Color.parseColor("#000000"));
                text_thu.setTextColor(Color.parseColor("#000000"));
                fri.setTextColor(Color.parseColor("#03A9F4"));
                text_fri.setTextColor(Color.parseColor("#03A9F4"));
                text_mon.setText(String.valueOf(cal[2].get(Calendar.DAY_OF_MONTH)));
                text_tue.setText(String.valueOf(cal[3].get(Calendar.DAY_OF_MONTH)));
                text_wed.setText(String.valueOf(cal[4].get(Calendar.DAY_OF_MONTH)));
                text_thu.setText(String.valueOf(cal[5].get(Calendar.DAY_OF_MONTH)));
                text_fri.setText(String.valueOf(cal[6].get(Calendar.DAY_OF_MONTH)));
                break;
            case 7:
                mon.setTextColor(Color.parseColor("#000000"));
                text_mon.setTextColor(Color.parseColor("#000000"));
                tue.setTextColor(Color.parseColor("#000000"));
                text_tue.setTextColor(Color.parseColor("#000000"));
                wed.setTextColor(Color.parseColor("#000000"));
                text_wed.setTextColor(Color.parseColor("#000000"));
                thu.setTextColor(Color.parseColor("#000000"));
                text_thu.setTextColor(Color.parseColor("#000000"));
                fri.setTextColor(Color.parseColor("#000000"));
                text_fri.setTextColor(Color.parseColor("#000000"));
                text_mon.setText(String.valueOf(cal[1].get(Calendar.DAY_OF_MONTH)));
                text_tue.setText(String.valueOf(cal[2].get(Calendar.DAY_OF_MONTH)));
                text_wed.setText(String.valueOf(cal[3].get(Calendar.DAY_OF_MONTH)));
                text_thu.setText(String.valueOf(cal[4].get(Calendar.DAY_OF_MONTH)));
                text_fri.setText(String.valueOf(cal[5].get(Calendar.DAY_OF_MONTH)));
                break;
            case 1:
                mon.setTextColor(Color.parseColor("#000000"));
                text_mon.setTextColor(Color.parseColor("#000000"));
                tue.setTextColor(Color.parseColor("#000000"));
                text_tue.setTextColor(Color.parseColor("#000000"));
                wed.setTextColor(Color.parseColor("#000000"));
                text_wed.setTextColor(Color.parseColor("#000000"));
                thu.setTextColor(Color.parseColor("#000000"));
                text_thu.setTextColor(Color.parseColor("#000000"));
                fri.setTextColor(Color.parseColor("#000000"));
                text_fri.setTextColor(Color.parseColor("#000000"));
                text_mon.setText(String.valueOf(cal[0].get(Calendar.DAY_OF_MONTH)));
                text_tue.setText(String.valueOf(cal[1].get(Calendar.DAY_OF_MONTH)));
                text_wed.setText(String.valueOf(cal[2].get(Calendar.DAY_OF_MONTH)));
                text_thu.setText(String.valueOf(cal[3].get(Calendar.DAY_OF_MONTH)));
                text_fri.setText(String.valueOf(cal[4].get(Calendar.DAY_OF_MONTH)));
                break;
        }
    }

}
